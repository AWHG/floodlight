package benbi.util;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;



public class HttpRequester {

    private static final CloseableHttpClient httpclient = HttpClients.createDefault();

    /**
     * 发送HttpGet请求
     * @param url
     * @return
     */
    public static String sendGet(String url) {

        HttpGet httpget = new HttpGet(url);
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httpget);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        String result = null;
        try {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                result = EntityUtils.toString(entity);
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }


    /**
     * 发送不带参数的HttpPost请求
     * @param url
     * @return
     */
    public static String sendPost(String url) {
        HttpPost httppost = new HttpPost(url);
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httppost);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpEntity entity = response.getEntity();
        String result = null;
        try {
            result = EntityUtils.toString(entity);
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String sendPut(String url) {
        InputStream is = null;
        BufferedReader br = null;
        StringBuilder stringBuilder = null;
        HttpPut httpput = new HttpPut(url);
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httpput);
            //连接成功
//            if(200 == response.getStatusLine().getStatusCode()){
                System.out.println("200");
                HttpEntity httpEntity = response.getEntity();
                is = httpEntity.getContent();
                br = new BufferedReader(new InputStreamReader(is));
                String tempStr;
                stringBuilder = new StringBuilder();
                while ((tempStr = br.readLine()) != null) {
                    stringBuilder.append(tempStr);
                }
                br.close();
                is.close();
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder==null? "":stringBuilder.toString();
    }

    public static String msgFormalize(String message){
        return message.replaceAll(" ","%20").replaceAll("/","%2F")
                .replaceAll(".","%2E").replaceAll(":","%3A");
    }
}
