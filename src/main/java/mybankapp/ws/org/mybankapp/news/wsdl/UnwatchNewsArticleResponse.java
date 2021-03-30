package mybankapp.ws.org.mybankapp.news.wsdl;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "newsId"
})
@XmlRootElement(name = "unwatchNewsArticleResponse")
public class UnwatchNewsArticleResponse {

    @XmlElement(required = true)
    protected long newsId;

    public long getNewsId() {
        return newsId;
    }

    public void setNewsId(long newsId) {
        this.newsId = newsId;
    }
}
