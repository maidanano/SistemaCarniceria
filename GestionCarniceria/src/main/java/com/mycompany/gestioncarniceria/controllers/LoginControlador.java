package com.mycompany.gestioncarniceria.controllers;

import com.mycompany.gestioncarniceria.models.Empleados;
import com.mycompany.gestioncarniceria.models.EmpleadosDao;
import com.mycompany.gestioncarniceria.views.VistaLogin;
import com.mycompany.gestioncarniceria.views.VistaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class LoginControlador implements ActionListener {

    private Empleados empleado;
    private EmpleadosDao empleadoDao;
    private VistaLogin vistaLogin;

    public LoginControlador(Empleados empleado, EmpleadosDao empleadoDao, VistaLogin vistaLogin) {
        this.empleado = empleado;
        this.empleadoDao = empleadoDao;
        this.vistaLogin = vistaLogin;
        this.vistaLogin.btnLoginIniciarSesion.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Obtener los datos de la vista
        String usuario = vistaLogin.txtLoginNombreUsuario.getText().trim();
        String contraseña = String.valueOf(vistaLogin.txtLogginContraseña.getPassword());

        if (e.getSource() == vistaLogin.btnLoginIniciarSesion) {
            if (!usuario.equals("") || !contraseña.equals("")) {
                empleado = empleadoDao.loginQuery(usuario, contraseña);

                if (empleado.getNombreUsuario() != null) {
                    if (empleado.getRol().equals("Administrador")) {
                        VistaPrincipal admin = new VistaPrincipal();
                        admin.setVisible(true);
                    } else {
                        VistaPrincipal aux = new VistaPrincipal();
                        aux.setVisible(true);
                    }
                    this.vistaLogin.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrecto");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Los campos están vacíos");
            }

        }

    }

}
