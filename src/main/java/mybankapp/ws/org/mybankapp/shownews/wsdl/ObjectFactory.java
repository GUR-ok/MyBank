//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.03.28 at 02:13:26 PM GMT+04:00 
//


package mybankapp.ws.org.mybankapp.shownews.wsdl;

import mybankapp.dto.NewsArticleDTO;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.mybankapp.shownews.wsdl package. 
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


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.mybankapp.shownews.wsdl
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ShowNewsRequest }
     * 
     */
    public ShowNewsRequest createShowNewsRequest() {
        return new ShowNewsRequest();
    }

    /**
     * Create an instance of {@link ShowNewsResponse }
     * 
     */
    public ShowNewsResponse createShowNewsResponse() {
        return new ShowNewsResponse();
    }

    /**
     * Create an instance of {@link NewsArticleDTO }
     * 
     */
    public NewsArticleDTO createNewsArticleDTO() {
        return new NewsArticleDTO();
    }

}
