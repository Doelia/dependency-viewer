package tp1.project1_1;

public class PrincipalImpot {
	
	public static void main(String[] params) {
		Revenu revenu1 = new Revenu(10, 0, 5); 
		Imposable impos1 = new Imposable("Dupond", "Bernard", 345, 1, null, 3, revenu1);
		Imposable impos2 = new Imposable("Dupond", "Jean", 346, 2, null, 1, revenu1);
		
		{
			impos1.setFrais(4); 
			Impot imp = new Impot();
			double montantImpot = imp.calculerImpot(impos1);
			impos1.enregistrerImpot(montantImpot);
			System.out.println(montantImpot); 
		}
		
		{
			impos2.setFrais(4); 
			Impot imp = new Impot();
			double montantImpot = imp.calculerImpot(impos2);
			impos2.enregistrerImpot(montantImpot);
			System.out.println(montantImpot); 
		}
		
		(new FeuilleImpot(2)).editer(5);
	}

}


