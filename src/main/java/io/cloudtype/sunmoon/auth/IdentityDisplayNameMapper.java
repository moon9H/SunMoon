package io.cloudtype.sunmoon.auth;

import java.util.Map;

public final class IdentityDisplayNameMapper {

    private static final Map<String, String> DISPLAY_NAME_BY_VIEWER_ID = Map.of(
        "Moon", "문규",
        "Sun", "선영"
    );

    private IdentityDisplayNameMapper() {
    }

    public static String toDisplayName(String viewerId) {
        if (viewerId == null) {
            return "";
        }

        return DISPLAY_NAME_BY_VIEWER_ID.getOrDefault(viewerId, viewerId);
    }
}
