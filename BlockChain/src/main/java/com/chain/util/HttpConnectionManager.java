package com.chain.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.CodingErrorAction;
import java.util.Arrays;

import javax.net.ssl.SSLContext;

import org.apache.commons.httpclient.params.HttpParams;
import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.MessageConstraints;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.DnsResolver;
import org.apache.http.conn.HttpConnectionFactory;
import org.apache.http.conn.ManagedHttpClientConnection;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.DefaultHttpResponseFactory;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultHttpResponseParser;
import org.apache.http.impl.conn.DefaultHttpResponseParserFactory;
import org.apache.http.impl.conn.ManagedHttpClientConnectionFactory;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.conn.SystemDefaultDnsResolver;
import org.apache.http.impl.io.DefaultHttpRequestWriterFactory;
import org.apache.http.io.HttpMessageParser;
import org.apache.http.io.HttpMessageParserFactory;
import org.apache.http.io.HttpMessageWriterFactory;
import org.apache.http.io.SessionInputBuffer;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicLineParser;
import org.apache.http.message.LineParser;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.CharArrayBuffer;
import org.springframework.stereotype.Component;

@Component
public class HttpConnectionManager {
    /**
     * 最大连接数
     */
    public final static int MAX_TOTAL_CONNECTIONS = 100;

    /**
     * 每个路由最大连接数
     */
    public final static int MAX_ROUTE_CONNECTIONS = 20;

    /**
     * 指客户端和服务进行数据交互的时间，是指两者之间如果两个数据包之间的时间大于该时间则认为超时，
     * 而不是整个交互的整体时间，比如如果设置1秒超时，如果每隔0.8秒传输一次数据，传输10次，总共8秒，这样是不超时的。 而如果任意两个数据包之间的时间超过了1秒，则超时。
     */
    public final static int SOCKET_TIMEOUT = 5000;

    /**
     * 连接超时时间
     */
    public final static int CONNECT_TIMEOUT = 5000;

    /**
     * 指从连接池获取到连接的超时时间，如果是非连接池的话，该参数暂时没有发现有什么用
     */
    public final static int CONNECTION_REQUEST_TIMEOUT = 5000;

    HttpParams httpParams;
    private PoolingHttpClientConnectionManager connManager;
    private CookieStore cookieStore;
    private CredentialsProvider credentialsProvider;
    private RequestConfig defaultRequestConfig;

    public HttpConnectionManager() {
        HttpMessageParserFactory<HttpResponse> responseParserFactory = new DefaultHttpResponseParserFactory() {
            @Override
            public HttpMessageParser<HttpResponse> create(SessionInputBuffer buffer, MessageConstraints constraints) {
                LineParser lineParser = new BasicLineParser() {
                    @Override
                    public Header parseHeader(final CharArrayBuffer buffer) {
                        try {
                            return super.parseHeader(buffer);
                        } catch (ParseException ex) {
                            return new BasicHeader(buffer.toString(), null);
                        }
                    }
                };
                return new DefaultHttpResponseParser(buffer, lineParser, DefaultHttpResponseFactory.INSTANCE, constraints) {
                    @Override
                    protected boolean reject(final CharArrayBuffer line, int count) {
                        // try to ignore all garbage preceding a status line infinitely
                        return false;
                    }
                };
            }
        };

        HttpMessageWriterFactory<HttpRequest> requestWriterFactory = new DefaultHttpRequestWriterFactory();

        // Use a custom connection factory to customize the process of
        // initialization of outgoing HTTP connections. Beside standard connection
        // configuration parameters HTTP connection factory can define message
        // parser / writer routines to be employed by individual connections.
        HttpConnectionFactory<HttpRoute, ManagedHttpClientConnection> connFactory = new ManagedHttpClientConnectionFactory(requestWriterFactory, responseParserFactory);

        // Client HTTP connection objects when fully initialized can be bound to
        // an arbitrary network socket. The process of network socket initialization,
        // its connection to a remote address and binding to a local one is controlled
        // by a connection socket factory.

        // SSL context for secure connections can be created either based on
        // system or application specific properties.
        SSLContext sslcontext = SSLContexts.createSystemDefault();

        // Create a registry of custom connection socket factories for supported
        // protocol schemes.
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create().register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", new SSLConnectionSocketFactory(sslcontext)).build();

        // Use custom DNS resolver to override the system DNS resolution.
        DnsResolver dnsResolver = new SystemDefaultDnsResolver() {
            @Override
            public InetAddress[] resolve(final String host) throws UnknownHostException {
                if (host.equalsIgnoreCase("myhost")) {
                    return new InetAddress[] {InetAddress.getByAddress(new byte[] {127, 0, 0, 1})};
                } else {
                    return super.resolve(host);
                }
            }
        };

        connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry, connFactory, dnsResolver);

        // Create socket configuration
        SocketConfig socketConfig = SocketConfig.custom().setTcpNoDelay(true).build();
        // Configure the connection manager to use socket configuration either
        // by default or for a specific host.
        connManager.setDefaultSocketConfig(socketConfig);
        connManager.setSocketConfig(new HttpHost("somehost", 80), socketConfig);
        // Validate connections after 1 sec of inactivity
        connManager.setValidateAfterInactivity(1000);

        // Create message constraints
        MessageConstraints messageConstraints = MessageConstraints.custom().setMaxHeaderCount(200).setMaxLineLength(2000).build();
        // Create connection configuration
        ConnectionConfig connectionConfig = ConnectionConfig.custom().setMalformedInputAction(CodingErrorAction.IGNORE).setUnmappableInputAction(CodingErrorAction.IGNORE)
                .setCharset(Consts.UTF_8).setMessageConstraints(messageConstraints).build();
        // Configure the connection manager to use connection configuration either
        // by default or for a specific host.
        connManager.setDefaultConnectionConfig(connectionConfig);
        connManager.setConnectionConfig(new HttpHost("somehost", 80), ConnectionConfig.DEFAULT);

        // Configure total max or per route limits for persistent connections
        // that can be kept in the pool or leased by the connection manager.

        connManager.setMaxTotal(MAX_TOTAL_CONNECTIONS); // 设置最大连接数
        connManager.setDefaultMaxPerRoute(MAX_ROUTE_CONNECTIONS); // 每个路由默认的连接数
        connManager.setMaxPerRoute(new HttpRoute(new HttpHost("somehost", 80)), MAX_ROUTE_CONNECTIONS);// 路由最大连接数

        // Use custom cookie store if necessary.
        cookieStore = new BasicCookieStore();
        // Use custom credentials provider if necessary.
        credentialsProvider = new BasicCredentialsProvider();
        // Create global request configuration
        defaultRequestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT).setExpectContinueEnabled(true)
                .setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM, AuthSchemes.DIGEST)).setProxyPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC)).build();
    }

    public CloseableHttpClient getHttpClient() {
        RequestConfig requestConfig = RequestConfig.copy(defaultRequestConfig).setSocketTimeout(SOCKET_TIMEOUT).setConnectTimeout(CONNECT_TIMEOUT)
                .setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT).build();

        CloseableHttpClient httpclient = HttpClients.custom().setConnectionManager(connManager).setDefaultCookieStore(cookieStore)
                .setDefaultCredentialsProvider(credentialsProvider).setDefaultRequestConfig(requestConfig).build();

        return httpclient;
    }
}
