/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TiendaDidierBolivar.demo.service.impl;

import TiendaDidierBolivar.demo.dao.CategoriaDao;
import TiendaDidierBolivar.demo.domain.Categoria;
import TiendaDidierBolivar.demo.service.CategoriaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author rdcd2
 */
@Service
public class CategoriaServiceImpl implements CategoriaService {

     @Autowired
    private CategoriaDao categoriaDao;

    /**
     *
     * @param activos
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public List<Categoria> getCategorias(boolean activos) {
        var lista = categoriaDao.findAll();
        if (activos) {
            lista.removeIf(e -> !e.isActivo());
        }
        return lista;
    }

    @Transactional(readOnly = true)
    public Categoria getCategoria(Categoria categoria) {
        return categoriaDao.findById(categoria.getIdCategoria()).orElse(null);
    }

    @Transactional
    public void save(Categoria categoria) {
        categoriaDao.save(categoria);
    }

    @Transactional
    public void delete(Categoria categoria) {
        categoriaDao.delete(categoria);
    }
    
}

