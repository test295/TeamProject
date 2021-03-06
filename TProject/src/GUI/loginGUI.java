package GUI;

import DBConnect.*;
import com.sun.tools.javac.Main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.security.Key;
import java.sql.*;
import java.util.Calendar;
import java.util.concurrent.Flow;
import java.util.concurrent.TimeUnit;
import javax.swing.*;
import javax.swing.table.*;

public class loginGUI extends JFrame{

    private final JLabel title;
    private final JTextField login;
    private final JPasswordField password;
    private final JButton Login, Exit;
    private boolean enter = false;
    private String username,Pass_word;
    public loginGUI(){
        super("Login");
        setLayout(new FlowLayout());
        setResizable(false);
        setSize(250,200);
        Dimension XD = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(XD.width/2 - this.getSize().width/2, XD.height/2 - this.getSize().height/2);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        title = new JLabel("Login");
        add(title);

        login = new JTextField(20);
        login.setToolTipText("Enter username");
        add(login);

        password = new JPasswordField(20);
        password.setToolTipText("Enter password");
        password.setEchoChar('*');
        add(password);

        Login = new JButton("Login");
        Login.setToolTipText("Click to login");
        add(Login);

        Exit = new JButton("Exit");
        Exit.setToolTipText("Exit BAPERS");
        add(Exit);

        password.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    enter = true;
                }else{
                    enter = false;
                }
            }
        });
        Enter Action = new Enter();
        login.addActionListener(Action);
        password.addActionListener(Action);
        Login.addActionListener(Action);
        Exit.addActionListener(Action);


    }

    private class Enter implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            String Message="Please press ok ";
            String role = "";
            if(e.getSource()==Login || enter){
                username = login.getText();
                Pass_word = password.getText();
                DBConnection login = new DBConnection(username,Pass_word);
                role = login.RoleReturn();
                if(role.equals("Technician")) {
                    JOptionPane.showMessageDialog(null,Message+username);
                    TechInterface test = new TechInterface(username);
                    test.setVisible(true);
                }else if(role.equals("Receptionist")){
                    JOptionPane.showMessageDialog(null,Message+username);
                    FInterface Front = new FInterface();
                    Front.setVisible(true);
                }else if(role.equals("Office Manager")){
                    JOptionPane.showMessageDialog(null, Message+username);
                    OfficeManagerInterface Office = new OfficeManagerInterface(username,role);
                }else if(role.equals("Shift Manager")){
                    JOptionPane.showMessageDialog(null, Message+username);
                    ShiftManagerInterface shift = new ShiftManagerInterface(username,role);
                }else{
                    JOptionPane.showMessageDialog(null,"Fuck off");
                    System.exit(0);
                }
                dispose();
            }if(e.getSource() == Exit){
                System.exit(0);
            }
        }
    }
}
