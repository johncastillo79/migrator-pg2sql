/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package migrator.dao;

import java.util.List;
import java.util.Map;

/**
 *
 * @author desktop
 */
public interface DestinoDao {

    public List<Map<String, Object>> ListData(String sql);
    
    public void insertA();
}
