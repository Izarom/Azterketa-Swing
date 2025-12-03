package kontzertuak2;


import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class Printzipala extends JFrame implements ActionListener, ListSelectionListener{
	
	KontzertuZerrenda plataforma;
	JList<Kontzertua> jlKontzertuak;
	Kontzertua [] bistaratzekoak;
	List<JComboBox<String>> aukerenZerrenda;
	NireAkzioa erosi, anulatu, irten;
	
	public Printzipala(){
		super("Kontzertuen Sarreren Erosketa");
		plataforma = new KontzertuZerrenda();
		sortuAkzioak();
		this.setLocation(100,100);
		this.setSize(800,600);
		aukerenZerrenda = new ArrayList<>();
		this.setContentPane(crearPanelVentana());
		this.setJMenuBar(crearMenuBar());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}
		
	
private void sortuAkzioak() {
	erosi = new NireAkzioa ("Erosi",new ImageIcon("irudiak/erosi.jpg"),"erosi aukeratutako kontzerturako sarrera",
			new Integer(KeyEvent.VK_A));
	anulatu = new NireAkzioa ("Anulatu",new ImageIcon("irudiak/bueltatu.jpg"),"erositako sarrera bueltatzeko aukera",
			new Integer(KeyEvent.VK_B));
	irten = new NireAkzioa ("Irten",new ImageIcon("irudiak/exit.jpg"),"Aplikazioa itxi",
			new Integer(KeyEvent.VK_S));
	}
private Container crearPanelVentana() {
	JPanel panel = new JPanel(new BorderLayout (0,20));
	panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
	panel.add(crearToolBar(), BorderLayout.NORTH);
	panel.add(crearPanelCentral(),BorderLayout.CENTER);
	return panel;
}
private Component crearPanelCentral() {
	JSplitPane panel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, false,
			crearPanelSeleccion(),crearPanelKontzertuak());
	return panel;
}
private Component crearPanelKontzertuak() {
	JScrollPane panel = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	jlKontzertuak = new JList<>();
	jlKontzertuak.setCellRenderer(new NireAdaptadorea());
	jlKontzertuak.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	jlKontzertuak.addListSelectionListener(this);
	bistaratzekoak = sortuBistaratzekoak();
	jlKontzertuak.setListData(bistaratzekoak);
	
	panel.setViewportView(jlKontzertuak);
	return panel;
}
private Kontzertua[] sortuBistaratzekoak() {
	List<Kontzertua> bistaratzekoakList = plataforma.getKontzertuak();
	
	for (int i= 0; i< aukerenZerrenda.size(); i++) {
		JComboBox<String> opcion =aukerenZerrenda.get(i); 
		String balioa = (String) opcion.getSelectedItem();
		if (!balioa.equals("denak")) {
			switch (i) {
			case 0: bistaratzekoakList=plataforma.filtratu(FabricaFiltros.getFiltroTaldea(balioa));break;
			case 1: bistaratzekoakList = plataforma.filtratu(FabricaFiltros.getFiltroAretoa(balioa), bistaratzekoakList);break;
			case 2: bistaratzekoakList= plataforma.filtratu(FabricaFiltros.getFiltorData(balioa), bistaratzekoakList);break;
			}
		}
	}
	return (bistaratzekoakList.toArray(new Kontzertua[0]));
}

private Component crearPanelSeleccion() {
	JPanel panel = new JPanel (new BorderLayout());
	JPanel panelInterior = new JPanel (new GridLayout(5,1));
	panelInterior.add(crearPanelOpcion(FabricaFiltros.getFiltroTaldea("denak"),"Taldea"));
	panelInterior.add(crearPanelOpcion(FabricaFiltros.getFiltroAretoa("denak"),"Aretoa"));
	panelInterior.add(crearPanelOpcion(FabricaFiltros.getFiltorData("denak"),"Data"));
	panel.add(panelInterior);
	
	panel.add(crearPanelBoton(), BorderLayout.SOUTH);
	return panel;
}


private Component crearPanelOpcion(Filter<Kontzertua,String> selector, String titulo) {
	JPanel panel = new JPanel(new BorderLayout());
	JPanel panelInterior = new JPanel (new GridLayout(2,1,0,10));
	JLabel label = new JLabel (titulo);
	JComboBox<String> comboOpciones = new JComboBox<>(plataforma.jasoAukerak(selector));
	comboOpciones.setSelectedIndex(0);
	aukerenZerrenda.add(comboOpciones);
	panelInterior.add(label);
	panelInterior.add(comboOpciones);
	panel.add(panelInterior, BorderLayout.NORTH);
	return panel;
}


private Component crearPanelBoton() {
	JPanel panel = new JPanel ();
	JButton boton = new JButton ("Bilatu");
	boton.addActionListener(this);
	panel.add(boton);
	return panel;
}

private Component crearToolBar() {
	JToolBar toolBar = new JToolBar();
	
	toolBar.add(erosi).setSize(10, 10);
	toolBar.add(anulatu);
	toolBar.add(Box.createHorizontalGlue());
	toolBar.add(irten);
	return toolBar;
}
private JMenuBar crearMenuBar() {
	JMenuBar barra = new JMenuBar();
	barra.add (crearMenuEditar());
	barra.add(Box.createHorizontalGlue());
	barra.add (crearMenuSalir());
	
	return barra;
}
private JMenu crearMenuEditar() {
	JMenu menuEditar = new JMenu ("Ticket-ak");
	menuEditar.setMnemonic(new Integer(KeyEvent.VK_E));
	menuEditar.add(erosi);
	menuEditar.add(anulatu);
	
	return menuEditar;
}
private JMenu crearMenuSalir() {
	
	JMenu menuSalir = new JMenu ("Irten");
	menuSalir.setMnemonic(new Integer(KeyEvent.VK_S));
	menuSalir.add(irten);
	
	
	return menuSalir;
}
private class NireAkzioa extends AbstractAction {
	String texto;
	public NireAkzioa (String texto, Icon imagen, String descrip, Integer nemonic){
		super(texto,imagen);
		
		this.texto = texto;
		this.putValue( Action.SHORT_DESCRIPTION ,descrip);
		this.putValue(Action.MNEMONIC_KEY, nemonic);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		int id;
		if (texto.equals("Erosi")){
			if (jlKontzertuak.getSelectedIndex()!=-1) {
				Kontzertua kontzertua = jlKontzertuak.getSelectedValue();
				id = kontzertua.getSarrera().getId();
				StringBuilder mensaje = new StringBuilder("Ondorengo Kontzerturako Sarrera Erostera Zoaz: ")
						.append(kontzertua.getTaldeaIzena()).append("\nAretoa: "+kontzertua.getAretoaIzena())
						.append("\nData: "+ kontzertua.getData()+ "\nID: "+id);
				int opcion = JOptionPane.showConfirmDialog(Printzipala.this, mensaje.toString(),"Erosketa Konfirmazioa",JOptionPane.OK_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE);
				if (opcion == JOptionPane.OK_OPTION) {
					kontzertua.getSarrera().setSalduta(id, 1);
					jlKontzertuak.repaint();
				}
			}
		}
		if (texto.equals("Anulatu")){
			if (jlKontzertuak.getSelectedIndex()!=-1) {
				Kontzertua kontzertua = jlKontzertuak.getSelectedValue();
				StringBuilder mensaje = new StringBuilder("Konzerturako Sarrera Anulatzera Zoaz: ")
						.append(kontzertua.getTaldeaIzena()).append("\nAretoa: "+kontzertua.getAretoaIzena())
						.append("\nData: "+ kontzertua.getData());
				String opcion = (String) JOptionPane.showInputDialog(Printzipala.this, mensaje.toString(), "Anulazioaren Konfirmazioa", JOptionPane.QUESTION_MESSAGE, new ImageIcon("irudiak/bueltatu.jpg"), null, null);
				if (opcion != null){
					kontzertua.getSarrera().setSalduta(Integer.valueOf(opcion),-1);
					jlKontzertuak.repaint();
				}
			}
		}
		if (texto.equals("Irten")){
		
			Printzipala.this.dispose();
		}
	}
}


	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting()) return;
		if (jlKontzertuak.getSelectedIndex()==-1) {
			erosi.setEnabled(false);
			anulatu.setEnabled(false);
		}else {
			Kontzertua kontzertua = jlKontzertuak.getSelectedValue();
			if (kontzertua.getSarrera().sarrerarikBai() && kontzertua.getSarrera().getSalduta()!=0) {
				erosi.setEnabled(true);
				anulatu.setEnabled(true);
			}else if(kontzertua.getSarrera().sarrerarikBai() && kontzertua.getSarrera().getSalduta()==0) {
				erosi.setEnabled(true);
				anulatu.setEnabled(false);
			}
			else {
				erosi.setEnabled(false);
				anulatu.setEnabled(true);
			}
		}
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {

	
		bistaratzekoak = sortuBistaratzekoak();
	    jlKontzertuak.setListData(bistaratzekoak);
	
	}
	public static void main(String[] args) {
		Printzipala programa = new Printzipala();
		
	}	
}
