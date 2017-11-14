package model.basic;

import java.util.*;
import java.time.LocalDateTime;

public class Transaction implements Comparable<Transaction>{
  private int id, acctId, userId, codeId;
  private double subTotal, feeTotal, total;
  private double acctTotal;
  private String otherParty, descr;
  private LocalDateTime date;
  private boolean isExpense, paidFee;

  // CONSTRUCTORS
  // With no Fees
  public Transaction(int id, int acctId, int userId, int codeId, double subTotal, double acctTotal, String otherParty, String descr, LocalDateTime date, boolean isExpense){
    this.id = id;
    this.acctId = acctId;
    this.userId = userId;
    this.codeId = codeId;
    this.subTotal = subTotal;
    this.feeTotal = 0;
    this.total = subTotal;
    this.otherParty = otherParty;
    this.descr = descr;
    this.date = date;
    this.isExpense = isExpense;
    this.paidFee = true;
  }
  // With Fees
  public Transaction(int id, int acctId, int userId, int codeId, double subTotal, double feeTotal, double acctTotal, String otherParty, String descr, LocalDateTime date, boolean isExpense, boolean paidFee){
    this.id = id;
    this.acctId = acctId;
    this.userId = userId;
    this.codeId = codeId;
    this.subTotal = subTotal;
    this.feeTotal = feeTotal;
    this.total = subTotal + feeTotal;
    this.acctTotal = acctTotal;
    this.otherParty = otherParty;
    this.descr = descr;
    this.date = date;
    this.isExpense = isExpense;
    this.paidFee = paidFee;
  }
  public Transaction(int id){  // Dummy Search Instance
    this.id = id;
  }

  // SETTERS & GETTERS
  public Integer getID(){
    return id;
  }
  public void setID(int id){
    this.id = id;
  }
  public int getAcctID(){
    return acctId;
  }
  public void setAcctID(int acctId){
    this.acctId = acctId;
  }
  public int getUserID(){
    return userId;
  }
  public void setUserID(int userId){
    this.userId = userId;
  }
  public int getCodeID(){
    return codeId;
  }
  public void setCodeID(int codeId){
    this.codeId = codeId;
  }
  public double getSubTotal(){
    return subTotal;
  }
  public void setSubTotal(double subTotal){
    this.subTotal = subTotal;
  }
  public double getFeeTotal(){
    return feeTotal;
  }
  public void setFeeTotal(double subTotal){
    this.subTotal = feeTotal;
  }
  public double getTotal(){
    return total;
  }
  public void setTotal(double total){
    this.total = total;
  }
  public double getAcctTotal(){
    return acctTotal;
  }
  public void setAcctTotal(double acctTotal){
    this.acctTotal = acctTotal;
  }
  public String getOtherParty(){
    return otherParty;
  }
  public void setOtherParty(String otherParty){
    this.total = total;
  }
  public String getDescr(){
    return descr;
  }
  public void setDescr(String descr){
    this.descr = descr;
  }
  public LocalDateTime getDate(){
    return date;
  }
  public void setDate(LocalDateTime date){
    this.date = date;
  }
  public boolean getIsExpense(){
    return isExpense;
  }
  public void setIsExpense(boolean isExpense){
    this.isExpense = isExpense;
  }
  public boolean getPaidFee(){
    return paidFee;
  }
  public void setPaidFee(boolean paidFee){
    this.paidFee = paidFee;
  }

  // COMPARATORS
  public int compareTo(Transaction that){     // Default comparator: By ID
    return this.getID().compareTo(that.getID());
  }
  public static Comparator<Transaction> BY_DATE_EARLIEST(){  // Compares two User by Date (Most Recent First)
    return new Comparator<Transaction>() {
      public int compare(Transaction a, Transaction b) {
        return a.getDate().compareTo(b.getDate());
      }
    };
  }
  public static Comparator<Transaction> BY_DATE_OLDEST(){  // Compares two User by Date (Oldest First)
    return new Comparator<Transaction>() {
      public int compare(Transaction a, Transaction b) {
        int testTime = a.getDate().compareTo(b.getDate());
        return testTime*(-1);
      }
    };
  }
  public boolean equals(Transaction that){
    return this.getID().equals(that.getID());
  }
  // PRINT DEMO
  public void printInfo(){
    System.out.println("TRANSACTION DETAILS:");
    System.out.println("ID: " + id + ", Account ID: " + acctId + ", User ID: " + userId + ", Code ID: " + codeId);
    System.out.println("SubTotal: " + subTotal + ", Fee: " + feeTotal + ", Total: " + total);
    System.out.println("Other Party: " + otherParty + ", Descr: " + descr);
    System.out.println("Date: " + date + ", Is it an Expense?: " + isExpense + ", Has the fee been paid?: " + paidFee);
  }

  // UNIT TEST
  public static void main(String[] args){
    LocalDateTime ldt = LocalDateTime.now();
    ArrayList<Transaction> testList = new ArrayList<Transaction>();
      Transaction testTrans = new Transaction(1, 1, 1, 50105, 10.00, 1000.00, 10.0, "Some Guy", "Loaned him $10", ldt, true, false);
      testList.add(testTrans);
      testTrans = new Transaction(5, 3, 7, 50105, 5.00, 10000.0, 10.0, "Other Guy", "Earliest - Loaned him $5", ldt.minusDays(3), true, false);
      testList.add(testTrans);
      testTrans = new Transaction(2, 3, 1, 50105, 80.00, 500.0, 10.0, "Some Guy", "Loaned him $80", ldt.minusHours(5), true, false);
      testList.add(testTrans);
      testTrans = new Transaction(4, 3, 1, 50105, 10.00, 600.0, 10.0, "University", "Latest - Fees on Loans", ldt.plusMonths(2), true, true);
      testList.add(testTrans);

    System.out.println("---");
    System.out.println("INPUT ORDER");
    for (Transaction trans : testList){
      trans.printInfo();
    }
    System.out.println("");

    Collections.sort(testList);
    System.out.println("---");
    System.out.println("NATURAL ORDER (TRANS ID)");
    for (Transaction trans : testList){
      trans.printInfo();
    }
    System.out.println("");

    Collections.sort(testList, Transaction.BY_DATE_EARLIEST());
    System.out.println("---");
    System.out.println("DATE ORDER (EARLIEST FIRST)");
    for (Transaction trans : testList){
      trans.printInfo();
    }
    System.out.println("");

    Collections.sort(testList, Transaction.BY_DATE_OLDEST());
    System.out.println("---");
    System.out.println("DATE ORDER (OLDEST FIRST)");
    for (Transaction trans : testList){
      trans.printInfo();
    }
    System.out.println("");
  }
}
