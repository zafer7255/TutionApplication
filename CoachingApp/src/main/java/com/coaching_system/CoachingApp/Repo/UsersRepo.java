package com.coaching_system.CoachingApp.Repo;

import com.coaching_system.CoachingApp.Model.StudentNewTable;
import com.coaching_system.CoachingApp.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepo extends JpaRepository<Users , String> {
    Users findByUsername(String username);
}
