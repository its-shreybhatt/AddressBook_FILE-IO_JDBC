package org.FileIO;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

import static Constants.Constants.*;

public class AddressBookMain {
    Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println(" Welcome to Address Book Program !! ");
        AddressBookMain mainBook = new AddressBookMain();
        CSVClass csvClassObj = new CSVClass();
        JsonClass jsonClass = new JsonClass();
        int userChoice = 0;
        while (userChoice != 4) {
            System.out.println("How do you want to you Address Book - ");
            System.out.print("1-Console, 2-CSV File, 3-JSON File , 4-Stop -->");
            userChoice = mainBook.input.nextInt();
            try {
                switch (userChoice) {
                    case USE_CONSOLE:
                        mainBook.useConsole();
                        break;
                    case USE_CSV_FILE:
                        csvClassObj.toReadWriteCSV();
                        break;
                    case USE_JSON_FILE:
                        jsonClass.toReadWriteJSON();
                        break;
                    case STOP_PROGRAM:
                        System.out.println(" <<- Thank You ->> ");
                        break;
                    default:
                        throw new CustomException("Invalid Input Please Try Again");
                }
            } catch (CustomException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void useConsole() {
        HashMap<String, LinkedList<Contact>> hMap = new HashMap<>();

        AddressBookMain mainBook = new AddressBookMain();
        HashMapClass mapObject = new HashMapClass();
        int userResponse = 0;
        while (userResponse != 8) {
            System.out.println("What do you want to perform (Main Menu) ");
            System.out.print("1.Add / 2.Edit / 3.Delete / 4.List of Books / " +
                    "5.Print ParticularBook / 6.Print CompleteBook / 7. SEARCH / 8.Stop Menu - ");
            userResponse = mainBook.input.nextInt();
            try {
                switch (userResponse) {
                    case TO_ADD_NEW_BOOK:
                        mapObject.addBook(hMap);
                        break;
                    case TO_EDIT_BOOK:
                        mapObject.toEdit(hMap);
                        break;
                    case TO_DELETE_CONTACT:
                        mapObject.deleteBook(hMap);
                        break;
                    case TO_PRINT_LIST_OF_BOOKS:
                        mapObject.listOfBookNames(hMap);
                        break;
                    case TO_PRINT_PARTICULAR_BOOK:
                        mapObject.particularBookName(hMap);
                        break;
                    case TO_PRINT_BOOK:
                        mapObject.printMap(hMap);
                        break;
                    case TO_SEARCH:
                        mapObject.toSearch(hMap);
                        break;
                    case TO_STOP_MAIN_MENU:
                        System.out.println("<  Thank You >");
                        break;
                    default:
                        throw new CustomException("Invalid Input Please Try Again");
                }
            } catch (CustomException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}