package io.cloudtype.sunmoon.auth;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

public class AuthenticatedUser extends User {

    private final Long id;
    private final String displayName;
    private final String viewerId;

    public AuthenticatedUser(AppUser appUser) {
        super(
            appUser.getLoginId(),
            appUser.getPasswordHash(),
            appUser.isEnabled(),
            true,
            true,
            true,
            AuthorityUtils.createAuthorityList("ROLE_USER")
        );
        this.id = appUser.getId();
        this.displayName = appUser.getDisplayName();
        this.viewerId = appUser.getViewerId();
    }

    public Long getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getViewerId() {
        return viewerId;
    }
}
