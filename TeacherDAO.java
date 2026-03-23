package org.example;
import java.sql.*;
import java.util.ArrayList;
import  java.util.List;

public class TeacherDAO {

    public void addTeacher(Teacher t){
        String sql = "INSERT INTO teacher(id,name,subject,email)Values(?,?,?,?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)){
           stmt.setInt( 1,t.getId());
           stmt.setString(2,t.getName());
           stmt.setString(3,t.getSubject());
           stmt.setString(4,t.getEmail());
           stmt.executeUpdate();
           System.out.println("Teacher added to the database");

        }catch (Exception e)
        {
            e.printStackTrace();
        }
        }
        public List<Teacher> getAllTeachers(){
        List<Teacher> teachers = new ArrayList<>();
        String sql = "SELECT * FROM teacher";
        try(Connection conn = DBConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery()){
            while (rs.next()){
                Teacher t = new Teacher(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("subject"),
                rs.getString("email")
                        );
                teachers.add(t);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return teachers;
        }
        public boolean updateTeacher(Teacher t)
        {
            String sql = "Update teacher SET name = ?, subject = ? , email = ? WHERE id = ?";
            try(Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql))
            {
                stmt.setString(1, t.getName());
                stmt.setString(2,t.getSubject());
                stmt.setString(3,t.getEmail());
                stmt.setInt(4,t.getId());
                int rows = stmt.executeUpdate();
                return rows>0;
            }catch (Exception e)
            {
                 e.printStackTrace();
            }
            return false;
        }
        public boolean deleteteacher(int id)
        {
            String sql = "DELETE  FROM teacher WHERE id=?";
            try(Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

                stmt.setInt(1,id);
                int rows = stmt.executeUpdate();
                return rows>0;
        }catch(Exception e){
                e.printStackTrace();
        }
            return false;
        }
    }


