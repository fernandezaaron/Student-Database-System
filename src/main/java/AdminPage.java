import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;

public class AdminPage extends JFrame {

    String[] Courses = new String[]{"OOP","CSA","DSA","PHYSICS","ENGLISH","RIZAL"};

    JButton Admin_HomeJB, StudentJB, GradesJB, LogoutJB, LoadStudentsJB, Admin_AboutUsJB;
    JButton AddJB, CalculateJB, Grade_LoadStudentsJB, Grade_ViewStudentsJB, SearchJB;
    JLabel Label_FirstName, Label_MiddleName, Label_LastName, Label_Course, Label_ID, Label_Add, Label_Section;
    JTextField StudentName;
    JTextField Text_FirstName, Text_MiddleName, Text_LastName, Text_Course, Text_ID, Text_Address, Text_Section;
    JTextField Text_SearchBar;
    JTextArea CalculatedGrades;

    JComboBox<String> GradeBox1, GradeBox2, GradeBox3, GradeBox4, GradeBox5, NameBox, Student_IDBox, Subject_Box, StudentID_JCB;
    JLabel Grade1, Grade2, Grade3, Grade4, Grade5;
    JLabel Admin_BG, Admin_GIF, Admin_AboutUs, sa, Admin_dasquad, Student_dasquad, satitle, welcomegif,quackeroats;

    JPanel Admin_Panel, Admin_One, Admin_Two, Admin_Three, Admin_Four;

    JTable StudentTable, GradesTable;

    DefaultTableModel TableModel, TableModel2;
    JScrollPane Grade_ScrollBar, Student_ScrollBar;
    CardLayout card = new CardLayout();


    public AdminPage() throws IOException{
        super("Admin Database");

        setIconImage(ImageIO.read(new File("./Interface/SA.png")));

        Icon a = new ImageIcon("./Interface/asdf.png");
        Admin_AboutUs = new JLabel(a);

        BufferedImage c = ImageIO.read(new File("./Interface/SA.png"));
        sa = new JLabel(new ImageIcon(c));

        BufferedImage d = ImageIO.read(new File("./Interface/SA2.png"));
        satitle = new JLabel(new ImageIcon(d));

        BufferedImage f = ImageIO.read(new File("./Interface/dasquad.png"));
        Admin_dasquad = new JLabel(new ImageIcon(f));

        BufferedImage g = ImageIO.read(new File("./Interface/dasquad.png"));
        Student_dasquad = new JLabel(new ImageIcon(g));

        BufferedImage j = ImageIO.read(new File("./Interface/BGtry.png"));
        Admin_BG = new JLabel(new ImageIcon(j));

        Icon icon = new ImageIcon("./Interface/oop.gif");
        Admin_GIF = new JLabel(icon);

        Icon icon2 = new ImageIcon("./Interface/welcome.gif");
        welcomegif = new JLabel(icon2);

        Icon icon3 = new ImageIcon("./Interface/welcomegif2.gif");
        quackeroats = new JLabel(icon3);

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

        Admin_HomeJB = new JButton(new AbstractAction("HOME") {
            @Override
            public void actionPerformed(ActionEvent e) {
                card.show(Admin_Panel, "home");
            }
        });

        StudentJB = new JButton(new AbstractAction("STUDENT") {
            @Override
            public void actionPerformed(ActionEvent e) {
                card.show(Admin_Panel, "stud");
            }
        });

        SearchJB = new JButton(new AbstractAction("SEARCH") {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String Search = Text_SearchBar.getText();
                    database db = database.getInstance();
                    ResultSet rs = db.getResult("SELECT * FROM student WHERE student_id='"+Search+"'");
                    if(rs.next()){
                        Search vfs = new Search();
                        System.out.println(rs.getString("first_name"));
                    }
                    Text_SearchBar.setText("");

                }catch (Exception ex){
                    ex.printStackTrace();
                }

            }
        });

        AddJB = new JButton(new AbstractAction("Add Student") {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (e.getSource() == AddJB) {
                        database db = database.getInstance();
                        String ID = Text_ID.getText();
                        String FName = Text_FirstName.getText();
                        String MName = Text_MiddleName.getText();
                        String LName = Text_LastName.getText();
                        String COR = Text_Course.getText();
                        String Sec = Text_Section.getText();
                        String Adrs = Text_Address.getText();
                        if(ID.isBlank() && FName.isBlank() && MName.isBlank() && LName.isBlank() && COR.isBlank() && Sec.isBlank() && Adrs.isBlank()){
                            JOptionPane.showMessageDialog(null, "Empty input!!", "Error!!", JOptionPane.WARNING_MESSAGE);
                        }
                        else{
                            ResultSet resSet = db.getResult("SELECT * FROM student WHERE EXISTS(SELECT * FROM student WHERE student_id = '" + ID + "')");
                            int count = 0;
                            while (resSet.next()) {
                                count++;
                            }
                            if (count > 0) {
                                JOptionPane.showMessageDialog(null, "Username already exists!!", "Error!!", JOptionPane.WARNING_MESSAGE);
                            } else {
                                db.update_db(ID, FName, MName, LName, COR, Sec, Adrs);
                                db.setResult("INSERT INTO studentlogin VALUES('"+ID+"','"+LName+"'"+")");
                                System.out.println("Successfully created an account for " + ID);
                                JOptionPane.showMessageDialog(null, "Successfully Added!", "Congratulations!!", JOptionPane.INFORMATION_MESSAGE);
                                Student_IDBox.insertItemAt(ID,0);
                            }
                        }
                        Text_ID.setText("");
                        Text_FirstName.setText("");
                        Text_MiddleName.setText("");
                        Text_LastName.setText("");
                        Text_Course.setText("");
                        Text_Section.setText("");
                        Text_Address.setText("");
                    }
                } catch(Exception ex){
                    ex.printStackTrace();
                }

            }
        });

        SearchJB = new JButton(new AbstractAction("SEARCH") {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String Search = Text_SearchBar.getText();
                    database db = database.getInstance();
                    ResultSet rs = db.getResult("SELECT * FROM student WHERE student_id='"+Search+"'");
                    if(rs.next()){
                        Search vfs = new Search();
                        vfs.student_id.setText(rs.getString("student_id"));
                        vfs.firstname.setText(rs.getString("first_name"));
                        vfs.middlename.setText(rs.getString("middle_name"));
                        vfs.lastname.setText(rs.getString("last_name"));
                        vfs.course.setText(rs.getString("course"));
                        vfs.section.setText(rs.getString("section"));
                        vfs.address.setText(rs.getString("address"));
                    }
                    Text_SearchBar.setText("");

                }catch (Exception ex){
                    ex.printStackTrace();
                }

            }
        });

        //i need debugging here
        LoadStudentsJB = new JButton(new AbstractAction("Load Students") {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    database db = database.getInstance();
                    ResultSet rs = db.getResult("SELECT * FROM student");
                    while (rs.next()) {
                        String ID = rs.getString("student_id");
                        String FName = rs.getString("first_name");
                        String MName = rs.getString("middle_name");
                        String LName = rs.getString("last_name");
                        String COR = rs.getString("course");
                        String Sec = rs.getString("section");
                        String Adrs = rs.getString("address");
                        String[] data = new String[]{ID, FName, MName, LName, COR, Sec, Adrs};

                        for(int i = 0; i < TableModel.getRowCount(); i++){
                            Object value = TableModel.getValueAt(i, 0);
                            if(value.equals(ID)){
                                TableModel.removeRow(i);
                            }
                        }
                        TableModel.addRow(data);
                    }
                }catch(Exception ex){
                    ex.printStackTrace();
                }

            }
        });

        GradesJB = new JButton(new AbstractAction("GRADES") {
            @Override
            public void actionPerformed(ActionEvent e) {
                card.show(Admin_Panel, "grades");
            }
        });

        Grade_LoadStudentsJB = new JButton(new AbstractAction("Load Students") {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    database db = database.getInstance();
                    ResultSet rs = db.getResult("SELECT * FROM student");
                    while (rs.next()) {
                        String StudentID_JCB = rs.getString("student_id");
                        for(int i = 0; i < Student_IDBox.getItemCount(); i++){
                            String contains = Student_IDBox.getItemAt(i);
                            if(contains.equals(StudentID_JCB)){
                                Student_IDBox.removeItem(contains);
                            }
                        }
                        Student_IDBox.addItem(StudentID_JCB);
                        System.out.println(Student_IDBox.getItemCount());
                        Student_IDBox.addActionListener(new AbstractAction() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                try{
                                    database db = database.getInstance();
                                    ResultSet rs = db.getResult("SELECT student_id, last_name FROM student where student_id = '" + Student_IDBox.getSelectedItem() + "'");
                                    while (rs.next()) {
                                        String name = rs.getString("last_name");
                                        StudentName.setEditable(true);
                                        StudentName.setEnabled(true);
                                        StudentName.setText(name);
//                                        StudentName.setEnabled(false);
                                        StudentName.setEditable(false);
                                    }
                                }catch (Exception ex){
                                    ex.printStackTrace();
                                }
                            }
                        });
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }

            }
        });

        CalculateJB = new JButton(new AbstractAction("Calculate Grade") {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == CalculateJB) {
                    try{
                        int count = 0;
                        database db = database.getInstance();
                        ResultSet rs = db.getResult("SELECT * FROM student, grades WHERE student_id = '" + Student_IDBox.getSelectedItem() + "'");
                        while(rs.next()){
                            String StudentID_JCB = rs.getString("student_id");
                            if(StudentID_JCB.equals(Student_IDBox.getSelectedItem())){
                                ResultSet resultSet = db.getResult("SELECT * FROM grades WHERE EXISTS(SELECT * FROM grades WHERE subject = '" + Subject_Box.getSelectedItem() + "' and id = '" + Student_IDBox.getSelectedItem() + "')");
                                while (resultSet.next()){
                                    count++;
                                }
                            }
                        }
                        if(count > 0){
                            JOptionPane.showMessageDialog(null, "Already Existing!!", "Error!!", JOptionPane.WARNING_MESSAGE);
                        }
                        else{
                            if(JOptionPane.showConfirmDialog(null, "Are you sure?", "Warning!!", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                                double Dgrade1 = Double.parseDouble(String.valueOf(GradeBox1.getSelectedItem()));
                                double Dgrade2 = Double.parseDouble(String.valueOf(GradeBox2.getSelectedItem()));
                                double Dgrade3 = Double.parseDouble(String.valueOf(GradeBox3.getSelectedItem()));
                                double Dgrade4 = Double.parseDouble(String.valueOf(GradeBox4.getSelectedItem()));
                                double Dgrade5 = Double.parseDouble(String.valueOf(GradeBox5.getSelectedItem()));
                                double Summative_Grade = (((Dgrade1+Dgrade2+Dgrade3+Dgrade4)/100)*.75)*100;
                                double Finals_Grade = ((Dgrade5/30)*.25)*100;
                                double FinalGrade = Summative_Grade + Finals_Grade;
                                int CeilFGrade = (int) FinalGrade;
                                String ID = String.valueOf(Student_IDBox.getSelectedItem());
                                String Course = String.valueOf(Subject_Box.getSelectedItem());
                                CalculatedGrades.setText
                                        (StudentName.getText()+"\n"+Student_IDBox.getSelectedItem()+"\n"+String.valueOf(Dgrade1)
                                                +"\n" + String.valueOf(Dgrade2) + "\n" + String.valueOf(Dgrade3) + "\n" + String.valueOf(Dgrade4)
                                                +"\n" + String.valueOf(Dgrade5) + "\n" + FinalGrade );
                                db.grade_db(ID,Course,Dgrade1,Dgrade2,Dgrade3,Dgrade4,Dgrade5,CeilFGrade);
                            }
                        }
                    }catch (Exception ex){
                        ex.printStackTrace();
                    }
                }
            }
        });

        Grade_ViewStudentsJB = new JButton(new AbstractAction("Load Grades") {
            @Override
            public void actionPerformed(ActionEvent e){
                if(e.getSource() == Grade_ViewStudentsJB){
                    try{
                        database db = database.getInstance();
                        ResultSet resultSet = db.getResult("SELECT * FROM student, grades WHERE student_id = '" + Student_IDBox.getSelectedItem() + "' and id = '" + Student_IDBox.getSelectedItem() + "'");
                        while(resultSet.next()) {
                            String ID = resultSet.getString("student_id");
                            String FName = resultSet.getString("first_name");
                            String MName = resultSet.getString("middle_name");
                            String LName = resultSet.getString("last_name");
                            String COR = resultSet.getString("course");
                            String Sec = resultSet.getString("section");
                            String FinalGrade = resultSet.getString("finalgrade");
                            String Sub = resultSet.getString("subject");
                            String[] data = new String[]{ID, FName, MName, LName, COR, Sec, FinalGrade, Sub};
                            for(int i = 0; i < TableModel2.getRowCount(); i++){
                                Object value = TableModel2.getValueAt(i, 7);
                                if(value.equals(Sub)){
                                    TableModel2.removeRow(i);
                                }
                            }
                            TableModel2.addRow(data);
                        }
                    }catch(Exception ex){
                        ex.printStackTrace();
                    }
                }
            }
        });

        Admin_AboutUsJB = new JButton(new AbstractAction("ABOUT US") {
            @Override
            public void actionPerformed(ActionEvent e) {
                card.show(Admin_Panel,"abt");
            }
        });

        GradeBox1 = new JComboBox<>();
        GradeBox2 = new JComboBox<>();
        GradeBox3 = new JComboBox<>();
        GradeBox4 = new JComboBox<>();
        GradeBox5 = new JComboBox<>();

        NameBox = new JComboBox<>();
        Student_IDBox = new JComboBox<>();
        StudentID_JCB = new JComboBox<>();

        for (int i = 0; i <= 25; i++) {
            String converter = String.valueOf(i);
            GradeBox1.addItem(converter);
            GradeBox2.addItem(converter);
            GradeBox3.addItem(converter);
            GradeBox4.addItem(converter);

        }

        for (int i = 0; i <= 30; i++) {
            String converter = String.valueOf(i);
            GradeBox5.addItem(converter);
        }

        //Admin_Panel(admin)
        Admin_Panel = new JPanel();
        Admin_Panel.setBackground(Color.black);
        Admin_Panel.setBounds(0,95, 1280, 720);
        Admin_Panel.setOpaque(false);
        Admin_Panel.setLayout(card);
        Admin_Panel.setVisible(true);

        //homebuttons
        Admin_HomeJB.setBounds(112,0, 200, 55);
        Admin_HomeJB.setBackground(new Color(255, 255, 255));
        Admin_HomeJB.setOpaque(false);
        Admin_HomeJB.setFocusable(false);
        Admin_HomeJB.setVisible(true);

        StudentJB.setBounds(330,0, 200, 55);
        StudentJB.setBackground(new Color(255, 255, 255));
        StudentJB.setOpaque(false);
        StudentJB.setFocusable(false);
        StudentJB.setVisible(true);

        GradesJB.setBounds(543,0, 200, 55);
        GradesJB.setBackground(new Color(255, 255, 255));
        GradesJB.setOpaque(false);
        GradesJB.setFocusable(false);
        GradesJB.setVisible(true);

        LogoutJB.setBounds(970,0,200,55);
        LogoutJB.setBackground(new Color(246,145,91));
        LogoutJB.setOpaque(false);
        LogoutJB.setFocusable(false);

        Admin_AboutUsJB.setBounds(757,0,200,55);
        Admin_AboutUsJB.setBackground(new Color(246,145,91));
        Admin_AboutUsJB.setOpaque(false);
        Admin_AboutUsJB.setFocusable(false);
        Admin_AboutUsJB.setVisible(true);

        TableModel = new DefaultTableModel(new Object[]{"ID","First Name","Middle Name","Last Name", "Course", "Section","Address"},0);

        StudentTable = new JTable(TableModel);
        StudentTable.setBackground(new Color(236,219,197));
        StudentTable.setBounds(500,20,700,Integer.MAX_VALUE);

        Student_ScrollBar = new JScrollPane(StudentTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        Student_ScrollBar.setBounds(500,20,700,550);
        Student_ScrollBar.setVisible(true);


        Admin_One = new JPanel();
        Admin_One.setBackground(Color.black);
        Admin_One.setOpaque(false);
        Admin_One.setLayout(null);

        //student
        Admin_Two = new JPanel();
        Admin_Two.setLayout(null);
        Admin_Two.setBackground(Color.GRAY);
        Admin_Two.setOpaque(false);

        Text_ID = new JTextField();
        Text_ID.setBounds(240, 20,200,30);
        Text_ID.setBackground(new Color(236,219,197));

        Text_FirstName = new JTextField();
        Text_FirstName.setBounds(240, 60,200,30);
        Text_FirstName.setBackground(new Color(236,219,197));

        Text_MiddleName = new JTextField();
        Text_MiddleName.setBounds(240, 100,200,30);
        Text_MiddleName.setBackground(new Color(236,219,197));

        Text_LastName = new JTextField();
        Text_LastName.setBounds(240, 140,200,30);
        Text_LastName.setBackground(new Color(236,219,197));

        Text_Course = new JTextField();
        Text_Course.setBounds(240, 180,200,30);
        Text_Course.setBackground(new Color(236,219,197));

        Text_Address = new JTextField();
        Text_Address.setBounds(240, 220,200,30);
        Text_Address.setBackground(new Color(236,219,197));

        Text_Section = new JTextField();
        Text_Section.setBounds(240, 260,200,30);
        Text_Section.setBackground(new Color(236,219,197));

        Text_SearchBar = new JTextField();
        Text_SearchBar.setBounds(900,-5,190,20);
        Text_SearchBar.setBackground(new Color(236,219,197));

        SearchJB.setBounds(1100,-5,100,20);
        SearchJB.setBackground(new Color(224,198,164));

        AddJB.setBounds(240,500,200,30);
        AddJB.setBackground(new Color(224,198,164));

        LoadStudentsJB.setBounds(30,500,200,30);
        LoadStudentsJB.setBackground(new Color(224,198,164));

        Label_ID = new JLabel("Student ID: ");
        Label_ID.setBounds(40, 20,200,30);
        Label_ID.setForeground(new Color(224,198,164));

        Label_FirstName = new JLabel("First Name: ");
        Label_FirstName.setBounds(40, 60,200,30);
        Label_FirstName.setForeground(new Color(224,198,164));

        Label_MiddleName = new JLabel("Middle Name: ");
        Label_MiddleName.setBounds(40, 100,200,30);
        Label_MiddleName.setForeground(new Color(224,198,164));

        Label_LastName = new JLabel("Last Name: ");
        Label_LastName.setBounds(40, 140,200,30);
        Label_LastName.setForeground(new Color(224,198,164));

        Label_Course = new JLabel("Course: ");
        Label_Course.setBounds(40, 180,200,30);
        Label_Course.setForeground(new Color(224,198,164));

        Label_Add = new JLabel("Address: ");
        Label_Add.setBounds(40, 220,200,30);
        Label_Add.setForeground(new Color(224,198,164));

        Label_Section = new JLabel("Section: ");
        Label_Section.setBounds(40, 260,200,30);
        Label_Section.setForeground(new Color(224,198,164));
        //student

        //grades
        Admin_Three = new JPanel();
        Admin_Three.setLayout(null);
        Admin_Three.setBackground(Color.ORANGE);
        Admin_Three.setOpaque(false);

        TableModel2 = new DefaultTableModel(new Object[]{"ID", "First Name", "Middle Name", "Last Name", "Degree", "Section", "Final Grade","Subject"}, 0);

        GradesTable = new JTable(TableModel2);
        GradesTable.setVisible(true);
        GradesTable.setBackground(new Color(236,219,197));
        GradesTable.setBounds(500,20,700,Integer.MAX_VALUE);

        Grade_ScrollBar = new JScrollPane(GradesTable,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        Grade_ScrollBar.setBounds(550,20,700,550);
        Grade_ScrollBar.setEnabled(true);
        Grade_ScrollBar.setVisible(true);

        Student_IDBox.setBounds(40,40,160,30);
        Student_IDBox.setBackground(new Color(236,219,197));

        StudentName = new JTextField();
        StudentName.setBounds(40,80,160,30);
        StudentName.setEditable(false);
        StudentName.setBackground(new Color(236,219,197));

        Subject_Box = new JComboBox<>(Courses);
        Subject_Box.setBounds(220,40,160,30);
        Subject_Box.setBackground(new Color(236,219,197));

        Grade1 = new JLabel("Summative 1 (25): ");
        Grade1.setBounds(40,140,120,30);
        Grade1.setForeground(Color.white);
        GradeBox1.setBounds(160,140,50,30);
        GradeBox1.setBackground(new Color(236,219,197));

        Grade2 = new JLabel("Summative 2 (25): ");
        Grade2.setBounds(40,180,120,30);
        Grade2.setForeground(Color.white);
        GradeBox2.setBounds(160,180,50,30);
        GradeBox2.setBackground(new Color(236,219,197));

        Grade3 = new JLabel("Summative 3 (25): ");
        Grade3.setBounds(40,220,120,30);
        Grade3.setForeground(Color.white);
        GradeBox3.setBounds(160,220,50,30);
        GradeBox3.setBackground(new Color(236,219,197));

        Grade4 = new JLabel("Summative 4 (25): ");
        Grade4.setBounds(40,260,120,30);
        Grade4.setForeground(Color.white);
        GradeBox4.setBounds(160,260,50,30);
        GradeBox4.setBackground(new Color(236,219,197));

        Grade5 = new JLabel("Finals Grade (30): ");
        Grade5.setBounds(40,300,120,30);
        Grade5.setForeground(Color.white);
        GradeBox5.setBounds(160,300,50,30);
        GradeBox5.setBackground(new Color(236,219,197));

        CalculatedGrades = new JTextArea();
        CalculatedGrades.setBounds(220,90,160,300);
        CalculatedGrades.setBackground(new Color(236,219,197));


        CalculateJB.setBounds(40,380,150,30);
        CalculateJB.setBackground(new Color(224,198,164));

        Grade_LoadStudentsJB.setBounds(40,340,150,30);
        Grade_LoadStudentsJB.setBackground(new Color(224,198,164));

        Grade_ViewStudentsJB.setBounds(40,420,150,30);
        Grade_ViewStudentsJB.setBackground(new Color(224,198,164));
        //grades

        Admin_GIF.setLayout(new BorderLayout());
        Admin_GIF.setBackground(Color.red);
        Admin_GIF.setBounds(239,0,800,500);

        satitle.setLayout(new BorderLayout());
        satitle.setBackground(Color.black);
        satitle.setBounds(135,-120,1000,300);

        Admin_dasquad.setLayout(new BorderLayout());
        Admin_dasquad.setBackground(Color.black);
        Admin_dasquad.setBounds(190,410,900,200);

        welcomegif.setLayout(new BorderLayout());
        welcomegif.setBounds(10,40,300,500);

        quackeroats.setLayout(new BorderLayout());
        quackeroats.setBounds(960,35,320,500);

        Admin_One.add(Admin_GIF);
        Admin_One.add(Admin_dasquad);
        Admin_One.add(satitle);
        Admin_One.add(welcomegif);
        Admin_One.add(quackeroats);

        Admin_Two.add(Text_ID);
        Admin_Two.add(Text_FirstName);
        Admin_Two.add(Text_MiddleName);
        Admin_Two.add(Text_LastName);
        Admin_Two.add(Text_Course);
        Admin_Two.add(Text_Address);
        Admin_Two.add(Text_Section);
        Admin_Two.add(Label_ID);
        Admin_Two.add(Label_FirstName);
        Admin_Two.add(Label_MiddleName);
        Admin_Two.add(Label_LastName);
        Admin_Two.add(Label_Course);
        Admin_Two.add(Label_Section);
        Admin_Two.add(Label_Add);
        Admin_Two.add(LoadStudentsJB);
        Admin_Two.add(Student_ScrollBar);
        Admin_Two.add(AddJB);
        Admin_Two.add(Text_SearchBar);
        Admin_Two.add(SearchJB);

        Admin_Three.add(GradeBox1);
        Admin_Three.add(GradeBox2);
        Admin_Three.add(GradeBox3);
        Admin_Three.add(GradeBox4);
        Admin_Three.add(GradeBox5);

//        Admin_Three.add(namebox);
        Admin_Three.add(CalculatedGrades);
        Admin_Three.add(StudentName);
        Admin_Three.add(Student_IDBox);
        Admin_Three.add(CalculateJB);
        Admin_Three.add(Grade1);
        Admin_Three.add(Grade2);
        Admin_Three.add(Grade3);
        Admin_Three.add(Grade4);
        Admin_Three.add(Grade5);

//        Admin_Three.add(GradesTable);
        Admin_Three.add(Grade_ScrollBar);
        Admin_Three.add(Grade_LoadStudentsJB);
        Admin_Three.add(Grade_ViewStudentsJB);
        Admin_Three.add(Subject_Box);

        //Admin_AboutUsJB(admin)
        Admin_Four = new JPanel();
        Admin_Four.setLayout(null);
        Admin_Four.setBackground(Color.GRAY);
        Admin_Four.setOpaque(false);
        Admin_AboutUs.setLayout(new BorderLayout());
        Admin_AboutUs.setBounds(10,0,1200,580);
        Admin_Four.add(Admin_AboutUs);
        //Admin_AboutUsJB(admin)

        add(Admin_HomeJB);
        add(GradesJB);
        add(StudentJB);
        add(LogoutJB);
        add(Admin_AboutUsJB);

        Admin_Panel.add("home", Admin_One);
        Admin_Panel.add("stud", Admin_Two);
        Admin_Panel.add("grades", Admin_Three);
        Admin_Panel.add("abt",Admin_Four);
        add(Admin_Panel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getContentPane().add(Admin_BG);
        setSize(1280,720);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }
}