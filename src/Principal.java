import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

public class Principal {
	public static String mensaje = "hola mundo";
	public static void main(String[] args) {
		
		String mensajeFirmado = firmarMensaje(mensaje);
		System.out.println("mensajeFirmado en hexa: " + mensajeFirmado);
		
		byte[] bytes = hexaToBytes(mensajeFirmado);
		validarFirma(bytes);
		
	}
	
//	public static String strMsgToSign = "este es mi mensaje a firmar";
//	public static String strMsgToSign2 = "este es mi mensaje a firmar";
	
//	public static void main(String[] args) {
//		try {
////			Properties pro = System.getProperties();
////			pro.list(System.out);
////			Enumeration enun = pro.elements();
////			while (enun.hasMoreElements()) {
////				Object object = (Object) enun.nextElement();
////				System.out.println(object.toString()+":"+pro.get(object));
////			}
//				
//			// 2.1 Especifique el almacén de claves que se haya importado el
//			// certificado Receptores --> PKCS12
//			KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
//			char[] password = "testpwd".toCharArray();
//			java.io.FileInputStream fis = new java.io.FileInputStream("/run/media/Edicson/Informacion IPG/certificados/testkeystore.ks");
//			ks.load(fis, password);
//			fis.close();
//			
//			// 4. Firma el mensaje
//			// 4.1 Obtener la clave privada del remitente desde el almacén de
//			// claves, proporcionando la contraseña establecida para la llave
//			// privada mientras se crea las llaves usados.
//			char[] keypassword = "send123".toCharArray();
//			Key myKey = ks.getKey("testsender", keypassword);
//			PrivateKey myPrivateKey = (PrivateKey) myKey;
//			
//			// 4.2 Firmar el mensaje
//		    Signature mySign = Signature.getInstance("MD5withRSA");
//		    mySign.initSign(myPrivateKey);
//		    mySign.update(strMsgToSign.getBytes());
//		    byte[] byteSignedData = mySign.sign();
//		    
//		    // 6. Validar la firma
//		    // 6.1 Extraer la clave pública de su certificado de remitentes
//			X509Certificate sendercert = (X509Certificate)ks.getCertificate("testsender");
//		    PublicKey pubKeySender = sendercert.getPublicKey();
//		    
//		    // 6.2 Verificar la Firma
//		    Signature myVerifySign = Signature.getInstance("MD5withRSA");
//		    myVerifySign.initVerify(pubKeySender);
//		    myVerifySign.update(strMsgToSign.getBytes());
//		    
//		    boolean verifySign = myVerifySign.verify(byteSignedData);
//		    if (verifySign == false) {
//		    	System.out.println(" Error in validating Signature ");
//		    }else {
//		    	System.out.println(" Successfully validated Signature ");
//		    }
//		    
//		} catch (NoSuchAlgorithmException | CertificateException | IOException e) {
//			e.printStackTrace();
//		} catch (KeyStoreException e) {
//			e.printStackTrace();
//		} catch (UnrecoverableKeyException e) {
//			e.printStackTrace();
//		} catch (InvalidKeyException e) {
//			e.printStackTrace();
//		} catch (SignatureException e) {
//			e.printStackTrace();
//		}
//	}
	
	public static String firmarMensaje(String mensajeRecibido){
		String mensajeFirmado = null;
		try {
			KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
			char[] password = "testpwd".toCharArray();
			java.io.FileInputStream fis = new java.io.FileInputStream("/run/media/Edicson/Informacion IPG/certificados/testkeystore.ks");
			ks.load(fis, password);
			fis.close();
			char[] keypassword = "send123".toCharArray();
			Key myKey = ks.getKey("testsender", keypassword);
			PrivateKey myPrivateKey = (PrivateKey) myKey;
			// 4.2 Firmar el mensaje
		    Signature mySign = Signature.getInstance("MD5withRSA");
		    mySign.initSign(myPrivateKey);
		    mySign.update(mensajeRecibido.getBytes());
		    byte[] byteSignedData = mySign.sign();//Mensaje firmado
		    mensajeFirmado = byteToHexa(byteSignedData);
		} catch (KeyStoreException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (CertificateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (UnrecoverableKeyException e) {
			e.printStackTrace();
		} catch (SignatureException e) {
			e.printStackTrace();
		}
		return mensajeFirmado;
	}
	
	public static boolean validarFirma(byte[] mensajeRecibido){
		try {
			KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
			char[] password = "testpwd".toCharArray();
			java.io.FileInputStream fis = new java.io.FileInputStream("/run/media/Edicson/Informacion IPG/certificados/testkeystore.ks");
			ks.load(fis, password);
			fis.close();
			// 6. Validar la firma
		    // 6.1 Extraer la clave pública de su certificado de remitentes
			X509Certificate sendercert = (X509Certificate)ks.getCertificate("testsender");
		    PublicKey pubKeySender = sendercert.getPublicKey();
		    
		    // 6.2 Verificar la Firma
		    Signature myVerifySign = Signature.getInstance("MD5withRSA");
		    myVerifySign.initVerify(pubKeySender);
		    myVerifySign.update(mensaje.getBytes());
		    
		    boolean verifySign = myVerifySign.verify(mensajeRecibido);
		    if (verifySign == false) {
		    	System.out.println(" Error in validating Signature ");
		    }else {
		    	System.out.println(" Successfully validated Signature ");
		    }
			
		} catch (KeyStoreException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (SignatureException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (CertificateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static String byteToHexa(byte[] bytes){
		return Hex.encodeHexString(bytes);
	}
	
	public static byte[] hexaToBytes(String hexa){
		byte[] bytes = null;
		try {
			bytes = Hex.decodeHex(hexa.toCharArray());
		} catch (DecoderException e) {
			e.printStackTrace();
		}
		return bytes;
	}

}
