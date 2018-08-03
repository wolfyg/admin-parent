package cn.ctx.api.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.UnsupportedCharsetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by AsHome on 2016/5/31.
 */
public class HttpClientUtil {
    private static PoolingHttpClientConnectionManager cm;
    private static String EMPTY_STR = "";
    private static String UTF_8 = "UTF-8";

    private static void init() {
        if (cm == null) {
            cm = new PoolingHttpClientConnectionManager();
            cm.setMaxTotal(100);//整个连接池最大连接数
            cm.setDefaultMaxPerRoute(5);//每路由最大连接数，默认值是2
        }
    }

    /**
     * 通过连接池获取HttpClient
     *
     * @return
     */
    private static CloseableHttpClient getHttpClient() {
        init();
        return HttpClients.custom().setConnectionManager(cm).build();
    }

    /**
     * @param url
     * @return
     */
    public static String get(String url) {
        HttpGet httpGet = new HttpGet(url);
        return getResult(httpGet);
    }

    public static String get(String url, Map<String, Object> params) {
        URIBuilder ub = new URIBuilder();
        ub.setPath(url);

        ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
        ub.setParameters(pairs);

        try {
            HttpGet httpGet = new HttpGet(ub.build());
            return getResult(httpGet);
        }catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return EMPTY_STR;

    }
	public static String getContent(String code,String accessTokenUrl) throws IOException {
		System.out.println("wxopenidurl:"+accessTokenUrl.replaceAll("\\{code\\}", code));
		URL url = new URL(accessTokenUrl.replaceAll("\\{code\\}", code));
		HttpURLConnection urlConnection =(HttpURLConnection) url.openConnection();

		InputStream input = null;
		try {
			input = urlConnection.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(input));
			StringBuilder s = new StringBuilder();
			String line;
			while ((line = br.readLine()) != null) {
				s.append(line).append('\n');
			}
			return s.toString();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(urlConnection!=null){
				urlConnection.disconnect();
			}
		}
	}

    public static String get(String url, Map<String, Object> headers, Map<String, Object> params) {
        URIBuilder ub = new URIBuilder();
        ub.setPath(url);

        ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
        ub.setParameters(pairs);

        try {
            HttpGet httpGet = new HttpGet(ub.build());
            for (Map.Entry<String, Object> param : headers.entrySet()) {
                httpGet.addHeader(param.getKey(), param.getValue().toString());
            }
            return getResult(httpGet);
        }catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return EMPTY_STR;
    }

    public static String post(String url) {
        HttpPost httpPost = new HttpPost(url);
        return getResult(httpPost);
    }

    public static String post(String url, String data) {
        HttpPost httpPost = new HttpPost(url);
        try {
            StringEntity entity = new StringEntity(data, UTF_8);
            httpPost.setEntity(entity);
        }catch (UnsupportedCharsetException e) {
            e.printStackTrace();
        }
        return getResult(httpPost);
    }

    public static String post(String url, Map<String, Object> params) {
        HttpPost httpPost = new HttpPost(url);
        ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
        try {
            HttpEntity entity = new UrlEncodedFormEntity(pairs, UTF_8);
            httpPost.setEntity(entity);
        }catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return getResult(httpPost);
    }

    public static String post(String url, Map<String, Object> headers, Map<String, Object> params) {
        HttpPost httpPost = new HttpPost(url);

        for (Map.Entry<String, Object> param : headers.entrySet()) {
            httpPost.addHeader(param.getKey(), param.getValue().toString());
        }

        ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
        try {
            HttpEntity entity = new UrlEncodedFormEntity(pairs, UTF_8);
            httpPost.setEntity(entity);
        }catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return getResult(httpPost);
    }

    private static ArrayList<NameValuePair> covertParams2NVPS(Map<String, Object> params) {
        ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
        for (Map.Entry<String, Object> param : params.entrySet()) {
            pairs.add(new BasicNameValuePair(param.getKey().toString(), param.getValue()==null?"":param.getValue().toString()));
        }

        return pairs;
    }


    /**
     * 处理Http请求
     *
     * @param request
     * @return
     */
    private static String getResult(HttpRequestBase request) {
        //CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpClient httpClient = getHttpClient();
        try {
            CloseableHttpResponse response = httpClient.execute(request);
            //response.getStatusLine().getStatusCode();
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                //long len = entity.getContentLength();// -1 表示长度未知
                String result = EntityUtils.toString(entity,"UTF-8");
                response.close();
                //httpClient.close();
                return result;
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }

        return EMPTY_STR;
    }
    
//    public static void main(String arg[]){
//		String phone="15357909051";
//		String password="csz123456";
//		String json="phone="+phone+"&password="+password+"";
//		Map map=new HashMap();
//		map.put("phone", phone);
//		map.put("password", password);
//    	System.out.println(post("http://192.168.23.50:8081/user-platform/service/appuser/userLogin",map));
//    }
	

//    public static void main(String[] args)throws InterruptedException{
//		Map<String, Object> map=new HashMap<String, Object>();
//		map.put("grant_type", "client_credential");
//		map.put("appid", "wxa628316ca08962d9");
//		map.put("secret", "ce4b2079c6e7ddbd8f89416fde714ed4");
//		String json=HttpClientUtil.get("https://api.weixin.qq.com/cgi-bin/token", map);
//		JSONObject jsonObject =  JSONObject.parseObject(json);
//		System.setProperty("access_token", (String) jsonObject.get("access_token"));
//		System.setProperty("expires_in", String.valueOf((System.currentTimeMillis()/1000+Long.valueOf((Integer) jsonObject.get("expires_in")))));
//		System.out.println(System.getProperty("access_token"));
//		System.out.println(System.getProperty("expires_in"));
//		Thread.sleep(5000);
//		System.out.println((Long.valueOf(System.getProperty("expires_in")))-System.currentTimeMillis()/1000);
//		
//	}
}