package cft.focusstart.finalprogect.authservice.service;

import cft.focusstart.finalprogect.authservice.model.SystemUser;
import cft.focusstart.finalprogect.authservice.model.SystemUserRole;

public interface SystemUserService {
    SystemUser saveSystemUser(String name, String password, SystemUserRole systemUserRole);
    SystemUser updateSystemUserById(Long id, String name, String password);

    SystemUser getUserById(Long id);
    SystemUser getAllSystemUsers();
    SystemUser getAllSystemUsersByRole(SystemUserRole systemUserRole);

    void deleteUserById(Long id);
}
