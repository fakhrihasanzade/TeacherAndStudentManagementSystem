package serviceİmpl;

import database.DatabaseConnection;
import entity.Teacher;
import enums.Position;
import exception.NullPoitException;
import service.CommonService;
import service.TeacherService;
import util.FileWrite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherServiceImpl implements CommonService<Teacher>, TeacherService {
    @Override
    public List<Teacher> getAll() throws SQLException {

        List<Teacher> teachers = new ArrayList<>();

        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select *from Teacher");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {

            Teacher teacher = new Teacher(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getDouble(6),
                    resultSet.getString(7),
                    Position.valueOf(resultSet.getString(8))
            );

            teachers.add(teacher);

        }

        return teachers;
    }

    @Override
    public Teacher getByID(Integer id) {

        try{
        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("" +
                "select *from Teacher where id=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Teacher teacher = null;
        if (resultSet.next()) {
            teacher = new Teacher(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getDouble(6),
                    resultSet.getString(7),
                    Position.valueOf(resultSet.getString(8))
            );

        }
            return teacher;
        } catch (SQLException e) {
            try {
                throw new NullPoitException("Teacher isn't exist");
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }

    }

    @Override
    public Teacher create(Teacher element) {

        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "insert into Teacher values(?,?,?,?,?,?,?,?)");
            preparedStatement.setInt(1, element.getId());
            preparedStatement.setString(2, element.getFullName());
            preparedStatement.setInt(3, element.getAge());
            preparedStatement.setString(4, element.getEmail());
            preparedStatement.setString(5, element.getPhoneNumber());
            preparedStatement.setDouble(6, element.getSalary());
            preparedStatement.setString(7, element.getSubject());
            preparedStatement.setString(8, element.getPosition().name());
            preparedStatement.executeUpdate();
            preparedStatement.close();

            FileWrite.fileWriteFromService("TeacherInfo", element.toString());
        } catch (SQLException e) {
            try {
                throw new NullPoitException("Teacher isn't created");
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        return element;
    }

    @Override
    public Teacher updateByID(Integer id, Teacher element) {

        try{
        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("" +
                "\"update Student set full_name=?,age=?,email=?, phone_number=?,salary=?,subject=?," +
                "position=? where id=?\"");

        preparedStatement.setString(1, element.getFullName());
        preparedStatement.setInt(2, element.getAge());
        preparedStatement.setString(3, element.getEmail());
        preparedStatement.setString(4, element.getPhoneNumber());
        preparedStatement.setDouble(5, element.getSalary());
        preparedStatement.setString(6, element.getSubject());
        preparedStatement.setString(7, element.getPosition().name());
        preparedStatement.setInt(8, element.getId());
        preparedStatement.executeUpdate();
        preparedStatement.close();

        FileWrite.fileWriteFromService("TeacherInfo",element.toString());
        } catch (SQLException e) {
            try {
                throw new NullPoitException(id.toString()+"th teacher isn't updated");
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
                    "delete from Teacher where id=?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();

            FileWrite.fileWriteFromService("TeacherInfo", id.toString() + "th teacher deleted");
        }catch (SQLException e) {
            try {
                throw new NullPoitException(id.toString()+"th teacher isn't deleted");
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        return id;
    }

    @Override
    public List<Teacher> getBySubject(String subject) {

        List<Teacher> teachers = new ArrayList<>();

        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "select *from Teacher where subject =?");
            preparedStatement.setString(1, subject);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                Teacher teacher = new Teacher(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getInt(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getDouble(6),
                        resultSet.getString(7),
                        Position.valueOf(resultSet.getString(8))
                );
                teachers.add(teacher);
            }

            preparedStatement.close();
            resultSet.close();
        }catch (SQLException e) {
            try {
                throw new NullPoitException(subject+" isn't exist");
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }

        return teachers;
    }

    @Override
    public List<Teacher> getBySalaryBound(Integer lowerSalary, Integer upperSalary) {

        List<Teacher> teachers = new ArrayList<>();

        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select *from Teacher where salary>? and salary<?"
            );
            preparedStatement.setDouble(1, lowerSalary);
            preparedStatement.setDouble(2, upperSalary);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                Teacher teacher = new Teacher(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getInt(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getDouble(6),
                        resultSet.getString(7),
                        Position.valueOf(resultSet.getString(8))
                );
                teachers.add(teacher);

            }

            preparedStatement.close();
            resultSet.close();
        }catch (SQLException e) {
            try {
                throw new NullPoitException("There are no elements in this interval");
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        return teachers;
    }

    @Override
    public List<Teacher> getByLowerAge(Integer lowAge) {
        List<Teacher> teachers = new ArrayList<>();

        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select *from Teacher where age>?"
            );
            preparedStatement.setDouble(1, lowAge);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                Teacher teacher = new Teacher(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getInt(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getDouble(6),
                        resultSet.getString(7),
                        Position.valueOf(resultSet.getString(8))
                );
                teachers.add(teacher);

            }

            preparedStatement.close();
            resultSet.close();
        }catch (SQLException e) {
            try {
                throw new NullPoitException("doesn't have a teacher older than this in database");
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        return teachers;
    }
}
