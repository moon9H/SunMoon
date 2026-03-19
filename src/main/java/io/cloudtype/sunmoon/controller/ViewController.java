package io.cloudtype.sunmoon.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import io.cloudtype.sunmoon.auth.AuthenticatedUser;

@Controller
public class ViewController {

    @GetMapping("/")
    public String index(
        @AuthenticationPrincipal AuthenticatedUser authenticatedUser,
        Model model
    ) {
        model.addAttribute("currentViewerId", authenticatedUser.getViewerId());
        model.addAttribute("currentDisplayName", authenticatedUser.getDisplayName());
        model.addAttribute("currentLoginId", authenticatedUser.getUsername());
        return "index";
    }

    @GetMapping("/login")
    public String login(Authentication authentication) {
        if (authentication != null
            && authentication.isAuthenticated()
            && !(authentication instanceof AnonymousAuthenticationToken)) {
            return "redirect:/";
        }

        return "login";
    }
}
