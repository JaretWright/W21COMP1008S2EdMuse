package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import utilities.DBUtility;
import utilities.SceneChanger;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CreateProfessorViewController implements Initializable{

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private TextField addressTextField;

    @FXML
    private DatePicker birthdayDatePicker;

    @FXML
    private Label msgLabel;

    @FXML
    private VBox teachablesVBox;

    @FXML
    void createProfessor(ActionEvent event) {

    }

    @FXML
    void returnToDashboard(ActionEvent event) throws IOException {
        SceneChanger.changeScenes(event,"../views/dashboardView.fxml","EdMuse");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<String> courses = DBUtility.getCourseCodesAndNames();

        for (String course : courses)
        {
            CheckBox checkBox = new CheckBox(course);
            teachablesVBox.getChildren().add(checkBox);
        }

    }
}