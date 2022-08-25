import java.util.ArrayList;

public class HitzenLista implements HitzenInterfazea {

	ArrayList<Hitza> lista;

	HitzenLista() {
		lista = new ArrayList<Hitza>();
	}

	/**
	 * Hitz bat gehitzen dio listari
	 * 
	 * @param hitza: gehitzen den hitza
	 */
	public void hitzaGehitu(Hitza hitza) {
		lista.add(hitza);
	}

	/**
	 * Emandako stringa bilatzen du hitzen listan eta dagokion hitza itzultzen du
	 * 
	 * @param s: bilatu nahi den hitzaren testua (stringa)
	 * @return s stringari dagokion hitza (listan badago), null bestela
	 */
	public Hitza hitzaBilatu(String s) {

		int hi = this.lista.size() - 1;
		int lo = 0;
		int m;
		while (lo <= hi) {
			m = (lo + hi) / 2;
			if (lista.get(m).getTestua().compareToIgnoreCase(s) == 0) {
				return lista.get(m);

			}
			if (lista.get(m).getTestua().compareToIgnoreCase(s) < 0) {
				lo = m + 1;
			} else {
				hi = m - 1;
			}
		}

		return null;

	}

	public ArrayList<Hitza> getLista() {
		return lista;
	}

	public void setLista(ArrayList<Hitza> lista) {
		this.lista = lista;
	}

}
