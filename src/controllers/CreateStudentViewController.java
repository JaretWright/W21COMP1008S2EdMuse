package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import models.Student;

import java.time.LocalDate;

public class CreateStudentViewController {

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
    private void createStudent(){
        Student newStudent = new Student("firstName",
                                        "lastName",
                                        "address",
                                        LocalDate.of(2000,12,23),
                                        1000002);
    }
}
