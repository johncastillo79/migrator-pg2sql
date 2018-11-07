/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package migrator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import migrator.configuration.ApplicationConfig;
import migrator.services.DestinoService;
import migrator.services.OrigenService;
import org.postgresql.util.PGobject;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

/**
 *
 * @author desktop
 */
public class MainApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        OrigenService origen = (OrigenService) context.getBean("origenService");
        DestinoService destino = (DestinoService) context.getBean("destinoService");
        // TODO
        // List Master
        List<Map<String, Object>> ms = origen.listMaster();
        // integer ID
        int i = 1;
        for (Map<String, Object> m : ms) {
            // Map<String, Object> m = ms.get(0);
            // System.out.println("Master event: " + m.get("id_evento"));

            // Select Detalle
            List<Map<String, Object>> dt = origen.listDetail(m.get("id_evento"));

            // insertable map
            Map<String, Object> in = new HashMap<>();
            in.put("\"IdCiudadano\"", i);
            for (Map<String, Object> d : dt) {
                System.out.println(d.get("descripcion_plantilla"));
                // process data
                in.putAll(MainApp.parseJson(d));
            }
            System.out.println(in);
            destino.insertMap(in);

            // next id
            i++;
        }

        context.close();
    }

    public static Map<String, Object> parseJson(Map<String, Object> en) throws ParseException {
        Map<String, Object> obj = new HashMap<>();
        // procesar el archivo: PGobject.class contiene logica para procesar el json 
        Integer id_plantilla = (Integer) en.get("id_plantilla");
        PGobject ob = (PGobject) en.get("atributo_evento");
        JsonProcessor jp = new JsonProcessor(ob.getValue());
        // print attributes, se puede comentar
        jp.printAttributes();

        boolean sw = false; // para controlar si se ejecuta alguna accion

        if (id_plantilla == 86) { // DATOS_CABECERA_PARTIDA_DEFUNCION
            // sacar values 
            String FechaInscripcion = jp.getJson().getString("fecha_inscripcion");
            Integer TrnPartDefuncion = jp.getJson().getInt("numero_oficialia");
            // set principal 
            obj.put("\"TrnPartDefuncion\"", TrnPartDefuncion);
            obj.put("\"FechaInscripcion\"", MainApp.parseDate(FechaInscripcion, "dd/MM/yyyy"));
            sw = true;
        }
        if (id_plantilla == 87) { // DATOS_FALLECIDO
            // sacar values 
            String NombresFallecido = jp.getJson().getString("nombres");

            // set principal 
            obj.put("\"NombresFallecido\"", NombresFallecido);
            sw = true;
        }
        if (id_plantilla == 88) { // DATOS_FALLECIMIENTO
            sw = true;
        }
        if (id_plantilla == 89) { // DATOS_DECLARANTE_FALLECIMIENTO
            sw = true;
        }
        if (id_plantilla == 91) { // DATOS_CERTIFICACION_FALLECIMIENTO
            sw = true;
        }
        if (id_plantilla == 92) { // DATOS_PRIMER_TESTIGO_FALLECIMIENTO
            sw = true;
        }
        if (id_plantilla == 93) { // DATOS_SEGUNDO_TESTIGO_FALLECIMIENTO
            sw = true;
        }
        if (id_plantilla == 94) { // OBSERVACION_DEFUNCION
            sw = true;
        }

        // control despues delproceso
        if (!sw) {
            System.out.println("---- Ninguno de los ID_PLANTILLA considerados: " + id_plantilla);
        }

        return obj;
    }

    public static Date parseDate(String src, String pattr) throws ParseException {
        DateFormat df = new SimpleDateFormat(pattr);
        return df.parse(src);
    }
}
