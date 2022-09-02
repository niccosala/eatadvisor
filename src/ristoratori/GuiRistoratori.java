package ristoratori;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import condivisa.AzioneFile;
import condivisa.Indirizzo;
import condivisa.Indirizzo.Qualificatore;
import condivisa.InfoGui;
import condivisa.Ristorante;
import condivisa.Ristorante.Tipologia;

/** Rappresenta la GUI dedicata all'applicazione ristoratori.
 * @author Niccolò Sala
 * @version 1.0
*/
public class GuiRistoratori extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private final int LAR_UNIT = InfoGui.getUnitaLarghezza();
	private final int ALT_UNIT = InfoGui.getUnitaAltezza();
	private final int ALT_COST = 70;
	private int LAR_COST = 65;
	private final int EO_SPAZIO = InfoGui.getSpazioEO(LAR_COST);
	private final int NS_SPAZIO = InfoGui.getSpazioNS(ALT_COST);
			
	private JPanel pannello, pnl_agg, pnl_list;
	private JScrollPane pnl_tab;
	private JLabel lbl_titolo, lbl_desc, lbl_imm, lbl_benv, lbl_nome, lbl_url, lbl_tel, lbl_qual, lbl_ind, lbl_num, lbl_com, lbl_cap, lbl_sig, lbl_tipo, lbl_aiu, lbl_err;
	private JButton btn_agg, btn_list, btn_piu, btn_can;
	private JTextField txt_nome, txt_url, txt_tel, txt_ind, txt_num, txt_com, txt_sig, txt_cap; 
	private JComboBox<Tipologia> cmb_tipo;
	private JComboBox<Qualificatore> cmb_ind;
	private JTable tbl_list;
	private ImageIcon img = new ImageIcon(this.getClass().getResource("icona_s.png"));
	
	private AzioneFile file = new AzioneFile("EatAdvisor");
	private ArrayList<Object> lista;
	private Object []attr = new Object[5];
	private Ristorante ristorante;
	private String []colonne = {"Nome", "Tipologia", "Indirizzo", "Telefono", "Sito"};
	private DefaultTableModel modello = new DefaultTableModel();
	
	/** Crea e inzializza i componenti della GUI dedicata all'applicazione ristoratori.
	*/
	public GuiRistoratori() {
		
		if(((float)InfoGui.getLarghezza() / (float)InfoGui.getAltezza()) > 1.61) 
			LAR_COST = 60;
		
		setVisible(true);
		setTitle("EatAdvisor: Ristoratori");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(LAR_UNIT * LAR_COST, ALT_UNIT * ALT_COST);
		setLocation(EO_SPAZIO, NS_SPAZIO);
		setResizable(false);
		setBackground(Color.DARK_GRAY);
		setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("icona_s.png")));
		
		pannello = new JPanel();
		pannello.setBackground(Color.DARK_GRAY);
		pannello.setBorder(new EmptyBorder(5, 5, 5, 5));
		pannello.setLayout(null);
		setContentPane(pannello);
		
		lbl_titolo = new JLabel("EatAdvisor", SwingConstants.CENTER);
		lbl_titolo.setForeground(Color.WHITE);
		lbl_titolo.setFont(new Font("Helvetica", Font.BOLD, 45));
		lbl_titolo.setBounds(0, 15, this.getWidth(), (int)(this.getHeight() * 0.075));
		pannello.add(lbl_titolo);
		
		lbl_desc = new JLabel("Versione per ristoratori", SwingConstants.CENTER);
		lbl_desc.setForeground(Color.WHITE);
		lbl_desc.setBounds(0, (int)(this.getHeight() * 0.08) + 15, this.getWidth(), (int)(this.getHeight() * 0.035));
		lbl_desc.setFont(new Font("Helvetica", Font.BOLD, 18));
		pannello.add(lbl_desc);
		
		btn_agg = new JButton("ANAGRAFICA");
		btn_agg.setForeground(Color.GRAY);
		btn_agg.setBackground(Color.WHITE);
		btn_agg.setBounds((int)(this.getWidth() / 4) - 10, (int)(this.getHeight() * 0.205) + 15, (int)(this.getWidth() / 4), (int)(this.getHeight() * 0.075));
		btn_agg.setFont(new Font("Helvetica", Font.BOLD, 18));
		btn_agg.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pannello.add(btn_agg);
		
		btn_list = new JButton("LISTA");
		btn_list.setForeground(Color.GRAY);
		btn_list.setBackground(Color.WHITE);
		btn_list.setBounds((int)(this.getWidth() / 2) + 10, (int)(this.getHeight() * 0.205) + 15, (int)(this.getWidth() / 4), (int)(this.getHeight() * 0.075));
		btn_list.setFont(new Font("Helvetica", Font.BOLD, 18));
		btn_list.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btn_list.setVisible(true);
		pannello.add(btn_list);

		lbl_imm = new JLabel(img, SwingConstants.CENTER);
		lbl_imm.setBounds(0, (int)(this.getHeight() * 0.41), (int)(this.getWidth()), (int)(this.getHeight() / 2.75));
		lbl_imm.setVisible(true);
		pannello.add(lbl_imm);
		
		lbl_benv = new JLabel("<html><center>Benvenuto nel pannello di controllo per ristoratori. <br>Aggiungi, elimina o visiona l'elenco dei ristoranti tramite gli appositi pulsanti.</center></html>", SwingConstants.CENTER);
		lbl_benv.setForeground(Color.WHITE);
		lbl_benv.setFont(new Font("Helvetica", Font.BOLD, 18));
		lbl_benv.setBounds(0, (int)(this.getHeight() * 0.7), (int)(this.getWidth()), (int)(this.getHeight() * 0.2));
		pannello.add(lbl_benv);
		
		
		btn_agg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btn_agg.setForeground(Color.DARK_GRAY);
				btn_list.setForeground(Color.GRAY);
				lbl_benv.setVisible(false);
				lbl_imm.setVisible(false);
				pnl_list.setVisible(false);
				pnl_agg.setVisible(true);
			}
		});
		
		pnl_agg = new JPanel();
		pnl_agg.setBackground(Color.WHITE);
		pnl_agg.setLayout(null);
		pnl_agg.setBounds(0, (int)(this.getHeight() * 0.35), (int)(this.getWidth()), (int)(this.getHeight() * 0.65));
		pnl_agg.setBorder(BorderFactory.createMatteBorder(5, 0, 0, 0, Color.ORANGE));
		pannello.add(pnl_agg);
		pnl_agg.setVisible(false);

		lbl_err = new JLabel();
		lbl_err.setForeground(Color.GRAY);
		lbl_err.setFont(new Font("Helvetica", Font.BOLD, 13));
		lbl_err.setBounds((int)(this.getWidth() * 0.8), 0, (int)(this.getWidth() * 0.2), (int)(this.getHeight() * 0.05));
		pnl_agg.add(lbl_err);
		
		lbl_nome = new JLabel("Nome ristorante:");
		lbl_nome.setForeground(Color.DARK_GRAY);
		lbl_nome.setFont(new Font("Helvetica", Font.BOLD, 15));
		lbl_nome.setBounds((int)(this.getWidth() * 0.03), (int)(this.getHeight() * 0.07), (int)(this.getWidth() * 0.3), (int)(this.getHeight() * 0.035));
		pnl_agg.add(lbl_nome);
		
		txt_nome = new JTextField();
		txt_nome.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.DARK_GRAY));
		txt_nome.setBounds((int)(this.getWidth() * 0.03), (int)(this.getHeight() * 0.12), (int)(this.getWidth() * 0.3), (int)(this.getHeight() * 0.075));
		txt_nome.setFont(new Font("Helvetica", Font.BOLD, 18));
		pnl_agg.add(txt_nome);
		
		lbl_tel = new JLabel("Telefono:");
		lbl_tel.setForeground(Color.DARK_GRAY);
		lbl_tel.setFont(new Font("Helvetica", Font.BOLD, 15));
		lbl_tel.setBounds((int)(this.getWidth() * 0.35), (int)(this.getHeight() * 0.07), (int)(this.getWidth() * 0.3), (int)(this.getHeight() * 0.035));
		pnl_agg.add(lbl_tel);
		
		txt_tel = new JTextField();
		txt_tel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.DARK_GRAY));
		txt_tel.setBounds((int)(this.getWidth() * 0.35), (int)(this.getHeight() * 0.12), (int)(this.getWidth() * 0.3), (int)(this.getHeight() * 0.075));
		txt_tel.setFont(new Font("Helvetica", Font.BOLD, 18));
		pnl_agg.add(txt_tel);
		
		lbl_url = new JLabel("URL sito:");
		lbl_url.setForeground(Color.DARK_GRAY);
		lbl_url.setFont(new Font("Helvetica", Font.BOLD, 15));
		lbl_url.setBounds((int)(this.getWidth() * 0.67), (int)(this.getHeight() * 0.07), (int)(this.getWidth() * 0.3), (int)(this.getHeight() * 0.035));
		pnl_agg.add(lbl_url);
		
		txt_url = new JTextField();
		txt_url.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.DARK_GRAY));
		txt_url.setBounds((int)(this.getWidth() * 0.67), (int)(this.getHeight() * 0.12), (int)(this.getWidth() * 0.3), (int)(this.getHeight() * 0.075));
		txt_url.setFont(new Font("Helvetica", Font.BOLD, 18));
		pnl_agg.add(txt_url);
		
		lbl_tipo = new JLabel("Tipologia:");
		lbl_tipo.setForeground(Color.DARK_GRAY);
		lbl_tipo.setFont(new Font("Helvetica", Font.BOLD, 15));
		lbl_tipo.setBounds((int)(this.getWidth() * 0.03), (int)(this.getHeight() * 0.22), (int)(this.getWidth() * 0.2), (int)(this.getHeight() * 0.035));
		pnl_agg.add(lbl_tipo);
		
		cmb_tipo = new JComboBox<Tipologia>(Tipologia.values());
		cmb_tipo.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.DARK_GRAY));
		cmb_tipo.setBounds((int)(this.getWidth() * 0.03), (int)(this.getHeight() * 0.27), (int)(this.getWidth() * 0.2), (int)(this.getHeight() * 0.075));
		cmb_tipo.setFont(new Font("Helvetica", Font.BOLD, 18));
		pnl_agg.add(cmb_tipo);
		
		lbl_qual = new JLabel("Qualif.:");
		lbl_qual.setForeground(Color.DARK_GRAY);
		lbl_qual.setFont(new Font("Helvetica", Font.BOLD, 15));
		lbl_qual.setBounds((int)(this.getWidth() * 0.25), (int)(this.getHeight() * 0.22), (int)(this.getWidth() * 0.125), (int)(this.getHeight() * 0.035));
		pnl_agg.add(lbl_qual);
		
		cmb_ind = new JComboBox<Qualificatore>(Qualificatore.values());
		cmb_ind.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 0, Color.DARK_GRAY));
		cmb_ind.setBounds((int)(this.getWidth() * 0.25), (int)(this.getHeight() * 0.27), (int)(this.getWidth() * 0.125), (int)(this.getHeight() * 0.075));
		cmb_ind.setFont(new Font("Helvetica", Font.BOLD, 18));
		pnl_agg.add(cmb_ind);
		
		lbl_ind = new JLabel("Nome via:");
		lbl_ind.setForeground(Color.DARK_GRAY);
		lbl_ind.setFont(new Font("Helvetica", Font.BOLD, 15));
		lbl_ind.setBounds((int)(this.getWidth() * 0.374), (int)(this.getHeight() * 0.22), (int)(this.getWidth() * 0.2), (int)(this.getHeight() * 0.035));
		pnl_agg.add(lbl_ind);
		
		txt_ind = new JTextField();
		txt_ind.setBorder(BorderFactory.createMatteBorder(2, 0, 2, 0, Color.DARK_GRAY));
		txt_ind.setBounds((int)(this.getWidth() * 0.374), (int)(this.getHeight() * 0.27), (int)(this.getWidth() * 0.2), (int)(this.getHeight() * 0.075));
		txt_ind.setFont(new Font("Helvetica", Font.BOLD, 18));
		pnl_agg.add(txt_ind);
		
		lbl_num = new JLabel("Civ.:");
		lbl_num.setForeground(Color.DARK_GRAY);
		lbl_num.setFont(new Font("Helvetica", Font.BOLD, 15));
		lbl_num.setBounds((int)(this.getWidth() * 0.574), (int)(this.getHeight() * 0.22), (int)(this.getWidth() * 0.05), (int)(this.getHeight() * 0.035));
		pnl_agg.add(lbl_num);
		
		txt_num = new JTextField();
		txt_num.setBorder(BorderFactory.createMatteBorder(2, 0, 2, 0, Color.DARK_GRAY));
		txt_num.setBounds((int)(this.getWidth() * 0.574), (int)(this.getHeight() * 0.27), (int)(this.getWidth() * 0.05), (int)(this.getHeight() * 0.075));
		txt_num.setFont(new Font("Helvetica", Font.BOLD, 18));
		pnl_agg.add(txt_num);
	
		lbl_com = new JLabel("Comune:");
		lbl_com.setForeground(Color.DARK_GRAY);
		lbl_com.setFont(new Font("Helvetica", Font.BOLD, 15));
		lbl_com.setBounds((int)(this.getWidth() * 0.624), (int)(this.getHeight() * 0.22), (int)(this.getWidth() * 0.189), (int)(this.getHeight() * 0.035));
		pnl_agg.add(lbl_com);
		
		txt_com = new JTextField();
		txt_com.setBorder(BorderFactory.createMatteBorder(2, 0, 2, 0, Color.DARK_GRAY));
		txt_com.setBounds((int)(this.getWidth() * 0.624), (int)(this.getHeight() * 0.27), (int)(this.getWidth() * 0.189), (int)(this.getHeight() * 0.075));
		txt_com.setFont(new Font("Helvetica", Font.BOLD, 18));
		pnl_agg.add(txt_com);
		
		lbl_sig = new JLabel("Prov.:");
		lbl_sig.setForeground(Color.DARK_GRAY);
		lbl_sig.setFont(new Font("Helvetica", Font.BOLD, 15));
		lbl_sig.setBounds((int)(this.getWidth() * 0.81), (int)(this.getHeight() * 0.22), (int)(this.getWidth() * 0.059), (int)(this.getHeight() * 0.035));
		pnl_agg.add(lbl_sig);
		
		txt_sig = new JTextField();
		txt_sig.setBorder(BorderFactory.createMatteBorder(2, 0, 2, 0, Color.DARK_GRAY));
		txt_sig.setBounds((int)(this.getWidth() * 0.81), (int)(this.getHeight() * 0.27), (int)(this.getWidth() * 0.059), (int)(this.getHeight() * 0.075));
		txt_sig.setFont(new Font("Helvetica", Font.BOLD, 18));
		pnl_agg.add(txt_sig);
		
		lbl_cap = new JLabel("CAP:");
		lbl_cap.setForeground(Color.DARK_GRAY);
		lbl_cap.setFont(new Font("Helvetica", Font.BOLD, 15));
		lbl_cap.setBounds((int)(this.getWidth() * 0.868), (int)(this.getHeight() * 0.22), (int)(this.getWidth() * 0.102), (int)(this.getHeight() * 0.035));
		pnl_agg.add(lbl_cap);
		
		txt_cap = new JTextField();
		txt_cap.setBorder(BorderFactory.createMatteBorder(2, 0, 2, 2, Color.DARK_GRAY));
		txt_cap.setBounds((int)(this.getWidth() * 0.868), (int)(this.getHeight() * 0.27), (int)(this.getWidth() * 0.102), (int)(this.getHeight() * 0.075));
		txt_cap.setFont(new Font("Helvetica", Font.BOLD, 18));
		pnl_agg.add(txt_cap);
		
		lbl_aiu = new JLabel("<html><center>* Aggiungi: Compila tutti i campi e clicca il pulsante 'AGGIUNGI'<br>* Elimina: Compila tutti i campi e premi il pulsante 'ELIMINA'. </html></center>",  SwingConstants.CENTER);
		lbl_aiu.setForeground(Color.GRAY);
		lbl_aiu.setFont(new Font("Helvetica", Font.BOLD, 12));
		lbl_aiu.setBounds((int)(this.getWidth() * 0.03), (int)(this.getHeight() * 0.4), (int)(this.getWidth() * 0.97), (int)(this.getHeight() * 0.1));
		pnl_agg.add(lbl_aiu);
		
		btn_can = new JButton("ELIMINA");
		btn_can.setForeground(Color.DARK_GRAY);
		btn_can.setBackground(Color.WHITE);
		btn_can.setBounds((int)(this.getWidth() / 4) - 10, (int)(this.getHeight() * 0.51), (int)(this.getWidth() / 4), (int)(this.getHeight() * 0.075));
		btn_can.setFont(new Font("Helvetica", Font.BOLD, 18));
		btn_can.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pnl_agg.add(btn_can);
		
		btn_piu = new JButton("AGGIUNGI");
		btn_piu.setForeground(Color.ORANGE);
		btn_piu.setBackground(Color.WHITE);
		btn_piu.setBounds((int)(this.getWidth() / 2) + 10, (int)(this.getHeight() * 0.51), (int)(this.getWidth() / 4), (int)(this.getHeight() * 0.075));
		btn_piu.setFont(new Font("Helvetica", Font.BOLD, 18));
		btn_piu.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pnl_agg.add(btn_piu);
		
		
		btn_piu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {	
				Ristorante ristorante = controllaInput();
				
				if(ristorante != null) {
					if(file.scrivi(ristorante, false)) {
						lbl_err.setText("Ristorante aggiunto.");
						txt_nome.setText(null);
						txt_ind.setText(null);
						txt_num.setText(null);
						txt_com.setText(null);
						txt_sig.setText(null);
						txt_url.setText(null);
						txt_tel.setText(null);
						txt_cap.setText(null);
						file.leggi(true);
					}
					else
						lbl_err.setText("Errore.");
				}
				
			}
		});
		
		
		btn_can.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Ristorante ristorante = controllaInput();
				
				if(ristorante != null) {
					if(file.cancella(ristorante)) {
						lbl_err.setText("Ristorante eliminato.");
						txt_nome.setText(null);
						txt_ind.setText(null);
						txt_num.setText(null);
						txt_com.setText(null);
						txt_sig.setText(null);
						txt_url.setText(null);
						txt_tel.setText(null);
						txt_cap.setText(null);
						file.leggi(true);
					}
					else
						lbl_err.setText("Errore.");
				}
				
			}
		});
		


		
		btn_list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(modello.getRowCount() > 0)
					modello.setRowCount(0);
				
				btn_agg.setForeground(Color.GRAY);
				btn_list.setForeground(Color.DARK_GRAY);
				lbl_benv.setVisible(false);
				lbl_imm.setVisible(false);
				pnl_agg.setVisible(false);
				pnl_list.setVisible(true);
				
				lista = file.filtraPerTipo(Ristorante.class);
				for(int i=0; i<lista.size(); i++) {
					ristorante = (Ristorante)lista.get(i);
					
					attr[0] = ristorante.getNome();
					attr[1] = ristorante.getTipologia().toString();
					attr[2] = ristorante.getIndirizzo().toString();
					attr[3] = ristorante.getTelefono();
					attr[4] = ristorante.getUrl();
					
					modello.addRow(attr);
				}
			}
		});
		
		pnl_list = new JPanel();
		pnl_list.setBackground(Color.WHITE);
		pnl_list.setLayout(null);
		pnl_list.setBorder(BorderFactory.createMatteBorder(5, 0, 0, 0, Color.ORANGE));
		pnl_list.setBounds(0, (int)(this.getHeight() * 0.35), (int)(this.getWidth()), (int)(this.getHeight() * 0.65));
		pannello.add(pnl_list);
		
		tbl_list = new JTable() {
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) {                
				return false;               
		};};
		modello.setColumnIdentifiers(colonne);
		tbl_list.setModel(modello);
		tbl_list.setBackground(Color.WHITE);
		tbl_list.setForeground(Color.BLACK);
		tbl_list.setFont(new Font("Helvetica", Font.BOLD, 15));
		tbl_list.setRowHeight(20);
		
		pnl_tab = new JScrollPane();
		pnl_tab.setBounds(10, 10, (int)(this.getWidth() - 20), (int)(this.getHeight() * 0.65 - 10));
		pnl_tab.setBackground(Color.WHITE);
		pnl_list.add(pnl_tab);
		pnl_tab.setViewportView(tbl_list);
		pnl_list.setVisible(false);
	}
	
	private Ristorante controllaInput() {
		boolean flag = true;
		
		if(txt_cap.getText().length() != 5) {
			flag = false;
			txt_cap.setBackground(Color.PINK);
		} else
			txt_cap.setBackground(Color.WHITE);
		if(txt_sig.getText().length() != 2) {
			flag = false;
			txt_sig.setBackground(Color.PINK);
		} else
			txt_sig.setBackground(Color.WHITE);
		if(txt_nome.getText().equals(null) || txt_nome.getText().equals("")) {
			flag = false;
			txt_nome.setBackground(Color.PINK);
		} else {
			txt_nome.setBackground(Color.WHITE);
			txt_nome.setText(txt_nome.getText().substring(0,1).toUpperCase() + txt_nome.getText().substring(1).toLowerCase());
		}
		if(txt_url.getText().equals(null) || txt_url.getText().equals("")) {
			flag = false;
			txt_url.setBackground(Color.PINK);
		} else
			txt_url.setBackground(Color.WHITE);
		if(!(txt_tel.getText().matches("[0-9]+")) || txt_tel.getText().length() < 9 || txt_tel.getText().equals(null) || txt_tel.getText().equals("")) {
			flag = false;
			txt_tel.setBackground(Color.PINK);
		} else
			txt_tel.setBackground(Color.WHITE);
		if(txt_com.getText().equals(null) || txt_com.getText().equals("")) {
			flag = false;
			txt_com.setBackground(Color.PINK);
		} else {
			txt_com.setBackground(Color.WHITE);
			txt_com.setText(txt_com.getText().substring(0,1).toUpperCase() + txt_com.getText().substring(1).toLowerCase());
		}
		if(txt_ind.getText().equals(null) || txt_ind.getText().equals("")) {
			flag = false;
			txt_ind.setBackground(Color.PINK);
		} else {
			txt_ind.setBackground(Color.WHITE);
			txt_ind.setText(txt_ind.getText().substring(0,1).toUpperCase() + txt_ind.getText().substring(1).toLowerCase());
		}
		if(!(txt_num.getText().matches("[0-9]+")) || txt_num.getText().equals(null) || txt_num.getText().equals("")){
			flag = false;
			txt_num.setBackground(Color.PINK);
		} else
			txt_num.setBackground(Color.WHITE);
		
		if(flag) {
			Indirizzo indirizzo = new Indirizzo(
					(Qualificatore)cmb_ind.getSelectedItem(),
					txt_ind.getText(),
					Integer.parseInt(txt_num.getText()),
					txt_com.getText(),
					txt_sig.getText().toUpperCase(),
					Integer.parseInt(txt_cap.getText())
					);
			
			Ristorante ristorante = new Ristorante(
					txt_nome.getText(), 
					txt_url.getText().toLowerCase(),
					txt_tel.getText(),
					indirizzo,
					(Tipologia)cmb_tipo.getSelectedItem()
					);
			
			return ristorante;
		}
		return null;
	}
}
