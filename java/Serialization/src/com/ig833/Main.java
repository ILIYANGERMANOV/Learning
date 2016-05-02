package com.ig833;

import java.io.*;

public class Main {

    public static void main(String[] args) {
        // write your code here
        {
            Employee other = new Employee();
            other.name = "Ico Bagera";

            Employee e = new Employee();
            e.name = "Reyan Ali";
            e.address = "Phokka Kuan, Ambehta Peer";
            e.SSN = 11122333;
            e.number = 101;
            e.otherEmployee = other;

            try {
                File file = new File("employee.ser");
                FileOutputStream fileOut = new FileOutputStream(file);
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                out.writeObject(e);
                out.close();
                fileOut.close();
                System.out.printf("Serialized data is saved in " + file.getAbsolutePath() + "\n\n");
            } catch (IOException i) {
                i.printStackTrace();
            }
        }

        Employee e;
        try {
            File file = new File("employee.ser");
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            e = (Employee) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("Employee class not found");
            c.printStackTrace();
            return;
        }
        System.out.println("Deserialized Employee...");
        System.out.println("Name: " + e.name);
        System.out.println("Address: " + e.address);
        System.out.println("SSN: " + e.SSN);
        System.out.println("Poznava: " + e.otherEmployee.name);
        e.printColleague();
    }

}
