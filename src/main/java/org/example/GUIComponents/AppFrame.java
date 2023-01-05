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

    public void completeLogin(String username, String pass, String type)
    {
        remove(currentScreen);
        User loggedUser = Controller.getUserInfo(username, pass, type);
        currentScreen = new MainScreen(loggedUser).getRoot();
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
