package com.infotech.springmvc.domain;

import java.util.Set;
import java.util.HashSet;
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
 * simple business object representing a role.
 *
 * @author Sevak Gharibian
 *
 */
@Entity
@Table(name="role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotEmpty
    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany
    @JoinTable(name="rolepermission", 
               joinColumns = {@JoinColumn(name="fk_role", nullable=false)},
               inverseJoinColumns = {@JoinColumn(name="fk_permission", nullable=false)}) 
    private Set<Permission> permissions = new HashSet<Permission>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        return String.format("Role[id:%d, name:%s]", id, name);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Role)) {
            return false;
        }        
        Role role = (Role)other;        
        if (this.id == role.id) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = 17;
        if (id != null) {
            result = 31 * result + id.hashCode();
        }
        return result;
    }
}
