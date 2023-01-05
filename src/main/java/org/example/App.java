
package org.example;

import javax.swing.*;

import org.example.GUIComponents.AppFrame;

public class App
{
    public static void main( String[] args )
    {
        Runnable thread = () -> new App();
        SwingUtilities.invokeLater(thread);
    }

    App()
    {
        JFrame frm = new AppFrame("BookStore App");
        frm.setVisible(true);
        frm.setSize(600, 500);
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
