package extractor;

import java.util.ArrayList;
import java.util.HashSet;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@SuppressWarnings("unchecked")
public abstract class Extractor {

	protected class Arc {
		String A;
		String B;
		int poids = 1;

		@Override
		public String toString() {
			return A + " -> " + B;
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

		System.out.println("Arc " + arc);
		arcs.add(arc);
	}

	public void addNoeud(String n) {
		this.noeuds.add(n);
		System.out.println("Noeud " + n);
	}

	public HashSet<String> noeuds = new HashSet<>();
	public ArrayList<Arc> arcs = new ArrayList<>();

	private JSONArray getNodesJson() {
		JSONArray mJSONArray = new JSONArray();
		for (String c : noeuds) {
			JSONObject jObjd = new JSONObject();
			jObjd.put("id", c);
			jObjd.put("value", 0);
			jObjd.put("label", c);
			mJSONArray.add(jObjd);
		}
		return mJSONArray;
	}

	private JSONArray getArcsJson() {
		JSONArray mJSONArray = new JSONArray();
		for (Arc c : arcs) {
			JSONObject jObjd = new JSONObject();
			jObjd.put("from", c.A);
			jObjd.put("to", c.B);
			jObjd.put("value", c.poids);
			jObjd.put("title", "");
			mJSONArray.add(jObjd);
		}
		return mJSONArray;
	}

	public String toJson() {
		JSONObject jObjd = new JSONObject();
		jObjd.put("nodes", getNodesJson());
		jObjd.put("edges", getArcsJson());
		return jObjd.toJSONString();
	}

}
