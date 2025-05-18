package com.mycompany.gestioncarniceria.services;

import com.mycompany.gestioncarniceria.models.Clientes;
import com.mycompany.gestioncarniceria.models.ClientesDao;
import com.mycompany.gestioncarniceria.models.Empleados;
import com.mycompany.gestioncarniceria.models.EmpleadosDao;
import static com.mycompany.gestioncarniceria.models.EmpleadosDao.rolUsuario;
import com.mycompany.gestioncarniceria.views.VistaPrincipal;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class EmpleadoService {

    private Empleados empleado;
    private EmpleadosDao empleadoDao;
    private VistaPrincipal vista;

    DefaultTableModel model = new DefaultTableModel();

    public EmpleadoService(Empleados empleado, EmpleadosDao empleadoDao, VistaPrincipal vista) {
        this.empleado = empleado;
        this.empleadoDao = empleadoDao;
        this.vista = vista;
    }


    
    //Registrar Empleado
    public void RegistrarEmpleado() {
        empleado.setId(Integer.parseInt(vista.txtEmpleadoId.getText().trim()));
        empleado.setApellidoNombre(vista.txtEmpleadoNombre.getText().trim());
        empleado.setNombreUsuario(vista.txtEmpleadoUsuario.getText().trim());
        empleado.setDireccion(vista.txtEmpleadoDireccion.getText().trim());
        empleado.setTelefono(vista.txtEmpleadoTelefono.getText().trim());
        empleado.setEmail(vista.txtEmpleadoEmail.getText().trim());
        empleado.setContraseña(String.valueOf(vista.txtEmpleadoContraseña.getPassword()));
        empleado.setRol(vista.cmbEmpleadoRol.getSelectedItem().toString());

        if (empleadoDao.registrarEmpleadoQuery(empleado)) {
            LimpiarTabla();
            LimpiarCampos();
            ListaTodosEmpleados();
            JOptionPane.showMessageDialog(null, "Empleado Registrado con éxito");
        } else {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al registrar al empleado");
        }
    }
    
    //Modificar Empleado
    public void ModificarEmpleado(){
                    empleado.setId(Integer.parseInt(vista.txtEmpleadoId.getText().trim()));
                    empleado.setApellidoNombre(vista.txtEmpleadoNombre.getText().trim());
                    empleado.setNombreUsuario(vista.txtEmpleadoUsuario.getText().trim());
                    empleado.setDireccion(vista.txtEmpleadoDireccion.getText().trim());
                    empleado.setTelefono(vista.txtEmpleadoTelefono.getText().trim());
                    empleado.setEmail(vista.txtEmpleadoEmail.getText().trim());
                    empleado.setContraseña(String.valueOf(vista.txtEmpleadoContraseña.getPassword()));
                    empleado.setRol(vista.cmbEmpleadoRol.getSelectedItem().toString());

                    if (empleadoDao.modificarEmpleadoQuery(empleado)) {
                        LimpiarTabla();
                        LimpiarCampos();
                        ListaTodosEmpleados();
                        vista.btnEmpleadoRegistrar.setEnabled(true);

                        JOptionPane.showMessageDialog(null, "Datos del emepleado modificados con éxito");

                    } else {
                        JOptionPane.showMessageDialog(null, "Ha ocurrido un error al modificar al empleado");
                    }
    }

    //Listar todos los empleados
    public void ListaTodosEmpleados() {
        if (rolUsuario.equals("Administrador")) {
            List<Empleados> lista = empleadoDao.listaEmpleadoQuery(vista.txtEmpleadoBuscar.getText());
            model = (DefaultTableModel) vista.tablaEmpleado.getModel();
            Object[] row = new Object[7];
            for (int i = 0; i < lista.size(); i++) {
                row[0] = lista.get(i).getId();
                row[1] = lista.get(i).getApellidoNombre();
                row[2] = lista.get(i).getNombreUsuario();
                row[3] = lista.get(i).getRol();
                row[4] = lista.get(i).getTelefono();
                row[5] = lista.get(i).getEmail();
                row[6] = lista.get(i).getDireccion();
                model.addRow(row);
            }
        }
    }

    //Limpiar campos
    public void LimpiarCampos() {
        vista.txtEmpleadoId.setText("");
        vista.txtEmpleadoId.setEditable(true);
        vista.txtEmpleadoNombre.setText("");
        vista.txtEmpleadoUsuario.setText("");
        vista.txtEmpleadoDireccion.setText("");
        vista.txtEmpleadoTelefono.setText("");
        vista.txtEmpleadoEmail.setText("");
        vista.txtEmpleadoContraseña.setText("");
        vista.cmbEmpleadoRol.setSelectedIndex(0);
    }

    //Limpiar Tabla
    public void LimpiarTabla() {
        for (int i = 0; i < model.getRowCount(); i++) {
            model.removeRow(i);
            i = i - 1;

        }
    }
}
