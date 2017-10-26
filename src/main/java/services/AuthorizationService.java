package services;

import pojo.User;

public interface AuthorizationService {
    User auth(String login, String password);
}
