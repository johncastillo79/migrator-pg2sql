/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package migrator.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

/**
 *
 * @author desktop
 */
@Repository
@Qualifier("destinoDao")
public class DestinoDaoImpl implements DestinoDao {

    @Autowired
    JdbcTemplate jdbcTemplate1;

    public List<Map<String, Object>> ListData(String sql) {
        return jdbcTemplate1.queryForList(sql);
    }
    /**
     * Primera implementacion de insert
     */
    public void insertA() {        
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate1.getDataSource())
                .withTableName("\"TrnPartDefuncionDemo\"")
                .usingColumns("\"IdCiudadano\"", "\"FechaInscripcion\"", "\"NombresFallecido\"", "\"TrnPartDefuncion\"")
                .withSchemaName("public");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("\"IdCiudadano\"", 101);
        parameters.put("\"FechaInscripcion\"", new Date());
        parameters.put("\"NombresFallecido\"", "Joe Dev");
        parameters.put("\"TrnPartDefuncion\"", 5);
        
        simpleJdbcInsert.execute(parameters);
    }
    /**
     * Segunda implementacion de insert, sql nativo
     * @param params DEMO
     */
    public void insertB(Map<String, Object> params) {
        String sql = "INSERT INTO public.\"TrnPartDefuncionDemo\" ("
                + "\"IdCiudadano\", "
                + "\"FechaInscripcion\", "
                + "\"NombresFallecido\", "
                + "\"TrnPartDefuncion\""
                + ") VALUES (?, ?, ?, ?)";
        jdbcTemplate1.update(sql,
                100, new Date(), "Joe Dev", 5);
        System.out.println("Insert success!!");
    }
    
    public void insertMap(Map<String, Object> params) {        
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate1.getDataSource())
                .withTableName("\"TrnPartDefuncionDemo\"")
                .usingColumns("\"IdCiudadano\"", "\"FechaInscripcion\"", "\"NombresFallecido\"", "\"TrnPartDefuncion\"")
                .withSchemaName("public");
        
        simpleJdbcInsert.execute(params);
    }
}
