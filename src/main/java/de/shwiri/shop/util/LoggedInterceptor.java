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

//Bind interceptor to this class
@Logged
@Interceptor
@Priority(Interceptor.Priority.APPLICATION)
public class LoggedInterceptor {

    @Inject
    private Logger logger;

    @Inject
    private LoginController loginController;

    //Could populate this from DB or security manager
    // with name of currently executing user
    private String user = loginController.getLoggedInUserEmail();

    //This method will be called and passed invocation context by container
    @AroundInvoke
    public Object logMethodCall(InvocationContext context) throws Exception {
        //Log for example user who called method and time
        logger.log(Level.INFO, "User {0} invoked {1} method at {2}", new Object[]{user, context.getMethod().getName(), LocalDate.now()});
        return context.proceed();
    }

}
