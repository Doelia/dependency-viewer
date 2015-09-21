package project1;

public class Tranche {
	
	public int identifiant; 
	
	double calculerImpot(double montantRevenu, double nombreParts) {
		return montantRevenu/nombreParts;
	}
}
