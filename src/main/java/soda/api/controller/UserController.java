package soda.api.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.*;

import soda.api.entity.User;
import soda.api.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.ui.Model;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;


    @GetMapping("/all")
    public String publicData() {
        return "Ruta pública";
    }

    @GetMapping("/index")
public String index(Model model, OAuth2AuthenticationToken authentication) {

    if (authentication.getPrincipal() instanceof org.springframework.security.oauth2.core.user.OAuth2User oAuth2User) {

        model.addAttribute("name", oAuth2User.getAttribute("name"));
        model.addAttribute("picture", oAuth2User.getAttribute("picture"));

    } else {
        model.addAttribute("name", authentication.getName());
        model.addAttribute("picture", null);
    }

    return "index";
}

    @GetMapping("/user/data")
    public String userData() {
        return "Datos para USER o ADMIN";
    }

    @GetMapping("/admin/data")
    public String adminData() {
        return "Datos solo para ADMIN";
    }

    @GetMapping("/auth/google/success")
    public Map<String, Object> loginSuccess(OAuth2AuthenticationToken authentication) {

        OAuth2User oAuth2User = authentication.getPrincipal();

        String email = oAuth2User.getAttribute("email");

        // 🔍 Buscar usuario

        User user = userRepository.findByUsername(email)
                .orElse(new User());

        // 🧠 Guardar datos
        user.setUsername(email);
        user.setName(oAuth2User.getAttribute("name"));
        user.setPicture(oAuth2User.getAttribute("picture"));
        user.setProvider("google");
        user.setRole("ROLE_USER");
        user.setPassword(null); // importante

        userRepository.save(user);

        // 📤 Respuesta
        Map<String, Object> data = new HashMap<>();
        data.put("message", "Login con Google exitoso");
        data.put("email", email);

        return data;
    }
}