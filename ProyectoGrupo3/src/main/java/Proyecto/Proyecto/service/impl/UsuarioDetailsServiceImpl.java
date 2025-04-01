package Proyecto.Proyecto.service.impl;

import Proyecto.Proyecto.service.UsuarioDetailsService;
import Proyecto.Proyecto.dao.UsuarioDao;
import Proyecto.Proyecto.domain.Usuario;
import Proyecto.Proyecto.domain.Rol;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsService")
public class UsuarioDetailsServiceImpl implements UsuarioDetailsService, UserDetailsService{

     @Autowired
    private UsuarioDao usuarioDao;
    @Autowired
    private HttpSession session;
    
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Busca el usuario en la tabla
        Usuario usuario = usuarioDao.findByUsername(username);
        //Si no existe el usuario lanza una excepción
        if (usuario == null) {
            throw new UsernameNotFoundException(username);
        }
        session.removeAttribute("usuarioImagen");
        session.setAttribute("usuarioImagen", usuario.getRutaImagen());
        //Si está acá es porque existe el usuario... sacamos los roles que tiene
        var roles = new ArrayList<GrantedAuthority>();
        for (Rol rol : usuario.getRoles()) {    
        roles.add(new SimpleGrantedAuthority("ROLE_" + rol.getNombre()));
        }
        //Se devuelve User (clase de UserDetails)
        return new User(username, usuario.getPassword(), roles);
    }
}
