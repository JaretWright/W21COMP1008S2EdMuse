package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import models.Student;
import utilities.DBUtility;
import utilities.SceneChanger;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class CreateStudentViewController implements Initializable {

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private TextField addressTextField;

    @FXML
    private TextField studentNumberTextField;

    @FXML
    private DatePicker birthdayDatePicker;

    @FXML
    private Label msgLabel;

    @FXML
    private ComboBox<String> majorComboBox;

    @FXML
    private void createStudent(){
        if (fieldsArePopulated())
        {
            try {
                Student newStudent = new Student(firstNameTextField.getText(),
                        lastNameTextField.getText(),
                        addressTextField.getText(),
                        birthdayDatePicker.getValue());
                int studentNum = DBUtility.insertStudentIntoDB(newStudent);
                newStudent.setStudentNum(studentNum);
                msgLabel.setText(newStudent.toString() + majorComboBox.getValue());
            }catch(IllegalArgumentException | SQLException e)
            {
                msgLabel.setText(e.getMessage());
            }
        }
    }

    private boolean fieldsArePopulated()
    {
        String errMsg = "The following fields are empty: ";
        if (firstNameTextField.getText().isEmpty())
            errMsg += "first name, ";

        if (lastNameTextField.getText().isEmpty())
            errMsg += "last name, ";

        if (addressTextField.getText().isEmpty())
            errMsg += "address, ";

        if (birthdayDatePicker.getValue()==null)
            errMsg += "birthday, ";

        if (majorComboBox.getValue() == null)
            errMsg += "major, ";

        if (errMsg.equals("The following fields are empty: "))
            return true;

        //there was at least 1 empty field
        msgLabel.setText(errMsg.substring(0, errMsg.length()-2));
        return false;
    }

    @FXML
    private void returnToDashboard(ActionEvent event) throws IOException {
        SceneChanger.changeScenes(event, "../views/dashboardView.fxml","EdMuse");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        majorComboBox.getItems().addAll(DBUtility.getMajors());
    }
}
