package com.coaching_system.CoachingApp.Repo;

import com.coaching_system.CoachingApp.Model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepo extends JpaRepository<Admin,String> {
    Admin findByUsername(String username);
}
