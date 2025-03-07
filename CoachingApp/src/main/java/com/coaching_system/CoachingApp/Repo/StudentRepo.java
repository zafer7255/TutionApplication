package com.coaching_system.CoachingApp.Repo;

import com.coaching_system.CoachingApp.Model.StudentNewTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepo extends JpaRepository<StudentNewTable, Integer> {

    List<StudentNewTable> findByName(String name);
}
