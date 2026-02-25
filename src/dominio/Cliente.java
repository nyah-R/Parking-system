// Trabajo desarollado por: Facundo Esquivel (306504) y Nyah Rüting (270931).
package dominio;

import java.io.Serializable;
import java.util.ArrayList;

public class Cliente extends Persona implements Serializable {

    private static final long serialVersionUID = 1L;
    private String celular;
    private int cliDesde;
    private ArrayList<Contrato> listaContratos;

    public Cliente(String nombre, String cedula, String direccion, String celular, int cliDesde) {
        super(nombre, cedula, direccion);
        this.celular = celular;
        this.cliDesde = cliDesde;
        this.listaContratos = new ArrayList<Contrato>();
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public void setCliDesde(int cliDesde) {
        this.cliDesde = cliDesde;
    }

    public void setListaContratos(ArrayList<Contrato> listaContratos) {
        this.listaContratos = listaContratos;
    }

    public ArrayList<Contrato> getListaContratos() {
        return listaContratos;
    }

    public String getCelular() {
        return celular;
    }

    public int getCliDesde() {
        return cliDesde;
    }

    public void agregarContrato(Contrato contrato) {
        listaContratos.add(contrato);
    }

    public void eliminarContrato(Contrato contrato) {
        listaContratos.remove(contrato);
    }

    public boolean tieneContrato(Contrato contrato) {
        return listaContratos.contains(contrato);
    }

    @Override
    public String toString() {
        return this.getNombre() + " - " + this.getCedula();
    }

}
