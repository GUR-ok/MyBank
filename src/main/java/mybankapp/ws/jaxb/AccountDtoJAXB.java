package mybankapp.ws.jaxb;

import mybankapp.dto.CurrencyAccountDTO;
import mybankapp.dto.PersonDTO;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.File;

public class AccountDtoJAXB {

    public void marshall(CurrencyAccountDTO currencyAccountDTO){
        try {
            JAXBContext context = JAXBContext.newInstance(PersonDTO.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            File file = new File( "src/main/resources/schemas/accountdto.xml" );
            marshaller.marshal(currencyAccountDTO, file);
        } catch (Exception e) {
            System.out.println(""+e.getMessage());
        }
    }
}
