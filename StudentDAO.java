package org.example;
import java.sql.Connection;
import java.sql.PreparedStatement;
import  java.sql.ResultSet;
import  java.util.List;
import java.util.ArrayList;
public class StudentDAO {

    public void addStudent(Student s)
    {
        String sql = "INSERT INTO student(name,email,grade,teacher_id)Values(?,?,?,?)";
        try(Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql))
        {

            stmt.setString(1,s.getName());
            stmt.setString(2,s.getEmail());
            stmt.setString(3,s.getGrade());
            stmt.setInt(4, s.getTeacherId());
            stmt.executeUpdate();
            System.out.println("Student added to the Database");
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public List<Student> getAllStudents(){
        List<Student> students = new ArrayList<>();
        String sql = " SELECT * FROM  student";
        try(Connection conn = DBConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery())
        {
            while(rs.next()){
                Student s= new Student(
                  rs.getInt("id"),
                  rs.getString("name"),
                  rs.getString("email"),
                  rs.getString("grade"),
                        rs.getInt("teacher_id")
                );
                students.add(s);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return students;
    }
    public boolean updateStudent(Student s){
        String sql = "UPDATE student SET name=?, email=?, grade=? WHERE id=?";
        try(Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setString(1, s.getName());
            stmt.setString(2, s.getEmail());
            stmt.setString(3, s.getGrade());
            stmt.setInt(4, s.getId());

            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

public boolean deleteStudent(int id)
{
    String sql = "DELETE FROM student WHERE id=?  ";
    try(Connection conn = DBConnection.getConnection();
    PreparedStatement stmt = conn.prepareStatement(sql))
    {
        stmt.setInt(1,id);
        int rows = stmt.executeUpdate();
        return rows>0;
    }catch (Exception e)
    {
        e.printStackTrace();
    }
    return false;
}

}
