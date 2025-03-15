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


    public String Update(Admin admin, MultipartFile image) throws IOException {
        Admin existingAdmin = adminRepo.findByUsername(admin.getUsername()); // Assuming this method exists

        if (existingAdmin == null) {
            return "Admin Not Found";
        }

        existingAdmin.setAddress(admin.getAddress());
        existingAdmin.setEmail(admin.getEmail());
        existingAdmin.setName(admin.getName());
        existingAdmin.setPhoneNo(admin.getPhoneNo());

        if (image != null && !image.isEmpty()) {
            existingAdmin.setImageName(image.getOriginalFilename());
            existingAdmin.setImageType(image.getContentType());
            existingAdmin.setImageData(image.getBytes());
        }

        adminRepo.save(existingAdmin);
        return "Updated Successfully";

    }

    public String Delete(String username) {
        for (Admin a : adminRepo.findAll())
        {
            if (a.getUsername() == username)
            {
                adminRepo.delete(a);
                return "Deleted";
            }
        }
        return "Admin Not found";
    }
}
