package org.FileIO;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static Constants.Constants.*;

public class CSVClass {

    public void toReadWriteCSV() {
        AddressBookMain mainBook = new AddressBookMain();
        CSVClass main = new CSVClass();
        int inputOption = 0;
        while (inputOption != 3) {
            System.out.print("What do you want (1.Write , 2.Read , 3.Stop) - ");
            inputOption = mainBook.input.nextInt();
            switch (inputOption) {
                case WRITE_CSV_FILE:
                    try {
                        FileWriter file = new FileWriter("C:\\Users\\USER\\IdeaProjects\\" +
                                "AddressBookFileIO\\src\\main\\resources\\addressBook.csv");
                        CSVWriter writer = new CSVWriter(file);
                        String[] header = {"Name", "LastName", "Address", "City", "State", "ZIP", "Phone"};
                        writer.writeNext(header);
                        String[] contactOne = {"Shrey", "Bhatt", "XYZ", "Dehradun", "Uttarakhand", "123", "9999"};
                        writer.writeNext(contactOne);
                        String choice;
                        do {
                            writer.writeNext(main.writingToCSV());
                            System.out.print("Do you want to add more - ");
                            choice = mainBook.input.next();
                        } while (!choice.equals("no"));
                        file.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;

                case READ_CSV_FILE:
                    File myFile = new File("C:\\Users\\USER\\IdeaProjects\\" +
                            "AddressBookFileIO\\src\\main\\resources\\addressBook.csv");
                    try {
                        Scanner scan = new Scanner(myFile);
                        while (scan.hasNextLine()) {
                            System.out.println(scan.nextLine());
                        }
                        scan.close();
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e.getMessage());
                    }
                    break;

                case STOP_CSV_FILE:
                    System.out.println(" <- Thank you -> ");
            }
        }
    }

    public String[] writingToCSV() {
        System.out.println("Enter details -");
        System.out.println("Name, Lastname, Address, City, State, ZIP, Phone");
        String[] userInput = new String[7];
        Scanner input = new Scanner(System.in);
        for (int i = 0; i < userInput.length; i++) {
            userInput[i] = input.next();
        }
        return userInput;
    }
}