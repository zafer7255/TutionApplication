package com.coaching_system.CoachingApp.Service;

import com.coaching_system.CoachingApp.Model.StudentNewTable;
import com.coaching_system.CoachingApp.Repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.coaching_system.CoachingApp.Model.UserPrincipal;


@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private StudentRepo studentRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        StudentNewTable studentNewTable = studentRepo.findByUsername(username);
        if( studentNewTable == null)
        {
            throw new UsernameNotFoundException("user 404");
        }
        return new UserPrincipal(studentNewTable);
    }
}
