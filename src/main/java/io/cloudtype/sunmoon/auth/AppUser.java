package io.cloudtype.sunmoon.auth;

public class AppUser {

    private final Long id;
    private final String loginId;
    private final String passwordHash;
    private final String displayName;
    private final String viewerId;
    private final boolean enabled;

    public AppUser(
        Long id,
        String loginId,
        String passwordHash,
        String displayName,
        String viewerId,
        boolean enabled
    ) {
        this.id = id;
        this.loginId = loginId;
        this.passwordHash = passwordHash;
        this.displayName = displayName;
        this.viewerId = viewerId;
        this.enabled = enabled;
    }

    public Long getId() {
        return id;
    }

    public String getLoginId() {
        return loginId;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getViewerId() {
        return viewerId;
    }

    public boolean isEnabled() {
        return enabled;
    }
}
