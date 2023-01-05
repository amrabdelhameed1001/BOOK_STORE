package org.example.GUIComponents;

import org.example.Controller;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManageDeck {
    DefaultTableModel tableDTM;
    private JPanel manageActionsP;
    private JButton goToPromoteUserButton;
    private JButton goToAddBookButton;
    private JButton goToOrderBookButton;
    private JButton goToUpdateBookButton;
    private JButton goToSalesReportButton;
    private JPanel deck;
    private JPanel addBookP;
    private JTextField addBookISBNF;
    private JTextField addBookAuthorF;
    private JTextField addBookYearF;
    private JTextField addBookTitleF;
    private JTextField addBookPubF;
    private JTextField addBookPriceF;
    private JTextField addBookMinF;
    private JComboBox addBookCatBox;
    private JButton addBookConfirmButton;
    private JButton addBookBackButton;
    private JLabel addBookErrorLabel;
    private JTextField addBookinStockF;
    private JPanel promoteUserP;
    private JTextField promoteNameField;
    private JButton promoteBackButton;
    private JButton promoteButton;
    private JLabel promoteLabel;
    private JPanel placeOrderP;
    private JTextField placeOrderISBNF;
    private JButton placeOrderBackButton;
    private JButton placeOrderButton;
    private JLabel placeOrderErrorLabel;
    private JPanel ordersP;
    private JTable ordersTable;
    private JPanel updateBookP;
    private JTextField updateBookISBNF;
    private JTextField updateBookSoldF;
    private JButton updateBookBackButton;
    private JButton updateBookConfirmButton;
    private JLabel updateBookErrorLabel;
    private JButton seeOrdersButton;
    private JButton ordersbackButton;


    public ManageDeck() {

        $$$setupUI$$$();
        goToAddBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((CardLayout) deck.getLayout()).show(deck, "AddBookScreen");
            }
        });
        goToUpdateBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((CardLayout) deck.getLayout()).show(deck, "UpdateBookScreen");
            }
        });
        goToOrderBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((CardLayout) deck.getLayout()).show(deck, "PlaceOrderScreen");
            }
        });
        goToPromoteUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((CardLayout) deck.getLayout()).show(deck, "PromoteUserScreen");
            }
        });
        goToSalesReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        addBookBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((CardLayout) deck.getLayout()).show(deck, "ManageActionsScreen");
                addBookErrorLabel.setText("");
            }
        });
        addBookConfirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title, author, pub, year, cat;
                int isbn, price, min, inStock;

                title = addBookTitleF.getText();
                author = addBookAuthorF.getText();
                pub = addBookPubF.getText();
                year = addBookYearF.getText();
                cat = (String) addBookCatBox.getSelectedItem();
                String tempPrice = addBookPriceF.getText();
                String tempMin = addBookMinF.getText();
                String tempisbn = addBookISBNF.getText();
                String tempStock = addBookinStockF.getText();
                if (tempisbn.isEmpty() || title.isEmpty() || author.isEmpty() || pub.isEmpty()
                        || year.isEmpty() || cat.isEmpty() || tempPrice.isEmpty() || tempMin.isEmpty() || tempStock.isEmpty()) {
                    addBookErrorLabel.setText("Fill all fields");
                    return;
                }
                try {
                    isbn = Integer.parseInt(tempisbn);
                    price = Integer.parseInt(tempPrice);
                    min = Integer.parseInt(tempMin);
                    inStock = Integer.parseInt(tempStock);
                    boolean result = Controller.tryAddBook(isbn, title, author, pub, year, price, cat, min, inStock);

                    if (!result) {
                        addBookErrorLabel.setText("Book already exists");
                        return;
                    }
                    addBookErrorLabel.setText("Success!");
                    return;
                } catch (NumberFormatException exception) {
                    addBookErrorLabel.setText("Invalid values.");
                    return;
                }
            }
        });
        promoteBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((CardLayout) deck.getLayout()).show(deck, "ManageActionsScreen");
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
                else promoteLabel.setText("Can't Promote.");
            }
        });
        placeOrderBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((CardLayout) deck.getLayout()).show(deck, "ManageActionsScreen");
                placeOrderErrorLabel.setText("");
            }
        });
        placeOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String strisbn;
                strisbn = placeOrderISBNF.getText();
                if (strisbn.isEmpty()) {
                    placeOrderErrorLabel.setText("Fill all fields");
                    return;
                }
                try {
                    int isbn = Integer.parseInt(strisbn);
                    boolean result = new Controller().placeOrder(isbn);
                    if (result) {
                        placeOrderErrorLabel.setText("Success!");
                    }
                    else placeOrderErrorLabel.setText("Failed.");
                } catch (NumberFormatException exception) {
                    placeOrderErrorLabel.setText("Invalid input.");
                    return;
                }
            }
        });
        updateBookBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((CardLayout) deck.getLayout()).show(deck, "ManageActionsScreen");
                updateBookErrorLabel.setText("");
            }
        });

        updateBookConfirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tempisbn, tempSold;
                tempisbn = updateBookISBNF.getText();
                tempSold = updateBookSoldF.getText();
                if (tempisbn.isEmpty() || tempSold.isEmpty()) {
                    updateBookErrorLabel.setText("Fill all fields");
                    return;
                }
                try {
                    int isbn, sold;
                    isbn = Integer.parseInt(tempisbn);
                    sold = Integer.parseInt(tempSold);
                    boolean result = new Controller().modify(isbn, sold);
                    if (result) {
                        updateBookErrorLabel.setText("Success!");
                    } else
                        updateBookErrorLabel.setText("Failed to update. Check the book is registered and enough copies are available.");
                } catch (NumberFormatException exception) {
                    updateBookErrorLabel.setText("Invalid values");
                    return;
                }
            }
        });
        seeOrdersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((CardLayout) deck.getLayout()).show(deck, "OrdersScreen");
            }
        });
        ordersbackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((CardLayout) deck.getLayout()).show(deck, "ManageActionsScreen");
            }
        });
    }

    void goToUpdateBook(int isbn) {
        updateBookISBNF.setText(String.valueOf(isbn));
        ((CardLayout) deck.getLayout()).show(deck, "UpdateBookScreen");
    }

    public JPanel getRoot() {
        return deck;
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
        deck = new JPanel();
        deck.setLayout(new CardLayout(0, 0));
        manageActionsP = new JPanel();
        manageActionsP.setLayout(new GridBagLayout());
        deck.add(manageActionsP, "ManageActionsScreen");
        goToPromoteUserButton = new JButton();
        goToPromoteUserButton.setText("Promote User");
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 8;
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
        goToOrderBookButton.setText("Place Order");
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
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new CardLayout(0, 0));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.ipady = 15;
        manageActionsP.add(panel1, gbc);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new CardLayout(0, 0));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.ipady = 15;
        manageActionsP.add(panel2, gbc);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new CardLayout(0, 0));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.ipady = 15;
        manageActionsP.add(panel3, gbc);
        goToSalesReportButton = new JButton();
        goToSalesReportButton.setText("Sales Report");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 10;
        manageActionsP.add(goToSalesReportButton, gbc);
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new CardLayout(0, 0));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.ipady = 15;
        manageActionsP.add(panel4, gbc);
        seeOrdersButton = new JButton();
        seeOrdersButton.setText("See Orders");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        manageActionsP.add(seeOrdersButton, gbc);
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new CardLayout(0, 0));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.ipady = 15;
        manageActionsP.add(panel5, gbc);
        addBookP = new JPanel();
        addBookP.setLayout(new GridBagLayout());
        deck.add(addBookP, "AddBookScreen");
        final JPanel spacer1 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.VERTICAL;
        addBookP.add(spacer1, gbc);
        final JPanel spacer2 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        addBookP.add(spacer2, gbc);
        final JLabel label1 = new JLabel();
        label1.setText("ISBN");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.ipady = 20;
        addBookP.add(label1, gbc);
        addBookISBNF = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        addBookP.add(addBookISBNF, gbc);
        final JLabel label2 = new JLabel();
        label2.setText("Author");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.ipady = 20;
        addBookP.add(label2, gbc);
        addBookAuthorF = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        addBookP.add(addBookAuthorF, gbc);
        final JLabel label3 = new JLabel();
        label3.setText("Publication Year");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.ipady = 20;
        addBookP.add(label3, gbc);
        addBookYearF = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        addBookP.add(addBookYearF, gbc);
        final JLabel label4 = new JLabel();
        label4.setText("Minimum Quantity");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.ipady = 20;
        addBookP.add(label4, gbc);
        final JLabel label5 = new JLabel();
        label5.setText("Title");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.ipady = 20;
        addBookP.add(label5, gbc);
        addBookTitleF = new JTextField();
        addBookTitleF.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        addBookP.add(addBookTitleF, gbc);
        final JLabel label6 = new JLabel();
        label6.setText("Publisher");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.ipady = 20;
        addBookP.add(label6, gbc);
        addBookPubF = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        addBookP.add(addBookPubF, gbc);
        final JLabel label7 = new JLabel();
        label7.setText("Selling Price");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.ipady = 20;
        addBookP.add(label7, gbc);
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
        final JLabel label8 = new JLabel();
        label8.setText("Category");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.ipady = 20;
        addBookP.add(label8, gbc);
        addBookCatBox = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement("");
        defaultComboBoxModel1.addElement("Science");
        defaultComboBoxModel1.addElement("Art");
        defaultComboBoxModel1.addElement("History");
        defaultComboBoxModel1.addElement("Geography");
        defaultComboBoxModel1.addElement("Religion");
        addBookCatBox.setModel(defaultComboBoxModel1);
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        addBookP.add(addBookCatBox, gbc);
        final JPanel panel6 = new JPanel();
        panel6.setLayout(new CardLayout(0, 0));
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 6;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.ipady = 10;
        addBookP.add(panel6, gbc);
        addBookConfirmButton = new JButton();
        addBookConfirmButton.setText("Add book");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 7;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        addBookP.add(addBookConfirmButton, gbc);
        addBookBackButton = new JButton();
        addBookBackButton.setText("Back");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        addBookP.add(addBookBackButton, gbc);
        addBookErrorLabel = new JLabel();
        addBookErrorLabel.setForeground(new Color(-4511196));
        addBookErrorLabel.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 7;
        gbc.anchor = GridBagConstraints.WEST;
        addBookP.add(addBookErrorLabel, gbc);
        final JLabel label9 = new JLabel();
        label9.setText("Copies in-stock");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.ipady = 20;
        addBookP.add(label9, gbc);
        addBookinStockF = new JTextField();
        addBookinStockF.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        addBookP.add(addBookinStockF, gbc);
        promoteUserP = new JPanel();
        promoteUserP.setLayout(new GridBagLayout());
        deck.add(promoteUserP, "PromoteUserScreen");
        final JLabel label10 = new JLabel();
        label10.setText("User name");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        promoteUserP.add(label10, gbc);
        final JPanel spacer3 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        promoteUserP.add(spacer3, gbc);
        final JPanel spacer4 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.VERTICAL;
        promoteUserP.add(spacer4, gbc);
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
        final JPanel panel7 = new JPanel();
        panel7.setLayout(new CardLayout(0, 0));
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.ipady = 50;
        promoteUserP.add(panel7, gbc);
        promoteLabel = new JLabel();
        promoteLabel.setForeground(new Color(-4515049));
        promoteLabel.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        promoteUserP.add(promoteLabel, gbc);
        placeOrderP = new JPanel();
        placeOrderP.setLayout(new GridBagLayout());
        deck.add(placeOrderP, "PlaceOrderScreen");
        final JLabel label11 = new JLabel();
        label11.setText("ISBN");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.ipady = 30;
        placeOrderP.add(label11, gbc);
        final JPanel spacer5 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        placeOrderP.add(spacer5, gbc);
        final JPanel spacer6 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        placeOrderP.add(spacer6, gbc);
        placeOrderISBNF = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        placeOrderP.add(placeOrderISBNF, gbc);
        placeOrderBackButton = new JButton();
        placeOrderBackButton.setText("Back");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        placeOrderP.add(placeOrderBackButton, gbc);
        placeOrderButton = new JButton();
        placeOrderButton.setText("Place Order");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        placeOrderP.add(placeOrderButton, gbc);
        placeOrderErrorLabel = new JLabel();
        placeOrderErrorLabel.setForeground(new Color(-4511196));
        placeOrderErrorLabel.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.ipady = 50;
        placeOrderP.add(placeOrderErrorLabel, gbc);
        ordersP = new JPanel();
        ordersP.setLayout(new BorderLayout(0, 0));
        deck.add(ordersP, "OrdersScreen");
        final JPanel panel8 = new JPanel();
        panel8.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel8.setPreferredSize(new Dimension(50, 0));
        ordersP.add(panel8, BorderLayout.EAST);
        final JPanel panel9 = new JPanel();
        panel9.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel9.setPreferredSize(new Dimension(0, 50));
        ordersP.add(panel9, BorderLayout.SOUTH);
        ordersbackButton = new JButton();
        ordersbackButton.setText("Back");
        panel9.add(ordersbackButton);
        final JScrollPane scrollPane1 = new JScrollPane();
        ordersP.add(scrollPane1, BorderLayout.CENTER);
        ordersTable.setAutoResizeMode(0);
        scrollPane1.setViewportView(ordersTable);
        updateBookP = new JPanel();
        updateBookP.setLayout(new GridBagLayout());
        deck.add(updateBookP, "UpdateBookScreen");
        final JLabel label12 = new JLabel();
        label12.setText("Book ISBN");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        updateBookP.add(label12, gbc);
        final JPanel spacer7 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        updateBookP.add(spacer7, gbc);
        final JPanel spacer8 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        updateBookP.add(spacer8, gbc);
        updateBookISBNF = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        updateBookP.add(updateBookISBNF, gbc);
        final JLabel label13 = new JLabel();
        label13.setText("Copies in-stock");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        updateBookP.add(label13, gbc);
        updateBookSoldF = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        updateBookP.add(updateBookSoldF, gbc);
        final JPanel panel10 = new JPanel();
        panel10.setLayout(new CardLayout(0, 0));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.ipady = 50;
        updateBookP.add(panel10, gbc);
        updateBookBackButton = new JButton();
        updateBookBackButton.setText("Back");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        updateBookP.add(updateBookBackButton, gbc);
        updateBookConfirmButton = new JButton();
        updateBookConfirmButton.setText("Confirm");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        updateBookP.add(updateBookConfirmButton, gbc);
        updateBookErrorLabel = new JLabel();
        updateBookErrorLabel.setForeground(new Color(-4511196));
        updateBookErrorLabel.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        updateBookP.add(updateBookErrorLabel, gbc);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return deck;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here

        String[] headers = {"ISBN", "Title", "Quantity", "Confirm"};
        tableDTM = new DefaultTableModel(0, 0);
        tableDTM.setColumnIdentifiers(headers);
        ordersTable = new JTable() {
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        ordersTable.getTableHeader().setReorderingAllowed(false);
        ordersTable.setModel(tableDTM);
    }
}
