package org.example;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // 1️⃣ Test DB Connection
        try (Connection conn = DBConnection.getConnection()) {
            System.out.println("Connection is successful");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 2️⃣ Add a Teacher
        Teacher teacher = new Teacher(0, "Shayan", "Maths", "sha@example.com");
        TeacherDAO teacherDAO = new TeacherDAO();
        teacherDAO.addTeacher(teacher);

        List<Teacher> allTeachers = teacherDAO.getAllTeachers();
        System.out.println("All Teachers: " + allTeachers);

        // Grab the teacher ID inserted (assuming auto_increment)
        int teacherId = allTeachers.get(0).getId(); // first teacher

        // 3️⃣ Add a Student with valid teacher_id
        Student student = new Student(
                0,                       // ID = 0 for auto_increment
                "Ahmed",
                "ahmed@example.com",
                "A",
                teacherId               // assign valid teacher ID
        );

        StudentDAO studentDAO = new StudentDAO();
        studentDAO.addStudent(student);

        List<Student> allStudents = studentDAO.getAllStudents();
        System.out.println("All Students: " + allStudents);

        // Grab the student ID inserted (assuming auto_increment)
        int studentId = allStudents.get(0).getId(); // first student

        // 4️⃣ Mark Attendance
        Attendance attendance = new Attendance(
                0,               // ID = 0 for auto_increment
                studentId,       // valid student ID
                LocalDate.now(),
                "PRESENT"
        );

        AttendanceDAO attendanceDAO = new AttendanceDAO();
        attendanceDAO.markAttendance(attendance);

        List<Attendance> allAttendance = attendanceDAO.getAllAttendance();
        System.out.println("All Attendance Records: " + allAttendance);

        // 5️⃣ Update Attendance
        Attendance updatedAttendance = new Attendance(
                allAttendance.get(0).getId(), // get the inserted attendance ID
                studentId,
                LocalDate.now(),
                "ABSENT"
        );
        attendanceDAO.updateAttendance(updatedAttendance);
        System.out.println("Attendance after update: " + attendanceDAO.getAllAttendance());

        // ✅ Optional: Delete Student or Attendance
        // studentDAO.deleteStudent(studentId);
        // attendanceDAO.deleteAttendance(updatedAttendance.getId());
    }
}
