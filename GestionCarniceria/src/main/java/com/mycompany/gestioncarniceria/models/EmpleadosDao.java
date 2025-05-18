package com.mycompany.gestioncarniceria.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class EmpleadosDao {

    //Instanciar la conexión
    ConexionMySQL cn = new ConexionMySQL();
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;

    //Variables para enviar datos entre interfaces
    public static int idUsuario = 0;
    public static String apellidoNombreUsuario = "";
    public static String nombreUsuario = "";
    public static String direccionUsuario = "";
    public static String rolUsuario = "";
    public static String emailUsuario = "";
    public static String telefonoUsuario = "";

    //Método de Login
    public Empleados loginQuery(String usuario, String contraseña) {
        String query = "SELECT * FROM empleados WHERE nombre_usuario = ? AND contraseña = ?";
        Empleados empleado = new Empleados();
        try {
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);

            //Enviar parámetros
            pst.setString(1, usuario);
            pst.setString(2, contraseña);
            rs = pst.executeQuery();

            //Guardar la informacion de usuario
            if (rs.next()) {
                empleado.setId(rs.getInt("id"));
                idUsuario = empleado.getId();
                empleado.setApellidoNombre(rs.getString("apellido_nombre"));
                apellidoNombreUsuario = empleado.getApellidoNombre();
                empleado.setNombreUsuario(rs.getString("nombre_usuario"));
                nombreUsuario = empleado.getNombreUsuario();
                empleado.setDireccion(rs.getString("direccion"));
                direccionUsuario = empleado.getDireccion();
                empleado.setTelefono(rs.getString("telefono"));
                telefonoUsuario = empleado.getTelefono();
                empleado.setEmail(rs.getString("correo"));
                emailUsuario = empleado.getEmail();
                empleado.setRol(rs.getString("rol"));
                rolUsuario = empleado.getRol();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener al empleado " + e);
        }
        return empleado;
    }

    //Registrar empleado
    public boolean registrarEmpleadoQuery(Empleados empleado) {
        String query = "INSERT INTO empleados (id, apellido_nombre, direccion, telefono, correo, rol, contraseña, nombre_usuario) VALUES(?,?,?,?,?,?,?,?)";

        try {
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            pst.setInt(1, empleado.getId());
            pst.setString(2, empleado.getApellidoNombre());
            pst.setString(3, empleado.getDireccion());
            pst.setString(4, empleado.getTelefono());
            pst.setString(5, empleado.getEmail());
            pst.setString(6, empleado.getRol());
            pst.setString(7, empleado.getContraseña());
            pst.setString(8, empleado.getNombreUsuario());
            pst.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar al empleado ");
            return false;
        }
    }

    //Listar empleado
    public List listaEmpleadoQuery(String value) {
        List<Empleados> listaEmpleados = new ArrayList();
        String query = "SELECT * FROM empleados";
        String queryBuscarEmpleado = "SELECT * FROM empleados WHERE id LIKE '%" + value + "%'";

        try {
            conn = cn.getConnection();
            if (value.equalsIgnoreCase("")) {
                pst = conn.prepareStatement(query);
                rs = pst.executeQuery();
            } else {
                pst = conn.prepareStatement(queryBuscarEmpleado);
                rs = pst.executeQuery();
            }

            while (rs.next()) {
                Empleados empleado = new Empleados();
                empleado.setId(rs.getInt("id"));
                empleado.setApellidoNombre(rs.getString("apellido_nombre"));
                empleado.setNombreUsuario(rs.getString("nombre_usuario"));
                empleado.setRol(rs.getString("rol"));
                empleado.setTelefono(rs.getString("telefono"));
                empleado.setEmail(rs.getString("correo"));
                empleado.setDireccion(rs.getString("direccion"));;
                listaEmpleados.add(empleado);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return listaEmpleados;
    }

    //Modificar empleado
    public boolean modificarEmpleadoQuery(Empleados empleado) {
        String query = "UPDATE empleados SET apellido_nombre = ?, direccion = ?, telefono = ?, correo = ?, rol = ?, contraseña = ?, nombre_usuario = ? WHERE id = ?";

        try {
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            pst.setString(1, empleado.getApellidoNombre());
            pst.setString(2, empleado.getDireccion());
            pst.setString(3, empleado.getTelefono());
            pst.setString(4, empleado.getEmail());
            pst.setString(5, empleado.getRol());
            pst.setString(6, empleado.getContraseña());
            pst.setString(7, empleado.getNombreUsuario());
            pst.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al modificar los datos del empleado ");
            return false;
        }
    }

    //Eliminar empleado
    public boolean eliminarEmpleadoQuery(int id) {
        String query = "DELETE FROM empleados WHERE id = " + id;

        try {
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            pst.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No puede eliminar a un empleado que tenga relacion con otra tabla");
            return false;
        }
    }

    //Cambiar la contraseña
    public boolean modificarContraseñaEmpleado(Empleados empleado) {
        String query = "UPDATE empleados SET contraseña = ? WHERE nombre_usuario ='" + nombreUsuario + "'";
        try {
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            pst.setString(1, empleado.getContraseña());
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al modificar la contraseña " + e);
            return false;
        }
    }

}
