package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EstudianteDao extends Conexion { //Peticiones a SQL 

    private static final String SQL_SELECT
            = "SELECT id_estudiante, nombre, apellido, email, telefono, edad FROM estudiante";
    private static final String SQL_SELECT_BY_ID
            = "SELECT id_estudiante, nombre, apellido, email, telefono, edad FROM estudiante WHERE id_estudiante = ? ";
    private static final String SQL_INSERT
            = "INSERT INTO estudiante (nombre, apellido, email, telefono, edad) VALUES (?,?,?,?,?)";
    private static final String SQL_UPDATE
            = "UPDATE estudiante SET nombre=?, apellido=?, email=?, telefono=?, edad=? WHERE id_estudiante =?";
    private static final String SQL_DELETE
            = "DELETE FROM estudiante WHERE id_estudiante=?";

    public List<Estudiante> Listar() { //Creo lista de obj "estudiantes" 

        List<Estudiante> estudiantes = new ArrayList<>();

        try {
            Connection con = Conexion.getConnection();
            PreparedStatement st = con.prepareStatement(SQL_SELECT);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int idEstudiante = rs.getInt("id_estudiante");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String telefono = rs.getString("telefono");
                String email = rs.getString("email");
                double edad = rs.getDouble("edad");

                Estudiante estudiante = new Estudiante(idEstudiante, nombre, apellido, telefono, email, edad);
                estudiantes.add(estudiante);
            }
        } catch (Exception e) {
            System.out.println("Error al listar los estudiantes: " + e.getMessage());
        }

        return estudiantes;
    }

    public Estudiante buscar(Estudiante estudiante) {
        try {
            Connection con = Conexion.getConnection();
            PreparedStatement st = con.prepareStatement(SQL_SELECT_BY_ID);
            st.setInt(1, estudiante.getIdEstudiante());
            try {
                ResultSet rs = st.executeQuery();
                if (rs.next()) {
                    String nombre = rs.getString("nombre");
                    String apellido = rs.getString("apellido");
                    String telefono = rs.getString("telefono");
                    String email = rs.getString("email");
                    Double edad = rs.getDouble("edad");

                    estudiante.setNombre(nombre);
                    estudiante.setApellido(apellido);
                    estudiante.setEmail(email);
                    estudiante.setTelefono(telefono);
                    estudiante.setEdad(edad);
                }
            } catch (Exception e) {
                System.out.println("Error con id" + e);
            }
        } catch (Exception e) {
            System.out.println("Error buscando" + e);
        }
        return estudiante;
    }

    public int insertar(Estudiante estudiante) {
        int row = 0;
        try {
            Connection con = Conexion.getConnection();
            PreparedStatement st = con.prepareStatement(SQL_INSERT);
            st.setString(1, estudiante.getNombre());
            st.setString(2, estudiante.getApellido());
            st.setString(3, estudiante.getEmail());
            st.setString(4, estudiante.getTelefono());
            st.setDouble(5, estudiante.getEdad());
            
            row = st.executeUpdate(); //si es valido row = 1, executeUpdate da 1 o 0 , se pudo o no.
            
        } catch (Exception e) {
            System.out.println("Error al insertar" + e);
        }

        return row;
    }
    
    public int actualizar(Estudiante estudiante){
        int row = 0;
        
        try {
            Connection con = Conexion.getConnection();
            PreparedStatement st = con.prepareStatement(SQL_UPDATE);
            st.setString(1, estudiante.getNombre());
            st.setString(2, estudiante.getApellido());
            st.setString(3, estudiante.getEmail());
            st.setString(4, estudiante.getTelefono());
            st.setDouble(5, estudiante.getEdad());
            st.setInt(6, estudiante.getIdEstudiante());
            
            row = st.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error al actualizar"+e);
            
        }
        
        return row;
    }
    
    public int eliminar(Estudiante estudiante){
        int row = 0;        
        try {
        Connection con = Conexion.getConnection();
        PreparedStatement st = con.prepareStatement(SQL_DELETE);
        st.setInt(1, estudiante.getIdEstudiante());   
        row = st.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error al borrar");
        }
        return row = 0;
    
    }

}
