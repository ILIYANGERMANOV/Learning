package com.ig833;

public class Employee implements java.io.Serializable {
    public String name;
    public String address;
    public Employee otherEmployee;
    public transient int SSN;
    public int number;

    public void mailCheck() {
        System.out.println("Mailing a check to " + name + " " + address);
    }

    public void printColleague() {
        System.out.println("Averche s " + otherEmployee.name);
    }
}