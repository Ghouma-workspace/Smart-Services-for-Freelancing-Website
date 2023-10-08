package com.backengtest.demo.dto;
import com.backengtest.demo.model.ProjectApplication;
import com.backengtest.demo.model.ProjectWorking;
import com.backengtest.demo.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    //private String password;
    private int age;
    private String gender;
    private double avgVote;
    //private UserRole userRole;
    //private Instant created;
    //private Boolean enabled;
    private Long profileId;
}
