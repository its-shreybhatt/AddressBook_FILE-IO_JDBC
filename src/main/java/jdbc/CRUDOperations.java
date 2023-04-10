package jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

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
}
