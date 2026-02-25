// Trabajo desarollado por: Facundo Esquivel (306504) y Nyah Rüting (270931).
package interfaz;

import dominio.*;
import java.util.Observable;
import java.util.Observer;
import javax.swing.ImageIcon;

public class VentanaConDetalles extends javax.swing.JFrame implements Observer {

    private Sistema sistema;

    public VentanaConDetalles(Sistema sistema) {

        initComponents();
        sistema.addObserver(this);
        this.sistema = sistema;
        ImageIcon icono = new ImageIcon(getClass().getResource("logo.jpg"));
        this.setIconImage(icono.getImage());
        actualizarColor();

    }

    public void actualizarColor() {
        ColoresFondo.aplicarTema(this.getContentPane(), sistema.esTemaOscuro());
    }

    public void mostrarCliente(Cliente c) {
        lbltitulo.setText("DATOS DEL CLIENTE");
        lblPrimera.setText("Nombre: " + c.getNombre());
        lblSegunda.setText("Cédula: " + c.getCedula());
        lblTecera.setText("Dirección: " + c.getDireccion());
        lblCuarta.setText("Celular: " + c.getCelular());
        lblQuinta.setText("Cliente desde: " + c.getCliDesde());
    }

    public void mostrarEmpleado(Empleado em) {
        lbltitulo.setText("DATOS DEL EMPLEADO");
        lblPrimera.setText("Nombre: " + em.getNombre());
        lblSegunda.setText("Cédula: " + em.getCedula());
        lblTecera.setText("Dirección: " + em.getDireccion());
        lblCuarta.setText("Número de empleado: " + em.getNroEmpleado());
        lblQuinta.setText("");
    }

    public void mostrarVehiculo(Vehiculo vehic) {
        lbltitulo.setText("DATOS DEL VEHICULO");
        lblPrimera.setText("Matricula: " + vehic.getMatricula());
        lblSegunda.setText("Marca: " + vehic.getMarca());
        lblTecera.setText("Modelo: " + vehic.getModelo());
        lblCuarta.setText("Estado: " + vehic.getEstado());
        lblQuinta.setText("");
    }

    public void mostrarServicioAdicional(ServicioAdicional servicio) {
        lbltitulo.setText("DATOS DEL SERVICIO");
        lblPrimera.setText("Tipo: " + servicio.getTipoServicio());
        lblSegunda.setText("Fecha: " + servicio.getFechaHora().toLocalDate());
        lblTecera.setText("Hora: " + servicio.getFechaHora().toLocalTime());
        lblCuarta.setText("Empleado: " + servicio.getEmpleado());
        lblQuinta.setText("Vehiculo: " + servicio.getVehiculo());
    }

    public void mostrarContrato(Contrato unContrato) {
        lbltitulo.setText("DATOS DEL CONTRATO");
        lblPrimera.setText("Cliente: " + unContrato.getCliente());
        lblSegunda.setText("Vehiculo: " + unContrato.getVehiculo());
        lblTecera.setText("Realizado por: " + unContrato.getEmpleado());
        lblCuarta.setText("Costo Mensual:  $" + unContrato.getValorMensual());
        lblQuinta.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbltitulo = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        lblPrimera = new javax.swing.JLabel();
        lblSegunda = new javax.swing.JLabel();
        lblTecera = new javax.swing.JLabel();
        lblCuarta = new javax.swing.JLabel();
        lblQuinta = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Información del Cliente"); // NOI18N
        setResizable(false);
        getContentPane().setLayout(null);

        lbltitulo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbltitulo.setText("TITULO: ");
        getContentPane().add(lbltitulo);
        lbltitulo.setBounds(70, 20, 240, 16);
        getContentPane().add(jSeparator1);
        jSeparator1.setBounds(66, 40, 240, 10);

        lblPrimera.setText("Nombre: ");
        getContentPane().add(lblPrimera);
        lblPrimera.setBounds(50, 68, 282, 16);

        lblSegunda.setText("Cédula:");
        getContentPane().add(lblSegunda);
        lblSegunda.setBounds(50, 96, 282, 16);

        lblTecera.setText("Dirección:");
        getContentPane().add(lblTecera);
        lblTecera.setBounds(50, 124, 282, 16);

        lblCuarta.setText("Celular:");
        getContentPane().add(lblCuarta);
        lblCuarta.setBounds(50, 152, 282, 16);

        lblQuinta.setText("Cliente desde: ");
        getContentPane().add(lblQuinta);
        lblQuinta.setBounds(50, 180, 282, 16);

        setSize(new java.awt.Dimension(394, 268));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblCuarta;
    private javax.swing.JLabel lblPrimera;
    private javax.swing.JLabel lblQuinta;
    private javax.swing.JLabel lblSegunda;
    private javax.swing.JLabel lblTecera;
    private javax.swing.JLabel lbltitulo;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(Observable o, Object arg) {
        actualizarColor();
    }

}
