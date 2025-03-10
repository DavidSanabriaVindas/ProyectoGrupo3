package Proyecto.Proyecto.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Data
@Entity
@Table(name="suite")

public class Suite implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    private String nombre;
    private String apellido;
    private String cedula;
    private String telefono;
    private String correo;
    private boolean activo;

    public Suite() {
    }

    public Suite(String nombre, boolean activo) {
        this.nombre = nombre;
        this.activo = activo;
    }
    
}