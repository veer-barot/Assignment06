/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package builtIt13;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 *
 * @author c0719943
 */
@ApplicationPath("api")
public class ApplicationConfiq extends Application {
    public Set<Class<?>> getClasses() {
       Set<Class<?>> result = new HashSet<>();
       result.add(ProductCodeRest.class);
       return result;
    }
}
