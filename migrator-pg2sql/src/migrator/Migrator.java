/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package migrator;

import java.util.List;
import migrator.benas.Person;
import migrator.configuration.ApplicationConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import migrator.services.OrigenService;

/**
 *
 * @author desktop
 */
public class Migrator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        OrigenService personService = (OrigenService) context.getBean("personService");

        Person yashwant = new Person(1, "Yashwant", "Chavan", 32);
        Person mahesh = new Person(2, "Mahesh", "Patil", 25);
        Person vishal = new Person(3, "Vishal", "Naik", 40);

        personService.addPerson(yashwant);
        personService.addPerson(mahesh);
        personService.addPerson(vishal);

        System.out.println("Find All");
        List< Person> persons = personService.findAll();
        for (Person person : persons) {
            System.out.println(person);
        }

        System.out.println("Delete person Id = 3");
        int deleteMe = 3;
        personService.deletePerson(deleteMe);

        yashwant.setFirstName("Yashwant - Updated");
        yashwant.setLastName("Chavan - Updated");
        yashwant.setAge(40);

        System.out.println("Update person Id = 1");
        int updateMe = 1;
        personService.editPerson(yashwant, updateMe);

        System.out.println("Find person Id = 2");
        Person person = personService.find(2);
        System.out.println(person);

        System.out.println("Find All Again");
        persons = personService.findAll();
        for (Person p : persons) {
            System.out.println(p);
        }

        context.close();
    }

}
