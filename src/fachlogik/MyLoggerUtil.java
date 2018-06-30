package fachlogik;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class MyLoggerUtil {
	private static String javadir = System.getProperty("user.dir");
	
	public static Logger createLogger() {
		Logger root = Logger.getLogger("");
		FileHandler txt = null;
		try {
			File file = new File(javadir + "/Fahrschule/Logs/Log.txt");
			file.getParentFile().mkdirs();
			txt = new FileHandler(javadir + "/Fahrschule/Logs/Log.txt");
		} catch (Exception e) {
			e.printStackTrace();
		}
		root.setLevel(Level.INFO);
		try {
			txt.setFormatter(new Formatter() {
				@Override
				public String format(LogRecord record) {
					String ret = "";
					ret += "\r\n";
					SimpleDateFormat sdf = new SimpleDateFormat(" dd MMM yyyy HH:mm");
					Date d = new Date(record.getMillis());
					ret += sdf.format(d);
					if(record.getLevel().intValue() >= Level.WARNING.intValue()) {
						ret += "ATTENTION!: ";
					}
					ret += this.formatMessage(record);
					ret += record.getLevel();
					ret += "\r\n";
					return ret;
				}
			});
		} catch (Exception e) {
		}
		root.addHandler(txt);
		return root;
	}

}
