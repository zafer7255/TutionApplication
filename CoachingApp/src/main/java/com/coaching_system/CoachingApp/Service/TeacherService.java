package com.coaching_system.CoachingApp.Service;

import com.coaching_system.CoachingApp.Model.TeacherNewTable;
import com.coaching_system.CoachingApp.Repo.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class TeacherService {

    @Autowired
    TeacherRepo teacherRepo;
    public List<TeacherNewTable> GetAllStudent() {
        return teacherRepo.findAll();
    }
    public List<TeacherNewTable> GetTeacherByName(String name) {

        return teacherRepo.findByName(name);
    }
    public TeacherNewTable GetTeacherByUsername(String username) {
        TeacherNewTable t = teacherRepo.findByUsername(username);
        return t == null ? new TeacherNewTable() : t;
    }

    public String Update(TeacherNewTable teacher , MultipartFile image) throws IOException {

        TeacherNewTable t = teacherRepo.findByUsername(teacher.getUsername());
        if(t == null)
        {
            return "username not found";
        }
        t.setName(teacher.getName());
        t.setSubjectsTeach(teacher.getSubjectsTeach());
        t.setAddress(teacher.getAddress());
        t.setEmail(teacher.getEmail());
        t.setPhoneNo(teacher.getPhoneNo());

        if (image != null && !image.isEmpty()) {
            t.setImageName(image.getOriginalFilename());
            t.setImageType(image.getContentType());
            t.setImageData(image.getBytes());
        }
        return " Updated ";
    }
    public String DeleteTeacher(String username) {
        for (TeacherNewTable t : teacherRepo.findAll())
        {
            if (t.getUsername() == username)
            {
                teacherRepo.delete(t);
                return "Deleted Success";
            }
        }
        return "Teacher Not Found";
    }
}
