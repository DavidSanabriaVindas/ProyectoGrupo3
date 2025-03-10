/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Proyecto.Proyecto.service;

import Proyecto.Proyecto.domain.Suite;
import java.util.List;

public interface SuiteService {

    public List<Suite> getSuites(boolean activo);

    public Suite getSuite(Suite suite);
    
    public void save(Suite suite);
    
    public void delete(Suite suite);
}
