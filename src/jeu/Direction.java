package jeu;

public enum Direction {

	HG, HD, D, G, BG, BD;
	
	public static Direction inverse(Direction d){
		switch (d) {
		case HG: 	return BD;
		case HD: 	return BG;
		case G: 	return D;
		case D: 	return G;
		case BG: 	return HD;
		case BD: 	return HG;
		default:	return null;
		}
		
	}
}
