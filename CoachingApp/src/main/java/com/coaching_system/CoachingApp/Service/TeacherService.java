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
    public TeacherNewTable GetTeacherById(int id) {
        return teacherRepo.findById(id).orElse(new TeacherNewTable());
    }

    public String SaveOrUpdate(TeacherNewTable teacher , MultipartFile image) throws IOException {
        List<TeacherNewTable> teachers = teacherRepo.findAll();
        for (TeacherNewTable t : teachers)
        {
            if (t.getId() == teacher.getId())
            {
                t.setAddress(teacher.getAddress());
                t.setName(teacher.getName());
                t.setPhoneNo(teacher.getPhoneNo());
                t.setSubjectsTeach(teacher.getSubjectsTeach());
                t.setImageName(image.getOriginalFilename());
                t.setImageType(image.getContentType());
                t.setImageData(image.getBytes());
                return "Teacher Already exist UPDATED...";
            }
        }
        teacher.setImageName(image.getOriginalFilename());
        teacher.setImageType(image.getContentType());
        teacher.setImageData(image.getBytes());
        teacherRepo.save(teacher);
        return "Teacher Saved";
    }
    public String DeleteTeacher(int id) {
        for (TeacherNewTable t : teacherRepo.findAll())
        {
            if (t.getId() == id)
            {
                teacherRepo.delete(t);
                return "Deleted Success";
            }
        }
        return "Teacher Not Found";
    }
}
