package com.club.masterpiece.web.annotation;

import org.springframework.security.test.context.support.WithSecurityContext;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = WithMockOAuthUserSecurityContextFactory.class)
public @interface WithMockOAuthUser {

    String value() default "";

    String username() default "";

    String[] roles() default {"ADMIN", "MASTER_USER", "MANAGER_USER", "GENERAL_USER"};

    String[] authorities() default {};

    String password() default "password";

    String id() default "";

    String email() default "";

    String profile() default "";
}
