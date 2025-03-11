package Proyecto.Proyecto.controller;

import Proyecto.Proyecto.domain.Premium;
import Proyecto.Proyecto.service.PremiumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/premium")
public class PremiumController {
    
    @Autowired
    private PremiumService premiumService;

    @GetMapping("/listado")
    public String inicio(Model model) {
        var premium = premiumService.getPremium(false);
        model.addAttribute("premium", premium);
        model.addAttribute("totalPremium", premium.size());
        return "/premium/listado";
    }
    
    @GetMapping("/nuevo")
    public String premiumNuevo(Premium premium) {
        return "/premium/modifica";
    }
    
    @PostMapping("/guardar")
    public String premiumGuardar(Premium premium) {        
        premiumService.save(premium);
        return "redirect:/premium/listado";
    }

    @GetMapping("/eliminar/{id}")
    public String premiumEliminar(Premium premium) {
        premiumService.delete(premium);
        return "redirect:/premium/listado";
    }

    @GetMapping("/modificar/{id}")
    public String premiumModificar(Premium premium, Model model) {
        premium = premiumService.getPremium(premium);
        model.addAttribute("habitacion_premium", premium);
        return "/premium/modifica";
    }   
}










