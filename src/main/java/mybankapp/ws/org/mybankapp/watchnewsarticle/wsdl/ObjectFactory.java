//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.03.28 at 02:13:26 PM GMT+04:00 
//


package mybankapp.ws.org.mybankapp.watchnewsarticle.wsdl;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.mybankapp.watchnewsarticle.wsdl package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _WatchNewsArticleResponse_QNAME = new QName("http://www.mybankapp.org/watchnewsarticle/wsdl", "watchNewsArticleResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.mybankapp.watchnewsarticle.wsdl
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link WatchNewsArticleRequest }
     * 
     */
    public WatchNewsArticleRequest createWatchNewsArticleRequest() {
        return new WatchNewsArticleRequest();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Object }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.mybankapp.org/watchnewsarticle/wsdl", name = "watchNewsArticleResponse")
    public JAXBElement<Object> createWatchNewsArticleResponse(Object value) {
        return new JAXBElement<Object>(_WatchNewsArticleResponse_QNAME, Object.class, null, value);
    }

}
