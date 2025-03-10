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
    
    @GetMapping("/Reservas")
    public String mostrarReservas() {
        return "Reservas";
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
}