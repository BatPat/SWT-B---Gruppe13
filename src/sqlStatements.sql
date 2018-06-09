-- Testdata for fahrlehrer 
INSERT INTO fahrschule.fahrlehrer
(idfahrlehrer,
namefahrlehrer,
plzfahrlehrer,
wohnortfahrlehrer,
strassefahrlehrer,
hausnummerfahrlehrer)
VALUES
(1,"Stefan Terlau", "44723", "Dortmund", "Kaspergaeschen", "3");

INSERT INTO fahrschule.fahrlehrer
(idfahrlehrer,
namefahrlehrer,
plzfahrlehrer,
wohnortfahrlehrer,
strassefahrlehrer,
hausnummerfahrlehrer)
VALUES
(,"Lukas Schmidt", "45231", "Bochum", "Marienweg", "10")

INSERT INTO fahrschule.fahrlehrer
(idfahrlehrer,
namefahrlehrer,
plzfahrlehrer,
wohnortfahrlehrer,
strassefahrlehrer,
hausnummerfahrlehrer)
VALUES
(,"Elke Oltor", "43623", "Unna", "Frogeldamm", "33");

-- Testdata for fahrschueler
INSERT INTO fahrschule.fahrschueler
(idfahrschueler,
namefahrschueler,
plzfahrschueler,
wohnortfahrschueler,
strassefahrschueler,
hausnummerfahrschueler)
VALUES
(1,"Peter Jung", "41743", "Dortmund", "Perss-Alle", "51");

INSERT INTO fahrschule.fahrschueler
(idfahrschueler,
namefahrschueler,
plzfahrschueler,
wohnortfahrschueler,
strassefahrschueler,
hausnummerfahrschueler)
VALUES
(,"Julius Blanke", "51123", "Hagen", "Runhweg", "32")

INSERT INTO fahrschule.fahrschueler
(idfahrschueler,
namefahrschueler,
plzfahrschueler,
wohnortfahrschueler,
strassefahrschueler,
hausnummerfahrschueler)
VALUES
(,"Maria Chimea", "41243", "Dortmund", "Temmstraße", "41");

---- Testdata for fahrstunde
--INSERT INTO fahrschule.fahrstunde
--(idfahrstunde,
--artfahrstunde,
--lehrerfahrstunde,
--schuelerfahrstunde,
--genidfahrstunde,
--datumfahrstunde,
--uhrzeitfahrstunde,
--dauerfahrstunde,
--ortfahrstunde)
--VALUES
--(1,"Standardfahrt",1,1,1,"10-06-2018","10:00","1:00","Recklinghausen");
--
--INSERT INTO fahrschule.fahrstunde
--(idfahrstunde,
--artfahrstunde,
--lehrerfahrstunde,
--schuelerfahrstunde,
--genidfahrstunde,
--datumfahrstunde,
--uhrzeitfahrstunde,
--dauerfahrstunde,
--ortfahrstunde)
--VALUES
--(,"Sonderfahrt",1,2,1,"10-06-2018","11:00","1:00","Recklinghausen");
--
--INSERT INTO fahrschule.fahrstunde
--(idfahrstunde,
--artfahrstunde,
--lehrerfahrstunde,
--schuelerfahrstunde,
--genidfahrstunde,
--datumfahrstunde,
--uhrzeitfahrstunde,
--dauerfahrstunde,
--ortfahrstunde)
--VALUES
--(,"Sonderfahrt",2,3,1,"12-06-2018","09:00","1:00","Recklinghausen");
--
--INSERT INTO fahrschule.fahrstunde
--(idfahrstunde,
--artfahrstunde,
--lehrerfahrstunde,
--schuelerfahrstunde,
--genidfahrstunde,
--datumfahrstunde,
--uhrzeitfahrstunde,
--dauerfahrstunde,
--ortfahrstunde)
--VALUES
--(,"Sonderfahrt",1,1,1,"17-06-2018","11:00","1:00","Recklinghausen");
--
--INSERT INTO fahrschule.fahrstunde
--(idfahrstunde,
--artfahrstunde,
--lehrerfahrstunde,
--schuelerfahrstunde,
--genidfahrstunde,
--datumfahrstunde,
--uhrzeitfahrstunde,
--dauerfahrstunde,
--ortfahrstunde)
--VALUES
--(,"Sonderfahrt",3,2,1,"05-06-2018","18:00","1:00","Recklinghausen");
--
---- Testdata for pruefung
--INSERT INTO fahrschule.pruefung
--(idpruefung,
--genidpruefung,
--fahrlehrerpruefung,
--counterpruefung,
--bestandenpruefung,
--fahrschuelerpruefung,
--datumpruefung,
--uhrzeitpruefung,
--dauerpruefung,
--ortpruefung)
--VALUES
--(1,1,1,1,false,1,"01-06-2018","12:00","00:45","Recklinghausen");
--
---- Testdata for theoriestunde
--INSERT INTO fahrschule.theoriestunde
--(idtheoriestunde,
--thematheoriestunde,
--genidtheoriestunde,
--fahrlehrertheoriestunde,
--fahrschuelerlistetheoriestunde,
--countertheoriestunde,
--datumtheoriestunde,
--uhrzeittheoriestunde,
--dauertheoriestunde,
--orttheoriestunde)
--VALUES
--(1,"Vorfahrtregeln",1,1,{1,2,3},1,"01-04-2018","19:00","02:00","Recklinghausen");
--
--INSERT INTO fahrschule.theoriestunde
--(idtheoriestunde,
--thematheoriestunde,
--genidtheoriestunde,
--fahrlehrertheoriestunde,
--fahrschuelertheoriestunde,
--countertheoriestunde,
--datumtheoriestunde,
--uhrzeittheoriestunde,
--dauertheoriestunde,
--orttheoriestunde)
--VALUES
--(,"Rechtliche Rahmenbedingungen",3,1,{1,2},2,"08-04-2018","19:00","02:00","Recklinghausen");
