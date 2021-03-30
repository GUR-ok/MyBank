package mybankapp.ws.org.mybankapp.auth.wsdl;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "refreshtoken",
})
@XmlRootElement(name = "refreshRequest")
public class RefreshRequest {
    @XmlElement(required = true)
    protected String refreshtoken;

    public String getRefreshtoken() {
        return refreshtoken;
    }

    public void setRefreshtoken(String refreshtoken) {
        this.refreshtoken = refreshtoken;
    }
}
