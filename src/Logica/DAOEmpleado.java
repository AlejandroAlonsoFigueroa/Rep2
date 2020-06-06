/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;
//ACOPLAR AL BOOLEANO

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author alexa
 */
public class DAOEmpleado {

    public boolean borrarEmpleado(String clave) {
        String sql = "delete from empleado where clave = '"+clave+"';";
        
        ConexionPostgres conexion = ConexionPostgres.getConexion();
        boolean res = conexion.execute(sql) ;
        
        if(res)
            return true;
        else
            return false;
        
        
    }

    public boolean IngresarEmpleado(String clv, String nm, String dir, String tel) {
         String sql =  
            "insert into empleado(clave, nombre, direccion, telefono)"
            +" values ('"+clv+"', '"+nm+"', '"+dir+"', '"+tel+"');";  
        
        ConexionPostgres conexion = ConexionPostgres.getConexion();
        boolean res = conexion.execute(sql) ;
        
        if(res)
            return true;
        else
            return false;
        
        
    }
     public boolean ActualizarEmpleado(String clv, String nm, String dir, String tel, String clv2) {
         String sql = 
            "update empleado set clave = '"+clv+"', nombre = '"+nm+"', "
            + "direccion = '"+dir+"', telefono = '"+tel+"' where clave = '"+clv2+"';";
        ConexionPostgres conexion = ConexionPostgres.getConexion();
        boolean res = conexion.execute(sql) ;
        
        if(res)
            return true;
        else
            return false;
        
        
    }
    public String returnDatos() {
        ResultSet rs;
        Statement st = null ;
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
        }catch (SQLException ex){
        }
        
        
        return datos;
    }
     
     
     
     
    
}
