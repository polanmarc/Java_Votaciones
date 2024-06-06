package excepcion;

/**
 * La clase WrongComarcaProvincia representa una excepcion que se lanza cuando se detecta un valor incorrecto
 * en los campos de comarca o provincia
 */
public class WrongComarcaProvincia extends CustomException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor con el codigo y el mensaje
     *
     * @param pCodi   El codigo asociado al error
     * @param message El mensaje de error
     */
    public WrongComarcaProvincia(String pCodi, String message) {
        super(pCodi, message);
    }
    
}
