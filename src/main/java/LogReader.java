import java.io.*;

/**
 * Created by alexandru-petrisorpajarcu on 18/09/2016.
 */
public class LogReader {

	LogProcessor logProcessor;

	public LogReader(LogProcessor logProcessor) {
		this.logProcessor = logProcessor;
	}

	public void readLog(String logFile) {
		long byteCount = 0;
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(logFile);
			File inputFile = new File(logFile);
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));


			String line;
			while(true) {
				if (inputFile.exists()) {
					long currentByteCount = inputFile.length();
					if (currentByteCount < byteCount) {
						System.out.println("Log Rotated; Reading from line 1");
						inputStream = new FileInputStream(logFile);
						inputFile = new File(logFile);
						reader = new BufferedReader(new InputStreamReader(inputStream));
						byteCount = 0;
						logProcessor.handleRotateFile();
					}
					line = reader.readLine(); // blocks until next line available
					if (line != null) {
						logProcessor.handleLine(line);
						byteCount += line.length();
						System.out.println("Line read:" + line);
					} else {
						System.out.println("Waiting on new input");
						Thread.sleep(100);
					}
				}
				// do whatever You want with line
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
