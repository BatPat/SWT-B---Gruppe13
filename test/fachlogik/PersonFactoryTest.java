package fachlogik;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class PersonFactoryTest {
				
		private PersonFactory personFactory;

		@BeforeAll
		public void init() {
			//Given
			personFactory = new PersonFactory();
		}
		
		@Test
		public void createFahrlehrerTest(){
			//When
			Person p = personFactory.createPerson(PersonType.FAHRLEHRER, "name", "plz", "wOrt", "strasse", "hNummer", "tNummer", "gDatum", "fKlasse");
			
			//Then
			assertThat(p).isInstanceOf(FahrlehrerDTO.class);
		}
		
		@Test
		public void createFahrschuelerTest() {
			//When
			Person p = personFactory.createPerson(PersonType.FAHRSCHUELER, "name", "plz", "wOrt", "strasse", "hNummer", "tNummer", "gDatum", "fKlasse");
			
			//Then
			assertThat(p).isInstanceOf(FahrschuelerDTO.class);
		}

}
