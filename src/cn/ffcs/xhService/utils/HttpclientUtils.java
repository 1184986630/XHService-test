package cn.ffcs.xhService.utils;

import java.io.*;
import java.net.URL;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.net.www.protocol.http.HttpURLConnection;

public class HttpclientUtils {
    private static final Logger logger = LoggerFactory.getLogger(Thread
            .currentThread().getStackTrace()[1].getClassName());

    public HttpclientUtils(){
        httpclient = new DefaultHttpClient(new ThreadSafeClientConnManager());
    }
    private final String UTF8 = "UTF-8";
    private final String GBK = "GBK";
    private String Encoding = UTF8;
    private DefaultHttpClient httpclient;
    private CredentialsProvider credsProvider;

    public void setEncodingUTF8(){
        Encoding = UTF8;
    }

    public void setEncodingGBK(){
        Encoding = GBK;
    }

    /**
     * 设置Http代理
     * @param proxyHost
     * @param proxyPort
     * @param userName
     * @param password
     */
    public void setHttpProxy(String proxyHost, int proxyPort, String userName, String password){
        credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(
                new AuthScope(proxyHost,proxyPort),
                new UsernamePasswordCredentials(userName,password));
        httpclient.setCredentialsProvider(credsProvider);
        HttpHost proxy = new HttpHost(proxyHost, proxyPort, "http");
        httpclient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,proxy);
    }
    
    /**
     * 设置http超时时间
     * */
    public void setHttpTimeout(int timeout) {
    	httpclient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, timeout); // 读取超时时间
    }

    /**
     * Https访问，Get方法
     * @param host
     * @param addr
     * @return
     */
	public String httpsGet(String host,int port,String addr) {
        // 返回网页body
        String body = "";
        try{
            // 访问的目标站点，端口和协议
            HttpHost targetHost = new HttpHost(host, port, "https");
            // 目标地址
            HttpGet httpget = new HttpGet(addr);
            // 伪装浏览器类型
            // IE7 : Mozilla/4.0 (compatible; MSIE 7.0b; Windows NT 6.0)
            // Firefox3.03 : Mozilla/5.0 (Windows; U; Windows NT 5.2; zh-CN; rv:1.9.0.3) Gecko/2008092417 Firefox/3.0.3
            //httpget.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0b; Windows NT 6.0)");
            // 执行
            HttpResponse response = httpclient.execute(targetHost, httpget);
            HttpEntity entity = response.getEntity();
            logger.debug("---------------返回结果-----------------");
            logger.debug("" + response.getStatusLine());
            if (entity != null) {
                logger.debug("Response content length: " + entity.getContentLength());
            }
            // 显示结果
            body = EntityUtils.toString(entity, Encoding);
        }catch(IOException e){
            logger.error("httpsGet失败.",e);
        }
        return body;
    }

    /**
     * Http访问，Get方法
     * @param host
     * @param port
     * @param addr
     * @return
     */
	public String httpGet(String host,int port,String addr) {
        // 返回网页body
    	String body = null;
        try{
            // 访问的目标站点，端口和协议
            HttpHost targetHost = new HttpHost(host, port, "http");
            // 目标地址
            HttpGet httpget = new HttpGet(addr);
            // 伪装浏览器类型
            // IE7 : Mozilla/4.0 (compatible; MSIE 7.0b; Windows NT 6.0)
            // Firefox3.03 : Mozilla/5.0 (Windows; U; Windows NT 5.2; zh-CN; rv:1.9.0.3) Gecko/2008092417 Firefox/3.0.3
            //httpget.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0b; Windows NT 6.0)");
            // 执行
            HttpResponse response = httpclient.execute(targetHost, httpget);
            HttpEntity entity = response.getEntity();
            logger.debug("---------------返回结果-----------------");
            logger.debug("" + response.getStatusLine());
            if (entity != null) {
                logger.debug("Response content length: " + entity.getContentLength());
            }
            // 显示结果
            body = EntityUtils.toString(entity, Encoding);
        }catch(IOException e){
            logger.error("httpGet失败.",e);
        }
        return body;
    }

    /**
     * Http访问，Post方法
     * @param host
     * @param port
     * @param addr
     * @param params
     * @return
     */
	public String httpPost(String host, int port, String addr, List<NameValuePair> params) {
        // 返回网页body
    	String body = "";
        try{
            // 访问的目标站点，端口和协议
            HttpHost targetHost = new HttpHost(host, port, "http");
            // 目标地址
            HttpPost httppost = new HttpPost(addr);
            // 伪装浏览器类型
            // IE7 : Mozilla/4.0 (compatible; MSIE 7.0b; Windows NT 6.0)
            // Firefox3.03 : Mozilla/5.0 (Windows; U; Windows NT 5.2; zh-CN; rv:1.9.0.3) Gecko/2008092417 Firefox/3.0.3
            //httppost.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0b; Windows NT 6.0)");
            // 设置参数
            httppost.setEntity(new UrlEncodedFormEntity(params, Encoding));
            // 执行
            HttpResponse response = httpclient.execute(targetHost,httppost);
            HttpEntity entity = response.getEntity();
            logger.debug("---------------返回结果-----------------");
            logger.debug("" + response.getStatusLine());
            if (entity != null) {
                logger.debug("Response content length: " + entity.getContentLength());
            }
            // 显示结果
            body = EntityUtils.toString(entity, Encoding);
        }catch(IOException e){
            logger.error("httpPost失败.",e);
        }
        return body;
    }

    /**
     * post请求 调用IMS接口用
     * @param host
     * @param port
     * @param addr
     * @param se
     * @return
     */
	public String httpPost(String host, int port, String addr, StringEntity se) {
        // 返回网页body
		String body = "";
        try{
            // 访问的目标站点，端口和协议
            HttpHost targetHost = new HttpHost(host, port, "http");
            // 目标地址
            HttpPost httppost = new HttpPost(addr);
            // 伪装浏览器类型
            // IE7 : Mozilla/4.0 (compatible; MSIE 7.0b; Windows NT 6.0)
            // Firefox3.03 : Mozilla/5.0 (Windows; U; Windows NT 5.2; zh-CN; rv:1.9.0.3) Gecko/2008092417 Firefox/3.0.3
            //httppost.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0b; Windows NT 6.0)");
            // 设置类型
    		se.setContentType("application/x-www-form-urlencoded");
            // 设置参数
            httppost.setEntity(se);
            // 执行
            HttpResponse response = httpclient.execute(targetHost,httppost);
            HttpEntity entity = response.getEntity();
            logger.debug("---------------返回结果-----------------");
            logger.debug("" + response.getStatusLine());
            if (entity != null) {
                logger.debug("Response content length: " + entity.getContentLength());
            }
            // 显示结果
            body = EntityUtils.toString(entity);
            logger.debug(body);
        }catch(IOException e){
            logger.error("httpPost失败.",e);
        }
        return body;
    }

	/**
     * 发送soap协议
     * @param postUrl soap的wsdl地址
     * @param content soap的具体内容，编辑soapUI发送的xml内容
     * @param soapAction soapAction对应的url
     * @return 结果
     * @throws IOException
     */
    public String postSoap(String postUrl, String content, String soapAction) throws IOException {
        // Post请求的url，与get不同的是不需要带参数
        URL url = new URL(postUrl);
        // 打开连接
        HttpURLConnection connection = (HttpURLConnection) url
                .openConnection();

        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestMethod("POST");
        connection.setUseCaches(false);
        connection.setInstanceFollowRedirects(true);
        connection.setRequestProperty("Content-Type", "text/xml; charset=" + Encoding);
        connection.setRequestProperty("SOAPAction", soapAction);

        StringBuilder result = new StringBuilder();
        BufferedReader reader = null;
        try {
        	connection.connect();
            DataOutputStream out = new DataOutputStream(connection
                    .getOutputStream());
            out.write(content.getBytes(Encoding));

            out.flush();
            out.close(); // flush and close
            reader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
            	result.append(line);
            }
		} catch (Exception e) {
			logger.error("soap error: ", e);
		}finally{
			 reader.close();
		     connection.disconnect();
		}

        return result.toString();
    }

    /**
     * 解析请求参数
     * @param request 请求
     * @return
     */
    public static String getPostBody(HttpServletRequest request){
        String method = request.getMethod();

        if (!method.equalsIgnoreCase("POST")) {
            logger.debug("IS NOT POST REQUEST METHOD!={}", method);
            return null;
        }

        String httpBody = null;
        BufferedReader reader = null;
		try {
			reader = request.getReader();
			StringBuilder sb = new StringBuilder();
            String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			httpBody = sb.toString();
		} catch (IOException e) {
			logger.error("解析请求参数异常", e);
		}

        logger.debug("http body={}", httpBody);
        request.setAttribute("httpBody", httpBody);
        return httpBody;
    }

    /**
     * post 请求，调用统一平台用
     * @param host
     * @param port
     * @param addr
     * @param se
     * @return
     */
    public String httpPostJson(String host, int port, String addr, StringEntity se) {
        // 返回网页body
		String body = "";
        try{
            // 访问的目标站点，端口和协议
            HttpHost targetHost = new HttpHost(host, port, "http");
            // 目标地址
            HttpPost httppost = new HttpPost(addr);
            // 伪装浏览器类型
            // IE7 : Mozilla/4.0 (compatible; MSIE 7.0b; Windows NT 6.0)
            // Firefox3.03 : Mozilla/5.0 (Windows; U; Windows NT 5.2; zh-CN; rv:1.9.0.3) Gecko/2008092417 Firefox/3.0.3
            //httppost.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0b; Windows NT 6.0)");
            // 设置类型
    		se.setContentType("application/json");
            // 设置参数
            httppost.setEntity(se);
            // 执行
            HttpResponse response = httpclient.execute(targetHost, httppost);
            HttpEntity entity = response.getEntity();
            logger.debug("---------------返回结果-----------------");
            logger.debug("" + response.getStatusLine());
            if (entity != null) {
                logger.debug("Response content length: " + entity.getContentLength());
            }
            // 显示结果
            body = EntityUtils.toString(entity, Encoding);
        }catch(IOException e){
            logger.error("httpPost失败.", e);
        }
        return body;
    }

}
