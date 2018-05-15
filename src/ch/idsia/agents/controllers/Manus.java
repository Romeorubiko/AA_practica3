package ch.idsia.agents.controllers;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import ch.idsia.agents.Agent;
import ch.idsia.benchmark.mario.engine.sprites.Mario;
import ch.idsia.benchmark.mario.environments.Environment;
import ch.idsia.benchmark.mario.environments.MarioEnvironment;

import java.util.LinkedList;


public class Manus extends BasicMarioAIAgent implements Agent {
	
	int tick;
	int salto = 0;
    private byte[][] mergeObsr = null;
	private Funcion funcion;
	Instancia ins = new Instancia();
    private FileWriter fichero;
    private String path = "Manus.arff";
    String[] acciones = {"0", "1", "2", "3"};
    String[] estados  = {"0", "1", "2", "3"};
    QLearning ql  = new QLearning(0, 0.25, 0.5, estados, acciones, 4, 4);
	List<Tupla> mapa  = new ArrayList<Tupla>();
	LinkedList<Instancia> instancias;
	
    public Manus() throws IOException{
	    super("Manus");
	    fichero = new FileWriter(path, true);
        BufferedReader br = new BufferedReader(new FileReader(path));     
		if (br.readLine() == null) {
			
			Grabador.cabeceraWeka(fichero ,"Manus");
	    		System.out.println("No errors, and file empty");
		}
		else{
			br.close();
			Grabador.borrarUltimaLinea(path);
			PrintWriter pw = new PrintWriter(fichero);
			pw.println();
		}
	    tick = 0;
	    
		funcion = new Funcion();
		funcion.indexar("input.arff");
		instancias = funcion.instancias;
		/*
		public static final int DERECHA=0;
		public static final int IZQUIERDA=1;
		public static final int SALTAR=2;
		public static final int SALTARDERECHA=3;
		public static final int SALTARIZQUIERDA=4;
		*/

        Instancia ins = new Instancia();

        
		//Acciones y estados elegidos. 0 = Monstruo cerca; 1 = moneda cerca; 2= Hay un obstaculo que saltar; 3 = No hay nada;
		
        
        
        int estadoInicial;
        int estadoFinal;
        int accion;
        int refuerzo;
        
        boolean encontrado;
        
        
        int ciclosMaximos   = 8;
        int ciclos          = 0;
        int posicion;
		
        //Recorremos el array añadiendo en el mapa las tuplas. Usamos el LinkedList instancias el cual todavia no esta declarado
        for(int i=0; i <instancias.size(); i++) {
        	ins = instancias.get(i);
        	estadoInicial = ins.situacion;
            estadoFinal = ins.situacion24;
            //Creamos una nueva función que nos determine la accion tomada
            accion = determinarAccion(ins);
            refuerzo = ins.evaluacion;      			
        	mapa.add(new Tupla(estadoInicial, accion, estadoFinal, refuerzo));  
        }

		
        while (ciclos < ciclosMaximos)
        {
            for (int i = 0; i < mapa.size(); i++)
                ql.actualizarTablaQ(mapa.get(i));
		if(ql._alpha >0.10)ql._alpha -= 0.04;
		//if(ql._gamma <0.9)ql._gamma += 0.04;
            ciclos++;
        }
        
        ql.mostrarTablaQ();
   				
	}
    
    public int determinarAccion(Instancia ins){
    	//Desplazarse a la derecha
    	if(ins.right && !ins.jump) return 0;
    	//Salto a la derecha
    	else if(ins.right && ins.jump) return 3;
    	//Salto en el sitio
	else if(!ins.right && !ins.left && ins.jump) return 2;
	//Desplazarse a la izquierda
	else if(ins.left && !ins.jump) return 1;
	else{
		return 0;
	}
    }

    public void reset() {
    }

    public void integrateObservation(Environment environment) {
		mergeObsr = environment.getMergedObservationZZ(1, 1);
		
		ins.marioMode = environment.getMarioMode();
		ins.reward = environment.getIntermediateReward();
		ins.distance = environment.getEvaluationInfo().distancePassedCells;
		ins.merge9_10 = mergeObsr[9][10];	
		ins.merge9_11 = mergeObsr[9][11];
		ins.merge9_12 = mergeObsr[9][12];
		ins.merge8_10 = mergeObsr[8][10];
		ins.merge8_11 = mergeObsr[8][11];
		ins.merge8_12 = mergeObsr[8][12];
		ins.merge7_10 = mergeObsr[7][10];
		ins.merge7_11 = mergeObsr[7][11];
		ins.merge7_12 = mergeObsr[7][12];
		ins.merge6_10 = mergeObsr[6][10];
		ins.merge6_11 = mergeObsr[6][11];
		ins.merge6_12 = mergeObsr[6][12];
		ins.merge5_10 = mergeObsr[5][10];
		ins.merge5_11 = mergeObsr[5][11];
		ins.merge5_12 = mergeObsr[5][12];
		Grabador.grabar((MarioEnvironment)environment, action, fichero);
        tick++;
    }

    public boolean[] getAction() {

	action[Mario.KEY_DOWN] = false;
        action[Mario.KEY_UP] = false;
        action[Mario.KEY_RIGHT] = false;
        action[Mario.KEY_LEFT] = false;
        action[Mario.KEY_SPEED] = false;
        action[Mario.KEY_JUMP] = false;

		boolean MarioOnGround = true;

		int nearestCrea = Grabador.nearestCreature(mergeObsr);
		int nearestCoin = Grabador.nearestCoin(mergeObsr);
		if(mergeObsr[10][9] != 0) MarioOnGround = true;
		if(action[Mario.KEY_JUMP]) salto ++;
		else salto = 0;

		ins.nearestCreature = nearestCrea;
		ins.nearestCoin = nearestCoin;
		ins.marioOnGorund = MarioOnGround;
		ins.saltoSeguido = salto;


	   //BUSCAMOS EN LA TABLA Q LA SITUACION OBTENIDA Y HACEMOS LA ACCION CON MAS REFUERZO
	   double[] resultado = ql._tablaQ[funcion.pertenencia(ins, false)];
	   int m_pos = 0;
		
		
	   for (int i = 1; i < resultado.length; i++)
		   if(resultado[m_pos]<resultado[i])m_pos =i;
	if(funcion.pertenencia(ins, false) == 2 && Math.random()*10<8)m_pos =3;
	//if(tick%24==0)System.out.println("Situ: "+funcion.pertenencia(ins, false)+";acc: "+m_pos);
	   switch (m_pos) {
			case 0:
				action[Mario.KEY_RIGHT] = true;
				break;
			case 1:
				action[Mario.KEY_LEFT] = true;
				action[Mario.KEY_RIGHT] = false;
				break;
			case 2:
				action[Mario.KEY_JUMP] = true;
				break;
			case 3:
				action[Mario.KEY_RIGHT] = true;
				action[Mario.KEY_JUMP] = true;
				break;
			case 4:
				action[Mario.KEY_LEFT] = true;
				action[Mario.KEY_JUMP] = true;
				break;
	
		}
	
       return action;
    
    }
    
}
