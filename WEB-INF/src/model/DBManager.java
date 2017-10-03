package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class DBManager implements AutoCloseable {

    private Connection connection;

    public DBManager() throws SQLException, NamingException {
        connect();
    }

    private void connect() throws SQLException, NamingException {
        Context initCtx = new InitialContext();
        Context envCtx = (Context) initCtx.lookup("java:comp/env");
        DataSource ds = (DataSource) envCtx.lookup("jdbc/exercise");
        connection = ds.getConnection();
    }

    /**
     * Close the connection to the database if it is still open.
     *
     */
    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
        connection = null;
    }



    public boolean registerContact( String firstName, String lastname, String adress, String email,String phone) throws SQLException {
        boolean success = false;
        connection.setAutoCommit(false);
        String q1 = "INSERT INTO contact (firstName, lastname, adress, email, phone) "
                    + "VALUES (?, ?, ?, ?, ?)";

        if (existeContact(email)) {
            return false;
        }
        
        try (PreparedStatement stmt = connection.prepareStatement(q1)) {
            stmt.setString(1, firstName);
            stmt.setString(2, lastname);
            stmt.setString(3, adress);
            stmt.setString(4, email);
            stmt.setString(5, phone);

            int numRows = stmt.executeUpdate();
            if (numRows > 0) {
                success = true;
            }
        } finally {
            if (success) {
                connection.commit();
            } else {
                connection.rollback();
            }
            connection.setAutoCommit(true);
        }
        return success;
    }

     public boolean registerContact( contact contact) throws SQLException {
        boolean success = false;
        connection.setAutoCommit(false);
        String q1 = "INSERT INTO contact (firstName, lastname, adress, email, phone) "
                    + "VALUES (?, ?, ?, ?, ?)";

        if (existeContact(contact)) {
            return false;
        }
        
        try (PreparedStatement stmt = connection.prepareStatement(q1)) {
            stmt.setString(1, contact.getFirstName());
            stmt.setString(2, contact.getLastName());
            stmt.setString(3, contact.getAdress());
            stmt.setString(4, contact.getMail());
            stmt.setString(5, contact.getNumber());

            int numRows = stmt.executeUpdate();
            if (numRows > 0) {
                success = true;
            }
        } finally {
            if (success) {
                connection.commit();
            } else {
                connection.rollback();
            }
            connection.setAutoCommit(true);
        }
        return success;
    }

    public boolean existeContact(contact contact) throws SQLException {
        boolean existe = false;
        String q = "SELECT * FROM contact WHERE email = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(q)) {
            stmt.setString(1, contact.getMail());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                existe = true;
            }
            return existe;
        }
    }

public boolean existeContact(String email) throws SQLException {
        boolean existe = false;
        String q = "SELECT * FROM contact WHERE email = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(q)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                existe = true;
            }
            return existe;
        }
    }

   
}

  
