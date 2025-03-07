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
    @GetMapping("/getallteachers")
    public ResponseEntity<List<TeacherNewTable>> GetAllStudent()
    {
        List<TeacherNewTable> teachers = teacherService.GetAllStudent();
        if (teachers.size() > 0 && teachers.get(0).getId() != 0)
          return new ResponseEntity<>(teachers,HttpStatus.FOUND);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getteacherbyname/{name}")
    public ResponseEntity<List<TeacherNewTable>> GetTeacherByName(@PathVariable String name)
    {
        List<TeacherNewTable> teachers = teacherService.GetTeacherByName(name);
        if (teachers.get(0).getId() != 0)
          return new ResponseEntity<>(teachers,HttpStatus.FOUND);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getteacherbyid/{id}")
    public ResponseEntity<TeacherNewTable> GetTeacherById(@PathVariable int id)
    {
        TeacherNewTable teacher = teacherService.GetTeacherById(id);
        if (teacher.getId() != 0)
          return new ResponseEntity<>(teacher, HttpStatus.FOUND);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/postteacher")
    public ResponseEntity<?> PostTeacher(@RequestPart TeacherNewTable teacher, @RequestPart MultipartFile imageFile) {

        try {
            String result = teacherService.SaveOrUpdate(teacher , imageFile);
            return new ResponseEntity<>(result,HttpStatus.ACCEPTED);
        } catch (IOException e)
        {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateteacher")
    public ResponseEntity<?> UpdateTeacher(@RequestBody TeacherNewTable teacher , @RequestPart MultipartFile imageFile)
    {
        try {
            String result = teacherService.SaveOrUpdate(teacher , imageFile);
            return new ResponseEntity<>(result,HttpStatus.ACCEPTED);
        } catch (IOException e)
        {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteteacher/{id}")
    public ResponseEntity<?> DeleteTeacher(@PathVariable int id)
    {
        String result = teacherService.DeleteTeacher(id);
        if (result == "Deleted Success")
            return new ResponseEntity<>(result,HttpStatus.OK);
        else
            return new ResponseEntity<>(result,HttpStatus.NOT_FOUND);
    }

}
