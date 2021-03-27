package nominas;

public class HorasExtras {
    private float horasForzosas;
    private float horasNormales;

    public HorasExtras(float importeForzosas, float importeNormales) {
        this.horasForzosas = importeForzosas;
        this.horasNormales = importeNormales;
    }

    public float getForzosas (){
        return this.horasForzosas;
    }

    public float getNormales (){
        return this.horasNormales;
    }

    public float getTotalHoras (){
        return (this.horasForzosas+this.horasNormales);
    }

    public void setForzosas (float importeForzosas){
        this.horasForzosas = importeForzosas;
    }

    public void setNormales (float importeNormales){
        this.horasNormales = importeNormales;
    }
}
