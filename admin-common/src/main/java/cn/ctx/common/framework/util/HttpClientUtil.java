package cn.ctx.common.framework.util;

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

    public static String post(String url, Map<String, Object> params,int id) {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Cookie","JSESSIONID=aaagAZ50z8HqHgHJ1dMjw; up_location_prompt=1; isnv=1; zid="+id+"qqda; cn_e716846022b75pef3c0d_dplus=%7B%22distinct_id%22%3A%20%2216266405f54609-0b388280d72694-59462f1d-1fa400-16266405f556e2%22%7D; UM_distinctid=16266405f54609-0b388280d72694-59462f1d-1fa400-16266405f556e2; CNZZDATA30039725=cnzz_eid%3D2077127345-1522128874-%26ntime%3D1522128874; gr_user_id=e30bab27-e0d4-4373-b6d9-106c1fc567ff; gr_session_id_acec0eb2dafeaf05=52b258cb-5f29-4eff-a794-2b9cc092a92d; gr_cs1_52b258cb-5f29-4eff-a794-2b9cc092a92d=uid%3A0");
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
    
//    public static void main(String arg[]) throws InterruptedException{
//    	Map<String,Object> map=new HashMap<String,Object>();
//    	map.put("objectId", 6712407);
//    	map.put("objectType", 3);
//    	for(int i=1;i<1024;i++) {
//    		Thread.sleep(1000);
//        	System.out.println(post("http://www.zcool.com.cn/recommend/doRecommend.json", map,i));
////    		Thread.sleep(1000);
//    	}
//    }
}