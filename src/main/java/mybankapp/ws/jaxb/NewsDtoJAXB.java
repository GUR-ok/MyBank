package mybankapp.ws.jaxb;

import mybankapp.dto.NewsArticleDTO;
import mybankapp.dto.PersonDTO;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.File;

public class NewsDtoJAXB {

    public void marshall(NewsArticleDTO newsArticleDTO){
        try {
            JAXBContext context = JAXBContext.newInstance(PersonDTO.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            File file = new File( "src/main/resources/schemas/newsdto.xml" );
            marshaller.marshal(newsArticleDTO, file);
        } catch (Exception e) {
            System.out.println(""+e.getMessage());
        }
    }
}
