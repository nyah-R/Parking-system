// Trabajo desarollado por: Facundo Esquivel (306504) y Nyah Rüting (270931).
package dominio;

import java.io.Serializable;
import java.time.*;
import java.util.*;

public class Sistema extends Observable implements Serializable {

    private static final long serialVersionUID = 1L;

    private ArrayList<Cliente> listaClientes;
    private ArrayList<Empleado> listaEmpleados;
    private ArrayList<Vehiculo> listaVehiculos;
    private ArrayList<Contrato> listaContratos;
    private ArrayList<Movimiento> listaMovimientos;
    private boolean temaOscuro = false;
    private int ultimoContrato = 0;

    public Sistema() {

        listaClientes = new ArrayList<>();
        listaEmpleados = new ArrayList<>();
        listaVehiculos = new ArrayList<>();
        listaContratos = new ArrayList<>();
        listaMovimientos = new ArrayList<>();

    }

    //MÉTODOS GETTERS Y SETTERS
    public ArrayList<Cliente> getListaClientes() {
        return listaClientes;
    }

    public ArrayList<Empleado> getListaEmpleados() {
        return listaEmpleados;
    }

    public ArrayList<Vehiculo> getListaVehiculos() {
        return listaVehiculos;
    }

    public ArrayList<Movimiento> getListaMovimientos() {
        return listaMovimientos;
    }

    public ArrayList<Contrato> getListaContratos() {
        return listaContratos;
    }

    //MÉTODOS PARA AGREGAR- ELIMINAR ELEMENTOS DE LAS LISTAS
    public void agregarCliente(String nombre, String cedula, String direccion, String celular, int cliDesde) {
        Cliente unCliente = new Cliente(nombre, cedula, direccion, celular, cliDesde);
        listaClientes.add(unCliente);
        setChanged();
        notifyObservers();

    }

    public void eliminarCliente(Cliente unCliente) {
        ArrayList<Contrato> contratosARemover = new ArrayList<>();
        for (Contrato contrato : listaContratos) {
            if (contrato.getCliente().equals(unCliente)) {
                contratosARemover.add(contrato);
            }
        }
        listaContratos.removeAll(contratosARemover);
        listaClientes.remove(unCliente);
        setChanged();
        notifyObservers();

    }

    public void agregarVehiculo(String matricula, String marca, String modelo, String estado) {
        Vehiculo unVehiculo = new Vehiculo(matricula, marca, modelo, estado);
        listaVehiculos.add(unVehiculo);
        setChanged();
        notifyObservers();

    }

    public void eliminarVehiculo(Vehiculo unVehiculo) {
        listaVehiculos.remove(unVehiculo);
        setChanged();
        notifyObservers();

    }

    public void agregarEmpleado(String nombre, String cedula, String direccion, int nroEmpleado) {
        Empleado unEmpleado = new Empleado(nombre, cedula, direccion, nroEmpleado);
        listaEmpleados.add(unEmpleado);
        setChanged();
        notifyObservers();

    }

    public void eliminarEmpleado(Empleado unEmpleado) {
        listaEmpleados.remove(unEmpleado);
        setChanged();
        notifyObservers();

    }

    public void agregarServicioAdicional(LocalDateTime unaFechaHora, String unTipoServicio, double unCosto, Vehiculo unVehiculo, Empleado unEmpleado) {
        ServicioAdicional sa = new ServicioAdicional(unaFechaHora, unTipoServicio, unCosto, unVehiculo, unEmpleado);
        listaMovimientos.add(sa);
        unEmpleado.agregarMovim();
        setChanged();
        notifyObservers();

    }

    public void agregarEntrada(LocalDateTime unaFechaHora, Vehiculo unVehiculo, String unaNota, Empleado unEmpleado) {
        Entrada entrada = new Entrada(unaFechaHora, unVehiculo, unaNota, unEmpleado);
        listaMovimientos.add(entrada);
        unEmpleado.agregarMovim();
        setChanged();
        notifyObservers();
    }

    public void agregarSalida(LocalDateTime unaFechaHora, String unComentarioEstadoVehiculo, Entrada unaEntrada, Empleado unEmpleado) {
        Salida salida = new Salida(unaFechaHora, unComentarioEstadoVehiculo, unaEntrada, unEmpleado);
        listaMovimientos.add(salida);
        unEmpleado.agregarMovim();
        setChanged();
        notifyObservers();
        unaEntrada.setSalida(salida);
    }

    public void eliminarMovimiento(Movimiento unMovimiento) {
        listaMovimientos.remove(unMovimiento);
        setChanged();
        notifyObservers();
    }

    // AGREGAR CONTRATO
    public void agregarContrato(Cliente cli, Vehiculo vehic, Empleado empl, int precioMensual) {
        ultimoContrato++;
        Contrato nuevo = new Contrato(ultimoContrato, cli, vehic, empl, precioMensual);
        listaContratos.add(nuevo);
        cli.agregarContrato(nuevo);
        setChanged();
        notifyObservers();

    }

    public ArrayList<ServicioAdicional> mostrarServAdicional() {
        ArrayList<ServicioAdicional> listaServiciosAd = new ArrayList<>();
        for (Movimiento m : listaMovimientos) {
            if (m instanceof ServicioAdicional) {
                ServicioAdicional sa = (ServicioAdicional) m;
                listaServiciosAd.add(sa);
            }
        }
        return listaServiciosAd;
    }

    //    VALIDACIÓN DE CÉDULAS Y DE MATRICULAS
    public boolean validarExisteCedula(String unaCedula) {
        boolean existe = false;
        for (Cliente cliente : listaClientes) {
            if (cliente.getCedula().equals(unaCedula)) {
                existe = true;
            }
        }
        for (Empleado empleado : listaEmpleados) {
            if (empleado.getCedula().equals(unaCedula)) {
                existe = true;
            }
        }
        return existe;
    }

    public boolean validarExisteMatricula(String unaMatricula) {
        boolean existe = false;
        for (Vehiculo v : listaVehiculos) {
            if ((v.getMatricula().equalsIgnoreCase(unaMatricula)) || existe) {
                existe = true;
            }
        }
        return existe;
    }

    public boolean esTemaOscuro() {
        return temaOscuro;
    }

    public void cambiarTema() {
        this.temaOscuro = !this.temaOscuro;
        setChanged();
        notifyObservers();
    }

    public boolean tieneContrato(Vehiculo seleccionado) {
        boolean tiene = false;
        for (Contrato con : listaContratos) {
            if (con.getVehiculo().equals(seleccionado) || tiene) {
                tiene = true;
            }
        }
        return tiene;
    }

    public ArrayList<Entrada> mostrarEntradaSinSalida() {
        ArrayList<Entrada> listaEntradas = new ArrayList<>();
        for (Movimiento m : listaMovimientos) {
            if (m instanceof Entrada) {
                Entrada en = (Entrada) m;
                if (en.getSalida() == null) {
                    listaEntradas.add(en);
                }

            }
        }

        return listaEntradas;
    }

    public ArrayList<Vehiculo> mostrarVehiculosSinContrato() {
        ArrayList<Vehiculo> resultado = new ArrayList<>();

        for (Vehiculo veh : listaVehiculos) {
            boolean tieneContrato = false;

            for (Contrato c : listaContratos) {
                if (c.getVehiculo().equals(veh)) {
                    tieneContrato = true;
                    break;
                }

            }
            if (!tieneContrato) {
                resultado.add(veh);
            }
        }
        return resultado;
    }

    public ArrayList<Vehiculo> mostrarVehiculosFueraDelParking() {
        ArrayList<Vehiculo> resultado = new ArrayList<>();

        for (Vehiculo v : listaVehiculos) {
            boolean entro = false;
            boolean estaAfuera = true;

            for (Movimiento m : listaMovimientos) {
                if (m instanceof Entrada en && en.getVehiculo() != null && en.getVehiculo().equals(v)) {
                    entro = true;

                    // Si la entrada no tiene salida, entonces está adentro
                    if (en.getSalida() == null) {
                        estaAfuera = false;
                        break; // Ya sabemos que está adentro, no lo queremos
                    }
                }
            }

            if (!entro || estaAfuera) {
                resultado.add(v);
            }
        }

        return resultado;
    }

    public ArrayList<Vehiculo> getVehiculoSinEntrada() {
        ArrayList<Vehiculo> vehiculosSinE = new ArrayList<>();

        for (Vehiculo vehiculo : getListaVehiculos()) {
            boolean tieneEntrada = false;

            for (Movimiento m : listaMovimientos) {
                if (m instanceof Entrada) {
                    Entrada en = (Entrada) m;
                    if (en.getVehiculo() != null && en.getVehiculo().equals(vehiculo) && en.getSalida() == null) {
                        tieneEntrada = true;
                        break;
                    }
                }
            }
            if (!tieneEntrada) {
                vehiculosSinE.add(vehiculo);
            }
        }
        return vehiculosSinE;
    }

    public ArrayList<Movimiento> obtenerMovimientosPorRango(LocalDate fecha, LocalTime desde, LocalTime hasta) {
        ArrayList<Movimiento> resultado = new ArrayList<>();
        for (Movimiento mov : listaMovimientos) {
            LocalDateTime fechaHora = mov.getFechaHora();
            LocalDate f = fechaHora.toLocalDate();
            LocalTime h = fechaHora.toLocalTime();
            if (f.equals(fecha)
                 && (h.equals(desde) || h.equals(hasta) || (h.isAfter(desde) && h.isBefore(hasta)))) {
                resultado.add(mov);
            }
        }

        return resultado;
    }

    public long obtenerEstadiaMasLarga() {
        long maxEstadia = 0;

        for (Movimiento mov : listaMovimientos) {
            if (mov instanceof Salida) {
                Salida salida = (Salida) mov;
                long tiempo = salida.getTiempoEstadia();
                if (tiempo > maxEstadia) {
                    maxEstadia = tiempo;
                }
            }
        }

        return maxEstadia;
    }

    public ArrayList<String> obtenerTopClientesConMasVehiculos() {
        Collections.sort(listaClientes, new Comparator<Cliente>() {
            @Override
            public int compare(Cliente c1, Cliente c2) {
                return Integer.compare(c2.getListaContratos().size(), c1.getListaContratos().size());
            }
        });

        ArrayList<String> resultado = new ArrayList<>();
        for (Cliente c : listaClientes) {
            int cantidad = c.getListaContratos().size();
            resultado.add(cantidad + " - " + c.toString());
        }

        return resultado;
    }

    public ArrayList<String> obtenerTopEmpleadosConMenosMovimientos() {
        Collections.sort(listaEmpleados, new Comparator<Empleado>() {
            @Override
            public int compare(Empleado e1, Empleado e2) {
                return Integer.compare(e1.getCantidadMovimientos(), e2.getCantidadMovimientos());
            }
        });

        ArrayList<String> resultado = new ArrayList<>();
        for (Empleado e : listaEmpleados) {
            int cantidad = e.getCantidadMovimientos();
            resultado.add(cantidad + " - " + e.toString());
        }

        return resultado;
    }

    public ArrayList<String> obtenerTopServiciosUtilizados() {
        String[] tiposServicios = {"Lavado", "Cambio de rueda", "Limpieza de tapizado", "Cambio de luces", "Otro"};
        int[] conteos = new int[tiposServicios.length];

        // Contar cantidad de veces que se usa servicio
        for (Movimiento mov : listaMovimientos) {
            if (mov instanceof ServicioAdicional) {
                ServicioAdicional sa = (ServicioAdicional) mov;
                String tipo = sa.getTipoServicio();

                // Buscar el índice correspondiente al tipo
                for (int i = 0; i < tiposServicios.length; i++) {
                    if (tiposServicios[i].equalsIgnoreCase(tipo)) {
                        conteos[i]++;
                        break;
                    }
                }
            }
        }

        // Ordenar los arrays en paralelo por cantidad descendente
        for (int i = 0; i < tiposServicios.length - 1; i++) {
            for (int j = i + 1; j < tiposServicios.length; j++) {
                if (conteos[j] > conteos[i]) {
                    // Intercambiar cantidades
                    int auxCont = conteos[i];
                    conteos[i] = conteos[j];
                    conteos[j] = auxCont;

                    // Intercambiar tipos
                    String auxTipo = tiposServicios[i];
                    tiposServicios[i] = tiposServicios[j];
                    tiposServicios[j] = auxTipo;
                }
            }
        }

        ArrayList<String> resultado = new ArrayList<>();
        for (int i = 0; i < tiposServicios.length; i++) {
            if (conteos[i] > 0) {
                resultado.add(conteos[i] + " - " + tiposServicios[i]);
            }
        }

        return resultado;
    }

}
