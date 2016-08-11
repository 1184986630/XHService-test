package cn.ffcs.xhService.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileToolkit {

    public static void main(String[] args) {
        // long start = System.currentTimeMillis();
        // copy("E:/临时资料/wifi2.rar",
        // "C:/Users/Administrator/Documents/wifi2_cp.rar");
        // long end = System.currentTimeMillis();
        // System.out.println("nio 用时： " + (end - start));

        // writeTextFile("e:/1.txt", "232131");
        
        String text = readTextFile("e:/hibernate.cfg.xml");
        System.out.println(text);
    }

    public static String readTextFile(String dist) {
        File f = new File(dist);
        StringBuilder sbuf = new StringBuilder();
        InputStreamReader read = null;
        try {
            read = new InputStreamReader(new FileInputStream(f), "UTF-8");
            BufferedReader reader = new BufferedReader(read);
            String line = null;
            while ((line = reader.readLine()) != null) {
                if ("".equals(line)) {// 去除空行
                    continue;
                }
                sbuf.append(line).append("\n");
            }
            reader.close();
        } catch (UnsupportedEncodingException e) {
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        } finally {
            try {
            	if(read != null) {
            		read.close();
            	}
            } catch (IOException e) {
            }
        }
        return sbuf.toString();
    }

    public static void writeTextFile(String dist, String content) {
        File f = new File(dist);
        try {
            if (!f.exists()) {
                f.getParentFile().mkdirs();
                f.createNewFile();
            }
            OutputStream os = new FileOutputStream(f);
            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
            BufferedWriter bw = new BufferedWriter(osw);
            bw.write(content);
            bw.close();
        } catch (Exception e) {
        }
    }

    public static void copy(String src, String dist) {
        try {
            File srcFile = new File(src);
            File distFile = new File(dist);
            if (distFile.exists()) {
                distFile.delete();
            }

            FileInputStream fin = new FileInputStream(srcFile);
            FileOutputStream fout = new FileOutputStream(distFile);
            FileChannel inChannel = fin.getChannel();
            FileChannel outChannel = fout.getChannel();
            int ByteBufferSize = 1024 * 100;
            ByteBuffer buff = ByteBuffer.allocate(ByteBufferSize);

            while (inChannel.read(buff) > 0) {
                buff.flip();
                if (inChannel.position() == inChannel.size()) {// 判断是不是最后一段数据
                    int lastRead = (int) (inChannel.size() % ByteBufferSize);
                    byte[] bytes = new byte[lastRead];
                    buff.get(bytes, 0, lastRead);
                    outChannel.write(ByteBuffer.wrap(bytes));
                    buff.clear();
                } else {
                    outChannel.write(buff);
                    buff.clear();
                }
            }// 这个使用FileChannel 自带的复制
            // outChannel.transferFrom(inChannel, 0, inChannel.size());
            outChannel.close();
            inChannel.close();
            fin.close();
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
