package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import model.Municipi;

import java.awt.BorderLayout;
import java.util.List;

/**
 * La clase MunicipiView representa una ventana que muestra una tabla de municipios
 */
public class MunicipiView extends JFrame {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor
     */
    public MunicipiView() {
    	initialize();
    }

    /**
     * Inicializa la ventana de la tabla de municipios
     */
    void initialize() {
    	setTitle("Tabla de Municipios");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        setVisible(true);
    }
    
    /**
     * Método para mostrar una lista de todos los municipios en una tabla
     *
     * @param municipis La lista de municipios
     */
    public void tableSelectAll(List<Municipi> municipis) {
        String[] columnNames = {"Provincia", "Comarca", "Nombre"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        for (Municipi municipi : municipis) {
            Object[] rowData = {municipi.getProvincia(), municipi.getComarca(), municipi.getNom()};
            tableModel.addRow(rowData);
        }

        JTable table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        setSize(600, 400);
        setLocationRelativeTo(null);
    }   
}
