package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import model.Municipi;
import model.MunicipiModel;
import model.Partit;
import model.PartitModel;
import model.Resultat;
import model.ResultatModel;

public class ImportarCSV {
	
	private MunicipiModel mMunicipi;
    private ResultatModel mResultat;
    private PartitModel mPartit;

	private List<Municipi> listaMunicipis;
	private List<Partit> listaPartits;
	private List<Resultat> listaResultats;

	public ImportarCSV(ObjectContainer db) {
		this.mPartit = new PartitModel(db);
		this.mResultat = new ResultatModel(db);
		this.mMunicipi = new MunicipiModel(db);
		this.listaMunicipis = new ArrayList<Municipi>(mMunicipi.selectAll());
		this.listaPartits = new ArrayList<Partit>(mPartit.selectAll());
		this.listaResultats = new ArrayList<Resultat>(mResultat.selectAll());
	}

	public void importData(String fileRoute) {
		String linia = "";
	    try (BufferedReader reader = new BufferedReader(new FileReader(fileRoute))) {
	        boolean primeraLinia = true;

	        while ((linia = reader.readLine()) != null) {
	            if (primeraLinia) {
	                primeraLinia = false;
	                continue;
	            }

	            String[] fila = linia.split(";");
	            if (fila.length < 3 || fila[0].isEmpty() || fila[1].isEmpty() || fila[2].isEmpty()) {
	                continue;
	            }

                Municipi municipi = crearMunicipi(fila[1], fila[0], fila[2], mMunicipi);
                Partit partit = crearPartit(fila, mPartit);

                long votos = calcularVotos(fila[5]);
                double porcentaje = calcularPorcentaje(fila[6]);

                Resultat resultat = new Resultat(municipi, partit, porcentaje, votos);
                int posResultat = listaResultats.indexOf(resultat);
                if (posResultat == -1) {
                    listaResultats.add(resultat);
                    mResultat.crearResultat(resultat);
                } else {
                    Resultat updateResultat = listaResultats.get(posResultat);
                    if (updateResultat.getVots() != votos || updateResultat.getPercentatge() != porcentaje) {
                        updateResultat.setVots(votos);
                        updateResultat.setPercentatge(porcentaje);
                        mResultat.actualizar(updateResultat);
                    }
                }
	        }
	    } catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Error al convertir número en la línea: " + linia, "Error Convertir Numero", JOptionPane.ERROR_MESSAGE);
	    } catch (IndexOutOfBoundsException e) {
			JOptionPane.showMessageDialog(null, "Error de índice en la línea: " + linia, "Error en la cantidad de lineas", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error de E/S al leer el archivo: " + e.getMessage(), "Error E/S", JOptionPane.ERROR_MESSAGE);
	    } 
	    
	    JOptionPane.showMessageDialog(null, "Se ha importado el archivo CSV correctamente. Con estos datos:\nMunicipios: "
                + listaMunicipis.size() + "\nPartidos: " + listaPartits.size() + "\nResultados: "
                + listaResultats.size(), "Importación Finalizada", JOptionPane.INFORMATION_MESSAGE); 
	}

	private Municipi crearMunicipi(String provincia, String comarca, String municipi,MunicipiModel mMunicipi) {
		Municipi muni = new Municipi(provincia, comarca, municipi);
		int index = listaMunicipis.indexOf(muni);
		if (index == -1) {
			listaMunicipis.add(muni);
			mMunicipi.crear(muni);
			return muni;
		} else {
			return listaMunicipis.get(index);
		}
	}

	private Partit crearPartit(String[] fila, PartitModel mPartit) {
		if (fila.length >= 5 && !fila[3].isEmpty() && !fila[4].isEmpty()) {
			Partit partit = new Partit(fila[4], fila[3]);
			int index = listaPartits.indexOf(partit);
			if (index == -1) {
				listaPartits.add(partit);
				mPartit.crearPartit(partit);
				return partit;
			} else {
				return listaPartits.get(index);
			}
		}
		return null;
	}

	private static long calcularVotos(String valor) {
		return valor.equals("-") ? 0L : Long.parseLong(valor);
	}

	private static double calcularPorcentaje(String valor) {
		return valor.equals("-") ? 0.0 : Double.parseDouble(valor.replace(",", "."));
	}
	
}

//package controller;
//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import com.db4o.Db4oEmbedded;
//import com.db4o.ObjectContainer;
//import model.Municipi;
//import model.MunicipiModel;
//import model.Partit;
//import model.PartitModel;
//import model.Resultat;
//import model.ResultatModel;
//
//public class ImportarCSV {
//
//    public static void main(String[] args) {
//        ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "database.db4o");
//
//        PartitModel mPartit = new PartitModel(db);
//        ResultatModel mResultat = new ResultatModel(db);
//        MunicipiModel mMunicipi = new MunicipiModel(db);
//
//        List<Municipi> listaMunicipis = new ArrayList<>();
//        List<Partit> listaPartits = new ArrayList<>();
//        List<Resultat> listaResultats = new ArrayList<>();
//
//        try (BufferedReader reader = new BufferedReader(new FileReader("src/votacions.csv"))) {
//            boolean primeraLinia = true;
//            String linia;
//
//            while ((linia = reader.readLine()) != null) {
//                if (primeraLinia) {
//                    primeraLinia = false;
//                    continue;
//                }
//
//                String[] fila = linia.split(";");
//                if (fila.length < 7 || fila[0].isEmpty() || fila[1].isEmpty() || fila[2].isEmpty()) {
//                    continue;
//                }
//
//                Municipi municipi = crearMunicipi(fila[0], fila[1], fila[2], listaMunicipis, mMunicipi);
//                Partit partit = crearPartit(fila, listaPartits, mPartit);
//
//                long votos = calcularVotos(fila[5]);
//                double porcentaje = calcularPorcentaje(fila[6]);
//
//                Resultat resultat = new Resultat(municipi, partit, porcentaje, votos);
//                if (!listaResultats.contains(resultat)) {
//                    listaResultats.add(resultat);
//                    mResultat.crearResultat(resultat);
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            mPartit.cerrarConexion();
//            mResultat.cerrarConexion();
//            mMunicipi.cerrarConexion();
//        }
//    }
//
//    private static Municipi crearMunicipi(String provincia, String comarca, String municipi,
//                                           List<Municipi> listaMunicipis, MunicipiModel mMunicipi) {
//        Municipi muni = new Municipi(provincia, comarca, municipi);
//        int index = listaMunicipis.indexOf(muni);
//        if (index == -1) {
//            listaMunicipis.add(muni);
//            mMunicipi.crearMunicipi(muni);
//            return muni;
//        } else {
//            return listaMunicipis.get(index);
//        }
//    }
//
//    private static Partit crearPartit(String[] fila, List<Partit> listaPartits, PartitModel mPartit) {
//        if (fila.length >= 5 && !fila[3].isEmpty() && !fila[4].isEmpty()) {
//            Partit partit = new Partit(fila[4], fila[3]);
//            int index = listaPartits.indexOf(partit);
//            if (index == -1) {
//                listaPartits.add(partit);
//                mPartit.crearPartit(partit);
//                return partit;
//            } else {
//                return listaPartits.get(index);
//            }
//        }
//        return null;
//    }
//
//    private static long calcularVotos(String valor) {
//        return valor.equals("-") ? 0L : Long.parseLong(valor);
//    }
//
//    private static double calcularPorcentaje(String valor) {
//        return valor.equals("-") ? 0.0 : Double.parseDouble(valor.replace(",", "."));
//    }
//}
