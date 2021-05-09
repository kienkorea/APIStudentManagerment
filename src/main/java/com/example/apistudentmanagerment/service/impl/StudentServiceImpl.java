package com.example.apistudentmanagerment.service.impl;

import com.example.apistudentmanagerment.dto.StudentDto;
import com.example.apistudentmanagerment.service.StudentService;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.LongAdder;

@Service(value = "studentService")
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

            student.setName(stdto.getName());
            student.setAddress(stdto.getAddress());
            student.setAge(stdto.getAge());
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
