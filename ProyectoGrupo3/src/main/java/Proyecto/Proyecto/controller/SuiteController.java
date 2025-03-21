/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Proyecto.Proyecto.controller;

import Proyecto.Proyecto.domain.Suite;
import Proyecto.Proyecto.service.SuiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

@RequestMapping("/suite")
public class SuiteController {
    
    @Autowired
    private SuiteService suiteService;

    @GetMapping("/listado")
    public String inicio(Model model) {
        var suite = suiteService.getSuites(false);
        model.addAttribute("suite", suite);
        model.addAttribute("totalSuite", suite.size());
        return "/suite/listado";
    }
  
     @GetMapping("/nuevo")
    public String suiteNuevo(Suite suite) {
        return "/suite/modifica";
    }
    
    @PostMapping("/guardar")
    public String suiteGuardar(Suite suite){
        suiteService.save(suite);
        return "redirect:/suite/listado";
    }

    @GetMapping("/eliminar/{id}")
    public String suiteEliminar(Suite suite) {
        suiteService.delete(suite);
        return "redirect:/suite/listado";
    }

    @GetMapping("/modificar/{id}")
    public String suiteModificar(Suite suite, Model model) {
        suite = suiteService.getSuite(suite);
        model.addAttribute("suite", suite);
        return "/suite/modifica";
    }   
}
