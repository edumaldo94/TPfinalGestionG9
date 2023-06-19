/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Vistas;

import AccesoADatos.EquipoData;
import AccesoADatos.EquipoMiembroData;
import AccesoADatos.MiembroData;
import AccesoADatos.ProyectoData;
import AccesoADatos.TareaData;
import Entidades.Equipo;
import Entidades.EquipoMiembro;
import Entidades.Miembro;
import Entidades.Proyecto;
import Entidades.Tarea;
import java.sql.Date;
import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author edu-1
 */
public class ViewEquipo extends javax.swing.JInternalFrame {

    private Equipo equipo;
    private EquipoData equipoData;
    private Proyecto proyecto;
    private ProyectoData proData;
    private MiembroData mData;
    private EquipoMiembroData eqMdata;
    private EquipoMiembro eqMiembro;
    private Miembro miembro;
    private TareaData tData;
    private Tarea tarea;

    private DefaultTableModel tabla, tablaB;

    public ViewEquipo() {
        initComponents();
        equipo = new Equipo();
        equipoData = new EquipoData();
        proyecto = new Proyecto();
        proData = new ProyectoData();
        eqMiembro = new EquipoMiembro();
        miembro = new Miembro();
        tarea = new Tarea();
        tData = new TareaData();
        mData = new MiembroData();
        eqMdata = new EquipoMiembroData();
        tabla = new DefaultTableModel();
        crearTabla();
        tablaB = new DefaultTableModel();
        // crearTablaB();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtIdEq = new javax.swing.JTextField();
        txtIdPro = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        jCalendario = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListaEq = new javax.swing.JTable();
        btnGuardar = new javax.swing.JButton();
        btnBorrar = new javax.swing.JButton();
        btnLista = new javax.swing.JButton();
        btnActivar = new javax.swing.JButton();
        btnDesactivar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnIntegrantes = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtListaIntegrante = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setText("EQUIPO");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setText("N° de Equipo:");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setText("N° de Proyecto:");

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setText("Nombre:");

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setText("Fecha de Creación:");

        txtIdPro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtIdProMouseClicked(evt);
            }
        });
        txtIdPro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdProActionPerformed(evt);
            }
        });

        jListaEq.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "N° Proyecto", "N° Equipo", "Nombre", "Fecha de Creación", "Estado"
            }
        ));
        jListaEq.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jListaEqMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jListaEq);

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnBorrar.setText("Borrar");

        btnLista.setText("Lista");
        btnLista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListaActionPerformed(evt);
            }
        });

        btnActivar.setText("Activar");
        btnActivar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActivarActionPerformed(evt);
            }
        });

        btnDesactivar.setText("Desactivar");
        btnDesactivar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDesactivarActionPerformed(evt);
            }
        });

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel6.setText("Listado de Equipos Activos/No Activos");

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel7.setText("Integrantes del Equipo");

        btnIntegrantes.setText("Ver Integrantes");
        btnIntegrantes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIntegrantesActionPerformed(evt);
            }
        });

        txtListaIntegrante.setColumns(20);
        txtListaIntegrante.setRows(5);
        jScrollPane3.setViewportView(txtListaIntegrante);

        jButton1.setText("Salir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(261, 261, 261))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(194, 194, 194))))
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 547, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGap(121, 121, 121)
                            .addComponent(jLabel6)
                            .addGap(88, 88, 88)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(btnIntegrantes))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnGuardar)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(91, 91, 91)
                                        .addComponent(btnBorrar))
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtIdEq, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnBuscar))
                                    .addComponent(txtIdPro, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jCalendario, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addComponent(btnLista)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnActivar)))
                                        .addGap(18, 18, 18)
                                        .addComponent(btnDesactivar)))))))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtIdEq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jCalendario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnBorrar)
                    .addComponent(btnLista)
                    .addComponent(btnActivar)
                    .addComponent(btnDesactivar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addGap(4, 4, 4)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addComponent(jButton1)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnIntegrantes)
                        .addGap(83, 83, 83))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed

        try {
            int idP = Integer.parseInt(txtIdPro.getText());
            proyecto = proData.buscarProyectoPorId(idP);
            String nombre = txtNombre.getText();

            Date fechaSql = new Date(jCalendario.getDate().getTime());
            LocalDate fechaCreacion = fechaSql.toLocalDate();

            equipo = new Equipo(proyecto, nombre, fechaCreacion, true);
            equipoData.guardarEquipo(equipo);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "ERROR ");

        }

    }//GEN-LAST:event_btnGuardarActionPerformed

    private void txtIdProActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdProActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtIdProActionPerformed

    private void txtIdProMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtIdProMouseClicked
        // TODO add your handling code here:


    }//GEN-LAST:event_txtIdProMouseClicked

    private void btnListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListaActionPerformed
        borrarTabla();
       try{
        List<Equipo> dato;

        String[] fila = new String[5];

        dato = equipoData.listaEquipo();
        for (int i = 0; i < dato.size(); i++) {
            fila[0] = dato.get(i).getIdEquipo() + "";
            fila[1] = dato.get(i).getProyectoId().getIdProyecto() + "";
            fila[2] = dato.get(i).getNombre();
            fila[3] = dato.get(i).getFechaCreacion() + "";
            fila[4] = dato.get(i).isEstado() + "";
            tabla.addRow(fila);
        }
        jListaEq.setModel(tabla);
  } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "ERROR "+ex.getMessage());
        }

    }//GEN-LAST:event_btnListaActionPerformed

    private void btnIntegrantesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIntegrantesActionPerformed
        try {

            int idBB = 0;
            int filaSelec = jListaEq.getSelectedRow();
            if (filaSelec != -1) {
                String idEquipo = (String) tabla.getValueAt(filaSelec, 0);
                int id = Integer.parseInt(idEquipo);
                equipoData.informeDeEquipo(id);

                List<String> resultados = equipoData.informeDeEquipo(id);
                if (!resultados.isEmpty()) {
                    for (String resultado : resultados) {
                        resultado = resultado.substring(0, resultado.length() - 1);

                        txtListaIntegrante.setText(resultado);
                    }
                } else {

                    txtListaIntegrante.setText("Aun no hay Miembros en este Equipo");
                }

            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "ERROR: " + ex.getMessage());
        }
    }//GEN-LAST:event_btnIntegrantesActionPerformed

    private void jListaEqMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListaEqMouseClicked


    }//GEN-LAST:event_jListaEqMouseClicked

    private void btnActivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActivarActionPerformed
        // TODO add your handling code here:

        try {

            int filaSelec = jListaEq.getSelectedRow();
            if (filaSelec != -1) {
                String idProString = (String) tabla.getValueAt(filaSelec, 0);
                int id = Integer.parseInt(idProString);
                equipoData.activarEquipo(id);
            }
            borrarTabla();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "ERROR: " + ex.getMessage());
        }

    }//GEN-LAST:event_btnActivarActionPerformed

    private void btnDesactivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDesactivarActionPerformed
        // TODO add your handling code here:
        try {

            int filaSelec = jListaEq.getSelectedRow();
            if (filaSelec != -1) {
                String idProString = (String) tabla.getValueAt(filaSelec, 0);
                int id = Integer.parseInt(idProString);
                equipoData.desactivarEquipo(id);
            }
            borrarTabla();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "ERROR: " + ex.getMessage());
        }

    }//GEN-LAST:event_btnDesactivarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        borrarTabla();
        int id = Integer.parseInt(txtIdEq.getText());
        try {
            equipo = equipoData.buscarEquipoPorId(id);
//
//txtIdPro.setText(equipo.getProyectoId().getIdProyecto()+"");
//txtNombre.setText(equipo.getNombre());
//jCalendario.setDate(Date.valueOf(equipo.getFechaCreacion()));
//jEstado.setSelected(equipo.isEstado());
//  List<Equipo> dato;

            String[] fila = new String[5];

            for (int i = 0; i < 1; i++) {
                fila[0] = equipo.getIdEquipo() + "";
                fila[1] = equipo.getProyectoId().getIdProyecto() + "";
                fila[2] = equipo.getNombre();
                fila[3] = equipo.getFechaCreacion() + "";
                fila[4] = equipo.isEstado() + "";
                tabla.addRow(fila);
            }
            jListaEq.setModel(tabla);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "ERROR el  equipo debe haber sido eliminado");
        }


    }//GEN-LAST:event_btnBuscarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed
    public void crearTabla() {
        ArrayList<Object> columnas = new ArrayList<Object>();
        columnas.add("N° Equipo");
        columnas.add("N° Proyecto");
        columnas.add("Nombre");
        columnas.add("Fecha de Creación");
        columnas.add("Estado");
        for (Object iter : columnas) {
            tabla.addColumn(iter);
        }
        jListaEq.setModel(tabla);
    }

    public void borrarTabla() {
        int a = tabla.getRowCount() - 1;
        for (int i = a; i >= 0; i--) {
            tabla.removeRow(i);
        }
    }

//    public void crearTablaB() {
//        ArrayList<Object> columnasB = new ArrayList<Object>();
//        columnasB.add("Nombre");
//        columnasB.add("Apellido");
//        columnasB.add("DNI");
//        columnasB.add("Tarea Asignadas");
//        columnasB.add("Fecha de Incorporación");
//        for (Object iter : columnasB) {
//            tablaB.addColumn(iter);
//        }
//        jListaIntegranteEq.setModel(tablaB);
//    }
//    public void borrarTablaB() {
//        int a = tablaB.getRowCount() - 1;
//        for (int i = a; i >= 0; i--) {
//            tablaB.removeRow(i);
//        }
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActivar;
    private javax.swing.JButton btnBorrar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnDesactivar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnIntegrantes;
    private javax.swing.JButton btnLista;
    private javax.swing.JButton jButton1;
    private com.toedter.calendar.JDateChooser jCalendario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTable jListaEq;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField txtIdEq;
    private javax.swing.JTextField txtIdPro;
    private javax.swing.JTextArea txtListaIntegrante;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
