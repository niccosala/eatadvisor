package clienti;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import condivisa.AzioneFile;
import condivisa.InfoGui;
import condivisa.Ristorante;
import condivisa.Ristorante.Tipologia;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

/** Rappresenta la GUI dedicata all'applicazione clienti.
 * @author Niccolò Sala
 * @version 1.0
*/
public class GuiClienti extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private Cliente cliente;
	
	private final int LAR_UNIT = InfoGui.getUnitaLarghezza();
	private final int ALT_UNIT = InfoGui.getUnitaAltezza();
	private final int ALT_COST = 82;
	private int LAR_COST = 77;
	private final int EO_SPAZIO = InfoGui.getSpazioEO(LAR_COST);
	private final int NS_SPAZIO = InfoGui.getSpazioNS(ALT_COST);
	
	private JPanel pannello, pnl_sx, pnl_dx, pnl_giud, pnl_ctab, pnl_cgiud;
	private JScrollPane spnl_tab, spnl_giud;
	private JLabel lbl_tit, lbl_desc, lbl_r1, lbl_r2, lbl_gvoto, lbl_gdesc, lbl_gtit, lbl_rnome, lbl_rtipo, lbl_rind, lbl_rtel, lbl_rurl, lbl_v1, lbl_v2, lbl_v3, lbl_v4, lbl_v5, lbls_giu;
	private JButton btn_rn, btn_rt, btn_rc, btn_tc, btn_rip, btn_fil, btn_sel, btn_giu;
	private JTextField txt_r1, txt_r2, txt_giu;
	private JComboBox<Tipologia> cmb_tipo;
	private JComboBox<Integer> cmb_val;
	private JTable tbl_rist;
	private DefaultTableModel modello = new DefaultTableModel();
	private String []colonne = {"Nome", "Tipologia", "Comune"};
	private Object []attr = new Object[3];
	
	private AzioneFile file = new AzioneFile("EatAdvisor");
	
	private ArrayList<Object> listaRistoranti = new ArrayList<Object>(); 
	private ArrayList<Object> listaRistorantiFiltrata; 
	private Ristorante ristorante;
	int status = 1;
	
	/** Crea e inzializza i componenti della GUI dedicata all'accesso dell'applicazione clienti.
	 * @param cliente Cliente che ha fatto l'accesso all'applicazione clienti.
	*/
	public GuiClienti(Cliente cliente) {
		this.cliente = cliente;
		listaRistoranti = file.filtraPerTipo(Ristorante.class);
		listaRistorantiFiltrata = listaRistoranti;
		
		if(((float)InfoGui.getLarghezza() / (float)InfoGui.getAltezza()) > 1.61) 
			LAR_COST = 72;
		
		setVisible(true);
		setTitle("EatAdvisor: Clienti");
		setSize(LAR_UNIT * LAR_COST, ALT_UNIT * ALT_COST);
		setLocation(EO_SPAZIO, NS_SPAZIO);
		setBackground(Color.DARK_GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("icona_s.png")));
		
		pannello = new JPanel();
		pannello.setBackground(Color.DARK_GRAY);
		pannello.setBorder(new EmptyBorder(5, 5, 5, 5));
		pannello.setLayout(null);
		setContentPane(pannello);
		
		pnl_sx = new JPanel();
		pnl_sx.setBackground(Color.DARK_GRAY);
		pnl_sx.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 5, Color.ORANGE));
		pnl_sx.setLayout(null);
		pnl_sx.setBounds(0, 0, (int)(this.getWidth() * 0.38), (int)(this.getHeight()));
		pannello.add(pnl_sx);
		
		lbl_tit = new JLabel();
		lbl_tit.setForeground(Color.WHITE);
		lbl_tit.setFont(new Font("Hevetica", Font.BOLD, 18));
		lbl_tit.setBounds(15, (int)(this.getHeight() * 0.02), (int)(this.getWidth() - 15), (int)(this.getHeight() * 0.035));
		pnl_sx.add(lbl_tit);
		
		if(cliente != null)
			lbl_tit.setText("Ciao, " + cliente.getNickname() + ".");
		else
			lbl_tit.setText("Accesso avvenuto come ospite.");
		
		lbl_desc = new JLabel("<html>Seleziona uno dei metodi di ricerca per filtrare i<br> ristoranti.</html>");
		lbl_desc.setForeground(Color.LIGHT_GRAY);
		lbl_desc.setFont(new Font("Helvetica", Font.BOLD, 15));
		lbl_desc.setBounds(15, (int)(this.getHeight() * 0.075), (int)(this.getWidth() - 15), (int)(this.getHeight() * 0.05));
		pnl_sx.add(lbl_desc);
		
		btn_rn = new JButton("NOME");
		btn_rn.setForeground(Color.DARK_GRAY);
		btn_rn.setBackground(Color.WHITE);
		btn_rn.setBounds((int)(this.getWidth() * 0.01), (int)(this.getHeight() * 0.13), (int)(this.getWidth() * 0.09), (int)(this.getHeight() * 0.065));
		btn_rn.setFont(new Font("Helvetica", Font.BOLD, 12));
		btn_rn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pnl_sx.add(btn_rn);
		
		btn_rt = new JButton("TIPOLOGIA");
		btn_rt.setForeground(Color.GRAY);
		btn_rt.setBackground(Color.WHITE);
		btn_rt.setBounds((int)(this.getWidth() * 0.10), (int)(this.getHeight() * 0.13), (int)(this.getWidth() * 0.09), (int)(this.getHeight() * 0.065));
		btn_rt.setFont(new Font("Helvetica", Font.BOLD, 12));
		btn_rt.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pnl_sx.add(btn_rt);
		
		btn_rc = new JButton("COMUNE");
		btn_rc.setForeground(Color.GRAY);
		btn_rc.setBackground(Color.WHITE);
		btn_rc.setBounds((int)(this.getWidth() * 0.19), (int)(this.getHeight() * 0.13), (int)(this.getWidth() * 0.09), (int)(this.getHeight() * 0.065));
		btn_rc.setFont(new Font("Helvetica", Font.BOLD, 12));
		btn_rc.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pnl_sx.add(btn_rc);
		
		btn_tc = new JButton("TIP. E COM.");
		btn_tc.setForeground(Color.GRAY);
		btn_tc.setBackground(Color.WHITE);
		btn_tc.setBounds((int)(this.getWidth() * 0.28), (int)(this.getHeight() * 0.13), (int)(this.getWidth() * 0.09), (int)(this.getHeight() * 0.065));
		btn_tc.setFont(new Font("Helvetica", Font.BOLD, 12));
		btn_tc.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pnl_sx.add(btn_tc);
		
		lbl_r1 = new JLabel("Nome:");
		lbl_r1.setForeground(Color.WHITE);
		lbl_r1.setFont(new Font("Helvetica", Font.BOLD, 15));
		lbl_r1.setBounds(15, (int)(this.getHeight() * 0.2), (int)(this.getWidth() * 0.19 - 15), (int)(this.getHeight() * 0.05));
		pnl_sx.add(lbl_r1);
		
		lbl_r2 = new JLabel("Comune:");
		lbl_r2.setForeground(Color.WHITE);
		lbl_r2.setFont(new Font("Helvetica", Font.BOLD, 15));
		lbl_r2.setBounds((int)(this.getWidth() * 0.19 + 7), (int)(this.getHeight() * 0.2), (int)(this.getWidth() * 0.5 - 15), (int)(this.getHeight() * 0.05));
		lbl_r2.setVisible(false);
		pnl_sx.add(lbl_r2);
		
		txt_r1 = new JTextField();
		txt_r1.setForeground(Color.WHITE);
		txt_r1.setBackground(Color.DARK_GRAY);
		txt_r1.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.WHITE));
		txt_r1.setBounds(15, (int)(this.getHeight() * 0.24), (int)(this.getWidth() * 0.38 - 30), (int)(this.getHeight() * 0.065));
		txt_r1.setFont(new Font("Helvetica", Font.BOLD, 18));
		pnl_sx.add(txt_r1);
		
		txt_r2 = new JTextField();
		txt_r2.setForeground(Color.WHITE);
		txt_r2.setBackground(Color.DARK_GRAY);
		txt_r2.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.WHITE));
		txt_r2.setBounds((int)(this.getWidth() * 0.19 + 7), (int)(this.getHeight() * 0.24), (int)(this.getWidth() * 0.19 - 22.5), (int)(this.getHeight() * 0.065));
		txt_r2.setFont(new Font("Helvetica", Font.BOLD, 18));
		txt_r2.setVisible(false);
		pnl_sx.add(txt_r2);
		
		cmb_tipo = new JComboBox<Tipologia>(Tipologia.values());
		cmb_tipo.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.WHITE));
		cmb_tipo.setBounds(15, (int)(this.getHeight() * 0.24), (int)(this.getWidth() * 0.38 - 30), (int)(this.getHeight() * 0.065));
		cmb_tipo.setFont(new Font("Helvetica", Font.BOLD, 18));
		cmb_tipo.setVisible(false);
		pnl_sx.add(cmb_tipo);
		int cmb_tipo_w = cmb_tipo.getBounds().width;
		
		btn_rn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				status = 1;
				ripristina(listaRistoranti);
				btn_rn.setForeground(Color.DARK_GRAY);
				btn_rc.setForeground(Color.GRAY);
				btn_rt.setForeground(Color.GRAY);
				btn_tc.setForeground(Color.GRAY);
				txt_r1.setVisible(true);
				txt_r1.setText("");
				txt_r2.setVisible(false);
				lbl_r1.setText("Nome:");
				lbl_r2.setVisible(false);
				txt_r2.setText("");
				cmb_tipo.setVisible(false);
				cmb_tipo.setBounds(cmb_tipo.getBounds().x, cmb_tipo.getBounds().y, cmb_tipo_w, cmb_tipo.getBounds().height);
			}
		});
		
		btn_rt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {	
				status = 2;
				ripristina(listaRistoranti);
				btn_rn.setForeground(Color.GRAY);
				btn_rc.setForeground(Color.GRAY);
				btn_rt.setForeground(Color.DARK_GRAY);
				btn_tc.setForeground(Color.GRAY);
				txt_r1.setVisible(false);
				txt_r1.setText("");
				txt_r2.setVisible(false);
				txt_r2.setText("");
				lbl_r1.setText("Tipologia:");
				lbl_r2.setVisible(false);
				cmb_tipo.setVisible(true);
				cmb_tipo.setBounds(cmb_tipo.getBounds().x, cmb_tipo.getBounds().y, cmb_tipo_w, cmb_tipo.getBounds().height);
			}
		});
		
		btn_rc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				status = 3;	
				ripristina(listaRistoranti);
				btn_rn.setForeground(Color.GRAY);
				btn_rc.setForeground(Color.DARK_GRAY);
				btn_rt.setForeground(Color.GRAY);
				btn_tc.setForeground(Color.GRAY);
				txt_r1.setVisible(true);
				txt_r1.setText("");
				txt_r2.setVisible(false);
				txt_r2.setText("");
				lbl_r1.setText("Comune:");
				lbl_r2.setVisible(false);
				cmb_tipo.setVisible(false);
				cmb_tipo.setBounds(cmb_tipo.getBounds().x, cmb_tipo.getBounds().y, cmb_tipo_w, cmb_tipo.getBounds().height);
			}
		});
		
		btn_tc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {	
				status = 4;
				ripristina(listaRistoranti);
				btn_rn.setForeground(Color.GRAY);
				btn_rc.setForeground(Color.GRAY);
				btn_rt.setForeground(Color.GRAY);
				btn_tc.setForeground(Color.DARK_GRAY);
				txt_r1.setVisible(false);
				txt_r1.setText("");
				txt_r2.setVisible(true);
				txt_r2.setText("");
				lbl_r1.setText("Tipologia:");
				lbl_r2.setVisible(true);
				cmb_tipo.setVisible(true);
				cmb_tipo.setBounds(cmb_tipo.getBounds().x, cmb_tipo.getBounds().y, cmb_tipo_w / 2, cmb_tipo.getBounds().height);
			}
		});
		
		btn_rip = new JButton("RIPRISTINA");
		btn_rip.setForeground(Color.GRAY);
		btn_rip.setBackground(Color.WHITE);
		btn_rip.setBounds(15, (int)(this.getHeight() * 0.31), (int)(this.getWidth() * 0.19 - 20), (int)(this.getHeight() * 0.065));
		btn_rip.setFont(new Font("Helvetica", Font.BOLD, 15));
		btn_rip.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pnl_sx.add(btn_rip);
		
		btn_rip.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ripristina(listaRistoranti);
			}
		});
		
		btn_fil = new JButton("FILTRA");
		btn_fil.setForeground(Color.ORANGE);
		btn_fil.setBackground(Color.WHITE);
		btn_fil.setBounds((int)(this.getWidth() * 0.19 + 5), (int)(this.getHeight() * 0.31), (int)(this.getWidth() * 0.19 - 20), (int)(this.getHeight() * 0.065));
		btn_fil.setFont(new Font("Helvetica", Font.BOLD, 15));
		btn_fil.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pnl_sx.add(btn_fil);
		
		btn_fil.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				switch(status) {
				case 1:
					listaRistorantiFiltrata = filtraPerNome(txt_r1.getText(), listaRistoranti);		
					break;
				case 2:
					listaRistorantiFiltrata = filtraPerTipologia((Tipologia)cmb_tipo.getSelectedItem(), listaRistoranti);
					break;
				case 3:
					listaRistorantiFiltrata = filtraPerComune(txt_r1.getText(), listaRistoranti);
					break;
				case 4:
					listaRistorantiFiltrata = filtraPerTipologiaComune((Tipologia)cmb_tipo.getSelectedItem(), txt_r2.getText(), listaRistoranti);
					break;
				default:
					break;
				}
				
			}
		});
		
		pnl_ctab = new JPanel();
		pnl_ctab.setBackground(Color.DARK_GRAY);
		pnl_ctab.setLayout(null);
		pnl_ctab.setBounds(15, (int)(this.getHeight() * 0.39), (int)(this.getWidth() * 0.38 - 30), (int)(this.getHeight() * 0.48));
		pnl_sx.add(pnl_ctab);
		
		tbl_rist = new JTable() {
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) {                
				return false;               
		};};
		modello.setColumnIdentifiers(colonne);
		tbl_rist.setModel(modello);
		tbl_rist.setBackground(Color.WHITE);
		tbl_rist.setForeground(Color.BLACK);
		tbl_rist.setFont(new Font("Helvetica", Font.BOLD, 15));
		tbl_rist.setRowHeight(20);
		
		spnl_tab = new JScrollPane();
		spnl_tab.setBounds(0, 0, (int)(this.getWidth() * 0.38 - 30), (int)(this.getHeight() * 0.48));;
		spnl_tab.setBackground(Color.DARK_GRAY);
		pnl_ctab.add(spnl_tab);
		spnl_tab.setViewportView(tbl_rist);
		caricaTabella(tbl_rist, listaRistoranti, modello, attr);
		
		btn_sel = new JButton("VISUALIZZA");
		btn_sel.setForeground(Color.ORANGE);
		btn_sel.setBackground(Color.WHITE);
		btn_sel.setBounds((int)(this.getWidth() * 0.12), (int)(this.getHeight() * 0.88), (int)(this.getWidth() * 0.14), (int)(this.getHeight() * 0.06));
		btn_sel.setFont(new Font("Helvetica", Font.BOLD, 15));
		btn_sel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pnl_sx.add(btn_sel);
		
		btn_sel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(tbl_rist.getSelectionModel().isSelectionEmpty())
					return;
				ristorante = seleziona(listaRistorantiFiltrata, tbl_rist.getSelectedRow());
				popolaPannelloDx(ristorante);
				caricaGiudizi(ristorante);
				if(cliente != null && (trovaGiudizio(ristorante, cliente) == false)) 
					attivaDisattivaPannello(pnl_giud, true);
				else
					attivaDisattivaPannello(pnl_giud, false);
					
			}
		});
		
		pnl_dx = new JPanel();
		pnl_dx.setBackground(Color.WHITE);
		pnl_dx.setLayout(null);
		pnl_dx.setBounds((int)(this.getWidth() * 0.38), 0, (int)(this.getWidth() * 0.62) + 5, (int)(this.getHeight()));
		pannello.add(pnl_dx);
		
		lbl_rnome = new JLabel("EatAdvisor");
		lbl_rnome.setForeground(Color.DARK_GRAY);
		lbl_rnome.setFont(new Font("Helvetica", Font.BOLD, 35));
		lbl_rnome.setBounds((int)(this.getWidth() * 0.025), (int)(this.getHeight() * 0.025), (int)(this.getWidth() * 0.49), (int)(this.getHeight() * 0.075));
		pnl_dx.add(lbl_rnome);
		
		lbl_rtipo = new JLabel("Seleziona un ristorante per vedere le info.");
		lbl_rtipo.setForeground(Color.DARK_GRAY);
		lbl_rtipo.setFont(new Font("Helvetica", Font.BOLD, 15));
		lbl_rtipo.setBounds((int)(this.getWidth() * 0.025), (int)(this.getHeight() * 0.11), (int)(this.getWidth() * 0.49), (int)(this.getHeight() * 0.022));
		pnl_dx.add(lbl_rtipo);
		
		lbl_rind = new JLabel();
		lbl_rind.setForeground(Color.DARK_GRAY);
		lbl_rind.setFont(new Font("Helvetica", Font.BOLD, 15));
		lbl_rind.setBounds((int)(this.getWidth() * 0.025), (int)(this.getHeight() * 0.14), (int)(this.getWidth() * 0.49), (int)(this.getHeight() * 0.022));
		pnl_dx.add(lbl_rind);
		
		lbl_rtel = new JLabel();
		lbl_rtel.setForeground(Color.DARK_GRAY);
		lbl_rtel.setFont(new Font("Helvetica", Font.BOLD, 15));
		lbl_rtel.setBounds((int)(this.getWidth() * 0.025), (int)(this.getHeight() * 0.17), (int)(this.getWidth() * 0.49), (int)(this.getHeight() * 0.022));
		pnl_dx.add(lbl_rtel);
		
		lbl_rurl = new JLabel();
		lbl_rurl.setForeground(Color.DARK_GRAY);
		lbl_rurl.setFont(new Font("Helvetica", Font.BOLD, 15));
		lbl_rurl.setBounds((int)(this.getWidth() * 0.025), (int)(this.getHeight() * 0.2), (int)(this.getWidth() * 0.49), (int)(this.getHeight() * 0.022));
		pnl_dx.add(lbl_rurl);
		
		lbl_v1 = new JLabel("1 su 5: -");
		lbl_v1.setForeground(Color.DARK_GRAY);
		lbl_v1.setFont(new Font("Helvetica", Font.BOLD, 15));
		lbl_v1.setBounds((int)(this.getWidth() * 0.5), (int)(this.getHeight() * 0.175), (int)(this.getWidth() * 0.305), (int)(this.getHeight() * 0.022));
		pnl_dx.add(lbl_v1);
		
		lbl_v2 = new JLabel("2 su 5: -");
		lbl_v2.setForeground(Color.DARK_GRAY);
		lbl_v2.setFont(new Font("Helvetica", Font.BOLD, 15));
		lbl_v2.setBounds((int)(this.getWidth() * 0.5), (int)(this.getHeight() * 0.145), (int)(this.getWidth() * 0.305), (int)(this.getHeight() * 0.022));
		pnl_dx.add(lbl_v2);
		
		lbl_v3 = new JLabel("3 su 5: -");
		lbl_v3.setForeground(Color.DARK_GRAY);
		lbl_v3.setFont(new Font("Helvetica", Font.BOLD, 15));
		lbl_v3.setBounds((int)(this.getWidth() * 0.5), (int)(this.getHeight() * 0.115), (int)(this.getWidth() * 0.305), (int)(this.getHeight() * 0.022));
		pnl_dx.add(lbl_v3);
		
		lbl_v4 = new JLabel("4 su 5: -");
		lbl_v4.setForeground(Color.DARK_GRAY);
		lbl_v4.setFont(new Font("Helvetica", Font.BOLD, 15));
		lbl_v4.setBounds((int)(this.getWidth() * 0.5), (int)(this.getHeight() * 0.085), (int)(this.getWidth() * 0.305), (int)(this.getHeight() * 0.022));
		pnl_dx.add(lbl_v4);
		
		lbl_v5 = new JLabel("5 su 5: -");
		lbl_v5.setForeground(Color.DARK_GRAY);
		lbl_v5.setFont(new Font("Helvetica", Font.BOLD, 15));
		lbl_v5.setBounds((int)(this.getWidth() * 0.5), (int)(this.getHeight() * 0.055), (int)(this.getWidth() * 0.22), (int)(this.getHeight() * 0.022));
		pnl_dx.add(lbl_v5);
		
		lbl_gtit = new JLabel();
		lbl_gtit.setForeground(Color.LIGHT_GRAY);
		lbl_gtit.setFont(new Font("Helvetica", Font.BOLD, 15));
		lbl_gtit.setBounds((int)(this.getWidth() * 0.025), (int)(this.getHeight() * 0.27), (int)(this.getWidth() * 0.57), (int)(this.getHeight() * 0.022));
		pnl_dx.add(lbl_gtit);
		if(cliente == null)
			lbl_gtit.setText("Giudizi:");
		
		pnl_cgiud = new JPanel();
		pnl_cgiud.setBackground(Color.WHITE);
		pnl_cgiud.setBounds((int)(this.getWidth() * 0.025), (int)(this.getHeight() * 0.29), (int)(this.getWidth() * 0.57), (int)(this.getHeight() * 0.36));
		pnl_dx.add(pnl_cgiud);
		
		lbls_giu = new JLabel();
		lbls_giu.setForeground(Color.DARK_GRAY);
		lbls_giu.setFont(new Font("Helvetica", Font.BOLD, 15));
		lbls_giu.setVerticalAlignment(JLabel.TOP);
		lbls_giu.setVerticalTextPosition(JLabel.TOP);
		lbls_giu.setBorder(BorderFactory.createMatteBorder(15, 15, 15, 15, Color.WHITE));
		
		spnl_giud = new JScrollPane();
		spnl_giud.setPreferredSize(new Dimension((int)(this.getWidth() * 0.57), (int)(this.getHeight() * 0.35)));
		spnl_giud.setBackground(Color.WHITE);
		spnl_giud.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(240, 240, 240)));
		pnl_cgiud.add(spnl_giud);
		spnl_giud.setViewportView(lbls_giu);
		
		pnl_giud = new JPanel();
		pnl_giud.setBackground(new Color(245, 245, 245));
		pnl_giud.setLayout(null);
		pnl_giud.setBounds(0, (int)(this.getHeight() * 0.7), (int)(this.getWidth()), (int)(this.getHeight() * 0.3));
		pnl_dx.add(pnl_giud);
		
		attivaDisattivaPannello(pnl_dx, false);
		attivaDisattivaPannello(pnl_cgiud, false);
		
		lbl_gvoto = new JLabel("<html><center>Aggiungi un giudizio al ristorante selezionato</center></html>", SwingConstants.CENTER);
		lbl_gvoto.setForeground(Color.GRAY);
		lbl_gvoto.setFont(new Font("Helvetica", Font.BOLD, 18));
		lbl_gvoto.setBounds(0, (int)(this.getHeight() * 0.005), (int)(this.getWidth() * 0.62), (int)(this.getHeight() * 0.05));
		pnl_giud.add(lbl_gvoto);
		
		lbl_gvoto = new JLabel("Valutazione:");
		lbl_gvoto.setForeground(Color.LIGHT_GRAY);
		lbl_gvoto.setFont(new Font("Helvetica", Font.BOLD, 15));
		lbl_gvoto.setBounds((int)(this.getWidth() * 0.05), (int)(this.getHeight() * 0.06), (int)(this.getWidth() * 0.1), (int)(this.getHeight() * 0.04));
		pnl_giud.add(lbl_gvoto);
		
		lbl_gdesc = new JLabel("Descrizione breve:");
		lbl_gdesc.setForeground(Color.LIGHT_GRAY);
		lbl_gdesc.setFont(new Font("Helvetica", Font.BOLD, 15));
		lbl_gdesc.setBounds((int)(this.getWidth() * 0.15 - 1), (int)(this.getHeight() * 0.06), (int)(this.getWidth() * 0.5 - 15), (int)(this.getHeight() * 0.04));
		pnl_giud.add(lbl_gdesc);
		
		cmb_val = new JComboBox<Integer>();
		cmb_val.setBackground(Color.WHITE);
		cmb_val.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 0, Color.LIGHT_GRAY));
		cmb_val.setBounds((int)(this.getWidth() * 0.05), (int)(this.getHeight() * 0.1), (int)(this.getWidth() * 0.1), (int)(this.getHeight() * 0.065));
		cmb_val.setFont(new Font("Helvetica", Font.BOLD, 18));
		pnl_giud.add(cmb_val);
		for(int i=1; i<=5; i++)
			cmb_val.addItem(i);
		
		txt_giu = new JTextField();
		txt_giu.setForeground(Color.BLACK);
		txt_giu.setBackground(Color.WHITE);
		txt_giu.setBorder(BorderFactory.createMatteBorder(2, 0, 2, 2, Color.LIGHT_GRAY));
		txt_giu.setBounds((int)(this.getWidth() * 0.15 - 1), (int)(this.getHeight() * 0.1), (int)(this.getWidth() * 0.42), (int)(this.getHeight() * 0.065));
		txt_giu.setFont(new Font("Helvetica", Font.BOLD, 14));
		pnl_giud.add(txt_giu);
		
		btn_giu = new JButton("CONFERMA IL GIUDIZIO");
		btn_giu.setForeground(Color.ORANGE);
		btn_giu.setBackground(Color.WHITE);
		btn_giu.setBounds((int)(this.getWidth() * 0.21), (int)(this.getHeight() * 0.175), (int)(this.getWidth() * 0.22), (int)(this.getHeight() * 0.06));
		btn_giu.setFont(new Font("Helvetica", Font.BOLD, 15));
		btn_giu.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pnl_giud.add(btn_giu);
		
		btn_giu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(txt_giu.getText().equals(null) || txt_giu.getText().equals("")) {
					txt_giu.setBackground(Color.PINK);
					return;
				}
				txt_giu.setBackground(Color.WHITE);
				
				Giudizio giudizio = new Giudizio(
						Integer.parseInt(cmb_val.getSelectedItem().toString()), 
						txt_giu.getText(), 
						cliente, 
						ristorante
						);
				
				file.scrivi(giudizio, false);
				txt_giu.setText("");
				cmb_val.setSelectedIndex(0);
				attivaDisattivaPannello(pnl_giud, false);
				caricaGiudizi(ristorante);
			}
		});
		
		attivaDisattivaPannello(pnl_giud, false);
		
	}
	
	/** Ritorna il cliente che ha eseguito l'accesso all'applicazione clienti
	 * @return Un oggetto di tipo Cliente che rappresenta il cliente che ha eseguito l'accesso
	*/
	public Cliente getCliente() {
		return cliente;
	}

	/** Ritorna il file sul quale si stanno svolgendo le operazioni
	*/
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	private Ristorante seleziona(ArrayList<Object> lista, int posizione) {
		return (Ristorante)lista.get(posizione);
	}
	
	private void attivaDisattivaPannello(JPanel pannello, boolean flag) {
		Component[] comp = pannello.getComponents();
		for(int i = 0; i < comp.length; i++) 
			if(!(comp[i] instanceof JPanel))
		     comp[i].setEnabled(flag);
	}
	
	private void caricaTabella(JTable tabella, ArrayList<Object> lista, DefaultTableModel modello, Object []attributi) {
		Ristorante ristorante;
		for(int i=0; i<lista.size(); i++) {
			ristorante = (Ristorante)lista.get(i);
			System.out.println(ristorante.toString());
			
			attr[0] = ristorante.getNome();
			attr[1] = ristorante.getTipologia().toString();
			attr[2] = ristorante.getIndirizzo().getComune();
			modello.addRow(attr);
		}
	}
	
	private void caricaGiudizi(Ristorante ristorante) {
		String testo = "<html><p style='width: " + (int)(this.getWidth() * 0.57 - 60) + "'>";
		ArrayList<Object> giudizi = file.filtraPerTipo(Giudizio.class); 
		Giudizio giudizio;
		int v1 = 0, v2 = 0, v3 = 0, v4 = 0, v5 = 0;
		Cliente c;
		
		if(cliente == null)
			c = new Cliente("", "", "", "", "", "", "");
		else
			c = cliente;
		
		for(int i=0; i<giudizi.size(); i++) {
			giudizio = (Giudizio)giudizi.get(i);
			if(giudizio.getRistorante().toString().equals(ristorante.toString())) {
				if(giudizio.getCliente().toString().equals(c.toString()))
					testo += "<font color='rgb(255,204,0)'>";
				testo += "<u>" + giudizio.getCliente().getNickname() + "</u><br>" +
						giudizio.getGiudizioNumerico() + " su 5<br>''" +
						giudizio.getGiudizioTestuale() + "''<br><br>";
				if(giudizio.getCliente().toString().equals(c.toString()))
					testo += "</p></font>";
				
				switch(giudizio.getGiudizioNumerico()) {
				case 1:
					v1++;
					break;
				case 2:
					v2++;
					break;
				case 3:
					v3++;
					break;
				case 4:
					v4++;
					break;
				case 5:
					v5++;
					break;
				default:
					break;
				}
			}
		}
		testo += "</html>";

		lbls_giu.setText(testo);
		lbl_v1.setText("1 su 5:     " + v1);
		lbl_v2.setText("2 su 5:     " + v2);
		lbl_v3.setText("3 su 5:     " + v3);
		lbl_v4.setText("4 su 5:     " + v4);
		lbl_v5.setText("5 su 5:     " + v5);
	}
	
	private boolean trovaGiudizio(Ristorante ristorante, Cliente cliente) {
		ArrayList<Object> giudizi = file.filtraPerTipo(Giudizio.class); 
		Giudizio giudizio;
		
		for(int i=0; i<giudizi.size(); i++) {
			giudizio = (Giudizio)giudizi.get(i);
			System.out.println(giudizio);
			if(giudizio.getRistorante().toString().equals(ristorante.toString()) && giudizio.getCliente().toString().equals(cliente.toString()))
				return true;
		}
			return false;
	}
	
	private void popolaPannelloDx(Ristorante ristorante) {
		attivaDisattivaPannello(pnl_dx, true);
		attivaDisattivaPannello(pnl_cgiud, true);
		if(cliente != null)
			attivaDisattivaPannello(pnl_giud, true);
		
		lbl_gtit.setText("Giudizi per il ristorante '" + ristorante.getNome() + "':");
		lbl_rnome.setText(ristorante.getNome());
		lbl_rtipo.setText(ristorante.getTipologia().toString());
		lbl_rind.setText(ristorante.getIndirizzo().toString());
		lbl_rtel.setText(ristorante.getTelefono());
		lbl_rurl.setText(ristorante.getUrl());
	}
	
	private void ripristina(ArrayList<Object> lista) {
		modello.setRowCount(0);
		caricaTabella(tbl_rist, lista, modello, attr);
		listaRistorantiFiltrata = listaRistoranti;
	}
	
	private ArrayList<Object> filtraPerNome(String nome, ArrayList<Object> lista) {
		if(nome.equals("") || nome.equals(null))
			return lista;
		
		Ristorante ristorante;
		ArrayList<Object> listaNome = new ArrayList<Object>();
		
		for(int i=0; i<lista.size(); i++) {
			ristorante = (Ristorante)lista.get(i);
			if(ristorante.getNome().toLowerCase().contains(nome.toLowerCase()))
				listaNome.add(ristorante);
		}
		
		if(modello.getRowCount() > 0)
			modello.setRowCount(0);
		
		caricaTabella(tbl_rist, listaNome, modello, attr);
		return listaNome;
	}
	
	private ArrayList<Object> filtraPerComune(String comune, ArrayList<Object> lista) {
		if(comune.equals("") || comune.equals(null))
			return lista;
		
		Ristorante ristorante;
		ArrayList<Object> listaComune = new ArrayList<Object>();
		
		for(int i=0; i<lista.size(); i++) {
			ristorante = (Ristorante)lista.get(i);
			if(ristorante.getIndirizzo().getComune().toLowerCase().contains(comune.toLowerCase()))
				listaComune.add(ristorante);
		}
		
		if(modello.getRowCount() > 0)
			modello.setRowCount(0);
		
		caricaTabella(tbl_rist, listaComune, modello, attr);
		return listaComune;
	}
	
	private ArrayList<Object> filtraPerTipologia(Tipologia tipologia, ArrayList<Object> lista) {
		Ristorante ristorante;
		ArrayList<Object> listaTipo = new ArrayList<Object>();
		
		for(int i=0; i<lista.size(); i++) {
			ristorante = (Ristorante)lista.get(i);
			if(ristorante.getTipologia().equals(tipologia))
				listaTipo.add(ristorante);
		}
		
		if(modello.getRowCount() > 0)
			modello.setRowCount(0);
		
		caricaTabella(tbl_rist, listaTipo, modello, attr);
		return listaTipo;
	}
	
	private ArrayList<Object> filtraPerTipologiaComune(Tipologia tipologia, String comune, ArrayList<Object> lista) {
		if(comune.equals("") || comune.equals(null))
			return lista;
		Ristorante ristorante;
		ArrayList<Object> listaTC = new ArrayList<Object>();
		
		for(int i=0; i<lista.size(); i++) {
			ristorante = (Ristorante)lista.get(i);
			if(ristorante.getTipologia().equals(tipologia) && ristorante.getIndirizzo().getComune().toLowerCase().contains(comune.toLowerCase()))
				listaTC.add(ristorante);
		}
		
		if(modello.getRowCount() > 0)
			modello.setRowCount(0);
		
		caricaTabella(tbl_rist, listaTC, modello, attr);
		return listaTC;
	}

}
