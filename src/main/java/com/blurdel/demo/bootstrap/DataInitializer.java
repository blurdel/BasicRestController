package com.blurdel.demo.bootstrap;

import com.blurdel.demo.model.Person;
import com.blurdel.demo.services.PersonService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile({"bootstrap"})
@Component
public class DataInitializer implements CommandLineRunner {

    private final PersonService personService;

    public DataInitializer(PersonService pService) {
        this.personService = pService;
    }

    @Override
    public void run(String... args) throws Exception {
        personService.deleteAll();
        personService.add(new Person(null, "Zoey!", 15));
    }

}
