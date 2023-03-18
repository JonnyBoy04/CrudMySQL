module com.example.crudmysql {
    requires javafx.controls;
    requires javafx.fxml;
    requires mysql.connector.java;
    requires java.sql;

    opens com.example.crudmysql to javafx.fxml;
    exports com.example.crudmysql;
}