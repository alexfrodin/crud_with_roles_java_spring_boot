package se.sti.javasti.services;

import se.sti.javasti.model.Student;

import java.util.List;

public interface StudentService {
    List<Student> listAllStudents();

    void createStudent(Student student);

    Student getStudent(Long id);

    void deleteStudent(Long id);
}
