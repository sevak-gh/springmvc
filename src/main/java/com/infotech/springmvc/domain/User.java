package com.infotech.springmvc.domain;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.HashSet;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;

import org.hibernate.validator.constraints.NotEmpty; 

/**
 * simple business object representing a user.
 *
 * @author Sevak Gharibian
 *
 */
@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotEmpty
    @Column(name = "username", nullable = false)
    private String username;

    @Size(min=6)
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "expireDate")
    private LocalDateTime expireDate;

    @ManyToMany
    @JoinTable(name="userrole", 
               joinColumns = {@JoinColumn(name="fk_user", nullable=false)},
               inverseJoinColumns = {@JoinColumn(name="fk_role", nullable=false)}) 
    private Set<Role> roles = new HashSet<Role>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDateTime expireDate) {
        this.expireDate = expireDate;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return String.format("User[id:%d, username:%s, expireDate:%s]", id, username, expireDate);
    }
}
