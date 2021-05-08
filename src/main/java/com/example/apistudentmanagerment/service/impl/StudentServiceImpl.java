package com.example.apistudentmanagerment.service.impl;

import com.example.apistudentmanagerment.dto.StudentDto;
import com.example.apistudentmanagerment.service.StudentService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.util.concurrent.atomic.LongAdder;

@Service
public class StudentServiceImpl implements StudentService {

    // Simulator database
    public static final Map<Long, StudentDto> students = new TreeMap<>((o1, o2) -> (int) (o1 - o2));

    // Simulator Sequence for student
    public static final LongAdder sequence = new LongAdder();

    @Override
    public List<StudentDto> findAllStudent() {
        ArrayList<StudentDto> studentDtoResult = new ArrayList<>();

        students.forEach((Long key, StudentDto stdto) -> {
            StudentDto student = new StudentDto();
            BeanUtils.copyProperties(stdto, student);
            student.setId(key);
            studentDtoResult.add(student);
        });

        return studentDtoResult;
    }

    @Override
    public StudentDto create(StudentDto dto) throws Exception {
        if (!StringUtils.hasText(dto.getName())) {
            throw new Exception("Name can't be null");
        }
        sequence.increment();
        students.put(sequence.longValue(), dto);
        return dto;
    }

    @Override
    public StudentDto updateStudent(Long id, StudentDto dto) throws Exception {

        StudentDto student = students.get(id);

        if (student == null) {
            throw new Exception("Id student not found.");
        }

        student.setName(dto.getName());
        student.setAge(dto.getAge());
        student.setAddress(dto.getAddress());

        return student;
    }

    @Override
    public StudentDto deleteStudent(Long id) throws Exception {

        StudentDto student = students.get(id);

        if (student == null) {
            throw new Exception("Id student not found.");
        }

        students.remove(id);

        return student;
    }


}
