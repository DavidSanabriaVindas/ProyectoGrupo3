/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Proyecto.Proyecto.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 *
 * @author Andy
 */
@Controller
public class NavegacionController {
    
    @GetMapping("/")
    public String mostrarIndex() {
        return "index"; // Página de inicio
    }
    
    @GetMapping("/Habitaciones")
    public String mostrarReservas() {
        return "Habitaciones";
    }
    
    @GetMapping("/Servicios")
    public String mostrarServicios() {
        return "Servicios";
    }
    
    @GetMapping("/Nosotros")
    public String mostrarNosotros() {
        return "Nosotros";
    }
    
    @GetMapping("/Actividades")
    public String mostrarActividades() {
        return "Actividades";
    }
    @GetMapping("/ActividadAcuatica")
    public String mostrarActividadAcuatica() {
        return "ActividadAcuatica";
    }
    @GetMapping("/Actividades_AireLibre")
    public String mostrarActividadesAireLibre() {
        return "Actividades_AireLibre";
    }
    @GetMapping("/Actividad_Entretenimientos")
    public String mostrarActividadEntretenimientos() {
        return "Actividad_Entretenimientos";
    }
}