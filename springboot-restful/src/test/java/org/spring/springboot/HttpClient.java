package org.spring.springboot;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HTTP;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;
import org.springframework.http.MediaType;

public class HttpClient {



    private CloseableHttpClient m_client = null;

    private Map<String, String> m_requestHeaderMap = new HashMap<String, String>();
    private Map<String, String> m_responseHeaderMap = new HashMap<String, String>();

    
    
    public String post(String url, HttpEntity entity) throws IOException {
        Map<String, String> m_responseHeaderMap = new HashMap<String, String>();
        HttpPost post = new HttpPost(url);
        inputHeader(post);
        post.setEntity(entity);

        return sendRequest(post);
    }

    private void inputHeader(HttpRequestBase method) {
        if (m_requestHeaderMap.isEmpty()) {
            return;
        }

        m_requestHeaderMap.forEach((name, value) -> {
            method.addHeader(name, value);
        });
    }

    private String sendRequest(HttpRequestBase request) throws IOException {

        try (CloseableHttpClient httpclient = request.getURI().toString().startsWith("https") ? getHttpsClient() : HttpClients.createDefault();) {
            try (CloseableHttpResponse response = httpclient.execute(request);) {
                List<Header> responseHeaders = Arrays.asList(response.getAllHeaders());
                responseHeaders.forEach((header) -> {
                    m_responseHeaderMap.put(header.getName(), header.getValue());
                });
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode == 200 || statusCode == 201) {
                    return EntityUtils.toString(response.getEntity(), "UTF-8");
                } else {
                    System.out.println("======" + statusCode + response.getStatusLine().getReasonPhrase());
                    return null;
                }
            }
        }
    }



    private static CloseableHttpClient getHttpsClient() {
        RegistryBuilder<ConnectionSocketFactory> registryBuilder = RegistryBuilder.<ConnectionSocketFactory>create();
        // 指定信任密钥存储对象和连接套接字工厂
        try {
            KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            // 信任任何链接
            TrustStrategy anyTrustStrategy = new TrustStrategy() {
                @Override
                public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                    return true;
                }
            };
            SSLContext sslContext = SSLContexts.custom().useProtocol("TLS").loadTrustMaterial(trustStore, anyTrustStrategy).build();
            registryBuilder.register("https", new SSLConnectionSocketFactory(sslContext, new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            }));
        } catch (KeyStoreException e) {
            throw new RuntimeException(e);
        } catch (KeyManagementException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        Registry<ConnectionSocketFactory> registry = registryBuilder.build();
        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(registry);
        return HttpClientBuilder.create().setConnectionManager(connManager).build();
    }
}
