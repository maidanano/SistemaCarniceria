package com.mycompany.gestioncarniceria.services;

import com.mycompany.gestioncarniceria.models.Clientes;
import com.mycompany.gestioncarniceria.models.ClientesDao;
import com.mycompany.gestioncarniceria.views.VistaPrincipal;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ClienteService {

    private Clientes cliente;
    private VistaPrincipal vista;
    private ClientesDao clienteDao;
    
    DefaultTableModel model = new DefaultTableModel();

    public ClienteService(Clientes cliente, ClientesDao clienteDao, VistaPrincipal vista) {
        this.cliente = cliente;
        this.clienteDao = clienteDao;
        this.vista = vista;

        
   

    }
    
    //Registrar Cliente
     public void RegistrarCliente(){
        cliente.setApellidoNombre(vista.txtClienteNombre.getText().trim());
        cliente.setDireccion(vista.txtClienteDireccion.getText().trim());
        cliente.setTelefono(vista.txtClienteTelefono.getText().trim());
        cliente.setEmail(vista.txtClienteEmail.getText().trim());
        cliente.setProvincia(vista.txtClienteProvincia.getText().trim());
        cliente.setLocalidad(vista.txtClienteLocalidad.getText().trim());
        cliente.setCodigoPostal(vista.txtClienterCodigoPostal.getText().trim());
        cliente.setRazonSocial(vista.txtClienteRazonSocial.getText().trim());
        cliente.setCuit(vista.txtClienteCuit.getText().trim());

        if (clienteDao.resgistrarCustumerQuery(cliente)) {
            LimpiarTabla();
            LimpiarCampos();
            ListarClientes();
            JOptionPane.showMessageDialog(null, "Cliente registrado con éxito");
        } else {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al registrar al cliente");
        }
        } 
     
    //Modificar Cliente
     public void ModificarCliente(){
        
        cliente.setApellidoNombre(vista.txtClienteNombre.getText().trim());
        cliente.setDireccion(vista.txtClienteDireccion.getText().trim());
        cliente.setTelefono(vista.txtClienteTelefono.getText().trim());
        cliente.setEmail(vista.txtClienteEmail.getText().trim());
        cliente.setProvincia(vista.txtClienteProvincia.getText().trim());
        cliente.setLocalidad(vista.txtClienteLocalidad.getText().trim());
        cliente.setCodigoPostal(vista.txtClienterCodigoPostal.getText().trim());
        cliente.setRazonSocial(vista.txtClienteRazonSocial.getText().trim());
        cliente.setCuit(vista.txtClienteCuit.getText().trim());

        if (clienteDao.modificarClienteQuery(cliente)) {
            LimpiarTabla();
            LimpiarCampos();
            ListarClientes();
            JOptionPane.showMessageDialog(null, "Cliente modificado con éxito");
        } else {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al modificar al cliente");
        }
        }
     
    //Eliminar Cliente
     public void EliminarCliente() {
         int row = vista.tablaCliente.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Debes seleccionar un clienete para eliminar");
            } else {
                int id = Integer.parseInt(vista.tablaCliente.getValueAt(row, 0).toString());
                int question = JOptionPane.showConfirmDialog(null, "¡En realidad quieres eliminar a este cliente?");

                if (question == 0 && clienteDao. eliminarClienteQuery(id) != false) {
                    LimpiarTabla();
                    LimpiarCampos();
                    vista.btnClienteRegistar.setEnabled(true);
                    ListarClientes();
                    JOptionPane.showMessageDialog(null, "Ciente eliminado con éxito");
                }
            }
     }  
     
         
     //Listar clientes
     public void ListarClientes() {
        List<Clientes> list = clienteDao.listarClienteQuery(vista.txtClienteBuscar.getText());
        model = (DefaultTableModel) vista.tablaCliente.getModel();

        Object[] row = new Object[7];
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getId();
            row[1] = list.get(i).getApellidoNombre();
            row[2] = list.get(i).getTelefono();
            row[3] = list.get(i).getRazonSocial();
            row[4] = list.get(i).getCuit();
            row[5] = list.get(i).getEmail();
            row[6] = list.get(i).getDireccion();
            model.addRow(row);
        }
        vista.tablaCliente.setModel(model);

    }
         
     //Limpiar Tabla
     public void LimpiarTabla() {
        for (int i = 0; i < model.getRowCount(); i++) {
            model.removeRow(i);
            i = i - 1;

        }
    }
     
     //Limpiar Campos
     public void LimpiarCampos() {
         vista.txtClienteId.setText("");
         vista.txtClienteBuscar.setText("");
         vista.txtClienterCodigoPostal.setText("");
         vista.txtClienteCuit.setText("");
         vista.txtClienteDireccion.setText("");
         vista.txtClienteEmail.setText("");
         vista.txtClienteId.setText("");
         vista.txtClienteLocalidad.setText("");
         vista.txtClienteNombre.setText("");
         vista.txtClienteProvincia.setText("");
         vista.txtClienteRazonSocial.setText("");
         vista.txtClienteTelefono.setText("");
     }
     
     //Cargar Campos
     public void CargarCampos(java.awt.event.MouseEvent e) {
         
         int fila = vista.tablaCliente.rowAtPoint(e.getPoint());

         int id = Integer.parseInt(vista.tablaCliente.getValueAt(fila, 0).toString());
         

        Clientes clienteSeleccionado = clienteDao.buscarClienteQueryPorId(id);        

        
        vista.txtClienteNombre.setText(clienteSeleccionado.getApellidoNombre());
        vista.txtClienteDireccion.setText(clienteSeleccionado.getDireccion());
        vista.txtClienteTelefono.setText(clienteSeleccionado.getTelefono());
        vista.txtClienteEmail.setText(clienteSeleccionado.getEmail());
        vista.txtClienteProvincia.setText(clienteSeleccionado.getProvincia());
        vista.txtClienteLocalidad.setText(clienteSeleccionado.getLocalidad());
        vista.txtClienterCodigoPostal.setText(clienteSeleccionado.getCodigoPostal());
        vista.txtClienteRazonSocial.setText(clienteSeleccionado.getRazonSocial());
        vista.txtClienteCuit.setText(clienteSeleccionado.getCuit());
        vista.txtClienteId.setText(String.valueOf(clienteSeleccionado.getId()));

        // Guardás también en tu objeto "cliente" actual para futuras operaciones (editar, etc.)
        this.cliente = clienteSeleccionado;
     }
    
    
}

