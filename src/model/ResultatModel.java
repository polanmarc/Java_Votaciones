package model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

import excepcion.WrongComarcaProvincia;

public class ResultatModel extends Model {

    public ResultatModel(ObjectContainer db) {
        super(db);
    }

    public void crearResultat(Resultat resultat) {
    	db.store(resultat);
    }
    
    public List<Resultat> selectAll() {
        List<Resultat> resultados = new ArrayList<>();
        ObjectSet<Resultat> result = db.queryByExample(Resultat.class);
        while (result.hasNext()) {
        	resultados.add(result.next());
        }
        return resultados;
    }
    
    public List<String> selectAllProvincias() {
        List<String> provincias = new ArrayList<>();
        ObjectSet<Resultat> result = db.queryByExample(Resultat.class);

        while (result.hasNext()) {
            Resultat resultado = result.next();
            Municipi municipi = resultado.getMunicipi();
            if (municipi != null) {
                String provincia = municipi.getProvincia();
                if (!provincias.contains(provincia)) {
                    provincias.add(provincia);
                }
            }
        }

        return provincias;
    }
    
    public List<String> selectAllComarcasForProvincia(String provincia) {
        List<String> comarcas = new ArrayList<>();
        ObjectSet<Resultat> result = db.queryByExample(Resultat.class);
        
        while (result.hasNext()) {
            Resultat resultado = result.next();
            Municipi municipi = resultado.getMunicipi();
            if (municipi != null && municipi.getProvincia().equals(provincia)) {
                String comarca = municipi.getComarca();
                if (!comarcas.contains(comarca)) {
                    comarcas.add(comarca);
                }
            }
        }

        return comarcas;
    }
    
    public List<String> selectAllMunicipisForComarca(String comarca) {
        List<String> municipis = new ArrayList<>();
        ObjectSet<Resultat> result = db.queryByExample(Resultat.class);

        while (result.hasNext()) {
            Resultat resultado = result.next();
            Municipi municipi = resultado.getMunicipi();
            if (municipi != null && municipi.getComarca().equals(comarca)) {
                String nomMunicipi = municipi.getNom();
                if (!municipis.contains(nomMunicipi)) {
                    municipis.add(nomMunicipi);
                }
            }
        }

        return municipis;
    }
    
    public List<Resultat> reedPartitForProvinciaOrComarca(Municipi municipi) {
    	
    	System.out.println(municipi);
    	List<Resultat> resultats = new ArrayList<>();
        ObjectSet<Resultat> result = db.queryByExample(new Resultat(municipi, null, 0.0, 0));
        
    	try {
            while (result.hasNext()) {
                Resultat resultat = result.next();
                if (municipi.getComarca() != null) {
                	String aux = resultat.getMunicipi().getComarca();
                    if (aux.equals(municipi.getComarca())) {
                    	resultats.add(resultat);
                    }
                } else if (municipi.getProvincia() != null) {
                	String aux = resultat.getMunicipi().getProvincia();
                    if (aux.equals(municipi.getProvincia())) {
                    	resultats.add(resultat);
                    }
                } else {
                	throw new WrongComarcaProvincia("A001", "No existe esta comarca o provincia");
                }
            }
    	} catch (WrongComarcaProvincia e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "No existe esta Provincia o Comarca", JOptionPane.ERROR_MESSAGE);
    	} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    	}
    	
    	return resultats;
    }
    
    public List<Resultat> reedPartitsForMunicipi(Municipi municipi) {
        List<Resultat> resultats = new ArrayList<>();
        ObjectSet<Resultat> result = db.queryByExample(new Resultat(municipi, null, 0.0, 0));

        while (result.hasNext()) {
            Resultat resultat = result.next();
            String aux = resultat.getMunicipi().getNom();
            if (aux.equals(municipi.getNom())) {
            	resultats.add(resultat);
            }
        }

        return resultats;
    }
    
    public List<Resultat> reedMunicipiForPartits(Partit partit) {
    	System.out.println(partit);
        List<Resultat> resultats = new ArrayList<>();
        ObjectSet<Resultat> result = db.queryByExample(new Resultat(null, partit, 0.0, 0));

        while (result.hasNext()) {
            Resultat resultat = result.next();
            String aux = resultat.getPartit().getSigles();
            if (aux.equals(partit.getSigles())) {
            	resultats.add(resultat);
            }
        }

        return resultats;
    }

    public Resultat reedForMunicipi(Municipi municipi) {
        Resultat ejemplo = new Resultat(municipi, null, 0.0, 0);
        ObjectSet<Resultat> result = db.queryByExample(ejemplo);
        if (result.hasNext()) {
            return result.next();
        } else {
            return null;
        }
    }

    public void actualizar(Resultat resultatActualizado) {
        Resultat resultadoExistente = reedForMunicipi(resultatActualizado.getMunicipi());
        if (resultadoExistente != null) {
            resultadoExistente.setVots(resultatActualizado.getVots());
            resultadoExistente.setPercentatge(resultatActualizado.getPercentatge());
            System.out.println(resultadoExistente);
            db.store(resultadoExistente);
        }
    }

    public void eliminar(Resultat resultatEliminar) {
        Resultat resultadoExistente = reedForMunicipi(resultatEliminar.getMunicipi());
        if (resultadoExistente != null) {
            db.delete(resultadoExistente);
        }
    }

    public void cerrarConexion() {
        db.close();
    }
    
}
