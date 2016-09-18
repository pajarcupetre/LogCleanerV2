/**
 * Created by alexandru-petrisorpajarcu on 18/09/2016.
 */
public class LogTailer {

	public static void main(String args[]) {
		LogReader logReader = new LogReader(new DumpToFileLogProcessor("out.txt"));
		logReader.readLog("system.out");
	}

}
