package service;

import entity.Student;
import enums.CourseYear;

import java.sql.SQLException;
import java.util.List;

public interface StudentService {

    List<Student> getByFullName(String name) throws SQLException;

    List<Student> getByMajor(String major) throws SQLException;

    List<Student> getByCourse(CourseYear year) throws SQLException;

    List<Student> getByGpaBound(Integer lowerGpa, Integer upperGpa) throws SQLException;

}
