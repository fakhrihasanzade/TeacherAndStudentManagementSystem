package org.example.service;

import org.example.model.entity.Teacher;

import java.sql.SQLException;
import java.util.List;

public interface TeacherService {

    List<Teacher> getBySubject(String subject) throws SQLException;

    List<Teacher> getTeacherBySalaryBound(Integer lowerSalary, Integer upperSalary) throws SQLException;

    List<Teacher> getByLowerAge(Integer lowAge) throws SQLException;

}
