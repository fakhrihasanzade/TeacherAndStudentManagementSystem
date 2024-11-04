package org.example.model.entity;

import org.example.model.enums.CourseYear;

public class Student extends User {

    private String major;
    private CourseYear year;
    private Double gpa;

    public Student(Integer id, String fullName, Integer age, String email, String phoneNumber, String major, Double gpa, CourseYear year) {
        super(id, fullName, age, email, phoneNumber);
        this.major = major;
        this.year = year;
        this.gpa = gpa;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public CourseYear getYear() {
        return year;
    }

    public void setYear(CourseYear year) {
        this.year = year;
    }

    public Double getGpa() {
        return gpa;
    }

    public void setGpa(Double gpa) {
        this.gpa = gpa;
    }

    @Override
    public String toString() {
        return "Student{" + super.toString() +
                "major='" + major + '\'' +
                ", year=" + year +
                ", gpa=" + gpa +
                "} " + "\n";
    }
}
