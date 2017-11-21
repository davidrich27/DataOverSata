package model.basic;

import java.util.*;

public class FeeType implements Comparable<FeeType>{
  private int id;
  private String name, descr;
  private double amt;
  private boolean isPercent, isAdditional, isCustom;

  // CONSTRUCTOR
  public FeeType(int id, String name, String descr, double amt, boolean isPercent, boolean isAdditional, boolean isCustom){
    this.id = id;
    this.name = name;
    this.descr = descr;
    this.amt = amt;
    this.isPercent = isPercent;
    this.isAdditional = isAdditional;
    this.isCustom = isCustom;
  }
  public FeeType(int id){
    this.id = id;
  }
  public FeeType(int id, String name){  // Dummy Search Instace
    this.id = id;
    this.name = name;
  }
  // SETTERS & GETTERS
  public Integer getID(){
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
    this.name = descr;
  }
  public Double getAmt(){
    return amt;
  }
  public void setAmt(Double amt){
    this.amt = amt;
  }
  public Boolean getIsPercent(){
    return this.isPercent;
  }
  public void setIsPercent(boolean isPercent){
    this.isPercent = isPercent;
  }
  public Boolean getIsAdditional(){
    return this.isAdditional;
  }
  public void setIsAdditional(boolean isAdditional){
    this.isAdditional = isAdditional;
  }
  public Boolean getIsCustom(){
    return this.isCustom;
  }
  public void setIsCustom(boolean isAdditional){
    this.isCustom = isCustom;
  }
  // COMPARATORS
  public int compareTo(FeeType that){     // Default compare: By ID
    return this.getID().compareTo(that.getID());
  }
  public boolean equals(FeeType that){  // If IDs or name are the same, they are equal
    boolean testID = this.getID().equals(that.getID());
    if (testID){
      return true;
    }
    boolean testName = this.getName().equals(that.getName());
    if (testName){
      return true;
    }
    return false;
  }
  // PRINT DEMO
  public void printInfo(){
    System.out.println("FEE DETAILS:");
    System.out.println("ID: " + id + ", Name: " + name + ", Descr: " + descr);
    System.out.println("Amount: " + amt + ", Is it an Percentage?: " + isPercent + ", Is it Additional?: " + isAdditional + ", Is it Custom?: " + isCustom);
    System.out.println("---");
  }
  // UNIT TEST
  public static void main(String[] args){
    ArrayList<FeeType> testList = new ArrayList<FeeType>();
      FeeType testFee = new FeeType(1, "Credit Card Fee", "Apply when making credit card transactions.", 4.00, true, true, false);
      testList.add(testFee);
      testFee = new FeeType(4, "Wage Benefits", "Apply when paying out wages.", 25.00, true, false, false);
      testList.add(testFee);
      testFee = new FeeType(2, "Flat Charge", "Example of fee that is not a percentage.", 40.00, false, true, true);
      testList.add(testFee);

    System.out.println("INPUT ORDER:");
    for (FeeType fee : testList){
      fee.printInfo();
    }
    System.out.println("---");
    Collections.sort(testList);
    System.out.println("INPUT ORDER:");
    for (FeeType fee : testList){
      fee.printInfo();
    }
  }
}
