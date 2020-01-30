package cft.focusstart.finalprogect.authservice.model;

import org.springframework.security.core.GrantedAuthority;

public enum SystemUserRole implements GrantedAuthority {
    USER, QUERY_SERVICE, PROCESS_SERVICE,  ADMIN;

    @Override
    public String getAuthority()
    {
        return name();
    }
}