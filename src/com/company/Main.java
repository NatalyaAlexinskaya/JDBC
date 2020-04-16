package com.company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        DatabaseHandler handler = new DatabaseHandler();
        Connection connection = handler.getDbConnection();

        PreparedStatement preparedStatement1 = connection.prepareStatement("select Name, Salary from Employees where Department = 'Отдел делопроизводства' order by Salary");
        ResultSet resultSet1 = preparedStatement1.executeQuery();
        while (resultSet1.next()) {
            System.out.print(resultSet1.getString("Name") + " - ");
            System.out.println(resultSet1.getString("Salary"));
        }

        System.out.println();

        PreparedStatement preparedStatement2 = connection.prepareStatement("select Name from Employees where Department = 'Отдел сбыта' order by Name");
        ResultSet resultSet2 = preparedStatement2.executeQuery();
        while (resultSet2.next()) {
            System.out.println(resultSet2.getString("Name"));
        }

        System.out.println();

        PreparedStatement preparedStatement3 = connection.prepareStatement("select Name from Employees where Salary > (select Salary from Employees where Name = 'Петров Петр Петрович')");
        ResultSet resultSet3 = preparedStatement3.executeQuery();
        while (resultSet3.next()) {
            System.out.println(resultSet3.getString("Name"));
        }

        System.out.println();

        PreparedStatement preparedStatement4 = connection.prepareStatement("select Salary from Employees where Name = 'Коротков Иван Иванович'");
        ResultSet resultSet4 = preparedStatement4.executeQuery();
        while (resultSet4.next()) {
            System.out.println(resultSet4.getString("Salary"));
        }

        System.out.println();

        PreparedStatement preparedStatement5 = connection.prepareStatement("select Name, Salary from Employees where Department = (select Name from Departments where Manager = 'Иванов Иван Иванович') order by Name");
        ResultSet resultSet5 = preparedStatement5.executeQuery();
        while (resultSet5.next()) {
            System.out.print(resultSet5.getString("Name") + " - ");
            System.out.println(resultSet5.getString("Salary"));
        }
    }
}
