package org.FileIO;

import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static Constants.Constants.*;

public class AddressBook {

    public void toAdd(LinkedList<Contact> myList) {
        AddressBookMain mainBook = new AddressBookMain();
        String option;
        do {
            Contact contact = new Contact();
            System.out.print("enter name- ");
            contact.setName(mainBook.input.next());
            System.out.print("enter lastname- ");
            contact.setLastname(mainBook.input.next());
            System.out.print("enter address- ");
            contact.setAddress(mainBook.input.next());
            System.out.print("enter city- ");
            contact.setCity(mainBook.input.next());
            System.out.print("enter state- ");
            contact.setState(mainBook.input.next());
            System.out.print("enter zip code- ");
            contact.setZip(mainBook.input.nextInt());
            System.out.print("enter contact- ");
            contact.setPhone(mainBook.input.nextInt());
            System.out.print("enter email- ");
            contact.setEmail(mainBook.input.next());
            if (myList.stream().anyMatch(match -> contact.getPhone() == match.getPhone() || contact.getEmail().equals(match.getEmail()))) {    //Duplicate
                System.out.println("Details of this person already exists or Invalid details");
            } else myList.add(contact);
            System.out.print("Do you want to add more (yes/no) - ");
            option = mainBook.input.next();
        } while (!option.equalsIgnoreCase("no"));
    }

    public void toEdit(LinkedList<Contact> myList) {
        AddressBookMain mainBook = new AddressBookMain();
        System.out.println("what you want to edit :");
        System.out.print("name, lastname, address, state, contact - ");
        String choiceToEdit = mainBook.input.next();
        if (choiceToEdit.equals("name")) {
            System.out.print("whose name : ");
            String oldName = mainBook.input.next();

            myList.stream().filter(predicate -> oldName.equals(predicate.getName())).forEach(newStream -> {
                System.out.print("enter new name : ");
                newStream.setName(mainBook.input.next());
            });
        }
        if (choiceToEdit.equals("lastname")) {
            System.out.print("whose lastname : ");
            String oldName = mainBook.input.next();

            myList.stream().filter(p -> oldName.equals(p.getName())).forEach(list -> {
                System.out.print("enter new Lastname : ");
                list.setLastname(mainBook.input.next());
            });
        }
        if (choiceToEdit.equals("address")) {
            System.out.print("whose address : ");
            String oldName = mainBook.input.next();

            myList.stream().filter(p -> oldName.equals(p.getName())).forEach(list -> {
                System.out.print("enter new address : ");
                list.setAddress(mainBook.input.next());
            });
        }
        if (choiceToEdit.equals("state")) {
            System.out.print("whose state : ");
            String oldName = mainBook.input.next();

            myList.stream().filter(p -> oldName.equals(p.getName())).forEach(list -> {
                System.out.print("enter new state : ");
                list.setAddress(mainBook.input.next());
            });
        }
        if (choiceToEdit.equals("phone")) {
            System.out.print("whose phone : ");
            String oldName = mainBook.input.next();

            myList.stream().filter(p -> p.getName().equals(oldName)).forEach(p -> {
                System.out.print("enter new phone no. : ");
                p.setPhone(mainBook.input.nextInt());
            });
        }
    }

    public void toDelete(LinkedList<Contact> myList) {
        AddressBookMain mainBook = new AddressBookMain();
        System.out.print("Enter the name you want to delete : ");
        String nameToDelete = mainBook.input.next();
        System.out.println("Address book is now updated ");
        IntStream.range(0, myList.size()).filter(i -> nameToDelete.equals(myList.get(i).getName())).forEach(i -> myList.remove(myList.get(i)));
    }

    public void toSortDetails(LinkedList<Contact> myList) {
        AddressBookMain mainBook = new AddressBookMain();
        System.out.print("How do you want to Sort Data - 1.Name , 2.City , 3.State - ");
        int sortingChoice = mainBook.input.nextInt();

        switch (sortingChoice) {
            case SORT_BY_NAME:
                System.out.println(myList.stream().sorted((p, q) -> p.getName().compareTo(q.getName())).collect(Collectors.toList()));
                break;
            case SORT_BY_CITY:
                System.out.println(myList.stream().sorted((p, q) -> p.getCity().compareTo(q.getCity())).collect(Collectors.toList()));
                break;
            case SORT_BY_STATE:
                System.out.println(myList.stream().sorted((p, q) -> p.getState().compareTo(q.getState())).collect(Collectors.toList()));
                break;
        }
    }

    public void toPrint(LinkedList<Contact> myList) {
        myList.stream().map(contact -> contact.getName() + " " + contact.getLastname() + " " + contact.getAddress()
                + " " + contact.getCity() + " " + contact.getState() + " " + contact.getZip() + " " + contact.getPhone()
                + " " + contact.getEmail()).forEach(System.out::println);
    }
}