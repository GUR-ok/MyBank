package mybankapp.ws.jaxb;

import mybankapp.dto.TransactionDTO;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.File;

public class TransactionDtoJAXB {

    public void marshall(TransactionDTO transactionDTO){
        try {
            JAXBContext context = JAXBContext.newInstance(TransactionDTO.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            File file = new File( "src/main/resources/schemas/transactiondto.xml" );
            marshaller.marshal(transactionDTO, file);
        } catch (Exception e) {
            System.out.println(""+e.getMessage());
        }
    }
}
