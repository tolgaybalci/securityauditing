package com.example.security.auditing.securityauditing.config;

import com.example.security.auditing.securityauditing.domain.User;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor(){
        return Optional.of(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
    }

}
