package ch.idsia.agents.controllers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class Funcion {
	//Instancia situaciones[];
	LinkedList<Instancia> pertenece[];
	String path;
	
	public Funcion() {
		//initSituaciones();
		pertenece = new LinkedList[4];
		for (int i = 0; i < pertenece.length; i++) {
			pertenece[i] = new LinkedList<Instancia>();
		}
		
	}
	
	/**
	 * devuelve la posicion donde se encuenta la situcacion mas parecida y ademas la guarda en la estructura de datos
	 */
	public int pertenecia(Instancia ins) {//todo
	//A = Monstruo cerca; B = moneda cerca; C= Hay un bstaculo que saltar; D = No hay nada;
		float SituacionA;
		float SituacionB;
		float SituacionC;
		float SituacionD;
	//Pesos asignados a cada atributo
		float P1 = 35;
		float P2 = 35;
		float P3 = (float) 3.33;
		float P4 = (float) 1.11;

	//Calculo de la situaciÃ³n A	
		float nearestCreature = 0;
		float nearestCoin = 0;
		float merge3 = 0;
		float mergeResto = 0;
		nearestCreature = (Math.abs(ins.nearestCreature-3) * P1);
		nearestCoin = (Math.abs(ins.nearestCoin-11) * P2);
		if(ins.merge9_10 != 80) merge3 += 1;
		if(ins.merge9_11 != 80) merge3 += 1;
		if(ins.merge9_12 != 80) merge3 += 1;
		if(ins.merge8_10 != 80) merge3 += 1;
		if(ins.merge8_11 != 80) merge3 += 1;
		if(ins.merge7_10 != 80) merge3 += 1;
		merge3 *= P3;
		if(ins.merge5_10 != 0) mergeResto += 1;
		if(ins.merge5_11 != 0) mergeResto += 1;
		if(ins.merge5_12 != 0) mergeResto += 1;
		if(ins.merge6_10 != 0) mergeResto += 1;
		if(ins.merge6_11 != 0) mergeResto += 1;
		if(ins.merge6_12 != 0) mergeResto += 1;	
		if(ins.merge7_11 != 0) mergeResto += 1;
		if(ins.merge7_12 != 0) mergeResto += 1;
		if(ins.merge8_12 != 0) mergeResto += 1;
		mergeResto *= P4;

		SituacionA = nearestCreature + nearestCoin + merge3 + mergeResto;

	//Calculo de la situaciÃ³n B	

		nearestCreature = 0;
		nearestCoin = 0;
		merge3 = 0;
		mergeResto = 0;
		nearestCreature = Math.abs(ins.nearestCreature-11) * P1;
		nearestCoin = Math.abs(ins.nearestCoin-3) * P2;
		if(ins.merge9_10 != 80) merge3 += 1;
		if(ins.merge9_11 != 80) merge3 += 1;
		if(ins.merge9_12 != 80) merge3 += 1;
		if(ins.merge8_10 != 80) merge3 += 1;
		if(ins.merge8_11 != 80) merge3 += 1;
		if(ins.merge7_10 != 80) merge3 += 1;
		merge3 *= P3;
		if(ins.merge5_10 != 0) mergeResto += 1;
		if(ins.merge5_11 != 0) mergeResto += 1;
		if(ins.merge5_12 != 0) mergeResto += 1;
		if(ins.merge6_10 != 0) mergeResto += 1;
		if(ins.merge6_11 != 0) mergeResto += 1;
		if(ins.merge6_12 != 0) mergeResto += 1;	
		if(ins.merge7_11 != 0) mergeResto += 1;
		if(ins.merge7_12 != 0) mergeResto += 1;
		if(ins.merge8_12 != 0) mergeResto += 1;
		mergeResto *= P4;

		SituacionB = nearestCreature + nearestCoin + merge3 + mergeResto;

	//Calculo de la situaciÃ³n C	

		nearestCreature = 0;
		nearestCoin = 0;
		merge3 = 0;
		mergeResto = 0;
		nearestCreature = Math.abs(ins.nearestCreature-11) * P1;
		nearestCoin = Math.abs(ins.nearestCoin-11) * P2;
		if(ins.merge9_10 != -24 | ins.merge9_10 != -60 | ins.merge9_10 != -85) merge3 += 1;
		if(ins.merge9_11 != -24 | ins.merge9_10 != -60 | ins.merge9_10 != -85) merge3 += 1;
		if(ins.merge9_12 != -24 | ins.merge9_10 != -60 | ins.merge9_10 != -85) merge3 += 1;
		if(ins.merge8_10 != -24 | ins.merge9_10 != -60 | ins.merge9_10 != -85) merge3 += 1;
		if(ins.merge8_11 != -24 | ins.merge9_10 != -60 | ins.merge9_10 != -85) merge3 += 1;
		if(ins.merge7_10 != -24 | ins.merge9_10 != -60 | ins.merge9_10 != -85) merge3 += 1;
		merge3 *= P3;
		if(ins.merge5_10 != 0) mergeResto += 1;
		if(ins.merge5_11 != 0) mergeResto += 1;
		if(ins.merge5_12 != 0) mergeResto += 1;
		if(ins.merge6_10 != 0) mergeResto += 1;
		if(ins.merge6_11 != 0) mergeResto += 1;
		if(ins.merge6_12 != 0) mergeResto += 1;	
		if(ins.merge7_11 != 0) mergeResto += 1;
		if(ins.merge7_12 != 0) mergeResto += 1;
		if(ins.merge8_12 != 0) mergeResto += 1;
		mergeResto *= P4;

		SituacionC = nearestCreature + nearestCoin + merge3 + mergeResto;

	//Calculo de la situaciÃ³n D	

		nearestCreature = 0;
		nearestCoin = 0;
		merge3 = 0;
		mergeResto = 0;
		nearestCreature = Math.abs(ins.nearestCreature-11) * P1;
		nearestCoin = Math.abs(ins.nearestCoin-11) * P2;
		if(ins.merge9_10 != 0) merge3 += 1;
		if(ins.merge9_11 != 0) merge3 += 1;
		if(ins.merge9_12 != 0) merge3 += 1;
		if(ins.merge8_10 != 0) merge3 += 1;
		if(ins.merge8_11 != 0) merge3 += 1;
		if(ins.merge7_10 != 0) merge3 += 1;
		merge3 *= P3;
		if(ins.merge5_10 != 0) mergeResto += 1;
		if(ins.merge5_11 != 0) mergeResto += 1;
		if(ins.merge5_12 != 0) mergeResto += 1;
		if(ins.merge6_10 != 0) mergeResto += 1;
		if(ins.merge6_11 != 0) mergeResto += 1;
		if(ins.merge6_12 != 0) mergeResto += 1;	
		if(ins.merge7_11 != 0) mergeResto += 1;
		if(ins.merge7_12 != 0) mergeResto += 1;
		if(ins.merge8_12 != 0) mergeResto += 1;
		mergeResto *= P4;	

		SituacionD = nearestCreature + nearestCoin + merge3 + mergeResto;	

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
	 * devuelve la situacion qeu se parezca mï¿½s en la lista que se ha pasado como parï¿½metro
	 */
	public Instancia similitud(Instancia ins, LinkedList<Instancia> l) {
		Instancia[] list = l.toArray(new Instancia[l.size()]);

		//Array de ints que contendrÃ¡ los valores de similitud de cada instancia de la lista con la nueva instancia
		float valores[] = new float [list.length];
		//Variables donde vamos a guardar la posiciÃ³n en la lista de la instancia mÃ¡s parecÃ­a

	//Vamos a recorrer la lista comparando la instancia nueva con todas las demÃ¡s y guardando su similitud en el array
		for(int i=0; i<list.length;i++){
			valores[i] = comparar(ins,list[i]);
		}
	//Lo inicializamos con los 3 primeros
		float mejores[] = {valores[0], valores[1], valores[2]};
		Instancia posicion1 = list[0];
		Instancia posicion2 = list[1];
		Instancia posicion3 = list[2];
		int posicionPeorSimilitud;
	//Recorremos el array con los valores de similitud y vamos guardando los 3 mÃ¡s grandes
		for (int i=0; i<valores.length; i++){
			//PosiciÃ³n del peor de los mejores
			posicionPeorSimilitud = mayor(mejores);
			if(valores[i] < mejores[posicionPeorSimilitud]){
				mejores[posicionPeorSimilitud] = valores[i];
				switch(posicionPeorSimilitud){
					case 0: posicion1 = list[i];
							break;
					case 1: posicion2 = list[i];
							break;
					default: posicion3 = list[i];

				}
			}
		}

	/*Como las instancias de la lista ya tienen un valor de evaluacion, vamos a hacer la media 
	de la diferencia (ya que cuanta mÃ¡s evaluaciÃ³n mejor y cuanta menos similitud mejor) entre evaluaciÃ³n
	 y similitud */

		float total1 = (posicion1.evaluacion - mejores[0]) / 2;
		float total2 = (posicion2.evaluacion - mejores[1]) / 2;
		float total3 = (posicion3.evaluacion - mejores[2]) / 2;

		double random = Math.random();// generamos un numero al azar entre 0 y 1 

		if(total1 > total2 && total1 > total3){
			if(random < 0.7)// el 70% de las veces 
				return posicion1; 
			else if(random <0.9)// el 20% de las veces 
					return posicion2; 
				else// el 10% restante 
					return posicion3; 
		}
		if(total2 > total1 && total2 > total3){
			if(random < 0.7)// el 70% de las veces 
				return posicion2; 
			else if(random <0.9)// el 20% de las veces 
					return posicion1; 
				else// el 10% restante 
					return posicion3; 
		}
		else
			if(random < 0.7)// el 70% de las veces 
				return posicion3; 
			else if(random <0.9)// el 20% de las veces 
					return posicion2; 
				else// el 10% restante 
					return posicion1; 
		
		/*
		if((int)Math.random()*3 == 2) {
			return posicion1;
		}
		if((int)Math.random()*3 == 2) {
			return posicion2;
		}
		return posicion3;
		*/
	}

	//Funcion que devuelve la posición del mayor número
	public int mayor(float [] mejores){
        if(mejores[0] > mejores[1] && mejores[0] > mejores[2]){
        	return 0;
        }
        if(mejores[0] > mejores[1] && mejores[0] > mejores[2]){
        	return 1;
        }        
        else return 2;      
    }
//FunciÃ³n que devuelve la similitud entre la nueva instancia y una instancia de la lista

	public float comparar(Instancia ins, Instancia lista){

		float similitud;
		float nearestCreature = 0;
		float nearestCoin = 0;
		float marioOnGround = 0;
		float saltoSeguido;
		float merge3 = 0;
		float mergeResto = 0;
		float P1 = 25;
		float P2 = 25;
		double P3 = 3.33;	
		double P4 = 1.11;
		float P5 = 10;
		float P6 = 10;

		nearestCreature = (Math.abs(ins.nearestCreature-lista.nearestCreature) * P1);
		nearestCoin = (Math.abs(ins.nearestCoin-lista.nearestCoin) * P2);
		if(ins.marioOnGorund != lista.marioOnGorund) marioOnGround = 1 * P5;
		saltoSeguido = (Math.abs(ins.saltoSeguido-lista.saltoSeguido) * P6);
		if(ins.merge9_10 != lista.merge9_10) merge3 += 1;
		if(ins.merge9_11 != lista.merge9_11) merge3 += 1;
		if(ins.merge9_12 != lista.merge9_12) merge3 += 1;
		if(ins.merge8_10 != lista.merge8_10) merge3 += 1;
		if(ins.merge8_11 != lista.merge8_11) merge3 += 1;
		if(ins.merge7_10 != lista.merge7_10) merge3 += 1;
		merge3 *= P3;
		if(ins.merge5_10 != lista.merge5_10) mergeResto += 1;
		if(ins.merge5_11 != lista.merge5_11) mergeResto += 1;
		if(ins.merge5_12 != lista.merge5_12) mergeResto += 1;
		if(ins.merge6_10 != lista.merge6_10) mergeResto += 1;
		if(ins.merge6_11 != lista.merge6_11) mergeResto += 1;
		if(ins.merge6_12 != lista.merge6_12) mergeResto += 1;	
		if(ins.merge7_11 != lista.merge7_11) mergeResto += 1;
		if(ins.merge7_12 != lista.merge7_12) mergeResto += 1;
		if(ins.merge8_12 != lista.merge8_12) mergeResto += 1;
		mergeResto *= P4;

		similitud = nearestCreature + nearestCoin + merge3 + mergeResto + marioOnGround + saltoSeguido;
		return similitud;
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
						ins.merge5_10 = Integer.parseInt(valor);
						break;
					case 2:
						ins.merge5_11 = Integer.parseInt(valor);
						break;
					case 3:
						ins.merge5_12 = Integer.parseInt(valor);
						break;
					case 4:
						ins.merge6_10 = Integer.parseInt(valor);
						break;
					case 5:
						ins.merge6_11 = Integer.parseInt(valor);
						break;
					case 6:
						ins.merge6_12 = Integer.parseInt(valor);
						break;
					case 7:
						ins.merge7_10 = Integer.parseInt(valor);
						break;
					case 8:
						ins.merge7_11 = Integer.parseInt(valor);
						break;
					case 9:
						ins.merge7_12 = Integer.parseInt(valor);
						break;
					case 10:
						ins.merge8_10 = Integer.parseInt(valor);
						break;
					case 11:
						ins.merge8_11 = Integer.parseInt(valor);
						break;
					case 12:
						ins.merge8_12 = Integer.parseInt(valor);
						break;
					case 13:
						ins.merge9_10 = Integer.parseInt(valor);
						break;
					case 14:
						ins.merge9_11 = Integer.parseInt(valor);
						break;
					case 15:
						ins.merge9_12 = Integer.parseInt(valor);
						break;
					case 16:
						ins.reward = Integer.parseInt(valor);
						break;
					case 17:
						ins.nearestCoin = Integer.parseInt(valor);
						break;
					case 18:
						ins.nearestCreature = Integer.parseInt(valor);
						break;
					case 19:
						ins.distance = Integer.parseInt(valor);
						break;
					case 20:
						ins.saltoSeguido = Integer.parseInt(valor);
						break;
					case 21:
						ins.marioOnGorund = Boolean.parseBoolean(valor);
						break;
					case 22:
						ins.reward6 = Integer.parseInt(valor);
						break;
					case 23:
						ins.reward12 = Integer.parseInt(valor);
						break;
					case 24:
						ins.reward24 = Integer.parseInt(valor);
						break;
					case 25:
						ins.distance6 = Integer.parseInt(valor);
						break;
					case 26:
						ins.distance12 = Integer.parseInt(valor);
						break;
					case 27:
						ins.distance24 = Integer.parseInt(valor);
						break;
					case 28:
						ins.mode6 = Integer.parseInt(valor);
						break;
					case 29:
						ins.mode12 = Integer.parseInt(valor);
						break;
					case 30:
						ins.mode24 = Integer.parseInt(valor);
						break;
					case 31:
						ins.marioMode = Integer.parseInt(valor);
						break;
					case 32:
						ins.evaluacion = Integer.parseInt(valor);
						break;
					case 33:
						ins.down = Boolean.parseBoolean(valor);
						break;
					case 34:
						ins.jump = Boolean.parseBoolean(valor);
						break;
					case 35:
						ins.left = Boolean.parseBoolean(valor);
						break;
					case 36:
						ins.right = Boolean.parseBoolean(valor);
						break;
					case 37:
						ins.speed = Boolean.parseBoolean(valor);
						break;
					case 38:
						ins.up = Boolean.parseBoolean(valor);
						break;
					}
					valor = "";
				}
			}
			pertenece[pertenecia(ins)].add(ins);
			line = br.readLine();
			contador = 0;
		}
		System.out.println("�Indexado completado!");
		for (int i = 0; i < pertenece.length; i++) {
			System.out.println("Coincidencias con situacion "+i+": "+pertenece[i].size());
		}
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
