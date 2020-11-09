package ru.netology.data;

import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import ru.netology.entities.CreditRequestEntity;
import ru.netology.entities.PaymentEntity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLUtils {
    private static Connection getConnection() {
        String url = System.getProperty("url");
        String username = System.getProperty("username");
        String password = System.getProperty("password");
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static void cleanTables() {
        String deleteCreditRequest = "DELETE FROM credit_request_entity; ";
        String deleteOrderEntity = "DELETE FROM order_entity; ";
        String deletePaymentEntity = "DELETE FROM payment_entity; ";
        try (val conn = SQLUtils.getConnection();
             val deleteCreditRequestStmt = conn.createStatement();
             val deleteOrderEntityStmt = conn.createStatement();
             val deletePaymentEntityStmt = conn.createStatement()
        ) {
            deleteCreditRequestStmt.executeUpdate(deleteCreditRequest);
            deleteOrderEntityStmt.executeUpdate(deleteOrderEntity);
            deletePaymentEntityStmt.executeUpdate(deletePaymentEntity);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static String getPaymentStatus()  {
        val status = "SELECT * FROM payment_entity order by created desc limit 1;";
        val runner = new QueryRunner();
        try (val conn = getConnection()) {
            val paymentStatus = runner.query(conn, status, new BeanHandler<>(PaymentEntity.class));
            return paymentStatus.getStatus();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static String getCreditRequestStatus()  {
        val status = "SELECT * FROM credit_request_entity order by created desc limit 1;";
        val runner = new QueryRunner();
        try (val conn = getConnection()) {
            val creditRequestStatus = runner.query(conn, status, new BeanHandler<>(CreditRequestEntity.class));
            return creditRequestStatus.getStatus();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
    public static String getPaymentAmount()  {
        val amount = "SELECT * FROM payment_entity order by created desc limit 1;";
        val runner = new QueryRunner();
        try (val conn = getConnection()) {
            val paymentAmount = runner.query(conn, amount, new BeanHandler<>(PaymentEntity.class));
            return paymentAmount.getAmount();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

}