// Trabajo desarollado por: Facundo Esquivel (306504) y Nyah Rüting (270931).
package interfaz;

import dominio.*;
import java.awt.BorderLayout;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;
import java.util.Properties;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class VentanaSalidas extends javax.swing.JFrame implements Observer {

    private Sistema sistema;
    private JDatePickerImpl datePicker;
    private LocalDateTime fechaHoraSeleccionada;
    private UtilDateModel modeloDatePicker;
    private Date fechaSeleccionada;
    private JDatePanelImpl datePanel;

    public VentanaSalidas(Sistema sistema) {
        sistema.addObserver(this);
        initComponents();
        this.sistema = sistema;
        actualizarDatos();

        ImageIcon icono = new ImageIcon(getClass().getResource("logo.jpg"));
        this.setIconImage(icono.getImage());

        crearDatePicker();

        actualizarColor();
        inicializaHoraMinutos();
        cargarHoraActual();
    }

    public void actualizarColor() {
        ColoresFondo.aplicarTema(this.getContentPane(), sistema.esTemaOscuro());
        ColoresFondo.aplicarTemaDatePicker(datePicker, sistema.esTemaOscuro());

    }

    public void crearDatePicker() {
        modeloDatePicker = new UtilDateModel();
        Properties propiedadesDatePicker = new Properties();
        propiedadesDatePicker.put("text.today", "Hoy");
        propiedadesDatePicker.put("text.month", "Mes");
        propiedadesDatePicker.put("text.year", "Año");
        datePanel = new JDatePanelImpl(modeloDatePicker, propiedadesDatePicker);
        datePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());

        // Agregamos el componente al panelFecha
        PanelSalida.setLayout(new BorderLayout());
        PanelSalida.add(datePicker, BorderLayout.CENTER);
        PanelSalida.revalidate();

        modeloDatePicker.setDate(LocalDate.now().getYear(), LocalDate.now().getMonthValue() - 1, LocalDate.now().getDayOfMonth());
        modeloDatePicker.setSelected(true);

        fechaSeleccionada = (Date) datePicker.getModel().getValue();

        // Listener para cambios de fecha
        modeloDatePicker.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                fechaSeleccionada = (Date) modeloDatePicker.getValue();

            }
        });

    }

    public void actualizarDatos() {
        listaEntradasSinSalidas.setListData(sistema.mostrarEntradaSinSalida().toArray());
        listaEmpleados.setListData(sistema.getListaEmpleados().toArray());
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
        int hora = Integer.parseInt((String) cmbHoras.getSelectedItem());
        int minuto = Integer.parseInt((String) cmbMinutos.getSelectedItem());

        Date fechaSeleccionada = (Date) datePicker.getModel().getValue();
        if (fechaSeleccionada != null) {
            LocalDate fecha = fechaSeleccionada.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalTime horaSeleccionada = LocalTime.of(hora, minuto);
            fechaHoraSeleccionada = LocalDateTime.of(fecha, horaSeleccionada);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        listaEntradasSinSalidas = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        listaEmpleados = new javax.swing.JList();
        cmbHoras = new javax.swing.JComboBox<>();
        cmbMinutos = new javax.swing.JComboBox<>();
        PanelSalida = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtComentario = new javax.swing.JTextArea();
        lblTiempo = new javax.swing.JLabel();
        lblContratoTiene = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnMarcarSalida = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Salidas");
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);
        getContentPane().setLayout(null);

        listaEntradasSinSalidas.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listaEntradasSinSalidas.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listaEntradasSinSalidasValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(listaEntradasSinSalidas);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(20, 113, 190, 270);

        listaEmpleados.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(listaEmpleados);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(240, 110, 200, 270);

        getContentPane().add(cmbHoras);
        cmbHoras.setBounds(480, 130, 90, 22);

        getContentPane().add(cmbMinutos);
        cmbMinutos.setBounds(610, 130, 90, 22);

        javax.swing.GroupLayout PanelSalidaLayout = new javax.swing.GroupLayout(PanelSalida);
        PanelSalida.setLayout(PanelSalidaLayout);
        PanelSalidaLayout.setHorizontalGroup(
            PanelSalidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 190, Short.MAX_VALUE)
        );
        PanelSalidaLayout.setVerticalGroup(
            PanelSalidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        getContentPane().add(PanelSalida);
        PanelSalida.setBounds(530, 60, 190, 40);

        jLabel1.setText("Fecha:");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(480, 60, 50, 16);

        txtComentario.setColumns(20);
        txtComentario.setRows(5);
        jScrollPane3.setViewportView(txtComentario);

        getContentPane().add(jScrollPane3);
        jScrollPane3.setBounds(480, 190, 234, 90);

        lblTiempo.setText("Tiempo: ");
        getContentPane().add(lblTiempo);
        lblTiempo.setBounds(460, 300, 210, 16);

        lblContratoTiene.setText("Contrato actual:");
        getContentPane().add(lblContratoTiene);
        lblContratoTiene.setBounds(460, 320, 250, 40);

        jLabel4.setText("Entradas");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(30, 90, 140, 16);

        jLabel5.setText("Empleado");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(240, 90, 140, 16);

        jLabel6.setText("Estado del vehiculo:");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(480, 170, 230, 16);

        btnMarcarSalida.setText("Marcar salida");
        btnMarcarSalida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMarcarSalidaPresionado(evt);
            }
        });
        getContentPane().add(btnMarcarSalida);
        btnMarcarSalida.setBounds(530, 370, 140, 23);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel7.setText("Registrar Salidas");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(40, 20, 540, 18);
        getContentPane().add(jSeparator1);
        jSeparator1.setBounds(40, 40, 680, 10);

        jLabel8.setText(":");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(590, 130, 37, 16);

        jLabel9.setText("Hora");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(480, 110, 80, 16);

        jLabel10.setText("Minutos");
        getContentPane().add(jLabel10);
        jLabel10.setBounds(610, 110, 90, 16);

        setSize(new java.awt.Dimension(769, 466));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnMarcarSalidaPresionado(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMarcarSalidaPresionado

        actualizarFechaHoraSeleccionada();
        Empleado empl = (Empleado) listaEmpleados.getSelectedValue();
        Entrada ent = (Entrada) listaEntradasSinSalidas.getSelectedValue();

        if (ent == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un vehiculo de la lista de entradas.", "Entrada no seleccionado", JOptionPane.WARNING_MESSAGE);
        } else if (empl == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un empleado de la lista.", "Empleado no seleccionado", JOptionPane.WARNING_MESSAGE);
        } else if (fechaHoraSeleccionada.isBefore(ent.getFechaHora())) {

            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            JOptionPane.showMessageDialog(this,
                 "La fecha y hora seleccionada debe ser posterior a la fecha y hora de la entrada.\n"
                 + "Entrada: " + ent.getFechaHora().format(fmt) + "\n"
                 + "Seleccionada: " + fechaHoraSeleccionada.format(fmt),
                 "Fecha inválida",
                 JOptionPane.WARNING_MESSAGE);

        } else {
            sistema.agregarSalida(fechaHoraSeleccionada, txtComentario.getText(), ent, empl);
// Calcular duración entre entrada y salida
            Duration duracion = Duration.between(ent.getFechaHora(), fechaHoraSeleccionada);
            long horas = duracion.toHours();
            long minutos = duracion.toMinutes() % 60;
            String tiempoEstadia = horas + "h " + minutos + "min";
            JOptionPane.showMessageDialog(
                 this,
                 "Salida registrada correctamente para el vehículo con entrada el:\n"
                 + ent.getFechaHora().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")),
                 "Salida registrada",
                 JOptionPane.INFORMATION_MESSAGE
            );
            lblTiempo.setText("Tiempo: " + tiempoEstadia);
            txtComentario.setText("");
            actualizarDatos();

        }

    }//GEN-LAST:event_btnMarcarSalidaPresionado

    private void listaEntradasSinSalidasValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listaEntradasSinSalidasValueChanged
        if (!evt.getValueIsAdjusting()) {
            Entrada entr = (Entrada) listaEntradasSinSalidas.getSelectedValue();
            if (entr != null) {
                Vehiculo veh = entr.getVehiculo();
                String matricula = veh.getMatricula();
                if (sistema.tieneContrato(veh)) {
                    lblContratoTiene.setText("<html>Matrícula: <b>" + matricula + "</b><br>Estado: <span style='color:green;'>CONTRATO ACTIVO</span></html>");
                } else {
                    lblContratoTiene.setText("<html>Matrícula: <b>" + matricula + "</b><br>Estado: <span style='color:red;'>SIN CONTRATO</span></html>");
                }
            }

        }        // TODO add your handling code here:
    }//GEN-LAST:event_listaEntradasSinSalidasValueChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelSalida;
    private javax.swing.JButton btnMarcarSalida;
    private javax.swing.JComboBox<String> cmbHoras;
    private javax.swing.JComboBox<String> cmbMinutos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblContratoTiene;
    private javax.swing.JLabel lblTiempo;
    private javax.swing.JList listaEmpleados;
    private javax.swing.JList listaEntradasSinSalidas;
    private javax.swing.JTextArea txtComentario;
    // End of variables declaration//GEN-END:variables
    @Override
    public void update(Observable o, Object ob) {
        actualizarColor();
        actualizarDatos();

    }

}
