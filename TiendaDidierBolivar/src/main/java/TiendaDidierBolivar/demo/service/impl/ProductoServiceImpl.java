/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TiendaDidierBolivar.demo.service.impl;

/**
 *
 * @author rdcd2
 */
import TiendaDidierBolivar.demo.dao.ProductoDao;
import TiendaDidierBolivar.demo.domain.Producto;
import TiendaDidierBolivar.demo.service.ProductoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductoServiceImpl implements ProductoService {

     @Autowired
    private ProductoDao productoDao;

    @Override
    @Transactional(readOnly = true)
    public List<Producto> getProductos(boolean activos) {
        var lista = productoDao.findAll();
        if (activos) {
            lista.removeIf(e -> !e.isActivo());
        }
        return lista;
    }

    @Override
    @Transactional(readOnly = true)
    public Producto getProducto(Producto producto) {
        return productoDao.findById(producto.getIdProducto()).orElse(null);
    }

    @Override
    @Transactional
    public void save(Producto producto) {
        productoDao.save(producto);
    }

    @Override
    @Transactional
    public void delete(Producto producto) {
        productoDao.delete(producto);
    }
    
    //Lista de productos con precion entre ordenados por descripcion consultaAmpliada
    @Transactional(readOnly=true)
     public List<Producto> findByPrecioBetweenOrderByDescripcion(double precioInf, double precioSup){
         return productoDao.findByPrecioBetweenOrderByDescripcion(precioInf, precioSup);
     }
    
}