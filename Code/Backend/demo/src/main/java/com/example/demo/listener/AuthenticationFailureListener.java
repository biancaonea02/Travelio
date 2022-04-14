package com.example.demo.listener;

import com.example.demo.service.LogInAttemptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;


@Component
public class AuthenticationFailureListener {

    @Autowired
    private LogInAttemptService logInAttemptService;

    public AuthenticationFailureListener(LogInAttemptService logInAttemptService) {
        this.logInAttemptService = logInAttemptService;
    }

    @EventListener
    public void onAuthenticationFailure(AuthenticationFailureBadCredentialsEvent event) {
        Object principal = event.getAuthentication().getPrincipal();
        if(principal instanceof String)
        {
            String username = (String) event.getAuthentication().getPrincipal();
            logInAttemptService.addUserToLoginAttemptCache(username);
        }
    }
}
