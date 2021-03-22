package nomineando;

public class Complemento {
    private String[] tipoValido = { "Transporte", "Dietas", "Plus" };
    private String tipo;
    private float cantidad;
    private float cotiza;

    public Complemento(String tipo, float dinero, float cotizacion) {
        for (int i = 0; i < tipoValido.length; i++) {
            if (this.tipoValido[i].equals(tipo)) {
                this.tipo = tipo;
                this.cantidad = dinero;
                this.cotiza = cotizacion;
                break;
            }
        }
        this.tipo = "";
        this.cantidad = 0.0f;
        this.cotiza = 0.0f;
    }

    public String getTipo() {
        return this.tipo;
    }

    public float getDinero() {
        return this.cantidad;
    }

    public float getCotizacion(){
        return this.cotiza;
    }
}
