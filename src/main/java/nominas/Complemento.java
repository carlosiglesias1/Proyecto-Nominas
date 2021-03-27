package nominas;

/**
 * @author Carlos Iglesias Gómez
 * @version 1.5
 */

public class Complemento {
    private String tipo;
    private float cantidad;
    private float cotiza;

    /**
     * Constructor del complemento
     * @param tipo Dietas o tansporte
     * @param dinero Importe total del complemento
     * @param cotizacion Importe del complemento que cotiza para la seguridad social
     */
    public Complemento(String tipo, float dinero, float cotizacion) {
        String[] tipoValido = { "Transporte", "Dietas" };
        for (int i = 0; i < tipoValido.length; i++) {
            if (tipoValido[i].equals(tipo)) {
                this.tipo = tipo;
                this.cantidad = dinero;
                this.cotiza = cotizacion;
            } else {
                this.tipo = "";
                this.cantidad = 0.0f;
                this.cotiza = 0.0f;
            }
        }
    }

    
    /** 
     * @return String
     */
    public String getTipo() {
        return this.tipo;
    }

    
    /** 
     * @return float
     */
    public float getDinero() {
        return this.cantidad;
    }

    
    /** 
     * @return float
     */
    public float getCotizacion() {
        return this.cotiza;
    }
}
