package com.proyecto.service;

import com.proyecto.dao.UsuarioDAOImpl;
import com.proyecto.model.Usuario;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ricardotoledo
 */
@LocalBean
@Stateless
public class UsuarioServiceImpl implements UsuarioService {
    
    @Inject
    private UsuarioDAOImpl uDAO;
    @Override
    public void save(Usuario u) {
        uDAO.save(u);
    }

    @Override
    public void edit(Usuario u) {
        uDAO.edit(u);
    }

    @Override
    public void delete(Usuario u) {
        uDAO.delete(u);
    }

    @Override
    public List<Usuario> list() {
        return uDAO.list();
    }

    @Override
    public Usuario find(Usuario u) {
        return uDAO.find(u);
    }

   
    
}