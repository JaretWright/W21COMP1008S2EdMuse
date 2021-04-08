package models;

import utilities.DBUtility;

import java.time.LocalDate;
import java.util.TreeMap;

public class Student extends Person{
    private int studentNum;
    private TreeMap<String, Integer> grades;

    public Student(String firstName, String lastName, String address, LocalDate birthday, int studentNum) {
        super(firstName, lastName, address, birthday);
        setStudentNum(studentNum);
        grades = new TreeMap<>();
    }

    public int getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(int studentNum) {
        if (studentNum>=100000000)
            this.studentNum = studentNum;
        else
            throw new IllegalArgumentException("student number must be greater than 100000000");
    }

    public TreeMap<String, Integer> getGrades() {
        return grades;
    }

    /**
     * This method will validate that a courseCRN is valid and that the grade is in the range
     * of 0-100.  If the validation passes, it will add it to the list of grades
     * @param courseCRN
     * @param grade
     */
    public void addGrade(String courseCRN, int grade)
    {
        if (!DBUtility.validCRN(courseCRN))
            throw new IllegalArgumentException("course CRN not found in database");

        if (grade>=0 && grade<=100)
            grades.put(courseCRN, grade);
        else
            throw new IllegalArgumentException("grade must be 0-100");
    }

    /**
     * This method receives a CRN and returns the corresponding grade earned.
     * If the crn does not exist for the student, a grade of -1 is returned
     */
    public int getGradeForCourse(String crn)
    {
        //check if the crn is in the grades treemap
        if (grades.get(crn) == null)
            return -1;

        //if yes return it
        return grades.get(crn);
    }

    /**
     * This method will return the number of courses that have a grade at 50 or above
     */
    public int getNumOfCoursesPassed()
    {
        int numOfPassedCourses = 0;

        //loop over the courses with grades and increase the count by 1 for any course that has
        //a grade at or above 50%
        for (Integer grade : grades.values())
        {
            if (grade >= 50)
                numOfPassedCourses++;
        }
        return numOfPassedCourses;
    }

    public double getAvgGrade()
    {
        //if no grades are recorded, return -1 to indicate no grades recorded
        if (grades.size()==0)
            return -1;

        double sumOfGrades = 0;

        for (Integer grade : grades.values())
            sumOfGrades += grade;

        return sumOfGrades/grades.size();
    }
}
