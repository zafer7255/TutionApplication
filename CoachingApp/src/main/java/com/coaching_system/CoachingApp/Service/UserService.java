package com.coaching_system.CoachingApp.Service;


import com.coaching_system.CoachingApp.Model.Admin;
import com.coaching_system.CoachingApp.Model.StudentNewTable;
import com.coaching_system.CoachingApp.Model.TeacherNewTable;
import com.coaching_system.CoachingApp.Model.Users;
import com.coaching_system.CoachingApp.Repo.AdminRepo;
import com.coaching_system.CoachingApp.Repo.StudentRepo;
import com.coaching_system.CoachingApp.Repo.TeacherRepo;
import com.coaching_system.CoachingApp.Repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private AdminRepo adminRepo;
    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private TeacherRepo teacherRepo;
    @Autowired
    UsersRepo usersRepo;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public String registerUser(Admin admin)
    {
        for (Admin a : adminRepo.findAll())
        {
            if(a.getUsername() == admin.getUsername())
            {
                return " username already exist";
            }
        }
        admin.setPassword(encoder.encode(admin.getPassword()));
        adminRepo.save(admin);
        Users users = new Users();
        users.setUsername(admin.getUsername());
        users.setPassword(admin.getPassword());
        usersRepo.save(users);
        return "Registered";
    }
    public String registerUser(StudentNewTable studentNewTable)
    {
        for (StudentNewTable st : studentRepo.findAll())
        {
            if(st.getUsername() == studentNewTable.getUsername())
            {
                return "username already exist";
            }
            if(st.getRollNo() == studentNewTable.getRollNo())
            {
                return "Roll already exist";
            }
        }
        studentNewTable.setPassword(encoder.encode(studentNewTable.getPassword()));
        studentRepo.save(studentNewTable);
        Users users = new Users();
        users.setUsername(studentNewTable.getUsername());
        users.setPassword(studentNewTable.getPassword());
        usersRepo.save(users);
        return "Registered";
    }

    public String registerUser(TeacherNewTable teacherNewTable)
    {
        for (TeacherNewTable t : teacherRepo.findAll())
        {
            if (t.getUsername() == teacherNewTable.getUsername())
            {
                return " username already exist";
            }
        }
        teacherNewTable.setPassword(encoder.encode(teacherNewTable.getPassword()));
        teacherRepo.save(teacherNewTable);
        Users users = new Users();
        users.setUsername(teacherNewTable.getUsername());
        users.setPassword(teacherNewTable.getPassword());
        usersRepo.save(users);
        return " Registered";
    }

}