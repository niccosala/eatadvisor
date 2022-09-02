package clienti;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import condivisa.AzioneFile;
import condivisa.InfoGui;

/** Rappresenta la GUI dedicata all'accesso dell'applicazione clienti.
 * @author Niccolò Sala
 * @version 1.0
*/
public class GuiAccesso extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private final int LAR_UNIT = InfoGui.getUnitaLarghezza();
	private final int ALT_UNIT = InfoGui.getUnitaAltezza();
	private final int ALT_COST = 83;
	private int LAR_COST = 40;
	private final int EO_SPAZIO = InfoGui.getSpazioEO(LAR_COST);
	private final int NS_SPAZIO = InfoGui.getSpazioNS(ALT_COST);
	
	private JPanel pannello, pnl_acc, pnl_reg;
	private JLabel lbl_tit, lbl_imm, lbl_user, lbl_osp, lbl_lnome, lbl_lcog, lbl_lmail, lbl_lcom, lbl_lsig, lbl_luser, lbl_lpass, lbl_err;
	private JButton btn_acc, btn_reg, btn_aut, btn_nreg;
	private JTextField txt_user, txt_pw, txt_lnome, txt_lcog, txt_lmail, txt_lcom, txt_lsig, txt_luser, txt_lpass;
	private ImageIcon img = new ImageIcon(this.getClass().getResource("icona_s.png"));
	
	private AzioneFile file = new AzioneFile("Utenti");
	private Cliente cliente;
	
	/** Crea e inzializza i componenti della GUI dedicata all'accesso dell'applicazione clienti.
	*/
	public GuiAccesso() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		if(((float)InfoGui.getLarghezza() / (float)InfoGui.getAltezza()) > 1.61) 
			LAR_COST = 37;
		
		setVisible(true);
		setTitle("EatAdvisor: Clienti");
		setSize(LAR_UNIT * LAR_COST, ALT_UNIT * ALT_COST);
		setLocation(EO_SPAZIO, NS_SPAZIO);
		setBackground(Color.DARK_GRAY);
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("icona_s.png")));
		
		pannello = new JPanel();
		pannello.setBackground(Color.DARK_GRAY);
		pannello.setBorder(new EmptyBorder(5, 5, 5, 5));
		pannello.setLayout(null);
		setContentPane(pannello);
		
		lbl_imm = new JLabel(img, SwingConstants.CENTER);
		lbl_imm.setBounds(0, 5, (int)(this.getWidth()), (int)(this.getHeight() * 0.235));
		lbl_imm.setVisible(true);
		pannello.add(lbl_imm);
		
		lbl_tit = new JLabel("EatAdvisor", SwingConstants.CENTER);
		lbl_tit.setForeground(Color.WHITE);
		lbl_tit.setFont(new Font("Helvetica", Font.BOLD, 40));
		lbl_tit.setBounds(0, (int)(this.getHeight() * 0.235), this.getWidth(), (int)(this.getHeight() * 0.1));
		pannello.add(lbl_tit);
		
		btn_acc = new JButton("ACCEDI");
		btn_acc.setForeground(Color.DARK_GRAY);
		btn_acc.setBackground(Color.WHITE);
		btn_acc.setBounds((int)(this.getWidth() / 4) - 10, (int)(this.getHeight() * 0.335), (int)(this.getWidth() / 4), (int)(this.getHeight() * 0.075));
		btn_acc.setFont(new Font("Helvetica", Font.BOLD, 18));
		btn_acc.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pannello.add(btn_acc);
		
		btn_reg = new JButton("REGISTRATI");
		btn_reg.setForeground(Color.GRAY);
		btn_reg.setBackground(Color.WHITE);
		btn_reg.setBounds((int)(this.getWidth() / 2) + 10, (int)(this.getHeight() * 0.335), (int)(this.getWidth() / 4), (int)(this.getHeight() * 0.075));
		btn_reg.setFont(new Font("Helvetica", Font.BOLD, 18));
		btn_reg.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btn_reg.setVisible(true);
		pannello.add(btn_reg);
		
		btn_acc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btn_acc.setForeground(Color.DARK_GRAY);
				btn_reg.setForeground(Color.GRAY);
				pnl_acc.setVisible(true);
				pnl_reg.setVisible(false);
			}
		});
		
		pnl_acc = new JPanel();
		pnl_acc.setBackground(Color.WHITE);
		pnl_acc.setLayout(null);
		pnl_acc.setBounds(0, (int)(this.getHeight() * 0.435), (int)(this.getWidth()), (int)(this.getHeight() * 0.665));
		pnl_acc.setBorder(BorderFactory.createMatteBorder(5, 0, 0, 0, Color.ORANGE));
		pannello.add(pnl_acc);
		
		lbl_user = new JLabel("Nickname:");
		lbl_user.setForeground(Color.DARK_GRAY);
		lbl_user.setBounds((int)(this.getWidth() * 0.2), (int)(this.getHeight() * 0.05), (int)(this.getWidth() * 0.6), (int)(this.getHeight() * 0.035));
		lbl_user.setFont(new Font("Helvetica", Font.BOLD, 18));
		pnl_acc.add(lbl_user);
		
		txt_user = new JTextField();
		txt_user.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.DARK_GRAY));
		txt_user.setBounds((int)(this.getWidth() * 0.2), (int)(this.getHeight() * 0.09), (int)(this.getWidth() * 0.6), (int)(this.getHeight() * 0.06));
		txt_user.setFont(new Font("Helvetica", Font.BOLD, 18));
		pnl_acc.add(txt_user);
		
		lbl_user = new JLabel("Password:");
		lbl_user.setForeground(Color.DARK_GRAY);
		lbl_user.setBounds((int)(this.getWidth() * 0.2), (int)(this.getHeight() * 0.19), (int)(this.getWidth() * 0.6), (int)(this.getHeight() * 0.035));
		lbl_user.setFont(new Font("Helvetica", Font.BOLD, 18));
		pnl_acc.add(lbl_user);
		
		txt_pw = new JTextField();
		txt_pw.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.DARK_GRAY));
		txt_pw.setBounds((int)(this.getWidth() * 0.2), (int)(this.getHeight() * 0.23), (int)(this.getWidth() * 0.6), (int)(this.getHeight() * 0.06));
		txt_pw.setFont(new Font("Helvetica", Font.BOLD, 18));
		pnl_acc.add(txt_pw);
		
		lbl_osp = new JLabel("<html><center><u>ACCEDI COME OSPITE</u></center></html>");
		lbl_osp.setForeground(Color.DARK_GRAY);
		lbl_osp.setBounds((int)(this.getWidth() / 4) - 10, (int)(this.getHeight() * 0.4), (int)(this.getWidth() / 4), (int)(this.getHeight() * 0.075));
		lbl_osp.setFont(new Font("Helvetica", Font.BOLD, 18));
		lbl_osp.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pnl_acc.add(lbl_osp);
		
		btn_aut = new JButton("ACCEDI");
		btn_aut.setForeground(Color.ORANGE);
		btn_aut.setBackground(Color.WHITE);
		btn_aut.setBounds((int)(this.getWidth() / 2) + 10, (int)(this.getHeight() * 0.4), (int)(this.getWidth() / 4), (int)(this.getHeight() * 0.075));
		btn_aut.setFont(new Font("Helvetica", Font.BOLD, 18));
		btn_aut.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pnl_acc.add(btn_aut);
		
		lbl_osp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							GuiClienti window = new GuiClienti(null);
							window.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				dispose();
			}
		});
		
		btn_aut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cliente = controllaInputAccesso();
				System.out.println(cliente);
				if(cliente != null) {
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								GuiClienti finestra = new GuiClienti(cliente);
								finestra.setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
					dispose();
				}
			}
		});
		
		
		btn_reg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btn_acc.setForeground(Color.GRAY);
				btn_reg.setForeground(Color.DARK_GRAY);
				pnl_acc.setVisible(false);
				pnl_reg.setVisible(true);
			}
		});
		
		pnl_reg = new JPanel();
		pnl_reg.setBackground(Color.WHITE);
		pnl_reg.setLayout(null);
		pnl_reg.setBounds(0, (int)(this.getHeight() * 0.435), (int)(this.getWidth()), (int)(this.getHeight() * 0.665));
		pnl_reg.setBorder(BorderFactory.createMatteBorder(5, 0, 0, 0, Color.ORANGE));
		pannello.add(pnl_reg);
		pnl_reg.setVisible(false);
		
		lbl_err = new JLabel();
		lbl_err.setForeground(Color.DARK_GRAY);
		lbl_err.setBounds((int)(this.getWidth() * 0.7), 3, (int)(this.getWidth() * 0.3), (int)(this.getHeight() * 0.025));
		lbl_err.setFont(new Font("Helvetica", Font.BOLD, 12));
		pnl_reg.add(lbl_err);
		
		lbl_lnome = new JLabel("Nome:");
		lbl_lnome.setForeground(Color.DARK_GRAY);
		lbl_lnome.setBounds(30, (int)(this.getHeight() * 0.03), (int)(this.getWidth() * 0.5 - 40), (int)(this.getHeight() * 0.035));
		lbl_lnome.setFont(new Font("Helvetica", Font.BOLD, 18));
		pnl_reg.add(lbl_lnome);
		
		txt_lnome = new JTextField();
		txt_lnome.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.DARK_GRAY));
		txt_lnome.setBounds(30, (int)(this.getHeight() * 0.07), (int)(this.getWidth() * 0.5 - 40), (int)(this.getHeight() * 0.06));
		txt_lnome.setFont(new Font("Helvetica", Font.BOLD, 18));
		pnl_reg.add(txt_lnome);
		
		lbl_lcog = new JLabel("Cognome:");
		lbl_lcog.setForeground(Color.DARK_GRAY);
		lbl_lcog.setBounds((int)(this.getWidth() * 0.5 + 10), (int)(this.getHeight() * 0.03), (int)(this.getWidth() * 0.5 - 40), (int)(this.getHeight() * 0.035));
		lbl_lcog.setFont(new Font("Helvetica", Font.BOLD, 18));
		pnl_reg.add(lbl_lcog);
		
		txt_lcog = new JTextField();
		txt_lcog.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.DARK_GRAY));
		txt_lcog.setBounds((int)(this.getWidth() * 0.5 + 10), (int)(this.getHeight() * 0.07), (int)(this.getWidth() * 0.5 - 40), (int)(this.getHeight() * 0.06));
		txt_lcog.setFont(new Font("Helvetica", Font.BOLD, 18));
		pnl_reg.add(txt_lcog);
		
		lbl_lmail = new JLabel("Mail:");
		lbl_lmail.setForeground(Color.DARK_GRAY);
		lbl_lmail.setBounds(30, (int)(this.getHeight() * 0.15), (int)(this.getWidth() * 0.5 - 40), (int)(this.getHeight() * 0.035));
		lbl_lmail.setFont(new Font("Helvetica", Font.BOLD, 18));
		pnl_reg.add(lbl_lmail);
		
		txt_lmail = new JTextField();
		txt_lmail.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.DARK_GRAY));
		txt_lmail.setBounds(30, (int)(this.getHeight() * 0.19), (int)(this.getWidth() * 0.5 - 40), (int)(this.getHeight() * 0.06));
		txt_lmail.setFont(new Font("Helvetica", Font.BOLD, 18));
		pnl_reg.add(txt_lmail);
		
		lbl_lcom = new JLabel("Comune:");
		lbl_lcom.setForeground(Color.DARK_GRAY);
		lbl_lcom.setBounds((int)(this.getWidth() * 0.5 + 10), (int)(this.getHeight() * 0.15), (int)(this.getWidth() * 0.35 - 20), (int)(this.getHeight() * 0.035));
		lbl_lcom.setFont(new Font("Helvetica", Font.BOLD, 18));
		pnl_reg.add(lbl_lcom);
		
		txt_lcom = new JTextField();
		txt_lcom.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 0, Color.DARK_GRAY));
		txt_lcom.setBounds((int)(this.getWidth() * 0.5 + 10), (int)(this.getHeight() * 0.19), (int)(this.getWidth() * 0.35 - 20), (int)(this.getHeight() * 0.06));
		txt_lcom.setFont(new Font("Helvetica", Font.BOLD, 18));
		pnl_reg.add(txt_lcom);
		
		lbl_lsig = new JLabel("Prov.:");
		lbl_lsig.setForeground(Color.DARK_GRAY);
		lbl_lsig.setBounds((int)(this.getWidth() * 0.85 - 10), (int)(this.getHeight() * 0.15), (int)(this.getWidth() * 0.15 - 20), (int)(this.getHeight() * 0.035));
		lbl_lsig.setFont(new Font("Helvetica", Font.BOLD, 18));
		pnl_reg.add(lbl_lsig);
		
		txt_lsig = new JTextField();
		txt_lsig.setBorder(BorderFactory.createMatteBorder(2, 0, 2, 2, Color.DARK_GRAY));
		txt_lsig.setBounds((int)(this.getWidth() * 0.85 - 10), (int)(this.getHeight() * 0.19), (int)(this.getWidth() * 0.15 - 20), (int)(this.getHeight() * 0.06));
		txt_lsig.setFont(new Font("Helvetica", Font.BOLD, 18));
		pnl_reg.add(txt_lsig);
		
		lbl_luser = new JLabel("Nickname:");
		lbl_luser.setForeground(Color.DARK_GRAY);
		lbl_luser.setBounds(30, (int)(this.getHeight() * 0.27), (int)(this.getWidth() * 0.5 - 40), (int)(this.getHeight() * 0.035));
		lbl_luser.setFont(new Font("Helvetica", Font.BOLD, 18));
		pnl_reg.add(lbl_luser);
		
		txt_luser = new JTextField();
		txt_luser.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.DARK_GRAY));
		txt_luser.setBounds(30, (int)(this.getHeight() * 0.31), (int)(this.getWidth() * 0.5 - 40), (int)(this.getHeight() * 0.06));
		txt_luser.setFont(new Font("Helvetica", Font.BOLD, 18));
		pnl_reg.add(txt_luser);
		
		lbl_lpass = new JLabel("Password:");
		lbl_lpass.setForeground(Color.DARK_GRAY);
		lbl_lpass.setBounds((int)(this.getWidth() * 0.5 + 10), (int)(this.getHeight() * 0.27), (int)(this.getWidth() * 0.5 - 40), (int)(this.getHeight() * 0.035));
		lbl_lpass.setFont(new Font("Helvetica", Font.BOLD, 18));
		pnl_reg.add(lbl_lpass);
		
		txt_lpass = new JTextField();
		txt_lpass.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.DARK_GRAY));
		txt_lpass.setBounds((int)(this.getWidth() * 0.5 + 10), (int)(this.getHeight() * 0.31), (int)(this.getWidth() * 0.5 - 40), (int)(this.getHeight() * 0.06));
		txt_lpass.setFont(new Font("Helvetica", Font.BOLD, 18));
		pnl_reg.add(txt_lpass);
		
		btn_nreg = new JButton("REGISTRATI");
		btn_nreg.setForeground(Color.ORANGE);
		btn_nreg.setBackground(Color.WHITE);
		btn_nreg.setBounds((int)(this.getWidth() * 0.3) , (int)(this.getHeight() * 0.418), (int)(this.getWidth() * 0.4), (int)(this.getHeight() * 0.075));
		btn_nreg.setFont(new Font("Helvetica", Font.BOLD, 18));
		btn_nreg.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pnl_reg.add(btn_nreg);
		
		btn_nreg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cliente = controllaInputRegistrazione();
				if(cliente != null) {
					file.scrivi(cliente, false);
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								GuiClienti finestra = new GuiClienti(cliente);
								finestra.setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
					dispose();
				}
			}
		});
		
	}
	
	private Cliente controllaInputAccesso() {
		boolean flag = true;
		
		if(txt_user.getText().equals("") | txt_user.getText().equals(null)) {
			flag = false;
			txt_user.setBackground(Color.PINK);
		} else 
			txt_user.setBackground(Color.WHITE);
		if(txt_pw.getText().equals("") || txt_pw.equals(null)) {
			flag = false;
			txt_pw.setBackground(Color.PINK);
		} else
			txt_pw.setBackground(Color.WHITE);
		
		if(flag) {
			Cliente c = trovaCliente(txt_user.getText(), txt_pw.getText());
			if(c == null) {
				flag = false;
				txt_user.setBackground(Color.PINK);
				txt_pw.setBackground(Color.PINK);
			} else 
				return c;
		}
		
		return null;
	}
	
	private Cliente controllaInputRegistrazione() {
		boolean flag = true;
		
		if(txt_lnome.getText().equals("") | txt_lnome.getText().equals(null)) {
			flag = false;
			txt_lnome.setBackground(Color.PINK);
		} else 
			txt_lnome.setBackground(Color.WHITE);
		if(txt_lcog.getText().equals("") || txt_lcog.equals(null)) {
			flag = false;
			txt_lcog.setBackground(Color.PINK);
		} else
			txt_lcog.setBackground(Color.WHITE);
		if(txt_lmail.getText().equals("") | txt_lmail.getText().equals(null)) {
			flag = false;
			txt_lmail.setBackground(Color.PINK);
		} else 
			txt_lmail.setBackground(Color.WHITE);
		if(txt_lcom.getText().equals("") || txt_lcom.equals(null)) {
			flag = false;
			txt_lcom.setBackground(Color.PINK);
		} else
			txt_lcom.setBackground(Color.WHITE);
		if(txt_lsig.getText().equals("") | txt_lsig.getText().equals(null) || txt_lsig.getText().length() != 2) {
			flag = false;
			txt_lsig.setBackground(Color.PINK);
		} else 
			txt_lsig.setBackground(Color.WHITE);
		if(txt_luser.getText().equals("") || txt_luser.equals(null)) {
			flag = false;
			txt_luser.setBackground(Color.PINK);
		} else
			txt_luser.setBackground(Color.WHITE);
		if(txt_lpass.getText().equals("") | txt_lpass.getText().equals(null)) {
			flag = false;
			txt_lpass.setBackground(Color.PINK);
		} else 
			txt_lpass.setBackground(Color.WHITE);
		
		if(trovaNickname(txt_luser.getText())) {
			flag = false;
			txt_luser.setBackground(Color.PINK);
			lbl_err.setText("Nickname già utilizzato");
		} else
			lbl_err.setText("");
		
		if(flag) {

			Cliente c = new Cliente(
					txt_lnome.getText().substring(0,1).toUpperCase() + txt_lnome.getText().substring(1).toLowerCase(), 
					txt_lcog.getText().substring(0,1).toUpperCase() + txt_lcog.getText().substring(1).toLowerCase(), 
					txt_lmail.getText().toLowerCase(), 
					txt_luser.getText().toLowerCase(), 
					txt_lpass.getText(), 
					txt_lcom.getText().substring(0,1).toUpperCase() + txt_lcom.getText().substring(1).toLowerCase(),  
					txt_lsig.getText().toUpperCase()
					);
			
			return c;
		}
		
		return null;
	}
	
	private Cliente trovaCliente(String nick, String pw) {
		
		try {
			FileInputStream fi = new FileInputStream(file.getFile());
			ObjectInputStream oi = new ObjectInputStream(fi);
			
			while(fi.available() > 0) {
				Object current = oi.readObject();
				if(current instanceof Cliente) 
					if(((Cliente) current).getNickname().toLowerCase().equals(nick.toLowerCase()) && ((Cliente) current).getPassword().equals(pw)) {
						oi.close();
						fi.close();
						return (Cliente)current;
					}
			}

			oi.close();
			fi.close();
		} catch(Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	private boolean trovaNickname(String nick) {
		
		try {
			FileInputStream fi = new FileInputStream(file.getFile());
			ObjectInputStream oi = new ObjectInputStream(fi);
			
			while(fi.available() > 0) {
				Object current = oi.readObject();
				if(current instanceof Cliente) 
					if(((Cliente) current).getNickname().toLowerCase().equals(nick.toLowerCase())) {
						oi.close();
						fi.close();
						return true;
					}
			}

			oi.close();
			fi.close();
		} catch(Exception e) {
			e.printStackTrace();
		}

		return false;
	}
	
}


