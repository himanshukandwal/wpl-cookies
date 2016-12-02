# Server.xml HTTPS Connector configuration:

    <Connector port="8443" protocol="org.apache.coyote.http11.Http11NioProtocol"
               maxThreads="200" SSLEnabled="true" scheme="https" secure="true"
               SSLVerifyClient="optional" keystoreType="PKCS12"
               keystoreFile="/Users/Heman/Documents/workstation/Developement_Studio/Java_Laboratory/wpl-cookies/server-side/keystore.p12" 
               keystorePass="Cookies123" 
               clientAuth="false" sslProtocol="TLS" />
               
               
# In ce
