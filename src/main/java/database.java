import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class database {
    private static database instance;
    private Connection connection;
    private Statement statement;

    private database(){
        connection = null;
        statement = null;
    }

    public static database getInstance(){
        if(instance == null){
            instance = new database();
        }
        return instance;
    }

    public void initialize(){
        try{
            Class.forName("org.sqlite.JDBC"); //default
            connection = DriverManager.getConnection("jdbc:sqlite:AdminDatabase.db"); //filename
            statement = connection.createStatement();

            ResultSet rs;
            rs = statement.executeQuery("SELECT * FROM sqlite_master where type='table' and name='admin'");
            if(!rs.next()){
                statement.execute("CREATE TABLE admin" + "("
                        +"adminusername CHAR(50),"
                        +"adminpassword CHAR(35)"
                        +")"
                );
            }
            rs.close();

            rs = statement.executeQuery("SELECT adminusername FROM admin WHERE adminusername='adminpldt' and " +
                    "adminpassword='adminpass'");
            if(!rs.next()){
                statement.execute("INSERT INTO admin VALUES('adminpldt','adminpass')");
            }
            rs.close();

            rs= statement.executeQuery("SELECT * FROM sqlite_master where type='table' and name ='studentlogin'");
            if(!rs.next()){
                statement.execute("CREATE TABLE studentlogin" + "("
                        +"stud_id INT NOT NULL,"
                        +"lastname CHAR(35) NOT NULL"
                        + ")"
                );
                System.out.println("Successfully loaded a table named studentlogin.");
            }
            rs.close();

            rs= statement.executeQuery("SELECT * FROM sqlite_master where type='table' and name ='anadawan'");
            if(!rs.next()){
                statement.execute("CREATE TABLE anadawan" + "("
                        +"stud_id INT NOT NULL,"
                        +"lastname CHAR(35) NOT NULL"
                        + ")"
                );
                System.out.println("Successfully loaded a table named anadawan.");
            }
            rs.close();

            rs = statement.executeQuery("SELECT * FROM sqlite_master WHERE type='table' and name='student'"); //checheck if may table na
            if(!rs.next()){//if wala
                statement.execute("CREATE TABLE student" + "(" //panggawa ng table
                        + "student_id INT PRIMARY KEY,"
                        + "first_name CHAR(35) NOT NULL,"
                        + "middle_name CHAR(35) NOT NULL,"
                        + "last_name CHAR(35) NOT NULL,"
                        + "course CHAR(35) NOT NULL,"
                        + "section CHAR(35) NOT NULL,"
                        + "address CHAR(35) NOT NULL"
                        + ")");
                System.out.println("Successfully loaded a table named student.");
            }
            rs.close();

            //SELECT e.last_name, d.finalgrade FROM student e, grades d WHERE e.student_id = d.id
            //SELECT * FROM student, grades where username = student_id and password=last_name; for student login
            rs = statement.executeQuery("SELECT * FROM sqlite_master WHERE type='table' and name='grades'");
            if(!rs.next()){
                statement.execute("CREATE TABLE grades" + "("
                        + "id INT NOT NULL,"
                        + "subject CHAR(35) NOT NULL,"
                        + "grade1 INT NOT NULL,"
                        + "grade2 INT NOT NULL,"
                        + "grade3 INT NOT NULL,"
                        + "grade4 INT NOT NULL,"
                        + "grade5 INT NOT NULL,"
                        + "finalgrade INT NOT NULL" + ")"
                );
                System.out.println("Successfully loaded a table named grades.");
            }
            rs.close();

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void acc_update(String LogID, String LogPass) {
        try {
            statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO anadawan(stud_id, lastname)" +
                    "VALUES ('" + LogID +"' , '" + LogPass + "'" + ")");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update_db(String strSID, String strFN, String strMN, String strLN, String strCo, String strSec, String strAdrs){
        try {
            statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO student(student_id,first_name,middle_name,last_name,course,section,address) " +
                    "VALUES('"+strSID +"', '"+ strFN +"', '"+ strMN +"', '"+ strLN +"', '"+ strCo +"', '"+ strSec +"', '" + strAdrs +"'" + ")");
            System.out.println("Successfully inserted values to the table named student.");


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void grade_db(String id, String course,double g1, double g2, double g3, double g4, double g5, double g6){
        try{
            statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO grades(id,subject,grade1,grade2,grade3,grade4,grade5,finalgrade) VALUES('"
                    +id+"','"+course+"','"+g1+"','"+g2+"','"+g3+"','"+g4+"','"+g5+"','"+g6+"'"+")"

            );
            System.out.println("Successfully inserted values to the table named grades.");


        }catch(Exception e){
            e.printStackTrace();
        }
    }


    public ResultSet getResult(String query){ //getter para sa frame.java
        try{
            return statement.executeQuery(query);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet setResult(String query){
        try{
            statement.executeUpdate(query);

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}