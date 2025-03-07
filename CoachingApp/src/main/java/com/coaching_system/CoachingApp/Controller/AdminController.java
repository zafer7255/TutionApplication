package com.coaching_system.CoachingApp.Controller;

import com.coaching_system.CoachingApp.Model.Admin;
import com.coaching_system.CoachingApp.Model.StudentNewTable;
import com.coaching_system.CoachingApp.Model.TeacherNewTable;
import com.coaching_system.CoachingApp.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;
    @GetMapping("/getallstudents")
    public ResponseEntity<List<StudentNewTable>> GetStudents()
    {
       List<StudentNewTable> students = adminService.GetAllStudents();
       if (students.get(0).getRollNo() != 0) {
           return new ResponseEntity<>(students, HttpStatus.FOUND);
       } else {
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
    }

    @GetMapping("/getallteachers")
    public ResponseEntity<List<TeacherNewTable>> GetAllTeacher()
    {
        List<TeacherNewTable> teachers = adminService.GetAllTeachers();
        if(teachers.get(0).getId() != 0)
        {
            return new ResponseEntity<>(teachers,HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getstudentbyname/{name}")
    public ResponseEntity<List<StudentNewTable>> GetStudent(@PathVariable String name)
    {
        List<StudentNewTable> studentNewTables = adminService.GetStudentByName(name);
        if (studentNewTables.get(0).getRollNo() != 0)
        {
            return new ResponseEntity<>(studentNewTables,HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getteacherbyname/{name}")
    public ResponseEntity<List<TeacherNewTable>> GetTeacher(@PathVariable String name)
    {
        List<TeacherNewTable> teacherNewTables = adminService.GetTeacherByName(name);
        if(teacherNewTables.get(0).getId() != 0)
        {
            return new ResponseEntity<>(teacherNewTables,HttpStatus.FOUND);
        } else {
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/postadmin")
    public ResponseEntity<?> PostAdmin(@RequestPart Admin admin,@RequestPart MultipartFile imageFile) throws IOException {
        System.out.println("HELLO");
        String result = adminService.SaveOrUpdate(admin,imageFile);
        if (result != null)
            return new ResponseEntity<>(result,HttpStatus.ACCEPTED);
        else return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
