package kontzertuak2;

public class Aretoa {
	int aforoa;
	String izena;
	public Aretoa(int aforoa, String izena) {
		this.aforoa = aforoa;
		this.izena = izena;
	}
	@Override
	public String toString() {
		return "Aretoa [Izena=" + izena + ", Aforoa=" + aforoa + "]";
	}
	public int getAforoa() {
		return aforoa;
	}
	public String getIzena() {
		return izena;
	}
	
	
	

}
