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
    @GetMapping("/getallstudents")
    public ResponseEntity<List<StudentNewTable>> GetAllStudents()
    {
        List<StudentNewTable> students = studentService.GetAllStudent();
        if(students.size() > 0 && students.get(0).getRollNo() != 0)
            return new ResponseEntity<>(students,HttpStatus.FOUND);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getstudentbyname/{name}")
    public ResponseEntity<List<StudentNewTable>> GetStudentByName(@PathVariable String name)
    {
        List<StudentNewTable> students = studentService.GetStudentByName(name);
        if(students.size() > 0 && students.get(0).getRollNo() != 0)
             return new ResponseEntity<>(students,HttpStatus.FOUND);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("getstudentbyid/{id}")
    public ResponseEntity<StudentNewTable> GetStudentById(@PathVariable int id)
    {
            StudentNewTable student = studentService.GetStudentById(id);
            if(student.getRollNo() != 0)
               return new ResponseEntity<>(student,HttpStatus.FOUND);
            else
               return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/poststudents")
    public ResponseEntity<?> PostStudents(@RequestPart StudentNewTable student , @RequestPart MultipartFile imageFile){
        try {
            String result = studentService.SaveOrUpdate(student , imageFile);
                return new ResponseEntity<>(result,HttpStatus.ACCEPTED);
        } catch (IOException e)
        {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/updatestudent")
    public ResponseEntity<?> UpdateStudent(@RequestPart StudentNewTable student , @RequestPart MultipartFile imageFile)
    {
        try {
            String result = studentService.SaveOrUpdate(student , imageFile);
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
