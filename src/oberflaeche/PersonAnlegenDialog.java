package oberflaeche;

import java.util.Properties;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class PersonAnlegenDialog extends Dialog {

	private Text vornameInput, nachnameInput, strInput, hausnrInput, plzInput, ortInput, fsklasseInput, telInput;

	private DateTime gebdat;

	private Properties languageProperties;

	public PersonAnlegenDialog(Shell parentShell, Properties languageProperties) {
		super(parentShell);
		this.languageProperties = languageProperties;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		container.setLayout(new GridLayout(5, true));

		erzeugeDialogelemente(container);

		return container;
	}

	private void erzeugeDialogelemente(Composite composite) {

		Label vornameLabel = new Label(composite, SWT.NONE);
		vornameLabel.setText(languageProperties.getProperty("vorname"));

		vornameInput = new Text(composite, SWT.BORDER);
		aendereGroessevonTextfeld(vornameInput);

		new Label(composite, SWT.NONE);

		Label nachnameLabel = new Label(composite, SWT.NONE);
		nachnameLabel.setText(languageProperties.getProperty("nachname"));

		nachnameInput = new Text(composite, SWT.BORDER);
		aendereGroessevonTextfeld(nachnameInput);

		erzeugeTrennlinie(composite);

		Label strLabel = new Label(composite, SWT.NONE);
		strLabel.setText(languageProperties.getProperty("strasse"));

		strInput = new Text(composite, SWT.BORDER);
		aendereGroessevonTextfeld(strInput);

		new Label(composite, SWT.NONE);

		Label hausnrLabel = new Label(composite, SWT.NONE);
		hausnrLabel.setText(languageProperties.getProperty("hausnummer"));

		hausnrInput = new Text(composite, SWT.BORDER);
		aendereGroessevonTextfeld(hausnrInput);

		erzeugeTrennlinie(composite);

		Label plzLabel = new Label(composite, SWT.NONE);
		plzLabel.setText(languageProperties.getProperty("plz"));

		plzInput = new Text(composite, SWT.BORDER);
		aendereGroessevonTextfeld(plzInput);

		new Label(composite, SWT.NONE);

		Label ortLabel = new Label(composite, SWT.NONE);
		ortLabel.setText(languageProperties.getProperty("ort"));

		ortInput = new Text(composite, SWT.BORDER);
		aendereGroessevonTextfeld(ortInput);

		erzeugeTrennlinie(composite);

		Label gebdatLabel = new Label(composite, SWT.NONE);
		gebdatLabel.setText(languageProperties.getProperty("geburtsdatum"));

		gebdat = new DateTime(composite, SWT.DATE);

		new Label(composite, SWT.NONE);

		Label telLabel = new Label(composite, SWT.NONE);
		telLabel.setText(languageProperties.getProperty("telefonnummer"));

		telInput = new Text(composite, SWT.BORDER);
		aendereGroessevonTextfeld(telInput);

		erzeugeTrennlinie(composite);

		Label fsklasseLabel = new Label(composite, SWT.NONE);
		fsklasseLabel.setText(languageProperties.getProperty("fuehrerscheinklasse"));

		fsklasseInput = new Text(composite, SWT.BORDER);
		aendereGroessevonTextfeld(fsklasseInput);

	}

	private void aendereGroessevonTextfeld(Text vornameInputLabel2) {
		GridData data = new GridData();
		data.grabExcessHorizontalSpace = true;
		data.horizontalAlignment = GridData.FILL;
		vornameInputLabel2.setLayoutData(data);
	}

	private void erzeugeTrennlinie(Composite composite) {
		Label trennstrichLabel = new Label(composite, SWT.BORDER);
		GridData gridData = new GridData(SWT.FILL, SWT.TOP, true, false);
		gridData.heightHint = 1;
		gridData.horizontalSpan = 5;
		trennstrichLabel.setLayoutData(gridData);
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText(languageProperties.getProperty("personHinzufuegenFensterUeberschrift"));
	}

	@Override
	protected void okPressed() {
		super.okPressed();
	}

	public Text getVornameInput() {
		return vornameInput;
	}

	public Text getNachnameInput() {
		return nachnameInput;
	}

	public Text getStrInput() {
		return strInput;
	}

	public Text getHausnrInput() {
		return hausnrInput;
	}

	public Text getPlzInput() {
		return plzInput;
	}

	public Text getOrtInput() {
		return ortInput;
	}

	public Text getFsklasseInput() {
		return fsklasseInput;
	}

	public Text getTelInput() {
		return telInput;
	}

	public DateTime getGebdat() {
		return gebdat;
	}
}