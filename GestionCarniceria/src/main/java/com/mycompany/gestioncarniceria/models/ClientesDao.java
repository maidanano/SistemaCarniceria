package com.mycompany.gestioncarniceria.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

public class ClientesDao {

    //Instanciar la conexión
    ConexionMySQL cn = new ConexionMySQL();
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;

    //Registrar cliente
    public boolean resgistrarCustumerQuery(Clientes cliente) {
        String query = "INSERT INTO clientes (id, cliente, direccion, telefono, correo, provincia, localidad, codigoPostal, razonSocial, cuit, eliminado) VALUES(?,?,?,?,?,?,?,?,?,?,?)";

        Timestamp datetime = new Timestamp(new Date().getTime());
        try {
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            pst.setInt(1, cliente.getId());
            pst.setString(2, cliente.getApellidoNombre());
            pst.setString(3, cliente.getDireccion());
            pst.setString(4, cliente.getTelefono());
            pst.setString(5, cliente.getEmail());
            pst.setString(6, cliente.getProvincia());
            pst.setString(7, cliente.getLocalidad());
            pst.setString(8, cliente.getCodigoPostal());
            pst.setString(9, cliente.getRazonSocial());
            pst.setString(10, cliente.getCuit());
            pst.setBoolean(11, false);
            pst.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar al cliente ");
            return false;
        }
    }

    //Listar Clienete
    public List listarClienteQuery(String value) {
        List<Clientes> listaClientes = new ArrayList();
        String query = "SELECT * FROM clientes WHERE eliminado = FALSE";
        String queryBuscarCliente = "SELECT * FROM clientes WHERE eliminado = FALSE AND LOWER (cliente) LIKE ?";

        try {
            conn = cn.getConnection();
            if (value.equalsIgnoreCase("")) {
                pst = conn.prepareStatement(query);
                rs = pst.executeQuery();
            } else {
                pst = conn.prepareStatement(queryBuscarCliente);
                pst.setString(1, "%" + value.toLowerCase() + "%");
            }
            rs = pst.executeQuery();

            while (rs.next()) {
                Clientes cliente = new Clientes();
                cliente.setId(rs.getInt("id"));
                cliente.setApellidoNombre(rs.getString("cliente"));
                cliente.setDireccion(rs.getString("direccion"));;
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setEmail(rs.getString("correo"));
                cliente.setProvincia(rs.getString("provincia"));
                cliente.setLocalidad(rs.getString("localidad"));
                cliente.setCodigoPostal(rs.getString("codigoPostal"));
                cliente.setRazonSocial(rs.getString("razonSocial"));
                cliente.setCuit(rs.getString("cuit"));
                listaClientes.add(cliente);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return listaClientes;
    }

    //Modificar Cliente
    public boolean modificarClienteQuery(Clientes cliente) {
        String query = "UPDATE clientes SET cliente = ?, direccion = ?, telefono = ?, correo = ?, provincia = ?, localidad = ?, codigoPostal = ?, razonSocial = ?, cuit = ? WHERE id = ?";

        try {
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            pst.setString(1, cliente.getApellidoNombre());
            pst.setString(2, cliente.getDireccion());
            pst.setString(3, cliente.getTelefono());
            pst.setString(4, cliente.getEmail());
            pst.setString(5, cliente.getProvincia());
            pst.setString(6, cliente.getLocalidad());
            pst.setString(7, cliente.getCodigoPostal());
            pst.setString(8, cliente.getRazonSocial());
            pst.setString(9, cliente.getCuit());
            pst.setInt(10, cliente.getId());
            pst.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al modificar los datos del cliente ");
            return false;
        }
    }

    //Eliminar Cliente
    public boolean eliminarClienteQuery(int id) {
        Connection con = cn.getConnection();
        String sql = "UPDATE clientes SET eliminado = TRUE WHERE id = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar cliente lógicamente: " + e.getMessage());
            return false;
        }
    }

    //Buscar Cliente
    public Clientes buscarClienteQueryPorId(int id) {
        Clientes cliente = new Clientes();
        String query = "SELECT * FROM clientes WHERE id = ? AND eliminado = FALSE";
        try (Connection conn = cn.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                cliente.setId(rs.getInt("id"));
                cliente.setApellidoNombre(rs.getString("cliente"));
                cliente.setDireccion(rs.getString("direccion"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setEmail(rs.getString("correo"));
                cliente.setProvincia(rs.getString("provincia"));
                cliente.setLocalidad(rs.getString("localidad"));
                cliente.setCodigoPostal(rs.getString("codigoPostal"));
                cliente.setRazonSocial(rs.getString("razonSocial"));
                cliente.setCuit(rs.getString("cuit"));
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar cliente por ID: " + e.getMessage());
        }
        return cliente;
    }

}
