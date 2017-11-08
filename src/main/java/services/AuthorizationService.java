package services;

import entity.User;

public interface AuthorizationService {
    User auth(String login, String password);
    boolean getIsFullAccsessFromSecurityContext();
}
