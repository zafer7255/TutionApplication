package com.coaching_system.CoachingApp.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin {

    @Id
    private int adminId;
    private String name;
    private String phoneNo;
    private String address;
    private String email;
    private String imageName;
    private String imageType;
    @Lob
    private  byte[] imageData;
}
