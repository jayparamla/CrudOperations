package com.example.demoforJPA.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demoforJPA.entity.Student;

public interface StudentRepo extends JpaRepository<Student, Long>{

}
