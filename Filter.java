package kontzertuak2;

public interface Filter <T,V>{
	
	public boolean filtrar (T valor);
	public V seleccionar (T valor);
}
