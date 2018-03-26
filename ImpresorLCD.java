public class ImpresorLCD {

    private PantallaLCD pantallaLCD;

    // TODO code application logic here
    //String entrada = JOptionPane.showInputDialog("Digite el numero");


    public ImpresorLCD() {
    }

    /**
     *
     * Metodo encargado de imprimir un numero
     *
     * @param size Tamaño Segmento Digitos
     * @param numeroImp Numero a Imprimir
     * @param espacio Espacio Entre digitos
     */    
    private void imprimirNumero(int size, String numeroImp, int espacio) 
    {
        char[] digitos;
        // Calcula el numero de filasDig
        int filasDig, columDig, totalFilas, totalColum;

        // Calcula el numero de filas cada digito
        filasDig = (2 * size) + 3;

        // Calcula el numero de columna de cada digito
        columDig = size + 2;

        // Calcula el total de filas de la matriz en la que se almacenaran los digitos
        totalFilas = filasDig;

        // Calcula el total de columnas de la matriz en la que se almacenaran los digitos
        totalColum = (columDig * numeroImp.length())
                + (espacio * numeroImp.length());

        // crea matriz para almacenar los numero a imprimir
        this.pantallaLCD = new PantallaLCD(size, filasDig, columDig,
                totalColum, totalFilas);

        // crea el arreglo de digitos
        digitos = numeroImp.toCharArray();

        for (char digito : digitos) {
            
            //Valida que el caracter sea un digito
            if( ! Character.isDigit(digito))
            {
                throw new IllegalArgumentException("Caracter " + digito
                    + " no es un digito");
            }

            int numero = Integer.parseInt(String.valueOf(digito));

            this.pantallaLCD.adicionarDigito(numero, espacio);
        }

        this.pantallaLCD.imprimir();
    }

     /**
     *
     * Metodo encargado de procesar la entrada que contiene el size del segmento
     * de los digitos y los digitos a imprimir
     *
     * @param comando Entrada que contiene el size del segmento de los digito
     * y el numero a imprimir
     * @param espacioDig Espacio Entre digitos
     */  
    public void procesar(String comando, int espacioDig) {
        
        String[] parametros;
        
        int tam;

        if (!comando.contains(",")) {
            throw new IllegalArgumentException("Cadena " + comando
                    + " no contiene caracter ,");
        }
        
        //Se hace el split de la cadena
        parametros = comando.split(",");
        
        //Valida la cantidad de parametros
        if(parametros.length>2)
        {
           throw new IllegalArgumentException("Cadena " + comando
                    + " contiene mas caracter ,"); 
        }
        
        //Valida la cantidad de parametros
        if(parametros.length<2)
        {
           throw new IllegalArgumentException("Cadena " + comando
                    + " no contiene los parametros requeridos"); 
        }
        
        //Valida que el parametro size sea un numerico
        if(Utils.isNumeric(parametros[0]))
        {
            tam = Integer.parseInt(parametros[0]);
            
            // se valida que el size este entre 1 y 10
            if(tam <1 || tam >10)
            {
                throw new IllegalArgumentException("El parametro size ["+tam
                        + "] debe estar entre 1 y 10");
            }
        }
        else
        {
            throw new IllegalArgumentException("Parametro Size [" + parametros[0]
                    + "] no es un numero");
        }

        // Realiza la impresion del numero
        imprimirNumero(tam, parametros[1],espacioDig);

    }

}
