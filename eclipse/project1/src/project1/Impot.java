package project1;

public class Impot {
	
	private double calculerNombreParts(Imposable imps) {
		if (imps.statut == 1) {
			return (1 + imps.nombreEnfants + 1);
		}
		else {
			return (1 + imps.nombreEnfants);	
		}
	}
	
	Tranche identifierTranche(Imposable imp) {
		// TODO
		return new Tranche();
	}
	
	double calculerFraisReels(Imposable imp) {
		// TODO
		return 0; 
	}
	
	double calculerImpot(Imposable imps) {
		Revenu rev = imps.revenu; 
		
		double nombreParts = calculerNombreParts(imps); 
		Tranche tran = identifierTranche(imps);
		double frais = calculerFraisReels(imps);
		double MontantRevenu = rev.montant - frais; 
		double impot = tran.calculerImpot(MontantRevenu, nombreParts);
		
		return  impot;
	}

}
