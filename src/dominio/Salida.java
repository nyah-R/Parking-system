// Trabajo desarollado por: Facundo Esquivel (306504) y Nyah Rüting (270931).
package dominio;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;

public class Salida extends Movimiento implements Serializable {

    private static final long serialVersionUID = 1L;

    private String comentarioEstadoVehiculo;
    private Duration tiempoEstadia;
    private Entrada entrada;
    private Empleado empleado;

    public Salida(LocalDateTime unaFechaHora, String unComentarioEstadoVehiculo, Entrada unaEntrada, Empleado unEmpleado) {
        super(unaFechaHora);
        this.comentarioEstadoVehiculo = unComentarioEstadoVehiculo;
        this.tiempoEstadia = Duration.between(unaEntrada.getFechaHora(), unaFechaHora);
        this.entrada = unaEntrada;
        this.empleado = unEmpleado;
    }

    public String getComentarioEstadoVehiculo() {
        return comentarioEstadoVehiculo;
    }

    public long getTiempoEstadia() {
        return tiempoEstadia.toMinutes();
    }

    public Entrada getEntrada() {
        return entrada;
    }

    public void setFechaHora(LocalDateTime unaFechaHora) {
        this.fechaHora = unaFechaHora;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public Vehiculo getVehiculo() {
        return entrada.getVehiculo();
    }

    public Empleado getEmpleado() {
        return empleado;
    }

}
