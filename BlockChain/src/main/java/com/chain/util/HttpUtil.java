package com.chain.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HttpUtil {
    @Autowired
    HttpConnectionManager connManager;

    /**
     * post 方法
     * 
     * @param url
     * @param params
     * @return
     * @throws Exception
     */
    public String sendPost(String url, Map<String, String> params) throws Exception {
        CloseableHttpClient httpClient = connManager.getHttpClient();
        String ret;
        HttpPost request = new HttpPost(url);
        // set params
        if (params != null) {
            List<NameValuePair> nameValuePairList = new ArrayList<NameValuePair>();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                nameValuePairList.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
            UrlEncodedFormEntity bodyEntity = new UrlEncodedFormEntity(nameValuePairList, "UTF-8");
            request.setEntity(bodyEntity);
        }

        // send request
        CloseableHttpResponse response = httpClient.execute(request);
        try {
            // return content
            ret = readResponseContent(response.getEntity().getContent());
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            request.releaseConnection();
        }
        return ret;
    }

    /**
     * get 方法
     * 
     * @param url
     * @return
     * @throws Exception
     */
    public String sendGet(String url) throws Exception {
        CloseableHttpClient httpClient = connManager.getHttpClient();
        HttpGet request = new HttpGet(url);
        String ret = null;
        CloseableHttpResponse response = httpClient.execute(request);

        try {
            // 获取返回状态
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK) {
                ret = readResponseContent(response.getEntity().getContent());
            }
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            request.releaseConnection();
        }
        return ret;
    }

    /**
     * 读取流数据
     * 
     * @param inputStream
     * @return
     * @throws Exception
     */
    private String readResponseContent(InputStream inputStream) throws Exception {
        StringBuilder out = new StringBuilder();
        if (inputStream == null) {
            return "";
        }

        int len = 0;
        // 将流转换为字符串
        BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
        while ((len = rd.read()) != -1) {
            out.append((char) len);
        }

        rd.close();
        inputStream.close();
        return out.toString();
    }
}
