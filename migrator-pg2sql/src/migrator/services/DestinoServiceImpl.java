/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package migrator.services;

import java.util.List;
import java.util.Map;
import migrator.dao.DestinoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author desktop
 */
@Service("destinoService")
public class DestinoServiceImpl implements DestinoService {

    @Autowired
    DestinoDao destinoDao;
    
    public List<Map<String, Object>> listData() {
        String sql = "select * from registro.reg_datos_evento";
        return destinoDao.ListData(sql);
    }

    public void insertA() {
        destinoDao.insertA();
    }
    
    public void insertMap(Map<String, Object> parameters) {
        destinoDao.insertMap(parameters);
    }
}
