package fachlogik;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "theoriestunde")
@SuppressWarnings("serial")
public class TheoriestundeDTO extends Stunde {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(nullable = false, name = "idtheoriestunde")
	private long id;
	@Column(nullable = false, name = "thematheoriestunde")
	private TheorieThema thema;
	@Column(nullable = false, name = "genidtheoriestunde")
	private long genid;
	//TODO
	@Column(nullable = false, name = "fahrlehrertheoriestunde")
	private FahrlehrerDTO fahrlehrer;
	
	//TODO
//	@OneToMany(cascade=CascadeType.ALL, targetEntity=FahrschuelerDTO.class)
//	@JoinColumn(name="id")
	@Column(nullable = false, name = "fahrschuelerlistetheoriestunde")
	private List<FahrschuelerDTO> fahrschueler;
	
	@Column(nullable = false, name = "countertheoriestunde")
	private static long counter = 0;
//	@Temporal(TemporalType.DATE)
	@Column(nullable = false, name = "datumtheoriestunde")
	private LocalDate datum;
//	@Temporal(TemporalType.TIME)
	@Column(nullable = false, name = "uhrzeittheoriestunde")
	private LocalTime uhrzeit;
	@Column(nullable = false, name = "dauertheoriestunde")
	private Duration dauer;
	@Column(nullable = false, name = "orttheoriestunde")
	private String ort;

	public TheoriestundeDTO(LocalDate datum, LocalTime uhrzeit, Duration dauer, String ort) {
		super(datum, uhrzeit, dauer, ort);
	}

	public TheoriestundeDTO(TheorieThema thema, FahrlehrerDTO fahrlehrer, LocalDate datum, LocalTime uhrzeit, String ort) {
		super(datum, uhrzeit, Duration.ofHours(1), ort);
		this.genid = counter++;
		this.thema = thema;
		this.fahrlehrer = fahrlehrer;
		this.fahrschueler = new ArrayList<>();
	}

	public long getGenid() {
		return genid;
	}

	//TODO
	@Override
	public List<Person> getBeteiligtePersonen() {
		List<Person> personen = new ArrayList<>();
		personen.add(fahrlehrer);
		personen.addAll(fahrschueler);
		return personen;
	}

	public TheorieThema getThema() {
		return thema;
	}

	public FahrlehrerDTO getFahrlehrer() {
		return fahrlehrer;
	}

	public void setFahrlehrer(FahrlehrerDTO fahrlehrer) {
		this.fahrlehrer = fahrlehrer;
	}

	public List<FahrschuelerDTO> getFahrschueler() {
		return fahrschueler;
	}

	public void setId(long id) {
		this.id = id;
	}

	public static long getCounter() {
		return counter;
	}

	public static void setCounter(long counter) {
		TheoriestundeDTO.counter = counter;
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

	public void setThema(TheorieThema thema) {
		this.thema = thema;
	}

	public void setGenid(long genid) {
		this.genid = genid;
	}

	public void setFahrschueler(List<FahrschuelerDTO> fahrschueler) {
		this.fahrschueler = fahrschueler;
	}

	public long getId() {
		return id;
	}
}