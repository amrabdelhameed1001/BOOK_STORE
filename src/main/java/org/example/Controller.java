package org.example;

import java.sql.*;
import java.util.HashMap;

public class Controller {

    static final String DB_URL = "jdbc:mysql://localhost:3306/BOOK_STORE";

    //This changes from computer to computer. Make sure to change this before running.
    static final String USER = "Java";
    static final String PASS = "1234";

    // this function returns true if the username does not exist in the db table(no 2 usernames are the same)
    // and it returns false if the username exists in the db table
    public static boolean isValid_userName(String userName){
        boolean isValid = false;
        String QUERY = "SELECT EXISTS(SELECT * from user_info WHERE userName=\"" + userName + "\");";
        // Open a connection
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
        ) {
            String sql = "USE BOOK_STORE";
            stmt.executeUpdate(sql);
            ResultSet rs = stmt.executeQuery(QUERY);

            int valid = -1;
            while (rs.next()) {
                //Display values
                valid = rs.getInt(1);
            }
            if (valid == 1) {
                isValid = false;
            } else if (valid == 0) {
                isValid =  true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isValid;
    }

    public static long generateID(){
        long id = 0;
        final String QUERY = "SELECT MAX(userID) from user_info";
        System.out.println(QUERY);
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
        ) {
            String sql = "USE BOOK_STORE";
            stmt.executeUpdate(sql);
            ResultSet rs = stmt.executeQuery(QUERY);
            if(rs.next())
                id = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id+1;
    }

    public static User newUser = new User();
    // --------------------------------------------------------------
    // user sign up
    // --------------------------------------------------------------
    public static boolean trySignup(String userName, String password, String lastName
            , String firstName, String email, String phone, String address, String type){
        if(!isValid_userName(userName)){
            System.out.println("this username exists already for another user, enter a different one");
            return false;
        }
        newUser.setID(generateID()); newUser.setUserName(userName);
        newUser.setPassword(password); newUser.setLastName(lastName);
        newUser.setFirstName(firstName); newUser.setEmail(email);
        newUser.setPhone(phone); newUser.setAddress(address);
        newUser.setType(type);

        final String QUERY = "insert into user_info values(" + newUser.getID() + ",\"" + newUser.getUserName() + "\",\"" +
                newUser.getPassword() + "\",\"" + newUser.getLastName() + "\",\"" + newUser.getFirstName()
                + "\",\"" + newUser.getEmail() + "\",\"" + newUser.getPhone() + "\",\"" +
                newUser.getAddress() + "\",\"" + newUser.getType() + "\");";
        System.out.println(QUERY);
        try{
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            String sql = "USE BOOK_STORE";
            stmt.executeUpdate(sql);
            stmt.executeUpdate(QUERY);
            System.out.println("User Info saved in db successfully...");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // check if username, password and type are correct
    public static boolean validLogin(String username, String password, String type){
        String QUERY = "SELECT EXISTS(SELECT * from user_info WHERE userName=\"" + username + "\");";
        String QUERY1 = "SELECT Password, type from user_info WHERE userName=\"" + username + "\";";
        // Open a connection
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
        ) {
            String sql = "USE book_store";
            stmt.executeUpdate(sql);
            ResultSet rs = stmt.executeQuery(QUERY);
            int valid = -1;
            while (rs.next()) {
                //Display values
                valid = rs.getInt("EXISTS(SELECT * from user_info WHERE userName=\"" + username + "\")");
            }
            if (valid == 1) {
                rs = stmt.executeQuery(QUERY1);
                while (rs.next()) {
                    //Display values
                    String x = rs.getString("Password");
                    String y = rs.getString("type");
                    if(x.equals(password) && y.equals(type)){
                        valid = 1;
                    }else {
                        valid = 0;
                    }
                }
                if (valid == 1){
                    return true;
                }else {
                    System.out.println("incorrect password or type");
                    return false;
                }
            } else if (valid == 0) {
                System.out.println("invalid username");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // set the attributes of the customer in its object to be able to get it back when need
    public static User getUserInfo(String username, String password, String type){
        boolean ok = validLogin(username, password, type);
        String QUERY = "SELECT * FROM book_store.user_info where userName = \""+username+"\";";
        if (ok){
            try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                Statement stmt = conn.createStatement();
            ) {
                String sql = "USE book_store";
                stmt.executeUpdate(sql);
                ResultSet rs = stmt.executeQuery(QUERY);
                while(rs.next()){
                    //Display values
                    newUser.setID(rs.getInt("userID"));
                    newUser.setUserName(rs.getString("userName"));
                    newUser.setPassword(rs.getString("Password"));
                    newUser.setLastName(rs.getString("Lname"));
                    newUser.setFirstName(rs.getString("Fname"));
                    newUser.setEmail(rs.getString("Email"));
                    newUser.setPhone(rs.getString("Phone"));
                    newUser.setAddress(rs.getString("Address"));
                    newUser.setType(rs.getString("type"));
                }
                return newUser;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("Invalid data");
            return null;
        }
        return null;
    }

    // -------------------------------------------------------
    // user log in
    // -------------------------------------------------------
    public static boolean tryLogin(String userName, String password, String type){
        if(validLogin(userName, password, type)){
            newUser = getUserInfo(userName, password, type);
            System.out.println("log in DONE");
            return true;
        }
        System.out.println("log in ERROR");
        return false;
    }

    // -------------------------------------------------
    // edit info
    // -------------------------------------------------
    public boolean tryEditUserInfo(String userName, String password, String lastName
            , String firstName, String email, String phone, String address) {
        System.out.println("da dlw2ty: " + newUser.getUserName());
        if(!userName.equals(newUser.getUserName()) && !isValid_userName(userName)){
            System.out.println("INVALID MODIFICATION");
            return false;
        }
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
        ) {
            String sql = "USE BOOK_STORE";
            stmt.executeUpdate(sql);
            if (password != null) {
                String QUERY = "UPDATE user_info SET password = '" + password + "' WHERE userName = \"" + newUser.getUserName() + "\";";
                System.out.println(QUERY);
                stmt.executeUpdate(QUERY);
                System.out.println("password updated successfully...");
                newUser.setPassword(password);
            }
            if (lastName != null) {
                final String QUERY = "UPDATE user_info SET Lname = '" + lastName + "' WHERE userName = \"" + newUser.getUserName() + "\";";
                stmt.executeUpdate(QUERY);
                System.out.println("last name updated successfully...");
                newUser.setLastName(lastName);
            }

            if (firstName != null) {
                String QUERY = "UPDATE user_info SET Fname = '" + firstName + "' WHERE userName = \"" + newUser.getUserName() + "\";";
                System.out.println(QUERY);
                stmt.executeUpdate(QUERY);
                System.out.println("first name updated successfully...");
                newUser.setFirstName(firstName);
            }
            if (email != null) {
                String QUERY = "UPDATE user_info SET email = '" + email + "' WHERE userName = \"" + newUser.getUserName() + "\";";
                System.out.println(QUERY);
                stmt.executeUpdate(QUERY);
                System.out.println("email updated successfully...");
                newUser.setEmail(email);
            }
            if (phone != null) {
                final String QUERY = "UPDATE user_info SET phone = '" + phone + "' WHERE userName = \"" + newUser.getUserName() + "\";";
                stmt.executeUpdate(QUERY);
                System.out.println("phone updated successfully...");
                newUser.setPhone(phone);
            }
            if (address != null) {
                final String QUERY = "UPDATE user_info SET address = '" + address + "' WHERE userName = \"" + newUser.getUserName() + "\";";
                stmt.executeUpdate(QUERY);
                System.out.println("address updated successfully...");
                newUser.setAddress(address);
            }
            if (userName != null) {
                final String QUERY = "UPDATE user_info SET userName = '" + userName + "' WHERE userName = \"" + newUser.getUserName() + "\";";
                newUser.setUserName(userName);
                stmt.executeUpdate(QUERY);
                System.out.println("userName updated successfully...");
                newUser.setUserName(userName);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Done");
        return true;
    }

    // check if a specific book exists in the shopping cart
    public boolean isISBN_inCart(String ISBN) {
        boolean exists = false;
        String QUERY = "SELECT EXISTS(SELECT * from shopping_cart WHERE bookISBN=\"" + ISBN + "\");";
        // Open a connection
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
        ) {
            String sql = "USE BOOK_STORE";
            stmt.executeUpdate(sql);
            ResultSet rs = stmt.executeQuery(QUERY);

            int valid = -1;
            while (rs.next()) {
                //Display values
                valid = rs.getInt(1);
            }
            if (valid == 1) {
                exists = true;
            } else if (valid == 0) {
                exists = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exists;
    }

    // ----------------------------------------------------
    // add book to shopping cart
    // ----------------------------------------------------
    public boolean tryAddBookToCart(String ISBN, int quantity){
        if(isISBN_inCart(ISBN)){
            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                 Statement stmt = conn.createStatement();
            ) {
                String sql = "USE BOOK_STORE";
                stmt.executeUpdate(sql);
                if (ISBN != null) {
                    String QUERY = "UPDATE shopping_cart SET bookISBN = '" + ISBN + "',bookQuantity = " + quantity + " WHERE customerID = \"" + newUser.getID() + "\";";
                    System.out.println(QUERY);

                    stmt.executeUpdate(QUERY);
                    System.out.println("quantity updated successfully...");
                    return true;
                }
            }catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("ERROR IN ADD BOOK TO CART");
            return false;
        }
        final String QUERY = "insert into shopping_cart values(\"" + newUser.getID() + "\",\"" + ISBN + "\"," + quantity + ");";
        System.out.println(QUERY);
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
        ) {
            String sql = "USE BOOK_STORE";
            stmt.executeUpdate(sql);
            stmt.executeUpdate(QUERY);
            System.out.println("Customer order saved in db successfully...");
            System.out.println("Done");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Error");
        return false;
    }

    // --------------------------------------------
    // view items in shopping cart
    // --------------------------------------------
    public HashMap<String, Integer> viewItems(){
        HashMap<String, Integer> res = new HashMap<String, Integer>();
        final String QUERY = "select Title, bookQuantity from book JOIN shopping_cart ON bookISBN=ISBN where customerID= \"" + newUser.getID() + "\";";
        // Open a connection
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
        ) {
            String sql = "USE book_store";
            stmt.executeUpdate(sql);
            ResultSet rs = stmt.executeQuery(QUERY);
            while (rs.next()) {
                //Display values
                String bookTitle = rs.getString("Title");
                int bookQuantity= rs.getInt("bookQuantity");
                res.put(bookTitle, bookQuantity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    // --------------------------------------
    // view individual price
    // --------------------------------------
    public HashMap<String, Float> viewIndividualPrices(){
        HashMap<String, Float> res = new HashMap<String, Float>();
        final String QUERY = "select Title, Selling_price from book JOIN shopping_cart ON bookISBN=ISBN where customerID= \"" + newUser.getID() + "\";";
        // Open a connection
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
        ) {
            String sql = "USE book_store";
            stmt.executeUpdate(sql);
            ResultSet rs = stmt.executeQuery(QUERY);
            while (rs.next()) {
                //Display values
                String bookTitle = rs.getString("Title");
                Float bookPrice = rs.getFloat("Selling_price");
                res.put(bookTitle, bookPrice);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    // -------------------------------------
    // view total prices
    // -------------------------------------
    public Float viewTotalPrice(){
        HashMap<String, Integer> map1 = viewItems();
        HashMap<String, Float> map2 = viewIndividualPrices();
        float totalPrice = 0;
        for(int i = 0; i < map1.size(); i++){
            totalPrice += map1.get(map1.keySet().toArray()[i]) * map2.get(map2.keySet().toArray()[i]);
        }
        return totalPrice;
    }

    // -------------------------------------------
    // remove an item from the shopping cart
    // -------------------------------------------
    public boolean tryRemoveItem(String ISBN){
        final String QUERY = "delete from shopping_cart where bookISBN= \"" + ISBN + "\" and customerID=" + newUser.getID() + ");";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
        ) {
            String sql = "USE book_store";
            stmt.executeUpdate(sql);
            stmt.executeUpdate(QUERY);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("ERROR in remove item");
            return false;
        }
        System.out.println("remove item DONE");
        return true;
    }

    // ------------------------------------
    // log out
    // ------------------------------------
    public boolean logOut(){
        final String QUERY = "delete from shopping_cart where customerID= \"" + newUser.getID() + "\";";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
        ) {
            String sql = "USE book_store";
            stmt.executeUpdate(sql);
            stmt.executeUpdate(QUERY);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("logout ERROR");
            return false;
        }
        System.out.println("logout DONE");
        return true;
    }

    // get the price of a specific book
    public float getBprice(int ISBN){
        final String QUERY = "select Selling_price from book where ISBN=" + ISBN + ";";
        System.out.println(QUERY);
        float res = 0;
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
        ) {
            String sql = "USE BOOK_STORE";
            stmt.executeUpdate(sql);
            ResultSet rs = stmt.executeQuery(QUERY);
            while (rs.next()){
                res =  rs.getFloat(1);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    // insert a tuple from shopping cart into done list
    public boolean insert_into_done(int id, int ISBN, int quantity, float price){
        java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
        final String QUERY = "insert into done_orders values(\"" + id + "\",\"" + ISBN + "\"," + quantity + "," + price + ",\"" + sqlDate +"\");";
        System.out.println(QUERY);
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
        ) {
            String sql = "USE BOOK_STORE";
            stmt.executeUpdate(sql);
            stmt.executeUpdate(QUERY);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Error");
        return false;
    }

    // -----------------------------------------------------------------
    // move items from shopping cart into done list after confirming
    // -----------------------------------------------------------------
    public boolean confirm_orders_in_shopping_cart(){
        final String QUERY = "select * from shopping_cart ;";
        System.out.println(QUERY);
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
        ) {
            String sql = "USE BOOK_STORE";
            stmt.executeUpdate(sql);
            ResultSet rs = stmt.executeQuery(QUERY);
            while (rs.next()){
                int id = rs.getInt("customerID");
                int ISBN = rs.getInt("bookISBN");
                int quantity = rs.getInt("bookQuantity");
                float price = getBprice(ISBN);
                float totalPrice = quantity * price;
                insert_into_done(id, ISBN, quantity, totalPrice);
            }
            System.out.println("All items has moved into done list successfully");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Error");
        return false;
    }

    public static boolean tryAddBook(int ISBN, String title, String author, String publisher_name, String publication_year,
                                     float selling_price, String category , int threshold, int in_stock)
    {
        System.out.println("d5l");
        boolean flag = false;
        final String QUERY1 = "Select count(*) from publisher where name = \""+publisher_name+"\";";
        System.out.println(QUERY1);
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
        ) {
            String sql = "USE BOOK_STORE";
            stmt.executeUpdate(sql);
            ResultSet rs = stmt.executeQuery(QUERY1);
            int num=0;
            if(rs.next()) {
                num = rs.getInt("count(*)");
                System.out.println(num);
            }
            if(num==0){
                return false;
            }else{
                flag= true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(flag) {
            final String QUERY2 = "call Add_new_books(" + ISBN + ", \"" + title + "\", \"" + author + "\", \"" + publisher_name +
                    "\", \"" + publication_year + "\"," + selling_price + ", \"" + category + "\"," + in_stock + ", " + threshold + ");";
            System.out.println(QUERY2);
            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                 Statement stmt = conn.createStatement();
            ) {
                String sql = "USE BOOK_STORE";
                stmt.executeUpdate(sql);
                ResultSet rs = stmt.executeQuery(QUERY2);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public static Book[] search(Integer isbn, String title, String author, String pub, String cat)
    {//Should return an array of Book objects that meet the search criterea.
        //Any of the arguments can be null meaning they aren't part of the search.
        //At least one of the arguments will not be null.
        //Should return an array of Book objects. If no books meet the search criteria then return an empty array;
        return new Book[0];
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
        final String QUERY3 = "update user_info set type = \"Manager\" where username=\""+username+"\";";
        System.out.println(QUERY3);
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
        ) {
            String sql = "USE BOOK_STORE";
            stmt.executeUpdate(sql);
            stmt.executeUpdate(QUERY3);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    //This is called when a manager tries to place an order for a book.
    public static boolean tryPlaceOrder(int isbn, int quantity)
    {//Should check if isbn is valid. If yes then place the order.
        return true;
    }

    public static Order[] getOrders()
    {//Should all orders that haven't been confirmed yet.
        return new Order[0];
    }

}


