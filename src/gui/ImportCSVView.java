package gui;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.db4o.ObjectContainer;

import controller.ImportarCSV;
import excepcion.WrongFileExtension;
import java.io.File;

/**
 * La clase ImportCSVView representa una ventana de interfaz de usuario para importar archivos CSV.
 * Permite al usuario seleccionar un archivo CSV y procesarlo para importar datos en una base de datos.
 */
public class ImportCSVView extends JFrame {
    private static final long serialVersionUID = 1L;
    private ObjectContainer db;
    private JFileChooser fileChooser;

    /**
     * Constructor de la clase ImportCSVView.
     *
     * @param db La base de datos en la que se importarán los datos del archivo CSV.
     */
    public ImportCSVView(ObjectContainer db) {
        this.db = db;
        initialize();
        openFileChooser();
    }

    /**
     * Inicializa la ventana de selección de archivo y establece el filtro para seleccionar solo archivos CSV.
     */
    private void initialize() {
        fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Importar CSV");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos CSV", "csv");
        fileChooser.setFileFilter(filter);
    }

    /**
     * Abre el selector de archivo para que el usuario pueda seleccionar un archivo CSV.
     * Si se selecciona un archivo con extension correcta, se lo pasa al controlador para importar los datos.
     * Si se produce un error al abrir el selector de archivos o en comprobar que la extension del arxivo no
     * es .csv, se muestra un mensaje de error al usuario.
     */
    private void openFileChooser() {
    	boolean archivoCorrecto = false;
        try {
            while (!archivoCorrecto) {
                int result = fileChooser.showOpenDialog(this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    String filePath = selectedFile.getAbsolutePath();
                    if (isCSVFile(selectedFile)) {
                        System.out.println("Archivo seleccionado: " + filePath);
                        ImportarCSV cImportCSV = new ImportarCSV(db);
                        cImportCSV.importData(filePath);
                        archivoCorrecto = true;
                    } else {
                        throw new WrongFileExtension("A008", "Por favor, seleccione un archivo CSV.");
                    }
                } else {
                    dispose();
                    archivoCorrecto = true;
                }
            }
        } catch (WrongFileExtension e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), e.getCodi(), JOptionPane.ERROR_MESSAGE);
            openFileChooser();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al abrir el selector de archivos: " + e.getMessage(), "Error al abrir el archivo", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Verifica si el archivo seleccionado es un archivo CSV.
     *
     * @param file El archivo seleccionado por el usuario.
     * @return true si el archivo tiene extensión .csv, false de lo contrario.
     */
    private boolean isCSVFile(File file) {
        String fileName = file.getName();
        return fileName.endsWith(".csv");
    }
}
