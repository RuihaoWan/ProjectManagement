package com.rio.proj.entity;

import com.rio.proj.constant.Position;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "proj_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Long id;

    @Basic
    @Column(name = "username",nullable = false)
    private String username;

    @Basic
    @Column(name = "token",nullable = false)
    private String token;

    @Basic
    @Column(name = "position",nullable = false)
    private Integer position;

    public User(String username,String token,String position){
        this.username = username;
        this.token = token;
        this.position = Position.Match(position);
    }
}
