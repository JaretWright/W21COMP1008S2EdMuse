package utilities;

import models.Professor;
import models.Student;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class DBUtility {
    private static String user = "student";
    private static String password = "student";
    private static String connString = "jdbc:mysql://localhost:3306/edmuse";

    public static boolean validCRN(String crn)
    {
        return crn.matches("21[0-9]{3}");  //21000 -> 21999
    }

    public static ArrayList<Student> getStudentsFromDB() throws SQLException {
        ArrayList<Student> students = new ArrayList<>();

        //connect to the DB
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try{
            //connect to the Database
            conn = DriverManager.getConnection(connString, user, password);

            statement = conn.createStatement();

            //run the query on the DB
            resultSet = statement.executeQuery("SELECT * FROM students");

            //loop over the resultset and create Student objects
            while (resultSet.next()){
                Student newStudent = new Student(resultSet.getString("firstName"),
                                                resultSet.getString("lastName"),
                                                resultSet.getString("address"),
                                                resultSet.getDate("birthday").toLocalDate(),
                                                resultSet.getInt("studentNum"));
                students.add(newStudent);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally {
            if (conn != null)
                conn.close();
            if (statement != null)
                statement.close();
            if (resultSet != null)
                resultSet.close();
        }
        return students;
    }

    public static ArrayList<Professor> getProfessorsFromDB() throws SQLException {
        ArrayList<Professor> professors = new ArrayList<>();

        //connect to the DB
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try{
            //connect to the Database
            conn = DriverManager.getConnection(connString, user, password);

            statement = conn.createStatement();

            //run the query on the DB
            resultSet = statement.executeQuery("SELECT * FROM professors");

            //loop over the resultset and create Professor objects
            while (resultSet.next()){
                Professor newProfessor = new Professor(resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("address"),
                        resultSet.getDate("birthday").toLocalDate(),
                        resultSet.getInt("professorID"));
                professors.add(newProfessor);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally {
            if (conn != null)
                conn.close();
            if (statement != null)
                statement.close();
            if (resultSet != null)
                resultSet.close();
        }
        return professors;
    }

}
