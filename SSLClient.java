/**
 *
 * @author Kieron Ho
 *
 *	This class has the connection data required to make a new connection to the server and to send and receive data
 */
package questionservercommunication;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

import javax.net.ssl.SSLSocketFactory;

public class SSLClient {

	private String host;
	private int port;
	private SSLSocketFactory sslSocketFactory;
	private String keystoreName;
	private String keystorePassword;

	/**
	 * Constructor for an SSLClient
	 *
	 * @param host is the host name of the server
	 * @param port is the server port
	 * @param keystoreName is the path to the keystore file
	 * @param keystorePassword is the password for the trust store
	 */
	public SSLClient() {
		ConnectionDetails details = new ConnectionDetails();
		this.host = details.getHost();
		this.port = details.getPort();
		this.keystoreName = details.getKeystore();
		this.keystorePassword = details.getKeystorePass();
		//Uses the SSLSocketFactoryProvider class to make an ssl socket factory
		this.sslSocketFactory = SSLSocketFactoryProvider.getSocketFactory(keystoreName,  keystorePassword);

		if(this.sslSocketFactory == null) {
			throw new RuntimeException("Could not configure socket factory.");
		}
	}

	/**
	 * Sends a message to the server and receive the response
	 *
	 * @param message is the message to send to the server
	 * @return the response from the server
	 */
	public String relayMessage(String message) {
		String returnMessage = null;
		Socket socket = null;
		try {
			socket = sslSocketFactory.createSocket(host, port);
			
			// Send the message
			OutputStream outStream = socket.getOutputStream();
			OutputStreamWriter writer = new OutputStreamWriter(outStream);
			BufferedWriter out = new BufferedWriter(writer);
			out.write(message + "\n");//\n is important to signify the end of the message
			out.flush();

			// Read and return the response
			InputStream inStream = socket.getInputStream();
			InputStreamReader reader = new InputStreamReader(inStream);
			BufferedReader in = new BufferedReader(reader);
			returnMessage = in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				socket.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return returnMessage;
	}
}
