package model;
import view.*;
import controller.*;

import java.util.*;

public class Account implements Comparable<Account>{
  private int id;
  private String name, descr;
  private double initBalance, balance;

  // Constructor
  public Account(int id, String name, String descr, double initBalance){
    this.id = id;
    this.name = name;
    this.descr = descr;
    this.initBalance = initBalance;
    this.balance = initBalance;
  }
  public Account(int id, String name, String descr, double initBalance, double balance){
    this.id = id;
    this.name = name;
    this.descr = descr;
    this.initBalance = initBalance;
    this.balance = balance;
  }
  // DUMMY SEARCH INSTANCES
  public Account(int id, String name){
    this.id = id;
    this.name = name;
  }

  // Getters & Setters
  public int getID(){
    return id;
  }
  public void setID(int id){
    this.id = id;
  }
  public String getName(){
    return name;
  }
  public void setName(String name){
    this.name = name;
  }
  public String getDescr(){
    return descr;
  }
  public void setDescr(String descr){
    this.descr = descr;
  }
  public double getBalance(){
    return balance;
  }
  public double getInitBalance(){
    return initBalance;
  }

  // Comparators
  public int compareTo(Account that){   // Compares two Accounts by ID
    Integer thisID = this.getID();
    Integer thatID = that.getID();
    return thisID.compareTo(thatID);
  }
  public static Comparator<Account> BY_NAME(){  // Compares two Accounts by LastName, FirstName
    return new Comparator<Account>() {
      public int compare(Account a, Account b) {
        String aName = a.getName();
        String bName = b.getName();
        return aName.compareTo(bName);
      }
    };
  }

  // Equals (if Account has same ID or Name, then equal)
  public boolean equals(Account that){
    if (this.id == that.id){
      return true;
    }
    if (this.name.equals(that.name)){
      return true;
    }
    return false;
  }

  // Print Demo
  public void printInfo(){
    System.out.println("Account ID: " + getID());
    System.out.println("Account Name: " + getName());
    System.out.println("Account Descr: " + getDescr());
    System.out.println("Account Initial Balance: $" + getInitBalance());
    System.out.println("Account Balance: $" + getBalance());
    System.out.println("");
  }

  // Unit Test
  public static void main(String[] args){
    Account testAcct = new Account(1, "Johnny's Account", "Don't Ask", 1000000.01);
    testAcct.printInfo();
  }
}
