package ch.idsia.agents.controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;

import ch.idsia.benchmark.mario.engine.sprites.Mario;
import ch.idsia.benchmark.mario.environments.MarioEnvironment;

public class Grabador {

	static final String output = "ejemplo.arff";
	static int detalle = 1;
	static int ticks = 0;
	static int saltoSeguido=0;
	
	static Queue<Integer> hace24reward = new LinkedList<Integer>();
	static Queue<Integer> hace24distance = new LinkedList<Integer>();
	static Queue<Integer> hace24mode = new LinkedList<Integer>();
	static Queue<boolean[]> action24 = new LinkedList<boolean[]>();
	static Queue<byte[][]> hace24merge = new LinkedList<byte[][]>();
	
	
	static void grabar(MarioEnvironment e, boolean[] action, FileWriter fichero) {
		hace24reward.add(e.getIntermediateReward());
		hace24mode.add(e.getEvaluationInfo().marioMode);
		hace24distance.add(e.getEvaluationInfo().distancePassedCells);
		action24.add(action.clone());
		hace24merge.add(e.getMergedObservationZZ(detalle, detalle));
		if(ticks < 24) {
			ticks++;
			}
		else {
			Instancia ins = new Instancia();
			action = action24.poll();
			Queue tempList = new LinkedList<>(hace24reward);
			
			PrintWriter pw = new PrintWriter(fichero);
			
			byte[][] temp = hace24merge.poll();
			for (int i = 5; i < 10; i++) {
				for (int j = 10; j < 13; j++) {
					pw.print(temp[i][j]+",");
				}
			}
			
			pw.print(hace24reward.peek()+",");
			ins.reward = hace24reward.peek();
			pw.print(nearestCoin(temp)+",");
			ins.nearestCoin = nearestCoin(temp);
			pw.print(nearestCreature(temp)+",");
			ins.nearestCreature = nearestCreature(temp);
			pw.print(hace24distance.peek()+",");
			ins.distance = hace24distance.peek();
		
			pw.print(saltoSeguido+",");
			ins.saltoSeguido = saltoSeguido;
			
			if (temp[10][9]!=0){
				pw.print("true,");
			}
			else{
				pw.print("false,");
			}
			
			for (int i = 0; i < 5; i++) tempList.poll();
			pw.print(tempList.peek()+",");
			ins.reward6 = (int)tempList.poll();
			for (int i = 0; i < 5; i++) tempList.poll();
			pw.print(tempList.peek()+",");
			ins.reward12 = (int)tempList.poll();
			for (int i = 0; i < 11; i++) tempList.poll();
			pw.print(tempList.peek()+",");
			ins.reward24 = (int)tempList.poll();
			hace24reward.poll();
			
			tempList = new LinkedList<>(hace24distance);
			for (int i = 0; i < 5; i++) tempList.poll();
			pw.print(tempList.peek()+",");
			ins.distance6 = (int)tempList.poll();
			for (int i = 0; i < 5; i++) tempList.poll();
			pw.print(tempList.peek()+",");
			ins.distance12 = (int)tempList.poll();
			for (int i = 0; i < 11; i++) tempList.poll();
			pw.print(tempList.peek()+",");
			ins.distance24 = (int)tempList.poll();
			hace24distance.poll();
			
			tempList = new LinkedList<>(hace24mode);
			for (int i = 0; i < 5; i++) tempList.poll();
			pw.print(tempList.peek()+",");
			ins.mode6 = (int)tempList.poll();
			for (int i = 0; i < 5; i++) tempList.poll();
			pw.print(tempList.peek()+",");
			ins.mode12 = (int)tempList.poll();
			for (int i = 0; i < 11; i++) tempList.poll();
			pw.print(tempList.peek()+",");
			ins.mode24 = (int)tempList.poll();
			hace24mode.poll();

			pw.print(e.getMarioMode()+",");
			
			pw.print(Funcion.evaluacion(ins)+",");	
			
			pw.print(action[Mario.KEY_DOWN]+",");
			pw.print(action[Mario.KEY_JUMP]+",");
			pw.print(action[Mario.KEY_LEFT]+",");
			pw.print(action[Mario.KEY_RIGHT]+",");
			pw.print(action[Mario.KEY_SPEED]+",");
			pw.print(action[Mario.KEY_UP]);
			
			
			if(action[Mario.KEY_JUMP])saltoSeguido++;
			else saltoSeguido=0;
			pw.println();
			ticks ++; 
		}
		
	}
	
	static int nearestCoin(byte[][] e) {
		byte[][] temp = e;
		if(temp[9][10]==2)return 1;
		else if(temp[8][10]==2||temp[10][10]==2||temp[9][11]==2)return 2;
		else if(temp[11][10]==2||temp[10][11]==2||temp[9][12]==2||temp[8][11]==2||temp[7][10]==2)return 3;
		else if(temp[12][10]==2||temp[11][11]==2||temp[10][12]==2||temp[8][12]==2||temp[7][11]==2||temp[6][10]==2||temp[9][14]==2)return 4;
		else if(temp[13][10]==2||temp[12][11]==2||temp[11][12]==2||temp[10][13]==2||temp[9][14]==2||temp[9][13]==2||temp[7][12]==2||temp[6][11]==2||temp[5][10]==2)return 5;
		else if(temp[13][11]==2||temp[12][12]==2||temp[11][13]==2||temp[10][14]==2||temp[9][15]==2||temp[9][14]==2||temp[7][13]==2||temp[6][12]==2||temp[5][14]==2)return 6;
		else if(temp[13][12]==2||temp[12][13]==2||temp[11][14]==2||temp[10][15]==2||temp[8][15]==2||temp[7][14]==2||temp[6][13]==2||temp[5][15]==2)return 7;
		else if(temp[13][13]==2||temp[12][14]==2||temp[11][15]==2||temp[7][15]==2||temp[6][14]==2||temp[5][13]==2)return 8;
		else if(temp[13][14]==2||temp[12][15]==2||temp[6][15]==2||temp[5][14]==2)return 9;
		else if(temp[13][15]==2||temp[5][15]==2)return 10;
		return 11;
	}
	
	static int nearestCreature(byte[][] e) {
		byte[][] temp = e;
		if(temp[9][10]==80)return 1;
		else if(temp[8][10]==80||temp[10][10]==80||temp[9][11]==80)return 2;
		else if(temp[11][10]==80||temp[10][11]==80||temp[9][12]==80||temp[8][11]==80||temp[7][10]==80)return 3;
		else if(temp[12][10]==80||temp[11][11]==80||temp[10][12]==80||temp[8][12]==80||temp[7][11]==80||temp[6][10]==80||temp[9][14]==80)return 4;
		else if(temp[13][10]==80||temp[12][11]==80||temp[11][12]==80||temp[10][13]==80||temp[9][14]==80||temp[9][13]==80||temp[7][12]==80||temp[6][11]==80||temp[5][10]==80)return 5;
		else if(temp[13][11]==80||temp[12][12]==80||temp[11][13]==80||temp[10][14]==80||temp[9][15]==80||temp[9][14]==80||temp[7][13]==80||temp[6][12]==80||temp[5][14]==80)return 6;
		else if(temp[13][12]==80||temp[12][13]==80||temp[11][14]==80||temp[10][15]==80||temp[8][15]==80||temp[7][14]==80||temp[6][13]==80||temp[5][15]==80)return 7;
		else if(temp[13][13]==80||temp[12][14]==80||temp[11][15]==80||temp[7][15]==80||temp[6][14]==80||temp[5][13]==80)return 8;
		else if(temp[13][14]==80||temp[12][15]==80||temp[6][15]==80||temp[5][14]==80)return 9;
		else if(temp[13][15]==80||temp[5][15]==80)return 10;
		return 11;
	}
	
	
	static void borrarUltimaLinea(String path){
		try {
 
        File inFile = new File(path);	
        
        if (!inFile.isFile()) {
            System.out.println("no hay archivo");
            return;
        }
 
        //Construct the new file that will later be renamed to the original filename.
        File tempFile = new File(inFile.getAbsolutePath() + ".tmp");
 
        BufferedReader br = new BufferedReader(new FileReader(path));
        PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
 
        String line = br.readLine();
        String next = br.readLine();
 
        //Read from the original file and write to the new
        //unless content matches data to be removed.
        while ((next) != null) {
        		
                pw.println(line);
                pw.flush();
                line = next;
                next = br.readLine();
                
        }
        
        pw.close();
        br.close();
 
        //Delete the original file
        if (!inFile.delete()) {
            System.out.println("Could not delete file");
            return;
        }
 
        //Rename the new file to the filename the original file had.
        if (!tempFile.renameTo(inFile)){
            System.out.println("Could not rename file");
 
        }
    } catch (FileNotFoundException ex) {
        ex.printStackTrace();
    } catch (IOException ex) {
        ex.printStackTrace();
    }
	}
	
	static void cabeceraWeka(FileWriter path, String id) {
		PrintWriter pw = new PrintWriter(path);
		pw.println("@relation " + id);
		pw.println();
		
		for (int i = 5; i < 10; i++) {
			for (int j =10; j < 13; j++) {
				pw.println("@attribute mergeObsZZ_"+i+"_"+j+" {-85,-24,-60,-62,0,2,3,25,80,93}");
			}
		}
		pw.println("@attribute intermediateReward numeric");
		pw.println("@attribute nearestCoin numeric");
		pw.println("@attribute nearestCreature numeric");
		pw.println("@attribute distancePassedCells numeric");
		pw.println("@attribute saltoSeguido numeric");
		pw.println("@attribute marioOnGround {true,false}");
		pw.println("@attribute reward6 numeric");
		pw.println("@attribute reward12 numeric");
		pw.println("@attribute reward24 numeric");
		pw.println("@attribute distance6 numeric");
		pw.println("@attribute distance12 numeric");
		pw.println("@attribute distance24 numeric");
		pw.println("@attribute mode6 numeric");
		pw.println("@attribute mode12 numeric");
		pw.println("@attribute mode24 numeric");
		pw.println("@attribute marioMode numeric");
		pw.println("@attribute evaluacion numeric");
		pw.println("@attribute DOWN {true,false}");
		pw.println("@attribute JUMP {true,false}");
		pw.println("@attribute LEFT {true,false}");
		pw.println("@attribute RIGHT {true,false}");
		pw.println("@attribute SPEED {true,false}");
		pw.println("@attribute UP {true,false}");
		
		pw.println();
		pw.println("@data");
		
	}

}
	
	
