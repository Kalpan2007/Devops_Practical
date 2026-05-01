package com.example.studentcrudapi.service;

import com.example.studentcrudapi.model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class StudentService {
    
    private final List<Student> students = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong(1);
    
    public StudentService() {
        // Add sample default data
        students.add(new Student(counter.getAndIncrement(), "John Doe", "john.doe@example.com"));
        students.add(new Student(counter.getAndIncrement(), "Jane Smith", "jane.smith@example.com"));
        students.add(new Student(counter.getAndIncrement(), "Bob Johnson", "bob.johnson@example.com"));
    }
    
    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }
    
    public Optional<Student> getStudentById(Long id) {
        return students.stream()
                .filter(student -> student.getId().equals(id))
                .findFirst();
    }
    
    public Student createStudent(Student student) {
        student.setId(counter.getAndIncrement());
        students.add(student);
        return student;
    }
    
    public Optional<Student> updateStudent(Long id, Student studentDetails) {
        Optional<Student> existingStudent = getStudentById(id);
        
        if (existingStudent.isPresent()) {
            Student student = existingStudent.get();
            student.setName(studentDetails.getName());
            student.setEmail(studentDetails.getEmail());
            return Optional.of(student);
        }
        
        return Optional.empty();
    }
    
    public boolean deleteStudent(Long id) {
        return students.removeIf(student -> student.getId().equals(id));
    }
}
