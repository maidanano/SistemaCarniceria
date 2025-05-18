
package com.mycompany.gestioncarniceria.controllers;

import com.mycompany.gestioncarniceria.models.Empleados;
import com.mycompany.gestioncarniceria.models.EmpleadosDao;
import static com.mycompany.gestioncarniceria.models.EmpleadosDao.rolUsuario;
import com.mycompany.gestioncarniceria.services.EmpleadoService;
import com.mycompany.gestioncarniceria.views.VistaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class EmpleadoControlador extends EmpleadoService implements ActionListener, MouseListener, KeyListener {
    

    private Empleados empleado;
    private EmpleadosDao empleadoDao;
    private VistaPrincipal vista;
    //Rol
    String rol = rolUsuario;
    DefaultTableModel model = new DefaultTableModel();

    public EmpleadoControlador(Empleados empleado, EmpleadosDao empleadoDao, VistaPrincipal vista) {
        super (empleado, empleadoDao, vista);
        this.empleado = empleado;
        this.empleadoDao = empleadoDao;
        this.vista = vista;

        //Botón de registrar empleados
        this.vista.btnEmpleadoRegistrar.addActionListener(this);

        //Botón de Modificar empleados
        this.vista.btnEmpleadoModificar.addActionListener(this);

        //Borón de eliminar empleado
        this.vista.btnEmpleadoEliminar.addActionListener(this);

        //Botón de cancelar
        this.vista.btnEmpleadoEliminar.addActionListener(this);

        //Botón de cambiar contraseña
        //this.views.btn_modify_data.addActionListener(this); .---> Esto para cuando Agregue la pestaña "Perfil"
        
        

        this.vista.tablaEmpleado.addMouseListener(this);
        this.vista.txtEmpleadoBuscar.addKeyListener(this);

}

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnEmpleadoRegistrar) {
            RegistrarEmpleado();            
        }else if (e.getSource() == vista.btnEmpleadoModificar) {
            if (vista.txtEmpleadoId.equals("")) {
                JOptionPane.showMessageDialog(null, "Selecciona una fila para continuar");
            }else {
            ModificarEmpleado();
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
