package Proyecto.Proyecto.domain;
import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;
/**
 *
 * @author Andy
 */
@Data 
@Entity 
@Table(name = "reservas") 
public class Reservas implements Serializable{
    private static final long serialVersionUID = 1L;
    
    public static final String TIPO_ESTANDAR = "Estandar";
    public static final String TIPO_PREMIUM = "Premium";
    public static final String TIPO_SUITE = "Suite";
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "id") 
    private Long id;
    
    //Atributos
    private String nombre;
    private String apellido;
    private int cedula;
    private int telefono;
    private String correo;
    private String tipoHabitacion; 
    private boolean activo;
    
    public Reservas() {
    }
    
    public Reservas(String nombre, String tipoHabitacion, boolean activo) {
        this.nombre = nombre;
        this.tipoHabitacion = tipoHabitacion;
        this.activo = activo;
    }
    

}
