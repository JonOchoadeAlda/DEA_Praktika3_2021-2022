import java.util.LinkedList;

public class BZBHitzak implements HitzenInterfazea {

	private BZBAdabegiaHitzak erroa;

	public BZBHitzak() {
		this.erroa = null;
	}

	public boolean hutsaDa() {
		return (erroa == null);
	}

	/**
	 * Hitz bat gehitzen dio bilaketa-zuhaitz bitarrari
	 * 
	 * @param hitza: gehitzen den hitza
	 */
	public void hitzaGehitu(Hitza hitza) {
		if (this.hutsaDa()) {
			this.erroa = new BZBAdabegiaHitzak(hitza);
		} else {
			this.erroa.hitzaGehitu(hitza);
		}

	}

	/**
	 * Emandako stringa bilatzen du zuhaitzean eta dagokion hitza itzultzen du
	 * 
	 * @param s: bilatu nahi den hitzaren testua (stringa)
	 * @return s stringari dagokion hitza (zuhaitzean badago), null bestela
	 *         Aurrebaldintza: s stringa, agertzen bada, behin bakarrik agertuko da
	 */

	public Hitza hitzaBilatu(String s) {
		if (this.hutsaDa()) {
			return null;
		} else {
			return erroa.hitzaBilatu(s);
		}
	}

	/**
	 * Inongo web-orriren hitz gakoak ez diren zuhaitzeko hitzen lista bat itzultzen
	 * du
	 * 
	 * @return Ezabatu behar diren hitzen lista
	 */
	private LinkedList<Hitza> lortuEzabatzekoHitzak() {
		LinkedList<Hitza> ezabatzekoLista;
		if (this.hutsaDa()) {
			ezabatzekoLista = new LinkedList<Hitza>();
			return ezabatzekoLista;
		} else {
			return this.erroa.lortuEzabatzekoHitzak();
		}

	}

	/**
	 * Bilaketa-zuhaitz bitarretik hitza ezabatzen du. Bilaketa-zuhaitz bitarra
	 * izaten jarraitzen du.
	 * 
	 * @param hitza: ezabatuko den hitza Aurrebaldintza: hitza, agertzen bada, behin
	 *               bakarrik agertuko da
	 */
	private void ezabatuHitza(Hitza hitza) {
		if (!this.hutsaDa()) {
			this.erroa = this.erroa.ezabatuHitza(hitza);
		}

	}

	/**
	 * Aurreko metodoak erabiliz, ezabatu beharreko hitzen lista lortzen du, eta
	 * hitz horiek ezabatu egiten ditu..
	 */
	public void bahetuHitzGakoak() {
		LinkedList<Hitza> ezabatzeko = lortuEzabatzekoHitzak();
		for (Hitza h : ezabatzeko) {
			ezabatuHitza(h);
		}

	}

}
