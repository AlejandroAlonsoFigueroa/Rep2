/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;
import com.sun.istack.internal.logging.Logger;
import java.sql.*;

/**
 *
 * @author alexa
 */
public class ConexionPostgres {
    //Variable pa usar DriverManager.getConnection
    public  Connection con = null;
    //Variable para crear la sentencia
    private Statement st;
    //Variable para obtener lo que retorne una consulta
    private ResultSet rs;
    
    String URL ;
    String usuario ;
    String contraseña;
    //Para cargar el .Jar
    String driver ;
    
    //Objeto de tipo ConexionPostgres, para poder obtener objeto de tipo ConexionPostgres
    private static  ConexionPostgres conexion = null;
    
    private ConexionPostgres() {
        URL = "jdbc:postgresql://localhost:5432/empleados";
        usuario = "postgres";
        contraseña = "ajxy2381";
        driver = "org.postgresql.Driver";
        try{
            Class.forName(driver);
            con = DriverManager.getConnection(URL,usuario,contraseña);
            st = con.createStatement();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
 
    public static ConexionPostgres getConexion(){
        if (conexion == null) {
            conexion = new ConexionPostgres();
        }
        return conexion;
    }
    
    public boolean execute(String sql) {
        boolean res = false;
       
        
        try{
            st = con.createStatement();
            st.execute(sql);
            res = true;
        }catch(SQLException ex){
            
        }
        return res;
    }
    
    

    
    public String returnDatos() {
        String datos = "";
        try{
            ConexionPostgres conexion = ConexionPostgres.getConexion();
            String consulta = "select * from empleado;";
            rs = st.executeQuery(consulta);
            while(rs.next()){
                String clave, nombre, direccion, telefono;
                clave = rs.getString(1);
                nombre = rs.getString(2);
                direccion = rs.getString(3);
                telefono = rs.getString(4);
                datos = datos + "\nClave: "+clave+"\nNombre: "+nombre+"\nDireccion: "+direccion+
                        "\nTeléfono: "+telefono;
            }
        }catch (Exception ex){
            ex.printStackTrace();
      
        }
        
        
        return datos;
    }
  
    
    
}

