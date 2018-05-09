/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package qlearning;

import java.util.*;


/**
 *
 * @author moises
 */
public class main 
{
	public static final int DERECHA=0;
	public static final int IZQUIERDA=1;
	public static final int ARRIBA=2;
	public static final int ABAJO=3;
	
    public static void main(String[] args) 
    {   
        List<Tupla> mapa  = new ArrayList<Tupla>();
        		
        String[] acciones = {"0", "1", "2", "3"};
        String[] estados  = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18","19"};
        
        double[] estado = {2};
        double[] estadoFinal = {17};
        double[] accion = {0};
        double refuerzo = 0;
        
        boolean encontrado;
        
        QLearning ql        = new QLearning(0, 1, 0.3, estados, acciones, 20, 4);
        int ciclosMaximos   = 9;
        int ciclos          = 0;
        int posicion;
		
		//estadoActual,acción, estadoSiguiente, refuerzo
		mapa.add(new Tupla(0, DERECHA, 1, 0)); 
        mapa.add(new Tupla(0, ABAJO, 5, 0)); 
		mapa.add(new Tupla(1, IZQUIERDA, 0, 0));
		mapa.add(new Tupla(1, DERECHA, 2, 0));
		mapa.add(new Tupla(2, IZQUIERDA, 1, 0));
		mapa.add(new Tupla(2, ABAJO, 3, 0));
		mapa.add(new Tupla(3, IZQUIERDA, 2, 0));
		mapa.add(new Tupla(3, DERECHA, 4, 0));
		mapa.add(new Tupla(3, ABAJO, 8, 0));
		mapa.add(new Tupla(4, IZQUIERDA, 3, 0));
		mapa.add(new Tupla(5, ARRIBA, 0, 0));
		mapa.add(new Tupla(5, ABAJO, 10, 0));
		mapa.add(new Tupla(8, ARRIBA, 3, 0));
		mapa.add(new Tupla(8, ABAJO, 13, 0));
		mapa.add(new Tupla(10, ARRIBA, 5, 0));
		mapa.add(new Tupla(10, DERECHA, 10, 0));
		mapa.add(new Tupla(11, ABAJO, 16, 0));
		mapa.add(new Tupla(11, IZQUIERDA, 10, 0));
		mapa.add(new Tupla(13, ARRIBA, 8, 0));
		mapa.add(new Tupla(13, DERECHA, 14, 0));
		mapa.add(new Tupla(13, ABAJO, 18, 0));
		mapa.add(new Tupla(14, ABAJO, 19, 0));
		mapa.add(new Tupla(14, IZQUIERDA, 13, 0));
		mapa.add(new Tupla(16, ARRIBA, 11, 0));
		mapa.add(new Tupla(16, DERECHA, 17, 1));
		mapa.add(new Tupla(17, IZQUIERDA, 16, 0));
		mapa.add(new Tupla(17, DERECHA, 18, 0));
		mapa.add(new Tupla(18, ARRIBA, 13, 0));
		mapa.add(new Tupla(18, DERECHA, 19, 0));
		mapa.add(new Tupla(18, IZQUIERDA, 17, 1));
		mapa.add(new Tupla(19, ARRIBA, 14, 0));
		mapa.add(new Tupla(19, IZQUIERDA, 18, 0));
/*
		mapa.add(new Tupla(18, IZQUIERDA, 19, 0));
		mapa.add(new Tupla(18, IZQUIERDA, 19, 0));
		mapa.add(new Tupla(18, IZQUIERDA, 19, 0));
*/
		
        while (ciclos < ciclosMaximos)
        {
            for (int i = 0; i < mapa.size(); i++)
                ql.actualizarTablaQ(mapa.get(i));
            
            ciclos++;
        }
        
        ql.mostrarTablaQ();
        boolean salir = false;
        while (estado[0] != estadoFinal[0] && !salir)
        {
            accion      = ql.obtenerMejorAccion(estado);
            refuerzo    = ql.obtenerFuncionQMax(estado);
            posicion    = 0;
            encontrado  = false;
            
            while (!encontrado && !salir)
            {
                if ((mapa.get(posicion).getEstado(0) == estado[0]) && (mapa.get(posicion).getAccion(0) == accion[0])) 
                {
                    System.out.print("Transito de " + estado[0]);
                            
                    estado[0] = mapa.get(posicion).getEstadoSiguiente(0);
                    
                    System.out.println(" a " + estado[0] + " con " + accion[0] + "(" + (double)Math.round(refuerzo * 100)/100 + ")");
                    
                    encontrado = true;
                }
                
                posicion++;

		if(posicion >= mapa.size()) salir = true;
            }
        }
    }
}
