package com.infotech.springmvc.domain;

import java.time.LocalDateTime;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;

import org.hibernate.validator.constraints.NotEmpty; 

/**
 * simple business object representing a audit log.
 *
 * @author Sevak Gharibian
 *
 */
@Entity
@Table(name="audit")
public class Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotEmpty
    @Column(name = "username")
    private String username;

    @Column(name = "action")
    private String action;

    @Column(name = "info")
    private String info;

    @Column(name = "dt", nullable = false)
    private LocalDateTime dateTime;
    
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

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return String.format("Audit[username:%s, action: %s, info:%s, dateTime:%s]", username, action, info, dateTime);
    }
}
