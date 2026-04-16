package com.clopez.Sistema_Ventas.Entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "Ventas")
public class Venta {
    @Id
    @Column(name = "codigo_venta")
    private Integer codigoVenta;

    @Column(name = "fecha_venta")
    @Temporal(TemporalType.DATE)
    private Date fechaVenta;

    @Column(name = "total")
    private BigDecimal total;

    @Column(name = "estado")
    private Integer estado;

    @ManyToOne
    @JoinColumn(name = "clientes_dpi_cliente")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "usuarios_codigo_usuario")
    private Usuario usuario;

    // Getters y Setters

    public Integer getCodigoVenta() {
        return codigoVenta;
    }

    public void setCodigoVenta(Integer codigoVenta) {
        this.codigoVenta = codigoVenta;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}