package kontzertuak2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class KontzertuZerrenda {
	private final static String NOMBRE_FICHERO = "files/coches.txt";
	List<Kontzertua> kontzertuak;
	List <Aretoa> aretoak;
	List <Taldea> taldeak;
	
	public KontzertuZerrenda () {
		kontzertuak = new ArrayList<>();
		hasieratuKontzertuak();
	}
	
	public String[] jasoAukerak(Filter<Kontzertua,String> selector) {
		List <String> aukerak1 = new ArrayList<>();
		Map<String,List<Kontzertua>> agrupacion = taldekatu(selector);
		Set<Entry<String,List<Kontzertua>>> aukerak = agrupacion.entrySet();
		for (Entry<String,List<Kontzertua>> aukera : aukerak) {
			aukerak1.add(aukera.getKey());
		}
		aukerak1.add(0, "denak");
		return (aukerak1.toArray(new String[0]));
	}
	public Map<String, List<Kontzertua>> taldekatu(Filter<Kontzertua, String> selector) {
		Map<String,List<Kontzertua>> agrupazioa = new HashMap<>();
		for (Kontzertua kontzertua : kontzertuak) {
			
			String clave = selector.seleccionar(kontzertua);
			List<Kontzertua> kontzZerrenda = agrupazioa.get(clave);
			if (kontzZerrenda == null) {
				kontzZerrenda = new ArrayList<>();
			}
			kontzZerrenda.add(kontzertua);
			agrupazioa.put(clave, kontzZerrenda);
			
		}
		return agrupazioa;
	}
	public List<Kontzertua> filtratu(Filter<Kontzertua,String> selector) {
		List<Kontzertua> taldea = new ArrayList<>();
		for (Kontzertua kontzertua : kontzertuak) {	
			if (selector.filtrar(kontzertua)) {
				taldea.add(kontzertua);
			}
		}
		return taldea;
	}
	public List<Kontzertua> filtratu(Filter<Kontzertua, String> selector,List<Kontzertua> bistaratzekoakList) {
		List<Kontzertua> taldea = new ArrayList<>();
		for (Kontzertua kontzertua : bistaratzekoakList) {	
			if (selector.filtrar(kontzertua)) {
				taldea.add(kontzertua);
			}
		}
		return taldea;
		
	}
	public Kontzertua [] ekarriAukeratutakoKontzertuak (List<Predicate<Kontzertua>> baldintzak ) {
		List<Kontzertua> aukeratutakoKontzertuak = new ArrayList<>(kontzertuak);
		Collections.copy(aukeratutakoKontzertuak, kontzertuak);
		for (Predicate<Kontzertua> condicion : baldintzak) {
			aukeratutakoKontzertuak = aukeratutakoKontzertuak.stream().filter(condicion).collect(Collectors.toList());
		}
		return (aukeratutakoKontzertuak.toArray(new Kontzertua[0]));
	}
	public void hasieratuAretoak()
	{
		Aretoa aretoa;
		aretoak = new ArrayList<Aretoa>();
		aretoa = new Aretoa(2, "San Agustin");
		this.aretoak.add(aretoa);
		aretoa = new Aretoa(5, "Bilbo Kafe Antzokia");
		this.aretoak.add(aretoa);
		aretoa = new Aretoa(3, "Santana 27");
		this.aretoak.add(aretoa);
		aretoa = new Aretoa(2, "Dabadaba");
		this.aretoak.add(aretoa);	
	}
	public void hasieratuTaldeak()
	{
		Taldea taldea;
		taldeak = new ArrayList<Taldea>();
		
		taldea = new Taldea("Zetak", "Pop");
		this.taldeak.add(taldea);
		taldea = new Taldea("Vulk", "Rock");
		this.taldeak.add(taldea);
		taldea = new Taldea("Gasteizko Banda", "Klasikoa");
		this.taldeak.add(taldea);
		taldea = new Taldea("Latzen", "Heavy");
		this.taldeak.add(taldea);
		taldea = new Taldea("Izaro", "Pop");
		this.taldeak.add(taldea);
		taldea = new Taldea("Belako", "Pop-Rock");
		this.taldeak.add(taldea);
	}
	
	public void hasieratuKontzertuak() {
		String linea;
		Kontzertua kontzertua;
		hasieratuTaldeak();
		hasieratuAretoak();
		
					
		kontzertua = new Kontzertua (this.aretoak.get(0),this.taldeak.get(1),"2022/12/15",25 );
		kontzertuak.add(kontzertua);
		kontzertua = new Kontzertua (this.aretoak.get(1),this.taldeak.get(1),"2022/12/16",25 );
		kontzertuak.add(kontzertua);
		kontzertua = new Kontzertua (this.aretoak.get(2),this.taldeak.get(2),"2023/01/15",10 );
		kontzertuak.add(kontzertua);
		kontzertua = new Kontzertua (this.aretoak.get(2),this.taldeak.get(3),"2022/12/18",25 );
		kontzertuak.add(kontzertua);
		kontzertua = new Kontzertua (this.aretoak.get(3),this.taldeak.get(4),"2022/12/20",100 );
		kontzertuak.add(kontzertua);
		kontzertua = new Kontzertua (this.aretoak.get(3),this.taldeak.get(0),"2022/12/26",5 );
		kontzertuak.add(kontzertua);
		kontzertua = new Kontzertua (this.aretoak.get(0),this.taldeak.get(2),"2022/12/27",25 );
		kontzertuak.add(kontzertua);
		kontzertua = new Kontzertua (this.aretoak.get(1),this.taldeak.get(3),"2022/12/30",20 );
		kontzertuak.add(kontzertua);
		kontzertua = new Kontzertua (this.aretoak.get(1),this.taldeak.get(5),"2022/12/31",25 );
		kontzertuak.add(kontzertua);
		kontzertua = new Kontzertua (this.aretoak.get(1),this.taldeak.get(1),"2023/01/06",20 );
		kontzertuak.add(kontzertua);
		
		
		}

	public List<Kontzertua> getKontzertuak(){
		List<Kontzertua> copia = new ArrayList<>(kontzertuak);
		Collections.copy(copia,kontzertuak);
		return copia;
	}
	
}
