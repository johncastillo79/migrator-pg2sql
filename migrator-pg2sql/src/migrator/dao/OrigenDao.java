package migrator.dao;

import java.util.List;
import java.util.Map;
import migrator.benas.Person;

public interface OrigenDao {

    public void addPerson(Person person);

    public void editPerson(Person person, int personId);

    public void deletePerson(int personId);

    public Person find(int personId);

    public List< Person> findAll();
    
    public List<Map<String, Object>> ListData(String sql);

    public List<Map<String, Object>> findDetail(Object get);
}
