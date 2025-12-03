package kontzertuak2;

public class Sarrera {

	int saldutaKop;
	int salduta[];
	int id [];
	Aretoa aretoa;
	public Sarrera(Aretoa aretoa)
	{
		this.aretoa = aretoa;
		saldutaKop = 0;
		id = new int[aretoa.getAforoa()];
		salduta = new int[aretoa.getAforoa()];
		hasieratu();
	}
	private void hasieratu() {
		for (int i = 0; i<aretoa.getAforoa();i++) id[i]=i;
		for (int i = 0; i<aretoa.getAforoa();i++) salduta[i]=0;
	}
	public int getSalduta() {
		return saldutaKop;
	}
	
	public int getId() {
		int i=0;
		while (i<aretoa.getAforoa() && salduta[i]==1 )i++;
		if(i<aretoa.getAforoa()) {
			salduta[i]=1;
			return  id[i];	
		}
		return -1;
		
		
	}
	public void setId(int[] id) {
		this.id = id;
	}
	
	public void setSalduta(int i,int zeinua) {
		if (zeinua<0 && this.salduta[i]== 1) {
			this.salduta[i]=0;
			this.saldutaKop +=zeinua;
		}
		if(zeinua>0) {
			this.saldutaKop +=zeinua;
			this.salduta[i]=1;
		}
			

	}
	public boolean sarrerarikBai() {
		
		if((this.aretoa.getAforoa()-saldutaKop)>0) return true;
		else return false;
	}
}
