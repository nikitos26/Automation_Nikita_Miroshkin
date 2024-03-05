package lesson_21;

import lombok.SneakyThrows;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Lesson_21 {

    Connection connection;
    Statement statement;

    @SneakyThrows
    @BeforeTest
    public void connectToBase() {
        connection = DriverManager.getConnection("jdbc:mysql://db4free.net/testqa07?user=testqa07&password=testqa07");
        statement = connection.createStatement();
    }

    @Test(priority = 1)
    public void selectAllUsersDataTest() {
        printResult("SELECT * FROM user");
    }

    @Test(priority = 2)
    public void selectTestUsers() {
        Assert.assertEquals(printResult("SELECT * FROM user WHERE first_name LIKE '%est%'").size(), 3);
    }

    @SneakyThrows
    @Test(priority = 3)
    public void setTestUser() {
        statement.executeUpdate("INSERT INTO user (id, last_name, first_name, age) VALUES (101, 'Marley', 'Bob', 36)");
        Assert.assertEquals(printResult("SELECT * FROM user").stream().filter(data -> data.get("id").equals("101")).collect(Collectors.toList()).get(0).get("first_name"), "Bob");
    }

    @SneakyThrows
    @Test(priority = 4)
    public void deleteTestUser() {
        statement.executeUpdate("DELETE FROM user WHERE first_name LIKE 'Bob'");
        Assert.assertEquals(printResult("SELECT * FROM user").stream().filter(data -> data.get("id").equals("101")).collect(Collectors.toList()).size(), 0);
    }

    @SneakyThrows
    @Test(priority = 5)
    public void setUserToEditData() {
        statement.executeUpdate("INSERT INTO user (id, first_name, last_name, age) VALUES (113, 'Karban', 'Dallas', 40)");
        Assert.assertEquals(printResult("SELECT * FROM user").stream().filter(data -> data.get("id").equals("113")).collect(Collectors.toList()).get(0).get("first_name"), "Karban");
        Assert.assertEquals(printResult("SELECT * FROM user").stream().filter(data -> data.get("id").equals("113")).collect(Collectors.toList()).get(0).get("last_name"), "Dallas");
    }

    @SneakyThrows
    @Test(priority = 6)
    public void editUserName() {
        statement.executeUpdate("UPDATE user SET first_name='Mickey' WHERE id=113");
        Assert.assertEquals(printResult("SELECT * FROM user").stream().filter(data -> data.get("id").equals("113")).collect(Collectors.toList()).get(0).get("first_name"), "Mickey");
    }

    @SneakyThrows
    @Test(priority = 7)
    public void editUserLastName() {
        statement.executeUpdate("UPDATE user SET last_name='Mouse' WHERE id=113");
        Assert.assertEquals(printResult("SELECT * FROM user").stream().filter(data -> data.get("id").equals("113")).collect(Collectors.toList()).get(0).get("last_name"), "Mouse");
    }

    @SneakyThrows
    @Test(priority = 8)
    public void deleteUpdatedUser() {
        statement.executeUpdate("DELETE FROM user WHERE first_name LIKE 'Mickey'");
        Assert.assertEquals(printResult("SELECT * FROM user").stream().filter(data -> data.get("id").equals("113")).collect(Collectors.toList()).size(), 0);
    }


    @SneakyThrows
    @AfterClass
    public void closeConnection() {
        statement.close();
    }

    @SneakyThrows
    public List<Map<String, String>> printResult(String query) {
        ResultSet resultSet = statement.executeQuery(query);
        ResultSetMetaData data = resultSet.getMetaData();
        List<Map<String, String>> table = new ArrayList<>();
        while (resultSet.next()) {
            Map<String, String> result = new HashMap<>();
            for (int i = 1; i <= data.getColumnCount(); i++) {
                result.put(data.getColumnName(i), resultSet.getString(i));
            }
            table.add(result);
        }

        System.out.println(query);
        table.forEach(System.out::println);
        System.out.println();
        return table;
    }
}
