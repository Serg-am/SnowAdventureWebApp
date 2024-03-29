package ru.snowadventure.SnowAdventureWebApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.snowadventure.SnowAdventureWebApp.models.UserWebApp;
import ru.snowadventure.SnowAdventureWebApp.repositories.UsersRepository;

import java.util.List;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UsersRepository userRepo;

    @Autowired
    private MailSender mailSender;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${hostname}")
    private String hostname;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserWebApp user = userRepo.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }

    public boolean addActivationCodeUser(UserWebApp user) {
        UserWebApp userFromDb = userRepo.findByUsername(user.getUsername());

        if (userFromDb != null) {
            return false;
        }

        user.setActivationCode(UUID.randomUUID().toString());

        sendMessage(user);

        return true;
    }

    private void sendMessage(UserWebApp user) {
        if (!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format(
                    "Приветствуем тебя, твой логин: %s \n" +
                            "Добро пожаловать в Snow-Adventure.\n" +
                            "Для активации аккаунта и подтверждения электронной почты нужно пройти по ссылке:\n" +
                            "http://%s/auth/activate/%s\n" +
                            "Хороших тебе покатушек!",
                    user.getUsername(),
                    hostname,
                    user.getActivationCode()
            );

            mailSender.send(user.getEmail(), "Activation code", message);

        }
    }

    public boolean activateUser(String code) {
        UserWebApp user = userRepo.findByActivationCode(code);

        if (user == null) {
            return false;
        }

        user.setActivationCode(null);
        user.setActive(true);
        userRepo.save(user);

        return true;
    }

    public List<UserWebApp> findAll() {
        return userRepo.findAll();
    }

    /*public void saveUser(UserWebApp user, String username, Map<String, String> form) {
        user.setUsername(username);

        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRole().clear();

        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRole().add(Role.valueOf(key));
            }
        }

        userRepo.save(user);
    }*/

    public void updateProfile(UserWebApp user, String password, String email) {
        String userEmail = user.getEmail();

        boolean isEmailChanged = (email != null && !email.equals(userEmail)) ||
                (userEmail != null && !userEmail.equals(email));

        if (isEmailChanged) {
            user.setEmail(email);

            if (!StringUtils.isEmpty(email)) {
                user.setActivationCode(UUID.randomUUID().toString());
            }
        }

        if (!StringUtils.isEmpty(password)) {
            user.setPassword(password);
        }

        userRepo.save(user);

        if (isEmailChanged) {
            sendMessage(user);
        }
    }

   /* public void subscribe(UserWebApp currentUser, UserWebApp user) {
        user.getSubscribers().add(currentUser);

        userRepo.save(user);
    }

    public void unsubscribe(User currentUser, User user) {
        user.getSubscribers().remove(currentUser);

        userRepo.save(user);
    }*/
}
