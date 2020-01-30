package cft.focusstart.finalprogect.authservice.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "system_user")
public class SystemUser {

    public static final int NAME_MAX_LEN = 100;
    public static final int NAME_MIN_LEN = 4;

    public static final int PASSWORD_MAX_LEN = 100;
    public static final int PASSWORD_MIN_LEN = 8;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", length = NAME_MAX_LEN, nullable = false, unique = true)
    @Size(min = NAME_MIN_LEN, max = NAME_MAX_LEN)
    private String name;

    @Column(name = "password", length = PASSWORD_MAX_LEN, nullable = false)
    @Size(min = PASSWORD_MIN_LEN, max = PASSWORD_MAX_LEN)
    private String password;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private SystemUserRole role;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    protected SystemUser()
    {}

    public SystemUser(String userName, String password, SystemUserRole role)
    {
        this.name = userName;
        this.password = password;
        this.role = role;
        isActive = true;
    }

}
