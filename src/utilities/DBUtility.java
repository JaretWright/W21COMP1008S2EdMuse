package utilities;

import models.Professor;
import models.Student;

import java.time.LocalDate;
import java.util.ArrayList;

public class DBUtility {

    public static boolean validCRN(String crn)
    {
        return crn.matches("21[0-9]{3}");  //21000 -> 21999
    }

    public static ArrayList<Student> getStudentsFromDB()
    {
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student("Rose","Ruffner","3846 St. Paul StreetSt Catharines ON L2S 3A1", LocalDate.of(1975,8,27),100000001));
        students.add(new Student("Jack","Bradbury","867 rue des Églises Est Ste Cecile De Masham QC J0X 2W0", LocalDate.of(1979,10,14),100000002));
        students.add(new Student("Elanore","Sanders","1145 47th Avenue Grassland AB T0A 1V0", LocalDate.of(1940,9,25),100000003));
        students.add(new Student("Nancy","Walsh","1459 Harvest Moon Dr Unionville ON L3R 0L7", LocalDate.of(1999,1,12),100000004));
        students.add(new Student("Greta","Tolbert","642 Front Street Toronto ON M5J 2N1", LocalDate.of(1957,12,18),100000005));
        students.add(new Student("Barbara","Gable","3671 Scotchmere Dr Sarnia ON N7T 7T9", LocalDate.of(2002,11,13),100000006));
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
