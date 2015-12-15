package com.infotech.ivr.reporting.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;

import org.hibernate.validator.constraints.NotEmpty; 

/**
 * simple business object representing a permission.
 *
 * @author Sevak Gharibian
 *
 */
@Entity
@Table(name="permission")
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotEmpty
    @Column(name = "name", nullable = false)
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("Permission[id:%d, name:%s]", id, name);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Permission)) {
            return false;
        }        
        Permission permission = (Permission)other; 
        if (this.id == permission.id) {
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
