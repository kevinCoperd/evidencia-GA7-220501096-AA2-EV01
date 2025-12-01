package com.mokevnet.proyecto.dao;

import com.mokevnet.proyecto.model.Producto;
import java.util.List;
import java.util.Optional;

public interface ProductoDAO {
    Producto insertar(Producto producto) throws Exception;
    Optional<Producto> obtenerPorId(int id) throws Exception;
    List<Producto> obtenerTodos() throws Exception;
    boolean actualizar(Producto producto) throws Exception;
    boolean eliminar(int id) throws Exception;
}
