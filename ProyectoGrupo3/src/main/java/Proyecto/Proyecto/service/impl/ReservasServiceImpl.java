package Proyecto.Proyecto.service.impl;

import Proyecto.Proyecto.dao.ReservasDao;
import Proyecto.Proyecto.domain.Reservas;
import Proyecto.Proyecto.service.ReservasService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReservasServiceImpl implements ReservasService{

     @Autowired
    private ReservasDao reservasDao;

    @Override
    @Transactional(readOnly=true)
    public List<Reservas> getReservas(boolean activo) {
        var lista=reservasDao.findAll();
        if (activo) {
           lista.removeIf(e -> !e.isActivo());
        }
        return lista;
    }
    
    @Override
    @Transactional(readOnly = true)
    public Reservas getReservas(Reservas reservas) {
        return reservasDao.findById(reservas.getId()).orElse(null);
    }

    @Override
    @Transactional
    public void save(Reservas reservas) {
        reservasDao.save(reservas);
    }

    @Override
    @Transactional
    public void delete(Reservas reservas) {
        reservasDao.delete(reservas);
    }
    
    
    @Override
    @Transactional(readOnly = true)
    public List<Reservas> findByIdBetweenOrderByNombre(Long idInicio, Long idFin) {
        return reservasDao.findByIdBetweenOrderByNombre(idInicio, idFin);
    }
}
