package Proyecto.Proyecto.controller;

import Proyecto.Proyecto.domain.Estandar;
import Proyecto.Proyecto.service.EstandarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/estandar")

public class EstandarController {

    @Autowired
    private EstandarService estandarService;

    @GetMapping("/listado")
    public String inicio(Model model) {
        var estandares = estandarService.getEstandar(false);
        model.addAttribute("estandares", estandares);
        model.addAttribute("totalEstandares", estandares.size());
        return "/estandar/listado";
    }

    @GetMapping("/nuevo")
    public String estandarNuevo(Estandar estandar) {
        return "/estandar/modifica";
    }

    @PostMapping("/guardar")
    public String estandarGuardar(Estandar estandar) {
        estandarService.save(estandar);
        return "redirect:/estandar/listado";
    }

    @GetMapping("/eliminar/{id}")
    public String estandarEliminar(Estandar estandar) {
        estandarService.delete(estandar);
        return "redirect:/estandar/listado";
    }

    @GetMapping("/modificar/{id}")
    public String categoriaModificar(Estandar estandar, Model model) {
        estandar = estandarService.getEstandar(estandar);
        model.addAttribute("estandar", estandar);
        return "/estandar/modifica";
    }
}


