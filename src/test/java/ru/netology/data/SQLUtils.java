package ru.netology.data;

import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class SQLUtils {
    public static Connection getConnection() throws SQLException, IOException {
        Properties props = new Properties();
        try (InputStream in = Files.newInputStream(Paths.get("application.properties"))) {
            props.load(in);
        }
        String url = props.getProperty("spring.datasource.url");
        String username = props.getProperty("spring.datasource.username");
        String password = props.getProperty("spring.datasource.password");

        return DriverManager.getConnection(url, username, password);
    }

    public static void cleanTables() throws SQLException, IOException {
        String deleteCreditRequest = "DELETE FROM credit_request_entity; ";
        String deleteOrderEntity = "DELETE FROM order_entity; ";
        String deletePaymentEntity = "DELETE FROM payment_entity; ";
        try (val conn = SQLUtils.getConnection();
             val deleteCreditRequestStmt = conn.createStatement();
             val deleteOrderEntityStmt = conn.createStatement();
             val deletePaymentEntityStmt = conn.createStatement();
        ) {
            deleteCreditRequestStmt.executeUpdate(deleteCreditRequest);
            deleteOrderEntityStmt.executeUpdate(deleteOrderEntity);
            deletePaymentEntityStmt.executeUpdate(deletePaymentEntity);
        }
    }

    public static String getPaymentStatus() throws SQLException, IOException {
        val status = "SELECT * FROM payment_entity order by created desc limit 1;";
        val runner = new QueryRunner();
        try (val conn = getConnection()) {
            val paymentStatus = runner.query(conn, status, new BeanHandler<>(PaymentEntity.class));
            return paymentStatus.getStatus();
        }
    }

    public static String getCreditRequestStatus() throws IOException, SQLException {
        val status = "SELECT * FROM credit_request_entity order by created desc limit 1;";
        val runner = new QueryRunner();
        try (val conn = getConnection()) {
            val creditRequestStatus = runner.query(conn, status, new BeanHandler<>(CreditRequestEntity.class));
            return creditRequestStatus.getStatus();
        }
    }
}