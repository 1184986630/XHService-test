package cn.ffcs.xhService.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * Java 加解密工具类
 * 
 * @author 
 *
 */
public class CryptToolkit {

	private static final Logger logger = LoggerFactory.getLogger(CryptToolkit.class);
    private static final String UTF8 = "utf-8";
    //定义 加密算法,可用 DES,DESede,Blowfish
    private static final String ALGORITHM_DESEDE = "DESede";

    /**
     * BASE64 加密
     * 
     * @param src
     * @return
     * @throws Exception
     */
    public String base64Encoder(String src) throws Exception {
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(src.getBytes(UTF8));
    }
    
    /**
     * BASE64解密
     * 
     * @param dest
     * @return
     * @throws Exception
     */
    public String base64Decoder(String dest) throws Exception {
        BASE64Decoder decoder = new BASE64Decoder();
        return new String(decoder.decodeBuffer(dest), UTF8);
    }
    
    /**
     * 3DES加密
     * 
     * @param src
     * @param key
     * @return
     * @throws Exception
     */
    public String desedeEncoder(String src, String key) throws Exception {
        SecretKey secretKey = new SecretKeySpec(build3DesKey(key), ALGORITHM_DESEDE);
        Cipher cipher = Cipher.getInstance(ALGORITHM_DESEDE);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] b = cipher.doFinal(src.getBytes(UTF8));
        
        return byte2HexStr(b);
    }
    
    /**
     * 3DES解密
     * 
     * @param dest
     * @param key
     * @return
     * @throws Exception
     */
    public String desedeDecoder(String dest, String key) throws Exception {
        SecretKey secretKey = new SecretKeySpec(build3DesKey(key), ALGORITHM_DESEDE);
        Cipher cipher = Cipher.getInstance(ALGORITHM_DESEDE);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] b = cipher.doFinal(str2ByteArray(dest));
        
        return new String(b, UTF8);
    
    }
    
    /**
     * 字节数组转化为大写16进制字符串
     * 
     * @param bytes
     * @return
     */
    private String byte2HexStr(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            String s = Integer.toHexString(b & 0xFF);
            if (s.length() == 1) {
                sb.append("0");
            }

            sb.append(s.toUpperCase());
        }
        
        return sb.toString();
    }
    
    /**
     * 字符串转字节数组
     * 
     * @param s
     * @return
     */
    private byte[] str2ByteArray(String s) {
        int byteArrayLength = s.length()/2;
        byte[] b = new byte[byteArrayLength];
        for (int i = 0; i < byteArrayLength; i++) {
            byte b0 = (byte) Integer.valueOf(s.substring(i*2, i*2+2), 16).intValue();
            b[i] = b0;
        }
        
        return b;
    }
    
    /**
     * 构造3DES加解密方法key
     * 
     * @param keyStr
     * @return
     * @throws UnsupportedEncodingException 
     * @throws Exception
     */
    private byte[] build3DesKey(String keyStr) throws UnsupportedEncodingException {
        byte[] key = new byte[24];
        byte[] temp = keyStr.getBytes(UTF8);
        if (key.length > temp.length) {
            System.arraycopy(temp, 0, key, 0, temp.length);
        } else {
            System.arraycopy(temp, 0, key, 0, key.length);
        }
        
        return key;
    }
    
    public String base643DesEncoder(String src, String key) {
		try {
			return this.base64Encoder(this.desedeEncoder(src, key));
		} catch (Exception e) {
			logger.error("加密异常", e);
			return null;
		}
    }
    
    public String base643DesDecoder(String dest, String key) {
    	try {
			return this.desedeDecoder(this.base64Decoder(dest), key);
		} catch (Exception e) {
			logger.error("解密异常", e);
			return null;
		}
    }

    public String SHA1(String decript) {
        try {
            MessageDigest digest = java.security.MessageDigest
                    .getInstance("SHA-1");
            digest.update(decript.getBytes());
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
    /**
     * SHA-1加密
     * */
    public static String sha1Encrypt(String Passwd) {
	    MessageDigest alg;
		try {
			alg = MessageDigest.getInstance("SHA-1");
			alg.update(Passwd.getBytes());
			byte[] bts = alg.digest();
			String result = "";
			String tmp = "";
			for (int i = 0; i < bts.length; i++) {
				tmp = (Integer.toHexString(bts[i] & 0xFF));
				if (tmp.length() == 1) {
					result += "0";
				}
				result += tmp;
			}
			return result;
		} catch (NoSuchAlgorithmException e) {
			logger.error("SHA-1加密异常", e);
			return null;
		}
	}
}
