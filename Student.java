package org.example;

public class Student {
    int id;
    String name;
    String email;
    String grade;
    private int teacherId;
    Student()
    {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGrade() {
        return grade;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
    public Student(int id, String name, String email, String grade,int teacherId)
    {
        this.id=id;
        this.name=name;
        this.email=email;
        this.grade=grade;
        this.teacherId= teacherId;
    }
    @Override
    public String toString()
    {
        return id + " | "+name+" | "+email+" | "+" | "+grade +" | "+teacherId;
    }
}
