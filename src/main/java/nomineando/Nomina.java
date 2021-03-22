package nomineando;

import java.util.List;

public class Nomina {
    private float sueldoBase;
    private int numExtras;
    private float horasExtras;
    private float plus;
    private List <Complemento> complementos;

    public Nomina (float sueldo, float plus, int extras, int numPagasExtras, List <Complemento> complementos){
        this.sueldoBase = sueldo;
        this.horasExtras = numPagasExtras;
        this.horasExtras = extras;
        this.complementos = complementos;
        this.plus = plus;
    }

    public float pPE (){
        return sueldoBase * numExtras / 12;
    }

    public float totalDevengado (){
        float total = 0.0f;
        total += this.sueldoBase + this.horasExtras;
        for (int i = 0; i < complementos.size(); i++) {
            total += complementos.get(i).getDinero();
        }
        return total;
    }
    
    public float remuneracionMensual (){
        float total = 0.0f;
        total += this.sueldoBase;
        for (int i = 0; i < this.complementos.size(); i++) {
            total += this.complementos.get(i).getCotizacion();
        }
        return total;
    }

    public float baseCCC (){
        float total = 0.0f;
        total += this.sueldoBase + this.pPE();
        for (int i = 0; i < this.complementos.size(); i++) {
            total += this.complementos.get(i).getCotizacion();
        }
        return total;
    }

    public float baseCCP (){
        return this.baseCCC()+horasExtras;
    }

    public float seguridadSocial (float porcentajeBCCC, float porcentajeBCCP, float porcentajeHE){
        return this.baseCCC()*porcentajeBCCC + this.baseCCP()*porcentajeBCCP + this.horasExtras*porcentajeHE;
    }

    public float irpf (){
        
    }
}
