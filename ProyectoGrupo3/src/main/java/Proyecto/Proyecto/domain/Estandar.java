package Proyecto.Proyecto.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Data 
@Entity 
@Table(name = "habitacion_estandar") 
public class Estandar implements Serializable{

    private static final long serialVersionUID = 1L;
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "id") 
    
    //Atributos
    private Long id;
    private String nombre;
    private String apellido;
    private int cedula;
    private int telefono;
    private String correo;
    private boolean activo;



    public Estandar() {
    }

    public Estandar(Long id, String nombre, String apellido, int cedula, int telefono, String correo, boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.telefono = telefono;
        this.correo = correo;
        this.activo = activo;
    }
    
    
}
