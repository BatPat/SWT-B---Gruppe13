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
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pruefung")
@SuppressWarnings("serial")
public class Pruefung implements Termin, Serializable {
	@Id
	@GeneratedValue
	@Column(nullable = false, name = "idpruefung")
	private long id;
	@Column(nullable = false, name = "genidpruefung")
	private long genid;
	@ManyToOne
	@Column(nullable = false, name = "fahrlehrerpruefung")
	private Fahrlehrer fahrlehrer;
	@Column(nullable = false, name = "counterpruefung")
	private static long counter = 0;
	@Column(nullable = false, name = "bestandenpruefung")
	private boolean bestanden;
	@ManyToOne
	@Column(nullable = false, name = "fahrschuelerpruefung")
	private Fahrschueler fahrschueler;
	@Column(nullable = false, name = "datumpruefung")
	private LocalDate datum;
	@Column(nullable = false, name = "uhrzeitpruefung")
	private LocalTime uhrzeit;
	@Column(nullable = false, name = "dauerpruefung")
	private Duration dauer;
	@Column(nullable = false, name = "ortpruefung")
	private String ort;

	public Pruefung() {
		super();
	}

	public Pruefung(Fahrlehrer fahrlehrer, Fahrschueler fahrschueler, LocalDate datum, LocalTime uhrzeit,
			Duration dauer, String ort) {
		this.genid = counter++;
		this.fahrlehrer = fahrlehrer;
		this.fahrschueler = fahrschueler;
		this.datum = datum;
		this.uhrzeit = uhrzeit;
		this.dauer = dauer;
		this.ort = ort;
	}

	public Pruefung(Fahrschueler fahrschueler, LocalDate datum, LocalTime uhrzeit, Duration dauer, String ort) {
		this(null, fahrschueler, datum, uhrzeit, dauer, ort);
	}

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

	public Fahrlehrer getFahrlehrer() {
		return fahrlehrer;
	}

	public Fahrschueler getFahrschueler() {
		return fahrschueler;
	}

	public boolean isBestanden() {
		return bestanden;
	}

	public void setBestanden(boolean bestanden) {
		this.bestanden = bestanden;
	}

}
