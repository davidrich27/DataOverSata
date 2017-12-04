package model.basic;

public class Code implements Comparable<Code>{
  private int id;
  private String name, descr;

  // CONSTRUCTOR
  public Code(int id, String name, String descr){
    this.id = id;
    this.name = name;
    this.descr = descr;
  }
  public Code(int id){  // Dummy Search Constructor
    this.id = id;
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
    this.descr = descr;
  }
  // COMPARATORS
  public int compareTo(Code that){     // Default compare: By ID
    return this.getID().compareTo(that.getID());
  }
  public boolean equals(Code that){  // If IDs or name are the same, they are equal
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
    System.out.println("CODE DETAILS:");
    System.out.println("ID: " + id + ", Name: " + name + ", Descr: " + descr);
    System.out.println("---");
  }
}
