package Databases;

public class Database
{
  //the login credentials have been hard coded. username ="admin" and password ="admin"
  private static final String USERNAME = "admin";

  private static final String PASSWORD = "admin";
  
  //check the entered login credentials with the stored login credentials
  public static boolean isValidUser(String username, String password)
  {
    return USERNAME.equalsIgnoreCase(username) && PASSWORD.equalsIgnoreCase(password);
  }
}
