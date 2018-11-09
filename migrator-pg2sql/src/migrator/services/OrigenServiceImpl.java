package migrator.services;

import java.util.List;
import java.util.Map;
import migrator.benas.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import migrator.dao.OrigenDao;

@Service("origenService")
public class OrigenServiceImpl implements OrigenService {

    @Autowired
    OrigenDao origenDao;

    public void addPerson(Person person) {
        origenDao.addPerson(person);

    }

    public void editPerson(Person person, int personId) {
        origenDao.editPerson(person, personId);
    }

    public void deletePerson(int personId) {
        origenDao.deletePerson(personId);
    }

    public Person find(int personId) {
        return origenDao.find(personId);
    }

    public List< Person> findAll() {
        return origenDao.findAll();
    }

    // Origen
    public List<Map<String, Object>> listData() {
        String sql = "select * from registro.reg_datos_evento";
        return origenDao.ListData(sql);
    }

    public List<Map<String, Object>> listMaster() {
        String sql = "select * from registro.maestro";
        return origenDao.ListData(sql);
    }

    public List<Map<String, Object>> listDetail(Object get) {
        return origenDao.findDetail(get);
    }

    public List<Map<String, Object>> paises() {
        String sql = "select * from geografia.pais";
        return origenDao.ListData(sql);
    }

    public List<Map<String, Object>> localidades() {
        String sql = "select * from geografia.localidad";
        return origenDao.ListData(sql);
    }
}
