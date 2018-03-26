import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PantallaLCD {
    // Puntos fijos
    private final int[] pf1;
    private final int[] pf2;
    private final int[] pf3;
    private final int[] pf4;
    private final int[] pf5;
    private int pivotX;
    private final String[][] matrizImpr;

    static final String CARACTER_VERTICAL = "|";
    static final String CARACTER_HORIZONTAL = "-";
    static final String POSICION_X = "X";
    static final String POSICION_Y = "Y";

    private final int sizeDigitos;
    private final int filasDigitos;
    private final int columnasDigitos;
    private final int totalFilas;
    private final int totalColumnas;

    public PantallaLCD(int sizeDigitos, int filasDigitos, int columnasDigitos, int totalColumnas, int totalFilas) {
        this.matrizImpr =  new String[totalFilas][totalColumnas];
        this.sizeDigitos = sizeDigitos;
        this.filasDigitos = filasDigitos;
        this.columnasDigitos = columnasDigitos;
        this.totalFilas = totalFilas;
        this.totalColumnas = totalColumnas;
        this.pivotX = 0;
        
        this.pf1 = new int[2];
        this.pf2 = new int[2];
        this.pf3 = new int[2];
        this.pf4 = new int[2];
        this.pf5 = new int[2];

        // Inicializa matriz
        for (int i = 0; i < this.totalFilas; i++) {
            for (int j = 0; j < this.totalColumnas; j++) {
                this.matrizImpr[i][j] = " ";
            }
        }
    }

    /**
     *
     * Metodo encargado de definir los segmentos que componen un digito y
     * a partir de los segmentos adicionar la representacion del digito a la matriz
     *
     * @param numero Digito
     * @param espacio
     */
    public void adicionarDigito(int numero, int espacio) {
        //Calcula puntos fijos
        this.pf1[0] = 0;
        this.pf1[1] = 0 + this.pivotX;

        this.pf2[0] = (this.filasDigitos / 2);
        this.pf2[1] = 0 + this.pivotX;

        this.pf3[0] = (this.filasDigitos - 1);
        this.pf3[1] = 0 + this.pivotX;

        this.pf4[0] = (this.columnasDigitos - 1);
        this.pf4[1] = (this.filasDigitos / 2) + this.pivotX;

        this.pf5[0] = 0;
        this.pf5[1] = (this.columnasDigitos - 1) + this.pivotX;

        this.pivotX = this.pivotX + this.columnasDigitos + espacio;

        // Establece los segmentos de cada numero
        List<Integer> segList = new ArrayList<>();

        switch (numero) {
            case 1:
                segList.add(3);
                segList.add(4);
                break;
            case 2:
                segList.add(5);
                segList.add(3);
                segList.add(6);
                segList.add(2);
                segList.add(7);
                break;
            case 3:
                segList.add(5);
                segList.add(3);
                segList.add(6);
                segList.add(4);
                segList.add(7);
                break;
            case 4:
                segList.add(1);
                segList.add(6);
                segList.add(3);
                segList.add(4);
                break;
            case 5:
                segList.add(5);
                segList.add(1);
                segList.add(6);
                segList.add(4);
                segList.add(7);
                break;
            case 6:
                segList.add(5);
                segList.add(1);
                segList.add(6);
                segList.add(2);
                segList.add(7);
                segList.add(4);
                break;
            case 7:
                segList.add(5);
                segList.add(3);
                segList.add(4);
                break;
            case 8:
                segList.add(1);
                segList.add(2);
                segList.add(3);
                segList.add(4);
                segList.add(5);
                segList.add(6);
                segList.add(7);
                break;
            case 9:
                segList.add(1);
                segList.add(3);
                segList.add(4);
                segList.add(5);
                segList.add(6);
                segList.add(7);
                break;
            case 0:
                segList.add(1);
                segList.add(2);
                segList.add(3);
                segList.add(4);
                segList.add(5);
                segList.add(7);
                break;
            default:
                break;
        }

        Iterator<Integer> iterator = segList.iterator();

        while (iterator.hasNext()) {
            adicionarSegmento(iterator.next());
        }
    }

    /**
     *
     * Metodo encargado de un segmento a la matriz de Impresion
     *
     * @param segmento Segmento a adicionar
     */  
    private void adicionarSegmento(int segmento) {

        switch (segmento) {
            case 1:
                adicionarLinea(this.pf1, POSICION_Y,
                        this.sizeDigitos, CARACTER_VERTICAL);
                break;
            case 2:
                adicionarLinea(this.pf2, POSICION_Y,
                        this.sizeDigitos, CARACTER_VERTICAL);
                break;
            case 3:
                adicionarLinea(this.pf5, POSICION_Y,
                        this.sizeDigitos, CARACTER_VERTICAL);
                break;
            case 4:
                adicionarLinea(this.pf4, POSICION_Y,
                        this.sizeDigitos, CARACTER_VERTICAL);
                break;
            case 5:
                adicionarLinea(this.pf1, POSICION_X,
                        this.sizeDigitos, CARACTER_HORIZONTAL);
                break;
            case 6:
                adicionarLinea(this.pf2, POSICION_X,
                        this.sizeDigitos, CARACTER_HORIZONTAL);
                break;
            case 7:
                adicionarLinea(this.pf3, POSICION_X,
                        this.sizeDigitos, CARACTER_HORIZONTAL);
                break;
            default:
                break;
        }
    }

    /**
     *
     * Metodo encargado de añadir una linea a la matriz de Impresion
     *
     * @param matriz Matriz Impresion
     * @param punto Punto Pivote
     * @param posFija Posicion Fija
     * @param size Tamaño Segmento
     * @param caracter Caracter Segmento
     */    
    private void adicionarLinea(int[] punto, String posFija,
            int size, String caracter) {

        if (posFija.equalsIgnoreCase(POSICION_X)) 
        {
            for (int y = 1; y <= size; y++) 
            {
                int valor = punto[1] + y;
                this.matrizImpr[punto[0]][valor] = caracter;
            }
        } 
        else 
        {
            for (int i = 1; i <= size; i++) 
            {
                int valor = punto[0] + i;
                this.matrizImpr[valor][punto[1]] = caracter;
            }
        }
    }

    public void imprimir() {
        // Imprime matriz
        for (int i = 0; i < this.totalFilas; i++) {
            for (int j = 0; j < this.totalColumnas; j++) {
                System.out.print(this.matrizImpr[i][j]);
            }
            System.out.println();
        }
    }

}