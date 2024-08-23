package com.user.etity;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class VerificationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;

    public String token;

    public LocalDateTime expireddate;

    @OneToOne
    private User user;

    public VerificationToken(User user) {
        this.user = user;
    }

    public VerificationToken() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getExpireddate() {
        return expireddate;
    }

    public void setExpireddate(LocalDateTime expireddate) {
        this.expireddate = expireddate;
    }

    @Override
    public String toString() {
        return "VerificationToken{" +
                "id=" + id +
                ", token='" + token + '\'' +
                ", expireddate=" + expireddate +
                '}';

    }
}
