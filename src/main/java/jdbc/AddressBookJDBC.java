package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import static Constants.Constants.*;

public class AddressBookJDBC {
    public static void main(String[] args) throws SQLException {

        Connection connection = DriverManager.getConnection(OS.url, OS.user, OS.password);
        Statement statement = connection.createStatement();

        Scanner input = new Scanner(System.in);
        CRUDOperations crudOperations = new CRUDOperations();
        BasicQueries queryObject = new BasicQueries();
        queryObject.queries(statement);
        System.out.println("Queries executed successfully....");

        int choice = 0;
        while (choice != 6) {
            System.out.println("What do you want to Perform");
            System.out.print("1.Create/Insert, 2.Read/Count, 3.Update, 4.Delete, 5.Search, 6.Stop  - ");
            choice = input.nextInt();
            try {
                switch (choice) {
                    case TO_INSERT_CONTACT_JDBC:
                        crudOperations.toInsertFromConsole(statement);
                        break;
                    case TO_PRINT_CONTACT_JDBC:
                        crudOperations.toCountData(statement);
                        break;
                    case TO_UPDATE_CONTACT_JDBC:
                        crudOperations.toUpdateData(statement);
                        break;
                    case TO_DELETE_CONTACT_JDBC:
                        crudOperations.toDeleteData(statement);
                        break;
                    case TO_SEARCH_CONTACT_JDBC:
                        crudOperations.toSearch(statement);
                        break;
                    case TO_STOP_JDBC:
                        queryObject.toDelete(statement);
                        break;

                    default:
                        throw new CustomException("Invalid Input Please Try Again");
                }
            } catch (CustomException e) {
                System.out.println(e.getMessage());
            }
        }
        connection.close();

    }
}