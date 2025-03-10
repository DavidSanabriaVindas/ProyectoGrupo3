package Proyecto.Proyecto.service;

import Proyecto.Proyecto.domain.Premium;
import java.util.List;

public interface PremiumService {
    
    public List<Premium> getPremium(boolean activos);
    
    public Premium getPremium(Premium premium);
    
    public void save(Premium premium);
    
    // Se elimina el categoria que tiene el id pasado por parámetro
    public void delete(Premium premium);
}

