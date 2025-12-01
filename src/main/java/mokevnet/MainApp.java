package com.mokevnet.proyecto;

import com.mokevnet.proyecto.dao.ProductoDAO;
import com.mokevnet.proyecto.dao.ProductoDAOImpl;
import com.mokevnet.proyecto.model.Producto;

import java.math.BigDecimal;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws Exception {
        ProductoDAO dao = new ProductoDAOImpl();

        // Insertar
        Producto p = new Producto(null, "Camiseta", "Algodón 100%", new BigDecimal("39.99"), 50);
        dao.insertar(p);
        System.out.println("Insertado: " + p);

        // Obtener por id
        dao.obtenerPorId(p.getId()).ifPresent(prod -> System.out.println("Recuperado: " + prod));

        // Actualizar
        p.setPrecio(new BigDecimal("34.99"));
        p.setInventario(45);
        dao.actualizar(p);
        System.out.println("Actualizado: " + p);

        // Listar todos
        List<Producto> lista = dao.obtenerTodos();
        lista.forEach(System.out::println);

        // Eliminar
        boolean borrado = dao.eliminar(p.getId());
        System.out.println("Eliminado? " + borrado);
    }
}
