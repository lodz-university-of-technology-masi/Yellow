package edu.pl.masi.yellow.entity;

import javax.persistence.*;

@Entity
@Table(name="user_data")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="user_name")
    private String username;

    @Column(name="user_pass")
    private String password;

    @Column(name="user_role")
    private String role;

    public UserEntity(String username, String password) {
        this.username = username;
        this.password = password;
        this.role = "user";
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
