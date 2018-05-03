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

public class Frampt extends BasicMarioAIAgent implements Agent {

    int tick;
    private byte[][] mergeObsr = null;
    private int salto = 0; 
    private int anteriorBloque = 0;
    private int precaucion = 0;
    private int agacharse =0;
    private String path = "Frampt.arff";
    private FileWriter fichero;
    private boolean grabar = true;

    public Frampt() throws IOException{
	        super("Frampt");
	        tick = 0;
		fichero = new FileWriter(path, true);
	        BufferedReader br = new BufferedReader(new FileReader(path));     
		if (br.readLine() == null) {
			
			Grabador.cabeceraWeka(fichero ,"Frampt");
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
	grabar = true;
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
       
	int nearestCrea = Grabador.nearestCreature(mergeObsr);
	int nearestCoin = Grabador.nearestCoin(mergeObsr);
	boolean MarioOnGround;
	if(mergeObsr[10][9] != 0) MarioOnGround = true;
	else MarioOnGround = false;
	if(action[Mario.KEY_JUMP]) salto ++;
	else salto = 0;

	if(salto <= 7){
		if(nearestCrea <= 3){
			if(salto <= 2){
				if(salto <= 1) {
					action[Mario.KEY_JUMP] = true; action[Mario.KEY_RIGHT] = true;
				}
				else {
					if(nearestCrea <= 2) {
						action[Mario.KEY_JUMP] = false; action[Mario.KEY_RIGHT] = true;
					}
					else action[Mario.KEY_JUMP] = true; action[Mario.KEY_RIGHT] = true;
				}
			}
			else action[Mario.KEY_JUMP] = false; action[Mario.KEY_RIGHT] = true;		
		}
		else{
			if(salto <= 0){
				if(MarioOnGround){
					 action[Mario.KEY_JUMP] = true; action[Mario.KEY_RIGHT] = true;
				}
				else{
					if(nearestCoin <= 5){
						 action[Mario.KEY_JUMP] = true; action[Mario.KEY_RIGHT] = true;
					}
					else action[Mario.KEY_JUMP] = false; action[Mario.KEY_RIGHT] = true;
				}
			}
			else{
				if(salto <= 4){
					if(MarioOnGround){
						if(salto <= 3){
							 action[Mario.KEY_JUMP] = true; action[Mario.KEY_RIGHT] = true;
						}
						else {
							if(nearestCrea <= 5) {
								action[Mario.KEY_JUMP] = false; action[Mario.KEY_RIGHT] = true;
							}
							else action[Mario.KEY_JUMP] = true; action[Mario.KEY_RIGHT] = true;
						}
					}
					else action[Mario.KEY_JUMP] = true; action[Mario.KEY_RIGHT] = true;
				}
				else{
					if(nearestCrea <= 5) {
						action[Mario.KEY_JUMP] = false; action[Mario.KEY_RIGHT] = true;
					}
					else{
						if(nearestCrea <= 5){
							 action[Mario.KEY_JUMP] = true; action[Mario.KEY_RIGHT] = true;
						}
						else{
							if(nearestCrea <= 7){
								 action[Mario.KEY_JUMP] = true; action[Mario.KEY_RIGHT] = true;
							}
							else{

								if(mergeObsr[10][9] == -85) action[Mario.KEY_JUMP] = true; action[Mario.KEY_RIGHT] = true;
								if(mergeObsr[10][9] == -24) action[Mario.KEY_JUMP] = false; action[Mario.KEY_RIGHT] = true;
								if(mergeObsr[10][9] == -60) action[Mario.KEY_JUMP] = true; action[Mario.KEY_RIGHT] = true;
								if(mergeObsr[10][9] == -62) action[Mario.KEY_JUMP] = false; action[Mario.KEY_RIGHT] = true;
								if(mergeObsr[10][9] == 0) action[Mario.KEY_JUMP] = true; action[Mario.KEY_RIGHT] = true;
								if(mergeObsr[10][9] == 2) action[Mario.KEY_JUMP] = true; action[Mario.KEY_RIGHT] = true;
								if(mergeObsr[10][9] == 3) action[Mario.KEY_JUMP] = true; action[Mario.KEY_RIGHT] = true;
								if(mergeObsr[10][9] == 25) action[Mario.KEY_JUMP] = true; action[Mario.KEY_RIGHT] = true;
								if(mergeObsr[10][9] == 80) action[Mario.KEY_JUMP] = false; action[Mario.KEY_RIGHT] = true;
								if(mergeObsr[10][9] == 93) action[Mario.KEY_JUMP] = true; action[Mario.KEY_RIGHT] = true;
								if(mergeObsr[9][10] != 0) action[Mario.KEY_JUMP] = true; action[Mario.KEY_RIGHT] = true;
							}	
						}
					}
				}
			}
	
		}
	}
	else{
		action[Mario.KEY_JUMP] = false; action[Mario.KEY_RIGHT] = true;
	}
		
		return action;
    }
    
}
