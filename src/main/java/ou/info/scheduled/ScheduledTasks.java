package ou.info.scheduled;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ou.info.entity.Person;
import ou.info.repository.PersonRepository;

import java.io.*;

@Component
@PropertySource("classpath:/application.properties")
public class ScheduledTasks {
    private final PersonRepository personRepository;

    @Autowired
    public ScheduledTasks(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Scheduled(fixedRate = 3600000)
    public void reportCurrentTime() throws IOException {
        File file = new File("E:/Сливы/Recruter/recruter.txt");
        FileReader fr = new FileReader(file);
        BufferedReader reader = new BufferedReader(fr);
        String line;
        while ((line = reader.readLine()).length() > 0)
        {
            String lines[] = line.split("\t");
            Person person = new Person();
            person.setName(lines[0]);
            person.setBirth(lines[1]);
            person.setAddress(lines[2]);
            person.setPassportNumber(lines[3]);
            person.setPassport(lines[4]);
            personRepository.save(person);
        }
        System.out.println("Success!");
    }
}
