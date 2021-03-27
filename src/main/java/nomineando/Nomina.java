package nomineando;

import java.util.ArrayList;

/**
 * La clase nomina hace uso de la clase complemento, de forma que listará los
 * complementos (transporte//dietas) a los que habrá que introducirles la parte
 * que cotizan, dado que la clase no sabe calcularlo a partir de ahí, la clase
 * solo necesita los porcentajes de cotización para calcular las bases
 * imponibles y la nómina final.
 * 
 * @author Carlos Iglesias Gómez
 * @version  1.7
 */

public class Nomina {
    private float sueldoBase;
    private float plus;
    private int numExtras;
    private float horasExtras;
    private ArrayList<Complemento> complementos = new ArrayList<Complemento>();

    /**
     * Constructor de la nómina
     * @param sueldo Sueldo Base
     * @param plus Plus de convenio u otros pluses que no sean complementos
     * @param extras Importe del valor de las horas extras
     * @param numPagasExtras Número de pagas extras anuales
     */
    public Nomina(float sueldo, float plus, float extras, int numPagasExtras) {
        this.sueldoBase = sueldo;
        this.plus = plus;
        this.horasExtras = extras;
        this.numExtras = numPagasExtras;
    }

    
    /** 
     * @param complemento
     * @return  <ul>
     *              <li>true: complemento añadidio</li>
     *              <li>false: complemento no añadido</li>
     *          </ul>  
     */
    public boolean addComplemento(Complemento complemento) {
        if (this.complementos.add(complemento))
            return true;
        else
            return false;
    }

    
    /** 
     * @return prorrata de pagas extras
     */
    public float pPE() {
        return sueldoBase * numExtras / 12;
    }

    
    /** 
     * @return la parte de la nómina de la que se sacan los devengos
     */
    public float totalDevengado() {
        float total = 0.0f;
        total += this.sueldoBase + this.horasExtras + this.plus;
        for (int i = 0; i < complementos.size(); i++) {
            total += complementos.get(i).getDinero();
        }
        return total;
    }

    
    /** 
     * @return la suma de todos los importes de los complementos que cotizan
     */
    public float getComplementosCotizan() {
        float total = 0.0f;
        for (int i = 0; i < this.complementos.size(); i++) {
            total += this.complementos.get(i).getCotizacion();
        }
        return total;
    }

    
    /** 
     * @return el valor del salario bruto
     */
    public float remuneracionMensual() {
        return (this.sueldoBase + getComplementosCotizan());
    }

    
    /** 
     * @return base de cotización de contingencias comunes
     */
    public float baseCCC() {
        return this.remuneracionMensual() + this.pPE();
    }

    
    /** 
     * @return base de cotización de contingencias profesionales
     */
    public float baseCCP() {
        return (this.baseCCC() + horasExtras);
    }

    
    /** 
     * @param porcentajeBCCC
     * @param porcentajeParo
     * @param porcentajeFP
     * @param porcentajeHE
     * @return cálculo del devengo de la seguridad social
     */
    public float seguridadSocial(float porcentajeBCCC, float porcentajeParo, float porcentajeFP, float porcentajeHE) {
        return (this.baseCCC() * porcentajeBCCC + this.baseCCP() * porcentajeParo + this.baseCCP() * porcentajeFP
                + this.horasExtras * porcentajeHE);
    }

    
    /** 
     * @param porcentajeIRPF
     * @return cálculo del irpf
     */
    public float irpf(float porcentajeIRPF) {
        return ((this.baseCCC()+this.horasExtras+this.plus) * porcentajeIRPF);
    }

    
    /** 
     * @param porcentajeIRPF
     * @param porcentajeBCCC
     * @param porcentajeParo
     * @param porcentajeFP
     * @param porcentajeHE
     * @return resultado del calculo del salario neto
     */
    public float salarioNeto(float porcentajeIRPF, float porcentajeBCCC, float porcentajeParo, float porcentajeFP,
            float porcentajeHE) {
        return this.totalDevengado() - this.irpf(porcentajeIRPF)
                - this.seguridadSocial(porcentajeBCCC, porcentajeParo, porcentajeFP, porcentajeHE);
    }
}
