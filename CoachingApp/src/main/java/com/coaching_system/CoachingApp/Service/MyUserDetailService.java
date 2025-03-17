package com.coaching_system.CoachingApp.Service;

import com.coaching_system.CoachingApp.Model.StudentNewTable;
import com.coaching_system.CoachingApp.Model.Users;
import com.coaching_system.CoachingApp.Repo.StudentRepo;
import com.coaching_system.CoachingApp.Repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.coaching_system.CoachingApp.Model.UserPrincipal;


@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UsersRepo usersRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = usersRepo.findByUsername(username);
        if (users == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }
        return new UserPrincipal(users);
    }
}
