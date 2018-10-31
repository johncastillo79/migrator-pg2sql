/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package migrator.dao;

import java.util.List;
import java.util.Map;
import migrator.benas.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("origenDao")
public class OrigenDaoImpl implements OrigenDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void addPerson(Person person) {
        jdbcTemplate.update("INSERT INTO person (first_name, last_name, age) VALUES (?, ?, ?)",
                person.getFirstName(), person.getLastName(), person.getAge());
        System.out.println("Person Added!!");
    }

    public void editPerson(Person person, int personId) {
        jdbcTemplate.update("UPDATE person SET first_name = ? , last_name = ? , age = ? WHERE person_id = ? ",
                person.getFirstName(), person.getLastName(), person.getAge(), personId);
        System.out.println("Person Updated!!");
    }

    public void deletePerson(int personId) {
        jdbcTemplate.update("DELETE FROM person WHERE person_id = ? ", personId);
        System.out.println("Person Deleted!!");
    }

    public Person find(int personId) {
        Person person = (Person) jdbcTemplate.queryForObject("SELECT * FROM person where person_id = ? ",
                new Object[]{personId}, new BeanPropertyRowMapper(Person.class));
        return person;
    }

    public List< Person> findAll() {
        List< Person> persons = jdbcTemplate.query("SELECT * FROM person", new BeanPropertyRowMapper(Person.class));
        return persons;
    }
    
    public List<Map<String, Object>> ListData(String sql) {
        return jdbcTemplate.queryForList(sql);
    }
    
    public List<Map<String, Object>> findDetail(Object get) {
        return jdbcTemplate.queryForList("select * from registro.reg_datos_evento where id_evento = ?", get);
    }
}
