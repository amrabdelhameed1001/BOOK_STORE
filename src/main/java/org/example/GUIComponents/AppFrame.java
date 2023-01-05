package org.example.GUIComponents;

import org.example.Controller;
import org.example.User;

import javax.swing.*;

public class AppFrame extends JFrame
{

    JPanel currentScreen;
    public AppFrame(String title)
    {
        super(title);
        currentScreen = new SigninDeck().getRoot();
        add(currentScreen);
    }

    public void completeLogin(User user)
    {
        remove(currentScreen);
        currentScreen = new MainScreen(user).getRoot();
        add(currentScreen);
        revalidate();
        repaint();
    }

    public void logout()
    {
        new Controller().logOut();
        remove(currentScreen);
        currentScreen = new SigninDeck().getRoot();
        add(currentScreen);
        revalidate();
        repaint();
    }


}
