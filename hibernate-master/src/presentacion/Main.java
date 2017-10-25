package presentacion;

import dominio.Persona;
import persistencia.hibernate.HibernateUtil;

public class Main {

    public static void main(String[] args) throws Exception {
             PersonaController personaController = new PersonaController();
             Persona persona = new Persona(38696030,"adri","rad",'a');
             personaController.guardar(persona);
             for(Persona i : personaController.consultarPersonas()) {
            	 System.out.println(i);
             }
             HibernateUtil.getSessionFactory().close();
     }
 }

