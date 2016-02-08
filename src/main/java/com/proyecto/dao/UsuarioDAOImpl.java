package com.proyecto.dao;

import com.proyecto.model.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author ricardotoledo
 */
public class UsuarioDAOImpl implements UsuarioDAO{
    @PersistenceContext
    private EntityManager em;
    
    public void save(Usuario u) {
        
        em.persist(u);
    }

    public void edit(Usuario u) {
        em.merge(u);
    }

    public void delete(Usuario u) {
        em.remove(em.merge(u));
    }

    public List<Usuario> list() {
        String sql = "select u from Usuario u ";
        Query query = em.createQuery(sql);
        List<Usuario> list= query.getResultList();
        return list;
    }

 
    public Usuario find(Usuario u) {
        String sql = "select u from Usuario u WHERE u.id =" + u.getId();
        Query query = em.createQuery(sql);
        Usuario user= (Usuario) query.getSingleResult();
        return user;
    }
}
