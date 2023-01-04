package org.example;

public class User {
    private long ID;
    private String name;
    private String password;
    private String lastName;
    private String firstName;
    private String email;
    private String phone;
    private String address;
    private String type;

    //setters & getters

    //--> user id
    public void setID(long ID){ this.ID = ID; }
    public long getID(){ return this.ID; }

    //--> user name
    public void setUserName(String name){ this.name = name; }
    public String getUserName(){ return this.name; }

    //--> user password
    public void setPassword(String password){ this.password = password; }
    public String getPassword(){ return this.password; }

    //--> user last name
    public void setLastName(String lastName){ this.lastName = lastName; }
    public String getLastName(){ return this.lastName; }

    //--> user first name
    public void setFirstName(String firstName){ this.firstName = firstName; }
    public String getFirstName(){ return this.firstName; }

    //--> user email
    public void setEmail(String email){ this.email = email; }
    public String getEmail(){ return this.email; }

    //--> user phone
    public void setPhone(String phone){ this.phone = phone; }
    public String getPhone(){ return this.phone; }

    //--> user address
    public void setAddress(String address){ this.address = address; }
    public String getAddress(){ return this.address; }

    //--> user type
    public void setType(String type){ this.type = type; }
    public String getType(){ return this.type; }
}
