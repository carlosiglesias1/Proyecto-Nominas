package nomineando;

public class HorasExtras {
    private float horasForzosas;
    private float horasNormales;
    private float cotizacionForzosas;
    private float cotizacionNormales;

    public HorasExtras(float importeForzosas, float importeNormales) {
        this.horasForzosas = importeForzosas;
        this.horasNormales = importeNormales;
        this.cotizacionForzosas = 0.02f;
        this.cotizacionNormales = 0.047f;
    }

    public float getForzosas() {
        return this.horasForzosas;
    }

    public float getNormales() {
        return this.horasNormales;
    }

    public float getTotalHoras() {
        return (this.horasForzosas + this.horasNormales);
    }

    public float getCotizacionNormales() {
        return this.cotizacionNormales;
    }

    public float getCotizacionForzosas() {
        return this.cotizacionForzosas;
    }

    public void setForzosas(float importeForzosas) {
        this.horasForzosas = importeForzosas;
    }

    public void setNormales(float importeNormales) {
        this.horasNormales = importeNormales;
    }
}
