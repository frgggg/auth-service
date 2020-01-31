package cft.focusstart.finalprogect.authservice.service.impl;

import cft.focusstart.finalprogect.authservice.model.SystemUser;
import cft.focusstart.finalprogect.authservice.model.SystemUserRole;
import cft.focusstart.finalprogect.authservice.repository.SystemUserRepository;
import cft.focusstart.finalprogect.authservice.service.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Service
public class SystemUserServiceImpl implements SystemUserService {

    private SystemUserRepository systemUserRepository;
    private EntityManager entityManager;

    @Autowired
    protected SystemUserServiceImpl(SystemUserRepository systemUserRepository, EntityManager entityManager) {
        this.systemUserRepository = systemUserRepository;
        this.entityManager = entityManager;
    }

    @Autowired
    PasswordEncoder encoder;

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public SystemUser saveSystemUser(String name, String login, String password, SystemUserRole systemUserRole) {
        SystemUser userForSave = new SystemUser(name, login, encoder.encode(password), systemUserRole);
        SystemUser savedUser = systemUserRepository.save(userForSave);
        entityManager.flush();
        return savedUser;
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public SystemUser updateSystemUserByLogin(String login, String name, String password) {
        SystemUser updatedSystemUser = systemUserRepository.updateSystemUserByLogin(login, name, encoder.encode(password));
        entityManager.flush();
        return updatedSystemUser;
    }

    @Override
    public List<SystemUser> getAllSystemUsers() {
        ArrayList<SystemUser> allSystemUsers = new ArrayList<>();
        systemUserRepository.findAll().forEach(allSystemUsers::add);
        return allSystemUsers;
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void deleteUserByLogin(String login) {
        systemUserRepository.deleteByLogin(login);
    }

    /*@PostConstruct
    void testSetFirstAdmin() {
        saveSystemUser("admin name", "admin", "admin",SystemUserRole.ADMIN );
    }*/
}
