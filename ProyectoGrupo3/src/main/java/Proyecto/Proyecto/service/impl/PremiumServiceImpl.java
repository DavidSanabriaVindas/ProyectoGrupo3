package Proyecto.Proyecto.service.impl;

import Proyecto.Proyecto.dao.PremiumDao;
import Proyecto.Proyecto.domain.Premium;
import Proyecto.Proyecto.service.PremiumService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PremiumServiceImpl implements PremiumService {
    
    @Autowired
    private PremiumDao premiumDao;

    @Override
    @Transactional(readOnly=true)
    public List<Premium> getPremium(boolean activos) {
        var lista=premiumDao.findAll();
        if (activos) {
           lista.removeIf(e -> !e.isActivo());
        }
        return lista;
    }
    
    @Override
    @Transactional(readOnly = true)
    public Premium getPremium(Premium premium) {
        return premiumDao.findById(premium.getIdPremium()).orElse(null);
    }

    @Override
    @Transactional
    public void save(Premium premium) {
        premiumDao.save(premium);
    }

    @Override
    @Transactional
    public void delete(Premium premium) {
        premiumDao.delete(premium);
    }
}

