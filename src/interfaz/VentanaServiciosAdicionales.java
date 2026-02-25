package interfaz;

import dominio.*;
import java.awt.BorderLayout;
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
import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class VentanaServiciosAdicionales extends javax.swing.JFrame implements Observer {

    private Sistema sistema;
    private JDatePanelImpl datePanel;
    private JDatePickerImpl datePicker;
    private UtilDateModel modeloDatePicker;

    // Variable para la fecha actual seleccionada
    private Date fechaSeleccionada;
    private LocalDateTime fechaHoraSeleccionada;

    public VentanaServiciosAdicionales(Sistema sistema) {
        sistema.addObserver(this);
        initComponents();
        this.sistema = sistema;
        lstServiciosAdicionales.setListData(sistema.mostrarServAdicional().toArray());
        cargarListas();
        actualizarColor();
        ImageIcon icono = new ImageIcon(getClass().getResource("logo.jpg"));
        this.setIconImage(icono.getImage());
        crearDatePicker();
        inicializaHoraMinutos();
        cargarHoraActual();
    }

    public void actualizarColor() {
        ColoresFondo.aplicarTema(this.getContentPane(), sistema.esTemaOscuro());
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
        panelDatePicker.setLayout(new BorderLayout());
        panelDatePicker.add(datePicker, BorderLayout.CENTER);
        panelDatePicker.revalidate();

        modeloDatePicker.setDate(LocalDate.now().getYear(), LocalDate.now().getMonthValue() - 1, LocalDate.now().getDayOfMonth());
        modeloDatePicker.setSelected(true);

        // Listener para cambios de fecha
        modeloDatePicker.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                fechaSeleccionada = (Date) modeloDatePicker.getValue();
            }
        });

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

    public void cargarListas() {
        lstEmpleado.setListData(sistema.getListaEmpleados().toArray(new Empleado[0]));
        lstVehiculo.setListData(sistema.getListaVehiculos().toArray(new Vehiculo[0]));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cmbTipo = new javax.swing.JComboBox();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstVehiculo = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstEmpleado = new javax.swing.JList<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtCosto = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        btbAgregar = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane3 = new javax.swing.JScrollPane();
        lstServiciosAdicionales = new javax.swing.JList();
        panelDatePicker = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        cmbHoras = new javax.swing.JComboBox<>();
        cmbMinutos = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Servicios Adicionales");
        setResizable(false);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel1.setText(" Gestión de Servicios Adicionales  ");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(60, 20, 220, 18);

        jLabel2.setText("Tipo de servicio:");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(30, 70, 125, 16);

        cmbTipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Lavado", "Cambio de rueda", "Limpieza de tapizado", "Cambio de luces", "Otro" }));
        cmbTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTipoActionPerformed(evt);
            }
        });
        getContentPane().add(cmbTipo);
        cmbTipo.setBounds(160, 70, 280, 22);
        getContentPane().add(jSeparator1);
        jSeparator1.setBounds(40, 40, 280, 10);

        jLabel3.setText("Fecha:");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(160, 110, 70, 16);

        jLabel4.setText("Hora:");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(160, 140, 90, 16);

        lstVehiculo.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(lstVehiculo);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(20, 210, 180, 270);

        lstEmpleado.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(lstEmpleado);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(230, 210, 220, 270);

        jLabel5.setText("Vehiculo");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(80, 190, 80, 16);

        jLabel6.setText("Empleado");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(250, 190, 100, 16);

        jLabel7.setText("Costo:");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(50, 500, 70, 20);
        getContentPane().add(txtCosto);
        txtCosto.setBounds(90, 500, 64, 22);

        jLabel8.setText("$");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(160, 490, 37, 40);

        btbAgregar.setText("Agregar Servicio");
        btbAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btbAgregarActionPerformed(evt);
            }
        });
        getContentPane().add(btbAgregar);
        btbAgregar.setBounds(250, 500, 200, 23);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel9.setText("Servicios Adicionales Realizados");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(500, 30, 240, 18);
        getContentPane().add(jSeparator2);
        jSeparator2.setBounds(490, 50, 220, 3);

        lstServiciosAdicionales.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tipo - Vehiculo  - Costo", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP));
        lstServiciosAdicionales.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lstServiciosAdicionales.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstServiciosAdicionalesValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(lstServiciosAdicionales);

        getContentPane().add(jScrollPane3);
        jScrollPane3.setBounds(480, 70, 270, 450);
        getContentPane().add(panelDatePicker);
        panelDatePicker.setBounds(250, 100, 190, 30);

        jLabel10.setText(":");
        getContentPane().add(jLabel10);
        jLabel10.setBounds(360, 130, 30, 40);

        getContentPane().add(cmbHoras);
        cmbHoras.setBounds(280, 140, 72, 22);

        getContentPane().add(cmbMinutos);
        cmbMinutos.setBounds(370, 140, 72, 22);

        setSize(new java.awt.Dimension(796, 579));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btbAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btbAgregarActionPerformed
        try {
            if (fechaHoraSeleccionada == null) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar una fecha y hora.", "Datos incompletos", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (cmbTipo.getSelectedItem() == null || ((String) cmbTipo.getSelectedItem()).trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar un tipo de servicio.", "Datos incompletos", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (txtCosto.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe ingresar un costo para el servicio.", "Datos incompletos", JOptionPane.WARNING_MESSAGE);
                return;
            }

            double costo;
            try {
                costo = Double.parseDouble(txtCosto.getText().trim());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "El costo ingresado no es válido. Debe ser un número.", "Formato incorrecto", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (lstVehiculo.getSelectedValue() == null) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar un vehículo.", "Datos incompletos", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (lstEmpleado.getSelectedValue() == null) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar un empleado.", "Datos incompletos", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Si todo está correcto, se agrega el servicio
            sistema.agregarServicioAdicional(
                 fechaHoraSeleccionada,
                 (String) cmbTipo.getSelectedItem(),
                 costo,
                 lstVehiculo.getSelectedValue(),
                 lstEmpleado.getSelectedValue()
            );

            lstServiciosAdicionales.setListData(sistema.mostrarServAdicional().toArray());

            JOptionPane.showMessageDialog(this, "Servicio adicional registrado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Ocurrió un error al registrar el servicio: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btbAgregarActionPerformed

    private void lstServiciosAdicionalesValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstServiciosAdicionalesValueChanged

        if (!evt.getValueIsAdjusting()) {
            ServicioAdicional servicio = (ServicioAdicional) lstServiciosAdicionales.getSelectedValue();
            if (servicio != null) {
                VentanaConDetalles vdc = new VentanaConDetalles(sistema);
                vdc.mostrarServicioAdicional(servicio);
                vdc.setVisible(true);
            }
        }

    }//GEN-LAST:event_lstServiciosAdicionalesValueChanged

    private void cmbTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbTipoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btbAgregar;
    private javax.swing.JComboBox<String> cmbHoras;
    private javax.swing.JComboBox<String> cmbMinutos;
    private javax.swing.JComboBox cmbTipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
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
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JList<Empleado> lstEmpleado;
    private javax.swing.JList lstServiciosAdicionales;
    private javax.swing.JList<Vehiculo> lstVehiculo;
    private javax.swing.JPanel panelDatePicker;
    private javax.swing.JTextField txtCosto;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(Observable o, Object ob) {
        actualizarColor();
        cargarListas();
    }

}
