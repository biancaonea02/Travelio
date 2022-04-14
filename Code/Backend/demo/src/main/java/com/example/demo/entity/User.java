package com.example.demo.entity;

import org.springframework.context.annotation.PropertySource;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name ="users")
public class User implements Serializable {

        @Id
        @GeneratedValue(strategy = GenerationType.TABLE)
        @Column(name = "id", nullable = false, updatable = false)
        private Long id;

        @Column(name = "userId")
        private String userId;

        @Column(name = "firstName")
        private String firstName;

        @Column(name = "lastName")
        private String lastName;

        @Column(name = "username")
        private String username;

        @Column(name = "password")
        private String password;

        @Column(name = "email")
        private String email;

        @Column(name = "lastLoginDate")
        private Date lastLoginDate;

        @Column(name = "lastLoginDateDisplay")
        private Date lastLoginDateDisplay;

        @Column(name = "joinDate")
        private Date joinDate;

        @Column(name = "role")
        private String role; //ROLE_USER {read, edit}, ROLE_ADMIN {delete, create}

        @Column(name = "authorities")
        private String[] authorities;

        @Column(name = "active")
        private Boolean active;

        @Column(name = "notLocked")
        private Boolean notLocked;

       public User(Long id, String userId, String firstName, String lastName, String username, String password, String email,
                   Date lastLoginDate, Date lastLoginDateDisplay, Date joinDate, String role, String[] authorities,
                   Boolean active, Boolean notLocked)
       {
           this.id = id;
           this.userId = userId;
           this.firstName = firstName;
           this.lastName = lastName;
           this.username = username;
           this.password = password;
           this.email = email;
           this.lastLoginDate = lastLoginDate;
           this.lastLoginDateDisplay = lastLoginDateDisplay;
           this.joinDate = joinDate;
           this.role = role;
           this.authorities = authorities;
           this.active = active;
           this.notLocked = notLocked;
       }

    public User() {
    }

    public User(Long id, String firstName, String lastName, String username, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
    }

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public String getUserId() {return userId;}
    public void setUserId(String userId) {this.userId = userId;}

    public String getFirstName() {return firstName;}
    public void setFirstName(String firstName) {this.firstName = firstName;}

    public String getLastName() {return lastName;}
    public void setLastName(String lastName) {this.lastName = lastName;}

    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}

    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public Date getLastLoginDate() {return lastLoginDate;}
    public void setLastLoginDate(Date lastLoginDate) {this.lastLoginDate = lastLoginDate;}

    public Date getLastLoginDateDisplay() {return lastLoginDateDisplay;}
    public void setLastLoginDateDisplay(Date lastLoginDateDisplay) {this.lastLoginDateDisplay = lastLoginDateDisplay;}

    public Date getJoinDate() {return joinDate;}
    public void setJoinDate(Date joinDate) {this.joinDate = joinDate;}

    public String getRole() {return role;}
    public void setRole(String role) {this.role = role;}

    public String[] getAuthorities() {return authorities;}
    public void setAuthorities(String[] authorities) {this.authorities = authorities;}

    public Boolean getActive() {return active;}
    public void setActive(Boolean active) {this.active = active;}

    public Boolean getNotLocked() {return notLocked;}
    public void setNotLocked(Boolean notLocked) {this.notLocked = notLocked;}
}

