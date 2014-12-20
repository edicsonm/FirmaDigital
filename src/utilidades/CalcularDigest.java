package utilidades;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.*;
public class CalcularDigest {
	
	public static String foo = "10030";
	public static byte[] bytes = foo.getBytes();
	
	public CalcularDigest(){}
	
	public static void main(String[] args) {
		printBytes(bytes);
		String hexas = byteToHexa(bytes);
		System.out.println(hexas);
		hexaToBytes(hexas);
	}
	
	public static String byteToHexa(byte[] bytes){
		return Hex.encodeHexString(bytes);
	}
	
	public static byte[] hexaToBytes(String hexa){
		byte[] bytes = null;
		try {
			bytes = Hex.decodeHex(hexa.toCharArray());
			System.out.println("1");
			printBytes(bytes);
			System.out.println("2");
		} catch (DecoderException e) {
			e.printStackTrace();
		}
		return bytes;
	}
	
	public static void printBytes(byte[] bytes){
		for (int i = 0; i < bytes.length; i++) {
			byte b = bytes[i];
			System.out.println(b);
		}
	}
}
