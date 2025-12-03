package kontzertuak2;

public class FabricaFiltros {

	public static Filter<Kontzertua,String> getFiltroTaldea(String balioa){
		return new FiltroTaldea(balioa);
	}
	public static Filter<Kontzertua,String> getFiltroAretoa(String balioa){
		return new FiltroAretoa(balioa);
	}
	public static Filter<Kontzertua,String> getFiltorData(String balioa){
		return new FiltroData(balioa);
	}
		
	public static class FiltroTaldea implements Filter<Kontzertua,String> {
		String taldea;
		public FiltroTaldea(String taldea) {
			this.taldea = taldea;
		}
		@Override
		public boolean filtrar(Kontzertua kontzertua) {
			return kontzertua.getTaldeaIzena().equals(taldea);
		}
		@Override
		public String seleccionar(Kontzertua kontzertua) {
			return kontzertua.getTaldeaIzena();
		}
		
	}
	public static class FiltroAretoa implements Filter<Kontzertua,String> {

		String aretoa;
		public FiltroAretoa(String aretoa) {
			this.aretoa = aretoa;
		}
		@Override
		public boolean filtrar(Kontzertua kontzertua) {
			return kontzertua.getAretoaIzena().equals(aretoa);
		}
		@Override
		public String seleccionar(Kontzertua kontzertua) {
			return kontzertua.getAretoaIzena();
		}
		
		
	}
	public static class FiltroData implements Filter<Kontzertua,String> {

		String data;
		public FiltroData(String data) {
			this.data = data;
		}
		
		@Override
		public boolean filtrar(Kontzertua kontzertua) {
			return kontzertua.getData().equals(data);
		}
		@Override
		public String seleccionar(Kontzertua kontzertua) {
			return kontzertua.getData();
		}
		
		
	}
}
