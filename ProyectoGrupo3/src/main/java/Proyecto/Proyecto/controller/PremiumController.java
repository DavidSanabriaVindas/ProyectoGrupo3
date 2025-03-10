package Proyecto.Proyecto.controller;

import Proyecto.Proyecto.domain.Premium;
import Proyecto.Proyecto.service.PremiumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/habitacion_premium")
public class PremiumController {
    
    @Autowired
    private PremiumService premiumService;

    @GetMapping("/listado")
    public String inicio(Model model) {
        var premium = premiumService.getPremium(false);
        model.addAttribute("habitacion_premium", premium);
        model.addAttribute("totalPremium", premium.size());
        return "/habitacion_premium/listado";
    }
    
    @GetMapping("/nuevo")
    public String premiumNuevo(Premium premium) {
        return "/habitacion_premium/modifica";
    }
    
    @PostMapping("/guardar")
    public String premiumGuardar(Premium premium,
            @RequestParam("imagenFile") MultipartFile imagenFile) {        

        premiumService.save(premium);
        return "redirect:/habitacion_premium/listado";
    }

    @GetMapping("/eliminar/{idPremium}")
    public String premiumEliminar(Premium premium) {
        premiumService.delete(premium);
        return "redirect:/habitacion_premium/listado";
    }

    @GetMapping("/modificar/{idPremium}")
    public String premiumModificar(Premium premium, Model model) {
        premium = premiumService.getPremium(premium);
        model.addAttribute("habitacion_premium", premium);
        return "/habitacion_premium/modifica";
    }   
}











public class PremiumController {

}
