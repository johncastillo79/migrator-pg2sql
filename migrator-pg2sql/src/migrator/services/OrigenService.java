package migrator.services;

import java.util.List;
import java.util.Map;
import migrator.benas.Person;

public interface OrigenService {
    
    // Dao ejemplos

    public void addPerson(Person person);

    public void editPerson(Person person, int personId);

    public void deletePerson(int personId);

    public Person find(int personId);

    public List<Person> findAll();
    
    // Origen
    
    public List<Map<String, Object>> listData();

    public List<Map<String, Object>> listMaster();

    public List<Map<String, Object>> listDetail(Object get);
    
}
