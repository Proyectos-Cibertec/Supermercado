package servicio;
import java.util.Optional;

import entidad.Empleado;
import model.LoginModel;

public class ServicioLogin {
	private LoginModel loginModel = new LoginModel();
	
	public Optional<Empleado> login(String usuario, String password) {
		return Optional.ofNullable(loginModel.login(usuario, password));
	}
}
