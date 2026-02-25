// Trabajo desarollado por: Facundo Esquivel (306504) y Nyah Rüting (270931).
package interfaz;

import dominio.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.*;
import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class VentanaReportes extends javax.swing.JFrame implements Observer {

    // Referencia al sistema principal
    private Sistema sistema;
    private Date fechaSeleccionada;

    // Ordenadores de filas para filtros a las JTables
    private TableRowSorter<DefaultTableModel> sorterHistorialMovimientos;

    // Modelos para Table
    private DefaultTableModel modeloTblMovHistorial;

    // Componentes del selector de fecha
    private JDatePanelImpl datePanel;
    private JDatePickerImpl datePicker;
    private UtilDateModel modeloDatePicker;

    public VentanaReportes(Sistema unSistema) {

        sistema = unSistema;

        // Subscribirse como Observador de cambios en el Sistema
        sistema.addObserver(this);

        // Inicializa componentes
        initComponents();

        // Aplica tema general
        actualizarColor();

        // Establece icono de la ventana
        ImageIcon icono = new ImageIcon(getClass().getResource("logo.jpg"));
        this.setIconImage(icono.getImage());

        // Asocia el sorter a la tabla de movimientos para permitir filtrado
        DefaultTableModel modeloTblMovHistorial = (DefaultTableModel) tblMovimientos.getModel();
        sorterHistorialMovimientos = new TableRowSorter<>(modeloTblMovHistorial);
        tblMovimientos.setRowSorter(sorterHistorialMovimientos);

        // Inicializa el panel con botones por franja horaria
        crearDatePicker();
        // Carga todos los movimientos en la tabla de Historial
        cargarTblMovHistorial();
        // Llena el ComboBox con los vehículos en la pestaña de Historial
        cargarCmbMovHistorial();

        cargarEstadisticas();
    }

    private Color colorPorCantidad(int cantidad) {
        if (cantidad < 5) {
            return Color.GREEN;
        } else if (cantidad <= 8) {
            return Color.YELLOW;
        } else {
            return Color.RED;
        }
    }

    private void cargarEstadisticas() {
        DefaultListModel<String> modeloServicios = new DefaultListModel<>();
        for (String linea : sistema.obtenerTopServiciosUtilizados()) {
            modeloServicios.addElement(linea);
        }
        lstTopUsoServicios.setModel(modeloServicios);

        DefaultListModel<String> modeloClientes = new DefaultListModel<>();
        for (String linea : sistema.obtenerTopClientesConMasVehiculos()) {
            modeloClientes.addElement(linea);
        }
        lstTopClientesVehiculos.setModel(modeloClientes);

        DefaultListModel<String> modeloEmpleados = new DefaultListModel<>();
        for (String linea : sistema.obtenerTopEmpleadosConMenosMovimientos()) {
            modeloEmpleados.addElement(linea);
        }
        lstTopEmpleadosLibres.setModel(modeloEmpleados);

        lblTopEstadiaValor.setText(String.valueOf(sistema.obtenerEstadiaMasLarga() + " minutos"));
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

        fechaSeleccionada = (Date) datePicker.getModel().getValue();
        construirGrillaMovimientos();

        // Listener para cambios de fecha
        modeloDatePicker.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                fechaSeleccionada = (Date) modeloDatePicker.getValue();
                construirGrillaMovimientos();

            }
        });

    }

    public void actualizarColor() {
        ColoresFondo.aplicarTema(this.getContentPane(), sistema.esTemaOscuro());
    }

    private void construirGrillaMovimientos() {
        panelGrillaMovimientos.removeAll();

        // Asegurar layout de 4 filas por 3 columnas
        panelGrillaMovimientos.setLayout(new java.awt.GridLayout(4, 3));

        String[] rangos = {"00:00-05:59", "06:00-11:59", "12:00-17:59", "18:00-23:59"};

        LocalDate baseDate = fechaSeleccionada.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        ArrayList<Movimiento> movimientos = sistema.getListaMovimientos();

        for (int fila = 0; fila < 4; fila++) {
            String[] partes = rangos[fila].split("-");
            LocalTime desde = LocalTime.parse(partes[0]);
            LocalTime hasta = LocalTime.parse(partes[1]);

            for (int col = 0; col < 3; col++) {
                LocalDate dia = baseDate.plusDays(col);
                int cantidad = 0;

                // Contar movimientos en ese rango
                for (int i = 0; i < movimientos.size(); i++) {
                    Movimiento mov = movimientos.get(i);

                    LocalDateTime fechaHora = mov.getFechaHora();
                    LocalDate fechaMov = fechaHora.toLocalDate();
                    LocalTime horaMov = fechaHora.toLocalTime();

                    if (fechaMov.equals(dia)
                         && !horaMov.isBefore(desde)
                         && !horaMov.isAfter(hasta)) {
                        cantidad++;
                    }
                }

                // Crear botón
                JButton nuevo = new JButton("<html>" + dia + "<br>" + rangos[fila] + "</html>");
                nuevo.setMargin(new Insets(-5, -5, -5, -5));
                nuevo.setForeground(Color.WHITE);
                nuevo.setBackground(colorPorCantidad(cantidad));
                nuevo.addActionListener(new MovListener());
                nuevo.putClientProperty("fecha", dia);
                nuevo.putClientProperty("desde", desde);
                nuevo.putClientProperty("hasta", hasta);

                panelGrillaMovimientos.add(nuevo);
            }
        }

        panelGrillaMovimientos.revalidate();
        panelGrillaMovimientos.repaint();
    }

    public void cargarCmbMovHistorial() {
        cmbVehiculo.removeAllItems();
        for (Vehiculo vec : sistema.getListaVehiculos()) {
            cmbVehiculo.addItem(vec);
        }
    }

    private void cargarTblMovHistorial() {
        modeloTblMovHistorial = (DefaultTableModel) tblMovimientos.getModel();
        modeloTblMovHistorial.setRowCount(0);
        for (Movimiento mov : sistema.getListaMovimientos()) {
            String tipo = mov.getClass().getSimpleName();
            if (tipo.equals("ServicioAdicional")) {
                tipo = "Servicio Adicional";
            }
            modeloTblMovHistorial.addRow(new Object[]{
                mov.getFechaHora(),
                tipo,
                mov.getVehiculo().toString()
            });
        }

        List<RowSorter.SortKey> clavesOrden = new ArrayList<>();
        clavesOrden.add(new RowSorter.SortKey(0, SortOrder.DESCENDING));
        sorterHistorialMovimientos.setSortKeys(clavesOrden);
        sorterHistorialMovimientos.sort();

    }

    private void aplicarFiltrosMovHistorial() {
        String tipo = (String) cmbMovimiento.getSelectedItem();
        Object vehiculoSeleccionado = cmbVehiculo.getSelectedItem();

        List<RowFilter<Object, Object>> filtros = new ArrayList<>();

        // Filtrar por tipo de movimiento
        if (tipo != null && !"Todos".equals(tipo)) {
            filtros.add(RowFilter.regexFilter("^" + tipo + "$", 1));
        }

        // Filtrar por vehículo (asume que toString del vehículo representa su valor en la tabla)
        if (vehiculoSeleccionado != null && !"Todos".equals(vehiculoSeleccionado.toString())) {
            String valorVehiculo = vehiculoSeleccionado.toString();
            filtros.add(RowFilter.regexFilter("^" + valorVehiculo + "$", 2));
        }

        // Aplicar filtro combinado
        if (filtros.isEmpty()) {
            sorterHistorialMovimientos.setRowFilter(null);
        } else {
            sorterHistorialMovimientos.setRowFilter(RowFilter.andFilter(filtros));
        }

        // Orden descendente por columna 0 (fecha/hora)
        List<RowSorter.SortKey> clavesOrden = new ArrayList<>();
        clavesOrden.add(new RowSorter.SortKey(0, SortOrder.DESCENDING));
        sorterHistorialMovimientos.setSortKeys(clavesOrden);
        sorterHistorialMovimientos.sort();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        panelHistorial = new javax.swing.JPanel();
        lblVehiculo = new javax.swing.JLabel();
        lblMovimientos = new javax.swing.JLabel();
        cmbVehiculo = new javax.swing.JComboBox();
        cmbMovimiento = new javax.swing.JComboBox<>();
        btnExportar = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblMovimientos = new javax.swing.JTable();
        panelMovimientos = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        panelDatePicker = new javax.swing.JPanel();
        panelGrillaMovimientos = new javax.swing.JPanel();
        panelEstadisticas = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstTopUsoServicios = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstTopClientesVehiculos = new javax.swing.JList<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        lstTopEmpleadosLibres = new javax.swing.JList<>();
        lblTopUsoServicios = new javax.swing.JLabel();
        lblTopClientesVehiculos = new javax.swing.JLabel();
        lblTopEmpleadosLibres = new javax.swing.JLabel();
        lblTopEstadia = new javax.swing.JLabel();
        lblTopEstadiaValor = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Reportes");
        setResizable(false);

        jTabbedPane1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        panelHistorial.setLayout(null);

        lblVehiculo.setText("Vehiculo");
        panelHistorial.add(lblVehiculo);
        lblVehiculo.setBounds(30, 50, 100, 20);

        lblMovimientos.setText("Movimientos");
        panelHistorial.add(lblMovimientos);
        lblMovimientos.setBounds(30, 80, 100, 20);

        cmbVehiculo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Todos" }));
        cmbVehiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbVehiculoActionPerformed(evt);
            }
        });
        panelHistorial.add(cmbVehiculo);
        cmbVehiculo.setBounds(130, 50, 200, 22);

        cmbMovimiento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Entrada", "Salida", "Servicio Adicional" }));
        cmbMovimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbMovimientoActionPerformed(evt);
            }
        });
        panelHistorial.add(cmbMovimiento);
        cmbMovimiento.setBounds(130, 80, 200, 22);

        btnExportar.setText("Exportar tabla a archivo");
        btnExportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportarActionPerformed(evt);
            }
        });
        panelHistorial.add(btnExportar);
        btnExportar.setBounds(30, 120, 300, 23);

        tblMovimientos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Fecha y Hora", "Tipo", "Vehiculo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblMovimientos.setShowHorizontalLines(true);
        tblMovimientos.setShowVerticalLines(true);
        jScrollPane4.setViewportView(tblMovimientos);

        panelHistorial.add(jScrollPane4);
        jScrollPane4.setBounds(30, 160, 780, 240);

        jTabbedPane1.addTab("Historial", panelHistorial);

        panelMovimientos.setLayout(null);

        jLabel5.setText("Fecha base");
        panelMovimientos.add(jLabel5);
        jLabel5.setBounds(26, 31, 100, 20);

        javax.swing.GroupLayout panelDatePickerLayout = new javax.swing.GroupLayout(panelDatePicker);
        panelDatePicker.setLayout(panelDatePickerLayout);
        panelDatePickerLayout.setHorizontalGroup(
            panelDatePickerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );
        panelDatePickerLayout.setVerticalGroup(
            panelDatePickerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        panelMovimientos.add(panelDatePicker);
        panelDatePicker.setBounds(110, 30, 150, 30);

        panelGrillaMovimientos.setLayout(new java.awt.GridLayout(4, 3));
        panelMovimientos.add(panelGrillaMovimientos);
        panelGrillaMovimientos.setBounds(30, 80, 750, 290);

        jTabbedPane1.addTab("Movimientos", panelMovimientos);

        panelEstadisticas.setLayout(null);

        lstTopUsoServicios.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(lstTopUsoServicios);

        panelEstadisticas.add(jScrollPane1);
        jScrollPane1.setBounds(30, 70, 220, 220);

        lstTopClientesVehiculos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(lstTopClientesVehiculos);

        panelEstadisticas.add(jScrollPane2);
        jScrollPane2.setBounds(280, 70, 240, 220);

        lstTopEmpleadosLibres.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane3.setViewportView(lstTopEmpleadosLibres);

        panelEstadisticas.add(jScrollPane3);
        jScrollPane3.setBounds(540, 70, 260, 220);

        lblTopUsoServicios.setText("Top 5 uso de servicios adicionales");
        panelEstadisticas.add(lblTopUsoServicios);
        lblTopUsoServicios.setBounds(30, 40, 240, 30);

        lblTopClientesVehiculos.setText("Top 10 clientes con más vehículos");
        panelEstadisticas.add(lblTopClientesVehiculos);
        lblTopClientesVehiculos.setBounds(280, 40, 250, 30);

        lblTopEmpleadosLibres.setText("Top 10 empleados con menos movimientos");
        panelEstadisticas.add(lblTopEmpleadosLibres);
        lblTopEmpleadosLibres.setBounds(540, 40, 280, 30);

        lblTopEstadia.setText("Estadía más larga:");
        panelEstadisticas.add(lblTopEstadia);
        lblTopEstadia.setBounds(30, 330, 130, 20);

        lblTopEstadiaValor.setText("---------");
        panelEstadisticas.add(lblTopEstadiaValor);
        lblTopEstadiaValor.setBounds(160, 330, 160, 20);

        jTabbedPane1.addTab("Estadísticas Generales", panelEstadisticas);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 836, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 456, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(60, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(911, 570));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarActionPerformed
        Vehiculo v = (Vehiculo) cmbVehiculo.getSelectedItem();
        String nombreArchivoBase = v.getMatricula();

        if (nombreArchivoBase == null || nombreArchivoBase.equals("Todos")) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un vehículo válido para exportar.", "Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Construir ruta al directorio del proyecto
        String nombreArchivoCSV = nombreArchivoBase + ".csv";
        File archivo = new File(System.getProperty("user.dir"), nombreArchivoCSV);

        try (PrintWriter pw = new PrintWriter(new FileWriter(archivo))) {
            for (int i = 0; i < tblMovimientos.getColumnCount(); i++) {
                pw.print(tblMovimientos.getColumnName(i));
                if (i < tblMovimientos.getColumnCount() - 1) {
                    pw.print(",");
                }
            }
            pw.println();

            // Escribir solo las filas visibles
            for (int j = 0; j < tblMovimientos.getRowCount(); j++) {
                for (int i = 0; i < tblMovimientos.getColumnCount(); i++) {
                    Object valor = tblMovimientos.getValueAt(j, i);
                    pw.print(valor != null ? valor.toString() : "");
                    if (i < tblMovimientos.getColumnCount() - 1) {
                        pw.print(",");
                    }
                }
                pw.println();
            }

            JOptionPane.showMessageDialog(this, "Archivo exportado como:\n" + archivo.getAbsolutePath());

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al guardar el archivo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnExportarActionPerformed

    private void cmbVehiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbVehiculoActionPerformed
        aplicarFiltrosMovHistorial();
    }//GEN-LAST:event_cmbVehiculoActionPerformed

    private void cmbMovimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbMovimientoActionPerformed
        aplicarFiltrosMovHistorial();
    }//GEN-LAST:event_cmbMovimientoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExportar;
    private javax.swing.JComboBox<String> cmbMovimiento;
    private javax.swing.JComboBox cmbVehiculo;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblMovimientos;
    private javax.swing.JLabel lblTopClientesVehiculos;
    private javax.swing.JLabel lblTopEmpleadosLibres;
    private javax.swing.JLabel lblTopEstadia;
    private javax.swing.JLabel lblTopEstadiaValor;
    private javax.swing.JLabel lblTopUsoServicios;
    private javax.swing.JLabel lblVehiculo;
    private javax.swing.JList<String> lstTopClientesVehiculos;
    private javax.swing.JList<String> lstTopEmpleadosLibres;
    private javax.swing.JList<String> lstTopUsoServicios;
    private javax.swing.JPanel panelDatePicker;
    private javax.swing.JPanel panelEstadisticas;
    private javax.swing.JPanel panelGrillaMovimientos;
    private javax.swing.JPanel panelHistorial;
    private javax.swing.JPanel panelMovimientos;
    private javax.swing.JTable tblMovimientos;
    // End of variables declaration//GEN-END:variables
    private class MovListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton cual = (JButton) e.getSource();

            LocalDate fecha = (LocalDate) cual.getClientProperty("fecha");
            LocalTime desde = (LocalTime) cual.getClientProperty("desde");
            LocalTime hasta = (LocalTime) cual.getClientProperty("hasta");

            if (sistema.obtenerMovimientosPorRango(fecha, desde, hasta).isEmpty()) {
                JOptionPane.showMessageDialog(null, "No hay movimientos en este tramo.");
            } else {
                VentanaMovimiento vm = new VentanaMovimiento(sistema, fecha, desde, hasta);
                vm.setVisible(true);
            }

        }
    }

    @Override
    public void update(Observable o, Object ob) {
        actualizarColor();
        cargarTblMovHistorial();
        cargarCmbMovHistorial();
        cargarEstadisticas();

    }
}
