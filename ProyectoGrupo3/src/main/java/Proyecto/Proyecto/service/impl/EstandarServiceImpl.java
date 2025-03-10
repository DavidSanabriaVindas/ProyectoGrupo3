package Proyecto.Proyecto.service.impl;

import Proyecto.Proyecto.dao.EstandarDao;
import Proyecto.Proyecto.domain.Estandar;
import Proyecto.Proyecto.service.EstandarService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EstandarServiceImpl implements EstandarService{

     @Autowired
    private EstandarDao estandarDao;

    @Override
    @Transactional(readOnly=true)
    public List<Estandar> getEstandar(boolean activo) {
        var lista=estandarDao.findAll();
        if (activo) {
           lista.removeIf(e -> !e.isActivo());
        }
        return lista;
    }
    
    @Override
    @Transactional(readOnly = true)
    public Estandar getEstandar(Estandar estandar) {
        return estandarDao.findById(estandar.getId()).orElse(null);
    }

    @Override
    @Transactional
    public void save(Estandar estandar) {
        estandarDao.save(estandar);
    }

    @Override
    @Transactional
    public void delete(Estandar estandar) {
        estandarDao.delete(estandar);
    }
}
