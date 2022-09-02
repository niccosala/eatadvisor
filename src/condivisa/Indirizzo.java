package condivisa;

import java.io.Serializable;

/** Rappresenta un indirizzo
 * @author Niccolò Sala
 * @version 1.0
*/
public class Indirizzo implements Serializable {
	
	private static final long serialVersionUID = -3558531991119864771L;

	/** Rappresenta un qualificatore per l'indirizzo
	 * @author Niccolò Sala
	 * @version 1.0
	*/
	public enum Qualificatore {
		Via,
		Viale,
		Corso,
		Piazza,
		Piazzale
	}
	
	private Qualificatore qualificatore;
	private String nome, comune, sigla_provincia;
	private int civico, cap;
	
	/** Crea un cliente.
	 * @param qualificatore Qualificatore dell'indirizzo.
	 * @param nome Nome della via dell'indirizzo.
	 * @param civico Numero civico dell'indirizzo
	 * @param comune Comune dell'indirizzo.
	 * @param sigla_provincia Sigla della provincia dell'indirizzo.
	 * @param cap Cap dell'indirizzo.
	*/
	public Indirizzo(Qualificatore qualificatore, String nome, int civico, String comune, String sigla_provincia, int cap) {
		this.qualificatore = qualificatore;
		this.nome = nome;
		this.sigla_provincia = sigla_provincia;
		this.comune = comune;
		
		if (civico > 0) 
			this.civico = civico;
		else 
			throw new IllegalArgumentException("Numero civico invalido");
		
		if (String.valueOf(cap).length() == 5)
			this.cap = cap;
		else 
			throw new IllegalArgumentException("CAP invalido");
	}
	
	/** Ritorna il qualificatore dell'indirizzo.
	 * @return Un qualificatore dell'indirizzo.
	*/
	public Qualificatore getQualificatore() {
		return qualificatore;
	}

	/** Ritorna il nome della via dell'indirizzo.
	 * @return Una stringa che rappresenta il nome della via dell'indirizzo.
	*/
	public String getNome() {
		return nome;
	}

	/** Ritorna il comune dell'indirizzo.
	 * @return Una stringa che rappresenta il comune dell'indirizzo.
	*/
	public String getComune() {
		return comune;
	}

	/** Ritorna la sigla della provincia dell'indirizzo.
	 * @return Una stringa che rappresenta la sigla della provincia dell'indirizzo.
	*/
	public String getSigla_provincia() {
		return sigla_provincia;
	}

	/** Ritorna il numero civico dell'indirizzo.
	 * @return Una stringa che rappresenta il numero civico dell'indirizzo.
	*/
	public int getCivico() {
		return civico;
	}

	/** Ritorna il cap dell'indirizzo.
	 * @return Un intero che rappresenta il cap dell'indirizzo.
	*/
	public int getCap() {
		return cap;
	}

	/** Ritorna tutti gli attributi dell'indirizzo.
	 * @return Una stringa che rappresenta l'insieme degli attributi dell'indirizzo.
	*/
	public String toString() {
		return qualificatore.toString() + " " + nome + " " + civico + ", " + comune + " (" + sigla_provincia + ") - " + cap;
	}
	
}
