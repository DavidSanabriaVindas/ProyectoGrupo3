package Proyecto.Proyecto.service.impl;

import Proyecto.Proyecto.dao.SuiteDao;
import Proyecto.Proyecto.domain.Suite;
import Proyecto.Proyecto.service.SuiteService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SuiteServiceImpl implements SuiteService {

     @Autowired
    private SuiteDao suiteDao;

    @Override
    @Transactional(readOnly = true)
    public List<Suite> getSuites(boolean activo) {
        var lista = suiteDao.findAll();
        if (activo) {
            lista.removeIf(e -> !e.isActivo());
        }
        return lista;
    }

    @Override
    @Transactional(readOnly = true)
    public Suite getSuite(Suite suite) {
        return suiteDao.findById(suite.getId()).orElse(null);
    }

    @Override
    @Transactional
    public void save(Suite suite) {
        suiteDao.save(suite);
    }

    @Override
    @Transactional
    public void delete(Suite suite) {
        suiteDao.delete(suite);
    }
    
}
