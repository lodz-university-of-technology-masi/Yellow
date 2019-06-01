package edu.pl.masi.yellow.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="user_data")
public class UserEntity {
    public enum UserRole {
        USER,
        REDACTOR,
        MODERATOR
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("userId")
    private int id;

    @Column(name="user_name")
    @JsonProperty("userName")
    private String username;

    @Column(name="user_pass")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(name="user_role")
    @Enumerated(EnumType.STRING)
    @JsonProperty("userRole")
    private UserRole role;

    public UserEntity(String username, String password) {
        this.username = username;
        this.password = password;
        this.role = UserRole.USER;
    }

    public UserEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
