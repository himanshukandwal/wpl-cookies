package edu.utdallas.wpl.cookies.front.end.config;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfiguration {

	@Value("${server.ssl.key-store}")
	private String keystoreFile;

	@Value("${server.ssl.key-store-password}")
	private String keystorePass;

	@Value("${server.ssl.keyStoreType}")
	private String keystoreType;

	@Value("${server.ssl.keyAlias}")
	private String keystoreAlias;

	@Value("${server.port}")
	private int tlsPort;

	@Bean
	public RestTemplate restTemplate() {
		// for localhost (self-signed certificate) handshaking.
		try {
			CloseableHttpClient httpClient = HttpClients.custom()
		        .setSSLHostnameVerifier(new NoopHostnameVerifier())
		        .build();
			
		    HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		    requestFactory.setHttpClient(httpClient);

			System.out.println("creating Rest template with request factory !");
			return new RestTemplate(requestFactory);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new RestTemplate();
	}

	@Bean
	public EmbeddedServletContainerFactory servletContainer() {
		TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();

		factory.addConnectorCustomizers(new TomcatConnectorCustomizer() {

			@Override
			public void customize(Connector connector) {

				connector.setPort(tlsPort);
				connector.setSecure(true);
				connector.setScheme("https");

				Http11NioProtocol proto = (Http11NioProtocol) connector.getProtocolHandler();
				proto.setSSLEnabled(true);
				proto.setKeystoreFile(keystoreFile);
				proto.setKeystorePass(keystorePass);
				proto.setKeystoreType(keystoreType);
				proto.setKeyAlias(keystoreAlias);
				proto.setSslProtocol("TLS");
				
				proto.setTruststoreFile(keystoreFile);
				proto.setTruststorePass(keystorePass);
				proto.setClientAuth("false");
				proto.setTruststoreType(keystoreType);
			}
		});

		return factory;
	}

}
