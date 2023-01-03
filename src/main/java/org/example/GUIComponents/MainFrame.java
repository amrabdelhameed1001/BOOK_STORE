package org.example.GUIComponents;

import org.example.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private JPanel rootPanel;
    private JPanel startScreen;
    private JButton toSignup;
    private JButton toLogin;
    private JPanel signupScreen;
    private JTextField fnameF;
    private JTextField lnameF;
    private JTextField usernameF;
    private JTextField emailF;
    private JTextField addressF;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JTextField phoneF;
    private JPanel loginScreen;
    private JButton signupBackButton;
    private JButton signupButton;
    private JTextField loginUserF;
    private JTextField loginPassF;
    private JButton loginBackButton;
    private JButton loginButton;
    private JComboBox loginBox;
    private JComboBox signupUserTypeBox;
    private JLabel signupErrorLabel;
    private JLabel loginErrorLabel;
    private JPanel customerScreen;
    private JPanel managerScreen;
    private JTabbedPane tabbedPane1;
    private JButton promoteUserButton;
    private JButton addBookButton;
    private JButton orderBookButton;
    private JButton updateBookButton;
    private JButton salesReportButton;
    private JPanel mPersonalP;
    private JPanel mManageP;
    private JPanel mSearchP;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JComboBox comboBox1;
    private JButton searchButton;
    private JButton button1;
    private JLabel fnameLabel;
    private JLabel lnameLabel;
    private JLabel emailLabel;
    private JLabel usernameLabel;

    public MainFrame(String title) {
        super(title);


        toLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((CardLayout) rootPanel.getLayout()).show(rootPanel, "LoginScreen");
            }
        });
        toSignup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((CardLayout) rootPanel.getLayout()).show(rootPanel, "SignupScreen");
            }
        });
        loginBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((CardLayout) rootPanel.getLayout()).show(rootPanel, "StartScreen");
            }
        });
        signupBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((CardLayout) rootPanel.getLayout()).show(rootPanel, "StartScreen");
            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                loginAs(usernameF.getText(), loginPassF.getText(), (String)loginBox.getSelectedItem());
            }
        });
    }

    void loginAs(String username, String password, String type)
    {
        boolean result = Controller.tryLogin(username, password, type);
        if (!result)
        {
            loginErrorLabel.setText("Invalid login Info");
            return;
        }
        if (type.equals("Customer"))
        {
            ((CardLayout) rootPanel.getLayout()).show(rootPanel, "CustomerScreen");
        }
        else
        {
            ((CardLayout) rootPanel.getLayout()).show(rootPanel, "ManagerScreen");
        }
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
        rootPanel = new JPanel();
        add(rootPanel);

    }
}
