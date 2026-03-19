package io.cloudtype.sunmoon.service;

import org.springframework.context.annotation.Profile;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.cloudtype.sunmoon.auth.AppUser;
import io.cloudtype.sunmoon.auth.AuthenticatedUser;
import io.cloudtype.sunmoon.repository.AppUserRepository;

@Profile("db")
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AppUserRepository appUserRepository;

    public CustomUserDetailsService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String loginId = username == null ? "" : username.trim();

        AppUser appUser = appUserRepository.findByLoginId(loginId)
            .orElseThrow(() -> new UsernameNotFoundException("User not found: " + loginId));

        return new AuthenticatedUser(appUser);
    }
}
