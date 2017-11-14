package model.basic;

import java.util.*;

public class Account implements Comparable<Account>{
  private int id;
  private String name, descr;
  private double balance, feesBalance, availBalance;

  // Constructor
  public Account(int id, String name, String descr, double balance, double feesBalance, double availBalance){  // For pulling from file
    this.id = id;
    this.name = name;
    this.descr = descr;
    this.balance = balance;
    this.feesBalance = feesBalance;
    this.availBalance = availBalance;
  }
  public Account(int id, String name, String descr){  // For making new account
    this.id = id;
    this.name = name;
    this.descr = descr;
    this.balance = 0;
    this.feesBalance = 0;
    this.availBalance = 0;
  }
  public Account(int id, String name){  // Dummy Search Instance
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
  public Double getBalance(){
    return balance;
  }
  public void setBalance(double balance){
    this.balance = balance;
  }
  public Double getFeesBalance(){
    return feesBalance;
  }
  public void setFeesBalance(double feesBalance){
    this.feesBalance = feesBalance;
  }
  public Double getAvailBalance(){
    return availBalance;
  }
  public void setAvailBalance(double availBalance){
    this.availBalance = availBalance;
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
    System.out.println("Account ID: " + getID() + ", Account Name: " + getName());
    System.out.println("Account Descr: " + getDescr());
    System.out.println("Account Balance: $" + getBalance());
    System.out.println("---");
  }

  // Unit Test
  public static void main(String[] args){
    ArrayList<Account> testList = new ArrayList<Account>();
      Account testAcct = new Account(3, "Johnny's Account", "Don't Ask", 407.50, 0.0, 0.0);
      testList.add(testAcct);
      testAcct = new Account(2, "Master Account", "Where all the Money is...", 12.43, 0.0, 0.0);
      testList.add(testAcct);
      testAcct = new Account(7, "Offshore Savings", "Shh...", 1000000.00, 0.0, 0.0);
      testList.add(testAcct);
    // Sort Tests
    System.out.println("INPUT ORDER:");
    for (Account acct : testList){
      acct.printInfo();
    }
    System.out.println("DEFAULT ORDER (BY_ID):");
    Collections.sort(testList);
    for (Account acct : testList){
      acct.printInfo();
    }
    System.out.println("BY_NAME ORDER:");
    Collections.sort(testList, Account.BY_NAME());
    for (Account acct : testList){
      acct.printInfo();
    }
  }
}
