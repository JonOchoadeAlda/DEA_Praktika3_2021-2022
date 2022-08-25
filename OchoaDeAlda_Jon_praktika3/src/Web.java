
public class Web {

	private int id;
	private String url;
	private WebenLista estekak;

	Web(int id, String url) {
		this.id = id;
		this.url = url;
		this.estekak = new WebenLista();
	}

	public int getKodea() {
		return id;
	}

	public void setKodea(int id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public WebenLista getEstekak() {
		return estekak;
	}

	public void setEstekak(WebenLista lista) {
		this.estekak = lista;
	}

}
