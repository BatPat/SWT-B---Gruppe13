package oberflaeche;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Display;

public class FahrschulTheme {

	private Color whiteColor, blueColor;
	private Font font1, font2, namefont;

	public FahrschulTheme() {

		whiteColor = new Color(Display.getCurrent(), 255, 255, 255);
		blueColor = new Color(Display.getCurrent(), 25, 61, 138);
		font1 = new Font(Display.getCurrent(), "Sans-Serif", 13, SWT.NORMAL);
		namefont = new Font(Display.getCurrent(), "Sans-Serif", 13, SWT.BOLD);
		font2 = new Font(Display.getCurrent(), "Sans-Serif", 11, SWT.NORMAL);
	}

	public Color getWhiteColor() {
		return whiteColor;
	}

	public Font getFont1() {
		return font1;
	}

	public Font getNamefont() {
		return namefont;
	}

	public Font getFont2() {
		return font2;
	}

	public Color getBlueColor() {
		return blueColor;
	}

}