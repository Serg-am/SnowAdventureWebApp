package ru.snowadventure.SnowAdventureWebApp.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.snowadventure.SnowAdventureWebApp.models.UserWebApp;

import java.util.Collection;
import java.util.Collections;

public class UserWebAppDetails implements UserDetails {

    private final UserWebApp userWebApp;

    public UserWebAppDetails(UserWebApp userWebApp) {
        this.userWebApp = userWebApp;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(userWebApp.getRole().toString()));
    }

    public UserWebApp getUserWebApp() {
        return userWebApp;
    }

    @Override
    public String getPassword() {
        return this.userWebApp.getPassword();
    }

    @Override
    public String getUsername() {
        return this.userWebApp.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
