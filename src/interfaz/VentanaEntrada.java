// Trabajo desarollado por: Facundo Esquivel (306504) y Nyah Rüting (270931).
package interfaz;

import dominio.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;
import java.util.Properties;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.jdatepicker.impl.*;

public class VentanaEntrada extends javax.swing.JFrame implements Observer {

    private Sistema sistema;
    private LocalDateTime fechaHoraSeleccionada;

    // Componentes del selector de fecha
    private JDatePanelImpl datePanel;
    private JDatePickerImpl datePicker;
    private UtilDateModel modeloDatePicker;

    public VentanaEntrada(Sistema sistema) {

        sistema.addObserver(this);
        initComponents();
        this.sistema = sistema;

        ImageIcon icono = new ImageIcon(getClass().getResource("logo.jpg"));
        this.setIconImage(icono.getImage());

        modeloDatePicker = new UtilDateModel();
        Properties p = new Properties();
        p.put("txt.today", "hoy");
        p.put("txt.month", "mes");
        p.put("txt.year", "año");

        datePanel = new JDatePanelImpl(modeloDatePicker, p);
        datePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());
        datePicker.setBounds(380, 80, 110, 30);

        panelEntrada.add(datePicker);
        actualizarColor();
        actualizarDatos();
        inicializaHoraMinutos();
        cargarHoraActual();
    }

    public void actualizarColor() {
        ColoresFondo.aplicarTema(this.getContentPane(), sistema.esTemaOscuro());
        ColoresFondo.aplicarTemaDatePicker(datePicker, sistema.esTemaOscuro());

    }

    public void inicializaHoraMinutos() {

        // Llenar ComboBox de horas (00 a 23)
        for (int hora = 0; hora < 24; hora++) {
            cmbHoras.addItem(String.format("%02d", hora));
        }

        // Llenar ComboBox de minutos (00 a 59)
        for (int min = 0; min < 60; min++) {
            cmbMinutos.addItem(String.format("%02d", min));
        }

        // Listener para cambios de fecha
        modeloDatePicker.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                actualizarFechaHoraSeleccionada();
            }
        });

    }

    private void cargarHoraActual() {
        LocalDateTime ahora = LocalDateTime.now();
        cmbHoras.setSelectedItem(String.format("%02d", ahora.getHour()));
        cmbMinutos.setSelectedItem(String.format("%02d", ahora.getMinute()));

        ((UtilDateModel) datePicker.getModel()).setDate(ahora.getYear(), ahora.getMonthValue() - 1, ahora.getDayOfMonth());
        ((UtilDateModel) datePicker.getModel()).setSelected(true);

        actualizarFechaHoraSeleccionada();
    }

    private void actualizarFechaHoraSeleccionada() {
        Date fechaSeleccionada = (Date) datePicker.getModel().getValue();

        if (cmbHoras.getSelectedIndex() != -1 && cmbMinutos.getSelectedIndex() != -1 && fechaSeleccionada != null) {
            int hora = Integer.parseInt((String) cmbHoras.getSelectedItem());
            int minuto = Integer.parseInt((String) cmbMinutos.getSelectedItem());
            LocalDate fecha = fechaSeleccionada.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalTime horaSeleccionada = LocalTime.of(hora, minuto);
            fechaHoraSeleccionada = LocalDateTime.of(fecha, horaSeleccionada);
        }
    }

    public void actualizarDatos() {
        listaVehiculosVentana.setListData(sistema.getVehiculoSinEntrada().toArray());

        cmbBoxEmpleado.removeAllItems();
        for (Empleado em : sistema.getListaEmpleados()) {
            cmbBoxEmpleado.addItem(em);

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        lblVehiculo = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listaVehiculosVentana = new javax.swing.JList();
        panelEntradas = new javax.swing.JPanel();
        cmbHoras = new javax.swing.JComboBox<>();
        cmbMinutos = new javax.swing.JComboBox<>();
        paneNotas = new javax.swing.JScrollPane();
        txtAreaNotas = new javax.swing.JTextArea();
        btnCancelar = new javax.swing.JButton();
        btnConfirmarEntrada = new javax.swing.JButton();
        lblMostrarBooleanContrato = new javax.swing.JLabel();
        lblContrato = new javax.swing.JLabel();
        cmbBoxEmpleado = new javax.swing.JComboBox();
        lblEmpleado = new javax.swing.JLabel();
        lblNotas = new javax.swing.JLabel();
        lblHora = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        subtitulo = new javax.swing.JLabel();
        panelEntrada = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Entradas");
        setResizable(false);
        getContentPane().setLayout(null);
        getContentPane().add(jSeparator1);
        jSeparator1.setBounds(40, 60, 560, 10);

        lblVehiculo.setText("Vehículo ");
        getContentPane().add(lblVehiculo);
        lblVehiculo.setBounds(40, 70, 120, 16);

        listaVehiculosVentana.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listaVehiculosVentana.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listaVehiculosVentanaValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(listaVehiculosVentana);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(40, 90, 190, 180);

        panelEntradas.setLayout(null);

        cmbHoras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbHorasActionPerformed(evt);
            }
        });
        panelEntradas.add(cmbHoras);
        cmbHoras.setBounds(450, 110, 72, 22);

        cmbMinutos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbMinutosActionPerformed(evt);
            }
        });
        panelEntradas.add(cmbMinutos);
        cmbMinutos.setBounds(540, 110, 72, 22);

        txtAreaNotas.setColumns(20);
        txtAreaNotas.setRows(5);
        paneNotas.setViewportView(txtAreaNotas);

        panelEntradas.add(paneNotas);
        paneNotas.setBounds(370, 180, 240, 90);

        btnCancelar.setText("Cancelar");
        panelEntradas.add(btnCancelar);
        btnCancelar.setBounds(290, 330, 140, 23);

        btnConfirmarEntrada.setText("Confirmar Entrada");
        btnConfirmarEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarEntrada(evt);
            }
        });
        panelEntradas.add(btnConfirmarEntrada);
        btnConfirmarEntrada.setBounds(120, 330, 150, 23);

        lblMostrarBooleanContrato.setText("Seleccione un vehículo");
        panelEntradas.add(lblMostrarBooleanContrato);
        lblMostrarBooleanContrato.setBounds(260, 290, 200, 16);

        lblContrato.setText("Contrato actual:");
        panelEntradas.add(lblContrato);
        lblContrato.setBounds(140, 290, 110, 16);

        cmbBoxEmpleado.setToolTipText("");
        panelEntradas.add(cmbBoxEmpleado);
        cmbBoxEmpleado.setBounds(420, 140, 190, 22);

        lblEmpleado.setText("Empleado que recibe:");
        panelEntradas.add(lblEmpleado);
        lblEmpleado.setBounds(250, 140, 150, 16);

        lblNotas.setText("Notas:");
        panelEntradas.add(lblNotas);
        lblNotas.setBounds(250, 180, 60, 16);

        lblHora.setText("Hora (HH:mm):");
        panelEntradas.add(lblHora);
        lblHora.setBounds(250, 110, 120, 16);

        lblFecha.setText("Fecha (dd/MM/yyyy):");
        panelEntradas.add(lblFecha);
        lblFecha.setBounds(250, 80, 190, 16);

        subtitulo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        subtitulo.setText("Registrar Entrada al Parking");
        panelEntradas.add(subtitulo);
        subtitulo.setBounds(240, 40, 190, 16);
        panelEntradas.add(panelEntrada);
        panelEntrada.setBounds(400, 70, 230, 50);

        getContentPane().add(panelEntradas);
        panelEntradas.setBounds(0, 0, 650, 410);

        setSize(new java.awt.Dimension(662, 419));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnConfirmarEntrada(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarEntrada

        boolean completo = true;

        Vehiculo vehSeleccionado = (Vehiculo) listaVehiculosVentana.getSelectedValue();
        Empleado empSeleccionado = (Empleado) cmbBoxEmpleado.getSelectedItem();

        if (vehSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un vehiculo de la lista.", "Vehiculo no seleccionado", JOptionPane.WARNING_MESSAGE);
            completo = false;
            return;
        } else if (empSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un empleado del combo.", "Empleado no seleccionado", JOptionPane.WARNING_MESSAGE);
            completo = false;
            return;
        } else {
            JOptionPane.showMessageDialog(this, "Entrada registrada correctamente para el vehiculo con matricula: " + vehSeleccionado.getMatricula(), "Entrada registrada", JOptionPane.INFORMATION_MESSAGE);

            sistema.agregarEntrada(fechaHoraSeleccionada, vehSeleccionado, txtAreaNotas.getText(), empSeleccionado);

            actualizarDatos();
        }

    }//GEN-LAST:event_btnConfirmarEntrada

    private void cmbMinutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbMinutosActionPerformed
        actualizarFechaHoraSeleccionada();
    }//GEN-LAST:event_cmbMinutosActionPerformed

    private void cmbHorasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbHorasActionPerformed

        actualizarFechaHoraSeleccionada();
    }//GEN-LAST:event_cmbHorasActionPerformed

    private void listaVehiculosVentanaValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listaVehiculosVentanaValueChanged
        if (!evt.getValueIsAdjusting()) {
            Vehiculo veh = (Vehiculo) listaVehiculosVentana.getSelectedValue();
            if (veh != null) {
                if (sistema.tieneContrato(veh)) {
                    lblMostrarBooleanContrato.setText("Tiene contrato");
                } else {
                    lblMostrarBooleanContrato.setText("No tiene contrato");
                }
            }
        }
    }//GEN-LAST:event_listaVehiculosVentanaValueChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnConfirmarEntrada;
    private javax.swing.JComboBox cmbBoxEmpleado;
    private javax.swing.JComboBox<String> cmbHoras;
    private javax.swing.JComboBox<String> cmbMinutos;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblContrato;
    private javax.swing.JLabel lblEmpleado;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblHora;
    private javax.swing.JLabel lblMostrarBooleanContrato;
    private javax.swing.JLabel lblNotas;
    private javax.swing.JLabel lblVehiculo;
    private javax.swing.JList listaVehiculosVentana;
    private javax.swing.JScrollPane paneNotas;
    private javax.swing.JPanel panelEntrada;
    private javax.swing.JPanel panelEntradas;
    private javax.swing.JLabel subtitulo;
    private javax.swing.JTextArea txtAreaNotas;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(Observable o, Object ob) {
        actualizarColor();
        actualizarDatos();
    }

}
