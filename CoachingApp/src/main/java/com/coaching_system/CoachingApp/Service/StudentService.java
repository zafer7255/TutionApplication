package com.coaching_system.CoachingApp.Service;

import com.coaching_system.CoachingApp.Model.StudentNewTable;
import com.coaching_system.CoachingApp.Repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentRepo studentRepo;

    public List<StudentNewTable> GetAllStudent()
    {
        return studentRepo.findAll();
    }

    public List<StudentNewTable> GetStudentByName(String name) {
        return studentRepo.findByName(name);
    }

    public StudentNewTable GetStudentById(int id)
    {
        return studentRepo.findById(id).orElse(new StudentNewTable());
    }

    public String SaveOrUpdate(StudentNewTable student , MultipartFile image) throws IOException {
        List<StudentNewTable> students = studentRepo.findAll();
        for (StudentNewTable s : students)
        {
            if(s.getRollNo() == student.getRollNo())
            {
                s.setAddress(student.getAddress());
                s.setName(student.getName());
                s.setPhoneNo(student.getPhoneNo());
                s.setSchoolStandard(student.getSchoolStandard());
                s.setImageName(image.getOriginalFilename());
                s.setImageType(image.getContentType());
                s.setImageData(image.getBytes());
                s.setSubjects(student.getSubjects());
                return "Student is already exist UPDATED...";
            }
        }
        student.setImageName(image.getOriginalFilename());
        student.setImageType(image.getContentType());
        student.setImageData(image.getBytes());
        studentRepo.save(student);
        return "Student saved";
    }

    public String DeleteStudent(int roll) {

        for (StudentNewTable s: studentRepo.findAll())
        {
            if (s.getRollNo() == roll)
            {
                studentRepo.delete(s);
                return "Deleted Success";
            }
        }
        return "Student Not Found ";
    }
}
