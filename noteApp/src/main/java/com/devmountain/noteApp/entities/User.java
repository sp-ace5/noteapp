package com.devmountain.noteApp.entities;

import com.devmountain.noteApp.dtos.UserDto;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Entity
@Table(name = "Users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;

    @Column (unique = true)
    private String username;

    @Column
    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JsonManagedReference
    private Set<Note> noteSet = new HashSet<>();

    public User(UserDto userDto) {
        if (userDto.getUsername() != null) {
            this.username= userDto.getUsername();
        }
        if (userDto.getPassword() != null) {
            this.password = userDto.getPassword();
        }
    }
}


