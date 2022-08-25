import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Internet {

	private static Internet instance;
	private WebenLista nireInternet;

	private Internet() {
		nireInternet = new WebenLista();
	}

	public static Internet getInstance() {
		if (instance == null) {
			instance = new Internet();
		}
		return instance;
	}

	/**
	 * Pasatako fitxategian dauden webak kargatzen ditu
	 * 
	 * @param fitxIzena: webak dauzkan fitxategiaren izena
	 */
	private void webakKargatu(String fitxIzena) {

		try {
			BufferedReader br = new BufferedReader(new FileReader(fitxIzena));
			String line;
			while ((line = br.readLine()) != null) {
				String[] arrayPuntuak = line.trim().split("\\s+");
				Web weba = new Web(Integer.parseInt((arrayPuntuak[1])), arrayPuntuak[0]);
				nireInternet.webaErantsi(weba);

			}
			br.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();

		}

	}

	/**
	 * Pasatako fitxategian dauden estekak kargatzen ditu
	 * 
	 * @param fitxIzena: estekak dauzkan fitxategiaren izena
	 */
	private void estekakKargatu(String fitxIzena) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(fitxIzena));
			String line;
			while ((line = br.readLine()) != null) {
				String[] arrayPuntuak = line.trim().split("\\s+");
				this.nireInternet.estekaErantsi(Integer.parseInt(arrayPuntuak[0]), Integer.parseInt(arrayPuntuak[1]));

			}
			br.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();

		}

	}

	/**
	 * Klasea hasieratzen du: horretarako web-orriak eta estekak kargatzen ditu
	 * 
	 * @param webenFitxIzena:   webak dauzkan fitxategiaren izena
	 * @param estekenFitxIzena: estekak dauzkan fitxategiaren izena
	 */
	public void hasieratu(String webenFitxIzena, String estekenFitxIzena) {
		Internet.getInstance().webakKargatu(webenFitxIzena);
		Internet.getInstance().estekakKargatu(estekenFitxIzena);

	}

	/**
	 * Hitz bati dagokion stringa emanda, pantailan inprimatzen ditu gako-hitz hori
	 * duten webak
	 * 
	 * @param s: hitzari dagokion stringa
	 */
	public void webBilatzailea(String s) {
		Hiztegia hiztegia = Hiztegia.getInstance();
		Hitza h = hiztegia.hitzaBilatu(s);
		int n = 1;
		if (h != null) {
			for (Web w : h.getLista().getLista()) {
				System.out.println(n + "- " + w.getUrl());
				n++;
			}
		} else {
			System.out.println("HITZ HORI EZ DAGO HIZTEGIAN");
		}
	}

	/**
	 * Bi URL emanda, jatorri-URLtik helburu-URLra esteken bide bat badagoen
	 * adierazten du.
	 * 
	 * @param url1: Jatorri-URLa
	 * @param url2: Helburu-URLa
	 * @return: true bide bat badago, false bestela
	 */
	public boolean konektatutaDaude(String url1, String url2) {
		Web jatorria = nireInternet.bilatuWebakUrlBidez(url1);

		HashSet<Web> bisitatuak = new HashSet<Web>();
		bisitatuak.add(jatorria);

		Queue<Web> ilara = new LinkedList<Web>();
		ilara.add(jatorria);

		while (!ilara.isEmpty()) {
			Web weba = ilara.remove();

			if (weba.getUrl().equals(url2)) {
				return true;
			} else {
				for (Web w : weba.getEstekak().getLista()) {
					if (!bisitatuak.contains(w)) {
						ilara.add(w);
						bisitatuak.add(w);
					}
				}
			}
		}

		return false;
	}

	/**
	 * Bi URL emanda, jatorri-URLtik helburu-URLra dagoen biderik motzena (esteka
	 * gutxien duena) inprimatzen du, bide hori existitzen bada.
	 * 
	 * @param url1: Jatorri-URLa
	 * @param url2: Helburu-URLa
	 */
	public void bideaInprimatu(String url1, String url2) {

		LinkedList<String> bidea = new LinkedList<String>();
		Web jatorria = nireInternet.bilatuWebakUrlBidez(url1);
		Web helburua = nireInternet.bilatuWebakUrlBidez(url2);

		Map<Web, Web> bisitatuak = new HashMap<Web, Web>();

		bisitatuak.put(jatorria, null);

		Queue<Web> ilara = new LinkedList<Web>();
		ilara.add(jatorria);

		boolean aurkitua = false;

		while (!ilara.isEmpty() && !aurkitua) {
			Web weba = ilara.remove();
			if (weba.getUrl().equals(url2))
				aurkitua = true;
			else {
				for (Web w : weba.getEstekak().getLista()) {
					if (!bisitatuak.containsKey(w)) {
						ilara.add(w);
						bisitatuak.put(w, weba);
					}
				}
			}
		}

		if (aurkitua) {

			Web unekoa = helburua;
			while (unekoa != null) {
				bidea.addFirst(unekoa.getUrl());
				unekoa = bisitatuak.get(unekoa);
			}

			for (String url : bidea) {
				System.out.println(url);

			}
		}

	}

	public WebenLista getNireInternet() {
		return nireInternet;
	}

	public void setNireInternet(WebenLista nireInternet) {
		this.nireInternet = nireInternet;
	}

}
