package presentacion;

import dominio.Persona;
import persistencia.hibernate.HibernateUtil;

public class Main {

    public static void main(String[] args) throws Exception {
    	
    	///POR OPERACION-------------------------------------------------
    	HibernateUtil.abrirSessionEnHilo();
	    PersonaController personaController = new PersonaController();
	    Persona persona = new Persona(386960304,"a4dri","rad",'a');
	    personaController.guardar(persona);
	    for(Persona i : personaController.consultarPersonas()) {
	    	 System.out.println(i);
	    }
     	HibernateUtil.cerrarSessionEnHilo();
     	//-----------------------------------------------------------------
     	
     	
     	HibernateUtil.eliminarSessionFactory();
     }
 }

