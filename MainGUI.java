package org.example;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainGUI extends Application {
    @Override
    public void start(Stage stage) {

        // ===== Input Fields =====
        TextField nameField = new TextField();
        nameField.setPromptText("Name");

        TextField emailField = new TextField();
        emailField.setPromptText("Email");

        TextField gradeField = new TextField();
        gradeField.setPromptText("Grade");

        TextField teacherIdField = new TextField();
        teacherIdField.setPromptText("Teacher ID");

        Button addButton = new Button("Add Student");
Button updatebutton = new Button("Update Button ");
Button deletebutton = new Button("Delete");
//---- teacher namefield
        TextField idtField = new TextField();
        idtField.setPromptText("Id");

        TextField namField = new TextField();
        namField.setPromptText("Name");

        TextField mailField = new TextField();
        mailField.setPromptText("Email");

        TextField subField = new TextField();
        subField.setPromptText("Subject");
        Button addteacher = new Button("AddTeacher");
        Button deleteteacher = new Button("Delete");
        // ===== TableView =====
        TableView<Student> table = new TableView<>();

        // Columns
        TableColumn<Student, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Student, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Student, String> emailCol = new TableColumn<>("Email");
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<Student, String> gradeCol = new TableColumn<>("Grade");
        gradeCol.setCellValueFactory(new PropertyValueFactory<>("grade"));

        TableColumn<Student, Integer> teacherIdCol = new TableColumn<>("Teacher ID");
        teacherIdCol.setCellValueFactory(new PropertyValueFactory<>("teacherId"));

        table.getColumns().addAll(idCol, nameCol, emailCol, gradeCol, teacherIdCol);

        // Load initial data
        StudentDAO dao = new StudentDAO();
        table.setItems(FXCollections.observableArrayList(dao.getAllStudents()));
        // ----- teacher table view
TableView<Teacher> tabl = new TableView<>();
TableColumn<Teacher,Integer> idcols = new TableColumn<>("ID");
idcols.setCellValueFactory(new PropertyValueFactory<>("id"));

TableColumn<Teacher,String> namecols = new TableColumn<>("Name");
namecols.setCellValueFactory(new PropertyValueFactory<>("name"));

TableColumn<Teacher,String> subcol = new TableColumn<>("Subject");
subcol.setCellValueFactory(new PropertyValueFactory<>("subject"));

TableColumn<Teacher,String> emailcol = new TableColumn<>("Email");
emailcol.setCellValueFactory(new PropertyValueFactory<>("email"));
              // -----teacher function
tabl.getColumns().addAll(idcols,namecols,subcol,emailcol);
TeacherDAO tdao = new TeacherDAO();
tabl.setItems(FXCollections.observableList(tdao.getAllTeachers()));
addteacher.setOnAction(e->{
    try{

        String name = namField.getText();
        String email = mailField.getText();
        String subject = subField.getText();
        Teacher teacher = new Teacher(0,name,email,subject);
        tdao.addTeacher(teacher);
        tabl.setItems(FXCollections.observableList(tdao.getAllTeachers()));
        namField.clear();
        mailField.clear();
        subField.clear();
        System.out.println("Added Succesfully");

    }catch (NumberFormatException ex)
    {
        System.out.println("Invalid teacher id");
    }
});
deleteteacher.setOnAction(e->{
    try{
        Teacher selection = tabl.getSelectionModel().getSelectedItem();
        if(selection==null){
            System.out.println("Please select ");
        }
        tdao.deleteteacher(selection.getId());
        tabl.setItems(FXCollections.observableList(tdao.getAllTeachers()));
    }catch (NumberFormatException ex){
        System.out.println("invalid ");
    }
});
        // ===== Button Action =====
        addButton.setOnAction(e -> {
            try {
                String name = nameField.getText();
                String email = emailField.getText();
                String grade = gradeField.getText();
                int teacherId = Integer.parseInt(teacherIdField.getText());

                Student student = new Student(0, name, email, grade, teacherId);
                dao.addStudent(student);

                // Refresh table
                table.setItems(FXCollections.observableArrayList(dao.getAllStudents()));

                // Clear input fields
                nameField.clear();
                emailField.clear();
                gradeField.clear();
                teacherIdField.clear();

                System.out.println("Student Added Successfully");

            } catch (NumberFormatException ex) {
                System.out.println("Invalid teacher id");
            }
        });
        updatebutton.setOnAction(e->{
            try {
                String name = nameField.getText();
                String email = emailField.getText();
                String grade = gradeField.getText();
                int teacherid = Integer.parseInt(teacherIdField.getText());
                Student student = new Student(3, name, email, grade, teacherid);
                dao.updateStudent(student);
                table.setItems(FXCollections.observableList(dao.getAllStudents()));
                nameField.clear();
                emailField.clear();
                gradeField.clear();
                teacherIdField.clear();
                System.out.println("student updated");
            }catch (NumberFormatException ex){
                System.out.println("invalid");
            }
        });
        deletebutton.setOnAction(e -> {

          Student selected = table.getSelectionModel().getSelectedItem();
          if(selected==null){
              System.out.println("please select student ");
          }else{
              dao.deleteStudent(selected.getId());
              table.setItems(FXCollections.observableList(dao.getAllStudents()));
          }
        });
TabPane tabPane = new TabPane();
Tab studentTab = new Tab("Students");
VBox studentlayout = new VBox(10);
studentlayout.getChildren().addAll(table, nameField, emailField, gradeField, teacherIdField, addButton,updatebutton,deletebutton);
studentTab.setContent(studentlayout);

Tab teacherTab = new Tab("Teachers");
VBox teacherlayout = new VBox(10);
teacherlayout.getChildren().addAll(tabl,namField,mailField,subField,addteacher,deleteteacher);
teacherTab.setContent(teacherlayout);

tabPane.getTabs().addAll(studentTab,teacherTab);
Scene scene = new Scene(tabPane,600,400);
stage.setScene(scene);
stage.setTitle("School Mangament");
stage.show();
        // ===== Layout =====

    }
}