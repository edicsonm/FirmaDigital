import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.IOUtils;

public class Comando {
	String comando = "keytool -genkey -validity $DAYS_VALID -keypass $CLIENT_PW -dname $CLIENT_DNAME -alias $CLIENT_ALIAS -keystore $CLIENT_KEYSTORE_NAME -storepass $CLIENT_KEYSTORE_PW";
	public static void main(String[] args) {
		new Comando().generarCertificado("365", "abcdef", "\"cn=Mark Jones, ou=Java, o=Oracle, c=US\"", "client", "MerchantX.ks", "ghijkl");
//		try {
////			$JAVA_HOME/bin/keytool
////			java.home
//			Process process = Runtime.getRuntime().exec("lsb_release -a");
//			
//			InputStream inputstream = process.getInputStream();
//			BufferedInputStream bufferedinputstream = new BufferedInputStream(inputstream);
//			String text = IOUtils.toString(bufferedinputstream);
//			System.out.println("--> " + text);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

	}
	public void generarCertificado(String days, String keyPassword, String clientDName, String alias, String keyStoreName, String keyStorePassword){
		try {
			Runtime runtime = Runtime.getRuntime();
			comando  = "openssl genrsa -out private.pem 1024";
			String[] cmd = { "/bin/sh", "-c", comando };
			Process process = runtime.exec(cmd);
			InputStream inputstream = process.getInputStream();
			BufferedInputStream bufferedinputstream = new BufferedInputStream(inputstream);
			String text = IOUtils.toString(bufferedinputstream);
			System.out.println("1--> " + text);
			
//			comando  = "cd "+ System.getProperty("java.home")+System.getProperty("file.separator")+"bin"+System.getProperty("file.separator")+"; sudo ./"+comando;
			
//			process = runtime.exec("pwd ");
//			inputstream = process.getInputStream();
//			bufferedinputstream = new BufferedInputStream(inputstream);
//			text = IOUtils.toString(bufferedinputstream);
//			System.out.println("2--> " + text);
			
//			comando = comando.replace("$DAYS_VALID", days);
//			comando = comando.replace("$CLIENT_PW", keyPassword);
//			comando = comando.replace("$CLIENT_DNAME", clientDName);
//			comando = comando.replace("$CLIENT_ALIAS", alias);
//			comando = comando.replace("$CLIENT_KEYSTORE_NAME", keyStoreName);
//			comando = comando.replace("$CLIENT_KEYSTORE_PW", keyStorePassword);
//			Runtime runtime = Runtime.getRuntime();
//			System.out.println(comando);
//			String[] cmd = { "/bin/sh", "-c", comando };
//			Process process = runtime.exec(cmd);
//			InputStream inputstream = process.getInputStream();
//			BufferedInputStream bufferedinputstream = new BufferedInputStream(inputstream);
//			String text = IOUtils.toString(bufferedinputstream);
//			System.out.println("1--> " + text);
//			
//			comando = "."+System.getProperty("java.home")+System.getProperty("file.separator")+"bin"+System.getProperty("file.separator")+ comando;
//			System.out.println("comando: " +comando );
//			process = Runtime.getRuntime().exec(comando);
//			InputStream inputstream = process.getInputStream();
//			BufferedInputStream bufferedinputstream = new BufferedInputStream(inputstream);
//			String text = IOUtils.toString(bufferedinputstream);
//			System.out.println("--> " + text);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
