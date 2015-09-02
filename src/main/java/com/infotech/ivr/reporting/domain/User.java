package com.infotech.ivr.reporting.domain;

import java.time.LocalDateTime;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Future;

import org.springframework.format.annotation.DateTimeFormat;
import static org.springframework.format.annotation.DateTimeFormat.ISO;
import org.hibernate.validator.constraints.NotEmpty; 

/**
 * simple business object representing a user.
 *
 * @author Sevak Gharibian
 *
 */
public class User {

    private Long id;
    private String username;
    private String password;

    private LocalDateTime expireDate;

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @NotEmpty
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Size(min=6, max=10)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //@NotNull
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    //@DateTimeFormat(iso = ISO.DATE)
    public LocalDateTime getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDateTime expireDate) {
        this.expireDate = expireDate;
    }

    @Override
    public String toString() {
        return String.format("User[id:%d, username:%s, expireDate:%s]", id, username, expireDate);
    }
}
