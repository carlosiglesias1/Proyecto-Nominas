package nomineando;

import java.util.ArrayList;

/**
 * La clase nomina hace uso de la clase complemento, de forma que listará los
 * complementos (transporte//dietas) a los que habrá que introducirles la parte
 * que cotizan, dado que la clase no sabe calcularlo a partir de ahí, la clase
 * solo necesita los porcentajes de cotización para calcular las bases
 * imponibles y la nómina final.
 */

public class Nomina {
    private float sueldoBase;
    private int numExtras;
    private float horasExtras;
    private float plus;
    private ArrayList<Complemento> complementos = new ArrayList<Complemento>();

    public Nomina(float sueldo, float plus, float extras, int numPagasExtras) {
        this.sueldoBase = sueldo;
        this.horasExtras = numPagasExtras;
        this.horasExtras = extras;
        this.plus = plus;
    }

    public boolean addComplemento (Complemento complemento){
        if(this.complementos.add(complemento))
            return true;
        else
            return false;
    }

    public float pPE() {
        return sueldoBase * numExtras / 12;
    }

    public float totalDevengado() {
        float total = 0.0f;
        total += this.sueldoBase + this.horasExtras;
        for (int i = 0; i < complementos.size(); i++) {
            total += complementos.get(i).getDinero();
        }
        return total;
    }

    private float getComplementosCotizan() {
        float total = 0.0f;
        for (int i = 0; i < this.complementos.size(); i++) {
            total += this.complementos.get(i).getCotizacion();
        }
        return total;
    }

    public float remuneracionMensual() {
        return this.sueldoBase + getComplementosCotizan();
    }

    public float baseCCC() {
        return this.sueldoBase + this.pPE() + this.getComplementosCotizan();
    }

    public float baseCCP() {
        return this.baseCCC() + horasExtras;
    }

    public float seguridadSocial(float porcentajeBCCC, float porcentajeBCCP, float porcentajeHE) {
        return this.baseCCC() * porcentajeBCCC + this.baseCCP() * porcentajeBCCP + this.horasExtras * porcentajeHE;
    }

    public float irpf(float porcentajeIRPF) {
        return (this.sueldoBase + this.plus + this.horasExtras + this.getComplementosCotizan()) * porcentajeIRPF;
    }

    public float salarioNeto(float porcentajeIRPF, float porcentajeBCCC, float porcentajeBCCP, float porcentajeHE) {
        return this.totalDevengado() - irpf(porcentajeIRPF)
                - seguridadSocial(porcentajeBCCC, porcentajeBCCP, porcentajeHE);
    }
}
