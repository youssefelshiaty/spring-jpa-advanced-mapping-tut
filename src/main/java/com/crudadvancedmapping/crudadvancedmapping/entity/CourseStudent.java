package com.crudadvancedmapping.crudadvancedmapping.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Table(name = "course_student")
public class CourseStudent {
    private int courseId;
    private int studentId;

}
