package model.basic;

import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class Transaction implements Comparable<Transaction>{
  private int id, acctId, userId, codeId;
  private double subTotal, feeTotal, total;
  private double acctTotal;
  private String otherParty, descr;
  private LocalDateTime dateEntry;
  private LocalDate dateSale;
  private boolean isExpense, paidFee;

  // CONSTRUCTORS
  // With no Fees
  public Transaction(int id, int acctId, int userId, int codeId, double subTotal, double acctTotal, String otherParty, String descr, LocalDateTime dateEntry, LocalDate dateSale, boolean isExpense){
    this.id = id;
    this.acctId = acctId;
    this.userId = userId;
    this.codeId = codeId;
    this.subTotal = subTotal;
    this.feeTotal = 0;
    this.total = subTotal;
    this.otherParty = otherParty;
    this.descr = descr;
    this.dateEntry = dateEntry;
    this.dateSale = dateSale;
    this.isExpense = isExpense;
    this.paidFee = true;
  }
  // With Fees
  public Transaction(int id, int acctId, int userId, int codeId, double subTotal, double feeTotal, double acctTotal, String otherParty, String descr, LocalDateTime dateEntry, LocalDate dateSale, boolean isExpense, boolean paidFee){
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
    this.dateEntry = dateEntry;
    this.dateSale = dateSale;
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
  public Integer getAcctID(){
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
  public LocalDateTime getDateEntry(){
    return dateEntry;
  }
  public void setDateEntry(LocalDateTime date){
    this.dateEntry = dateEntry;
  }
  public LocalDate getDateSale(){
    return dateSale;
  }
  public void setDateSale(LocalDate date){
    this.dateSale = dateSale;
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
  public static Comparator<Transaction> BY_DATESALE_EARLIEST(){  // Compares two Trans by Date (Most Recent First)
    return new Comparator<Transaction>() {
      public int compare(Transaction a, Transaction b) {
        return a.getDateSale().compareTo(b.getDateSale());
      }
    };
  }
  public static Comparator<Transaction> BY_DATESALE_OLDEST(){  // Compares two Trans by Date (Oldest First)
    return new Comparator<Transaction>() {
      public int compare(Transaction a, Transaction b) {
        int testTime = a.getDateSale().compareTo(b.getDateSale());
        return testTime*(-1);
      }
    };
  }
  public static Comparator<Transaction> BY_ACCOUNT(){  // Compares two Trans by Acct, then Date
    return new Comparator<Transaction>() {
      public int compare(Transaction a, Transaction b) {
        int testAcct = a.getAcctID().compareTo(b.getAcctID());
        if (testAcct != 0){
          return testAcct;
        }
        int testDate = a.getDateSale().compareTo(b.getDateSale());
        return testDate;
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
    System.out.println("Date of Sale: " + dateSale + ", Date of Entry: " + dateEntry + ", Is it an Expense?: " + isExpense + ", Has the fee been paid?: " + paidFee);
    System.out.println("---");
  }

  // UNIT TEST
  public static void main(String[] args){
    LocalDateTime ldt = LocalDateTime.now();
    LocalDate ld = LocalDate.now();
    ArrayList<Transaction> testList = new ArrayList<Transaction>();
      Transaction testTrans = new Transaction(1, 1, 1, 50105, 10.00, 1000.00, 10.0, "Some Guy", "Loaned him $10", ldt, ld, true, false);
      testList.add(testTrans);
      testTrans = new Transaction(5, 3, 7, 50105, 5.00, 10000.0, 10.0, "Other Guy", "Earliest - Loaned him $5", ldt.minusDays(3), ld, true, false);
      testList.add(testTrans);
      testTrans = new Transaction(2, 3, 1, 50105, 80.00, 500.0, 10.0, "Some Guy", "Loaned him $80", ldt.minusHours(5), ld, true, false);
      testList.add(testTrans);
      testTrans = new Transaction(4, 3, 1, 50105, 10.00, 600.0, 10.0, "University", "Latest - Fees on Loans", ldt.plusMonths(2), ld, true, true);
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

    Collections.sort(testList, Transaction.BY_DATESALE_EARLIEST());
    System.out.println("---");
    System.out.println("DATE ORDER (EARLIEST FIRST)");
    for (Transaction trans : testList){
      trans.printInfo();
    }
    System.out.println("");

    Collections.sort(testList, Transaction.BY_DATESALE_OLDEST());
    System.out.println("---");
    System.out.println("DATE ORDER (OLDEST FIRST)");
    for (Transaction trans : testList){
      trans.printInfo();
    }
    System.out.println("");
  }
}
