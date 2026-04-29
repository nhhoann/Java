/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Assignmentjava2;

/**
 *
 * @author Acer
 */
import java.sql.Connection;
import java.sql.DriverManager;

public class DBContext {
    public static Connection getConnection() throws Exception {
        // Thay đổi ServerName và DatabaseName cho đúng máy bạn
        // Ví dụ nếu tên DB của bạn trong SQL Server là QuanLyThuVien:
        String url = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyThuVien;encrypt=false;trustServerCertificate=true";
        String user = "sa"; // Tài khoản SQL Server
        String pass = "123"; // Mật khẩu
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection(url, user, pass);
    }
} 