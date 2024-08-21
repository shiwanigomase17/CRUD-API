package com.example.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.task.model.Salary;

@Repository
public interface SalaryRepository extends JpaRepository<Salary, Long> {
}


