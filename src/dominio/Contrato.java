// Trabajo desarollado por: Facundo Esquivel (306504) y Nyah Rüting (270931).
package dominio;

import java.io.Serializable;
// Trabajo desarollado por: Facundo Esquivel (306504) y Nyah Rüting (270931).

public class Contrato implements Serializable {

    private static final long serialVersionUID = 1L;

    private int nroContrato;
    private Cliente cliente;
    private Empleado empleado;
    private Vehiculo vehiculo;
    private double precioMensual;

    public Contrato(int nroContrato, Cliente cliente, Vehiculo vehiculo, Empleado empleado, double valorMensual) {
        this.nroContrato = nroContrato;
        this.cliente = cliente;
        this.empleado = empleado;
        this.vehiculo = vehiculo;
        this.precioMensual = valorMensual;
    }

    public void setNroContrato(int nroContrato) {
        this.nroContrato = nroContrato;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public void setValorMensual(double precioMensual) {
        this.precioMensual = precioMensual;
    }

    public int getNroContrato() {
        return nroContrato;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public double getValorMensual() {
        return precioMensual;
    }

    @Override
    public String toString() {
        return getNroContrato() + " " + getCliente();
    }
}
