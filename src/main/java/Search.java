import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;

public class Search extends JFrame {

    JTextField student_id,firstname, middlename, lastname, section, course, address;
    JButton firstname_edit, middlename_edit, lastname_edit, section_edit, course_edit, address_edit;
    JButton firstname_confirm, middlename_confirm, lastname_confirm, section_confirm, course_confirm, address_confirm;
    JLabel student_id_label, firstname_label, middlename_label, lastname_label, section_label, course_label,address_label;
    JPanel panel;
    JLabel student_search_bg;

    public Search() throws IOException {
        super("Search Student");

        setIconImage(ImageIO.read(new File("./Interface/SA.png")));

        BufferedImage ss = ImageIO.read(new File("./Interface/Studentsearchbg.png"));
        student_search_bg = new JLabel(new ImageIcon(ss));


        firstname_edit = new JButton(new AbstractAction("edit") {
            @Override
            public void actionPerformed(ActionEvent e) {
                firstname.setEditable(true);
                firstname.setText("");
                firstname_confirm.setVisible(true);
                firstname_edit.setVisible(false);

            }
        });
        firstname_confirm = new JButton(new AbstractAction("confirm") {
            @Override
            public void actionPerformed(ActionEvent e) {
                String stud_id = student_id.getText();
                String first_name = firstname.getText();

                try{
                    database db = database.getInstance();
                    db.setResult("UPDATE student " +
                            "SET first_name='" +first_name + "' WHERE student_id='"+stud_id+"'"
                    );

                }catch (Exception ex){
                    ex.printStackTrace();
                }
                firstname_confirm.setVisible(false);
                firstname_edit.setVisible(true);
                firstname.setEditable(false);
                System.out.println("Successfuly Edited First Name!");
            }
        });

        middlename_edit = new JButton(new AbstractAction("edit") {
            @Override
            public void actionPerformed(ActionEvent e) {
                middlename.setEditable(true);
                middlename.setText("");
                middlename_confirm.setVisible(true);
                middlename_edit.setVisible(false);

            }
        });
        middlename_confirm = new JButton(new AbstractAction("confirm") {
            @Override
            public void actionPerformed(ActionEvent e) {
                String stud_id = student_id.getText();
                String middle_name = middlename.getText();

                try{
                    database db = database.getInstance();
                    db.setResult("UPDATE student " +
                            "SET middle_name='" +middle_name + "' WHERE student_id='"+stud_id+"'"
                    );

                }catch (Exception ex){
                    ex.printStackTrace();
                }
                middlename_confirm.setVisible(false);
                middlename_edit.setVisible(true);
                middlename.setEditable(false);
                System.out.println("Successfuly Edited Middle Name!");

            }
        });

        lastname_edit = new JButton(new AbstractAction("edit") {
            @Override
            public void actionPerformed(ActionEvent e) {
                lastname.setEditable(true);
                lastname.setText("");
                lastname_confirm.setVisible(true);
                lastname_edit.setVisible(false);

            }
        });
        lastname_confirm = new JButton(new AbstractAction("confirm") {
            @Override
            public void actionPerformed(ActionEvent e) {
                String stud_id = student_id.getText();
                String last_name = lastname.getText();

                try{
                    database db = database.getInstance();
                    db.setResult("UPDATE student " +
                            "SET last_name='" +last_name + "' WHERE student_id='"+stud_id+"'"
                    );

                }catch (Exception ex){
                    ex.printStackTrace();
                }
                lastname_confirm.setVisible(false);
                lastname_edit.setVisible(true);
                lastname.setEditable(false);
                System.out.println("Successfuly Edited Last Name!");


            }
        });

        section_edit = new JButton(new AbstractAction("edit") {
            @Override
            public void actionPerformed(ActionEvent e) {
                section.setEditable(true);
                section.setText("");
                section_confirm.setVisible(true);
                section_edit.setVisible(false);


            }
        });
        section_confirm = new JButton(new AbstractAction("confirm") {
            @Override
            public void actionPerformed(ActionEvent e) {
                String stud_id = student_id.getText();
                String Section = section.getText();

                try{
                    database db = database.getInstance();
                    db.setResult("UPDATE student " +
                            "SET section='" +Section + "' WHERE student_id='"+stud_id+"'"
                    );

                }catch (Exception ex){
                    ex.printStackTrace();
                }
                section_confirm.setVisible(false);
                section_edit.setVisible(true);
                section.setEditable(false);
                System.out.println("Successfuly Edited Section!");


            }
        });

        course_edit = new JButton(new AbstractAction("edit") {
            @Override
            public void actionPerformed(ActionEvent e) {
                course.setEditable(true);
                course.setText("");
                course_confirm.setVisible(true);
                course_edit.setVisible(false);

            }
        });
        course_confirm = new JButton(new AbstractAction("confirm") {
            @Override
            public void actionPerformed(ActionEvent e) {
                String stud_id = student_id.getText();
                String Course = course.getText();

                try{
                    database db = database.getInstance();
                    db.setResult("UPDATE student " +
                            "SET course='" +Course + "' WHERE student_id='"+stud_id+"'"
                    );

                }catch (Exception ex){
                    ex.printStackTrace();
                }
                course_confirm.setVisible(false);
                course_edit.setVisible(true);
                course.setEditable(false);
                System.out.println("Successfuly Edited Course!");

            }
        });
        address_edit = new JButton(new AbstractAction("edit") {
            @Override
            public void actionPerformed(ActionEvent e) {
                address.setEditable(true);
                address.setText("");
                address_confirm.setVisible(true);
                address_edit.setVisible(false);

            }
        });
        address_confirm = new JButton(new AbstractAction("confirm") {
            @Override
            public void actionPerformed(ActionEvent e) {
                String stud_id = student_id.getText();
                String add = address.getText();

                try{
                    database db = database.getInstance();
                    db.setResult("UPDATE student " +
                            "SET address='" +add + "' WHERE student_id='"+stud_id+"'"
                    );

                }catch (Exception ex){
                    ex.printStackTrace();
                }
                address_confirm.setVisible(false);
                address_edit.setVisible(true);
                address.setEditable(false);
                System.out.println("Successfuly Edited Address!");


            }
        });



        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0,0,400,400);
        panel.setBackground(Color.ORANGE);
        panel.setOpaque(false);

        //180
        firstname_edit.setBounds(280,100,100,30);
        firstname_edit.setBackground(new Color(224,198,164));

        middlename_edit.setBounds(280,140,100,30);
        middlename_edit.setBackground(new Color(224,198,164));

        lastname_edit.setBounds(280,180,100,30);
        lastname_edit.setBackground(new Color(224,198,164));

        section_edit.setBounds(280,220,100,30);
        section_edit.setBackground(new Color(224,198,164));

        course_edit.setBounds(280,260,100,30);
        course_edit.setBackground(new Color(224,198,164));

        address_edit.setBounds(280,300,100,30);
        address_edit.setBackground(new Color(224,198,164));

        firstname_confirm.setBounds(280,100,100,30);
        firstname_confirm.setBackground(new Color(224,198,164));
        firstname_confirm.setVisible(false);

        middlename_confirm.setBounds(280,140,100,30);
        middlename_confirm.setBackground(new Color(224,198,164));
        middlename_confirm.setVisible(false);

        lastname_confirm.setBounds(280,180,100,30);
        lastname_confirm.setBackground(new Color(224,198,164));
        lastname_confirm.setVisible(false);

        section_confirm.setBounds(280,220,100,30);
        section_confirm.setBackground(new Color(224,198,164));
        section_confirm.setVisible(false);

        course_confirm.setBounds(280,260,100,30);
        course_confirm.setBackground(new Color(224,198,164));
        course_confirm.setVisible(false);

        address_confirm.setBounds(280,300,100,30);
        address_confirm.setBackground(new Color(224,198,164));
        address_confirm.setVisible(false);


        student_id = new JTextField();
        student_id.setBounds(120,60,150, 30);
        student_id.setBackground(new Color(236,219,197));
        student_id.setOpaque(true);
        student_id.setVisible(true);
        student_id.setEditable(false);

        firstname = new JTextField();
        firstname.setBounds(120,100,150,30);
        firstname.setBackground(new Color(236,219,197));
        firstname.setEditable(false);

        middlename = new JTextField();
        middlename.setBounds(120,140,150,30);
        middlename.setBackground(new Color(236,219,197));
        middlename.setEditable(false);

        lastname = new JTextField();
        lastname.setBounds(120,180,150,30);
        lastname.setBackground(new Color(236,219,197));
        lastname.setEditable(false);

        section = new JTextField();
        section.setBounds(120,220,150,30);
        section.setBackground(new Color(236,219,197));
        section.setEditable(false);

        course = new JTextField();
        course.setBounds(120,260,150,30);
        course.setBackground(new Color(236,219,197));
        course.setEditable(false);

        address = new JTextField();
        address.setBounds(120,300,150,30);
        address.setBackground(new Color(236,219,197));
        address.setEditable(false);

        student_id_label = new JLabel("Student ID: ");
        student_id_label.setBounds(10,60,150,30);
        student_id_label.setForeground(new Color(224,198,164));

        firstname_label = new JLabel("First Name: ");
        firstname_label.setBounds(10,100,150,30);
        firstname_label.setForeground(new Color(224,198,164));

        middlename_label = new JLabel("Middle Name: ");
        middlename_label.setBounds(10,140,150,30);
        middlename_label.setForeground(new Color(224,198,164));

        lastname_label = new JLabel("Last Name: ");
        lastname_label.setBounds(10,180,150,30);
        lastname_label.setForeground(new Color(224,198,164));

        section_label = new JLabel("Section: ");
        section_label.setBounds(10,220,150,30);
        section_label.setForeground(new Color(224,198,164));

        course_label = new JLabel("Course: ");
        course_label.setBounds(10,260,150,30);
        course_label.setForeground(new Color(224,198,164));

        address_label = new JLabel("Address: ");
        address_label.setBounds(10,300,150,30);
        address_label.setForeground(new Color(224,198,164));


        panel.add(student_id);
        panel.add(firstname);
        panel.add(middlename);
        panel.add(lastname);
        panel.add(section);
        panel.add(course);
        panel.add(address);
        panel.add(firstname_edit);
        panel.add(middlename_edit);
        panel.add(lastname_edit);
        panel.add(section_edit);
        panel.add(course_edit);
        panel.add(address_edit);
        panel.add(firstname_confirm);
        panel.add(middlename_confirm);
        panel.add(lastname_confirm);
        panel.add(section_confirm);
        panel.add(course_confirm);
        panel.add(address_confirm);
        panel.add(student_id_label);
        panel.add(firstname_label);
        panel.add(middlename_label);
        panel.add(lastname_label);
        panel.add(section_label);
        panel.add(course_label);
        panel.add(address_label);
        add(panel);


        getContentPane().add(student_search_bg);
        student_search_bg.setVisible(true);
        student_search_bg.setBounds(0,60,400,500);
        setSize(400,400);
        setVisible(true);

    }

}
