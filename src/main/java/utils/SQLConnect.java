package utils;

import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class SQLConnect {

    @SneakyThrows
    public static void main(String[] args) {

        Connection activeConnection = connection();

        Statement statement = activeConnection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from Album");


        while (resultSet.next()) {
            System.out.println("Artist ID:" + resultSet.getInt("ArtistId"));
            System.out.println("Title:" + resultSet.getString("Title"));
            System.out.println("");
        }
        activeConnection.close();
    }

    @SneakyThrows
    public static Connection connection() {
        Connection connection = null;
        String dbUrl = "jdbc:sqlite:/Users/nmilka/Library/DBeaverData/workspace6/.metadata/sample-database-sqlite-1/Chinook.db";
        return connection = DriverManager.getConnection(dbUrl);
    }
}
