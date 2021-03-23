package nomineando;

import java.util.Scanner;

public class Main {

    static float [] cortarConstructorNomina (String textoConstructor){
        float [] parametrosPrincipales = new float [4];
        parametrosPrincipales[0] = Float.parseFloat(textoConstructor.substring(0, textoConstructor.indexOf("_")));
        parametrosPrincipales[1] = Float.parseFloat(textoConstructor.substring(textoConstructor.indexOf("_")+1, textoConstructor.lastIndexOf("_")-1));
        parametrosPrincipales[2] = Float.parseFloat(textoConstructor.substring(textoConstructor.lastIndexOf("_")+1, textoConstructor.indexOf("/")));
        parametrosPrincipales[3] = Float.parseFloat(textoConstructor.substring(textoConstructor.lastIndexOf("/")+1));
        return parametrosPrincipales;
    }

    static Complemento crearComplemento (String textoConstructor){
        return new Complemento(textoConstructor.substring(0, textoConstructor.indexOf("_")),Float.parseFloat(textoConstructor.substring(textoConstructor.indexOf("_")+1, textoConstructor.lastIndexOf("_"))), Float.parseFloat(textoConstructor.substring(textoConstructor.lastIndexOf("_")+1)));
    }

    static float [] porcentajesCotizacion (String textoConstructor){
        return new float [] {Float.parseFloat(textoConstructor.substring(0,textoConstructor.indexOf("_"))), Float.parseFloat(textoConstructor.substring(textoConstructor.indexOf("_")+1, textoConstructor.lastIndexOf("_"))),Float.parseFloat(textoConstructor.substring(textoConstructor.lastIndexOf("_")+1, textoConstructor.indexOf("/"))),Float.parseFloat(textoConstructor.substring(textoConstructor.lastIndexOf("/")+1))};
    }

    public static void main(String[] args) {
        Nomina nomina;
        String textoConstructor = "";
        Scanner teclado = new Scanner(System.in);
        System.out.println("Hola! Introduce los parámetros de la nómina [sueldoBase_plusDeCombenio_ImporteHorasExtras//PagasExtras]");
        textoConstructor = teclado.nextLine();
        nomina = new Nomina(cortarConstructorNomina(textoConstructor)[0], cortarConstructorNomina(textoConstructor)[1], cortarConstructorNomina(textoConstructor)[2], (int)(cortarConstructorNomina(textoConstructor)[3]));
        System.out.println("Ahora introduce los complementos [tipo_importe_ParteDeCotizacion]");
        textoConstructor = teclado.nextLine();
        if(!textoConstructor.equals("") && nomina.addComplemento(crearComplemento(textoConstructor)))
            System.out.println("Complemento añadido");
        else
            System.out.println("Complemento no añadido");
        System.out.println("Con que porcentajes tengo que trabajar? [IRPF_BCCC_BCCP//HE]");
        textoConstructor = teclado.nextLine();

        System.out.println("El sueldo neto de esta persona es de "+nomina.salarioNeto(porcentajesCotizacion(textoConstructor)[0], porcentajesCotizacion(textoConstructor)[1], porcentajesCotizacion(textoConstructor)[2], porcentajesCotizacion(textoConstructor)[3]));

        teclado.close();
    }
}
