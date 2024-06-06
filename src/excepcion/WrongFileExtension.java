package excepcion;

/**
 * La clase WrongFileExtension representa una excepcion que se lanza cuando se selecciona un archivo
 * con una extension incorrecta
 */
public class WrongFileExtension extends CustomException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor de la clase WrongFileExtension con codigo y mensaje personalizado
     *
     * @param pCodi   El codigo asociado al error
     * @param message El mensaje de error
     */
    public WrongFileExtension(String pCodi, String message) {
        super(pCodi, message);
    }

}
