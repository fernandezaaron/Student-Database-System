import java.awt.*;
import java.io.*;
import java.sql.ResultSet;
import java.util.*;

public class Printer {
    String username, pass;
    String Student_ID, First_Name, Mid_Name, Last_Name, Section, Course;
    String Subject, Final_Grade;
    String Whole_Name;
    Hashtable<String, String> Print;
    PrintWriter writeFile;
    File srcFile;
    Formatter output;

    public Printer() throws IOException {
        try {
            database db = database.getInstance();
            ResultSet sr = db.getResult("SELECT * FROM anadawan");

            while (sr.next()) {
                username = sr.getString("stud_id");
                pass = sr.getString("lastname");
            }
            sr.close();
        } catch (Exception exc) {
            exc.printStackTrace();
        }

        try {
            database db = database.getInstance();
            ResultSet as = db.getResult("SELECT * FROM studentlogin, student WHERE '" + username + "'  = student_id");
            while (as.next()) {
                Student_ID = as.getString("student_id");
                First_Name = as.getString("first_name");
                Mid_Name = as.getString("middle_name");
                Last_Name = as.getString("last_name");
                Section = as.getString("section");
                Course = as.getString("course");
                Whole_Name = First_Name + " " + Mid_Name + " " + Last_Name;
            }
            as.close();

            ResultSet reset = db.getResult("SELECT subject, finalgrade FROM grades WHERE '"+username+"' = id");
            Print = new Hashtable<>();
            if(!Print.isEmpty()) {
                Print.clear();
            }
            while(reset.next()){
                Subject = as.getString("subject");
                Final_Grade = as.getString("finalgrade");
                Print.put(Subject, Final_Grade);
            }
            reset.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        srcFile = new File("./TextFiles/"+pass + ".txt");
        try {
            writeFile = new PrintWriter("./TextFiles/"+pass + ".txt");
            output = new Formatter();
            Enumeration<String> enumeration = Print.keys();

            writeFile.print("+---------------------------------------------+\n");
            writeFile.printf("|%-45s%1s%n", Whole_Name,"|");
            writeFile.printf("|%-45s%1s%n", Student_ID, "|");
            writeFile.printf("|%-25s%-20s%1s%n", Course, Section, "|");
            writeFile.print("+---------------------------------------------+\n");
            writeFile.print("|Subject                  |Grade              |\n");
            while(enumeration.hasMoreElements()) {
                String key = enumeration.nextElement();
                String FG = Print.get(key);
                writeFile.printf("|%-25s|%-19s%1s%n", key, converter(FG), "|");
            }
            writeFile.print("+---------------------------------------------+\n");
            writeFile.close();

            Desktop desktop = Desktop.getDesktop();
            desktop.open(srcFile);
        } catch(IOException e) {
            e.getStackTrace();
        }
        finally {
            Print.clear();
        }
    }

    public String converter(String convert) {
        double convict = Double.parseDouble(convert);

        if(convict >= 95 && convict <= 100) {
            convict = 4.0;
        }
        else if(convict >= 93 && convict <= 96) {
            convict = 3.5;
        }
        else if(convict >= 89 && convict <= 92) {
            convict = 3.0;
        }
        else if(convict >= 85 && convict <= 88) {
            convict = 2.5;
        }
        else if(convict >= 80 && convict <= 84) {
            convict = 2.0;
        }
        else if(convict >= 75 && convict <= 79) {
            convict = 1.5;
        }
        else if(convict >= 70 && convict <= 74) {
            convict = 1.0;
        }
        else if(convict < 70) {
            convict = 0.5;
        }

        convert = Double.toString(convict);
        return convert;
    }
}
