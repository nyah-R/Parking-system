// Trabajo desarollado por: Facundo Esquivel (306504) y Nyah Rüting (270931).
package dominio;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Entrada extends Movimiento implements Serializable {

    private static final long serialVersionUID = 1L;

    private Vehiculo vehiculo;
    private String notas;
    private Empleado empleado;
    private Salida salida;

    public Entrada(LocalDateTime unaFechaHora, Vehiculo unVehiculo, String unaNota, Empleado unEmpleado) {
        super(unaFechaHora);
        this.vehiculo = unVehiculo;
        this.notas = unaNota;
        this.empleado = unEmpleado;

    }

    // Getters
    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public String getNotas() {
        return notas;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    // Setters
    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public void setFechaHora(LocalDateTime unaFechaHora) {
        this.fechaHora = unaFechaHora;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Salida getSalida() {
        return salida;
    }

    public void setSalida(Salida salida) {
        this.salida = salida;
    }

    @Override
    public String toString() {
        return getFechaHora() + " - " + getVehiculo();
    }
}
