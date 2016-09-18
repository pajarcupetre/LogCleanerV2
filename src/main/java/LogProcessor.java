/**
 * Created by alexandru-petrisorpajarcu on 18/09/2016.
 */
public interface LogProcessor {

	public void handleLine(String line);

	public void handleRotateFile();

}
