package org.FileIO;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static Constants.Constants.*;

public class JsonClass {

    public void toReadWriteJSON() {
        AddressBookMain mainBook = new AddressBookMain();
        JsonClass main = new JsonClass();
        int inputOption = 0;
        while (inputOption != 3) {
            System.out.print("What do you want (1.Write , 2.Read , 3.Stop) - ");
            inputOption = mainBook.input.nextInt();
            switch (inputOption) {
                case WRITE_JSON_FILE:
                    JSONObject contactDetails = new JSONObject();
                    contactDetails.put("1* Name", "Shrey");
                    contactDetails.put("2* LastName", "Bhatt");
                    contactDetails.put("3* Address", "Dehradun");

                    JSONArray list = new JSONArray();
                    list.put(contactDetails);
                    String choice;
                    do {
                        list.put(main.writingToJSON2());
                        System.out.print("Do you want to add more - ");
                        choice = mainBook.input.next();
                    } while (!choice.equals("no"));

                    try (FileWriter myFile = new FileWriter("C:\\Users\\USER\\IdeaProjects\\" +
                            "AddressBookFileIO\\src\\main\\resources\\AddressBook2.json")) {
                        myFile.write(list.toString(1));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("File is Updated");
                    break;

                case READ_JSON_FILE:
                    File myFile = new File("C:\\Users\\USER\\IdeaProjects\\" +
                            "AddressBookFileIO\\src\\main\\resources\\AddressBook2.json");
                    try {
                        Scanner scanOne = new Scanner(myFile);
                        while (scanOne.hasNextLine()) {
                            System.out.println(scanOne.nextLine());
                        }
                        scanOne.close();
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e.getMessage());
                    }
                    System.out.println();
                    break;

                case STOP_JSON_FILE:
                    System.out.println("Thank You");
                    break;
            }
        }
    }

    public JSONObject writingToJSON2() {
        AddressBookMain mainBook = new AddressBookMain();
        System.out.println("Enter details -");
        JSONObject contactDetails = null;

        contactDetails = new JSONObject();
        System.out.println("Enter Name");
        String name = mainBook.input.next();
        System.out.println("Enter LastName");
        String last = mainBook.input.next();
        System.out.println("Enter Address");
        String add = mainBook.input.next();
        contactDetails.put("1* Name", name);
        contactDetails.put("2* LastName", last);
        contactDetails.put("3* Address", add);

        return contactDetails;
    }
}