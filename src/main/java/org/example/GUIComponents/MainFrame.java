package org.example.GUIComponents;

import org.example.Book;
import org.example.Controller;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    String loggedUser;
    Book[] searchResults;
    DefaultTableModel resultsDTM;
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
    private JButton goToPromoteUserButton;
    private JButton goToAddBookButton;
    private JButton goToOrderBookButton;
    private JButton goToUpdateBookButton;
    private JButton goToSalesReportButton;
    private JPanel manageActionsP;
    private JButton button1;
    private JLabel fnameLabel;
    private JLabel lnameLabel;
    private JLabel emailLabel;
    private JLabel usernameLabel;
    private JPanel manageDeck;
    private JPanel addBookP;
    private JTextField addBookISBNF;
    private JTextField addBookMinF;
    private JComboBox addBookCatBox;
    private JButton addBookConfirmButton;
    private JButton addBookBackButton;
    private JTextField addBookTitleF;
    private JTextField addBookAuthorF;
    private JTextField addBookPubF;
    private JTextField addBookYearF;
    private JTextField addBookPriceF;
    private JLabel addBookErrorLabel;
    private JPanel searchScreen;
    private JButton searchBackButton;
    private JTextField searchISBNF;
    private JTextField searchTitleF;
    private JTextField searchAuthorF;
    private JTextField searchPubF;
    private JComboBox searchCatBox;
    private JPanel promoteUserP;
    private JTextField promoteNameField;
    private JButton promoteBackButton;
    private JButton promoteButton;
    private JLabel promoteLabel;
    private JPanel searchDeck;
    private JPanel resultScreen;
    private JButton searchButton;
    private JButton resultSearchButton;
    private JTable searchResultsTable;
    private JLabel searchErrorLabel;

    public MainFrame(String title) {
        super(title);


        $$$setupUI$$$();
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
                loginErrorLabel.setText("");
            }
        });
        signupBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((CardLayout) rootPanel.getLayout()).show(rootPanel, "StartScreen");
                signupErrorLabel.setText("");
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
        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                signup();
            }
        });
        goToAddBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((CardLayout) manageDeck.getLayout()).show(manageDeck, "AddBookScreen");
            }
        });
        goToPromoteUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((CardLayout) manageDeck.getLayout()).show(manageDeck, "PromoteUserScreen");
            }
        });
        goToUpdateBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        goToSalesReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        goToOrderBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        addBookBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((CardLayout) manageDeck.getLayout()).show(manageDeck, "ManageActionsScreen");
                addBookErrorLabel.setText("");
            }
        });
        addBookConfirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title, author, pub, year, cat;
                int isbn, price, min;

                title = addBookTitleF.getText();
                author = addBookAuthorF.getText();
                pub = addBookPubF.getText();
                year = addBookYearF.getText();
                cat = (String) addBookCatBox.getSelectedItem();
                String tempPrice = addBookPriceF.getText();
                String tempMin = addBookMinF.getText();
                String tempisbn = addBookISBNF.getText();
                if (tempisbn.isEmpty() || title.isEmpty() || author.isEmpty() || pub.isEmpty()
                        || year.isEmpty() || cat.isEmpty() || tempPrice.isEmpty() || tempMin.isEmpty()) {
                    addBookErrorLabel.setText("Fill all fields");
                    return;
                }
                isbn = Integer.parseInt(tempisbn);
                price = Integer.parseInt(tempPrice);
                min = Integer.parseInt(tempMin);
                boolean result = Controller.tryAddBook(isbn, title, author, pub, year, price, cat, min);

                if (!result) {
                    addBookErrorLabel.setText("Book already exists");
                    return;
                }
                addBookErrorLabel.setText("Success! I think :/");
                return;

            }
        });
        promoteBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((CardLayout) manageDeck.getLayout()).show(manageDeck, "ManageActionsScreen");
                promoteLabel.setText("");
            }
        });
        promoteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = promoteNameField.getText();
                if (username.isEmpty()) {
                    promoteLabel.setText("Fill user name");
                    return;
                }
                boolean result = Controller.promoteUser(username);
                if (result) promoteLabel.setText("Success!");
                else promoteLabel.setText("Already promoted.");
            }
        });

        resultSearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                ((CardLayout) searchDeck.getLayout()).show(searchDeck, "SearchScreen");
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String tempisbn, pub, author, title, cat;
                Integer isbn;

                tempisbn = searchISBNF.getText();
                title = searchTitleF.getText();
                author = searchAuthorF.getText();
                pub = searchPubF.getText();
                cat = (String) searchCatBox.getSelectedItem();
                if (tempisbn.isEmpty() && title.isEmpty() && author.isEmpty() && pub.isEmpty() && cat.isEmpty())
                {
                    searchErrorLabel.setText("Please fill at least one of the fields");
                    return;
                }
                isbn = tempisbn.isEmpty() ? null : Integer.parseInt(tempisbn);
                title = title.isEmpty() ? null : title;
                author = author.isEmpty() ? null : author;
                pub = pub.isEmpty() ? null : pub;
                cat = cat.isEmpty() ? null : cat;
                searchResults = Controller.search(isbn, title, author, pub, cat);
                ((CardLayout) searchDeck.getLayout()).show(searchDeck, "ResultScreen");
                resultsDTM.setRowCount(0);
                if (searchResults != null)
                {
                    for (int i = 0; i < searchResults.length; i++)
                    {
                        Object[] row = new Object[7];
                        row[0] = searchResults[i].isbn;
                        row[1] = searchResults[i].title;
                        row[2] = searchResults[i].author;
                        row[3] = searchResults[i].publisher;
                        row[4] = searchResults[i].year;
                        row[6] = searchResults[i].cat;
                        row[5] = searchResults[i].price;
                    }
                }
                searchErrorLabel.setText("");
            }
        });
    }

    void loginAs(String username, String password, String type) {
        if (username.isEmpty() || password.isEmpty()) {
            loginErrorLabel.setText("Invalid login Info");
            return;
        }
        boolean result = Controller.tryLogin(username, password, type);
        if (!result) {
            loginErrorLabel.setText("Invalid login Info");
            return;
        }
        loggedUser = type;
        if (type.equals("Customer")) {
            ((CardLayout) rootPanel.getLayout()).show(rootPanel, "CustomerScreen");
        } else {
            ((CardLayout) rootPanel.getLayout()).show(rootPanel, "ManagerScreen");
        }
    }

    void signup() {
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
        boolean result = Controller.trySignup(fname, lname, pass, username, email, address, phone, userType);
        if (!result) {
            signupErrorLabel.setText("Invalid signup due to DB conflict");
            return;
        }
        loginAs(username, pass, userType);
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
        //Create Root Panel
        rootPanel = new JPanel();
        add(rootPanel);

        //Create results Table
        String[] headers = {"ISBN", "Title", "Author", "Publisher", "Publication Year", "Category", "Price"};
        resultsDTM = new DefaultTableModel(0, 0);
        resultsDTM.setColumnIdentifiers(headers);
        searchResultsTable = new JTable();
        searchResultsTable.setModel(resultsDTM);
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        rootPanel.setLayout(new CardLayout(0, 0));
        startScreen = new JPanel();
        startScreen.setLayout(new GridBagLayout());
        rootPanel.add(startScreen, "StartScreen");
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
        rootPanel.add(signupScreen, "SignupScreen");
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
        rootPanel.add(loginScreen, "LoginScreen");
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
        customerScreen = new JPanel();
        customerScreen.setLayout(new BorderLayout(0, 0));
        rootPanel.add(customerScreen, "CustomerScreen");
        final JTabbedPane tabbedPane2 = new JTabbedPane();
        tabbedPane2.setTabPlacement(1);
        customerScreen.add(tabbedPane2, BorderLayout.CENTER);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridBagLayout());
        tabbedPane2.addTab("Personal", panel2);
        final JPanel spacer7 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.VERTICAL;
        panel2.add(spacer7, gbc);
        final JPanel spacer8 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel2.add(spacer8, gbc);
        final JLabel label13 = new JLabel();
        label13.setText("First name");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.ipady = 20;
        panel2.add(label13, gbc);
        final JLabel label14 = new JLabel();
        label14.setText("Last name");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.ipady = 20;
        panel2.add(label14, gbc);
        final JLabel label15 = new JLabel();
        label15.setText("email");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.ipady = 20;
        panel2.add(label15, gbc);
        final JLabel label16 = new JLabel();
        label16.setText("username");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.ipady = 20;
        panel2.add(label16, gbc);
        button1 = new JButton();
        button1.setText("Button");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel2.add(button1, gbc);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new CardLayout(0, 0));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.BOTH;
        panel2.add(panel3, gbc);
        fnameLabel = new JLabel();
        fnameLabel.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel2.add(fnameLabel, gbc);
        lnameLabel = new JLabel();
        lnameLabel.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        panel2.add(lnameLabel, gbc);
        emailLabel = new JLabel();
        emailLabel.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        panel2.add(emailLabel, gbc);
        usernameLabel = new JLabel();
        usernameLabel.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        panel2.add(usernameLabel, gbc);
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridBagLayout());
        tabbedPane2.addTab("Search", panel4);
        final JPanel spacer9 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.VERTICAL;
        panel4.add(spacer9, gbc);
        final JPanel spacer10 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel4.add(spacer10, gbc);
        final JLabel label17 = new JLabel();
        label17.setText("ISBN");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.ipadx = 10;
        gbc.ipady = 20;
        panel4.add(label17, gbc);
        final JLabel label18 = new JLabel();
        label18.setText("Title");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.ipadx = 10;
        gbc.ipady = 20;
        panel4.add(label18, gbc);
        final JLabel label19 = new JLabel();
        label19.setText("Author");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.ipadx = 10;
        gbc.ipady = 20;
        panel4.add(label19, gbc);
        final JLabel label20 = new JLabel();
        label20.setText("Publisher");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.ipadx = 10;
        gbc.ipady = 20;
        panel4.add(label20, gbc);
        final JLabel label21 = new JLabel();
        label21.setText("Category");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.ipadx = 10;
        gbc.ipady = 20;
        panel4.add(label21, gbc);
        final JTextField textField1 = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel4.add(textField1, gbc);
        final JTextField textField2 = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel4.add(textField2, gbc);
        final JTextField textField3 = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel4.add(textField3, gbc);
        final JTextField textField4 = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel4.add(textField4, gbc);
        final JComboBox comboBox1 = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel3 = new DefaultComboBoxModel();
        defaultComboBoxModel3.addElement("");
        defaultComboBoxModel3.addElement("Science");
        defaultComboBoxModel3.addElement("Art");
        defaultComboBoxModel3.addElement("Religion");
        defaultComboBoxModel3.addElement("History");
        defaultComboBoxModel3.addElement("Geography");
        comboBox1.setModel(defaultComboBoxModel3);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel4.add(comboBox1, gbc);
        final JButton button2 = new JButton();
        button2.setText("Search");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel4.add(button2, gbc);
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new CardLayout(0, 0));
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.ipady = 30;
        panel4.add(panel5, gbc);
        managerScreen = new JPanel();
        managerScreen.setLayout(new BorderLayout(0, 0));
        rootPanel.add(managerScreen, "ManagerScreen");
        tabbedPane1 = new JTabbedPane();
        tabbedPane1.setTabPlacement(1);
        managerScreen.add(tabbedPane1, BorderLayout.CENTER);
        manageDeck = new JPanel();
        manageDeck.setLayout(new CardLayout(0, 0));
        tabbedPane1.addTab("Admin", manageDeck);
        manageActionsP = new JPanel();
        manageActionsP.setLayout(new GridBagLayout());
        manageDeck.add(manageActionsP, "ManageActionsScreen");
        goToPromoteUserButton = new JButton();
        goToPromoteUserButton.setText("Promote User");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.weightx = 1.0;
        manageActionsP.add(goToPromoteUserButton, gbc);
        goToAddBookButton = new JButton();
        goToAddBookButton.setText("Add Book");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        manageActionsP.add(goToAddBookButton, gbc);
        goToOrderBookButton = new JButton();
        goToOrderBookButton.setText("Order Book");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 1.0;
        manageActionsP.add(goToOrderBookButton, gbc);
        goToUpdateBookButton = new JButton();
        goToUpdateBookButton.setText("Update Book");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        manageActionsP.add(goToUpdateBookButton, gbc);
        final JPanel panel6 = new JPanel();
        panel6.setLayout(new CardLayout(0, 0));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.ipady = 15;
        manageActionsP.add(panel6, gbc);
        final JPanel panel7 = new JPanel();
        panel7.setLayout(new CardLayout(0, 0));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.ipady = 15;
        manageActionsP.add(panel7, gbc);
        final JPanel panel8 = new JPanel();
        panel8.setLayout(new CardLayout(0, 0));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.ipady = 15;
        manageActionsP.add(panel8, gbc);
        goToSalesReportButton = new JButton();
        goToSalesReportButton.setText("Sales Report");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 8;
        manageActionsP.add(goToSalesReportButton, gbc);
        final JPanel panel9 = new JPanel();
        panel9.setLayout(new CardLayout(0, 0));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.ipady = 15;
        manageActionsP.add(panel9, gbc);
        addBookP = new JPanel();
        addBookP.setLayout(new GridBagLayout());
        manageDeck.add(addBookP, "AddBookScreen");
        final JPanel spacer11 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.VERTICAL;
        addBookP.add(spacer11, gbc);
        final JPanel spacer12 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        addBookP.add(spacer12, gbc);
        final JLabel label22 = new JLabel();
        label22.setText("ISBN");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.ipady = 20;
        addBookP.add(label22, gbc);
        addBookISBNF = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        addBookP.add(addBookISBNF, gbc);
        final JLabel label23 = new JLabel();
        label23.setText("Author");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.ipady = 20;
        addBookP.add(label23, gbc);
        addBookAuthorF = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        addBookP.add(addBookAuthorF, gbc);
        final JLabel label24 = new JLabel();
        label24.setText("Publication Year");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.ipady = 20;
        addBookP.add(label24, gbc);
        addBookYearF = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        addBookP.add(addBookYearF, gbc);
        final JLabel label25 = new JLabel();
        label25.setText("Minimum Quantity");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.ipady = 20;
        addBookP.add(label25, gbc);
        final JLabel label26 = new JLabel();
        label26.setText("Title");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.ipady = 20;
        addBookP.add(label26, gbc);
        addBookTitleF = new JTextField();
        addBookTitleF.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        addBookP.add(addBookTitleF, gbc);
        final JLabel label27 = new JLabel();
        label27.setText("Publisher");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.ipady = 20;
        addBookP.add(label27, gbc);
        addBookPubF = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        addBookP.add(addBookPubF, gbc);
        final JLabel label28 = new JLabel();
        label28.setText("Selling Price");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.ipady = 20;
        addBookP.add(label28, gbc);
        addBookPriceF = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        addBookP.add(addBookPriceF, gbc);
        addBookMinF = new JTextField();
        addBookMinF.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        addBookP.add(addBookMinF, gbc);
        final JLabel label29 = new JLabel();
        label29.setText("Category");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.ipady = 20;
        addBookP.add(label29, gbc);
        addBookCatBox = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel4 = new DefaultComboBoxModel();
        defaultComboBoxModel4.addElement("");
        defaultComboBoxModel4.addElement("Science");
        defaultComboBoxModel4.addElement("Art");
        defaultComboBoxModel4.addElement("History");
        defaultComboBoxModel4.addElement("Geography");
        defaultComboBoxModel4.addElement("Religion");
        addBookCatBox.setModel(defaultComboBoxModel4);
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        addBookP.add(addBookCatBox, gbc);
        final JPanel panel10 = new JPanel();
        panel10.setLayout(new CardLayout(0, 0));
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.ipady = 10;
        addBookP.add(panel10, gbc);
        addBookConfirmButton = new JButton();
        addBookConfirmButton.setText("Add book");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 6;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        addBookP.add(addBookConfirmButton, gbc);
        addBookBackButton = new JButton();
        addBookBackButton.setText("Back");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        addBookP.add(addBookBackButton, gbc);
        addBookErrorLabel = new JLabel();
        addBookErrorLabel.setForeground(new Color(-4511196));
        addBookErrorLabel.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.WEST;
        addBookP.add(addBookErrorLabel, gbc);
        promoteUserP = new JPanel();
        promoteUserP.setLayout(new GridBagLayout());
        manageDeck.add(promoteUserP, "PromoteUserScreen");
        final JLabel label30 = new JLabel();
        label30.setText("User name");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        promoteUserP.add(label30, gbc);
        final JPanel spacer13 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        promoteUserP.add(spacer13, gbc);
        final JPanel spacer14 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.VERTICAL;
        promoteUserP.add(spacer14, gbc);
        promoteNameField = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        promoteUserP.add(promoteNameField, gbc);
        promoteBackButton = new JButton();
        promoteBackButton.setText("Back");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        promoteUserP.add(promoteBackButton, gbc);
        promoteButton = new JButton();
        promoteButton.setText("Promote");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        promoteUserP.add(promoteButton, gbc);
        final JPanel panel11 = new JPanel();
        panel11.setLayout(new CardLayout(0, 0));
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.ipady = 50;
        promoteUserP.add(panel11, gbc);
        promoteLabel = new JLabel();
        promoteLabel.setForeground(new Color(-4515049));
        promoteLabel.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        promoteUserP.add(promoteLabel, gbc);
        searchDeck = new JPanel();
        searchDeck.setLayout(new CardLayout(0, 0));
        tabbedPane1.addTab("Search", searchDeck);
        searchScreen = new JPanel();
        searchScreen.setLayout(new GridBagLayout());
        searchDeck.add(searchScreen, "SearchScreen");
        final JPanel spacer15 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        searchScreen.add(spacer15, gbc);
        final JPanel spacer16 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        searchScreen.add(spacer16, gbc);
        final JLabel label31 = new JLabel();
        label31.setText("ISBN");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.ipadx = 10;
        gbc.ipady = 20;
        searchScreen.add(label31, gbc);
        final JLabel label32 = new JLabel();
        label32.setText("Title");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.ipadx = 10;
        gbc.ipady = 20;
        searchScreen.add(label32, gbc);
        final JLabel label33 = new JLabel();
        label33.setText("Author");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.ipadx = 10;
        gbc.ipady = 20;
        searchScreen.add(label33, gbc);
        final JLabel label34 = new JLabel();
        label34.setText("Publisher");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.ipadx = 10;
        gbc.ipady = 20;
        searchScreen.add(label34, gbc);
        final JLabel label35 = new JLabel();
        label35.setText("Category");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.ipadx = 10;
        gbc.ipady = 20;
        searchScreen.add(label35, gbc);
        searchISBNF = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        searchScreen.add(searchISBNF, gbc);
        searchTitleF = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        searchScreen.add(searchTitleF, gbc);
        searchAuthorF = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        searchScreen.add(searchAuthorF, gbc);
        searchPubF = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        searchScreen.add(searchPubF, gbc);
        searchCatBox = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel5 = new DefaultComboBoxModel();
        defaultComboBoxModel5.addElement("");
        defaultComboBoxModel5.addElement("Science");
        defaultComboBoxModel5.addElement("Art");
        defaultComboBoxModel5.addElement("Religion");
        defaultComboBoxModel5.addElement("History");
        defaultComboBoxModel5.addElement("Geography");
        searchCatBox.setModel(defaultComboBoxModel5);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        searchScreen.add(searchCatBox, gbc);
        searchButton = new JButton();
        searchButton.setText("Search");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        searchScreen.add(searchButton, gbc);
        final JPanel panel12 = new JPanel();
        panel12.setLayout(new CardLayout(0, 0));
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.ipady = 30;
        searchScreen.add(panel12, gbc);
        searchErrorLabel = new JLabel();
        searchErrorLabel.setForeground(new Color(-4511196));
        searchErrorLabel.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 7;
        searchScreen.add(searchErrorLabel, gbc);
        resultScreen = new JPanel();
        resultScreen.setLayout(new BorderLayout(0, 0));
        searchDeck.add(resultScreen, "ResultScreen");
        resultSearchButton = new JButton();
        resultSearchButton.setText("Another Search");
        resultScreen.add(resultSearchButton, BorderLayout.SOUTH);
        final JScrollPane scrollPane1 = new JScrollPane();
        resultScreen.add(scrollPane1, BorderLayout.CENTER);
        scrollPane1.setViewportView(searchResultsTable);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return rootPanel;
    }

}
