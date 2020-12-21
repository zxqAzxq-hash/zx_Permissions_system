package text;

import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Map;
import java.util.Set;

public class SignUtil {
	
	//获得一对公钥私钥
	public static KeyPair getkey() throws Exception {
		KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
		KeyPair generateKeyPair = generator.generateKeyPair();
		return generateKeyPair;
	}

	//拿到字符串   拿到私钥   对数据进行签名
	public static String signqm(String text, String privateKey) throws Exception {
		Signature signature = Signature.getInstance("SHA256WithRSA");// SHA256WithRSA   支付宝默认  是这种
//		Signature signature = Signature.getInstance("MD5withRSA");// MD5withRSA
		PrivateKey privateKey2 = (PrivateKey) getPrivateKey(privateKey);
		// 用私钥初始化signature
		signature.initSign(privateKey2);
		// 更新原始字符串
		signature.update(text.getBytes());
		byte[] bytes = signature.sign();
		String sign = Base64.getEncoder().encodeToString(bytes);
		return sign;
	}
	
	//拿到公钥，拿到字符串数据，拿到sign   对数据进行验签
	public static boolean yansign(String publickey, String sign, String test) throws Exception {
		//指定签名的Signature对象
		Signature signature = Signature.getInstance("SHA256WithRSA");// SHA256WithRSA   支付宝默认  是这种
//		Signature signature = Signature.getInstance("MD5withRSA");// MD5withRSA
		//获得公钥key对象
		Key publicKey2 = getPublicKey(publickey);
		PublicKey publickey3 = (PublicKey) publicKey2;
		// 用公钥初始化signature
		signature.initVerify(publickey3);
		// 更新原始字符串
		signature.update(test.getBytes());
		// 校验签名是否正确
		boolean result = signature.verify(Base64.getDecoder().decode(sign));
		return result;
	}

	//拿到私钥，初始化私钥
	private static Key getPrivateKey(String key) throws Exception {
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(key));
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		Key k = keyFactory.generatePrivate(keySpec);
		return k;
	}

	//按到公钥，初始化公钥
	private static Key getPublicKey(String key) throws Exception {
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(key));
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		Key k = keyFactory.generatePublic(keySpec);
		return k;
	}
	
	//拿到map   从map转换成字符串string
	public static String map_to_string(Map<String, String> params) {
		StringBuffer content = new StringBuffer();
		Set<String> keys = params.keySet();
		int i = 0;
		for (String key:keys) {
			String value = params.get(key);
			content.append((i == 0 ? "" : "&") + key + "=" + value);
			i++;
		}
		return content.toString();
	}
	
	//拿到map，验签的时候，必须要去掉map中的sign 和sign_type 这两个key，然后再转换成string 
	public static String map_quchu_signandsigntype(Map<String, String> params) {
		params.remove("sign");
		params.remove("sign_type");
		
		StringBuffer content = new StringBuffer();
		Set<String> keys = params.keySet();
		int i = 0;
		for (String key:keys) {
			String value = params.get(key);
			content.append((i == 0 ? "" : "&") + keys + "=" + value);
			i++;
		}
		return content.toString();
	}
	
	//拿到map  拿到公钥   不去除sign_type  用map中的sign  对数据进行验签
	public static boolean yanqian(Map<String, String> map,String publickey) throws Exception {
		
		String sign = map.get("sign");
		map.remove("sign");
		map.remove("sign_type");
		String map_to_string = map_to_string(map);
		boolean yansign = yansign(publickey, sign, map_to_string);
		return yansign;
	}

	public static String MD5(String data) throws Exception {
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] array = md.digest(data.getBytes("UTF-8"));
		StringBuilder sb = new StringBuilder();
		for (byte item : array) {
			sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
		}
		return sb.toString().toUpperCase();
	}
}