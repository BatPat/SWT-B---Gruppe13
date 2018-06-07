package fachlogik;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "pruefung")
@SuppressWarnings("serial")
public class PruefungDTO implements Termin, Serializable {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(nullable = false, name = "idpruefung")
	private long id;
	@Column(nullable = false, name = "genidpruefung")
	private long genid;
	@Column(nullable = false, name = "fahrlehrerpruefung")
	private FahrlehrerDTO fahrlehrer;
	@Column(nullable = false, name = "counterpruefung")
	private static long counter = 0;
	@Column(nullable = false, name = "bestandenpruefung")
	private boolean bestanden;
	@Column(nullable = false, name = "fahrschuelerpruefung")
	private FahrschuelerDTO fahrschueler;
//	@Temporal(TemporalType.DATE)
	@Column(nullable = false, name = "datumpruefung")
	private LocalDate datum;
//	@Temporal(TemporalType.TIME)
	@Column(nullable = false, name = "uhrzeitpruefung")
	private LocalTime uhrzeit;
	@Column(nullable = false, name = "dauerpruefung")
	private Duration dauer;
	@Column(nullable = false, name = "ortpruefung")
	private String ort;

	public PruefungDTO() {
		super();
	}

	public PruefungDTO(FahrlehrerDTO fahrlehrer, FahrschuelerDTO fahrschueler, LocalDate datum, LocalTime uhrzeit,
			Duration dauer, String ort) {
		this.genid = counter++;
		this.fahrlehrer = fahrlehrer;
		this.fahrschueler = fahrschueler;
		this.datum = datum;
		this.uhrzeit = uhrzeit;
		this.dauer = dauer;
		this.ort = ort;
	}

	public PruefungDTO(FahrschuelerDTO fahrschueler, LocalDate datum, LocalTime uhrzeit, Duration dauer, String ort) {
		this(null, fahrschueler, datum, uhrzeit, dauer, ort);
	}

	//TODO
	@Override
	public List<Person> getBeteiligtePersonen() {
		List<Person> personen = new ArrayList<>();
		personen.add(fahrlehrer);
		personen.add(fahrschueler);
		return personen;
	}

	@Override
	public LocalDate getDatum() {
		return datum;
	}

	@Override
	public LocalTime getUhrzeit() {
		return uhrzeit;
	}

	@Override
	public Duration getDauer() {
		return dauer;
	}

	@Override
	public String getOrt() {
		return ort;
	}

	public long getGenid() {
		return genid;
	}

	public boolean isTheoriepruefung() {
		return this.fahrlehrer == null;
	}

	public FahrlehrerDTO getFahrlehrer() {
		return fahrlehrer;
	}

	public FahrschuelerDTO getFahrschueler() {
		return fahrschueler;
	}

	public boolean isBestanden() {
		return bestanden;
	}

	public void setBestanden(boolean bestanden) {
		this.bestanden = bestanden;
	}

}
