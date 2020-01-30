package cft.focusstart.finalprogect.authservice.service;

import cft.focusstart.finalprogect.authservice.model.SystemUser;
import cft.focusstart.finalprogect.authservice.model.SystemUserRole;

public interface SystemUserService {
    SystemUser saveUser(String name, String password);
    SystemUser saveQueryService(String name, String password);
    SystemUser saveProcessService(String name, String password);

    SystemUser updateUserById(Long id, String name, String password);
    SystemUser updateServiceById(Long id, String name, String password);

    SystemUser getUserById(Long id);
    SystemUser getAllUsers();
    SystemUser getAllUsersByRole(SystemUserRole systemUserRole);

    void deleteUserById(Long id);
}
