package jdbc;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static Constants.Constants.*;

public class CRUDOperations {

    Scanner input = new Scanner(System.in);
    public void printListOfBooks(Statement stmt) throws SQLException {
        String query = "SELECT book_name FROM book_type ;";
        List<String> listOfBooks = new ArrayList<>();
        ResultSet result = stmt.executeQuery(query);
        while (result.next()) {
            String book_name = result.getString("book_name");
            listOfBooks.add(book_name);
        }
        if (listOfBooks.size() != 0) {
            System.out.println("Books Available = " + listOfBooks.stream().collect(Collectors.toSet()));
        } else {
            System.out.println("No books Available -> Please create ");
        }
    }

    public void toInsertFromConsole(Statement stmt) throws SQLException {
        printListOfBooks(stmt);

        System.out.print("Do you want to Add Book (YES/NO) - ");
        String choiceForBook = input.next();
        if (choiceForBook.equalsIgnoreCase("yes")) {
            System.out.print("Enter the BookName - ");
            String book_name = input.next();
            String bookQuery = String.format("INSERT INTO book_type (book_name) " +
                    "VALUES ('%s') ", book_name);
            stmt.executeUpdate(bookQuery);
        }
        System.out.print("Enter S.Numb of Book you want to Use- ");
        int book_s_numb = input.nextInt();

        System.out.print("Enter the Firstname - ");
        String first_name = input.next();
        System.out.print("Enter the Lastname - ");
        String last_name = input.next();
        System.out.print("Enter the Phone Numb - ");
        String phone = input.next();
        System.out.print("Enter the email - ");
        String email = input.next();

        String printQuery = "SELECT address_numb,city,state,zip FROM address_details ;";
        ResultSet resultSet = stmt.executeQuery(printQuery);
        while (resultSet.next()) {
            String address_numb = String.valueOf(resultSet.getInt("address_numb"));
            String city = resultSet.getString("city");
            String state = resultSet.getString("state");
            String zip = String.valueOf(resultSet.getInt("zip"));
            System.out.println(address_numb + "- " + city + ", " + state + ", " + zip);
        }

        System.out.print("Do you want to Add new Address (YES/NO) - ");
        String choiceForAddress = input.next();
        if (choiceForAddress.equalsIgnoreCase("yes")) {
            System.out.print("Enter the city - ");
            String city = input.next();
            System.out.print("Enter the state - ");
            String state = input.next();
            System.out.print("Enter the zip - ");
            int zip = input.nextInt();
            String addressQuery = String.format("INSERT INTO address_details (city,state,zip) " +
                    "VALUES ('%s','%s','%s') ", city, state, zip);
            stmt.executeUpdate(addressQuery);
        }
        System.out.print("Enter S.Numb of Address you want to Use- ");
        int address_numb = input.nextInt();


        String insertQuery = String.format("INSERT INTO person_details (first_name,last_name,phone,email,book_s_numb,address_numb) " +
                "VALUES ('%s','%s','%s','%s','%s','%s') ", first_name, last_name, phone, email, book_s_numb, address_numb);
        stmt.executeUpdate(insertQuery);
    }

    public void toUpdateData(Statement stmt) throws SQLException {
        printListOfBooks(stmt);

        System.out.print("Enter the Name of Book - ");
        String bookName = input.next();

        String queryNames = String.format("SELECT first_name,last_name FROM person_details " +
                "JOIN book_type ON person_details.book_s_numb = book_type.book_s_numb " +
                "WHERE book_name = '%s' ;", bookName);
        ResultSet resultSet = stmt.executeQuery(queryNames);
        while (resultSet.next()) {
            String first_name = resultSet.getString("first_name");
            String last_name = resultSet.getString("last_name");
            System.out.println(first_name + ", " + last_name);
        }

        System.out.print("Enter the name of Person whose data you want to Update - ");
        String firstName = input.next();

        System.out.println("What do you want to Update - ");
        System.out.print("1.first_name, 2.last_name, 3.phone, 4.email - ");
        int toUpdate = input.nextInt();
        try {
            switch (toUpdate) {
                case 1:
                    System.out.print("Enter new First Name - ");
                    String newFirstName = input.next();
                    String forName = String.format("UPDATE person_details " +
                            "JOIN book_type ON person_details.book_s_numb = book_type.book_s_numb " +
                            "JOIN address_details ON person_details.address_numb = address_details.address_numb " +
                            "SET first_name = '%s' WHERE first_name = '%s' AND book_name = '%s' ;", newFirstName, firstName, bookName);
                    stmt.executeUpdate(forName);
                    break;
                case 2:
                    System.out.print("Enter new Last Name - ");
                    String newLastName = input.next();
                    String forLastName = String.format("UPDATE person_details " +
                            "JOIN book_type ON person_details.book_s_numb = book_type.book_s_numb " +
                            "JOIN address_details ON person_details.address_numb = address_details.address_numb " +
                            "SET last_name = '%s' WHERE first_name = '%s' AND book_name = '%s' ;", newLastName, firstName, bookName);
                    stmt.executeUpdate(forLastName);
                    break;
                case 3:
                    System.out.print("Enter new Phone Number - ");
                    String newPhone = input.next();
                    String forPhone = String.format("UPDATE person_details " +
                            "JOIN book_type ON person_details.book_s_numb = book_type.book_s_numb " +
                            "JOIN address_details ON person_details.address_numb = address_details.address_numb " +
                            "SET phone = '%s' WHERE first_name = '%s' AND book_name = '%s' ;", newPhone, firstName, bookName);
                    stmt.executeUpdate(forPhone);
                    break;
                case 4:
                    System.out.print("Enter new E-Mail - ");
                    String newEmail = input.next();
                    String forEmail = String.format("UPDATE person_details " +
                            "JOIN book_type ON person_details.book_s_numb = book_type.book_s_numb " +
                            "JOIN address_details ON person_details.address_numb = address_details.address_numb " +
                            "SET email = '%s' WHERE first_name = '%s' AND book_name = '%s' ;", newEmail, firstName, bookName);
                    stmt.executeUpdate(forEmail);
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void toDeleteData(Statement statement) throws SQLException {
        System.out.print("Enter the name of Person whose data you want to Delete - ");
        String personToDelete = input.next();
        printListOfBooks(statement);

        System.out.print("Enter the Name of Book - ");
        String bookName = input.next();
        String delQuery = String.format("DELETE person_details FROM person_details " +
                "JOIN book_type ON person_details.book_s_numb = book_type.book_s_numb " +
                "JOIN address_details ON person_details.address_numb = address_details.address_numb " +
                "WHERE first_name = '%s' AND book_name = '%s' ;", personToDelete, bookName);
        try {
            statement.executeUpdate(delQuery);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void toCountData(Statement stmt) {
        String printStatement = "SELECT book_name,first_name,last_name,phone,email,city,state,zip FROM person_details " +
                "JOIN book_type ON person_details.book_s_numb = book_type.book_s_numb " +
                "JOIN address_details ON person_details.address_numb = address_details.address_numb;";

        List<AddressData> listOfContacts = new ArrayList<>();
        try {
            ResultSet result = stmt.executeQuery(printStatement);
            while (result.next()) {
                String book_name = result.getString("book_name");
                String first_name = result.getString("first_name");
                String last_name = result.getString("last_name");
                String phone = result.getString("phone");
                String email = result.getString("email");
                String city = result.getString("city");
                String state = result.getString("state");
                int zip = result.getInt("zip");

                listOfContacts.add(new AddressData(first_name, last_name, phone, email));
                System.out.println(book_name + "-> " + first_name + ", " + last_name + ", " + phone
                        + ", " + email + ", " + city + ", " + state + ", " + zip);
            }
            System.out.println("Count of Contacts = " + listOfContacts.size());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void toSearch(Statement stmt) throws SQLException {
        System.out.print("How do you want to Search (1. By Book Name/ 2. By Person Name) - ");
        int optionToSearch = input.nextInt();

        switch (optionToSearch) {
            case SEARCH_BY_BOOKNAME_JDBC:
                printListOfBooks(stmt);

                System.out.print("Enter the name of Book - ");
                String bookNameToSearch = input.next();
                String sqlStatement = String.format("SELECT book_name,first_name,last_name,phone,city,state,zip FROM person_details " +
                        "JOIN book_type ON book_type.book_s_numb = person_details.book_s_numb " +
                        "JOIN address_details ON person_details.address_numb = address_details.address_numb " +
                        "WHERE book_name = '%s' ;", bookNameToSearch);
                try {
                    ResultSet resultSet = stmt.executeQuery(sqlStatement);
                    while (resultSet.next()) {
                        String book_name = resultSet.getString("book_name");
                        String first_name = resultSet.getString("first_name");
                        String last_name = resultSet.getString("last_name");
                        String phone = resultSet.getString("phone");
                        String city = resultSet.getString("city");
                        String state = resultSet.getString("state");
                        int zip = resultSet.getInt("zip");
                        System.out.println(book_name + ", " + first_name + ", " + last_name + ", " + phone
                                + ", " + city + ", " + state + ", " + zip);
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case SEARCH_BY_PERSONNAME_JDBC:
                System.out.print("Enter the name of Person - ");
                String nameToSearch = input.next();
                String statementTwo = String.format("SELECT book_name,first_name,last_name,phone,email,city,state,zip FROM person_details " +
                        "JOIN book_type ON person_details.book_s_numb = book_type.book_s_numb " +
                        "JOIN address_details ON person_details.address_numb = address_details.address_numb " +
                        "WHERE first_name = '%s' ;", nameToSearch);
                try {
                    ResultSet setForName = stmt.executeQuery(statementTwo);
                    while (setForName.next()) {
                        String book_name = setForName.getString("book_name");
                        String first_name = setForName.getString("first_name");
                        String last_name = setForName.getString("last_name");
                        String phone = setForName.getString("phone");
                        String email = setForName.getString("email");
                        String city = setForName.getString("city");
                        String state = setForName.getString("state");
                        int zip = setForName.getInt("zip");
                        System.out.println(book_name + ", " + first_name + ", " + last_name + ", " + phone
                                + ", " + email + ", " + city + ", " + state + ", " + zip);
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
        }
    }

    public void toInsertFromJSON(Connection connection) throws SQLException, IOException {
        String path = "C:\\Users\\USER\\IdeaProjects\\AddressBookUsingJDBC\\src\\main\\resources\\PersonData.json";
        Path filepath = Paths.get(path);

        Reader readerObject = Files.newBufferedReader(filepath);
        Gson gson = new Gson();
        AddressData[] objectJsonArray = gson.fromJson(readerObject, AddressData[].class);
        List<AddressData> jsonList = Arrays.asList(objectJsonArray);

        for (int i = 0; i < jsonList.size(); i++) {
            String first_name = jsonList.get(i).first_name;
            String last_name = jsonList.get(i).last_name;
            String phone = jsonList.get(i).phone;
            String email = jsonList.get(i).email;
            String book_name = jsonList.get(i).book_name;
            String city = jsonList.get(i).city;
            String state = jsonList.get(i).state;
            int zip = jsonList.get(i).zip;

            String JSONToBookType = "INSERT INTO book_type (book_name) VALUE (?) ;";
            String JSONToDBAddress = "INSERT INTO address_details (city,state,zip) VALUES (?,?,?) ;";
            String JSONToDBContact = "INSERT INTO person_details (first_name,last_name,phone,email,book_s_numb,address_numb) VALUES (?,?,?,?,?,?) ";
            PreparedStatement statementBookTable = connection.prepareStatement(JSONToBookType);
            PreparedStatement statementAddressTable = connection.prepareStatement(JSONToDBAddress);
            PreparedStatement statementContactTable = connection.prepareStatement(JSONToDBContact);

            statementBookTable.setString(1, book_name);
            statementBookTable.execute();

            String compareBook = String.format("SELECT book_s_numb FROM book_type WHERE book_name = '%s';", book_name);
            ResultSet result = statementAddressTable.executeQuery(compareBook);
            result.next();
            int book_s_numb = (result.getInt("book_s_numb"));

            statementAddressTable.setString(1, city);
            statementAddressTable.setString(2, state);
            statementAddressTable.setInt(3, zip);
            statementAddressTable.execute();

            String check = String.format("SELECT address_numb FROM address_details WHERE city = '%s';", city);
            ResultSet resultTwo = statementAddressTable.executeQuery(check);
            resultTwo.next();
            int address_numb = (resultTwo.getInt("address_numb"));

            statementContactTable.setString(1, first_name);
            statementContactTable.setString(2, last_name);
            statementContactTable.setString(3, phone);
            statementContactTable.setString(4, email);
            statementContactTable.setInt(5, book_s_numb);
            statementContactTable.setInt(6, address_numb);
            statementContactTable.execute();

        }
        System.out.println("JSON Data has been inserted successfully");
    }
}
