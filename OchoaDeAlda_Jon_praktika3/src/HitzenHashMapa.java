import java.util.HashMap;

public class HitzenHashMapa implements HitzenInterfazea {

	HashMap<String, Hitza> hitzenHasMapa;

	HitzenHashMapa() {
		hitzenHasMapa = new HashMap<String, Hitza>();
	}

	@Override
	public void hitzaGehitu(Hitza hitza) {
		hitzenHasMapa.put(hitza.getTestua(), hitza);

	}

	@Override
	public Hitza hitzaBilatu(String s) {
		return hitzenHasMapa.get(s);

	}

}
