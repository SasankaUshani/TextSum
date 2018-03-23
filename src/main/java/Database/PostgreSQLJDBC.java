//package Database;
//
//import java.sql.*;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.Statement;
//
//
//public class PostgreSQLJDBC {
//    public static void main( String args[] ) {
//        Connection c = null;
//        Statement stmt = null;
//        try {
//            Class.forName("org.postgresql.Driver");
//            c = DriverManager
//                    .getConnection("jdbc:postgresql://localhost:5000/Test",
//                            "postgres", "9080");
//            System.out.println("Opened database successfully");
//
//            stmt = c.createStatement();
//            String sql = "CREATE TABLE COMPANY " +
//                    "(ID INT PRIMARY KEY     NOT NULL," +
//                    " NAME           TEXT    NOT NULL, " +
//                    " AGE            INT     NOT NULL, " +
//                    " ADDRESS        CHAR(50), " +
//                    " SALARY         REAL)";
//            stmt.executeUpdate(sql);
//
////            insert
////            stmt = c.createStatement();
////            String sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) "
////                    + "VALUES (1, 'Paul', 32, 'California', 20000.00 );";
////            stmt.executeUpdate(sql);
////
////            sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) "
////                    + "VALUES (2, 'Allen', 25, 'Texas', 15000.00 );";
////            stmt.executeUpdate(sql);
////            sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) "
////                    + "VALUES (3, 'Teddy', 23, 'Norway', 20000.00 );";
////            stmt.executeUpdate(sql);
////
////            sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) "
////                    + "VALUES (4, 'Mark', 25, 'Rich-Mond ', 65000.00 );";
////            stmt.executeUpdate(sql);
//
////            select
////            stmt = c.createStatement();
////            ResultSet rs = stmt.executeQuery( "SELECT * FROM COMPANY;" );
////            while ( rs.next() ) {
////                int id = rs.getInt("id");
////                String name = rs.getString("name");
////                int age = rs.getInt("age");
////                String address = rs.getString("address");
////                float salary = rs.getFloat("salary");
////                System.out.println("ID = " + id);
////                System.out.println("NAME = " + name);
////                System.out.println("AGE = " + age);
////                System.out.println("ADDRESS = " + address);
////                System.out.println("SALARY = " + salary);
////                System.out.println();
//
//
//
//            stmt.close();
//            c.close();
//        } catch ( Exception e ) {
//            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
//            System.exit(0);
//        }
//        System.out.println("Records created successfully");
//    }
//}