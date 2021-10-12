/**
 * La clase modela un sencillo podómetro que registra información
 * acerca de los pasos, distancia, ..... que una persona
 * ha dado en una semana. 
 * 
 * @author    - Jiacheng - 
 */
public class Podometro {
    
    private final char HOMBRE = 'H';
    private final char MUJER = 'M';
    private final double ZANCADA_HOMBRE = 0.45;
    private final double ZANCADA_MUJER = 0.41;
    private final int SABADO = 6;
    private final int DOMINGO = 7;
    private String marca;
    private double altura;
    private char sexo;
    private double longitudZancada;
    private int totalPasosLaborales;
    private int totalPasosSabado;
    private int totalPasosDomingo;
    private double totalDistanciaSemana;
    private double totalDistanciaFinSemana;
    private int tiempo;
    private int caminatasNoche;
    
    /**
     * Inicializa el podómetro con la marca indicada por el parámetro.
     * El resto de atributos se ponen a 0 y el sexo, por defecto, es mujer
     */
    public Podometro(String queMarca){
        marca = queMarca;
        altura = 0;
        sexo = MUJER;
        longitudZancada = 0;
        totalPasosLaborales = 0;
        totalPasosSabado = 0;
        totalPasosDomingo = 0;
        totalDistanciaSemana = 0;
        totalDistanciaFinSemana = 0;
        tiempo = 0;
        caminatasNoche = 0;
        
    }

    /**
     * Accesor para la marca
     *  
     */
    public String getMarca(){
        return marca;

    }

    /**
     * Simula la configuración del podómetro.
     * Recibe como parámetros la altura y el sexo de una persona
     * y asigna estos valores a los atributos correspondiente.
     * Asigna además el valor adecuado al atributo longitudZancada
     * 
     * (leer enunciado)
     *  
     */
    public void configurar(double queAltura, char queSexo){
        altura = queAltura;
        sexo = queSexo;
        if(sexo == MUJER){
            longitudZancada = Math.floor(altura * ZANCADA_MUJER);
        } 
        else if(sexo == HOMBRE){
            longitudZancada = Math.ceil(altura * ZANCADA_HOMBRE);
        }
        else{
            System.out.println("Por favor introduza un sexo válido.");
        }
    }

     /**
     *  Recibe cuatro parámetros que supondremos correctos:
     *    pasos - el nº de pasos caminados
     *    dia - nº de día de la semana en que se ha hecho la caminata 
     *              (1 - Lunes, 2 - Martes - .... - 6 - Sábado, 7 - Domingo)
     *    horaInicio – hora de inicio de la caminata
     *    horaFin – hora de fin de la caminata
     *    
     *    A partir de estos parámetros el método debe realizar ciertos cálculos
     *    y  actualizará el podómetro adecuadamente  
     *   
     *   (leer enunciado del ejercicio)
     */
    public void registrarCaminata(int pasos, int dia, int horaInicio,
                            int horaFin) {
        
        double totalDistanciaSemanaCm = pasos * longitudZancada;
        totalDistanciaSemana = totalDistanciaSemanaCm / 100000;
        int horaInicioMinutos = horaInicio * 60;
        int horaFinMinutos = horaFin * 60;
        tiempo = horaFinMinutos - horaInicioMinutos;
        
        if(horaInicio >= 2100 && horaFin > 2100){
            caminatasNoche ++;
        }
        
        switch(dia){
            case 1: 
            case 2:
            case 3:
            case 4:
            case 5: totalPasosLaborales = totalPasosLaborales + pasos;
            break;
            case SABADO: totalPasosSabado = totalPasosSabado + pasos;
            break;
            case DOMINGO: totalPasosDomingo = totalPasosDomingo + pasos;
            break;
        }
        double totalDistanciaFinSemanaCm = (totalPasosDomingo * longitudZancada)
             + (totalPasosSabado * longitudZancada);
        totalDistanciaFinSemana = totalDistanciaFinSemanaCm / 100000;
    }
    
     /**
     * Muestra en pantalla la configuración del podómetro
     * (altura, sexo y longitud de la zancada)
     * 
     * (ver enunciado)
     *  
     */
    public void printConfiguracion(){
        double alturaEnMetros = altura / 100;
        double longitudZancadaEnMetros = longitudZancada / 100;
        System.out.println("Configuración del podómetro");
        System.out.println("***************************");
        System.out.println("Altura: " + alturaEnMetros + " mtos");
        switch(sexo){
            case HOMBRE: System.out.println("Sexo: HOMBRE");
            break;
            case MUJER: System.out.println("Sexo: MUJER");
            break;
        }
        System.out.println("Longitud zancada: " + longitudZancadaEnMetros + " mtos");

    }

    /**
     * Muestra en pantalla información acerca de la distancia recorrida,
     * pasos, tiempo total caminado, ....
     * 
     * (leer enunciado)
     *  
     */
    public void printEstadísticas() {
        System.out.println("Estadísticas");
        System.out.println("***************************");
        System.out.println("Distancia recorrida toda la semana: " + totalDistanciaSemana + " Km");
        System.out.println("Distancia recorrida fin de semana: " + totalDistanciaFinSemana + " Km");
        System.out.println("");
        System.out.println("Nº pasos días laborales: " + totalPasosLaborales);
        System.out.println("Nº pasos SÁBADO: " + totalPasosSabado);
        System.out.println("Nº pasos DOMINGO: " + totalPasosDomingo);
        System.out.println("");
        System.out.println("Nº caminatas realizadas a partir de las 21h.: " + caminatasNoche);
        System.out.println("");
        System.out.println("Tiempo total caminado en la semana: ");
        System.out.println("Día/s con más pasos caminados: ");

    }
   
    /**
     *  Calcula y devuelve un String que representa el nombre del día
     *  en el que se ha caminado más pasos - "SÁBADO"   "DOMINGO" o  "LABORABLES"
     */
    public String diaMayorNumeroPasos(){
        if(totalPasosLaborales > totalPasosDomingo && totalPasosLaborales > totalPasosSabado){
            System.out.println("LABORABLES");
        }
        else if(totalPasosDomingo > totalPasosSabado && totalPasosDomingo > totalPasosLaborales){
            System.out.println("DOMINGO");
        }
        else if(totalPasosSabado > totalPasosDomingo && totalPasosSabado > totalPasosLaborales){
            System.out.println("SABADO");
        }
        return "";
    }
    
    
    /**
     * Restablecer los valores iniciales del podómetro
     * Todos los atributos se ponen a cero salvo el sexo
     * que se establece a MUJER. La marca no varía
     *  
     */    
    public void reset(){
        sexo = MUJER;
        altura = 0;
        longitudZancada = 0;
        totalPasosLaborales = 0;
        totalPasosSabado = 0;
        totalPasosDomingo = 0;
        totalDistanciaSemana = 0;
        totalDistanciaFinSemana = 0;
        tiempo = 0;
        caminatasNoche = 0;
    }

}
