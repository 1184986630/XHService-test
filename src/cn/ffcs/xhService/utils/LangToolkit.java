package cn.ffcs.xhService.utils;

import java.io.StringReader;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * @author huangjx
 * 
 */
public class LangToolkit {

    /**
     * 将字符串转换成字节数组
     * 
     * @author huangjx
     * @param String
     * @return byte[]
     */
    public static byte[] String2Bytes(String s) {
        byte[] tmpBytes = new byte[200];
        byte[] retBytes = null;
        byte tmpbyte = 0;
        int index = 0;
        StringReader strReader = null;
        try {
            strReader = new StringReader(s);
            while (true) {
                tmpbyte = (byte) ((byte) strReader.read() & 0xFF);
                if (tmpbyte == -1) {
                    break;
                } else {
                    tmpBytes[index] = tmpbyte;
                }
                index++;
            }
            retBytes = new byte[index];
            System.arraycopy(tmpBytes, 0, retBytes, 0, retBytes.length);
        } catch (Exception e) {
            System.err.print(e.toString());
        } finally {
        	if(strReader != null) {
        		strReader.close();
        	}
        }
        return retBytes;
    }

    /**
     * 将字节数组转换成字符串
     * 
     * @author huangjx
     * @param byte[]
     * @return String
     */
    public static String Bytes2String(byte[] bytes) {
        StringBuilder buf = new StringBuilder();

        for (byte b : bytes) {
            buf.append((char) b);
        }

        return buf.toString();
    }

    /**
     * 将字符串转换为压缩的BCD码，每两个字符压缩成一个字节
     * 
     * @author huangjx
     * @param String
     * @return String
     */
    public static String Ascii2BCD(String inStr) {
        StringBuilder buf = new StringBuilder();

        if ((inStr.length() & 1) == 1)
            inStr = inStr + "0";

        while (inStr.length() > 0) {
            String asc = inStr.substring(0, 2);

            if (asc.contains("="))
                buf.append((char) Integer.parseInt(asc.replace('=', 'd'), 16));
            else
                buf.append((char) Integer.parseInt(asc, 16));
            inStr = inStr.substring(2);
        }
        return buf.toString();
    }

    /**
     * 将压缩的BCD码转换为字符串，每个字节的高4位和低4位分别转换成两个字符
     * 
     * @author huangjx
     * @param byte[]
     * @return byte[]
     */
    public static byte[] Bcd2Ascii(byte[] bcd) {
        byte bAscMac[] = new byte[bcd.length * 2];
        int index = 0;
        for (byte b : bcd) {
            bAscMac[index] = (byte) StringUtils.leftPad(
                    Integer.toHexString(0xFF & b).toUpperCase(), 2, "0")
                    .charAt(0);
            bAscMac[index + 1] = (byte) StringUtils.leftPad(
                    Integer.toHexString(0xFF & b).toUpperCase(), 2, "0")
                    .charAt(1);
            index += 2;
        }
        return bAscMac;
    }

    /**
     * 将字节数组的内容以16进制字符串的形式显示 效果等同于Bytes2String(Bcd2Ascii([字节数组])
     * 
     * @param byte[]
     * @return String
     */
    public static String Bytes2HexString(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        String plainText;

        for (byte b : bytes) {
            plainText = Integer.toHexString(0xFF & b);
            if (plainText.length() < 2) {
                plainText = "0" + plainText;
            }
            hexString.append(plainText);
        }
        return hexString.toString();
    }

    /**
     * 将16进制字符串转换成相应的字节数组 效果等同于String2Bytes(Ascii2BCD([16进制字符串])
     * 
     * @param String
     * @return byte[]
     */
    public static byte[] HexString2Bytes(String inStr) {

        StringBuilder buf = new StringBuilder();

        if ((inStr.length() & 1) == 1)
            inStr = inStr + "0";

        while (inStr.length() > 0) {
            String asc = inStr.substring(0, 2);

            if (asc.contains("="))
                buf.append((char) Integer.parseInt(asc.replace('=', 'd'), 16));
            else
                buf.append((char) Integer.parseInt(asc, 16));
            inStr = inStr.substring(2);
        }
        return String2Bytes(buf.toString());
    }

}
