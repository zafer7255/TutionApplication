package com.coaching_system.CoachingApp.Model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Scope("prototype")
public class TeacherNewTable {
    @Id
    private String username;
    private String password;
    private String name;
    private String phoneNo;
    private String address;
    private List<String> subjectsTeach;
    private String email;
    private String imageName;
    private String imageType;
    @Lob
    private  byte[] imageData;
}
