package condivisa;

import java.io.Serializable;

/** Rappresenta un ristorante
 * @author Niccolò Sala
 * @version 1.0
*/
public class Ristorante implements Serializable {
	
	private static final long serialVersionUID = 461385373360751967L;

	/** Rappresenta una tipologia del ristorante
	 * @author Niccolò Sala
	 * @version 1.0
	*/
	public enum Tipologia {
		Italiano,
		Etnico,
		Fusion
	}
	
	private String nome, url_sito, telefono;
	private Indirizzo indirizzo;
	private Tipologia tipologia;
	
	/** Crea un cliente.
	 * @param nome Nome del ristorante.
	 * @param url_sito URL del sito web ristorante.
	 * @param telefono Telefono del ristorante.
	 * @param indirizzo Indirizzo del ristorante.
	 * @param tipologia Tipologia del ristorante.
	 * @param comune Comune di residenza del cliente.
	*/
	public Ristorante(String nome, String url_sito, String telefono, Indirizzo indirizzo, Tipologia tipologia) {
		this.nome = nome;
		this.url_sito = url_sito;
		this.telefono = telefono;
		this.indirizzo = indirizzo;
		this.tipologia = tipologia;
	}
	
	/** Ritorna il nome del ristorante.
	 * @return Una stringa che rappresenta il nome del ristorante.
	*/
	public String getNome() {
		return nome;
	}
	
	/** Ritorna l'URL del sito web del ristorante.
	 * @return Una stringa che rappresenta l'URL del sito web del ristorante.
	*/
	public String getUrl() {
		return url_sito;
	}
	
	/** Ritorna il telefono del ristorante.
	 * @return Una stringa che rappresenta il telefono del ristorante.
	*/
	public String getTelefono() {
		return telefono;
	}
	
	/** Ritorna l'indirizzo del ristorante.
	 * @return Un oggetto indirizzo che rappresenta l'indirizzo del ristorante.
	*/
	public Indirizzo getIndirizzo() {
		return indirizzo;
	}
	
	/** Ritorna la tipologia del ristorante.
	 * @return Un oggetto tipologia che rappresenta la tipologia del ristorante.
	*/
	public Tipologia getTipologia() {
		return tipologia;
	}
	
	/** Ritorna tutti gli attributi del ristorante.
	 * @return Una stringa che rappresenta l'insieme degli attributi del ristorante.
	*/
	public String toString() {
		return "Nome: " + nome + "\n" +
				"Tipologia: " + tipologia.toString() + "\n" +
				"Indirizzo: " + indirizzo.toString() + "\n" +
				"Sito: " + url_sito + "\n" + 
				"Telefono: " + telefono + "\n";
	}

}
