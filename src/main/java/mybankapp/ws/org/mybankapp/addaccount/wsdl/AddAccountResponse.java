//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.03.27 at 08:49:59 PM GMT+04:00 
//


package mybankapp.ws.org.mybankapp.addaccount.wsdl;

import mybankapp.dto.CurrencyAccountDTO;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="currencyaccountDTO" type="{http://www.mybankapp.org/addaccount/wsdl}currencyaccountDTO"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "currencyaccountDTO"
})
@XmlRootElement(name = "addAccountResponse")
public class AddAccountResponse {

    @XmlElement(required = true)
    protected CurrencyAccountDTO currencyaccountDTO;

    /**
     * Gets the value of the currencyaccountDTO property.
     * 
     * @return
     *     possible object is
     *     {@link CurrencyAccountDTO }
     *     
     */
    public CurrencyAccountDTO getCurrencyaccountDTO() {
        return currencyaccountDTO;
    }

    /**
     * Sets the value of the currencyaccountDTO property.
     * 
     * @param value
     *     allowed object is
     *     {@link CurrencyAccountDTO }
     *     
     */
    public void setCurrencyaccountDTO(CurrencyAccountDTO value) {
        this.currencyaccountDTO = value;
    }

}
