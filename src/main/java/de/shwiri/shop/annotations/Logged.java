package de.shwiri.shop.annotations;

import jakarta.interceptor.InterceptorBinding;
import java.lang.annotation.*;

@InterceptorBinding
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Inherited
public @interface Logged {
}
