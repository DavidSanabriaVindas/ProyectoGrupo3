/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package Proyecto.Proyecto.dao;
import Proyecto.Proyecto.domain.Reservas;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 *
 * @author Andy
 */
public interface ReservasDao extends JpaRepository <Reservas, Long>{

}
