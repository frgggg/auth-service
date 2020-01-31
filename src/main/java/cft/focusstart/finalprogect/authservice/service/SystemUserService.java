package cft.focusstart.finalprogect.authservice.service;

import cft.focusstart.finalprogect.authservice.model.SystemUser;
import cft.focusstart.finalprogect.authservice.model.SystemUserRole;

import java.util.List;

public interface SystemUserService {
    SystemUser saveSystemUser(String name, String login, String password, SystemUserRole systemUserRole);

    SystemUser updateSystemUserByLogin(String login, String name, String password);

    List<SystemUser> getAllSystemUsers();

    void deleteUserByLogin(String login);
}
