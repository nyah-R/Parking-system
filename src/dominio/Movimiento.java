// Trabajo desarollado por: Facundo Esquivel (306504) y Nyah Rüting (270931).
package dominio;

import java.io.Serializable;
import java.time.LocalDateTime;

public abstract class Movimiento implements Serializable {

    private static final long serialVersionUID = 1L;

    protected LocalDateTime fechaHora;

    public Movimiento(LocalDateTime unaFechaHora) {
        this.fechaHora = unaFechaHora;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public Vehiculo getVehiculo() {
        return null;
    }

}
