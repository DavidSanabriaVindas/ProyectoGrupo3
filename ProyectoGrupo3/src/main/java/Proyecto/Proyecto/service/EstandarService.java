package Proyecto.Proyecto.service;

import Proyecto.Proyecto.domain.Estandar;
import java.util.List;

public interface EstandarService {

    // Se obtiene un listado de categorias en un List
    public List<Estandar> getEstandar(boolean activo);
    
    // Se obtiene un Categoria, a partir del id de un categoria
    public Estandar getEstandar(Estandar estandar);
    
    // Se inserta un nuevo categoria si el id del categoria esta vacío
    // Se actualiza un categoria si el id del categoria NO esta vacío
    public void save(Estandar estandar);
    
    // Se elimina el categoria que tiene el id pasado por parámetro
    public void delete(Estandar estandar);
}
