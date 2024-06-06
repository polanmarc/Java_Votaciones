package model;

import java.util.ArrayList;
import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

public class PartitModel extends Model {

    public PartitModel(ObjectContainer db) {
    	super(db);
    }

    public void crearPartit(Partit partit) {
    	db.store(partit);
    }

    public Partit leerPartitPorNombre(String nombre) {
        Partit ejemplo = new Partit(nombre, null);
        ObjectSet<Partit> result = db.queryByExample(ejemplo);
        if (result.hasNext()) {
            return result.next();
        } else {
            return null;
        }
    }
    
    public List<Partit> selectAll() {
        List<Partit> partidos = new ArrayList<>();
        ObjectSet<Partit> result = db.queryByExample(Partit.class);
        while (result.hasNext()) {
        	partidos.add(result.next());
        }
        return partidos;
    }

    public void actualizar(Partit partitActualizado) {
        Partit partitExistente = leerPartitPorNombre(partitActualizado.getNom());
        if (partitExistente != null) {
            partitExistente.setSigles(partitActualizado.getSigles());
            db.store(partitExistente);
        }
    }

    public void eliminar(String nombre) {
        Partit partitExistente = leerPartitPorNombre(nombre);
        if (partitExistente != null) {
            db.delete(partitExistente);
        }
    }

    public void cerrarConexion() {
        db.close();
    }
    
}
