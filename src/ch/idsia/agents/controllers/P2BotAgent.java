/*
 * Copyright (c) 2009-2010, Sergey Karakovskiy and Julian Togelius
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of the Mario AI nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */

package ch.idsia.agents.controllers;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.File;
import java.io.FileReader;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.PrintWriter;

import ch.idsia.agents.Agent;
import ch.idsia.benchmark.mario.engine.Replayer;
import ch.idsia.benchmark.mario.engine.sprites.Mario;
import ch.idsia.benchmark.mario.environments.Environment;
import ch.idsia.benchmark.mario.environments.MarioEnvironment;
import ch.idsia.tools.EvaluationInfo;
import ch.idsia.tools.MarioAIOptions;

import java.util.Random;

public class P2BotAgent extends BasicMarioAIAgent implements Agent {

    int tick;
    private byte[][] mergeObsr = null;
    private int salto = 0; 
    private int anteriorBloque = 0;
    private int precaucion = 0;
    private int agacharse =0;
    private String path = "P2BotTest.arff";
    private FileWriter fichero;
    private boolean grabar = true;
    private Funcion funcion;

    public P2BotAgent() throws IOException{
        super("P2BotAgent");
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
	}

    public void reset() {
    }

    public void integrateObservation(Environment environment) {
        mergeObsr = environment.getMergedObservationZZ(1, 1);
        tick++;
	grabar = true;//simpre se graba
        if(grabar) {
	        Grabador.grabar((MarioEnvironment)environment, action, fichero);
	        if(tick%24 == 0)grabar = false;
        }
    }

    public boolean[] getAction() {
       
    	action[Mario.KEY_DOWN] = false;
        action[Mario.KEY_UP] = false;
        action[Mario.KEY_RIGHT] = true;
        action[Mario.KEY_LEFT] = false;
        action[Mario.KEY_SPEED] = false;
        
        int[][] enemigos = new int[][] {{9,10},{9,11},{9,12},{9,13},{10,10},{10,12},{10,13},{10,11},{11,10},{11,11}};
        int[][] obstaculos = new int[][] {{9,10},{9,11},{9,12},{8,12},{7,12},{6,12},{10,12},{11,12},{11,9}}; 
	/*if(mergeObsr[9][10]==80 && action[Mario.KEY_RIGHT]){
		//action[Mario.KEY_SPEED] = true;
	}*/
         int nearest = Grabador.nearestCreature(mergeObsr);
        if(mergeObsr != null) {
        	if (nearest<4) {
				if(precaucion > 4){
					precaucion = 0;
					action[Mario.KEY_RIGHT] = false;
       				//action[Mario.KEY_LEFT] = true;
				}
				else if(action[Mario.KEY_RIGHT]) {
					//action[Mario.KEY_SPEED] = true;
					action[Mario.KEY_JUMP] = true;
					precaucion++;
				}
			}
        	for (int[] is : obstaculos) {
				if( mergeObsr[is[0]][is[1]]!=0
				|| mergeObsr[10][10]!=0) {
					action[Mario.KEY_JUMP] = true;
					grabar = true;
				}
			}
        }
        if(action[Mario.KEY_JUMP] && nearest<8) {
        	salto++;
        	if (salto > nearest) {
        		action[Mario.KEY_JUMP] = false;
        		salto = 0;
        		grabar = true;
        	}

        }
        else if(action[Mario.KEY_JUMP]){
        	salto++;
        	if (salto > Math.random()*3+5) {
        		action[Mario.KEY_JUMP] = false;
        		salto = 0;
        		grabar = true;
        	}
        	
        }
        if(anteriorBloque == 0 && mergeObsr[10][9]!=0){
        	action[Mario.KEY_JUMP] = false;
        	salto = 0; 
        }
		if(mergeObsr[8][10]!=0 && mergeObsr[9][10]==0) {
			action[Mario.KEY_DOWN] = true;
	        	agacharse++;
	        	if (agacharse > 2) {
	        		action[Mario.KEY_DOWN] = false;
	        		agacharse =0;
	        	}
	        }
		//if(action[Mario.KEY_JUMP] && mergeObsr[9][10]!=0)action[Mario.KEY_JUMP] = false;
		anteriorBloque = mergeObsr[10][9];
		action[Mario.KEY_DOWN] = false;
		return action;
    }
    
}
