package nomineando;

public class Complemento {
    private String tipo;
    private float cantidad;
    private float cotiza;

    public Complemento(String tipo, float dinero, float cotizacion) {
        String[] tipoValido = { "Transporte", "Dietas"};
        for (int i = 0; i < tipoValido.length; i++) {
            if (tipoValido[i].equals(tipo)) {
                this.tipo = tipo;
                this.cantidad = dinero;
                this.cotiza = cotizacion;
            }else{
                this.tipo = "";
                this.cantidad = 0.0f;
                this.cotiza = 0.0f;
            }
        }
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
