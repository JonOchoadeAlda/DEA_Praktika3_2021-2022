
public class Hitza {
	private String testua;
	private WebenLista lista;

	public Hitza(String testua) {
		this.testua = testua;
		this.lista = new WebenLista();
	}

	public WebenLista getLista() {
		return lista;
	}

	public void setLista(WebenLista lista) {
		this.lista = lista;
	}

	public String getTestua() {
		return testua;
	}

	public void setTestua(String testua) {
		this.testua = testua;
	}

}
