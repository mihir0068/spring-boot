package com.mihir.SpringSecurity.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mihir.SpringSecurity.Model.Student;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class StudentController {

    private List<Student> studentList = new ArrayList<>(List.of(
            new Student(1, "Mihir", 50),
            new Student(2, "ABD", 70)));

    @GetMapping("/student")
    public List<Student> getStudents() {
        return studentList;
    }

    @GetMapping("/csrf")
    public CsrfToken getCsrfToken(HttpServletRequest req) {
        return (CsrfToken) req.getAttribute("_csrf");
    }

    @PostMapping("/add-student")
    public String addStudent(@RequestBody Student student) {
        studentList.add(student);
        return "Student added successfully!";
    }
}
