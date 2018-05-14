package Fachlogik;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;

public class PdfDocumentBill {

	// Wird aufgerufen nachdem man einen Schüler in der Hauptanwendung ausgewählt
	// hat (Selected Value aus einem
	// Dropdown Menü) und dann auf einen Button Rechnung klickt.
	// Das selected Value wird übergeben und hier werden sich sämtliche Stammdaten
	// dazu geholt

	// private Adresse adr;
	// ...

	// public PdfDocument(Fahrschüler fahrschüler) {
	//
	// }

	private static Font red = new Font(FontFamily.HELVETICA, 24, Font.BOLDITALIC, BaseColor.RED);
	private static Font black = new Font(FontFamily.HELVETICA, 24, Font.BOLDITALIC, BaseColor.BLACK);
	private static Font righttop = new Font(FontFamily.SYMBOL, 20, Font.NORMAL, BaseColor.BLACK);
	private static Font top = new Font(FontFamily.SYMBOL, 20, Font.NORMAL, BaseColor.BLACK);
	private static Font address1 = new Font(FontFamily.HELVETICA, 9, Font.NORMAL, BaseColor.BLACK);
	private static Font address2 = new Font(FontFamily.HELVETICA, 6, Font.ITALIC, BaseColor.BLACK);
	private static Font fonthel12 = new Font(FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.BLACK);
	private static Font fonthel14 = new Font(FontFamily.HELVETICA, 14, Font.BOLD, BaseColor.BLACK);
	private static Font fonthell10 = new Font(FontFamily.HELVETICA, 10, Font.NORMAL, BaseColor.BLACK);
	private static Font fonthell10ita = new Font(FontFamily.HELVETICA, 10, Font.ITALIC, BaseColor.BLACK);
	private static Font fonthell10bol = new Font(FontFamily.HELVETICA, 10, Font.BOLD, BaseColor.BLACK);
	private static Font fonthell10undbolred = new Font(FontFamily.HELVETICA, 10, Font.UNDERLINE, BaseColor.RED);

	public void createPdf(Fahrschueler fahrschueler) throws IOException {
		try {

			String home = System.getProperty("user.home");
			File file = new File(home + "/Downloads/Rechnungen/" + "Name des Schülers" + ".pdf");
			file.getParentFile().mkdirs();
			Document document = new Document();
			// PdfWriter pdfwriterwsadas = PdfWriter.getInstance(document, new
			// FileOutputStream(file), PdfAC )
			PdfWriter pdfwriter = PdfWriter.getInstance(document, new FileOutputStream(file));
			pdfwriter.setPdfVersion(PdfWriter.PDF_VERSION_1_7);
			document.addAuthor("Fahrschule Terlau");
			document.addTitle("Rechnung" + " Name des Schülers " + ".pdf");
			// pdfwriter.setTagged();
			// pdfwriter.createXmpMetadata();
			// ------------------
			document.open();
			// ------------------
			// ICC_Profile icc = ICC_Profile.getInstance(new FileInputStream(ICC));
			// pdfwriter.setOutputIntents("Custom", "", "http://www.color.org", "sRGB
			// IBC61966-2-1", icc);

			createFirstTableAndAddHeader(document);

			createSecondTableAndAddAdressParts(document, pdfwriter, fahrschueler);

			createThirdTableAndAddTimestamp(document, pdfwriter);

			createFourthTableAndAddBillHeader(document, pdfwriter, fahrschueler);

			createBillTableAndAddRows(document, pdfwriter, fahrschueler);

			createSixthTableAndAddTaxInformation(document, pdfwriter);

			createSeventhTableAndAddGreetings(document, pdfwriter);

			createTableAndAddBankInformation(document, pdfwriter);

			// --------
			document.close();
			// --------
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	private void createTableAndAddBankInformation(Document document, PdfWriter pdfwriter) {
		// -> Bank Information part 1
		PdfPTable tablebankinf1 = new PdfPTable(1);
		tablebankinf1.setHorizontalAlignment(Element.ALIGN_MIDDLE);
		tablebankinf1.setWidthPercentage(30);
		PdfPCell cellbankinf1 = new PdfPCell(new Phrase(
				"                                                                      Volksbank Ruhr Mitte eG",
				fonthell10));
		cellbankinf1.setBorder(Rectangle.TOP);
		cellbankinf1.setFixedHeight(25);
		tablebankinf1.addCell(cellbankinf1);
		tablebankinf1.setTotalWidth(495);
		PdfContentByte canvas8 = pdfwriter.getDirectContent();
		tablebankinf1.writeSelectedRows(0, -1, document.left() + 10, document.top(720), canvas8);
		// ---------------------------------------------------

		// -> Bank Information part 2
		PdfPTable tablebankinf2 = new PdfPTable(1);
		tablebankinf2.setHorizontalAlignment(Element.ALIGN_MIDDLE);
		tablebankinf2.setWidthPercentage(30);
		PdfPCell cellbankinf2 = new PdfPCell(
				new Phrase("BIC: GENODEM1GBU   IBAN: DE72 4226 0001 7202 0035 00", fonthell10));
		cellbankinf2.setBorder(Rectangle.NO_BORDER);
		cellbankinf2.setFixedHeight(25);
		tablebankinf2.addCell(cellbankinf2);
		tablebankinf2.setTotalWidth(480);
		PdfContentByte canvas9 = pdfwriter.getDirectContent();
		tablebankinf2.writeSelectedRows(0, -1, document.left() + 110, document.top(740), canvas9);
		// ---------------------------------------------------

		// -> Bank Information part 3
		PdfPTable tablebankinf3 = new PdfPTable(1);
		tablebankinf3.setHorizontalAlignment(Element.ALIGN_MIDDLE);
		tablebankinf3.setWidthPercentage(30);
		PdfPCell cellbankinf3 = new PdfPCell(new Phrase("Steuer-Nr.: DE 359/5403/5977", fonthell10));
		cellbankinf3.setBorder(Rectangle.NO_BORDER);
		cellbankinf3.setFixedHeight(25);
		tablebankinf3.addCell(cellbankinf3);
		tablebankinf3.setTotalWidth(180);
		PdfContentByte canvas10 = pdfwriter.getDirectContent();
		tablebankinf3.writeSelectedRows(0, -1, document.left() + 190, document.top(760), canvas10);
		// ---------------------------------------------------
	}

	private void createSeventhTableAndAddGreetings(Document document, PdfWriter pdfwriter) {
		// -> Greeting at the End
		PdfPTable tableGreetings = new PdfPTable(1);
		tableGreetings.setHorizontalAlignment(Element.ALIGN_RIGHT);
		tableGreetings.setWidthPercentage(30);
		PdfPCell cellGreetings = new PdfPCell(new Phrase("M i t  f r e u n d l i c h e n  G r ü ß e n\r\n" + "\r\n"
				+ "\r\n" + "\r\n" + "R o b e r t  T e r l a u\r\n" + "", fonthel12));
		cellGreetings.setBorder(Rectangle.NO_BORDER);
		tableGreetings.addCell(cellGreetings);
		tableGreetings.setTotalWidth(450);
		PdfContentByte canvas7 = pdfwriter.getDirectContent();
		tableGreetings.writeSelectedRows(0, -1, document.left() + 10, document.top(570), canvas7);
		// ---------------------------------------------------
	}

	private void createSixthTableAndAddTaxInformation(Document document, PdfWriter pdfwriter) {
		// -> Little Information about the Taxes
		PdfPTable tableTaxes = new PdfPTable(1);
		tableTaxes.setHorizontalAlignment(Element.ALIGN_RIGHT);
		tableTaxes.setWidthPercentage(30);
		PdfPCell celltaxes = new PdfPCell(new Phrase(
				"*Die Mehrwertsteuer wird z.Zt. nicht ausgewiesen aufgrund eines \n anhängenden Verfahrens, wird jedoch an das Finanzamt abgeführt",
				fonthell10));
		celltaxes.setBorder(Rectangle.NO_BORDER);
		tableTaxes.addCell(celltaxes);
		tableTaxes.setTotalWidth(450);
		PdfContentByte canvas6 = pdfwriter.getDirectContent();
		tableTaxes.writeSelectedRows(0, -1, document.left() + 97, document.top(445), canvas6);
		// ---------------------------------------------------
	}

	private void createBillTableAndAddRows(Document document, PdfWriter pdfwriter, Fahrschueler fahrschueler) {
		// Table with the prices for every piece
		float[] columnWidths = { 7, (float) 2, (float) 2 };
		PdfPTable tableBill = new PdfPTable(columnWidths);
		tableBill.setHorizontalAlignment(Element.ALIGN_CENTER);
		tableBill.setWidthPercentage(100);

		createHeaderInBillTable(tableBill);

		createFirstRowBillTable(tableBill);

		createSecondRowInBillTable(tableBill, fahrschueler);

		createThirdRowInBillTable(tableBill, fahrschueler);

		createFourthRowInBillTable(tableBill, fahrschueler);

		createFifthRowInBillTable(tableBill, fahrschueler);

		createSixthRowInBillTable(tableBill, fahrschueler);

		createSeventhRowInBillTable(tableBill);

		createEightRowInBillTable(tableBill, fahrschueler);

		createTenthRowInBillTable(tableBill, fahrschueler);

		PdfContentByte canvas5 = pdfwriter.getDirectContent();
		tableBill.writeSelectedRows(0, -1, document.left() + 10, document.top(260), canvas5);
		// ---------------------------------------------------
	}

	private void createTenthRowInBillTable(PdfPTable tableBill, Fahrschueler fahrschueler) {
		PdfPCell cell1R11 = new PdfPCell(new Phrase("Rechnungsbetrag ", fonthell10undbolred));
		cell1R11.setBorder(Rectangle.BOTTOM);
		cell1R11.setFixedHeight(20);
		tableBill.addCell(cell1R11);
		PdfPCell cell2R11 = new PdfPCell(new Phrase(""));
		cell2R11.setBorder(Rectangle.BOTTOM);
		cell2R11.setFixedHeight(20);
		tableBill.addCell(cell2R11);

		// -Grundbetrag
		double gpreis = 110.00;
		// -Übungsstunden
		int anzNorm = 0;
		for (Fahrstunde f : fahrschueler.getFahrstunden()) {
			if (f.getArt() == Fahrstundenart.B_STANDARDFAHRT) {
				anzNorm++;
			}
		}
		gpreis = (anzNorm * Fahrstundenart.B_STANDARDFAHRT.getPreis());
		// -Sonderstunden
		int anzSonder = 0;
		for (Fahrstunde f : fahrschueler.getFahrstunden()) {
			if (f.getArt() != Fahrstundenart.B_STANDARDFAHRT) {
				anzSonder++;
			}
		}
		gpreis += (anzSonder * Fahrstundenart.B_SONDERFAHRT.getPreis());
		// -Vorstellung Theorieprüfung
		int countertheorie = 0;
		for (Pruefung p : fahrschueler.getPruefungen()) {
			if (p.isTheoriepruefung()) {
				countertheorie++;
			}
		}
		gpreis += (countertheorie * 77.00);
		// -Vorstellung Praxisprüfung
		int counterpraxis = 0;
		for (Pruefung p : fahrschueler.getPruefungen()) {
			if (!p.isTheoriepruefung()) {
				counterpraxis++;
			}
		}
		gpreis += (counterpraxis * 98.00) - 104.20;
		String rechnungsbetrag = gpreis + "";
		PdfPCell cell3R11 = new PdfPCell(new Phrase(rechnungsbetrag, fonthell10undbolred));
		cell3R11.setBorder(Rectangle.BOTTOM);
		cell3R11.setFixedHeight(20);
		tableBill.addCell(cell3R11);
		tableBill.setTotalWidth(500);
	}

	private void createEightRowInBillTable(PdfPTable tableBill, Fahrschueler fahrschueler) {
		PdfPCell cell1R9 = new PdfPCell(new Phrase("Gesamtbetrag	", fonthell10bol));
		cell1R9.setBorder(Rectangle.BOTTOM);
		cell1R9.setFixedHeight(20);
		tableBill.addCell(cell1R9);
		PdfPCell cell2R9 = new PdfPCell(new Phrase(""));
		cell2R9.setBorder(Rectangle.BOTTOM);
		cell2R9.setFixedHeight(20);
		tableBill.addCell(cell2R9);

		// -Grundbetrag
		double gpreis = 110.00;
		// -Übungsstunden
		int anzNorm = 0;
		for (Fahrstunde f : fahrschueler.getFahrstunden()) {
			if (f.getArt() == Fahrstundenart.B_STANDARDFAHRT) {
				anzNorm++;
			}
		}
		gpreis = (anzNorm * Fahrstundenart.B_STANDARDFAHRT.getPreis());
		// -Sonderstunden
		int anzSonder = 0;
		for (Fahrstunde f : fahrschueler.getFahrstunden()) {
			if (f.getArt() == Fahrstundenart.B_SONDERFAHRT) {
				anzSonder++;
			}
		}
		gpreis += (anzSonder * Fahrstundenart.B_SONDERFAHRT.getPreis());
		// -Vorstellung Theorieprüfung
		int countertheorie = 0;
		for (Pruefung p : fahrschueler.getPruefungen()) {
			if (p.isTheoriepruefung()) {
				countertheorie++;
			}
		}
		gpreis += (countertheorie * 77.00);
		// -Vorstellung Praxisprüfung
		int counterpraxis = 0;
		for (Pruefung p : fahrschueler.getPruefungen()) {
			if (!p.isTheoriepruefung()) {
				counterpraxis++;
			}
		}
		gpreis += (counterpraxis * 98.00) - 104.20;
		String gesamtpreisminsva = gpreis + "";
		PdfPCell cell3R9 = new PdfPCell(new Phrase(gesamtpreisminsva, fonthell10bol));
		cell3R9.setBorder(Rectangle.BOTTOM);
		cell3R9.setFixedHeight(20);
		tableBill.addCell(cell3R9);
		tableBill.setTotalWidth(500);
		// ---------------------------------------------------
	}

	private void createSeventhRowInBillTable(PdfPTable tableBill) {
		// -> Eigth Row
		PdfPCell cell1R8 = new PdfPCell(new Phrase("zzgl. Auslage SVA Gebühr", fonthell10));
		cell1R8.setBorder(Rectangle.BOTTOM);
		cell1R8.setFixedHeight(20);
		tableBill.addCell(cell1R8);
		PdfPCell cell2R8 = new PdfPCell(new Phrase(""));
		cell2R8.setBorder(Rectangle.BOTTOM);
		cell2R8.setFixedHeight(20);
		tableBill.addCell(cell2R8);
		PdfPCell cell3R8 = new PdfPCell(new Phrase("104,20", fonthell10));
		cell3R8.setBorder(Rectangle.BOTTOM);
		cell3R8.setFixedHeight(20);
		tableBill.addCell(cell3R8);
		tableBill.setTotalWidth(500);
		// ---------------------------------------------------
	}

	private void createSixthRowInBillTable(PdfPTable tableBill, Fahrschueler fahrschueler) {
		PdfPCell cell1R7 = new PdfPCell(new Phrase("Gesamtbetrag Führerscheinausbildung", fonthell10ita));
		cell1R7.setBorder(Rectangle.BOTTOM);
		cell1R7.setFixedHeight(18);
		tableBill.addCell(cell1R7);
		PdfPCell cell2R7 = new PdfPCell(new Phrase(""));
		cell2R7.setBorder(Rectangle.BOTTOM);
		cell2R7.setFixedHeight(18);
		tableBill.addCell(cell2R7);
		// -Grundbetrag
		double gpreis = 110.00;
		// -Übungsstunden
		int anzNorm = 0;
		for (Fahrstunde f : fahrschueler.getFahrstunden()) {
			if (f.getArt() == Fahrstundenart.B_STANDARDFAHRT) {
				anzNorm++;
			}
		}
		gpreis = (anzNorm * Fahrstundenart.B_STANDARDFAHRT.getPreis());
		// -Sonderstunden
		int anzSonder = 0;
		for (Fahrstunde f : fahrschueler.getFahrstunden()) {
			if (f.getArt() == Fahrstundenart.B_SONDERFAHRT) {
				anzSonder++;
			}
		}
		gpreis += (anzSonder * Fahrstundenart.B_SONDERFAHRT.getPreis());
		// -Vorstellung Theorieprüfung
		int countertheorie = 0;
		for (Pruefung p : fahrschueler.getPruefungen()) {
			if (p.isTheoriepruefung()) {
				countertheorie++;
			}
		}
		gpreis += (countertheorie * 77.00);
		// -Vorstellung Praxisprüfung
		int counterpraxis = 0;
		for (Pruefung p : fahrschueler.getPruefungen()) {
			if (!p.isTheoriepruefung()) {
				counterpraxis++;
			}
		}
		gpreis += (counterpraxis * 98.00);
		String gesamtpreis = gpreis + "";
		PdfPCell cell3R7 = new PdfPCell(new Phrase(gesamtpreis, fonthell10ita));
		cell3R7.setBorder(Rectangle.BOTTOM);
		cell3R7.setFixedHeight(18);
		tableBill.addCell(cell3R7);
		tableBill.setTotalWidth(500);
		// ---------------------------------------------------
	}

	private void createFifthRowInBillTable(PdfPTable tableBill, Fahrschueler fahrschueler) {
		// -> Sixth Row
		PdfPCell cell1R6 = new PdfPCell(new Phrase("Vorstellung zur praktische Prüfung", fonthell10));
		cell1R6.setBorder(Rectangle.BOTTOM);
		cell1R6.setFixedHeight(18);
		tableBill.addCell(cell1R6);
		int counter = 0;
		for (Pruefung p : fahrschueler.getPruefungen()) {
			if (!p.isTheoriepruefung()) {
				counter++;
			}
		}
		String anz = counter + "";
		PdfPCell cell2R6 = new PdfPCell(new Phrase(anz, fonthell10));
		cell2R6.setBorder(Rectangle.BOTTOM);
		cell2R6.setFixedHeight(18);
		tableBill.addCell(cell2R6);
		String preis = (counter * 98.00) + "";
		PdfPCell cell3R6 = new PdfPCell(new Phrase(preis, fonthell10));
		cell3R6.setBorder(Rectangle.BOTTOM);
		cell3R6.setFixedHeight(18);
		tableBill.addCell(cell3R6);
		tableBill.setTotalWidth(500);
		// ---------------------------------------------------
	}

	private void createFourthRowInBillTable(PdfPTable tableBill, Fahrschueler fahrschueler) {
		// -> Fifth Row
		PdfPCell cell1R5 = new PdfPCell(new Phrase("Vorstellung zur theoretischen Prüfung", fonthell10));
		cell1R5.setBorder(Rectangle.BOTTOM);
		cell1R5.setFixedHeight(18);
		tableBill.addCell(cell1R5);
		int counter = 0;
		for (Pruefung p : fahrschueler.getPruefungen()) {
			if (p.isTheoriepruefung()) {
				counter++;
			}
		}
		String anz = counter + "";
		PdfPCell cell2R5 = new PdfPCell(new Phrase(anz, fonthell10));
		cell2R5.setBorder(Rectangle.BOTTOM);
		cell2R5.setFixedHeight(18);
		tableBill.addCell(cell2R5);
		String preis = (counter * 77.00) + "";
		PdfPCell cell3R5 = new PdfPCell(new Phrase(preis, fonthell10));
		cell3R5.setBorder(Rectangle.BOTTOM);
		cell3R5.setFixedHeight(18);
		tableBill.addCell(cell3R5);
		tableBill.setTotalWidth(500);
		// ---------------------------------------------------
	}

	private void createThirdRowInBillTable(PdfPTable tableBill, Fahrschueler fahrschueler) {
		// -> Fourth Row
		PdfPCell cell1R4 = new PdfPCell(new Phrase("Sonderfahrten à 44,00€", fonthell10));
		cell1R4.setBorder(Rectangle.BOTTOM);
		cell1R4.setFixedHeight(18);
		tableBill.addCell(cell1R4);
		int anzSonder = 0;
		for (Fahrstunde f : fahrschueler.getFahrstunden()) {
			if (f.getArt() != Fahrstundenart.B_STANDARDFAHRT) {
				anzSonder++;
			}
		}
		String s = anzSonder + "";
		PdfPCell cell2R4 = new PdfPCell(new Phrase(s, fonthell10));
		cell2R4.setBorder(Rectangle.BOTTOM);
		cell2R4.setFixedHeight(18);
		tableBill.addCell(cell2R4);
		// TODO Preiskalkulierung abhängig von der Art der Sonderfahrt.
		String preis = (anzSonder * 40.00) + "";
		PdfPCell cell3R4 = new PdfPCell(new Phrase(preis, fonthell10));
		cell3R4.setBorder(Rectangle.BOTTOM);
		cell3R4.setFixedHeight(18);
		tableBill.addCell(cell3R4);
		tableBill.setTotalWidth(500);
		// ---------------------------------------------------
	}

	private void createSecondRowInBillTable(PdfPTable tableBill, Fahrschueler fahrschueler) {
		// -> Third Row
		PdfPCell cell1R3 = new PdfPCell(new Phrase("Übungsstunden à 34,00€", fonthell10));
		cell1R3.setBorder(Rectangle.BOTTOM);
		cell1R3.setFixedHeight(18);
		tableBill.addCell(cell1R3);
		int anzNorm = 0;
		for (Fahrstunde f : fahrschueler.getFahrstunden()) {
			if (f.getArt() == Fahrstundenart.B_STANDARDFAHRT) {
				anzNorm++;
			}
		}
		String s = anzNorm + "";
		PdfPCell cell2R3 = new PdfPCell(new Phrase(s, fonthell10));
		cell2R3.setBorder(Rectangle.BOTTOM);
		cell2R3.setFixedHeight(18);
		tableBill.addCell(cell2R3);
		String preis = (anzNorm * Fahrstundenart.B_STANDARDFAHRT.getPreis()) + "";
		PdfPCell cell3R3 = new PdfPCell(new Phrase(preis, fonthell10));
		cell3R3.setBorder(Rectangle.BOTTOM);
		cell3R3.setFixedHeight(18);
		tableBill.addCell(cell3R3);
		tableBill.setTotalWidth(500);
		// ---------------------------------------------------
	}

	private void createFirstRowBillTable(PdfPTable tableBill) {
		PdfPCell cell1R2 = new PdfPCell(new Phrase("Grundbetrag abzgl. Geschwisterrabatt", fonthell10));
		cell1R2.setBorder(Rectangle.BOTTOM);
		cell1R2.setFixedHeight(18);
		tableBill.addCell(cell1R2);
		PdfPCell cell2R2 = new PdfPCell(new Phrase("1", fonthell10));
		cell2R2.setBorder(Rectangle.BOTTOM);
		cell2R2.setFixedHeight(18);
		tableBill.addCell(cell2R2);
		PdfPCell cell3R2 = new PdfPCell(new Phrase("110,00", fonthell10));
		cell3R2.setBorder(Rectangle.BOTTOM);
		cell3R2.setFixedHeight(18);
		tableBill.addCell(cell3R2);
		tableBill.setTotalWidth(500);
		// ---------------------------------------------------
	}

	private void createHeaderInBillTable(PdfPTable tableBill) {
		// -> First Row Header
		PdfPCell cell1R1 = new PdfPCell(new Phrase(""));
		cell1R1.setBorder(Rectangle.BOTTOM);
		cell1R1.setFixedHeight(18);
		tableBill.addCell(cell1R1);
		PdfPCell cell2R1 = new PdfPCell(new Phrase("Anz.", fonthel12));
		cell2R1.setBorder(Rectangle.BOTTOM);
		cell2R1.setFixedHeight(18);
		tableBill.addCell(cell2R1);
		PdfPCell cell3R1 = new PdfPCell(new Phrase("Brutto €", fonthel12));
		cell3R1.setBorder(Rectangle.BOTTOM);
		cell3R1.setFixedHeight(18);
		tableBill.addCell(cell3R1);
		tableBill.setTotalWidth(500);
		// ---------------------------------------------------
	}

	private void createFourthTableAndAddBillHeader(Document document, PdfWriter pdfwriter, Fahrschueler fahrschueler) {
		// fourth row -> Header for Bill
		PdfPTable tableBillHeader = new PdfPTable(1);
		tableBillHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
		tableBillHeader.setWidthPercentage(30);
		String date2 = new SimpleDateFormat("MMyyyy").format(new Date());
		PdfPCell cellheader1 = new PdfPCell(new Phrase("Ausbildungsrechnung " + "(" + date2 + ")", fonthel14));
		cellheader1.setBorder(Rectangle.NO_BORDER);
		tableBillHeader.addCell(cellheader1);
		PdfPCell cellheader2 = new PdfPCell(new Phrase("Für den Fahrerlaubnisbewerber der Klasse : B", fonthel12));
		cellheader2.setBorder(Rectangle.NO_BORDER);
		tableBillHeader.addCell(cellheader2);
		PdfPCell cellheader3 = new PdfPCell(new Phrase(fahrschueler.getName(), fonthel12));
		cellheader3.setBorder(Rectangle.NO_BORDER);
		tableBillHeader.addCell(cellheader3);
		tableBillHeader.setTotalWidth(250);
		PdfContentByte canvas4 = pdfwriter.getDirectContent();
		tableBillHeader.writeSelectedRows(0, -1, document.left() + 10, document.top(200), canvas4);
		// ---------------------------------------------------
	}

	private void createThirdTableAndAddTimestamp(Document document, PdfWriter pdfwriter) {
		// third row -> DateTime on the middle right
		PdfPTable tableDateTime = new PdfPTable(1);
		tableDateTime.setHorizontalAlignment(Element.ALIGN_RIGHT);
		tableDateTime.setWidthPercentage(30);
		String date = new SimpleDateFormat("dd.MM.yyyy").format(new Date());
		PdfPCell celldate = new PdfPCell(new Phrase(date, fonthel12));
		celldate.setBorder(Rectangle.NO_BORDER);
		tableDateTime.addCell(celldate);
		tableDateTime.setTotalWidth(100);
		PdfContentByte canvas3 = pdfwriter.getDirectContent();
		tableDateTime.writeSelectedRows(0, -1, document.right() - 85, document.top(185), canvas3);
		// ---------------------------------------------------
	}

	private void createSecondTableAndAddAdressParts(Document document, PdfWriter pdfwriter, Fahrschueler fahrschueler) {
		// second row -> Address Part of the driving school
		PdfPTable tableAdressPartSchool = new PdfPTable(1);
		tableAdressPartSchool.setHorizontalAlignment(Element.ALIGN_RIGHT);
		tableAdressPartSchool.setWidthPercentage(30);
		PdfPCell cellone = new PdfPCell(new Phrase("Caspersgäßchen  2", address1));
		cellone.setBorder(Rectangle.NO_BORDER);
		tableAdressPartSchool.addCell(cellone);
		PdfPCell cellTwo = new PdfPCell(new Phrase("45657 Recklinghausen", address1));
		cellTwo.setBorder(Rectangle.NO_BORDER);
		tableAdressPartSchool.addCell(cellTwo);
		PdfPCell cellThree = new PdfPCell(new Phrase("Tel: 02361-943 11 88", address1));
		cellThree.setBorder(Rectangle.NO_BORDER);
		tableAdressPartSchool.addCell(cellThree);
		PdfPCell cellDummy = new PdfPCell(new Phrase(""));
		cellDummy.setBorder(Rectangle.NO_BORDER);
		tableAdressPartSchool.addCell(cellDummy);
		PdfPCell cellFour = new PdfPCell(new Phrase("Mobilfunk:  0162-1383448", address1));
		cellFour.setBorder(Rectangle.NO_BORDER);
		tableAdressPartSchool.addCell(cellFour);
		tableAdressPartSchool.setTotalWidth(150);
		PdfContentByte canvas = pdfwriter.getDirectContent();
		tableAdressPartSchool.writeSelectedRows(0, -1, document.right() - 125, document.top(90), canvas);
		// ---------------------------------------------------

		// second row -> Address Part of the student
		PdfPTable tableAdressPartStudent = new PdfPTable(1);
		tableAdressPartStudent.setHorizontalAlignment(Element.ALIGN_LEFT);
		tableAdressPartStudent.setWidthPercentage(30);
		PdfPCell cellx1 = new PdfPCell(new Phrase(
				"F a h r s c h u l e  T e r l a u, C a s p e r s g ä s s c h e n 2, \n4 5 6 5 7  R e c k l i n g h a u  s e n ",
				address2));
		cellx1.setBorder(Rectangle.BOTTOM);
		tableAdressPartStudent.addCell(cellx1);
		PdfPCell cellDummy2 = new PdfPCell(new Phrase(""));
		cellDummy2.setBorder(Rectangle.NO_BORDER);
		tableAdressPartStudent.addCell(cellDummy2);
		PdfPCell cellx2 = new PdfPCell(new Phrase(fahrschueler.getName(), fonthel12));
		cellx2.setBorder(Rectangle.NO_BORDER);
		tableAdressPartStudent.addCell(cellx2);
		PdfPCell cellx3 = new PdfPCell(
				new Phrase(fahrschueler.getStrasse() + " " + fahrschueler.getHausnummer(), fonthel12));
		cellx3.setBorder(Rectangle.NO_BORDER);
		tableAdressPartStudent.addCell(cellx3);
		PdfPCell cellx4 = new PdfPCell(new Phrase(fahrschueler.getPlz() + " " + fahrschueler.getWohnort(), fonthel12));
		cellx4.setBorder(Rectangle.NO_BORDER);
		tableAdressPartStudent.addCell(cellx4);
		tableAdressPartStudent.setTotalWidth(250);
		PdfContentByte canvas2 = pdfwriter.getDirectContent();
		tableAdressPartStudent.writeSelectedRows(0, -1, document.left() + 10, document.top(90), canvas2);
		// ---------------------------------------------------
	}

	private void createFirstTableAndAddHeader(Document document) throws DocumentException {
		// ---- Initialization of the First Table ------
		PdfPTable tableheader = new PdfPTable(1);
		tableheader.setTotalWidth(new float[] { 500 });
		tableheader.setLockedWidth(true);
		// ---------------------------------------------

		// ----- Chunks to show the header of the name from the driving school in
		// different colors
		Chunk chunkF = new Chunk("F");
		chunkF.setFont(red);

		Chunk chunkahr = new Chunk("ahr");
		chunkahr.setFont(black);

		Chunk chunks = new Chunk("s");
		chunks.setFont(red);

		Chunk chunkchule = new Chunk("chule");
		chunkchule.setFont(black);

		Chunk chunkT = new Chunk("T");
		chunkT.setFont(red);

		Chunk chunkerlau = new Chunk("erlau");
		chunkerlau.setFont(black);
		// ---------------------------------------------------

		// ------ Added the chunks into a Paragraph ----------
		Paragraph ueberschrift = new Paragraph();
		ueberschrift.setFont(new Font(Font.FontFamily.TIMES_ROMAN, 20));
		ueberschrift.add(chunkF);
		ueberschrift.add(chunkahr);
		ueberschrift.add(chunks);
		ueberschrift.add(chunkchule);
		ueberschrift.add(" ");
		ueberschrift.add(chunkT);
		ueberschrift.add(chunkerlau);
		// ---------------------------------------------------

		// first row -> header for the driving school
		Chunk glue = new Chunk(new VerticalPositionMark());
		PdfPCell cell = new PdfPCell();
		Phrase phrase = new Phrase();
		phrase.add(ueberschrift);
		phrase.add(glue);
		phrase.add("Recklinghausen");
		phrase.setFont(righttop);
		cell.addElement(phrase);
		cell.setFixedHeight(20);
		cell.setBorder(Rectangle.TOP);
		tableheader.addCell(cell);
		PdfPCell cell1 = new PdfPCell();
		Phrase phrase1 = new Phrase();
		phrase1.add("Fahrschulleiter und Inh.: Robert Terlau");
		phrase1.add(glue);
		phrase1.add("Stadtmitte");
		phrase1.setFont(top);
		cell1.addElement(phrase1);
		cell1.setFixedHeight(25);
		cell1.setBorder(Rectangle.BOTTOM);
		tableheader.addCell(cell1);
		document.add(tableheader);
		// ---------------------------------------------------
	}
}