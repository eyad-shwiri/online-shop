package de.shwiri.shop.util;

import de.shwiri.shop.annotations.Logged;
import de.shwiri.shop.controller.LoginController;
import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

@Logged
@Interceptor
@Priority(Interceptor.Priority.APPLICATION)
public class LoggedInterceptor {

    @Inject
    private Logger logger;

    @Inject
    private LoginController loginController;

    private final String user = loginController.getLoggedInUserEmail();

    @AroundInvoke
    public Object logMethodCall(InvocationContext context) throws Exception {
        logger.log(Level.INFO, "User {0} invoked {1} method at {2}", new Object[]{user, context.getMethod().getName(), LocalDate.now()});
        return context.proceed();
    }

}
