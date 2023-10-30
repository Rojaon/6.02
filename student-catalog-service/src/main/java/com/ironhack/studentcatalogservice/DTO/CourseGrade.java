package com.ironhack.studentcatalogservice.DTO;

import com.ironhack.studentcatalogservice.model.Grade;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseGrade {
    private String courseCode;
    private List<Grade> grades;
}
