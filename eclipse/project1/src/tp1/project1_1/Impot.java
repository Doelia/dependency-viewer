package tp1.project1_1;

public class Impot {
	
	private double calculerNombreParts(Imposable imps) {
		switch (imps.statut) {
			case 0: // Celibataire
				return (1+ imps.nombreEnfants);
			case 1: // Marié
				return (1+ imps.nombreEnfants + 1);
			case 2:
				return Math.pow((1 - imps.nombreEnfants), 2) * .9;
			default:
				return 0;
		}
	}
	
	Tranche identifierTranche(Imposable imp) {
		if (imp.revenu.getMontant() > 10) {
			return new Tranche(1);
		}
		else {
			return new Tranche(2);
		}
	}
	
	double calculerFraisReels(Imposable imp) {
		// TODO
		return (imp.getFraisDeclares()* 0.8); 
	}
	
	double calculerImpotEntreprise() {
		return 0;
	}
	
	double calculerImpot(Imposable imps) {
		Revenu rev= imps.revenu; 
		
		double nombreParts = calculerNombreParts(imps); 
		Tranche tran = identifierTranche(imps);
		double frais = calculerFraisReels(imps);
		
		double MontantRevenu = rev.getMontant() - frais; 
		
		double impot = tran.calculerImpot(MontantRevenu, nombreParts);
		
		return  impot;
	}

}
