package org.FileIO;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static Constants.Constants.*;

public class HashMapClass {

    public void addBook(HashMap<String, LinkedList<Contact>> map) {
        AddressBookMain mainBook = new AddressBookMain();
        AddressBook addressBook = new AddressBook();
        LinkedList<Contact> myLinkedList = new LinkedList<>();
        System.out.print("Enter the name of Address Book - ");
        String bookName = mainBook.input.next();
        if (map.containsKey(bookName)) {
            System.out.println("Name already exists ");
            System.out.println("Entered to " + bookName + " book ");
            addressBook.toAdd(map.get(bookName));
        } else {
            int choice = 0;
            while (choice != 6) {
                System.out.println("What do you want to perform (Inside " + bookName + " book)");
                System.out.print("1.ADD , 2.Edit , 3.Delete , 4.Print , 5.Sort , 6.Stop this book - ");
                choice = mainBook.input.nextInt();
                switch (choice) {
                    case TO_ADD_CONTACT:
                        addressBook.toAdd(myLinkedList);
                        break;
                    case TO_EDIT_CONTACT:
                        addressBook.toEdit(myLinkedList);
                        break;
                    case TO_DEL_CONTACT:
                        addressBook.toDelete(myLinkedList);
                        break;
                    case TO_PRINT:
                        addressBook.toPrint(myLinkedList);
                        break;
                    case TO_SORT:
                        addressBook.toSortDetails(myLinkedList);
                        break;
                    case TO_STOP_BOOK:
                        System.out.println("\nThank You for " + bookName + " Book \n");
                        break;
                }
                map.put(bookName, myLinkedList);
            }
        }
    }

    public void toEdit(HashMap<String, LinkedList<Contact>> map) {
        AddressBookMain mainBook = new AddressBookMain();
        System.out.println("enter the name of book");
        String bookName = mainBook.input.next();
        if (!map.containsKey(bookName)) {
            System.out.println(bookName + " Book does not exists ");
        } else {
            LinkedList<Contact> contactLinkedList = map.get(bookName);
            if (contactLinkedList.size() == 0) {
                System.out.println("Address Book is Empty");
            } else {
                System.out.println("what you want to edit :");
                System.out.print("name, lastname, address, state, phone - ");
                String choiceToEdit = mainBook.input.next();
                if (choiceToEdit.equals("name")) {
                    System.out.print("whose name : ");
                    String oldName = mainBook.input.next();
                    contactLinkedList.stream().filter(predicate -> oldName.equals(predicate.getName())).forEach(newStream -> {
                        System.out.print("enter new name : ");
                        newStream.setName(mainBook.input.next());
                    });
                }
                if (choiceToEdit.equals("lastname")) {
                    System.out.print("whose lastname : ");
                    String oldName = mainBook.input.next();
                    IntStream.range(0, contactLinkedList.size()).filter(j -> oldName.equals(contactLinkedList.get(j).getName())).forEach(j -> {
                        System.out.print("enter new Lastname : ");
                        contactLinkedList.get(j).setLastname(mainBook.input.next());
                    });
                }
                if (choiceToEdit.equals("address")) {
                    System.out.print("whose address : ");
                    String oldName = mainBook.input.next();
                    contactLinkedList.stream().filter(contact -> oldName.equals(contact.getName())).forEach(contact -> {
                        System.out.print("enter new address : ");
                        contact.setAddress(mainBook.input.next());
                    });
                }
                if (choiceToEdit.equals("state")) {
                    System.out.print("whose state : ");
                    String oldName = mainBook.input.next();
                    contactLinkedList.stream().filter(contact -> oldName.equals(contact.getName())).forEach(contact -> {
                        System.out.print("enter new state : ");
                        contact.setState(mainBook.input.next());
                    });
                }
                if (choiceToEdit.equals("phone")) {
                    System.out.print("whose phone : ");
                    String oldName = mainBook.input.next();
                    contactLinkedList.stream().filter(contact -> oldName.equals(contact.getName())).forEach(contact -> {
                        System.out.print("enter new phone no. : ");
                        contact.setPhone(mainBook.input.nextInt());
                    });
                }
            }
        }
    }

    public void deleteBook(HashMap<String, LinkedList<Contact>> map) {
        AddressBookMain mainBook = new AddressBookMain();
        System.out.println("enter the name of book");
        String bookName = mainBook.input.next();
        if (!map.containsKey(bookName)) {
            System.out.println(bookName + " Book does not exists ");
        } else {
            LinkedList<Contact> contactLinkedList = map.get(bookName);

            System.out.print("Enter the name you want to delete : ");
            String nameToDelete = mainBook.input.next();
            System.out.println("Address book is now updated ");
            IntStream.range(0, contactLinkedList.size()).filter(i -> nameToDelete.equals(contactLinkedList.get(i).getName()))
                    .forEach(i -> contactLinkedList.remove(contactLinkedList.get(i)));
        }
    }

    public void listOfBookNames(HashMap<String, LinkedList<Contact>> map) {
        System.out.println("\nList of All Book are ->");
        map.forEach((key, value) -> {
            System.out.print("* " + key + " Book -->   ");
            value.stream().map(contact -> contact.getName() + " " + contact.getLastname() + " , ").forEach(System.out::print);
            System.out.println();
        });
        System.out.println();
    }

    public void toSearch(HashMap<String, LinkedList<Contact>> map) {
        AddressBookMain mainBook = new AddressBookMain();
        System.out.println("How do you want to Search -> 1.Name / 2.City / 3.State");
        int option = mainBook.input.nextInt();

        switch (option) {
            case SEARCH_BY_NAME:
                System.out.println("enter the name you want to search ");
                String contactName = mainBook.input.next();

                int count = (int) map.entrySet().stream().flatMap(p -> p.getValue().stream())
                        .filter(p -> p.getName().equals(contactName)).count();
                if (count >= 1) {
                    System.out.println(map.entrySet().stream().flatMap(p -> p.getValue().stream())
                            .filter(p -> p.getName().equals(contactName)).collect(Collectors.toList()));
                } else System.out.println("Name " + contactName + " does not exists ");
                break;

            case SEARCH_BY_CITY:
                System.out.println("Please enter name of city");
                String cityName = mainBook.input.next();

                System.out.println(map.entrySet().stream().flatMap(p -> p.getValue().stream())
                        .filter(p -> p.getCity().equals(cityName)).collect(Collectors.toList()));
                System.out.println("Count = " + map.entrySet().stream().flatMap(p -> p.getValue().stream())
                        .filter(p -> p.getCity().equals(cityName)).count());
                break;

            case SEARCH_BY_STATE:
                System.out.println("Please enter name of state");
                String stateName = mainBook.input.next();

                System.out.println(map.entrySet().stream().flatMap(p -> p.getValue().stream())
                        .filter(p -> p.getState().equals(stateName)).collect(Collectors.toList()));
                System.out.println("Count = " + map.entrySet().stream().flatMap(p -> p.getValue().stream())
                        .filter(p -> p.getState().equals(stateName)).count());
                break;
        }
    }

    public void particularBookName(HashMap<String, LinkedList<Contact>> map) {
        AddressBookMain mainBook = new AddressBookMain();
        System.out.println("enter the name of book");
        String bookName = mainBook.input.next();
        if (!map.containsKey(bookName)) {
            System.out.println("Please enter correct address book");
        } else {
            LinkedList<Contact> contactLinkedList = map.get(bookName);
            contactLinkedList.stream().map(contact -> contact.getName() + " " + contact.getLastname() + " "
                    + contact.getAddress() + " " + contact.getCity() + " " + contact.getState() + " "
                    + contact.getZip() + " " + contact.getPhone() + " " + contact.getEmail()).forEach(System.out::println);
        }
    }

    public void printMap(HashMap<String, LinkedList<Contact>> map) {
        System.out.println(map.entrySet().stream().flatMap(p -> p.getValue().stream()).collect(Collectors.toList()));
        System.out.println("Method 2");

        map.forEach((key, value) -> {
            System.out.println(key + " -> ");
            value.stream().map(contact -> contact.getName() + " " + contact.getLastname() + " " + contact.getAddress()
                    + " " + contact.getCity() + " " + contact.getState() + " " + contact.getZip() + " " + contact.getPhone()
                    + " " + contact.getEmail()).forEach(System.out::println);
        });
    }
}