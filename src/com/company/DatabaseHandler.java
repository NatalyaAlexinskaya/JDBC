package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseHandler {
    private Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://localhost:3306/db?useUnicode=true&serverTimezone=UTC";

        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, "root", "0000");

        return dbConnection;
    }

    private void createTables() {
        try {
            Statement statement = dbConnection.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Departments (Name VARCHAR(50) not null, Manager VARCHAR(50) not null);");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Employees (Name VARCHAR(50) not null, Salary int(10) not null, Department VARCHAR(50) not null);");
            statement.executeUpdate("INSERT INTO Departments (Name, Manager) values ('Отдел делопроизводства', 'Иванов Иван Иванович'), " +
                    "('Отдел сбыта', 'Смирнов Роман Романович'), " +
                    "('Финансовый отдел', 'Сидоров Василий Васильевич') ");
            statement.executeUpdate("INSERT INTO Employees (Name, Salary, Department) values ('Шилов Алексей Сергеевич', 23000, 'Отдел делопроизводства')," +
                    "('Котов Николай Алексеевич', 21000, 'Отдел сбыта'), " +
                    "('Мышкин Сергей Иванович', 32000, 'Отдел делопроизводства'), " +
                    "('Федосеев Александр Викторович', 27000, 'Финансовый отдел'), " +
                    "('Зинин Дмитрий Петрович', 29000, 'Отдел сбыта'), " +
                    "('Копылов Федор Антонович', 30000, 'Отдел сбыта'), " +
                    "('Петров Петр Петрович', 25000, 'Отдел делопроизводства'), " +
                    "('Коротков Иван Иванович', 38000, 'Финансовый отдел')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
