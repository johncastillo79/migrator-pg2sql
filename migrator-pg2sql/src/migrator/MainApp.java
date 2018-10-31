/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package migrator;

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
    public static void main(String[] args) {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        OrigenService origen = (OrigenService) context.getBean("origenService");
        DestinoService destino = (DestinoService) context.getBean("destinoService");
        // TODO
        // List Master
        List<Map<String, Object>> ms = origen.listMaster();
        //for (Map<String, Object> m : ms) {
        Map<String, Object> m = ms.get(0);
            System.out.println("Master event: " + m.get("id_evento"));

            // Select Detalle
            List<Map<String, Object>> dt = origen.listDetail(m.get("id_evento"));
            
            // insertable map
            Map<String, Object> in = new HashMap<>();
            for (Map<String, Object> d : dt) {
                System.out.println(d.get("descripcion_plantilla"));
                // process data
                MainApp.parseJson(d);
            }
        //}

        context.close();
    }

    public static Map<String, Object> parseJson(Map<String, Object> en) {
        Map<String, Object> obj = new HashMap<>();
        // 
        Integer id_plantilla = (Integer) en.get("id_plantilla");
        switch (id_plantilla) {
            case 86: // "	DATOS_CABECERA_PARTIDA_DEFUNCION	"
                PGobject ob = (PGobject) en.get("atributo_evento");
                JsonProcessor jp = new JsonProcessor(ob.getValue());
                jp.printAttributes();
                break;
            case 87: // "	DATOS_FALLECIDO	"
                break;
            case 88: // "	DATOS_FALLECIMIENTO	"
                break;
            case 89: // "	DATOS_DECLARANTE_FALLECIMIENTO	"
                break;
            case 91: // "	DATOS_CERTIFICACION_FALLECIMIENTO	"
                break;
            case 92: // "	DATOS_PRIMER_TESTIGO_FALLECIMIENTO	"
                break;
            case 93: // "	DATOS_SEGUNDO_TESTIGO_FALLECIMIENTO	"
                break;
            case 94: // "	OBSERVACION_DEFUNCION	"
                break;
            default:
                System.out.println("---- Ninguno de los ID_PLANTILLA considerados: " + id_plantilla);
        }
        return obj;
    }
}
