package mybankapp.ws.org.mybankapp.bank.wsdl;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "updateAccountResponse")
public class UpdatePersonResponse {

    @XmlElement(required = true)
    protected String uuid;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

}
