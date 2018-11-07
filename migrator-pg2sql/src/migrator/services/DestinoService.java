/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package migrator.services;

import java.util.List;
import java.util.Map;

/**
 *
 * @author desktop
 */
public interface DestinoService {

    public List<Map<String, Object>> listData();
    
    public void insertA();
    
    public void insertMap(Map<String, Object> parameters);
}
