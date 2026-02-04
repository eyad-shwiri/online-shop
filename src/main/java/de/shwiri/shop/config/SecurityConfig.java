package de.shwiri.shop.config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import jakarta.security.enterprise.authentication.mechanism.http.LoginToContinue;

@CustomFormAuthenticationMechanismDefinition(
        loginToContinue = @LoginToContinue(loginPage = "/login.xhtml", errorPage = "/error.xhtml")
)
@ApplicationScoped
public class SecurityConfig {
}
