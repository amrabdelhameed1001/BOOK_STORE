package org.example.GUIComponents;

import org.example.Book;
import org.example.Controller;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SearchDeck {
    String userType;
    Book[] searchResults;
    DefaultTableModel tableDTM;

    MainScreen mainScreen;
    private JPanel deck;
    private JPanel searchScreen;
    private JPanel resultScreen;
    private JTextField searchISBNF;
    private JComboBox searchCatBox;
    private JButton searchButton;
    private JTextField searchPubF;
    private JTextField searchAuthF;
    private JTextField searchTitleF;
    private JTable resultTable;
    private JButton resultAnotherSearchButton;
    private JPanel resultSideP;
    private JLabel searchErrorLabel;
    private JLabel resultLabel;

    public SearchDeck(String userType, MainScreen mainScreen) {
        this.mainScreen = mainScreen;
        this.userType = userType;
        $$$setupUI$$$();
        resultTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int selectedCol = resultTable.getSelectedColumn();
                if (selectedCol == 7) {//Add to cart

                } else if (selectedCol == 8) {//Update book
                    int isbn = (Integer) resultTable.getValueAt(resultTable.getSelectedRow(), selectedCol);
                    mainScreen.goToUpdateBook(0);
                }
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tempisbn, pub, author, title, cat;
                Integer isbn;

                tempisbn = searchISBNF.getText();
                title = searchTitleF.getText();
                author = searchAuthF.getText();
                pub = searchPubF.getText();
                cat = (String) searchCatBox.getSelectedItem();
                if (tempisbn.isEmpty() && title.isEmpty() && author.isEmpty() && pub.isEmpty() && cat.isEmpty()) {
                    searchErrorLabel.setText("Please fill at least one of the fields");
                    return;
                }
                try {
                    isbn = tempisbn.isEmpty() ? null : Integer.parseInt(tempisbn);
                } catch (NumberFormatException exception) {
                    searchErrorLabel.setText("Invalid ISBN value");
                    return;
                }
                title = title.isEmpty() ? null : title;
                author = author.isEmpty() ? null : author;
                pub = pub.isEmpty() ? null : pub;
                cat = cat.isEmpty() ? null : cat;
                searchResults = Controller.search(isbn, title, author, pub, cat);
                ((CardLayout) deck.getLayout()).show(deck, "ResultScreen");
                searchErrorLabel.setText("");
                tableDTM.setRowCount(0);
                if (searchResults != null) {
                    for (int i = 0; i < searchResults.length; i++) {
                        Object[] row;
                        if (userType.equals("Manager")) {
                            row = new Object[9];
                            row[8] = "Modify";
                        } else row = new Object[8];
                        row[0] = searchResults[i].isbn;
                        row[1] = searchResults[i].title;
                        row[2] = searchResults[i].author;
                        row[3] = searchResults[i].publisher;
                        row[4] = searchResults[i].year;
                        row[5] = searchResults[i].cat;
                        row[6] = searchResults[i].price;
                        row[7] = "Add to Cart";
                        tableDTM.addRow(row);
                    }
                }
                if (searchResults == null || searchResults.length == 0) resultLabel.setText("No results found");
            }
        });
    }

    public JPanel getRoot() {
        return deck;
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here

        //Create result table
        tableDTM = new DefaultTableModel(0, 0);
        String[] headers;
        if (userType.equals("Customer")) {
            headers = new String[8];
            headers[0] = "ISBN";
            headers[1] = "Title";
            headers[2] = "Author";
            headers[3] = "Publisher";
            headers[4] = "Category";
            headers[5] = "Price";
            headers[6] = "Quantity";
            headers[7] = "Add to cart";
        } else {
            headers = new String[9];
            headers[0] = "ISBN";
            headers[1] = "Title";
            headers[2] = "Author";
            headers[3] = "Publisher";
            headers[4] = "Category";
            headers[5] = "Price";
            headers[6] = "Quantity";
            headers[7] = "Add to cart";
            headers[8] = "Modify";
        }
        tableDTM.setColumnIdentifiers(headers);
        resultTable = new JTable() {
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        resultTable.getTableHeader().setReorderingAllowed(false);
        resultTable.setModel(tableDTM);


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
        searchScreen = new JPanel();
        searchScreen.setLayout(new GridBagLayout());
        deck.add(searchScreen, "SearchScreen");
        final JLabel label1 = new JLabel();
        label1.setText("ISBN");
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.ipady = 20;
        searchScreen.add(label1, gbc);
        final JPanel spacer1 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        searchScreen.add(spacer1, gbc);
        searchISBNF = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        searchScreen.add(searchISBNF, gbc);
        final JLabel label2 = new JLabel();
        label2.setText("Title");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.ipady = 20;
        searchScreen.add(label2, gbc);
        searchTitleF = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        searchScreen.add(searchTitleF, gbc);
        final JLabel label3 = new JLabel();
        label3.setText("Author");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.ipady = 20;
        searchScreen.add(label3, gbc);
        searchAuthF = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        searchScreen.add(searchAuthF, gbc);
        final JLabel label4 = new JLabel();
        label4.setText("Publisher");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.ipady = 20;
        searchScreen.add(label4, gbc);
        searchPubF = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        searchScreen.add(searchPubF, gbc);
        final JLabel label5 = new JLabel();
        label5.setText("Category");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.ipady = 20;
        searchScreen.add(label5, gbc);
        searchCatBox = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement("");
        defaultComboBoxModel1.addElement("Science");
        defaultComboBoxModel1.addElement("Art");
        defaultComboBoxModel1.addElement("History");
        defaultComboBoxModel1.addElement("Geography");
        defaultComboBoxModel1.addElement("Religion");
        searchCatBox.setModel(defaultComboBoxModel1);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        searchScreen.add(searchCatBox, gbc);
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new CardLayout(0, 0));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.ipady = 30;
        searchScreen.add(panel1, gbc);
        searchButton = new JButton();
        searchButton.setText("Search");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        searchScreen.add(searchButton, gbc);
        searchErrorLabel = new JLabel();
        searchErrorLabel.setForeground(new Color(-4511196));
        searchErrorLabel.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.WEST;
        searchScreen.add(searchErrorLabel, gbc);
        resultScreen = new JPanel();
        resultScreen.setLayout(new BorderLayout(0, 0));
        deck.add(resultScreen, "ResultScreen");
        resultSideP = new JPanel();
        resultSideP.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        resultScreen.add(resultSideP, BorderLayout.EAST);
        resultLabel = new JLabel();
        resultLabel.setText("");
        resultSideP.add(resultLabel);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        resultScreen.add(panel2, BorderLayout.SOUTH);
        resultAnotherSearchButton = new JButton();
        resultAnotherSearchButton.setText("AnotherSearch");
        panel2.add(resultAnotherSearchButton);
        final JScrollPane scrollPane1 = new JScrollPane();
        resultScreen.add(scrollPane1, BorderLayout.CENTER);
        resultTable.setAutoResizeMode(0);
        scrollPane1.setViewportView(resultTable);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return deck;
    }

}
