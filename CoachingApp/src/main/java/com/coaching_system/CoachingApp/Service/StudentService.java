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

    public StudentNewTable GetStudentById(String username)
    {
        StudentNewTable st = studentRepo.findByUsername(username);
        return st == null ? new StudentNewTable() : st;
    }

    public String Update(StudentNewTable student , MultipartFile image) throws IOException {

        StudentNewTable st = studentRepo.findByUsername(student.getUsername());
        if(st == null)
        {
            return "username not found";
        }
        st.setName(student.getName());
        st.setSubjects(student.getSubjects());
        st.setSchoolStandard(student.getSchoolStandard());
        st.setAddress(student.getAddress());
        st.setEmail(student.getEmail());
        st.setPhoneNo(student.getPhoneNo());
        st.setParentsNo(student.getParentsNo());

        if (image != null && !image.isEmpty()) {
            st.setImageName(image.getOriginalFilename());
            st.setImageType(image.getContentType());
            st.setImageData(image.getBytes());
        }
        return "Updated";
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
