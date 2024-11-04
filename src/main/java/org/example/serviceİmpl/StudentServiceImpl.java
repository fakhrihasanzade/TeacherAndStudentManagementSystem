package org.example.serviceİmpl;

import com.mysql.cj.protocol.FullReadInputStream;
import org.example.database.DatabaseConnection;
import org.example.model.entity.Student;
import org.example.model.enums.CourseYear;
import org.example.exception.NullPoitException;
import org.example.service.CommonService;
import org.example.service.StudentService;
import org.example.util.FileWrite;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentServiceImpl implements CommonService<Student>, StudentService {

    @Override
    public List<Student> getAll() {

        List<Student> students = new ArrayList<>();

        try {
            Connection connection = DatabaseConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select *from Student");

            while (resultSet.next()) {

                Student student = new Student(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getInt(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getDouble(7),
                        CourseYear.valueOf(resultSet.getString(8))
                );

                students.add(student);
                statement.close();
                resultSet.close();

            }
        } catch (SQLException ex) {
            try {
                throw new NullPoitException("DataBase is empty");
            } catch (NullPoitException e) {
                throw new RuntimeException(e);
            }
        }


        return students;
    }

    @Override
    public Student getByID(Integer id) throws SQLException {

        Connection connection = DatabaseConnection.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select *from Student where id=" + id);


        if (resultSet.next()) {
            Student student = new Student(
                    id,
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getDouble(7),
                    CourseYear.valueOf(resultSet.getString(8))
            );
            return student;
        } else {
            throw new SQLException("No User found with ID: " + id);
        }

    }

    @Override
    public Student create(Student element) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into Student values(?,?,?,?,?,?,?,?)");

            preparedStatement.setInt(1, element.getId());
            preparedStatement.setString(2, element.getFullName());
            preparedStatement.setInt(3, element.getAge());
            preparedStatement.setString(4, element.getEmail());
            preparedStatement.setString(5, element.getPhoneNumber());
            preparedStatement.setString(6, element.getMajor());
            preparedStatement.setDouble(7, element.getGpa());
            preparedStatement.setString(8, element.getYear().name());
            preparedStatement.executeUpdate();
            preparedStatement.close();

            FileWrite.fileWriteFromService("StudentCreate.txt", element.toString());
        } catch (SQLException e) {
            try {
                throw new SQLException("Student isn't added");
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        return element;
    }

    @Override
    public Student updateByID(Integer id, Student element) {

        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "update Student set full_name=?,age=?,email=?, phone_number=?,major=?,gpa=?,cours_year=? where id=?"
            );
            preparedStatement.setString(1, element.getFullName());
            preparedStatement.setInt(2, element.getAge());
            preparedStatement.setString(3, element.getEmail());
            preparedStatement.setString(4, element.getPhoneNumber());
            preparedStatement.setString(5, element.getMajor());
            preparedStatement.setDouble(6, element.getGpa());
            preparedStatement.setString(7, element.getYear().name());
            preparedStatement.setInt(8, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();

            FileWrite.fileWriteFromService("StudentUpdate.txt", element.toString());
        } catch (SQLException e) {
            try {
                throw new SQLException(id.toString() + "th student isn't updated");
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        return element;
    }

    @Override
    public Integer deleteById(Integer id) {

        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "delete from Student where id=?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();

            FileWrite.fileWriteFromService("DeletedStudent.txt", id.toString() + "th element deleted");
        } catch (SQLException e) {
            try {
                throw new NullPoitException(id.toString() + "th student isn't exist");
            } catch (NullPoitException ex) {
                throw new RuntimeException(ex);
            }
        }
        return id;
    }

    @Override
    public List<Student> getByFullName(String name) {
        List<Student> students = new ArrayList<>();

        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "select *from Student where full_name=?");
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Student student = new Student(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getInt(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getDouble(7),
                        CourseYear.valueOf(resultSet.getString(8))
                );
                students.add(student);
            }
            preparedStatement.close();
        } catch (SQLException e) {
            try {
                throw new NullPoitException(name + " isn't exist");
            } catch (NullPoitException ex) {
                throw new RuntimeException(ex);
            }
        }


        return students;
    }

    @Override
    public List<Student> getByMajor(String major) {

        List<Student> students = new ArrayList<>();
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "select *from Student where major=?");
            preparedStatement.setString(1, major);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Student student = new Student(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getInt(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getDouble(7),
                        CourseYear.valueOf(resultSet.getString(8))
                );
                students.add(student);
            }

            preparedStatement.close();
        } catch (SQLException e) {
            try {
                throw new NullPoitException(major + " isn't exist");
            } catch (NullPoitException ex) {
                throw new RuntimeException(ex);
            }
        }
        return students;
    }

    @Override
    public List<Student> getByCourse(CourseYear year) {

        List<Student> students = new ArrayList<>();

        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "select *from Student where cours_year=?");
            preparedStatement.setString(1, year.name());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Student student = new Student(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getInt(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getDouble(7),
                        CourseYear.valueOf(resultSet.getString(8))
                );
                students.add(student);
            }

            preparedStatement.close();
        } catch (Exception e) {
            try {
                throw new NullPoitException(year + " isn't exist");
            } catch (NullPoitException ex) {
                throw new RuntimeException(ex);
            }
        }
        return students;
    }

    @Override
    public List<Student> getByGpaBound(Integer lowerGpa, Integer upperGpa) {

        List<Student> students = new ArrayList<>();
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "select *from Student where age>? and ahe<?");
            preparedStatement.setInt(1, lowerGpa);
            preparedStatement.setInt(2, upperGpa);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Student student = new Student(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getInt(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getDouble(7),
                        CourseYear.valueOf(resultSet.getString(8))
                );
                students.add(student);
            }

            preparedStatement.close();
        } catch (SQLException e) {
            try {
                throw new NullPoitException("there are no elements in this interval");
            } catch (NullPoitException ex) {
                throw new RuntimeException(ex);
            }
        }
        return students;
    }
}
