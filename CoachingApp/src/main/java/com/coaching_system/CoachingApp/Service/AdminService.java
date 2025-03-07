package com.coaching_system.CoachingApp.Service;

import com.coaching_system.CoachingApp.Model.Admin;
import com.coaching_system.CoachingApp.Model.StudentNewTable;
import com.coaching_system.CoachingApp.Model.TeacherNewTable;
import com.coaching_system.CoachingApp.Repo.AdminRepo;
import com.coaching_system.CoachingApp.Repo.StudentRepo;
import com.coaching_system.CoachingApp.Repo.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@Service
public class AdminService {

    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private TeacherRepo teacherRepo;
    @Autowired
    private AdminRepo adminRepo;

    public List<StudentNewTable> GetAllStudents() {
        List<StudentNewTable> studentNewTables = studentRepo.findAll();
        return studentNewTables;
    }

    public List<TeacherNewTable> GetAllTeachers() {
        List<TeacherNewTable> teacherNewTables = teacherRepo.findAll();
        return teacherNewTables;
    }

    public List<TeacherNewTable> GetTeacherByName(String name) {
         return teacherRepo.findByName(name);
    }

    public List<StudentNewTable> GetStudentByName(String name) {
        return studentRepo.findByName(name);
    }


    public String SaveOrUpdate(Admin admin, MultipartFile image) throws IOException {

        for (Admin a : adminRepo.findAll())
        {
            if (a.getAdminId() == admin.getAdminId())
            {
                a.setAddress(admin.getAddress());
                a.setEmail(admin.getEmail());
                a.setName(admin.getName());
                a.setPhoneNo(admin.getPhoneNo());
                a.setImageName(image.getOriginalFilename());
                a.setImageType(image.getContentType());
                a.setImageData(image.getBytes());
                return "Admin Already Found Updated ...";
            }
        }
        admin.setImageName(image.getOriginalFilename());
        admin.setImageType(image.getContentType());
        admin.setImageData(image.getBytes());
        adminRepo.save(admin);
        return "Admin Saved";
    }
}
