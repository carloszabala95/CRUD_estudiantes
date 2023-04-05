
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EstudianteDao extends Conexion {
    private static final String SQL_SELECT = 
            "SELECT id_estudiante, nombre, apellido, email, telefono, edad FROM estudiante";
    private static final String SQL_SELECT_BY_ID =
            "SELECT id_estudiante, nombre, apellido, email, telefono, edad FROM estudiante WHERE id_estudiante = ? ";
    private static final String SQL_INSERT =
            "INSERT INTO estudiante (nombre, apellido, email, telefono, edad) VALUES (?,?,?,?,?)";           
    private static final String SQL_UPDATE =
            "UPDATE estudiante SET nombre=?, apellido=?, email=?, telefono=?, edad=? WHERE id_estudiante =?";
    private static final String SQL_DELETE =
            "DELETE FROM estudiante WHERE id_estudiante=?";
    
    
    
    public List<Estudiante> Listar(){
                
        List<Estudiante> estudiantes = new ArrayList<>();
        
        try {
            Connection con = Conexion.getConnection();
            PreparedStatement st = con.prepareStatement(SQL_SELECT);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                int idEstudiante = rs.getInt("id_estudiante");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String telefono = rs.getString("telefono");
                String email = rs.getString("email");
                double edad = rs.getDouble("edad");
                
                Estudiante estudiante = new Estudiante(idEstudiante,nombre,apellido,telefono,email,edad);
                estudiantes.add(estudiante);
            }
        } catch (Exception e) {
            System.out.println("Error al listar los estudiantes: "+e.getMessage());
        }
        
        return estudiantes;
    }
    
    
}
