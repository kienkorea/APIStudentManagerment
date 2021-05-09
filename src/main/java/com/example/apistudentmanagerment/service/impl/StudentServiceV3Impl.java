package com.example.apistudentmanagerment.service.impl;

import com.example.apistudentmanagerment.dto.StudentDto;
import com.example.apistudentmanagerment.entity.Student;
import com.example.apistudentmanagerment.entity.StudentEntity;
import com.example.apistudentmanagerment.repository.StudentConnectedDBRepository;
import com.example.apistudentmanagerment.repository.StudentRepository;
import com.example.apistudentmanagerment.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service(value = "studentServiceV3")
public class StudentServiceV3Impl implements StudentService {

    @Autowired
    private StudentConnectedDBRepository studentRepository;

    @Override
    public List<StudentDto> findAllStudent() {
        List<StudentEntity> students = studentRepository.findAll();
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
        StudentEntity student = new StudentEntity();

        student.setName(dto.getName());
        student.setAddress(dto.getAddress());
        student.setAge(dto.getAge());

        student = studentRepository.save(student);

        dto.setId(student.getId());

        return dto;
    }

    @Override
    @Transactional
    public StudentDto updateStudent(Long id, StudentDto dto) throws Exception {
        StudentEntity student = new StudentEntity();

        student.setName(dto.getName());
        student.setAddress(dto.getAddress());
        student.setAge(dto.getAge());
        student.setId(id);

        student = studentRepository.save(student);

        dto.setName(student.getName());
        dto.setAddress(student.getAddress());
        dto.setAge(student.getAge());
        dto.setId(student.getId());

        return dto;
    }

    @Override
    public StudentDto deleteStudent(Long id) throws Exception {
        var studentOptional = studentRepository.findById(id);
        if (studentOptional.isEmpty()) {
            throw new Exception("Student id not found!");
        }
        studentRepository.deleteById(id);

        var student = studentOptional.get();

        StudentDto dto = new StudentDto();

        dto.setName(student.getName());
        dto.setAddress(student.getAddress());
        dto.setAge(student.getAge());
        dto.setId(student.getId());

        return dto;
    }
}
