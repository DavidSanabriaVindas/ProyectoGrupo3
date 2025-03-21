package Proyecto.Proyecto.controller;

import Proyecto.Proyecto.domain.Reservas;
import Proyecto.Proyecto.service.ReservasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


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
}


