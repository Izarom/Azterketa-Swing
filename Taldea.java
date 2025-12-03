package kontzertuak2;

public class Taldea {

	String izena;
	String mota;
	public Taldea(String izena, String mota) {
		this.izena = izena;
		this.mota = mota;
	}
	public String getIzena() {
		return izena;
	}
	
	@Override
	public String toString() {
		return "Taldea [Izena=" + izena + ", Mota=" + mota + "]";
	}
	public String getMota() {
		return mota;
	}
	
	

}
