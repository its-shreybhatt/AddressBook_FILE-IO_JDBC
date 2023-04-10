package jdbc;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class BasicQueries {

    public void queries(Statement statement) throws SQLException {
        String firstQuery = "CREATE DATABASE address_book_JDBC";
        String secondQuery = "USE address_book_JDBC";

        String thirdQuery = "CREATE TABLE book_type" +
                "(book_s_numb INT unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                "book_name VARCHAR(30) NOT NULL)";

        String fourthQuery = "CREATE TABLE address_details" +
                "(address_numb INT unsigned NOT NULL auto_increment PRIMARY KEY, " +
                "city VARCHAR(20) NOT NULL, state VARCHAR(20) NOT NULL, zip INT(6) NOT NULL)";

        String fifthQuery = "CREATE TABLE person_details " +
                "(person_sr_numb INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                "first_name VARCHAR(20) NOT NULL, last_name VARCHAR(20) NOT NULL, " +
                "phone VARCHAR(15) NOT NULL, email VARCHAR(100) NOT NULL, " +
                "book_s_numb int UNSIGNED, address_numb int UNSIGNED, " +
                "FOREIGN KEY(book_s_numb) REFERENCES book_type(book_s_numb), " +
                "FOREIGN KEY(address_numb) REFERENCES address_details(address_numb))";

        statement.execute(firstQuery);
        statement.execute(secondQuery);
        statement.execute(thirdQuery);
        statement.execute(fourthQuery);
        statement.execute(fifthQuery);
    }

    public void toDelete(Statement statement) throws SQLException {
        System.out.print("Do you want to delete the Table (Yes/No) - ");
        String choice = new Scanner(System.in).next();

        if (choice.equalsIgnoreCase("yes")) {
            String finalQueryFive = "DROP DATABASE address_book_JDBC";
            statement.execute(finalQueryFive);
            System.out.println("Tables and DB deleted successfully....");
        }
    }
}
