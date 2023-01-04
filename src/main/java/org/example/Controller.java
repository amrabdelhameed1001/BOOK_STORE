package org.example;

public class Controller {
    public static boolean tryLogin(String username, String password, String userType)
    {//Should return true if user credentials are valid and false otherwise
        return true;
    }

    public static boolean trySignup(String fname, String lname, String pass, String username, String email, String address, String phone, String userType)
    {//Should return true if email and username aren't used already and false otherwise.
        return true;
    }

    public static boolean tryAddBook(int isbn, String title, String author, String publisher, String year, int price, String category, int minAmount, int inStock)
    {//Should return true if the book isbn isn't already in the DB
        return true;
    }

    public static Book[] search(Integer isbn, String title, String author, String pub, String cat)
    {//Should return an array of Book objects that meet the search criterea.
        //Any of the arguments can be null meaning they aren't part of the search.
        //At least one of the arguments will not be null.
        return null;
    }


    public static boolean tryUpdateBook(int isbn, String title, String category)
    {
        return true;
    }
    public static boolean OrderBook()
    {
        return true;
    }

    public static boolean promoteUser(String username)
    {
        return true;
    }
}
