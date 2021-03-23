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
    private float plus;
    private HorasExtras horasExtras = new HorasExtras(0f, 0f);
    private int numExtras;
    private ArrayList<Complemento> complementos = new ArrayList<Complemento>();

    public Nomina(float sueldo, float plus, float importeForzosas, float importeNormales, int numPagasExtras) {
        this.sueldoBase = sueldo;
        this.plus = plus;
        this.horasExtras.setForzosas(importeForzosas);
        this.horasExtras.setNormales(importeNormales);
        this.numExtras = numPagasExtras;
    }

    public boolean addComplemento(Complemento complemento) {
        if (this.complementos.add(complemento))
            return true;
        else
            return false;
    }

    public float pPE() {
        return sueldoBase * numExtras / 12;
    }

    public float totalDevengado() {
        float total = 0.0f;
        total += this.sueldoBase + this.horasExtras.getTotalHoras() + this.plus;
        for (int i = 0; i < complementos.size(); i++) {
            total += complementos.get(i).getDinero();
        }
        return total;
    }

    public float getComplementosCotizan() {
        float total = 0.0f;
        for (int i = 0; i < this.complementos.size(); i++) {
            total += this.complementos.get(i).getCotizacion();
        }
        return total;
    }

    public float remuneracionMensual() {
        return (this.sueldoBase + getComplementosCotizan());
    }

    public float baseCCC() {
        return this.remuneracionMensual() + this.pPE();
    }

    public float baseCCP() {
        return (this.baseCCC() + horasExtras.getTotalHoras());
    }

    public float seguridadSocial(float porcentajeBCCC, float porcentajeParo, float porcentajeFP) {
        return (this.baseCCC() * porcentajeBCCC + this.baseCCP() * porcentajeParo + this.baseCCP() * porcentajeFP
                + this.horasExtras.getForzosas() * this.horasExtras.getCotizacionForzosas()
                + this.horasExtras.getNormales() * this.horasExtras.getCotizacionNormales());
    }

    public float irpf(float porcentajeIRPF) {
        return ((this.baseCCC() + this.horasExtras.getTotalHoras() + this.plus) * porcentajeIRPF);
    }

    public float salarioNeto(float porcentajeIRPF, float porcentajeBCCC, float porcentajeParo, float porcentajeFP) {
        return this.totalDevengado() - this.irpf(porcentajeIRPF)
                - this.seguridadSocial(porcentajeBCCC, porcentajeParo, porcentajeFP);
    }
}
