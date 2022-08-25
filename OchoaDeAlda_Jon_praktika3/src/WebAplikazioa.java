import java.io.IOException;
import java.util.Scanner;

public class WebAplikazioa {

	public static void main(String[] args) throws IOException {

		// SORTU ETA HASIERATU HIZTEGIA (Singleton)

		Hiztegia hiztegia = Hiztegia.getInstance();

		// SORTU ETA HASIERATU INTERNET (Singleton)

		Internet internet = Internet.getInstance();
		HitzenHashMapa hitzenMapa = new HitzenHashMapa();
		hiztegia.setHiztegia(hitzenMapa);

		internet.hasieratu("smallindex", "smallpld-arc");
		hiztegia.hasieratu("words.txt");

		//((BZBHitzak) hiztegia.getNireHiztegia()).bahetuHitzGakoak();

		int aukera = 1;
		Scanner sc = new Scanner(System.in);
		String hitza, urlJatorri, urlHelburu;
		while (aukera != 0) {
			System.out.println("Zer egin nahi duzu?");
			System.out.println("1. Web-orriak bilatu gako-hitzen bidez");
			System.out.println("2. Web-orriak konektatuta dauden ikusi url bidez");
			System.out.println("3. Web-orrien arteko bidea ikusi url bidez");
			System.out.println("0. Irten");
			aukera = Integer.parseInt(sc.nextLine());
			switch (aukera) {
			case 1:
				System.out.println("Sartu gako-hitz bat:");
				hitza = sc.nextLine();
				internet.webBilatzailea(hitza);

				break;
			case 2:
				System.out.println("Sartu jatorri url bat:");
				urlJatorri = sc.nextLine();
				System.out.println("Sartu helburu url bat konektatuta dauden ikusteko:");
				urlHelburu = sc.nextLine();
				if (internet.konektatutaDaude(urlJatorri, urlHelburu)) {
					System.out.println("+++KONEKTATUTA DAUDE!+++");
				} else {
					System.out.println("---EZ daude konektatuta---");
				}
				break;
			case 3:
				System.out.println("Sartu jatorri url bat:");
				urlJatorri = sc.nextLine();
				System.out.println("Sartu helburu url bat eta bide motzena adieraziko dut:");
				urlHelburu = sc.nextLine();
				internet.bideaInprimatu(urlJatorri, urlHelburu);
				break;
			default:
				break;
			}
		}
		sc.close();

	}

}
