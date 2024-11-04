package org.example.model.entity;

import org.example.model.enums.Position;

public class Teacher extends User {

    private Double salary;
    private String subject;
    Position position;

    public Teacher(Integer id, String fullName, Integer age, String email, String phoneNumber, Double salary, String subject, Position position) {
        super(id, fullName, age, email, phoneNumber);
        this.salary = salary;
        this.subject = subject;
        this.position = position;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Teacher{" + super.toString() +
                "salary=" + salary +
                ", subject='" + subject + '\'' +
                ", position=" + position +
                "} " + "\n";
    }
}
