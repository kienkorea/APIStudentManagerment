package com.example.apistudentmanagerment.service.impl;

import com.example.apistudentmanagerment.dto.StudentDto;
import com.example.apistudentmanagerment.entity.Student;
import com.example.apistudentmanagerment.repository.StudentRepository;
import com.example.apistudentmanagerment.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value = "studentServiceV2")
public class StudentServiceV2Impl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<StudentDto> findAllStudent() {
        List<Student> students = studentRepository.findAll();
        List<StudentDto> results = new ArrayList<>();

        for (var student : students) {

            StudentDto dto = new StudentDto();

            dto.setName(student.getName());
            dto.setAddress(student.getAddress());
            dto.setAge(student.getAge());
            dto.setId(student.getId());

            results.add(dto);
        }

        return results;
    }

    @Override
    public StudentDto create(StudentDto dto) throws Exception {
        Student student = new Student();

        student.setName(dto.getName());
        student.setAddress(dto.getAddress());
        student.setAge(dto.getAge());

        student = studentRepository.create(student);

        dto.setId(student.getId());

        return dto;
    }

    @Override
    public StudentDto updateStudent(Long id, StudentDto dto) throws Exception {
        Student student = new Student();

        student.setName(dto.getName());
        student.setAddress(dto.getAddress());
        student.setAge(dto.getAge());
        student.setId(id);

        student = studentRepository.update(student);

        dto.setName(student.getName());
        dto.setAddress(student.getAddress());
        dto.setAge(student.getAge());
        dto.setId(student.getId());

        return dto;
    }

    @Override
    public StudentDto deleteStudent(Long id) throws Exception {
        Student student = studentRepository.delete(id);

        StudentDto dto = new StudentDto();

        dto.setName(student.getName());
        dto.setAddress(student.getAddress());
        dto.setAge(student.getAge());
        dto.setId(student.getId());

        return dto;
    }
}
