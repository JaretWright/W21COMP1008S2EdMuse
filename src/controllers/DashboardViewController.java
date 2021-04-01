package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import models.Professor;
import models.Student;
import utilities.DBUtility;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DashboardViewController implements Initializable {

    @FXML
    private Label studentsLabel;

    @FXML
    private ListView<Student> studentListView;

    @FXML
    private Label professorsLabel;

    @FXML
    private ListView<Professor> professorsListView;

    @FXML
    private Label coursesLabel;
//    @FXML
//    private ListView<Course> coursesListView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            studentListView.getItems().addAll(DBUtility.getStudentsFromDB());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        studentsLabel.setText(String.format("Students: %d",studentListView.getItems().size()));

        professorsListView.getItems().addAll(DBUtility.getProfessorsFromDB());
        professorsLabel.setText(String.format("Professors: %d",professorsListView.getItems().size()));
    }
}
