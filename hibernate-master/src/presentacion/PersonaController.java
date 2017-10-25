package presentacion;
import persistencia.dao.PersonaDAO;
import persistencia.dao.impl.PersonaDAOImplHibernate;
import java.util.List;
import dominio.Persona;

public class PersonaController {
    private PersonaDAO personaDAO;

    public PersonaController() {
    	personaDAO=new PersonaDAOImplHibernate();//CAMBIANDO ESTA LINEA IMPLEMENTO OTRO TIPO DE DAO
    }

    public void guardar(Persona persona) throws Exception {
    	personaDAO.guardarOActualizar(persona);
    }
    
    public List<Persona> consultarPersonas() throws Exception {
    	return personaDAO.traerTodos();
    }
}
