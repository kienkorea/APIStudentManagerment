package com.example.apistudentmanagerment.repository;

import com.example.apistudentmanagerment.entity.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.LongAdder;

@Repository
public class StudentRepositoryImpl implements StudentRepository{

    // Simulator database
    public static final Map<Long, Student> students = new TreeMap<>((o1, o2) -> (int) (o1 - o2));

    // Simulator Sequence for student
    public static final LongAdder sequence = new LongAdder();

    @Override
    public List<Student> findAll() {
        // SELECT ... FROM TABLE WHERE
        // MAP result from db to class entity there is Student.class
        // Return.
        return new ArrayList<>(students.values());
    }

    @Override
    public Student create(Student student) {
        sequence.increment();
        student.setId(sequence.longValue());
        students.put(sequence.longValue(), student);
        return student;
    }

    @Override
    public Student findById(Long id) throws Exception {
        Student student = students.getOrDefault(id, null);

        if (student == null) {
            throw new Exception("Entity not found!");
        }

        return student;
    }

    @Override
    public Student delete(Long id) throws Exception {
        Student student = students.getOrDefault(id, null);

        if (student == null) {
            throw new Exception("Entity not found!");
        }
        students.remove(id);

        return student;
    }

    @Override
    public Student update(Student student) throws Exception {
        Student entity = students.getOrDefault(student.getId(), null);

        if (entity == null) {
            throw new Exception("Entity not found!");
        }
        entity.setName(student.getName());
        entity.setAge(student.getAge());
        entity.setAddress(student.getAddress());

        return entity;
    }
}
