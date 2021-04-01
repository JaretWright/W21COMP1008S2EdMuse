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

    public static ArrayList<Professor> getProfessorsFromDB()
    {
        ArrayList<Professor> professors = new ArrayList<>();
        professors.add(new Professor("Lois","Parker","674 Goyeau Ave Windsor ON N9A 1H9",LocalDate.of(1987,3,21),10001));
        professors.add(new Professor("Ginger","Harris","3514 Yonge Street Toronto ON M4W 1J7",LocalDate.of(1967,11,12),10002));
        professors.add(new Professor("Winchester","Solomon","3099 Balmy Beach Road Owen Sound ON N4K 2N7",LocalDate.of(1977,12,18),10003));
        professors.add(new Professor("John","Pressley","1101 Eglinton Avenue Toronto ON M4P 1A6",LocalDate.of(1973,11,29),10004));
        return professors;
    }

}
