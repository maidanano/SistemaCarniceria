
package com.mycompany.gestioncarniceria.models;

import lombok.Data;

@Data
public class Persona {
    private int id;
    private String apellidoNombre;
    private String telefono;
    private String email;
    private String localidad;
    private String provincia;
    private String codigoPostal;
    private String razonSocial;
    private String cuit;
    private String direccion;
}
