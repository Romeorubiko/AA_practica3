package ch.idsia.agents.controllers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class Funcion {
	//Instancia situaciones[];
	LinkedList<Instancia> instancias;
	String path;
	
	public Funcion() {
		//initSituaciones();
		instancias = new LinkedList();
		
	}
	
	/**
	 * devuelve la posicion donde se encuenta la situcacion mas parecida y ademas la guarda en la estructura de datos
	 */
	public int pertenencia(Instancia ins, boolean futuro) {//todo
	//A = Monstruo cerca; B = moneda cerca; C= Hay un bstaculo que saltar; D = No hay nada;
		float SituacionA;
		float SituacionB;
		float SituacionC;
		float SituacionD;
		
	//Pesos asignados a cada atributo
		float P1 = 3;
		float P2 = 3;
		float P3 = 30;
		
		int creature = ins.nearestCreature;
		int coin = ins.nearestCoin;
		int merge = ins.merge9_10;
		
		if(futuro){
			 creature = ins.creature24;
			 coin = ins.coin24;
			 merge = ins.merge24OBS9_10;
		}

	//Calculo de la situaciÃ³n A	
		float nearestCreature = 0;
		float nearestCoin = 0;
		float merge3 = 0;

		
		nearestCreature = (Math.abs(creature-3) * P1);
		nearestCoin = (Math.abs(coin-11) * P2);
		if(merge != 80) merge3 += 1;
		merge3 *= P3;


		SituacionA = nearestCreature + nearestCoin + merge3;

	//Calculo de la situaciÃ³n B	

		nearestCreature = 0;
		nearestCoin = 0;
		merge3 = 0;
		nearestCreature = Math.abs(creature-11) * P1;
		nearestCoin = Math.abs(coin-3) * P2;
		if(merge != 2) merge3 += 1;
		merge3 *= P3;


		SituacionB = nearestCreature + nearestCoin + merge3;

	//Calculo de la situaciÃ³n C	

		nearestCreature = 0;
		nearestCoin = 0;
		merge3 = 0;
		nearestCreature = Math.abs(creature-11) * P1;
		nearestCoin = Math.abs(coin-11) * P2;
		if(merge != -24 | merge != -60 | merge != -85) merge3 += 1;
		merge3 *= P3;


		SituacionC = nearestCreature + nearestCoin + merge3;

	//Calculo de la situaciÃ³n D	

		nearestCreature = 0;
		nearestCoin = 0;
		merge3 = 1;
		nearestCreature = Math.abs(creature-11) * P1;
		nearestCoin = Math.abs(coin-11) * P2;
		if(merge == -85 || merge == -24) merge3 -= 1;
		merge3 *= P3;


		SituacionD = nearestCreature + nearestCoin + merge3;	

		//Una vez calculadas todas las situaciones, devolvemos la posiciÃ³n de la mayor
		
		if(SituacionA > SituacionB && SituacionA > SituacionC && SituacionA > SituacionD)
			return 0;
		if(SituacionB > SituacionC && SituacionB > SituacionA && SituacionB > SituacionD)
			return 1;
		if(SituacionC > SituacionB && SituacionC > SituacionA && SituacionC > SituacionD)
			return 2;
		else
			return 3;
	}
	
	/**
	 * devuelve el valor que ha salido del resultado de aplicar la fï¿½rmula
	 */
	static public int evaluacion(Instancia ins) {//todo
	//Pesos asignados a cada atributo	
		int P1 = 5;
		int P2 = 15;
		int P3 = 20;
		int P4 = 30;
		int P5 = 10;
		int P6 = 20;
		
		return (P1 * (ins.reward12-ins.reward) + P2 * (ins.reward24-ins.reward)) 
				+ (P3 * (ins.distance12-ins.distance) + P4 * (ins.distance24-ins.distance))
				+ (P5 * (ins.mode12 - ins.marioMode) + P6 * (ins.mode24 - ins.marioMode));

	}
	
	public void indexar(String path) throws IOException {
		// rellenar el pertenece que es la estructura donde vamos a indexar
		this.path = path;
		//abrimos el fichero para su lectura
		BufferedReader br = new BufferedReader(new FileReader(path));     
		if (br.readLine() == null) {
			br.close();
			System.out.println("Error al indexar, archivo vacio");
		}
		//Llegamos a la zona de datos, que es la que nos importa 
		String line = "";
		while(!line.contains("@data"))line = br.readLine();
		line = br.readLine(); 
		//leemos datos 
		line = br.readLine();
		int contador = 0;
		while(line != null) {
			Instancia ins = new Instancia();
			String valor = "";
			for (char c : line.toCharArray()) {
				if(c!=',')valor = valor + c;
				else {
					contador++;
					switch (contador) {//todo aï¿½adir la posicion de los parametros que nos interesan y guardarlo en ins
					case 1:
						ins.merge9_10 = Integer.parseInt(valor);
						break;
					case 2:
						ins.reward = Integer.parseInt(valor);
						break;
					case 3:
						ins.nearestCoin = Integer.parseInt(valor);
						break;
					case 4:
						ins.nearestCreature = Integer.parseInt(valor);
						break;
					case 5:
						ins.distance = Integer.parseInt(valor);
						break;
					case 6:
						ins.saltoSeguido = Integer.parseInt(valor);
						break;
					case 7:
						ins.marioOnGorund = Boolean.parseBoolean(valor);
						break;
					case 8:
						ins.evaluacion = Integer.parseInt(valor);
						break;
					case 9:
						ins.marioMode = Integer.parseInt(valor);
						break;
					case 10:
						ins.reward24 = Integer.parseInt(valor);
						break;
					case 11:
						ins.coin24 = Integer.parseInt(valor);
						break;
					case 12:
						ins.creature24 = Integer.parseInt(valor);
						break;
					case 13:
						ins.distance24 = Integer.parseInt(valor);
						break;
					case 14:
						ins.salto24 = Integer.parseInt(valor);
						break;
					case 15:
						ins.ground24 = Boolean.parseBoolean(valor);
						break;
					case 16:
						ins.mode24 = Integer.parseInt(valor);
						break;
					case 17:
						ins.merge24OBS9_10 = Integer.parseInt(valor);
						break;
					case 18:
						ins.down = Boolean.parseBoolean(valor);
						break;
					case 19:
						ins.jump = Boolean.parseBoolean(valor);
						break;
					case 20:
						ins.left = Boolean.parseBoolean(valor);
						break;
					case 21:
						ins.right = Boolean.parseBoolean(valor);
						break;
					case 22:
						ins.speed = Boolean.parseBoolean(valor);
						break;
					case 23:
						ins.up = Boolean.parseBoolean(valor);
						break;
					}
					valor = "";
				}
			}
			ins.situacion = pertenencia(ins,false);
			ins.situacion24 = pertenencia(ins,true);
			instancias.add(ins);
			line = br.readLine();
			contador = 0;
		}
		System.out.println("�Indexado completado!");
		System.out.println("___________________________________________________________");
		
	}
	
	/*private void initSituaciones() {
		
		situaciones =  new Instancia[4];

		//caso a
		Instancia temp = new Instancia();
		temp.nearestCreature = 3;
		temp.nearestCoin = 11;
		temp.merge9_10 = 80;
		temp.merge9_11 = 80;
		temp.merge9_12 = 80;
		temp.merge8_10 = 80;
		temp.merge8_11 = 80;
		temp.merge7_10 = 80;
		temp.merge5_10 = 0;
		temp.merge5_11 = 0;
		temp.merge5_12 = 0;
		temp.merge6_10 = 0;
		temp.merge6_11 = 0;
		temp.merge6_12 = 0;
		temp.merge7_11 = 0;
		temp.merge7_12 = 0;
		temp.merge8_12 = 0;
		situaciones[0] = temp;
		
		//caso b
		temp = new Instancia();
		temp.nearestCreature = 11;
		temp.nearestCoin = 3;
		temp.merge9_10 = 80;
		temp.merge9_11 = 80;
		temp.merge9_12 = 80;
		temp.merge8_10 = 80;
		temp.merge8_11 = 80;
		temp.merge7_10 = 80;
		temp.merge5_10 = 0;
		temp.merge5_11 = 0;
		temp.merge5_12 = 0;
		temp.merge6_10 = 0;
		temp.merge6_11 = 0;
		temp.merge6_12 = 0;
		temp.merge7_11 = 0;
		temp.merge7_12 = 0;
		temp.merge8_12 = 0;
		situaciones[1] = temp;

		//caso c
		temp = new Instancia();
		temp.nearestCreature = 11;
		temp.nearestCoin = 11;
		temp.merge9_10 = 80;
		temp.merge9_11 = 80;
		temp.merge9_12 = 80;
		temp.merge8_10 = 80;
		temp.merge8_11 = 80;
		temp.merge7_10 = 80;
		temp.merge5_10 = 0;
		temp.merge5_11 = 0;
		temp.merge5_12 = 0;
		temp.merge6_10 = 0;
		temp.merge6_11 = 0;
		temp.merge6_12 = 0;
		temp.merge7_11 = 0;
		temp.merge7_12 = 0;
		temp.merge8_12 = 0;
		situaciones[2] = temp;

		//caso d
		temp = new Instancia();
		temp.nearestCreature = 11;
		temp.nearestCoin = 11;
		temp.merge9_10 = -60;
		temp.merge9_11 = -60;
		temp.merge9_12 = -60;
		temp.merge8_10 = -60;
		temp.merge8_11 = -60;
		temp.merge7_10 = -60;
		temp.merge5_10 = 0;
		temp.merge5_11 = 0;
		temp.merge5_12 = 0;
		temp.merge6_10 = 0;
		temp.merge6_11 = 0;
		temp.merge6_12 = 0;
		temp.merge7_11 = 0;
		temp.merge7_12 = 0;
		temp.merge8_12 = 0;
		situaciones[3] = temp;
		
		
	}*/
	
	
}
