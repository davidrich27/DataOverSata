package model.basic;

import java.util.*;

import javax.crypto.spec.PBEKeySpec;
import javax.crypto.SecretKeyFactory;
import java.security.spec.InvalidKeySpecException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.math.BigInteger;

public class User implements Comparable<User> {
  private int id;
  private String username, pwd;
  private String firstName, lastName;
  private String email, phone;
  private boolean admin;

  // Constructors
  public User(int id, String username, String pwd, String firstName, String lastName, String email, String phone, boolean admin){
    this.id = id;
    this.username = username.toLowerCase();
    this.pwd = pwd;
    this.admin = admin;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phone = phone;
  }
  // DUMMY SEARCH INSTANCES
  public User(int id, String username){
    this.id = id;
    this.username = username.toLowerCase();
  }

  // Setters & Getters
  public int getID(){
    return id;
  }
  public void setID(int id){
    this.id = id;
  }
  public String getUsername(){
    return username;
  }
  public void setUsername(String username){
    this.username = username;
  }
  public boolean testPwd(String test) throws NoSuchAlgorithmException, InvalidKeySpecException{
    return (test.equals(pwd));
  }
  public String getPwd(){
    return pwd;
  }
  public void setPwd(String pwd) throws NoSuchAlgorithmException, InvalidKeySpecException{
    this.pwd = pwd;
  }
  public String[] getName(){
    String[] name = {firstName, lastName};
    return name;
  }
  public void setName(String firstName, String lastName){
    this.firstName = firstName;
    this.lastName = lastName;
  }
  public String getFirstName(){
    return firstName;
  }
  public void setFirstName(String firstName){
    this.firstName = firstName;
  }
  public String getLastName(){
    return lastName;
  }
  public void setLastName(String lastName){
    this.lastName = lastName;
  }
  public String getEmail(){
    return email;
  }
  public void setEmail(String email){
    this.email = email;
  }
  public String getPhone(){
    return phone;
  }
  public void setPhone(String phone){
    this.phone = phone;
  }
  public boolean getAdmin(){
    return admin;
  }
  public void setAdmin(boolean admin){
    this.admin = admin;
  }

  // Comparators
  public int compareTo(User that){    // DEFAULT: Compares two Users by ID
    Integer thisID = this.getID();
    Integer thatID = that.getID();
    return thisID.compareTo(thatID);
  }
  public static Comparator<User> BY_NAME(){  // Compares two User by LastName, FirstName
    return new Comparator<User>() {
      public int compare(User a, User b) {
        String[] aName = a.getName();
        String[] bName = b.getName();
        int testFirstName = aName[0].compareTo(bName[0]);
        int testLastName = aName[1].compareTo(bName[1]);
        if (testLastName != 0){
          return testLastName;
        } else if (testFirstName != 0) {
          return testFirstName;
        } else {
          return a.compareTo(b);
        }
      }
    };
  }
  public static Comparator<User> BY_USERNAME(){  // Compares two User by LastName, FirstName
    return new Comparator<User>() {
      public int compare(User a, User b) {
        String aUser = a.getUsername();
        String bUser = b.getUsername();
        return aUser.compareTo(bUser);
      }
    };
  }

  // Equals (if User has same ID or Username, then equal)
  public boolean equals(User that){
    if (this.id == that.id){
      return true;
    }
    System.out.println("Is " + this.username + " equal to " + that.username + "?");
    if (this.username.equals(that.username)){
      return true;
    }
    return false;
  }

  // Login
  public boolean login(String username, String pwd) throws InvalidKeySpecException, NoSuchAlgorithmException{
    username = username.toLowerCase();
    if (this.username.equals(username)){
      return testPwd(pwd);
    }
    return false;
  }

  public String toString(){
    return username;
  }

  // ************************ Password Hashing *******************************************

  /* Example
  String originalPassword = "password";
  String generatedSecuredPasswordHash = generateStrongPasswordHash(originalPassword);
  System.out.println(generatedSecuredPasswordHash);
  */

  public static String generateStrongPasswordHash(String password) throws NoSuchAlgorithmException, InvalidKeySpecException
  {
      int iterations = 1000;
      char[] chars = password.toCharArray();
      byte[] salt = getSalt();

      PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64 * 8);
      SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
      byte[] hash = skf.generateSecret(spec).getEncoded();
      return iterations + ":" + toHex(salt) + ":" + toHex(hash);
  }
  private static byte[] getSalt() throws NoSuchAlgorithmException
  {
      SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
      byte[] salt = new byte[16];
      sr.nextBytes(salt);
      return salt;
  }

  private static String toHex(byte[] array) throws NoSuchAlgorithmException
  {
      BigInteger bi = new BigInteger(1, array);
      String hex = bi.toString(16);
      int paddingLength = (array.length * 2) - hex.length();
      if(paddingLength > 0)
      {
          return String.format("%0"  +paddingLength + "d", 0) + hex;
      } else {
          return hex;
      }
  }

  // ************************** Demos / Unit Tests *******************************

  // Print Demo
  public void printInfo(){
    System.out.println("USER DETAILS: ");
    System.out.println("ID: " + getID() + ", Username: " + getUsername() + ", Password: " + getPwd());
    System.out.println("Name: " + getName()[0] + " " + getName()[1]);
    System.out.println("Email: " + getEmail() + ", Phone: " + getPhone());
    System.out.println("Is Admin? " + getAdmin());
    System.out.println("---");
  }

  // Unit Test
  public static void main(String[] args){
  }
}
