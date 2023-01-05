package org.example.GUIComponents;

import org.example.Controller;
import org.example.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SigninDeck {
    private JPanel deck;
    private JTextField fnameF;
    private JTextField lnameF;
    private JTextField usernameF;
    private JTextField emailF;
    private JTextField addressF;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JTextField phoneF;
    private JButton signupButton;
    private JButton signupBackButton;
    private JComboBox signupUserTypeBox;
    private JLabel signupErrorLabel;
    private JPanel signupScreen;
    private JPanel loginScreen;
    private JTextField loginUserF;
    private JTextField loginPassF;
    private JButton loginBackButton;
    private JButton loginButton;
    private JComboBox loginBox;
    private JLabel loginErrorLabel;
    private JPanel startScreen;
    private JButton toLogin;
    private JButton toSignup;

    public SigninDeck() {
        toLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((CardLayout) deck.getLayout()).show(deck, "LoginScreen");
            }
        });
        toSignup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((CardLayout) deck.getLayout()).show(deck, "SignupScreen");
            }
        });
        loginBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((CardLayout) deck.getLayout()).show(deck, "StartScreen");
            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = loginUserF.getText();
                String password = loginPassF.getText();
                String type = (String) loginBox.getSelectedItem();
                loginAs(username, password, type);
            }
        });
        signupBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((CardLayout) deck.getLayout()).show(deck, "StartScreen");
            }
        });
        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fname, lname, username, pass, email, address, phone, userType;
                fname = fnameF.getText();
                lname = lnameF.getText();
                username = usernameF.getText();
                pass = String.valueOf(passwordField1.getPassword());
                email = emailF.getText();
                address = addressF.getText();
                phone = phoneF.getText();
                userType = (String) signupUserTypeBox.getSelectedItem();
                String p1 = String.valueOf(passwordField1.getPassword());
                String p2 = String.valueOf(passwordField2.getPassword());
                if (!p1.equals(p2)) {
                    signupErrorLabel.setText("The two passwords aren't the same");
                    return;
                }
                if (fname.isEmpty() || lname.isEmpty() || username.isEmpty() ||
                        pass.isEmpty() || email.isEmpty() || address.isEmpty() || phone.isEmpty()) {
                    signupErrorLabel.setText("Please fill all the fields");
                    return;
                }
                boolean result = Controller.trySignup(username, pass, lname, fname, email, phone, address, userType);
                if (!result) {
                    signupErrorLabel.setText("Invalid signup due to DB conflict");
                    return;
                }
                loginAs(username, pass, userType);
            }
        });

    }

    void loginAs(String username, String password, String type) {
        if (username.isEmpty() || password.isEmpty()) {
            loginErrorLabel.setText("Fill user name and password");
            return;
        }
        boolean result = Controller.tryLogin(username, password, type);
        if (!result) {
            loginErrorLabel.setText("Invalid login Info");
            return;
        }
        User user = new User();
        user.setUserName(username);
        user.setFirstName(fnameF.getText());
        user.setLastName(lnameF.getText());
        user.setPassword(password);
        user.setEmail(emailF.getText());
        user.setAddress(addressF.getText());
        user.setPhone(phoneF.getText());
        user.setType(type);
        ((AppFrame) deck.getTopLevelAncestor()).completeLogin(user);
    }


    public JPanel getRoot() {
        return deck;
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        deck = new JPanel();
        deck.setLayout(new CardLayout(0, 0));
        startScreen = new JPanel();
        startScreen.setLayout(new GridBagLayout());
        deck.add(startScreen, "StartScreen");
        final JPanel spacer1 = new JPanel();
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        startScreen.add(spacer1, gbc);
        final JPanel spacer2 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.ipadx = 20;
        gbc.ipady = 20;
        startScreen.add(spacer2, gbc);
        toLogin = new JButton();
        toLogin.setText("Login");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        startScreen.add(toLogin, gbc);
        toSignup = new JButton();
        toSignup.setText("Signup");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        startScreen.add(toSignup, gbc);
        signupScreen = new JPanel();
        signupScreen.setLayout(new GridBagLayout());
        deck.add(signupScreen, "SignupScreen");
        final JPanel spacer3 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        signupScreen.add(spacer3, gbc);
        final JPanel spacer4 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        signupScreen.add(spacer4, gbc);
        final JLabel label1 = new JLabel();
        label1.setText("First name");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.ipady = 20;
        signupScreen.add(label1, gbc);
        fnameF = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        signupScreen.add(fnameF, gbc);
        final JLabel label2 = new JLabel();
        label2.setText("Last name");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.ipady = 20;
        signupScreen.add(label2, gbc);
        final JLabel label3 = new JLabel();
        label3.setText("User name");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.ipady = 20;
        signupScreen.add(label3, gbc);
        final JLabel label4 = new JLabel();
        label4.setText("Password");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.ipady = 20;
        signupScreen.add(label4, gbc);
        final JLabel label5 = new JLabel();
        label5.setText("Repeat password");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.ipady = 20;
        signupScreen.add(label5, gbc);
        lnameF = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        signupScreen.add(lnameF, gbc);
        usernameF = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        signupScreen.add(usernameF, gbc);
        final JLabel label6 = new JLabel();
        label6.setText("Address");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.ipady = 20;
        signupScreen.add(label6, gbc);
        final JLabel label7 = new JLabel();
        label7.setText("Phone");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.ipady = 20;
        signupScreen.add(label7, gbc);
        final JLabel label8 = new JLabel();
        label8.setText("Email");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.ipady = 20;
        signupScreen.add(label8, gbc);
        emailF = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        signupScreen.add(emailF, gbc);
        addressF = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 7;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        signupScreen.add(addressF, gbc);
        passwordField1 = new JPasswordField();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        signupScreen.add(passwordField1, gbc);
        passwordField2 = new JPasswordField();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 6;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        signupScreen.add(passwordField2, gbc);
        phoneF = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 8;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        signupScreen.add(phoneF, gbc);
        signupButton = new JButton();
        signupButton.setText("Sign Up");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 11;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        signupScreen.add(signupButton, gbc);
        signupBackButton = new JButton();
        signupBackButton.setText("Back");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 11;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        signupScreen.add(signupBackButton, gbc);
        signupUserTypeBox = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement("Customer");
        defaultComboBoxModel1.addElement("Manager");
        signupUserTypeBox.setModel(defaultComboBoxModel1);
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 9;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        signupScreen.add(signupUserTypeBox, gbc);
        final JLabel label9 = new JLabel();
        label9.setText("User Type");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.ipady = 20;
        signupScreen.add(label9, gbc);
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.ipady = 20;
        signupScreen.add(panel1, gbc);
        signupErrorLabel = new JLabel();
        signupErrorLabel.setForeground(new Color(-4515305));
        signupErrorLabel.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 11;
        gbc.anchor = GridBagConstraints.WEST;
        signupScreen.add(signupErrorLabel, gbc);
        loginScreen = new JPanel();
        loginScreen.setLayout(new GridBagLayout());
        deck.add(loginScreen, "LoginScreen");
        final JPanel spacer5 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        loginScreen.add(spacer5, gbc);
        final JPanel spacer6 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        loginScreen.add(spacer6, gbc);
        final JLabel label10 = new JLabel();
        label10.setText("User name");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.ipadx = 20;
        gbc.ipady = 30;
        loginScreen.add(label10, gbc);
        loginUserF = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        loginScreen.add(loginUserF, gbc);
        loginPassF = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        loginScreen.add(loginPassF, gbc);
        loginBackButton = new JButton();
        loginBackButton.setText("Back");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.weighty = 0.1;
        gbc.ipadx = 20;
        loginScreen.add(loginBackButton, gbc);
        loginButton = new JButton();
        loginButton.setText("Login");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.ipadx = 20;
        loginScreen.add(loginButton, gbc);
        loginBox = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel2 = new DefaultComboBoxModel();
        defaultComboBoxModel2.addElement("Customer");
        defaultComboBoxModel2.addElement("Manager");
        loginBox.setModel(defaultComboBoxModel2);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.weighty = 0.05;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        loginScreen.add(loginBox, gbc);
        final JLabel label11 = new JLabel();
        label11.setText("Login type");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        loginScreen.add(label11, gbc);
        final JLabel label12 = new JLabel();
        label12.setText("Password");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.ipady = 30;
        loginScreen.add(label12, gbc);
        loginErrorLabel = new JLabel();
        loginErrorLabel.setForeground(new Color(-4513762));
        loginErrorLabel.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        loginScreen.add(loginErrorLabel, gbc);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return deck;
    }

}
