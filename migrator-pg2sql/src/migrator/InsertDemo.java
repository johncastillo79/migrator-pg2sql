/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package migrator;

import migrator.configuration.ApplicationConfig;
import migrator.services.DestinoService;
import migrator.services.OrigenService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

/**
 *
 * @author desktop
 */
public class InsertDemo {

    public static void main(String[] args) {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        DestinoService destino = (DestinoService) context.getBean("destinoService");
        
        destino.insertA();
    }
}
