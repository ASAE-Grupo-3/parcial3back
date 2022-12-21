package co.edu.unicauca.asae.core.proyecto.exceptionControllers.exceptions;

public class ErrorAlmacenamientoBDException extends GestionClientesRuntimeException {

    private static final String FORMATO_EXCEPCION = "%s - Error: %s";
  
    private final String msg;
  
    public ErrorAlmacenamientoBDException(final String msg) {
      super(CodigoError.Violacion_almacenamiento_DB);
      this.msg = msg;
    }
  
    @Override
    public String formatException() {
      return String.format(FORMATO_EXCEPCION, codigoError.getCodigo(), msg);
    }
  }
  