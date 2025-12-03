package kontzertuak2;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;


public class NireAdaptadorea  implements ListCellRenderer<Kontzertua> {

	@Override
	public Component getListCellRendererComponent(JList<? extends Kontzertua> list,
	         Kontzertua c,  int index, boolean isSelected,  boolean cellHasFocus)  {
		
		
			JPanel panel = new JPanel(new BorderLayout(10,0));
			
			panel.add(crearImagen(c), BorderLayout.WEST);
			panel.add(crearDatos(c), BorderLayout.CENTER);
			
	        
	        panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10,10,10,10),
	        		BorderFactory.createLineBorder(Color.red)));
	        panel.setBackground(isSelected ? Color.BLUE : Color.white);
	       
	         panel.setOpaque(true);
	       
	         return panel;
	     }

	private Component crearDatos(Kontzertua c) {
		JPanel panel = new JPanel (new BorderLayout(0,10));
		JPanel panelInterior = new JPanel(new GridLayout(3,2));
		panelInterior.add(crearCampo(" ", c.getTaldea().toString(), new Font("Arial",Font.BOLD,12),Color.BLACK));
		panelInterior.add(crearCampo(" ",c.getAretoa().toString(),new Font("Arial",Font.BOLD,14), Color.BLACK ));
		panelInterior.add(crearCampo("Data", c.getData(),new Font("Arial",Font.ITALIC,12),Color.BLACK));
		panelInterior.add(crearCampo("Prezioa", String.valueOf(c.getPrezioa()), new Font("Arial",Font.ITALIC,12),Color.RED));

		panelInterior.add(crearCampo("Sarrerak salgai", String.valueOf(c.getAretoa().getAforoa()-c.getSarrera().getSalduta()), new Font("Arial",Font.BOLD,14),Color.BLACK));
		
		JPanel panelSur = new JPanel ();
		String icono = c.getSarrera().sarrerarikBai()?"irudiak/ticket.jpg":"irudiak/soldout.jpg";
		ImageIcon icon = new ImageIcon (icono);
		JLabel label = new JLabel(icon);
		
		panelSur.add(label);
		panel.add(panelInterior);
		panel.add(panelSur,BorderLayout.SOUTH);
		return panel;
	}
	private Component crearCampo (String titulo, String valor, Font font, Color color) {
		JLabel label = new JLabel (titulo+" " +valor);
		label.setFont(font);
		label.setForeground(color);
		return label;
	}
	
	private Component crearImagen(Kontzertua c) {
		JLabel label = new JLabel (new ImageIcon(c.getImagen()));
		return label;
	}

	
	

	

}
