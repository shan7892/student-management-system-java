package org.example;
import java.sql.*;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
public class AttendanceDAO {
    public void markAttendance(Attendance attendance) {

        String sql = "INSERT INTO attendance (student_id, date, status) VALUES (?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, attendance.getStudentId());
            ps.setDate(2, Date.valueOf(attendance.getDate()));
            ps.setString(3, attendance.getStatus());

            ps.executeUpdate();

            System.out.println("Attendance is marked");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
public List<Attendance> getAllAttendance() {

        List<Attendance> list = new ArrayList<>();
        String sql = "SELECT * FROM attendance";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Attendance attendance = new Attendance(
                        rs.getInt("id"),
                        rs.getInt("student_id"),
                        rs.getDate("date").toLocalDate(),
                        rs.getString("status")
                );

                list.add(attendance);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    public void updateAttendance(Attendance attendance) {
        String sql = "UPDATE attendance SET status=? WHERE id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, attendance.getStatus());
            ps.setInt(2, attendance.getId());
            ps.executeUpdate();
            System.out.println("Attendance update");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void deleteAttendance(int id) {

        String sql = "DELETE FROM attendance WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Attendance deleted successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
