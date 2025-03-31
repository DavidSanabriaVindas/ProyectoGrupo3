package Proyecto.Proyecto.controller;

import Proyecto.Proyecto.domain.Reservas;
import Proyecto.Proyecto.service.ReservasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/reservas")

public class ReservasController {

    @Autowired
    private ReservasService reservasService;

    @GetMapping("/listado")
    public String inicio(Model model) {
        var reservas = reservasService.getReservas(false);
        model.addAttribute("reservas", reservas);
        return "/reservas/listado";
    }

    @GetMapping("/nuevo")
    public String reservasNuevo(Reservas reservas) {
        return "/reservas/modifica";
    }

    @PostMapping("/guardar")
    public String reservasGuardar(Reservas reservas) {
        reservasService.save(reservas);
        return "redirect:/reservas/listado";
    }

    @GetMapping("/eliminar/{id}")
    public String reservasEliminar(Reservas reservas) {
        reservasService.delete(reservas);
        return "redirect:/reservas/listado";
    }

    @GetMapping("/modificar/{id}")
    public String categoriaModificar(Reservas reservas, Model model) {
        reservas = reservasService.getReservas(reservas);
        model.addAttribute("reservas", reservas);
        return "/reservas/modifica";
    }
    
    @GetMapping("/queryPorId")
    public String consultaPorId(@RequestParam(value = "idInicial", required = false) Long idInicio,
                            @RequestParam(value = "idFinal", required = false) Long idFin, 
                            Model model) {
        // If no parameters are provided, return to the full list
        if (idInicio == null || idFin == null) {
        return "redirect:/reservas/listado";
        }

        // Ensure idInicio is not greater than idFin
        if (idInicio > idFin) {
         // Swap values if entered in wrong order
            Long temp = idInicio;
            idInicio = idFin;
            idFin = temp;
        }

        var reservas = reservasService.findByIdBetweenOrderByNombre(idInicio, idFin);
    
        model.addAttribute("reservas", reservas);
        model.addAttribute("totalReservas", reservas.size());
        model.addAttribute("idInicial", idInicio);
        model.addAttribute("idFinal", idFin);
    
        return "reservas/listado";
    }
}


