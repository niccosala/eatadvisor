package clienti;

import java.io.Serializable;

/** Rappresenta un cliente
 * @author Niccolò Sala
 * @version 1.0
*/
public class Cliente implements Serializable {

	private static final long serialVersionUID = -7216897694967325633L;
	
	private String nome, cognome, nickname, password, comune, sigla_provincia, email;
	
	/** Crea un cliente.
	 * @param nome Nome del cliente.
	 * @param cognome Cognome del cliente.
	 * @param email Email del cliente.
	 * @param nickname Nickname del cliente.
	 * @param password Password per l'accesso del cliente.
	 * @param comune Comune di residenza del cliente.
	 * @param sigla_provincia Sigla della provincia di residenza del cliente.
	*/
	public Cliente(String nome, String cognome, String email, String nickname, String password, String comune, String sigla_provincia) {
		this.nome = nome;	
		this.cognome = cognome;
		this.nickname = nickname;
		this.password = password;
		this.comune = comune;
		this.sigla_provincia = sigla_provincia;
		this.email = email;
	}
	
	/** Ritorna il nome del cliente.
	 * @return Una stringa che rappresenta il nome del cliente.
	*/
	public String getNome() {
		return nome;
	}
	
	/** Ritorna il cognome del cliente.
	 * @return Una stringa che rappresenta il cognome del cliente.
	*/
	public String getCognome() {
		return cognome;
	}
	
	/** Ritorna il nickname del cliente.
	 * @return Una stringa che rappresenta il nickname del cliente.
	*/
	public String getNickname() {
		return nickname;
	}
	
	/** Ritorna la password del cliente.
	 * @return Una stringa che rappresenta la password del cliente.
	*/
	public String getPassword() {
		return password;
	}
	
	/** Ritorna il comune di residenza del cliente.
	 * @return Una stringa che rappresenta il comune di residenza del cliente.
	*/
	public String getComune() {
		return comune;
	}
	
	/** Ritorna la sigla della provincia di residenza del cliente.
	 * @return Una stringa che rappresenta la sigla della provincia di residenza del cliente.
	*/
	public String getSiglaProvincia() {
		return sigla_provincia;
	}
	
	/** Ritorna la mail del cliente.
	 * @return Una stringa che rappresenta la mail del cliente.
	*/
	public String getSiglaEmail() {
		return email;
	}
	
	/** Ritorna tutti gli attributi del cliente.
	 * @return Una stringa che rappresenta l'insieme degli attributi del cliente.
	*/
	public String toString() {
		return "Nome: " + nome + "\n" +
				"Cognome: " + cognome + "\n" +
				"Nickname: " + nickname + "\n" +
				"Password: " + password + "\n" +
				"Email: " + email + "\n" +
				"Comune: " + comune + "\n" +
				"Sigla: " + sigla_provincia + "\n";
	}
	
}
