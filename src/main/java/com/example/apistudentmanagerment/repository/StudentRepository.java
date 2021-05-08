package com.example.apistudentmanagerment.repository;

import com.example.apistudentmanagerment.entity.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

// @Repository
public interface StudentRepository {
    List<Student> findAll();

    Student create(Student student);

    Student findById(Long id) throws Exception;

    Student delete(Long id) throws Exception;

    Student update(Student student) throws Exception;
}
