/**
 * 
 * @author Kieron Ho
 * 
 * For extracting the required connection details from a text file
 *
 */
package questionservercommunication;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ConnectionDetails {

	private String host;
	private int port;
	private String keystore;
	private String keystorePass;
	private BufferedReader bufferedReader;
	private FileReader fileReader;

	/**
	 * This constructor loads the required connection details from a text file
	 */
	public ConnectionDetails(){
		try {
			//connectionDetails.txt must be stored in resources folder
	File details = new File("./resources/connectionDetails.txt");
		FileReader fileReader = new FileReader(details);
		bufferedReader = new BufferedReader(fileReader);
		
		// Read through the connectionDetails.txt file and extract the required details
		this.host = bufferedReader.readLine().replace("host:", "").trim();
		this.port = Integer.parseInt(bufferedReader.readLine().replace("port:", "").trim());
		this.keystore = "./resources/" + bufferedReader.readLine().replace("keystore:", "").trim();
		this.keystorePass = bufferedReader.readLine().replace("keystorePass:", "").trim();


		} catch ( IOException e) {
			e.printStackTrace();
		}
		finally{
			try {
				bufferedReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * @return the host value for this connection
	 */
	public String getHost(){
		return host;
	}

	/**
	 * 
	 * @return the port value for this connection
	 */
	public int getPort(){
		return port;
	}

	/**
	 * 
	 * @return the keystore name for this connection
	 */
	public String getKeystore(){
		return keystore;
	}

	/**
	 * 
	 * @return the keystore password for this connection
	 */
	public String getKeystorePass(){
		return keystorePass;
	}
}
