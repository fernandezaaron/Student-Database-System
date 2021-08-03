import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.Vector;

public class StudentPage extends JFrame {

    String username, pass;
    JButton Student_ProfileViewJB, Student_GradeViewJB, Student_HomeJB, Student_AboutUsJB, LogoutJB, PrintJB;
    JTextField GradesView;
    JTable GradesViewTable;
    JLabel Welcome;
    JTextArea ta_first_name, ta_mid_name, ta_last_name, ta_student_id, ta_course, ta_section, ta_address;
    JTextArea ta_first_name2, ta_mid_name2, ta_last_name2, ta_student_id2, ta_course2, ta_section2, ta_address2;
    JTextArea Student_Details, Student_Account, Student_Personal;
    JLabel Student_BG, Student_GIF, Student_AboutUs, sa,sa2, Student_dasquad, satitle2,welcomegif_student, quackeroats_student;
    JPanel Student_Panel, Student_Profile, Student_Grades, Student_Home, Student_PAboutUs;
    JPanel Imagery;
    JTable StudentTable, GradesTable;
    DefaultTableModel TableModel, TableModel2, TableModel3;
    JScrollPane Grade_ScrollBar, Student_ScrollBar, GradeView_ScrollBar;
    CardLayout card = new CardLayout();

    public StudentPage() throws IOException {
        super("Student Database");

        setIconImage(ImageIO.read(new File("./Interface/SA.png")));

        BufferedImage b = ImageIO.read(new File("./Interface/asdf.png"));
        Student_AboutUs = new JLabel(new ImageIcon(b));

        BufferedImage c = ImageIO.read(new File("./Interface/SA.png"));
        sa = new JLabel(new ImageIcon(c));

        BufferedImage d = ImageIO.read(new File("./Interface/SA2.png"));
        sa2 = new JLabel(new ImageIcon(d));

        BufferedImage e = ImageIO.read(new File("./Interface/SA2.png"));
        satitle2 = new JLabel(new ImageIcon(e));

        BufferedImage g = ImageIO.read(new File("./Interface/dasquad.png"));
        Student_dasquad = new JLabel(new ImageIcon(g));

        BufferedImage h = ImageIO.read(new File("./Interface/BGtry.png"));
        Student_BG = new JLabel(new ImageIcon(h));

        Icon ic = new ImageIcon("./Interface/oop.gif");
        Student_GIF = new JLabel(ic);

        Icon ic2 = new ImageIcon("./Interface/welcome.gif");
        welcomegif_student = new JLabel(ic2);

        Icon ic3 = new ImageIcon("./Interface/studentgif2.gif");
        quackeroats_student = new JLabel(ic3);

        Font font = new Font("Arial",Font.PLAIN, 15);

        try {
            database db = database.getInstance();
            ResultSet sr = db.getResult("SELECT * FROM anadawan");

            while(sr.next()) {
                username = sr.getString("stud_id");
                pass = sr.getString("lastname");
            }
            sr.close();
        } catch (Exception exc) {
            exc.printStackTrace();
        }

        Student_ProfileViewJB = new JButton(new AbstractAction("STUDENT PROFILE") {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == Student_ProfileViewJB){
                    try{
                        card.show(Student_Panel, "sp");
                    }catch(Exception ex){
                        ex.printStackTrace();
                    }
                }
            }
        });

        Student_GradeViewJB = new JButton(new AbstractAction("STUDENT GRADES") {
            @Override
            public void actionPerformed(ActionEvent e) {
                card.show(Student_Panel, "sg");
            }
        });

        PrintJB = new JButton(new AbstractAction("PRINT GRADES") {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Printer PG = new Printer();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        Student_HomeJB = new JButton(new AbstractAction("HOME") {
            @Override
            public void actionPerformed(ActionEvent e) {
                card.show(Student_Panel, "hm");
            }
        });

        Student_AboutUsJB = new JButton(new AbstractAction("ABOUT US") {
            @Override
            public void actionPerformed(ActionEvent e) {
                card.show(Student_Panel,"abtus");
            }
        });

        LogoutJB = new JButton(new AbstractAction("Logout") {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == LogoutJB){
                    try {
                        dispose();
                        Login log = new Login();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }
        });

        LogoutJB.setBounds(970,0,200,55);
        LogoutJB.setBackground(new Color(246,145,91));
        LogoutJB.setOpaque(false);
        LogoutJB.setFocusable(false);

        TableModel = new DefaultTableModel(new Object[]{"ID","First Name","Middle Name","Last Name", "Course", "Section","Address"},0);

        StudentTable = new JTable(TableModel);
        StudentTable.setBounds(500,20,700,Integer.MAX_VALUE);

        Student_ScrollBar = new JScrollPane(StudentTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        Student_ScrollBar.setBounds(500,20,700,550);
        Student_ScrollBar.setVisible(true);

        TableModel2 = new DefaultTableModel(new Object[]{"Last Name", "ID", "Summative 1", "Summative 2", "Summative 3", "Summative 4", "Finals","FINAL GRADE"}, 0);

        GradesTable = new JTable(TableModel2);
        GradesTable.setVisible(true);
        GradesTable.setBounds(500,20,700,Integer.MAX_VALUE);

        Grade_ScrollBar = new JScrollPane(GradesTable,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        Grade_ScrollBar.setBounds(550,20,700,550);
        Grade_ScrollBar.setEnabled(true);
        Grade_ScrollBar.setVisible(true);

        //Student_Panel(studentprofile)
        Student_Panel =  new JPanel();
        Student_Panel.setBackground(Color.black);
        Student_Panel.setBounds(0,95,1280,720);
        Student_Panel.setOpaque(false);
        Student_Panel.setLayout(card);
        Student_Panel.setVisible(true);

        Student_Profile = new JPanel();
        Student_Profile.setLayout(null);
        Student_Profile.setBackground(Color.GRAY);
        Student_Profile.setOpaque(false);

        Student_Grades = new JPanel();
        Student_Grades.setLayout(null);
        Student_Grades.setBackground(Color.RED);
        Student_Grades.setOpaque(false);

        Student_Home = new JPanel();
        Student_Home.setBackground(Color.black);
        Student_Home.setOpaque(false);

        Student_HomeJB.setBounds(112,0,200,55);
        Student_HomeJB.setBackground(new Color(255, 255, 255));
        Student_HomeJB.setOpaque(false);
        Student_HomeJB.setVisible(true);

        Student_ProfileViewJB.setBounds(330,0,200,55);
        Student_ProfileViewJB.setBackground(new Color(255, 255, 255));
        Student_ProfileViewJB.setOpaque(false);
        Student_ProfileViewJB.setVisible(true);

        Student_Account = new JTextArea();
        Student_Account.setLayout(null);
        Student_Account.setBounds(110,20,150,50);
        Student_Account.setFont(new Font("Arial", Font.BOLD, 25));
        Student_Account.setForeground(Color.orange);
        Student_Account.setOpaque(false);
        Student_Account.setText("ACCOUNT");

        Student_Personal = new JTextArea();
        Student_Personal.setLayout(null);
        Student_Personal.setBounds(110,110,170,50);
        Student_Personal.setFont(new Font("Arial", Font.BOLD, 25));
        Student_Personal.setForeground(Color.orange);
        Student_Personal.setOpaque(false);
        Student_Personal.setText("PROFILE");

        Student_Details = new JTextArea();
        Student_Details.setLayout(null);
        Student_Details.setBounds(110,350,240,50);
        Student_Details.setFont(new Font("Arial", Font.BOLD, 25));
        Student_Details.setForeground(Color.orange);
        Student_Details.setOpaque(false);
        Student_Details.setText("STUDENT DETAILS");

        ta_student_id2 = new JTextArea();
        ta_student_id2.setLayout(null);
        ta_student_id2.setBounds(110,70,150,20);
        ta_student_id2.setBackground(new Color(224,198,164));
        ta_student_id2.setFont(font);
        ta_student_id2.setText("Student ID: ");
        ta_student_id2.setEditable(false);

        ta_first_name2 = new JTextArea();
        ta_first_name2.setLayout(null);
        ta_first_name2.setBounds(110,150,150,20);
        ta_first_name2.setBackground(new Color(224,198,164));
        ta_first_name2.setFont(font);
        ta_first_name2.setText("First Name: ");
        ta_first_name2.setEditable(false);

        ta_mid_name2 = new JTextArea();
        ta_mid_name2.setLayout(null);
        ta_mid_name2.setBounds(110,200,150,20);
        ta_mid_name2.setBackground(new Color(224,198,164));
        ta_mid_name2.setFont(font);
        ta_mid_name2.setText("Middle Name: ");
        ta_mid_name2.setEditable(false);

        ta_last_name2 = new JTextArea();
        ta_last_name2.setLayout(null);
        ta_last_name2.setBounds(110,250,150,20);
        ta_last_name2.setBackground(new Color(224,198,164));
        ta_last_name2.setFont(font);
        ta_last_name2.setText("Last Name: ");
        ta_last_name2.setEditable(false);

        ta_address2 = new JTextArea();
        ta_address2.setLayout(null);
        ta_address2.setBounds(110,300,150,20);
        ta_address2.setBackground(new Color(224,198,164));
        ta_address2.setFont(font);
        ta_address2.setText("Address: ");
        ta_address2.setEditable(false);

        ta_course2 = new JTextArea();
        ta_course2.setLayout(null);
        ta_course2.setBounds(110,410,150,20);
        ta_course2.setBackground(new Color(224,198,164));
        ta_course2.setFont(font);
        ta_course2.setText("Course: ");
        ta_course2.setEditable(false);

        ta_section2 = new JTextArea();
        ta_section2.setLayout(null);
        ta_section2.setBounds(110, 460, 150, 20);
        ta_section2.setBackground(new Color(224,198,164));
        ta_section2.setFont(font);
        ta_section2.setText("Section: ");
        ta_section2.setEditable(false);

        ta_student_id = new JTextArea();
        ta_student_id.setLayout(null);
        ta_student_id.setFont(font);
        ta_student_id.setBounds(280,70,150,20);
        ta_student_id.setBackground(new Color(236,219,197));
        ta_student_id.setEditable(false);

        ta_first_name = new JTextArea();
        ta_first_name.setLayout(null);
        ta_first_name.setFont(font);
        ta_first_name.setBounds(280,150,150,20);
        ta_first_name.setBackground(new Color(236,219,197));
        ta_first_name.setEditable(false);

        ta_mid_name = new JTextArea();
        ta_mid_name.setLayout(null);
        ta_mid_name.setFont(font);
        ta_mid_name.setBounds(280,200,150,20);
        ta_mid_name.setBackground(new Color(236,219,197));
        ta_mid_name.setEditable(false);

        ta_last_name = new JTextArea();
        ta_last_name.setLayout(null);
        ta_last_name.setFont(font);
        ta_last_name.setBounds(280,250,150,20);
        ta_last_name.setBackground(new Color(236,219,197));
        ta_last_name.setEditable(false);

        ta_address = new JTextArea();
        ta_address.setLayout(null);
        ta_address.setFont(font);
        ta_address.setBounds(280,300,150,20);
        ta_address.setBackground(new Color(236,219,197));
        ta_address.setEditable(false);

        ta_course = new JTextArea();
        ta_course.setLayout(null);
        ta_course.setFont(font);
        ta_course.setBounds(280,410,150,20);
        ta_course.setBackground(new Color(236,219,197));
        ta_course.setEditable(false);

        ta_section = new JTextArea();
        ta_section.setLayout(null);
        ta_section.setBounds(280, 460, 150, 20);
        ta_section.setBackground(new Color(236,219,197));
        ta_section.setFont(font);
        ta_section.setEditable(false);

        Welcome = new JLabel();
        Welcome.setLayout(null);
        Welcome.setBounds(1000,30,200,20);
        Welcome.setText("Welcome (name)");
        Welcome.setForeground(new Color(236,219,197));

        Imagery = new JPanel();
        Imagery.setLayout(new BorderLayout());
        Imagery.setBackground(Color.BLACK);
        Imagery.setOpaque(false);
        Imagery.setBounds(600,40,400,600);

        GradesView = new JTextField();
        GradesView.setBounds(20,20,150,30);
        GradesView.setBackground(new Color(236,219,197));
        GradesView.setEditable(false);

        PrintJB.setBounds(600, 500, 150,40);
        PrintJB.setBackground(new Color(224,198,164));
        PrintJB.setVisible(true);

        sa2.setLayout(new BorderLayout());
        sa2.setBounds(70,-50,1200,200);

        TableModel3 = new DefaultTableModel(new Object[]{"Last Name", "Student ID", "Subject","Final Grade"},0);

        GradesViewTable = new JTable(TableModel3);
        GradesViewTable.setBackground(new Color(236,219,197));
        GradesViewTable.setVisible(true);
        GradesViewTable.setBounds(-50,90,800,Integer.MAX_VALUE);

        GradeView_ScrollBar = new JScrollPane(GradesViewTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        GradeView_ScrollBar.setBounds(170,90,1000,400);
        GradeView_ScrollBar.setBackground(new Color(236,219,197));
        GradeView_ScrollBar.setVisible(true);
        GradeView_ScrollBar.setEnabled(true);

        try {
            Welcome.setText("Welcome " + pass);
            database db = database.getInstance();
            ResultSet as = db.getResult("SELECT * FROM studentlogin, student WHERE '" + username + "'  = student_id");
            while (as.next()) {
                ta_student_id.setText(as.getString("student_id"));
                ta_first_name.setText(as.getString("first_name"));
                ta_mid_name.setText(as.getString("middle_name"));
                ta_last_name.setText(as.getString("last_name"));
                ta_section.setText(as.getString("section"));
                ta_course.setText(as.getString("course"));
                ta_address.setText(as.getString("address"));
            }
            as.close();

            ResultSet reset = db.getResult("SELECT * FROM studentlogin, student, grades WHERE '"+username+"' = id and last_name='"+pass+"'");
            while(reset.next()){
                GradesView.setText(as.getString("student_id"));
                String Student_ID = as.getString("id");
                String Last_Name = as.getString("last_name");
                String Subject = as.getString("subject");
                String Final_Grade = as.getString("finalgrade");
                for(int i = 0; i < TableModel3.getRowCount(); i++){
                    Object value = TableModel3.getValueAt(i, 2);
                    if(value.equals(Subject)){
                        TableModel3.removeRow(i);
                    }
                }
                TableModel3.insertRow(TableModel3.getRowCount(),new Object[]{
                        Last_Name, Student_ID, Subject, Final_Grade
                });
            }
            reset.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        Student_GradeViewJB.setBounds(543,0,200,55);
        Student_GradeViewJB.setBackground(new Color(255, 255, 255));
        Student_GradeViewJB.setOpaque(false);
        Student_GradeViewJB.setVisible(true);

        Student_AboutUsJB.setBounds(757,0,200,55);
        Student_AboutUsJB.setBackground(new Color(255, 255, 255));
        Student_AboutUsJB.setOpaque(false);
        Student_AboutUsJB.setVisible(true);
        //Student_Panel(Student_Profile)

        Student_dasquad.setLayout(new BorderLayout());
        Student_dasquad.setBackground(Color.black);
        Student_dasquad.setBounds(190,410,900,200);

        welcomegif_student.setLayout(new BorderLayout());
        welcomegif_student.setBounds(10,40,300,500);

        quackeroats_student.setLayout(new BorderLayout());
        quackeroats_student.setBounds(960,35,320,500);

        Student_Home.setLayout(null);

        Student_GIF.setLayout(new BorderLayout());
        Student_GIF.setBackground(Color.red);
        Student_GIF.setBounds(239,0,800,500);

        satitle2.setLayout(new BorderLayout());
        satitle2.setBackground(Color.black);
        satitle2.setBounds(135,-120,1000,300);

        Student_Home.add(Student_GIF);
        Student_Home.add(Student_dasquad);
        Student_Home.add(satitle2);
        Student_Home.add(welcomegif_student);
        Student_Home.add(quackeroats_student);
        Imagery.add(sa);

        //Student_Profile(Student_Profile)
        Student_Profile.add(ta_student_id);
        Student_Profile.add(ta_first_name);
        Student_Profile.add(ta_mid_name);
        Student_Profile.add(ta_last_name);
        Student_Profile.add(ta_course);
        Student_Profile.add(ta_section);
        Student_Profile.add(ta_address);
        Student_Profile.add(ta_student_id2);
        Student_Profile.add(ta_first_name2);
        Student_Profile.add(ta_mid_name2);
        Student_Profile.add(ta_last_name2);
        Student_Profile.add(ta_course2);
        Student_Profile.add(ta_section2);
        Student_Profile.add(ta_address2);
        Student_Profile.add(Student_Account);
        Student_Profile.add(Student_Details);
        Student_Profile.add(Student_Personal);
        Student_Profile.add(Welcome);
        Student_Profile.add(Imagery);

        Student_Grades.add(GradesView);
        Student_Grades.add(GradeView_ScrollBar);
        Student_Grades.add(PrintJB);
        Student_Grades.add(sa2);

        //Student_AboutUsJB(Student_Profile)
        Student_PAboutUs = new JPanel();
        Student_PAboutUs.setLayout(null);
        Student_PAboutUs.setBackground(Color.GRAY);
        Student_PAboutUs.setOpaque(false);
        Student_AboutUs.setLayout(new BorderLayout());
        Student_AboutUs.setBounds(10,0,1200,580);
        Student_PAboutUs.add(Student_AboutUs);
        //Student_AboutUsJB(Student_Profile)

        add(LogoutJB);
        add(Student_HomeJB);
        add(Student_GradeViewJB);
        add(Student_ProfileViewJB);
        add(Student_AboutUsJB);

        Student_Panel.add("hm", Student_Home);
        Student_Panel.add("sp",Student_Profile);
        Student_Panel.add("sg",Student_Grades);
        Student_Panel.add("abtus", Student_PAboutUs);

        add(Student_Panel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getContentPane().add(Student_BG);
        setSize(1280,720);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }
}