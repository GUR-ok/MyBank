//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.03.28 at 02:13:26 PM GMT+04:00 
//


package mybankapp.ws.org.mybankapp.unwatchnewsarticle.wsdl;

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
 *         &lt;element name="newsId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="uuid"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="36"/&gt;
 *               &lt;pattern value="([0-9]|[a-f]|[A-F]){8}(-([0-9]|[a-f]|[A-F]){4})(-([0-9]|[a-f]|[A-F]){4})(-([0-9]|[a-f]|[A-F]){4})(-([0-9]|[a-f]|[A-F]){12})"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
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
    "newsId",
    "uuid"
})
@XmlRootElement(name = "unwatchNewsArticleRequest")
public class UnwatchNewsArticleRequest {

    protected long newsId;
    @XmlElement(required = true)
    protected String uuid;

    /**
     * Gets the value of the newsId property.
     * 
     */
    public long getNewsId() {
        return newsId;
    }

    /**
     * Sets the value of the newsId property.
     * 
     */
    public void setNewsId(long value) {
        this.newsId = value;
    }

    /**
     * Gets the value of the uuid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * Sets the value of the uuid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUuid(String value) {
        this.uuid = value;
    }

}
