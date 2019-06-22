/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tareastestjdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import jdbc.ConnectionFactory;

/**
 *
 * @author ander
 */
public class TareasTestJDBC {

    //Objetos de conexión:
    static Connection cn = new ConnectionFactory().getConnection();
    static Scanner leer = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        //VAriable que almacena la sentencia...
        String sql = "select * from tareas";

        //Crear un obj PS
        PreparedStatement ps = cn.prepareStatement(sql);

        //Crear un obj RS
        ResultSet rs = ps.executeQuery();

        System.out.println("Conectado a la Base de Datos: " + cn.getCatalog());

        //Recorrer el RS y mostrar los datos...
        while (rs.next()) {
            //Imprimir los datos...
            System.out.println(
                    "Tarea " + rs.getString("id") + " || "
                    + rs.getString("descri") + " || "
                    + sdf.format(Date.valueOf(rs.getString("fec"))) + " || "
                    + (rs.getString("fin").equals("1") ? "FInalizada" : "En espera")
            );
        }

        System.out.println("\nPulse 1 para ALTAS; Pulse 2 para BAJAS; Pulse 3 para MODIFICACIONES");
        int opc = leer.nextInt();
        
        switch(opc) {
            case 1: 
                altaTareas();
                break;
        }

    }

    static void altaTareas() {
        leer = new Scanner(System.in);
        //Datos a leer...
        String descri, fec;

        //Crear consulta INSERT:
        String sql = "insert into tareas (descri, fec) values (?, ?)";

        System.out.println("Ingrese los datos a guardar");
        System.out.println("====================================\n");
        System.out.print("Tarea: ");
        descri = leer.nextLine();
        System.out.println("Fecha (aaaa-mm-dd): ");
        fec = leer.nextLine();

        try {
            PreparedStatement ps = cn.prepareStatement(sql);

            //Enviar los parametros...
            ps.setString(1, descri);
            ps.setDate(2, java.sql.Date.valueOf(fec));
                    

            ps.execute();
            ps.close(); //Cerrar statement...

            System.out.println("Datos guardados correctamente...");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());

        }

    }

}
