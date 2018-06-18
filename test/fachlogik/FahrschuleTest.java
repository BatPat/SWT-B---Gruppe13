package fachlogik;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FahrschuleTest {
	
	public Fahrschule fahrschule;

	@BeforeEach
	public void init() {
		//Given
		fahrschule = new Fahrschule();
	}
	
	@Test
	public void getTermineTest(){
		//When
		
		//Then
		
	}
	
	@Test
	public void getAnzTheoriestundenTest(){
		//When
		
		
		//Then
		
	}
	
	@Test
	public void getAnzSonderfahrtenTest(){
		//When
		
		
		//Then
		
	}
	

	
	@Test
	public void getAnzStandardfahrtenTest(){
		//When
		
		
		//Then
		
	}
	
	@Test
	public void getAnzSonderAndStandardFahrtenTest(){
		//When
		
		
		//Then
		
	}
}
