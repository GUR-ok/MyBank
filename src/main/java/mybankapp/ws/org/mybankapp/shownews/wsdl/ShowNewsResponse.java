//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.03.28 at 02:13:26 PM GMT+04:00 
//


package mybankapp.ws.org.mybankapp.shownews.wsdl;

import mybankapp.dto.NewsArticleDTO;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="newsArticleDTO" type="{http://www.mybankapp.org/shownews/wsdl}newsArticleDTO" maxOccurs="unbounded" minOccurs="0"/&gt;
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
    "newsArticleDTO"
})
@XmlRootElement(name = "showNewsResponse")
public class ShowNewsResponse {

    protected List<NewsArticleDTO> newsArticleDTO;

    /**
     * Gets the value of the newsArticleDTO property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the newsArticleDTO property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNewsArticleDTO().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NewsArticleDTO }
     * 
     * 
     */
    public List<NewsArticleDTO> getNewsArticleDTO() {
        if (newsArticleDTO == null) {
            newsArticleDTO = new ArrayList<NewsArticleDTO>();
        }
        return this.newsArticleDTO;
    }

    public void setNewsArticleDTO(List<NewsArticleDTO> list) {
       this.newsArticleDTO.addAll(list);
    }

}
