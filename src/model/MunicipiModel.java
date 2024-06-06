package model;

import java.util.ArrayList;
import java.util.List;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

/**
 * El modelo MunicipiModel proporciona métodos para realizar operaciones CRUD
 * (crear, leer, actualizar, eliminar) en la base de datos para la entidad Municipi.
 */
public class MunicipiModel extends Model {

    /**
     * Constructor de la clase MunicipiModel.
     *
     * @param db El ObjectContainer de la base de datos.
     */
    public MunicipiModel(ObjectContainer db) {
    	super(db);
    }

    /**
     * Crea un nuevo municipio y lo almacena en la base de datos.
     *
     * @param municipi El municipio a crear y almacenar.
     */
    public void crear(Municipi municipi) {
        db.store(municipi);
    }

    /**
     * Lee un municipio de la base de datos por su nombre.
     *
     * @param nombre El nombre del municipio a buscar.
     * @return El municipio encontrado o null si no se encuentra.
     */
    public Municipi leerMunicipiPorNombre(String nombre) {
        Municipi ejemplo = new Municipi(null, null, nombre);
        ObjectSet<Municipi> result = db.queryByExample(ejemplo);
        if (result.hasNext()) {
            return result.next();
        } else {
            return null;
        }
    }

    /**
     * Busca un municipio por su comarca o provincia.
     *
     * @param buscar La comarca o provincia a buscar.
     * @return El municipio encontrado o null si no se encuentra.
     */
    public Municipi searchComarcaOrProvincia(String buscar) {
        Municipi municipiEncontrado = null;
        List<Municipi> municipios = selectAll();

        ResultatModel mResultat = new ResultatModel(db);
        List<String> provinciasConocidas = mResultat.selectAllProvincias();

        if (provinciasConocidas.contains(buscar)) {
            municipiEncontrado = new Municipi(null, buscar, null);
        } else {
            for (Municipi municipio : municipios) {
                if (municipio.getComarca().equalsIgnoreCase(buscar)) {
                    municipiEncontrado = new Municipi(buscar, null, null);
                    break;
                }
            }
        }
        
        return municipiEncontrado;
    }

    /**
     * Devuelve una lista de todos los municipios almacenados en la base de datos.
     *
     * @return Una lista de todos los municipios.
     */
    public List<Municipi> selectAll() {
        List<Municipi> municipios = new ArrayList<>();
        ObjectSet<Municipi> result = db.queryByExample(Municipi.class);
        while (result.hasNext()) {
            municipios.add(result.next());
        }
        return municipios;
    }

    /**
     * Actualiza un municipio existente en la base de datos con los datos proporcionados.
     *
     * @param municipiActualizado El municipio actualizado con los nuevos datos.
     */
    public void actualizar(Municipi municipiActualizado) {
        Municipi municipiExistente = leerMunicipiPorNombre(municipiActualizado.getNom());
        if (municipiExistente != null) {
            municipiExistente.setComarca(municipiActualizado.getComarca());
            db.store(municipiExistente);
        }
    }

    /**
     * Elimina un municipio de la base de datos por su nombre.
     *
     * @param nombre El nombre del municipio a eliminar.
     */
    public void eliminar(String nombre) {
        Municipi municipiExistente = leerMunicipiPorNombre(nombre);
        if (municipiExistente != null) {
            db.delete(municipiExistente);
        }
    }

    /**
     * Cierra la conexión con la base de datos.
     */
    public void cerrarConexion() {
        db.close();
    }

}

