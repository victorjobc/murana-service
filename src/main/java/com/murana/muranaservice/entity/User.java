package com.murana.muranaservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private String id;
    private String name;
    private String email;
    private String password;
    @OneToMany(mappedBy = "properUser", cascade = CascadeType.ALL)
    private Set<Phone> phones = new HashSet<>();
    private String token;
    private Boolean isActive;
    private Date created;
    private Date modified;
    private Date lastLogin;

    @PrePersist
    public void prePersist() {
        this.id = UUID.randomUUID().toString();
    }

    public User(String name, String email, String encodedPassword) {
        this.name = name;
        this.email = email;
        this.password = encodedPassword;
        this.isActive = true;
        this.created = new Date();
        this.modified = new Date();
        this.lastLogin = new Date();
    }

    public void addPhone(Phone phone) {
        this.phones.add(phone);
        phone.setProperUser(this);
    }
}