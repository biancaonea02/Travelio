package com.example.demo.listener;


import com.example.demo.entity.User;
import com.example.demo.entity.UserPrincipal;
import com.example.demo.service.LogInAttemptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationSuccessListener {

    @Autowired
    private LogInAttemptService logInAttemptService;

    public AuthenticationSuccessListener(LogInAttemptService logInAttemptService) {
        this.logInAttemptService = logInAttemptService;
    }

    @EventListener
    public void onAuthenticationSuccess(AuthenticationSuccessEvent event)
    {
        Object principal = event.getAuthentication().getPrincipal();
        if(principal instanceof UserPrincipal)
        {
            UserPrincipal user = (UserPrincipal) event.getAuthentication().getPrincipal();
            logInAttemptService.evictUserFromLoginAttemptCache(user.getUsername());
        }
    }
}
