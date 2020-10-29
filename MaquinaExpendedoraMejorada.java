public class MaquinaExpendedoraMejorada {

    // El precio del billete
    private int precioBillete;
    // La cantidad de dinero que lleva metida el cliente actual
    private int balanceClienteActual;
    // El total de dinero almacenado en la maquina desde su ultimo vaciado
    private int totalDineroAcumulado;
    // El origen del billete
    private String estacionOrigen;
    // El destino del billete
    private String estacionDestino;
    // billetes vendidos
    private int billetesVendidos;
    // Tipo de maquina
    private boolean maquinaConPremio;
    // Numero maximo billetes
    private int billetesMaximos;
    // premio
    private int premioAsegurado;

    /**
     * Crea una maquina expendedora de billetes de tren con el 
     * precio del billete y el origen y destino dados. Se asume que el precio
     * del billete que se recibe es mayor que 0.
     */
    public MaquinaExpendedoraMejorada(int precioDelBillete, String origen, String destino, boolean tienePremio, int numBilletesMax) {
        precioBillete = precioDelBillete;
        billetesVendidos = 0;
        balanceClienteActual = 0;
        totalDineroAcumulado = 0;
        estacionOrigen = origen;
        estacionDestino = destino;
        maquinaConPremio = tienePremio;
        billetesMaximos = numBilletesMax;
        premioAsegurado = 0;
    }

    /**
     * Devuelve el precio del billete
     */
    public int getPrecioBillete() {
        return precioBillete;
    }

    /**
     * Devuelve la cantidad de dinero que el cliente actual lleva introducida
     */
    public int getBalanceClienteActual() {
        return balanceClienteActual;
    }

    /**
     * Simula la introduccion de dinero por parte del cliente actual
     */
    public void introducirDinero(int cantidadIntroducida) {
        if (billetesMaximos != 0) {
            if (cantidadIntroducida > 0) {
                balanceClienteActual = balanceClienteActual + cantidadIntroducida;
            }
            else {
                System.out.println(cantidadIntroducida + " no es una cantidad de dinero valida.");
            }     
        }
        else {
            System.out.println("No se puede meter mas dinero debido a que no hay mas billetes a la venta");
        }
    }

    /**
     * Retira todo el dinero
     */
    public int vaciarDineroDeLaMaquina () {
        int dineroTotal = totalDineroAcumulado;
        if (balanceClienteActual != 0) {
            System.out.println("No se puede sacar el dinero de la máquina porque hay un operación en curso");
            dineroTotal= -1;
        }
        else {
            totalDineroAcumulado = 0;
        }
        return dineroTotal;
    }

    /**
     * Cuenta el num de billetes impresos
     */
    public int getNumeroBilletesVendidos () {
        return billetesVendidos;
    }

    /**
     * Imprime un billete para el cliente actual
     */
    public void imprimirBillete() {
        int cantidadDeDineroqueFalta = precioBillete - balanceClienteActual;
        if (billetesMaximos != 0) {
            if (cantidadDeDineroqueFalta <= 0) { 
                // Simula la impresion de un billete
                System.out.println("##################");
                System.out.println("# Billete de tren:");
                System.out.println("# De " + estacionOrigen + " a " + estacionDestino);
                System.out.println("# " + precioBillete + " euros.");
                System.out.println("##################");
                System.out.println();
                if (maquinaConPremio == true) {
                    if (premioAsegurado == 3) {
                        System.out.println("Tienes " + precioBillete * 10 / 100F + " euros de descuento en Bershka presentando este billete");
                    }
                    else {
                        System.out.println("Mala suerte, no hay descuento");
                        System.out.println("Quedan " + (3-premioAsegurado) + " billetes para el descuento");
                        premioAsegurado +=1;
                    }
                }
                else {
                    System.out.println("No tienes descuento");
                }

                // Actualiza el total de dinero acumulado en la maquina
                totalDineroAcumulado = totalDineroAcumulado + precioBillete;
                // Reduce el balance del cliente actual dejandole seguir utilizando la maquina
                balanceClienteActual = balanceClienteActual - precioBillete;
                // Billetes Vendidos
                billetesVendidos += 1;
                // Billetes restantes
                billetesMaximos -= 1;
            }
            else {
                System.out.println("Necesitas introducir " + (cantidadDeDineroqueFalta) + " euros mas!");

            }  
        }
        else {
            System.out.println("Billetes agotados, no se pueden imprimir mas billetes.");
        }
    }

    /**
     * Cancela la operacion de compra del cliente actual y le
     * devuelve al cliente el dinero que ha introducido hasta el momento
     */
    public int cancelarOperacionYDevolverDinero() {
        int cantidadDeDineroADevolver;
        cantidadDeDineroADevolver = balanceClienteActual;
        balanceClienteActual = 0;
        return cantidadDeDineroADevolver;
    } 
}
