package dominio;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "PERSONA")
public class Persona implements Serializable  {
	@Id
	@Column(name = "dni")
	private int dni;
	@Column(name = "apellido")
	private String apellido;
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "sexo")
	private char sexo;
	
	public Persona() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Persona(int dni, String apellido, String nombre, char sexo) {
		super();
		this.dni = dni;
		this.apellido = apellido;
		this.nombre = nombre;
		this.sexo = sexo;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}
	
	public String toString() {
		return dni + " " + apellido + ", " + nombre + " (" + sexo + ")";
	}
	
}
