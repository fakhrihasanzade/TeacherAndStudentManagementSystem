package org.example;

import entity.Student;
import entity.Teacher;
import enums.CourseYear;
import enums.Position;
import service.StudentService;
import serviceİmpl.StudentServiceImpl;
import serviceİmpl.TeacherServiceImpl;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {

        Student st=new Student(1,"Fexri H",26,"fexri@gmail.com","554402066",
                "CompScience",5.1, CourseYear.FOUR);

        StudentServiceImpl s = new StudentServiceImpl();
        //System.out.println(s.getAll());
        //System.out.println(s.getByID(1));
        //System.out.println(s.create(st));
        //System.out.println(s.updateByID(1, st));
        //System.out.println(s.deleteById(10));
        //System.out.println(s.getByFullName("Fexri H"));

        TeacherServiceImpl t=new TeacherServiceImpl();
        Teacher teacher=new Teacher(11,"Fexri H",26,"fexri@gmail.com","554402066",
                5000.0,
                "Mathematics", Position.DEKAN);
        //System.out.println(t.create(teacher));
        //System.out.println(t.getByID(1));
        //System.out.println(t.deleteById(1));
        System.out.println(t.getAll());

    }
}