import java.util.ArrayList;


public class WebenLista {

	ArrayList<Web> lista;

	public WebenLista() {
		this.lista = new ArrayList<Web>();
	}

	/**
	 * Web bat gehitzen dio listari
	 * 
	 * @param web: gehitzen den weba AURRE: web ez dago listan
	 */
	public void webaErantsi(Web web) {
		this.lista.add(web);

	}

	/**
	 * Listako web bati esteka bat eransten dio
	 * 
	 * @param idJatorriWeba: Jatorriko webaren id-a
	 * @param idHelburuWeba: Helburuko webaren id-a AURRE: lista id-en arabera
	 *                       ordenatuta dago AURRE: idJatorriWeba eta idHelburuWeba
	 *                       id-a duten webak listan daude
	 */
	public void estekaErantsi(int idJatorriWeba, int idHelburuWeba) {
		Web jatorriWeba = null;
		Web helburuWeba = null;

		int hi = this.lista.size() - 1;
		int lo = 0;
		int m;
		while (lo <= hi) {
			m = (lo + hi) / 2;
			if (lista.get(m).getKodea() == idJatorriWeba) {
				jatorriWeba = lista.get(m);

			}
			if (lista.get(m).getKodea() < idJatorriWeba) {
				lo = m + 1;
			} else {
				hi = m - 1;
			}
		}

		hi = this.lista.size() - 1;
		lo = 0;

		while (lo <= hi) {
			m = (lo + hi) / 2;
			if (lista.get(m).getKodea() == idHelburuWeba) {
				helburuWeba = lista.get(m);

			}
			if (lista.get(m).getKodea() < idHelburuWeba) {
				lo = m + 1;
			} else {
				hi = m - 1;
			}
		}

		jatorriWeba.getEstekak().webaErantsi(helburuWeba);

	}

	/**
	 * URL bat emanda, listan URL hori duen weba itzultzen du
	 * 
	 * @param url: bilatzen den URLa
	 * @return: URL hori duen web-a (listan badago), bestela null.
	 */
	public Web bilatuWebakUrlBidez(String url) {
		ArrayList<Web> lista = this.lista;

		for (Web w : lista) {
			if (w.getUrl().equals(url)) {
				return w;
			}
		}
		return null;
	}

	public ArrayList<Web> getLista() {
		return lista;
	}

	public void setLista(ArrayList<Web> lista) {
		this.lista = lista;
	}

}
