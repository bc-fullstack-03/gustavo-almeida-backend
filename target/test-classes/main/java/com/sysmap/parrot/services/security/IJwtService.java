package com.sysmap.parrot.services.security;


import java.util.UUID;

public interface IJwtService {
    String generateToken(UUID userId);
    boolean isValidToken(String token, String userId);
}
