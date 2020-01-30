package cft.focusstart.finalprogect.authservice.service.impl;

import cft.focusstart.finalprogect.authservice.model.SystemUser;
import cft.focusstart.finalprogect.authservice.model.SystemUserRole;
import cft.focusstart.finalprogect.authservice.repository.SystemUserRepository;
import cft.focusstart.finalprogect.authservice.service.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

@Service
public class SystemUserServiceImpl implements SystemUserService {

    @Autowired
    private SystemUserRepository systemUserRepository;

    @Autowired
    private EntityManager entityManager;

    private SystemUser save(SystemUser userForSave) {
        SystemUser savedUser = systemUserRepository.save(userForSave);
        entityManager.flush();
        return savedUser;
    }

    @Override
    public SystemUser saveSystemUser(String name, String password, SystemUserRole systemUserRole) {
        return save(new SystemUser(name, password, systemUserRole));
    }

}
