package com.mycompany.gestioncarniceria.controllers;

import com.mycompany.gestioncarniceria.models.ClientesDao;
import com.mycompany.gestioncarniceria.models.Clientes;
import com.mycompany.gestioncarniceria.services.ClienteService;
import com.mycompany.gestioncarniceria.views.VistaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;


public class ClienteControlador extends ClienteService implements ActionListener, MouseListener, KeyListener {

    private Clientes cliente;
    private VistaPrincipal vista;
    private ClientesDao clienteDao;
    private ClienteService clienteService;
    

    public ClienteControlador(Clientes cliente, ClientesDao clienteDao, VistaPrincipal vista, ClienteService clienteService) {
        super(cliente, clienteDao, vista);
        this.cliente = cliente;
        this.clienteDao = clienteDao;
        this.vista = vista;
        this.clienteService = clienteService;

        //---------------Botones y Listeners------------------//
        
        //Botón de registrar cliente
        this.vista.btnClienteRegistar.addActionListener(this);

        //Boton de mofificar cliente
        this.vista.btnClienteModificar.addActionListener(this);

        //Borón de eliminar cliente
        this.vista.btnClienteElminar.addActionListener(this);

        //Botón de cancelar
        this.vista.btnClienteCancelar.addActionListener(this);

        //Botón buscador
        this.vista.txtClienteBuscar.addKeyListener(this);
        this.vista.tablaCliente.addMouseListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Registrar Cliente
        if (e.getSource() == vista.btnClienteRegistar) {
            RegistrarCliente();
            
        //Modificar Cliente
        } else if (e.getSource() == vista.btnClienteModificar) {
            if (vista.txtClienteId.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Seleccione una fila para continuar");
            } else {
                ModificarCliente();
            }
            
        //Cancelar
        } else if (e.getSource() == vista.btnClienteCancelar) {
            vista.btnClienteRegistar.setEnabled(true);
            LimpiarCampos();
            LimpiarTabla();
            ListarClientes();
            
        //Eliminar Cliente
        }else if (e.getSource() == vista.btnClienteElminar) {
            EliminarCliente();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == vista.tablaCliente) {
            CargarCampos(e);
        }
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
        //Buscar Cliente
       if (e.getSource() == vista.txtClienteBuscar) {
           LimpiarTabla();
           ListarClientes();
       }
    }

}
