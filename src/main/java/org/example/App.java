
package org.example;

import javax.swing.*;

import org.example.GUIComponents.MainFrame;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        Runnable thread = () -> new App();
        SwingUtilities.invokeLater(thread);
    }

    App()
    {
        JFrame frm = new MainFrame("BookStore App");
        frm.setVisible(true);
        frm.setSize(600, 500);
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
