import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Login extends JFrame {
    JTextField textUsername;
    JLabel textOnlyUser, textOnlyPass, BG;
    JPasswordField textPassword;
    JButton studentLogButton, adminLogButton;
    String textUser, textPass;


    public Login() throws IOException {
        super("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setIconImage(ImageIO.read(new File("./Interface/SA.png")));

        BufferedImage h = ImageIO.read(new File("./Interface/BG2.jpg"));
        BG = new JLabel(new ImageIcon(h));

        textUsername = new JTextField();
        textUsername.setBounds(250,110,200,30);
        textUsername.setBackground(new Color(236,219,197));
        textOnlyUser = new JLabel("Username: ");
        textOnlyUser.setBounds(170,110,200,30);
        textOnlyUser.setForeground(new Color(224,198,164));

        textPassword = new JPasswordField(10);
        textPassword.setBounds(250,160,200,30);
        textPassword.setBackground(new Color(236,219,197));
        textOnlyPass = new JLabel("Password: ");
        textOnlyPass.setBounds(170,160,200,30);
        textOnlyPass.setForeground(new Color(224,198,164));

        studentLogButton = new JButton(new AbstractAction("Student") {
            @Override
            public void actionPerformed(ActionEvent ae) {
                textUser = textUsername.getText();
                textPass = textPassword.getText();
                if(ae.getSource() == studentLogButton) {
                    setStudentButton();
                }
            }
        });

        adminLogButton = new JButton(new AbstractAction("Admin") {
            @Override
            public void actionPerformed(ActionEvent ae) {
                textUser = textUsername.getText();
                textPass = textPassword.getText();
                if(ae.getSource() == adminLogButton) {
                    setAdminButton();
                }
            }
        });

        adminLogButton.setBounds(350,220,100,30);
        adminLogButton.setBackground(new Color(224,198,164));

        studentLogButton.setBounds(240,220,100,30);
        studentLogButton.setBackground(new Color(224,198,164));

        add(studentLogButton);
        add(adminLogButton);
        add(textUsername);
        add(textPassword);
        add(textOnlyUser);
        add(textOnlyPass);
        getContentPane().add(BG);
        setSize(700,410);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    public void setStudentButton() {
        try {
            database db = database.getInstance();
            ResultSet resSet = db.getResult("SELECT * FROM studentlogin WHERE stud_id ='" + textUser + "' and lastname ='" + textPass + "'");
            if (resSet.next()) {
                JOptionPane.showMessageDialog(rootPane, "Successfully Logged in as Student","Success",JOptionPane.INFORMATION_MESSAGE);
                db.acc_update(textUser, textPass);
                dispose();
                new StudentPage();
            } else {
                textUsername.setText("");
                textPassword.setText("");
                JOptionPane.showMessageDialog(rootPane, "Wrong Input!", "Failed", JOptionPane.WARNING_MESSAGE);
            }
        }
        catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public void setAdminButton() {
        try {
            database db = database.getInstance();
            ResultSet resSet = db.getResult("SELECT * FROM admin WHERE adminusername ='" + textUser + "' and adminpassword ='" + textPass + "'");
            if (resSet.next()) {
                JOptionPane.showMessageDialog(rootPane, "Successfully Logged in as Admin", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();
                AdminPage admPage = new AdminPage();
            }

            else if(!resSet.next()){
                JOptionPane.showMessageDialog(rootPane, "Wrong Input!", "Failed", JOptionPane.WARNING_MESSAGE);
            }

            else {
                textUsername.setText("");
                textPassword.setText("");
                JOptionPane.showMessageDialog(rootPane, "Wrong input!", "Failed", JOptionPane.WARNING_MESSAGE);
            }
        }
        catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
}