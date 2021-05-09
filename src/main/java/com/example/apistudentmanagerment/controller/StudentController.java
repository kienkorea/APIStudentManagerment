package com.example.apistudentmanagerment.controller;

import com.example.apistudentmanagerment.dto.StudentDto;
import com.example.apistudentmanagerment.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    @Qualifier("studentServiceV2") // specific bean name
    private StudentService studentServiceV2;

    @Autowired
    @Qualifier("studentServiceV3") // specific bean name
    private StudentService studentServiceV3;

    public final ArrayList<StudentDto> students = new ArrayList<>();

    // Post co the lam duoc giong Get, nhung ma Get khong the lam duoc giong nhu Post
    @GetMapping(value = "/hello", produces = "application/json")
    public String helloWord(@RequestParam("id") String id) {
        return id;
    }

    @PostMapping(value = "/greeting")
    public String greeting(@RequestBody String name) {
        return "Hello " + name;
    }

    @GetMapping("student")
    public StudentDto getStudent() {
        StudentDto studentDto = new StudentDto("Joson", 12, "VIetNam");
        return studentDto;
    }

    @GetMapping("students")
    public ArrayList<StudentDto> disPlayStudents() {
        StudentDto studentDto = new StudentDto("Manh", 12, "VietNam");
        StudentDto studentDto2 = new StudentDto("Bou", 43, "VietNam");
        StudentDto studentDto3 = new StudentDto("Love", 23, "VietNam");
        StudentDto studentDto4 = new StudentDto("Or", 15, "VietNam");
        StudentDto studentDto5 = new StudentDto("Broken?", 45, "VietNam");

        students.add(studentDto);
        students.add(studentDto2);
        students.add(studentDto3);
        students.add(studentDto4);
        students.add(studentDto5);
        return students;
    }

    @GetMapping("update")
    public StudentDto updateStudent(@RequestParam("name") String name) {
        StudentDto studentDto = new StudentDto(name, 12, "VN");
        System.out.println("Update student by name:");
        studentDto.setName("Nguyen Minh Luan");
        studentDto.setAge(20);
        studentDto.setAddress("Korea");
        return studentDto;
    }

    @GetMapping("delete")
    public ArrayList<StudentDto> deleteStudent(@RequestParam("name") String name) {

        StudentDto student = new StudentDto(name, 12, "VN");
        StudentDto studentDto = new StudentDto("1", 12, "VietNam");
        StudentDto studentDto2 = new StudentDto("2", 43, "VietNam");
        StudentDto studentDto3 = new StudentDto("3", 23, "VietNam");
        StudentDto studentDto4 = new StudentDto("4", 15, "VietNam");
        StudentDto studentDto5 = new StudentDto("5", 45, "VietNam");

        students.add(studentDto);
        students.add(studentDto2);
        students.add(studentDto3);
        students.add(studentDto4);
        students.add(studentDto5);

        for(StudentDto studentDto1: students){
            if(name.equals(studentDto1.getName()));
                students.remove(studentDto1);
        }
//        for (int i = 0; i < students.size(); i++) {
//            if (name.equals(students.get(i).getName()))
//                students.remove(i);
//        }
        return students;

    }

    @GetMapping("v2/students")
    public List<StudentDto> findAllStudent() {
        // Check permission
        return studentService.findAllStudent();
    }

    @PostMapping("v2/students/add")
    public StudentDto addStudent(@RequestBody StudentDto dto) throws Exception {
        // Check permission
        return studentService.create(dto);
    }

    @PostMapping("v2/students/update")
    public StudentDto updateStudent(@RequestParam("id") Long id,
                                    @RequestBody StudentDto dto) throws Exception {
        // Check permission
        return studentService.updateStudent(id, dto);
    }

    @GetMapping("v2/students/delete") // risk
    public StudentDto deleteStudent(@RequestParam("id") Long id) throws Exception {
        // Check permission
        return studentService.deleteStudent(id);
    }


    @GetMapping("v3/students")
    public List<StudentDto> findAllStudentV3() {
        // Check permission
        return studentServiceV2.findAllStudent();
    }

    @PostMapping("v3/students/add")
    public StudentDto addStudentV3(@RequestBody StudentDto dto) throws Exception {
        // Check permission
        return studentServiceV2.create(dto);
    }

    @PostMapping("v3/students/update")
    public StudentDto updateStudentV3(@RequestParam("id") Long id,
                                    @RequestBody StudentDto dto) throws Exception {
        // Check permission
        return studentServiceV2.updateStudent(id, dto);
    }

    @GetMapping("v3/students/delete") // risk
    public StudentDto deleteStudentV3(@RequestParam("id") Long id) throws Exception {
        // Check permission
        return studentServiceV2.deleteStudent(id);
    }

    @GetMapping("v4/students")
    public List<StudentDto> findAllStudentV4() {
        // Check permission
        return studentServiceV3.findAllStudent();
    }

    @PostMapping("v4/students/add")
    public StudentDto addStudentV4(@RequestBody StudentDto dto) throws Exception {
        // Check permission
        return studentServiceV3.create(dto);
    }

    @PostMapping("v4/students/update")
    public StudentDto updateStudentV4(@RequestParam("id") Long id,
                                      @RequestBody StudentDto dto) throws Exception {
        // Check permission
        return studentServiceV3.updateStudent(id, dto);
    }

    @GetMapping("v4/students/delete") // risk
    public StudentDto deleteStudentV4(@RequestParam("id") Long id) throws Exception {
        // Check permission
        return studentServiceV3.deleteStudent(id);
    }
}

