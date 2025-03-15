package com.coaching_system.CoachingApp.Controller;


import com.coaching_system.CoachingApp.Model.TeacherNewTable;
import com.coaching_system.CoachingApp.Service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class TeacherController {

    @Autowired
    private TeacherService teacherService;


    @GetMapping("/getteacherbyusername/{username}")
    public ResponseEntity<TeacherNewTable> GetTeacherById(@PathVariable String username)
    {
        TeacherNewTable teacher = teacherService.GetTeacherByUsername(username);
        if (teacher.getUsername() != null)
          return new ResponseEntity<>(teacher, HttpStatus.FOUND);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/updateteacher")
    public ResponseEntity<?> UpdateTeacher(@RequestBody TeacherNewTable teacher , @RequestPart MultipartFile imageFile)
    {
        try {
            String result = teacherService.Update(teacher , imageFile);
            return new ResponseEntity<>(result,HttpStatus.ACCEPTED);
        } catch (IOException e)
        {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteteacher/{username}")
    public ResponseEntity<?> DeleteTeacher(@PathVariable String username)
    {
        String result = teacherService.DeleteTeacher(username);
        if (result == "Deleted Success")
            return new ResponseEntity<>(result,HttpStatus.OK);
        else
            return new ResponseEntity<>(result,HttpStatus.NOT_FOUND);
    }

}
