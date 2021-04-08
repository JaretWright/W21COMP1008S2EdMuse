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

    /**
     * This method receive a Student object and insert it into the database
     * @return the student number generated by the database server
     */
    public static int insertStudentIntoDB(Student newStudent) throws SQLException {
        int studentNum = -1;

        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try{
            //1. connect to the DB
            conn = DriverManager.getConnection(connString,user,password);

            //2. create our sql statement
            statement = conn.prepareStatement("INSERT INTO students (firstName, lastName, address, birthday) VALUES " +
                                              "(?,?,?,?)", new String[]{"studentNum"});

            //3. bind the values to the datatypes
            statement.setString(1, newStudent.getFirstName());
            statement.setString(2, newStudent.getLastName());
            statement.setString(3, newStudent.getAddress());
            statement.setDate(4, Date.valueOf(newStudent.getBirthday()));

            //4. execute the insert
            statement.executeUpdate();

            //5. get the student number returned
            resultSet = statement.getGeneratedKeys();

            //6. update the student number variable
            while (resultSet.next())
                studentNum = resultSet.getInt(1);

        }catch (Exception e)
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
            return studentNum;
        }
    }

    /**
     * This method will return an ArrayList of String objects.  The Strings represent the course code and name
     */
    public static ArrayList<String> getCourseCodesAndNames()
    {
        ArrayList<String> courses = new ArrayList<>();
        //try with resources...doing this will auto-close the objects that can be closed
        try(
                Connection conn = DriverManager.getConnection(connString, user, password);
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM approvedCourses");
                )
        {
            while (resultSet.next())
            {
                courses.add(resultSet.getString("courseCode")+"-"+resultSet.getString("courseName"));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return courses;
    }


}
