//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.03.27 at 08:49:59 PM GMT+04:00 
//


package mybankapp.ws.org.mybankapp.addtransaction.wsdl;

import mybankapp.dto.TransactionDTO;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.mybankapp.addtransaction.wsdl package. 
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
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.mybankapp.addtransaction.wsdl
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AddTransactionRequest }
     * 
     */
    public AddTransactionRequest createAddTransactionRequest() {
        return new AddTransactionRequest();
    }

    /**
     * Create an instance of {@link TransactionDTO }
     * 
     */
    public TransactionDTO createTransactionDTO() {
        return new TransactionDTO();
    }

    /**
     * Create an instance of {@link AddTransactionResponse }
     * 
     */
    public AddTransactionResponse createAddTransactionResponse() {
        return new AddTransactionResponse();
    }

}
