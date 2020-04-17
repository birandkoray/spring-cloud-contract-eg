package spring.cloud.contract.producer.service;

import data.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import spring.cloud.contract.producer.service.stream.PersonStreamSender;

@RestController
public class PersonRestController {

    @Autowired
    PersonStreamSender personStreamSender;

    @Autowired
    PersonService personService;

    @GetMapping("/person/{id}")
    public Person findPersonById(@PathVariable("id") Long id) {
        return personService.findPersonById(id);
    }

    @GetMapping("/personsend/{id}")
    public Person findPersonByIdAndSend(@PathVariable("id") Long id) {
        Person person = personService.findPersonById(id);
        personStreamSender.sendPerson(person);
        return person;
    }
}
