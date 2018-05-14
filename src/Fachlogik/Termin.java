package Fachlogik;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public interface Termin {
	
	public List<Person> getBeteiligtePersonen();
	
	public LocalDate getDatum();
	
	public LocalTime getUhrzeit();
	
	public Duration getDauer();
	
	public String getOrt();
}
