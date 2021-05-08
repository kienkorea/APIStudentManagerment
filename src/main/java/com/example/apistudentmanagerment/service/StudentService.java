package com.example.apistudentmanagerment.service;

import com.example.apistudentmanagerment.dto.StudentDto;

import java.util.List;

public interface StudentService {
    // public private default protected

    /**
     * List student.
     * @return list student dto
     */
    List<StudentDto> findAllStudent();

    /**
     * Create student.
     * @param dto input
     * @return response inserted object.
     * @throws Exception validate
     */
    StudentDto create(StudentDto dto) throws Exception;

    StudentDto updateStudent(Long id, StudentDto dto) throws Exception;

    StudentDto deleteStudent(Long id) throws Exception;
}
