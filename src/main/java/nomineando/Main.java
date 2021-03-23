package nomineando;

import java.util.Scanner;

public class Main {

        static Scanner teclado = new Scanner(System.in);

        static float[] cortarConstructorNomina(String textoConstructor) {
                float[] parametrosPrincipales = new float[5];
                parametrosPrincipales[0] = Float
                                .parseFloat(textoConstructor.substring(0, textoConstructor.indexOf("_")));
                parametrosPrincipales[1] = Float.parseFloat(textoConstructor
                                .substring(textoConstructor.indexOf("_") + 1, textoConstructor.lastIndexOf("_")));
                parametrosPrincipales[2] = Float.parseFloat(textoConstructor
                                .substring(textoConstructor.lastIndexOf("_") + 1, textoConstructor.indexOf("/")));
                parametrosPrincipales[3] = Float.parseFloat(textoConstructor
                                .substring(textoConstructor.indexOf("/") + 1, textoConstructor.lastIndexOf("/")));
                parametrosPrincipales[4] = Float
                                .parseFloat(textoConstructor.substring(textoConstructor.lastIndexOf("/") + 1));
                return parametrosPrincipales;
        }

        static Complemento crearComplemento(String textoConstructor) {
                return new Complemento(textoConstructor.substring(0, textoConstructor.indexOf("_")),
                                Float.parseFloat(textoConstructor.substring(textoConstructor.indexOf("_") + 1,
                                                textoConstructor.lastIndexOf("_"))),
                                Float.parseFloat(textoConstructor.substring(textoConstructor.lastIndexOf("_") + 1)));
        }

        static float[] porcentajesCotizacion(String textoConstructor) {
                return new float[] { Float.parseFloat(textoConstructor.substring(0, textoConstructor.indexOf("_"))),
                                Float.parseFloat(textoConstructor.substring(textoConstructor.indexOf("_") + 1,
                                                textoConstructor.lastIndexOf("_"))),
                                Float.parseFloat(textoConstructor.substring(textoConstructor.lastIndexOf("_") + 1,
                                                textoConstructor.indexOf("/"))),
                                Float.parseFloat(textoConstructor.substring(textoConstructor.indexOf("/") + 1)) };
        }

        static void añadirComplementos(int numeroComplementos, Nomina nomina) {
                for (int i = 0; i < numeroComplementos; i++) {
                        System.out.println("Ahora introduce el complementos [tipo_importe_ParteDeCotizacion]");
                        String textoConstructor = teclado.nextLine();

                        if (!textoConstructor.equals("") && nomina.addComplemento(crearComplemento(textoConstructor)))
                                System.out.println("Complemento añadido");
                        else
                                System.out.println("Complemento no añadido");
                }
        }

        static void printData(Nomina nomina, String textoConstructor) {
                System.out.printf("PPE: %.2f%n", nomina.pPE());
                System.out.printf("Total Devengado: %.2f%n", nomina.totalDevengado());
                System.out.printf("Complementos que cotizan: %.2f%n", nomina.getComplementosCotizan());
                System.out.printf("Remuneracion Mensual: %.2f%n", nomina.remuneracionMensual());
                System.out.printf("BCCC: %.2f%n", nomina.baseCCC());
                System.out.printf("BCCP: %.2f%n", nomina.baseCCP());
                System.out.printf("Seguridad Social: %.2f%n",
                                nomina.seguridadSocial(porcentajesCotizacion(textoConstructor)[1],
                                                porcentajesCotizacion(textoConstructor)[2],
                                                porcentajesCotizacion(textoConstructor)[3], 0.02f, 0.047f));
                System.out.printf("IRPF: %.2f%n", nomina.irpf(porcentajesCotizacion(textoConstructor)[0]));
        }

        public static void main(String[] args) {
                Nomina nomina;
                String textoConstructor = "";
                System.out.println(
                                "Hola! Introduce los parámetros de la nómina [sueldoBase_plusDeCombenio_ImporteHorasExtrasForzosas/ImporteHorasExtrasNormales/PagasExtras]");
                textoConstructor = teclado.nextLine();
                nomina = new Nomina(cortarConstructorNomina(textoConstructor)[0],
                                cortarConstructorNomina(textoConstructor)[1],
                                cortarConstructorNomina(textoConstructor)[2],
                                cortarConstructorNomina(textoConstructor)[3],
                                (int) (cortarConstructorNomina(textoConstructor)[4]));

                System.out.println("Cuántos complementos tiene la nómina?");
                añadirComplementos(Integer.parseInt(teclado.nextLine()), nomina);

                System.out.println("Con que porcentajes tengo que trabajar? [IRPF_BCCC_Paro/FP]");
                textoConstructor = teclado.nextLine();

                printData(nomina, textoConstructor);

                System.out.printf("El sueldo neto de esta persona es de %.2f%n",
                                nomina.salarioNeto(porcentajesCotizacion(textoConstructor)[0],
                                                porcentajesCotizacion(textoConstructor)[1],
                                                porcentajesCotizacion(textoConstructor)[2],
                                                porcentajesCotizacion(textoConstructor)[3], 0.02f, 0.047f));

                teclado.close();
        }
}
