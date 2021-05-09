package com.example.apistudentmanagerment.repository;

import com.example.apistudentmanagerment.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentConnectedDBRepository extends JpaRepository<StudentEntity, Long> {
}
