package com.coaching_system.CoachingApp.Repo;

import com.coaching_system.CoachingApp.Model.TeacherNewTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepo extends JpaRepository<TeacherNewTable, String> {
    List<TeacherNewTable> findByName(String name);

    TeacherNewTable findByUsername(String username);
}
