package ru.job4j.serialization.xml;

import ru.job4j.serialization.json.Task;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import java.io.StringWriter;
import java.text.DateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

@XmlRootElement(name = "feature")
@XmlAccessorType(XmlAccessType.FIELD)
public class Feature {

    @XmlAttribute
    private int numStory;

    @XmlAttribute
    private String nameFeature;

    private Task task;


    @XmlElementWrapper(name = "checkPoints")
    @XmlElement(name = "checkPoint")
    private Date[] checkPoints;

    @XmlAttribute
    private boolean isClose;

    public Feature() {
    }

    public Feature(int numStory, String nameFeature, Task task, Date[] checkPoints, boolean isClose) {
        this.numStory = numStory;
        this.nameFeature = nameFeature;
        this.task = task;
        this.checkPoints = checkPoints;
        this.isClose = isClose;
    }

    public static void main(String[] args) throws JAXBException {
        Feature ft = new Feature(2, "New Mega Feature Ever", new Task(false),
                new Date[]{Date.from(Instant.now()), Date.from(LocalDate.parse("2032-12-31").
                        atStartOfDay(ZoneId.of("Asia/Tokyo")).toInstant())}, false);

        JAXBContext context = JAXBContext.newInstance(Feature.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(ft, writer);
            String result = writer.getBuffer().toString();
            System.out.println(result);
        } catch (Exception e) {

        }
    }
}
