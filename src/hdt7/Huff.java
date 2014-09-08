package hdt7;
public class Huff
{
	//declaramos los parametros 
    private Nod direccionNodo;
    private char arregloCharac[];
    public String patronBitFinal = "";
    
	//constructor con sus respectivos parametros
    public Huff(Nod Nodo, char [] arregloC)
    {
        String temp;
        direccionNodo = Nodo;
        this.arregloCharac = arregloC;
		//
        for(int i = 0; i < arregloC.length; i++)
        {
			//mandamos a llamar a la funcion buscar valor 
            temp = buscarVal(direccionNodo, "", arregloC[i]);
            patronBitFinal += temp + " ";
			//nos devuelve la secuencia binaria
            System.out.println("Valores: "+ arregloC[i] + " " + temp);
        }
    }
    // metodo utiliado para buscar los valores 
	//Verifica si existe un nodo con los parametros requeridos
    public String buscarVal(Nod direccionNodo, String valor,char caracter)
    {
        String valorL = "";
		//verificamos si no es null
        if(direccionNodo != null)
        {
			//verificamos si no es null
            if(direccionNodo.izq != null){
				//devolvemos el valor encontrado
                valorL = buscarVal(direccionNodo.izq, valor + "0", caracter);
            }
			//si el caracter el igual
            if(direccionNodo.caracter == caracter){
			//devolvemos el valor 
                return valor;
            }
            else
            {
				//si es igual a vacio
                if(valorL == "")
                {
					//retornamos el valor econtrado con estos parametros
                    return buscarVal(direccionNodo.der, valor+"1",caracter);
                }
                else
                {
					//sino retornamos ""
                    return valorL;
                }
            }
        }
        else
        {
		//sino retornamos ""
            return "";
        }
    }
        
}