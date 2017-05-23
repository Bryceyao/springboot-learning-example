package org.spring.springboot.util.interceptor;

import org.springframework.stereotype.Service;

@Service
public class ValidationService {
    
    
    public boolean verifyAccessToken(String accessToken){
        
        System.err.println(accessToken);
        return true;
    }
}
