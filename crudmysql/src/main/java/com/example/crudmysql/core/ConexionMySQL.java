package com.example.crudmysql.core;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionMySQL {
    Connection conn;

    /**
     * El metodo open sirve para conectar con la base de datos a usar, se crea la variable user con nuestro usuario en MySQL
     * y otra con nuestra contraseña, también la url para conectarnos a la base de datos
     * @return
     */
    public Connection open() {
        String user = "us0jarx6jh6daz6w";
        String password = "FRPnQS8it1o0M5z4QPZ2";
        String url = "jdbc:mysql://us0jarx6jh6daz6w:FRPnQS8it1o0M5z4QPZ2@bwjriyhdfyndyprylzdb-mysql.services.clever-cloud.com:3306/bwjriyhdfyndyprylzdb";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            return conn;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * El metodo close cierra la conexion con nuetra base de datos
     */
    public void close() {
        if (conn != null) {
            try{
                conn.close();
            }catch(Exception e){
                e.printStackTrace();
                System.out.println("Exception controlada");
            }
        }
    }
}
