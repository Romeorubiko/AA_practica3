package ch.idsia.agents.controllers;

public class Instancia {
	int nearestCreature;
	int nearestCoin;
	int saltoSeguido;
	boolean marioOnGorund;
	int merge9_10, merge9_11, merge9_12;
	int merge8_10, merge8_11, merge8_12;
	int merge7_10, merge7_11, merge7_12;
	int merge6_10, merge6_11, merge6_12;
	int merge5_10, merge5_11, merge5_12;
	int reward, reward6, reward12, reward24;
	int distance, distance6, distance12, distance24;
	int evaluacion;
	int marioMode, mode6, mode12,mode24;
	boolean down, jump, left, right, speed, up;

	public Instancia() {
		
	}

	@Override
	public String toString() {
		return "Instancia [nearestCreature=" + nearestCreature + ", nearestCoin=" + nearestCoin + ", saltoSeguido="
				+ saltoSeguido + ", marioOnGorund=" + marioOnGorund + ", merge9_10=" + merge9_10 + ", merge9_11="
				+ merge9_11 + ", merge9_12=" + merge9_12 + ", merge8_10=" + merge8_10 + ", merge8_11=" + merge8_11
				+ ", merge8_12=" + merge8_12 + ", merge7_10=" + merge7_10 + ", merge7_11=" + merge7_11 + ", merge7_12="
				+ merge7_12 + ", merge6_10=" + merge6_10 + ", merge6_11=" + merge6_11 + ", merge6_12=" + merge6_12
				+ ", merge5_10=" + merge5_10 + ", merge5_11=" + merge5_11 + ", merge5_12=" + merge5_12 + ", reward="
				+ reward + ", reward6=" + reward6 + ", reward12=" + reward12 + ", reward24=" + reward24 + ", distance="
				+ distance + ", distance6=" + distance6 + ", distance12=" + distance12 + ", distance24=" + distance24
				+ ", evaluacion=" + evaluacion + ", marioMode=" + marioMode + ", mode6=" + mode6 + ", mode12=" + mode12
				+ ", mode24=" + mode24 + ", down=" + down + ", jump=" + jump + ", left=" + left + ", right=" + right
				+ ", speed=" + speed + ", up=" + up + "]";
	}
	
}
