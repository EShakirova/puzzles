package services;

import javax.xml.bind.*;
import java.io.File;

public class ClassSerializeXML {

    public static void classInXML( Object object, String filename) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance("ru/inno/classesFromXSD");
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(object, new File(filename));
    }

    public static Object classFromXML(String filename)throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance("ru/inno/classesFromXSD");
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return unmarshaller.unmarshal(new File(filename));
    }
}
