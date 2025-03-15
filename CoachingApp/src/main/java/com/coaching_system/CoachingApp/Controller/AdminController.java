package com.coaching_system.CoachingApp.Controller;

import com.coaching_system.CoachingApp.Model.Admin;
import com.coaching_system.CoachingApp.Model.StudentNewTable;
import com.coaching_system.CoachingApp.Model.TeacherNewTable;
import com.coaching_system.CoachingApp.Service.AdminService;
import com.coaching_system.CoachingApp.Service.StudentService;
import com.coaching_system.CoachingApp.Service.TeacherService;
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
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;
    @GetMapping("/getallstudents")
    public ResponseEntity<List<StudentNewTable>> GetAllStudents()
    {
        List<StudentNewTable> students = studentService.GetAllStudent();
        if(students.size() > 0 && students.get(0).getRollNo() != 0)
            return new ResponseEntity<>(students,HttpStatus.FOUND);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getallteachers")
    public ResponseEntity<List<TeacherNewTable>> GetAllStudent()
    {
        List<TeacherNewTable> teachers = teacherService.GetAllStudent();
        if (teachers.size() > 0 && teachers.get(0).getUsername() != null)
            return new ResponseEntity<>(teachers,HttpStatus.FOUND);
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

    @GetMapping("/getteacherbyname/{name}")
    public ResponseEntity<List<TeacherNewTable>> GetTeacherByName(@PathVariable String name)
    {
        List<TeacherNewTable> teachers = teacherService.GetTeacherByName(name);
        if (teachers.get(0).getUsername() != null)
            return new ResponseEntity<>(teachers,HttpStatus.FOUND);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

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

    @PutMapping("/updateadmin")
    public ResponseEntity<?> PostAdmin(@RequestPart Admin admin,@RequestPart MultipartFile imageFile) throws IOException {
        String result = adminService.Update(admin,imageFile);
        if (result != null)
            return new ResponseEntity<>(result,HttpStatus.ACCEPTED);

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
   @DeleteMapping("/deleteAdmin/{username}")
   public ResponseEntity<?> DeleteAdmin(@PathVariable String username)
   {
       String result = adminService.Delete(username);
       if (result != null)
           return new ResponseEntity<>(result,HttpStatus.ACCEPTED);

       return new ResponseEntity<>(result , HttpStatus.NOT_FOUND);
   }

}
