package fachlogik;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "fahrstunde")
@SuppressWarnings("serial")
public class Fahrstunde extends Stunde {
	@Id
	@Column(nullable = false, name = "idfahrstunde")
	private long id;
	@Column(nullable = false, name = "artfahrstunde")
	private Fahrstundenart art;
	@ManyToOne
	@Column(nullable = false, name = "lehrerfahrstunde")
	private Fahrlehrer lehrer;
	@ManyToOne
	@Column(nullable = false, name = "schuelerfahrstunde")
	private Fahrschueler schueler;
	@Column(nullable = false, name = "genidfahrstunde")
	private long genid;
	@Column(nullable = false, name = "datumfahrstunde")
	private LocalDate datum;
	@Column(nullable = false, name = "uhrzeitfahrstunde")
	private LocalTime uhrzeit;
	@Column(nullable = false, name = "dauerfahrstunde")
	private Duration dauer;
	@Column(nullable = false, name = "ortfahrstunde")
	private String ort;

	public Fahrstunde(LocalDate datum, LocalTime uhrzeit, Duration dauer, String ort) {
		super(datum, uhrzeit, dauer, ort);
	}

	public Fahrstunde(Fahrstundenart art, Fahrlehrer lehrer, Fahrschueler schueler, LocalTime uhrzeit, LocalDate datum,
			String ort) {
		super(datum, uhrzeit, Duration.ofHours(1), ort);
		this.art = art;
		this.lehrer = lehrer;
		this.schueler = schueler;
		this.genid = genid++;
	}

	/**
	 * @return the art
	 */
	public Fahrstundenart getArt() {
		return art;
	}

	public void setArt(Fahrstundenart art) {
		this.art = art;
	}

	/**
	 * @return the lehrer
	 */
	public Fahrlehrer getLehrer() {
		return lehrer;
	}

	/**
	 * @return the schueler
	 */
	public Fahrschueler getSchueler() {
		return schueler;
	}

	public void setLehrer(Fahrlehrer lehrer) {
		this.lehrer = lehrer;
	}

	public void setSchueler(Fahrschueler schueler) {
		this.schueler = schueler;
	}

	@Override
	public List<Person> getBeteiligtePersonen() {
		List<Person> personen = new ArrayList<>();
		personen.add(lehrer);
		personen.add(schueler);
		return personen;
	}

	public long getGenid() {
		return genid;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDate getDatum() {
		return datum;
	}

	public void setDatum(LocalDate datum) {
		this.datum = datum;
	}

	public LocalTime getUhrzeit() {
		return uhrzeit;
	}

	public void setUhrzeit(LocalTime uhrzeit) {
		this.uhrzeit = uhrzeit;
	}

	public Duration getDauer() {
		return dauer;
	}

	public void setDauer(Duration dauer) {
		this.dauer = dauer;
	}

	public String getOrt() {
		return ort;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}

	public void setGenid(long genid) {
		this.genid = genid;
	}
}