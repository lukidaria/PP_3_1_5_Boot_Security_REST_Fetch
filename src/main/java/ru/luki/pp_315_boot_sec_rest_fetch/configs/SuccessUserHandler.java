package ru.luki.pp_315_boot_sec_rest_fetch.configs;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@Component
public class SuccessUserHandler implements AuthenticationSuccessHandler {
    // Spring Security использует объект Authentication, пользователя авторизованной сессии.


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        if (roles.contains("ROLE_ADMIN") && roles.contains("ROLE_USER")) {
            response.sendRedirect("/admin");
            return;
        }
        if (roles.contains("ROLE_ADMIN")) {
            response.sendRedirect("/admin");
        }
        if (roles.contains("ROLE_USER")) {
            response.sendRedirect("/admin");
        }
    }
}