    FahrlehrerDaoImpl fahrlehrer = new FahrlehrerDaoImpl(); 
    FahrschuelerDaoImpl fahrschueler = new FahrschuelerDaoImpl(); 
    fahrlehrer.addFahrlehrer(new Fahrlehrer("Stefan Terlau", "44723", "Dortmund", "Kaspergaeschen", "3")); 
    fahrlehrer.addFahrlehrer(new Fahrlehrer("Lukas Schmidt", "45231", "Bochum", "Marienweg", "10")); 
    fahrlehrer.addFahrlehrer(new Fahrlehrer("Elke Oltor", "43623", "Unna", "Frogeldamm", "33")); 
    fahrschueler.addFahrschueler(new Fahrschueler("Peter Jung", "41743", "Dortmund", "Perss-Alle", "51")); 
    fahrschueler.addFahrschueler(new Fahrschueler("Julius Blanke", "51123", "Hagen", "Runhweg", "32")); 
    fahrschueler.addFahrschueler(new Fahrschueler("Maria Chimea", "41243", "Dortmund", "Temmstraße", "41")); 

-- Testdata for fahrlehrer 
INSERT INTO `fahrschule`.`fahrlehrer`
(`idfahrlehrer`,
`namefahrlehrer`,
`plzfahrlehrer`,
`wohnortfahrlehrer`,
`strassefahrlehrer`,
`hausnummerfahrlehrer`)
VALUES
(<{idfahrlehrer: }>,
<{namefahrlehrer: }>,
<{plzfahrlehrer: }>,
<{wohnortfahrlehrer: }>,
<{strassefahrlehrer: }>,
<{hausnummerfahrlehrer: }>);

INSERT INTO `fahrschule`.`fahrlehrer`
(`idfahrlehrer`,
`namefahrlehrer`,
`plzfahrlehrer`,
`wohnortfahrlehrer`,
`strassefahrlehrer`,
`hausnummerfahrlehrer`)
VALUES
(<{idfahrlehrer: }>,
<{namefahrlehrer: }>,
<{plzfahrlehrer: }>,
<{wohnortfahrlehrer: }>,
<{strassefahrlehrer: }>,
<{hausnummerfahrlehrer: }>)

INSERT INTO `fahrschule`.`fahrlehrer`
(`idfahrlehrer`,
`namefahrlehrer`,
`plzfahrlehrer`,
`wohnortfahrlehrer`,
`strassefahrlehrer`,
`hausnummerfahrlehrer`)
VALUES
(<{idfahrlehrer: }>,
<{namefahrlehrer: }>,
<{plzfahrlehrer: }>,
<{wohnortfahrlehrer: }>,
<{strassefahrlehrer: }>,
<{hausnummerfahrlehrer: }>);

-- Testdata for fahrschueler
INSERT INTO `fahrschule`.`fahrschueler`
(`idfahrschueler`,
`namefahrschueler`,
`plzfahrschueler`,
`wohnortfahrschueler`,
`strassefahrschueler`,
`hausnummerfahrschueler`)
VALUES
(<{idfahrschueler: }>,
<{namefahrschueler: }>,
<{plzfahrschueler: }>,
<{wohnortfahrschueler: }>,
<{strassefahrschueler: }>,
<{hausnummerfahrschueler: }>);

INSERT INTO `fahrschule`.`fahrschueler`
(`idfahrschueler`,
`namefahrschueler`,
`plzfahrschueler`,
`wohnortfahrschueler`,
`strassefahrschueler`,
`hausnummerfahrschueler`)
VALUES
(<{idfahrschueler: }>,
<{namefahrschueler: }>,
<{plzfahrschueler: }>,
<{wohnortfahrschueler: }>,
<{strassefahrschueler: }>,
<{hausnummerfahrschueler: }>)

INSERT INTO `fahrschule`.`fahrschueler`
(`idfahrschueler`,
`namefahrschueler`,
`plzfahrschueler`,
`wohnortfahrschueler`,
`strassefahrschueler`,
`hausnummerfahrschueler`)
VALUES
(<{idfahrschueler: }>,
<{namefahrschueler: }>,
<{plzfahrschueler: }>,
<{wohnortfahrschueler: }>,
<{strassefahrschueler: }>,
<{hausnummerfahrschueler: }>);

-- Testdata for fahrstunde
INSERT INTO `fahrschule`.`fahrstunde`
(`idfahrstunde`,
`artfahrstunde`,
`lehrerfahrstunde`,
`schuelerfahrstunde`,
`genidfahrstunde`,
`datumfahrstunde`,
`uhrzeitfahrstunde`,
`dauerfahrstunde`,
`ortfahrstunde`)
VALUES
(<{idfahrstunde: }>,
<{artfahrstunde: }>,
<{lehrerfahrstunde: }>,
<{schuelerfahrstunde: }>,
<{genidfahrstunde: }>,
<{datumfahrstunde: }>,
<{uhrzeitfahrstunde: }>,
<{dauerfahrstunde: }>,
<{ortfahrstunde: }>);

INSERT INTO `fahrschule`.`fahrstunde`
(`idfahrstunde`,
`artfahrstunde`,
`lehrerfahrstunde`,
`schuelerfahrstunde`,
`genidfahrstunde`,
`datumfahrstunde`,
`uhrzeitfahrstunde`,
`dauerfahrstunde`,
`ortfahrstunde`)
VALUES
(<{idfahrstunde: }>,
<{artfahrstunde: }>,
<{lehrerfahrstunde: }>,
<{schuelerfahrstunde: }>,
<{genidfahrstunde: }>,
<{datumfahrstunde: }>,
<{uhrzeitfahrstunde: }>,
<{dauerfahrstunde: }>,
<{ortfahrstunde: }>);

INSERT INTO `fahrschule`.`fahrstunde`
(`idfahrstunde`,
`artfahrstunde`,
`lehrerfahrstunde`,
`schuelerfahrstunde`,
`genidfahrstunde`,
`datumfahrstunde`,
`uhrzeitfahrstunde`,
`dauerfahrstunde`,
`ortfahrstunde`)
VALUES
(<{idfahrstunde: }>,
<{artfahrstunde: }>,
<{lehrerfahrstunde: }>,
<{schuelerfahrstunde: }>,
<{genidfahrstunde: }>,
<{datumfahrstunde: }>,
<{uhrzeitfahrstunde: }>,
<{dauerfahrstunde: }>,
<{ortfahrstunde: }>);

INSERT INTO `fahrschule`.`fahrstunde`
(`idfahrstunde`,
`artfahrstunde`,
`lehrerfahrstunde`,
`schuelerfahrstunde`,
`genidfahrstunde`,
`datumfahrstunde`,
`uhrzeitfahrstunde`,
`dauerfahrstunde`,
`ortfahrstunde`)
VALUES
(<{idfahrstunde: }>,
<{artfahrstunde: }>,
<{lehrerfahrstunde: }>,
<{schuelerfahrstunde: }>,
<{genidfahrstunde: }>,
<{datumfahrstunde: }>,
<{uhrzeitfahrstunde: }>,
<{dauerfahrstunde: }>,
<{ortfahrstunde: }>);

INSERT INTO `fahrschule`.`fahrstunde`
(`idfahrstunde`,
`artfahrstunde`,
`lehrerfahrstunde`,
`schuelerfahrstunde`,
`genidfahrstunde`,
`datumfahrstunde`,
`uhrzeitfahrstunde`,
`dauerfahrstunde`,
`ortfahrstunde`)
VALUES
(<{idfahrstunde: }>,
<{artfahrstunde: }>,
<{lehrerfahrstunde: }>,
<{schuelerfahrstunde: }>,
<{genidfahrstunde: }>,
<{datumfahrstunde: }>,
<{uhrzeitfahrstunde: }>,
<{dauerfahrstunde: }>,
<{ortfahrstunde: }>);

-- Testdata for pruefung
INSERT INTO `fahrschule`.`pruefung`
(`idpruefung`,
`genidpruefung`,
`fahrlehrerpruefung`,
`counterpruefung`,
`bestandenpruefung`,
`fahrschuelerpruefung`,
`datumpruefung`,
`uhrzeitpruefung`,
`dauerpruefung`,
`ortpruefung`)
VALUES
(<{idpruefung: }>,
<{genidpruefung: }>,
<{fahrlehrerpruefung: }>,
<{counterpruefung: }>,
<{bestandenpruefung: }>,
<{fahrschuelerpruefung: }>,
<{datumpruefung: }>,
<{uhrzeitpruefung: }>,
<{dauerpruefung: }>,
<{ortpruefung: }>);

-- Testdata for theoriestunde
INSERT INTO `fahrschule`.`theoriestunde`
(`idtheoriestunde`,
`thematheoriestunde`,
`genidtheoriestunde`,
`fahrlehrertheoriestunde`,
`fahrschuelertheoriestunde`,
`countertheoriestunde`,
`datumtheoriestunde`,
`uhrzeittheoriestunde`,
`dauertheoriestunde`,
`orttheoriestunde`)
VALUES
(<{idtheoriestunde: }>,
<{thematheoriestunde: }>,
<{genidtheoriestunde: }>,
<{fahrlehrertheoriestunde: }>,
<{fahrschuelertheoriestunde: }>,
<{countertheoriestunde: }>,
<{datumtheoriestunde: }>,
<{uhrzeittheoriestunde: }>,
<{dauertheoriestunde: }>,
<{orttheoriestunde: }>);

INSERT INTO `fahrschule`.`theoriestunde`
(`idtheoriestunde`,
`thematheoriestunde`,
`genidtheoriestunde`,
`fahrlehrertheoriestunde`,
`fahrschuelertheoriestunde`,
`countertheoriestunde`,
`datumtheoriestunde`,
`uhrzeittheoriestunde`,
`dauertheoriestunde`,
`orttheoriestunde`)
VALUES
(<{idtheoriestunde: }>,
<{thematheoriestunde: }>,
<{genidtheoriestunde: }>,
<{fahrlehrertheoriestunde: }>,
<{fahrschuelertheoriestunde: }>,
<{countertheoriestunde: }>,
<{datumtheoriestunde: }>,
<{uhrzeittheoriestunde: }>,
<{dauertheoriestunde: }>,
<{orttheoriestunde: }>);
