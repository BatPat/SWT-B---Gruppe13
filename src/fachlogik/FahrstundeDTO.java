package fachlogik;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "fahrstunde")
@SuppressWarnings("serial")
public class FahrstundeDTO extends Stunde {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(nullable = false, name = "idfahrstunde")
	private long id = 0;
	@Column(nullable = false, name = "artfahrstunde")
	private Fahrstundenart art;
	
	@ManyToOne
	@Column(nullable = false)
	@JoinColumn(name="namefahrlehrer")
	@NotFound(action=NotFoundAction.IGNORE)
	private FahrlehrerDTO lehrer;
	
	@ManyToOne
	@Column(nullable = false)
	@JoinColumn(name="namefahrschueler")
	@NotFound(action=NotFoundAction.IGNORE)
	private FahrschuelerDTO schueler;
	
	@Column(nullable = false, name = "genidfahrstunde")
	private long genid;
//	@Temporal(TemporalType.DATE)
	@Column(nullable = false, name = "datumfahrstunde")
	private LocalDate datum;
//	@Temporal(TemporalType.TIME)
	@Column(nullable = false, name = "uhrzeitfahrstunde")
	private LocalTime uhrzeit;
	@Column(nullable = false, name = "dauerfahrstunde")
	private Duration dauer;
	@Column(nullable = false, name = "ortfahrstunde")
	private String ort;

	public FahrstundeDTO(LocalDate datum, LocalTime uhrzeit, Duration dauer, String ort) {
		super(datum, uhrzeit, dauer, ort);
	}

	public FahrstundeDTO(Fahrstundenart art, FahrlehrerDTO lehrer, FahrschuelerDTO schueler, LocalTime uhrzeit, LocalDate datum,
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
	public FahrlehrerDTO getLehrer() {
		return lehrer;
	}

	/**
	 * @return the schueler
	 */
	public FahrschuelerDTO getSchueler() {
		return schueler;
	}

	public void setLehrer(FahrlehrerDTO lehrer) {
		this.lehrer = lehrer;
	}

	public void setSchueler(FahrschuelerDTO schueler) {
		this.schueler = schueler;
	}

//	TODO
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