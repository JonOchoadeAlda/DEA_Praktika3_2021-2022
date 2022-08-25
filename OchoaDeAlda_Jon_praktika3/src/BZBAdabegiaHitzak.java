import java.util.ArrayList;
import java.util.LinkedList;

public class BZBAdabegiaHitzak {

	Hitza info;
	BZBAdabegiaHitzak ezkerra;
	BZBAdabegiaHitzak eskuina;

	public BZBAdabegiaHitzak(Hitza h) {
		this.info = h;

	}

	public boolean baduEzkerra() {
		return this.ezkerra != null;
	}

	public boolean baduEskuina() {
		return this.eskuina != null;
	}

	/**
	 * Hitz bat gehitzen dio bilaketa-zuhaitz bitarrari
	 * 
	 * @param hitza: gehitzen den hitza
	 */
	public void hitzaGehitu(Hitza hitza) {
		if (this.info.getTestua().compareTo(hitza.getTestua()) > 0) {
			if (this.baduEzkerra()) {
				this.ezkerra.hitzaGehitu(hitza);
			} else {
				this.ezkerra = new BZBAdabegiaHitzak(hitza);
			}

		} else {
			if (this.baduEskuina()) {
				this.eskuina.hitzaGehitu(hitza);
			} else {
				this.eskuina = new BZBAdabegiaHitzak(hitza);
			}
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

		if (this.info.getTestua().equals(s)) {
			return this.info;
		} else {
			if (this.info.getTestua().compareTo(s) > 0) {
				if (this.baduEzkerra()) {
					return this.ezkerra.hitzaBilatu(s);
				} else {
					return null;
				}
			} else {
				if (this.baduEskuina()) {
					return this.eskuina.hitzaBilatu(s);
				} else {
					return null;
				}
			}
		}
	}

	/**
	 * Inongo web-orriren hitz gakoak ez diren zuhaitzeko hitzen lista bat itzultzen
	 * du
	 * 
	 * @return Ezabatu behar diren hitzen lista
	 */
	public LinkedList<Hitza> lortuEzabatzekoHitzak() {
		LinkedList<Hitza> ezabatzekoLista = new LinkedList<Hitza>();
		LinkedList<Hitza> ezabatzekoEzkerra;
		LinkedList<Hitza> ezabatzekoEskuina;

		if (baduEzkerra()) {
			ezabatzekoEzkerra = this.ezkerra.lortuEzabatzekoHitzak();
		} else {
			ezabatzekoEzkerra = new LinkedList<Hitza>();
		}

		if (baduEskuina()) {
			ezabatzekoEskuina = this.eskuina.lortuEzabatzekoHitzak();
		} else {
			ezabatzekoEskuina = new LinkedList<Hitza>();
		}
		ArrayList<Web> webak = this.info.getLista().getLista();

		if (webak.isEmpty())
			ezabatzekoLista.addFirst(this.info);

		ezabatzekoLista.addAll(ezabatzekoEzkerra);
		ezabatzekoLista.addAll(ezabatzekoEskuina);

		return ezabatzekoLista;
	}

	/**
	 * Bilaketa-zuhaitz bitarretik hitza ezabatzen du. Bilaketa-zuhaitz bitarra
	 * izaten jarraitzen du.
	 * 
	 * @param hitza: ezabatuko den hitza Aurrebaldintza: hitza, agertzen bada, behin
	 *               bakarrik agertuko da
	 */
	public BZBAdabegiaHitzak ezabatuHitza(Hitza hitza) {

		if (this.info.getTestua().equals(hitza.getTestua())) {
			if (!this.baduEzkerra()) {
				return this.eskuina;
			} else if (!this.baduEskuina()) {
				return this.ezkerra;
			} else {
				EzabatuMinEmaitza min = this.eskuina.ezabatuMin();
				this.eskuina = min.adabegia;
				this.info = min.balioa;
				return this;
			}

		} else {

			if (this.info.getTestua().compareTo(hitza.getTestua()) > 0) {// (b): ezabatu beharreko elementua ezkerreko
																			// azpizuhaitzean egongo da, baldin badago
				if (this.baduEzkerra()) {
					this.ezkerra = this.ezkerra.ezabatuHitza(hitza);

				}
				return this;

			} else {// this.info < elem: (c) ezabatu beharreko elementua eskuineko azpizuhaitzean
					// egongo da, baldin badago
				if (this.baduEskuina()) {
					this.eskuina = this.eskuina.ezabatuHitza(hitza);

				}
				return this;

			}
		}
	}

	public EzabatuMinEmaitza ezabatuMin() {
		EzabatuMinEmaitza emaitza = new EzabatuMinEmaitza();
		if (!this.baduEzkerra()) {// b) txikiena unekoa da
			emaitza.balioa = this.info;
			emaitza.adabegia = this.eskuina;
		} else { // a) txikiena ezkerreko azpizuhaitzean dago
			EzabatuMinEmaitza emaitzaEzkerra = this.ezkerra.ezabatuMin();
			this.ezkerra = emaitzaEzkerra.adabegia;
			emaitza.balioa = emaitzaEzkerra.balioa;
			emaitza.adabegia = this;
		}
		return emaitza;
	}

	public boolean hostoaDa() {
		if (!(this.baduEskuina() && this.baduEzkerra())) {
			return true;
		} else {
			return false;
		}
	}

	public Hitza getInfo() {
		return info;
	}

	public void setInfo(Hitza info) {
		this.info = info;
	}

	public BZBAdabegiaHitzak getEzkerra() {
		return ezkerra;
	}

	public void setEzkerra(BZBAdabegiaHitzak ezkerra) {
		this.ezkerra = ezkerra;
	}

	public BZBAdabegiaHitzak getEskuina() {
		return eskuina;
	}

	public void setEskuina(BZBAdabegiaHitzak eskuina) {
		this.eskuina = eskuina;
	}

	public class EzabatuMinEmaitza {
		Hitza balioa;
		BZBAdabegiaHitzak adabegia;

		public Hitza getBalioa() {
			return balioa;
		}

		public void setBalioa(Hitza balioa) {
			this.balioa = balioa;
		}

		public BZBAdabegiaHitzak getAdabegia() {
			return adabegia;
		}

		public void setAdabegia(BZBAdabegiaHitzak adabegia) {
			this.adabegia = adabegia;
		}

	}

}
