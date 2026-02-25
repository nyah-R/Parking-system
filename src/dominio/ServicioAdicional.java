// Trabajo desarollado por: Facundo Esquivel (306504) y Nyah Rüting (270931).
package dominio;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ServicioAdicional extends Movimiento implements Serializable {

    private static final long serialVersionUID = 1L;

    private String tipoServicio;
    private double costo;
    private Vehiculo vehiculo;
    private Empleado empleado;

    public ServicioAdicional(LocalDateTime unaFechaHora, String unTipoServicio, double unCosto, Vehiculo unVehiculo, Empleado unEmpleado) {
        super(unaFechaHora);
        this.tipoServicio = unTipoServicio;
        this.costo = unCosto;
        this.vehiculo = unVehiculo;
        this.empleado = unEmpleado;
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    public int getCosto() {
        return (int) costo;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setFechaHora(LocalDateTime unaFechaHora) {
        this.fechaHora = unaFechaHora;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    @Override
    public String toString() {
        return this.getTipoServicio() + " - " + getVehiculo() + " - $" + getCosto();
    }

}
