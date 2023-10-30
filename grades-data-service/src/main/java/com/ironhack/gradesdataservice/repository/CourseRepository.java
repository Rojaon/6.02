package com.ironhack.gradesdataservice.repository;

import com.ironhack.gradesdataservice.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course,String> {
}
