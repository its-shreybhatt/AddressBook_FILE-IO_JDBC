package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class AddressBookJDBC {
    public static void main(String[] args) throws SQLException {

        Connection connection = DriverManager.getConnection(OS.url, OS.user, OS.password);
    }

}
