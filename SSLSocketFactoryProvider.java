/**
*
* @author Kieron Ho
*
* This class uses a provided keystore to create an SSLContext by which to make an SSLSocketFactory
*/
package questionservercommunication;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

public class SSLSocketFactoryProvider {

	/**
	 * Uses a provided keystore to generate an SSLSocketFactory to return
	 * 
	 * @param keystoreName is the name of the keystore to use
	 * @param trustStorePassword is the password to the keystore
	 * @return an SSLSocketFactory from the keystore
	 */
	public static SSLSocketFactory getSocketFactory(String keystoreName, String trustStorePassword) {

		try{
			FileInputStream keystoreFile = new FileInputStream(keystoreName);
			TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
			KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());

			// Create an empty keystore
			KeyStore keystore = KeyStore.getInstance("jks");
			
			// Load the desired keystore into the new keystore instance
			keystore.load(keystoreFile, trustStorePassword.toCharArray());

			// Initialise the TrustManagerFactory
			tmf.init(keystore);
			
			// Initialise the KeyManagerFactory
			kmf.init(keystore, trustStorePassword.toCharArray());

			// Create a new Context instance
			SSLContext sslContext = SSLContext.getInstance("TLS");
			
			// Initialise the Context based on the TMF and KMF
			sslContext.init(kmf.getKeyManagers() , tmf.getTrustManagers(), null);

			return sslContext.getSocketFactory();

			// All the catch statements
		}
		catch (NoSuchAlgorithmException ex) {
			ex.printStackTrace();
		}
		catch (KeyStoreException ex){
			ex.printStackTrace();
		}
		catch(KeyManagementException ex){
			ex.printStackTrace();
		}
		catch(CertificateException ex){
			ex.printStackTrace();
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
		catch (UnrecoverableKeyException ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
