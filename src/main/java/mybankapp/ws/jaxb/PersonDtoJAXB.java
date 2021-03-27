package mybankapp.ws.jaxb;
import mybankapp.dto.PersonDTO;

import javax.xml.bind.*;
import java.io.*;

public class PersonDtoJAXB {

    public void marshall(PersonDTO personDTO){
        try {
            JAXBContext context = JAXBContext.newInstance(PersonDTO.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            File file = new File( "src/main/resources/schemas/persondto.xml" );
            marshaller.marshal(personDTO, file);
        } catch (Exception e) {
            System.out.println(""+e.getMessage());
        }
    }

}
