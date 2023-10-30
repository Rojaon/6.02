package com.ironhack.studentcatalogservice.controller;

import com.ironhack.studentcatalogservice.DTO.CourseGrade;
import com.ironhack.studentcatalogservice.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CatalogController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/catalogs/courses/{courseCode}")
    @ResponseStatus(HttpStatus.OK)
    public Catalog getCatalog(@PathVariable String courseCode) {

        Course course = restTemplate.getForObject("http://grades-data-service/api/" + courseCode, Course.class);
        CourseGrade courseGrade = restTemplate.getForObject("http://grades-data-service/api/courses/" + courseCode + "/grades", CourseGrade.class);

        Catalog catalog = new Catalog();
        catalog.setCourseName(course.getCourseName());
        List<StudentGrade> studentsGrades = new ArrayList<>();

        for (Grade grades : courseGrade.getGrades()) {
            Student student = restTemplate.getForObject("http://student-info-service/api/students/" + grades.getStudentId(), Student.class);
            studentsGrades.add(new StudentGrade(student.getName(), student.getAge(), grades.getGrade()));
        }

        catalog.setStudentGrades(studentsGrades);
        return catalog;
    }
}
