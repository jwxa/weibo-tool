package com.github.jwxa;

import com.github.jwxa.entity.Status;
import com.github.jwxa.entity.StatusList;
import com.google.gson.Gson;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;

/**
 * 类描述
 * <p>
 * 方法描述列表
 * </p>
 * User: Jwxa Date: 2018/1/24 ProjectName: weibotool Version: 1.0
 */
public class Main {

    private static final String UID = "5572923429";
    private static final String ACCESS_TOKEN = "2.00qyRPqB06XASOa5159dfc2bDJXq2C";
    private static final String URL = "https://api.weibo.com/2/statuses/user_timeline.json";
    private static final String DEFAULT_CHARSET = "UTF-8";
    private static CloseableHttpClient httpClient = createSSLClientDefault();


    public static void main(String[] args) throws URISyntaxException, IOException {
        int i = 1;
        boolean flag = true;
        List<String> allList = new ArrayList<>();
        while (flag) {
            List<String> resultList = getAACInfo(i);
            flag = resultList.size() == 0 ? false : true;
            if(flag){
                allList.addAll(resultList);
                i++;
            }
        }
        allList.forEach((e)->{
            System.out.println("-----");
            System.out.println(e);
        });
    }

    private static List<String> getAACInfo(int page) throws IOException, URISyntaxException {
        HttpGet httpGet = new HttpGet();
        Map<String, String> parameterMap = new HashMap();
        parameterMap.put("access_token", ACCESS_TOKEN);
        parameterMap.put("uid", UID);
        parameterMap.put("since_id", "0");
        parameterMap.put("max_id", "0");
        parameterMap.put("count", "100");
        parameterMap.put("page", Integer.toString(page));
        parameterMap.put("feature", "0");
        parameterMap.put("base_app", "0");
        parameterMap.put("trim_user", "0");
        UrlEncodedFormEntity getEntity = new UrlEncodedFormEntity(
                getParam(parameterMap), DEFAULT_CHARSET);
        String str = EntityUtils.toString(getEntity);
        httpGet.setURI(new URI(URL + "?" + str));
        setHeader(httpGet);
        CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
        String responseContent = EntityUtils.toString(httpResponse.getEntity(), DEFAULT_CHARSET);
        Gson gson = new Gson();
        StatusList statusList = gson.fromJson(responseContent, StatusList.class);
        List<String> resultList = new ArrayList<>();
        for (Status status : statusList.statuses) {
            if (status.text == null || !status.text.contains("http://t.cn")) {
                continue;
            }
            resultList.add(new String(status.text));
        }
        return resultList;
    }

    private static void setHeader(HttpGet httpGet) {
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:50.0) Gecko/20100101 Firefox/50.0");
        httpGet.setHeader("Accept", "application/json, text/javascript, */*");
        httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
        httpGet.setHeader("Accept-Encoding", "gzip, deflate, br");
        httpGet.setHeader("Content-Type", "application/x-www-form-urlencoded");
        httpGet.setHeader("X-Requested-With", "XMLHttpRequest");
        httpGet.setHeader("Connection", "keep-alive");
    }

    public static CloseableHttpClient createSSLClientDefault() {
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                //信任所有
                public boolean isTrusted(X509Certificate[] chain,

                                         String authType) throws CertificateException {
                    return true;
                }
            }).build();
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
            return HttpClients.custom().setSSLSocketFactory(sslsf).build();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
        return HttpClients.createDefault();
    }

    public static List<NameValuePair> getParam(Map parameterMap) {
        List<NameValuePair> param = new ArrayList<>();
        Iterator it = parameterMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry paramEntry = (Map.Entry) it.next();
            param.add(new BasicNameValuePair((String) paramEntry.getKey(),
                    (String) paramEntry.getValue()));
        }
        return param;
    }

}
