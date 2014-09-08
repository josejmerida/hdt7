package hdt7;
public class Huffman {
	
	private  char ArregloCaracteres[]; /*Variable para convertir el String en un arreglo de caracteres*/
	private  int tablaF[] = new int[0x7f]; /*Variable que contiene las frecuencias*/
        private  Nod nodoM[]; /* Lista de tipo nodoH*/
	private  int tamTabla = 0; /*Tamaño de Tabla*/
	private  Tree arbolH;  /* Arbol*/
	private  int contadorNodos = 0; /*contador de nodos*/
	public  Huff hC;  /*parametro de tipo HuffmanT*/
        
        //Contructor que recibe de parametro un String
        public Huffman(String valor)
        {
			//mandamos a llamar a funciones
            frecuenciaTabla(valor);
            ordenarNodo();
			//Creamos nuestro objetos
            Nod nodo = crearArbolH();
            hC = new Huff(nodo,ArregloCaracteres);
        }
		//Metodo que acumula las frecuencias de los datos
        public  void frecuenciaTabla(String value)
		{
			int i;
			ArregloCaracteres = value.toCharArray();
			for(i = 0; i < ArregloCaracteres.length; i++)
				tablaF[obtenerAscii(ArregloCaracteres[i])] += 1; 
		}
		//obtenemos el ascii del dato
		public  int obtenerAscii(char substringValue)
		{
			return substringValue&0x7f;
		}
             
                //metodo utilizado para crear lso arboles
        public  Nod crearArbolH()
        {
           for(int i = 1; i < contadorNodos ; i++)
           {
				//verificamos si la frecuencia es mayor o igual 
               if(nodoM[1].frecuA >= nodoM[0].frecuA)
               {
					//Creamos el arbol 
                   arbolH = new Tree(nodoM[0],nodoM[i]);
                   nodoM[0] = arbolH;
                   moverO(i, contadorNodos );
                   contadorNodos  -= 1; 
                   i -= 1;
                   sort();
               }
               else
               {
                   if(i+1 < contadorNodos )
                   {
                        arbolH = new Tree(nodoM[i], nodoM[i+1]);
                        nodoM[1] = arbolH;
                        moverO(i+1, contadorNodos );
                        sort();
                        contadorNodos  -= 1;
                        i -= 1;
                   }
                   else
                   {
                       nodoM[1] = nodoM[i];
                       nodoM[0] = new Tree(nodoM[0], nodoM[1]);
                   }
               }

           }
            return nodoM[0];
        }
                
		//metodo utilizado para ordenar los nodos
        public  void ordenarNodo()    
        {
            int cont = 0;
            int j = 0;
            for(int i = 0; i < tablaF.length; i++)
            {
                if(tablaF[i]>0)
                    cont++;
            }
            
            tamTabla =  cont;
            cont = 0;    
            nodoM = new Nod[tamTabla];
            
            for(int i = 0; i < 127; i++)
            {
                if(tablaF[i] != 0)
                {
                    nodoM[cont]= new Nod(tablaF[i], (char)i, null, null);
                    cont++;
                }
            }
            contadorNodos  = nodoM.length;
            sort();
            
        }
        
		//Metodo utilizado para mover una posicion los valores de la lista
        private  void moverO(int index, int length)
        {   try
            {
				//movemos una posicion los valores 
                for(int i = index; i < length; i++)
                    nodoM[i] = nodoM[i+1];
                
            }
            catch(Exception e)
            {
                System.out.println("Error");
            }
        }
		//metodo que ordena la expresion en orden a la frecuencia
        private  void sort()
        {
            Nod temp;
			//recorremos segun el contador de nodos
            for(int i = contadorNodos -1; i > 1; i--)
            {
                for(int j = 0; j < i; j++)
                {
					//verificamos cual posee mayor frecuencia
                    if(nodoM[j].frecuA > nodoM[j+1].frecuA)
                    {
                        temp = nodoM[j+1];
                        nodoM[j+1] = nodoM[j];
                        nodoM[j] = temp;
                    }
                    //verificamos si son iguales 
                    if(nodoM[j].frecuA == nodoM[j+1].frecuA && nodoM[j].izq != null)
                    {
                        temp = nodoM[j+1];
                        nodoM[j+1] = nodoM[j];
                        nodoM[j] = temp;
                    }
                }
            }
        }
}