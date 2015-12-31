package extractor;

import java.util.ArrayList;

import extractor.Extractor.Arc;

public class Extractor {

	protected class Arc {
		String A;
		String B;
		
		@Override
		public String toString() {
			return A+" -> "+B;
		}
	}
	
	public void addArc(String A, String B) {
		Arc arc = new Arc();
		arc.A = A;
		arc.B = B;
		
		for (Arc a : arcs) {
			if (a.A.equals(A) && a.B.equals(B)) {
				return;
			}
		}
		
		System.out.println("Arc "+arc);
		arcs.add(arc);
	}
	
	public void arrNoeud(String n) {
		this.noeuds.add(n);
		System.out.println("Noeud "+n);
	}
	
	public ArrayList<String> noeuds = new ArrayList<>();
	public ArrayList<Arc> arcs = new ArrayList<>();
	
}
