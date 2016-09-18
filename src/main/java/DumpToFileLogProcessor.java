import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by alexandru-petrisorpajarcu on 18/09/2016.
 */
public class DumpToFileLogProcessor implements LogProcessor {

	BufferedWriter bufferedWriter;
	String targetPath;
	File fout;

	public DumpToFileLogProcessor(String targetPath) {
		this.targetPath = targetPath;
		initTarget();
	}

	public void handleLine(String line) {
		try {
			bufferedWriter.write(line);
			bufferedWriter.newLine();
			bufferedWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void handleRotateFile() {
		String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		fout.renameTo(new File(targetPath + timestamp));
		try {
			bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		initTarget();
	}

	void initTarget() {
		fout = new File(targetPath);
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(fout);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		bufferedWriter = new BufferedWriter(new OutputStreamWriter(fos));
	}
}
