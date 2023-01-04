package org.example;

public class Main {
    public static void main(String[] args) {
        Controller c = new Controller();
//        if(c.validLogin("salma123", "123", "Manager"))
//        System.out.println("validdddddd");
//        if(c.tryLogin("salma123", "123", "Customer"))
//            System.out.println("Doneeeee");
//        if(c.tryAddBookToCart("123", 3))
//            System.out.println("adddddddd");
//        if(c.tryEditUserInfo("salma123", "123", "saeed", "salma", "salma@gmail.com", "01117642351", "victoria"))
//            System.out.println("yesssssssss");
//        if(c.confirm_orders_in_shopping_cart())
//            System.out.println();
//        System.out.println(c.getBprice(123));
        if(c.confirm_orders_in_shopping_cart()){
            System.out.println("heeeeeeeeh");
        }
    }
}
