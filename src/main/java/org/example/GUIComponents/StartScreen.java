package org.example.GUIComponents;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StartScreen extends JPanel implements ActionListener
{
    JButton toLS;
    JButton toRF;

    public StartScreen()
    {
        setLayout(new GridLayout(4, 3, 5, 20));
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        toLS = new JButton("Sign up");
        toRF = new JButton("Login");
        add(new JPanel());
        add(new JPanel());
        add(new JPanel());
        add(new JPanel());
        add(toLS);
        add(new JPanel());
        add(new JPanel());
        add(toRF);
        add(new JPanel());
        add(new JPanel());
        add(new JPanel());
        add(new JPanel());
        toLS.setActionCommand("To LS");
        toRF.setActionCommand("To RF");
        toLS.addActionListener(this);
        toRF.addActionListener(this);

    }

    public void actionPerformed(ActionEvent ae)
    {
        if (ae.getActionCommand().equals("Sign up"))
        {

        }
        else
        {

        }
    }
}



