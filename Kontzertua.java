package kontzertuak2;

import java.util.Date;

public class Kontzertua {
	Aretoa aretoa;
	Taldea taldea;
	double prezioa;
	String data;
	private String imagen;
	Sarrera sarrera;
	
	public Kontzertua(Aretoa aretoa, Taldea taldea, String data, double prezioa) {
		super();
		this.aretoa = aretoa;
		this.taldea = taldea;
		this.data = data;
		this.prezioa = prezioa;
		this.imagen = "irudiak/"+taldea.getIzena()+".jpg";
		this.sarrera = new Sarrera (aretoa);
		
		
	}

	
	
	public Aretoa getAretoa() {
		return aretoa;
	}
	public String getAretoaIzena() {
		return aretoa.getIzena();
	}

	public Taldea getTaldea() {
		return taldea;
	}
	public String getTaldeaIzena() {
		return taldea.getIzena();
	}

	public double getPrezioa() {
		return prezioa;
	}

	public String getData() {
		return data;
	}

	@Override
	public int hashCode() {
		
		return taldea.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof Kontzertua)) return false;
		Kontzertua kontzertua = (Kontzertua) obj;
		if (!this.taldea.equals(kontzertua.taldea)) return false;
		
		return true;
	}
	@Override
	public String toString() {
		
		return taldea.toString();
	}

	public String getImagen() {
		
		return imagen;
	}



	public Sarrera getSarrera() {
		
		return sarrera;
	}

	
}
