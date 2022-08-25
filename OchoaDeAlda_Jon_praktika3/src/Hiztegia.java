import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Hiztegia {

	private static Hiztegia instance;
	private HitzenInterfazea nireHiztegia;

	public Hiztegia() {
		nireHiztegia = new HitzenLista();
	}

	/**
	 * Hiztegia hasieratzen du parametro gisa pasatzen zaion hiztegiarekin
	 * 
	 * @param hiztegia
	 */
	public void setHiztegia(HitzenInterfazea hiztegia) {
		this.nireHiztegia = hiztegia;
	}
	
	
	

	public HitzenInterfazea getNireHiztegia() {
		return nireHiztegia;
	}

	public static Hiztegia getInstance() {
		if (instance == null) {
			instance = new Hiztegia();
		}
		return instance;
	}

	/**
	 * Hiztegia kargatzen du emandako fitxategitik
	 * 
	 * @param fitxIzena: hiztegia daukan fitxategiaren izena
	 */
	private void hitzakKargatu(String fitxIzena) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(fitxIzena));
			String line;
			while ((line = br.readLine()) != null) {
				Hitza hitzBerria = new Hitza(line);
				nireHiztegia.hitzaGehitu(hitzBerria);
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Hiztegiko hitz bakoitzari erreferentziatzen dituen web-orriak esleitzen
	 * zaizkio Aurre: Internet eta hiztegia kargatuta daude jadanik
	 */
	private void hitzenWebakKonputatu() {
		WebenLista internetLista = Internet.getInstance().getNireInternet();

		for (Web w : internetLista.getLista()) {
			String url = w.getUrl();
			for (int i = 0; i < url.length() && url.length() >= 4; i++) {
				for (int j = i; j <= url.length() && j - i < 11; j++) {

					if (j - i >= 4) {
						String substring = url.substring(i, j);

						Hitza h = Hiztegia.getInstance().nireHiztegia.hitzaBilatu(substring);

						if (h != null) {

							h.getLista().webaErantsi(w);
						}

					}
				}

			}
		}

	}

	/**
	 * Hiztegia kargatzen du emandako fitxategitik, eta hitz bakoitzaren webak
	 * konputatzen ditu (hitz bakoitzari erreferentziatzen dituen web-orriak
	 * esleitzen zaizkio)
	 * 
	 * @param fitxIzena: hiztegia daukan fitxategiaren izena AURRE: Internet eta
	 *                   hiztegia kargatuta daude dagoeneko
	 */
	public void hasieratu(String fitxIzena) {
		Hiztegia.getInstance().hitzakKargatu(fitxIzena);
		Hiztegia.getInstance().hitzenWebakKonputatu();
	}

	/**
	 * Emandako stringa bilatzen du hiztegian eta dagokion hitza itzultzen du
	 * 
	 * @param s: bilatu nahi den hitzaren testua (stringa)
	 * @return s stringari dagokion hitza (hiztegian badago), null bestela
	 */
	public Hitza hitzaBilatu(String s) {
		return Hiztegia.getInstance().nireHiztegia.hitzaBilatu(s);

	}

}
