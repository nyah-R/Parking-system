// Trabajo desarollado por: Facundo Esquivel (306504) y Nyah Rüting (270931).
package dominio;

import java.io.Serializable;

public class Empleado extends Persona implements Serializable {

    private static final long serialVersionUID = 1L;

    private int nroEmpleado;
    private int cantMovimient;

    public Empleado(String nombre, String cedula, String direccion, int nroEmpleado) {
        super(nombre, cedula, direccion);
        this.nroEmpleado = nroEmpleado;
        this.cantMovimient = 0;
    }

    public int getNroEmpleado() {
        return nroEmpleado;
    }

    public void setNroEmpleado(int nroEmpleado) {
        this.nroEmpleado = nroEmpleado;
    }

    public void agregarMovim() {
        cantMovimient++;
    }

    public int getCantidadMovimientos() {
        return cantMovimient;
    }

    @Override
    public String toString() {

        return getNombre() + " - " + getCedula();
    }
}
