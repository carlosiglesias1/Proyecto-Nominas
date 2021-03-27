package nominas;

public class HorasExtras {
    private float horasForzosas;
    private float horasNormales;

    /**
     * Constructor de las horas extras
     * @param importeForzosas
     * @param importeNormales
     */
    public HorasExtras(float importeForzosas, float importeNormales) {
        this.horasForzosas = importeForzosas;
        this.horasNormales = importeNormales;
    }

    
    /** 
     * @return float
     */
    public float getForzosas (){
        return this.horasForzosas;
    }

    
    /** 
     * @return float
     */
    public float getNormales (){
        return this.horasNormales;
    }

    
    /** 
     * @return total de las horas extras
     */
    public float getTotalHoras (){
        return (this.horasForzosas+this.horasNormales);
    }

    
    /** 
     * @param importeForzosas
     */
    public void setForzosas (float importeForzosas){
        this.horasForzosas = importeForzosas;
    }

    
    /** 
     * @param importeNormales
     */
    public void setNormales (float importeNormales){
        this.horasNormales = importeNormales;
    }
}
