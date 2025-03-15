package com.coaching_system.CoachingApp.Controller;


import com.coaching_system.CoachingApp.Model.StudentNewTable;
import com.coaching_system.CoachingApp.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("getstudentbyusername/{username}")
    public ResponseEntity<StudentNewTable> GetStudentById(@PathVariable String username)
    {
            StudentNewTable student = studentService.GetStudentById(username);
            if(student.getRollNo() != 0)
               return new ResponseEntity<>(student,HttpStatus.FOUND);
            else
               return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/updatestudent")
    public ResponseEntity<?> UpdateStudent(@RequestPart StudentNewTable student , @RequestPart MultipartFile imageFile)
    {
        try {
            String result = studentService.Update(student , imageFile);
            return new ResponseEntity<>(result,HttpStatus.ACCEPTED);
        } catch (IOException e)
        {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deletestudent/{roll}")
    public ResponseEntity<?> DeleteStudent(@PathVariable int roll)
    {
        String result = studentService.DeleteStudent(roll);
        if (result ==  "Deleted Success")
            return new ResponseEntity<>(result,HttpStatus.OK);
        else
            return new ResponseEntity<>(result,HttpStatus.NOT_FOUND);
    }
}
