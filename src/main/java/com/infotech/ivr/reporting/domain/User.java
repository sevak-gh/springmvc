package com.infotech.ivr.reporting.domain;

import java.time.LocalDateTime;

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

    @Override
    public String toString() {
        return String.format("[id:%d, username:%s, expireDate:%s]", id, username, expireDate);
    }
}
