package ch.idsia.agents.controllers;

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


public class Kaathe extends BasicMarioAIAgent implements Agent {

	int tick;
	int salto = 0;
    private byte[][] mergeObsr = null;
	private Funcion funcion;
	Instancia ins = new Instancia();
	Instancia resultado = new Instancia();
	String path = "P2BotAgent.txt";
	FileWriter fichero;
	
    public Kaathe() throws IOException{
	        super("Kaathe");
	        tick = 0;
		fichero = new FileWriter(path, true);
	        BufferedReader br = new BufferedReader(new FileReader(path));     
		if (br.readLine() == null) {
			
			Grabador.cabeceraWeka(fichero ,"P2BotAgent");
	    		System.out.println("No errors, and file empty");
		}
		else{
			br.close();
			Grabador.borrarUltimaLinea(path);
			PrintWriter pw = new PrintWriter(fichero);
			pw.println();
		}

		funcion = new Funcion();
		funcion.indexar("input.arff");
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
        action[Mario.KEY_RIGHT] = true;
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
		
		int situacion = funcion.pertenecia(ins);

	   resultado = funcion.similitud(ins,funcion.pertenece[situacion]);
	   
	   action[Mario.KEY_DOWN] = resultado.down;
       action[Mario.KEY_UP] = resultado.up;
       action[Mario.KEY_RIGHT] = resultado.right;
       action[Mario.KEY_LEFT] = resultado.left;
       action[Mario.KEY_SPEED] = resultado.speed;
       action[Mario.KEY_JUMP] = resultado.jump;
       
       return action;
    
    }
    
}
