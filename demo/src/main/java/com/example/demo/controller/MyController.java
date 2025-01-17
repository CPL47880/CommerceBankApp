package com.example.demo.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.sql.*;

@Controller
public class MyController {
    Statement statement;

    public MyController() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/javabook", "root", "root");
        System.out.println("Database connected");
        statement = connection.createStatement();
    }
    @GetMapping("hello") //@GetMapping refers to URL of the web application
    public String helloJunit(Model model) {
        model.addAttribute("data","Hello World 123");
        return "hello"; //return refers to the HTML file located in templates
    }

    @GetMapping("login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("checkLastName")
    public String checkLastName(Model model) {
        return "checkLastName";
    }

    @GetMapping("GetFullName")
    public String GetFullName(Model model, @ModelAttribute("lastname") String lastName, @ModelAttribute("ssn") String ssn) throws SQLException {
            System.out.println(lastName);
            System.out.println(ssn);

            ResultSet resultSet=statement.executeQuery("select firstName, mi, lastName from Student where lastName= '" + lastName + "'" + " and ssn = '" + ssn + "'");

            String output = "";

            while (resultSet.next()) {
                String firstName = resultSet.getString("firstName");
                String midName = resultSet.getString("mi");
                String lName = resultSet.getString("lastName");

                System.out.println(firstName + " " + midName + " " + lName);
                output+=firstName + " " + midName + " " + lName + "<br>";
            }
            model.addAttribute("data1", output);

        return "checkResult";
    }
}

