package com.mokevnet.proyecto.dao;

import com.mokevnet.proyecto.model.Producto;
import com.mokevnet.proyecto.util.JdbcUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductoDAOImpl implements ProductoDAO {

    @Override
    public Producto insertar(Producto producto) throws Exception {
        String sql = "INSERT INTO producto (nombre, descripcion, precio, inventario) VALUES (?, ?, ?, ?)";
        try (Connection conn = JdbcUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getDescripcion());
            ps.setBigDecimal(3, producto.getPrecio());
            ps.setInt(4, producto.getInventario());
            int rows = ps.executeUpdate();
            if (rows == 0) throw new SQLException("No se insertó registro.");
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) producto.setId(rs.getInt(1));
            }
            return producto;
        }
    }

    @Override
    public Optional<Producto> obtenerPorId(int id) throws Exception {
        String sql = "SELECT id, nombre, descripcion, precio, inventario, creado FROM producto WHERE id = ?";
        try (Connection conn = JdbcUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return Optional.of(mapRow(rs));
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Producto> obtenerTodos() throws Exception {
        String sql = "SELECT id, nombre, descripcion, precio, inventario, creado FROM producto";
        List<Producto> list = new ArrayList<>();
        try (Connection conn = JdbcUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(mapRow(rs));
        }
        return list;
    }

    @Override
    public boolean actualizar(Producto producto) throws Exception {
        String sql = "UPDATE producto SET nombre = ?, descripcion = ?, precio = ?, inventario = ? WHERE id = ?";
        try (Connection conn = JdbcUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getDescripcion());
            ps.setBigDecimal(3, producto.getPrecio());
            ps.setInt(4, producto.getInventario());
            ps.setInt(5, producto.getId());
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean eliminar(int id) throws Exception {
        String sql = "DELETE FROM producto WHERE id = ?";
        try (Connection conn = JdbcUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    private Producto mapRow(ResultSet rs) throws SQLException {
        Producto p = new Producto();
        p.setId(rs.getInt("id"));
        p.setNombre(rs.getString("nombre"));
        p.setDescripcion(rs.getString("descripcion"));
        p.setPrecio(rs.getBigDecimal("precio"));
        p.setInventario(rs.getInt("inventario"));
        Timestamp ts = rs.getTimestamp("creado");
        if (ts != null) p.setCreado(ts.toLocalDateTime());
        return p;
    }
}
