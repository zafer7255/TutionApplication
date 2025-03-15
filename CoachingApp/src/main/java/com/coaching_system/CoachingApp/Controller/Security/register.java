package com.coaching_system.CoachingApp.Controller.Security;


import com.coaching_system.CoachingApp.Controller.StudentController;
import com.coaching_system.CoachingApp.Model.Admin;
import com.coaching_system.CoachingApp.Model.StudentNewTable;
import com.coaching_system.CoachingApp.Model.TeacherNewTable;
import com.coaching_system.CoachingApp.Service.Registration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;

@RestController
public class register {
    
    @Autowired
    private Registration registration;
    @PostMapping("/register/student")
    public ResponseEntity<?> register(@RequestBody StudentNewTable studentNewTable)
    {
        String result = registration.registerUser(studentNewTable);
        if (result == "username already exist") {
            return new ResponseEntity<>("Username already exist",HttpStatus.ALREADY_REPORTED);
        } else if (result == "Roll already exist") {
            return new ResponseEntity<>(result,HttpStatus.ALREADY_REPORTED);
        }
        return new ResponseEntity<>(result,HttpStatus.ACCEPTED);
    }
    @PostMapping("/register/teacher")
    public ResponseEntity<?> register(@RequestBody TeacherNewTable teacher)
    {
        String result = registration.registerUser(teacher);
        if (result == "username already exist")
        {
            return new ResponseEntity<>("Username already exist",HttpStatus.ALREADY_REPORTED);
        }
        return new ResponseEntity<>(result,HttpStatus.ACCEPTED);
    }
    @PostMapping("/register/admin")
    public ResponseEntity<?> register(@RequestBody Admin admin)
    {
        String result = registration.registerUser(admin);
        if(result == "username already exist")
        {
            return new ResponseEntity<>("Username already exist",HttpStatus.ALREADY_REPORTED);
        }
        return new ResponseEntity<>(result,HttpStatus.ACCEPTED);
    }
}
