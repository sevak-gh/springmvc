package com.infotech.ivr.reporting.domain;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.HashSet;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Future;
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.springframework.format.annotation.DateTimeFormat;
import static org.springframework.format.annotation.DateTimeFormat.ISO;
import org.hibernate.validator.constraints.NotEmpty; 

/**
 * simple business object representing a user.
 *
 * @author Sevak Gharibian
 *
 */
@NamedQueries({
    @NamedQuery(
        name = "findByUsername",
        query = "SELECT user FROM User user LEFT JOIN FETCH user.roles r LEFT JOIN FETCH r.permissions p WHERE user.username = :username"
    ),
    @NamedQuery(
        name = "findById",
        query = "SELECT user FROM User user LEFT JOIN FETCH user.roles WHERE user.id = :id"
    )
})
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
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private LocalDateTime expireDate;

    @ManyToMany
    @JoinTable(name="userrole", 
               joinColumns = {@JoinColumn(name="fk_user", nullable=false)},
               inverseJoinColumns = {@JoinColumn(name="fk_role", nullable=false)}) 
    private Set<Role> roles = new HashSet<Role>();

    public Long getId() {
        return id;
    }

    public void setId(long id) {
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
