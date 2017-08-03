package com.compraventa.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.compraventa.entidades.Productos;
import com.compraventa.entidades.Usuarios;
import com.compraventa.service.UsuarioManager;

@Controller
public class UsuarioController {
	
	protected static Logger logger = Logger.getLogger("UsuarioController");

	@Autowired
	UsuarioManager personal;
	
	@Autowired
	HttpServletRequest request;
	
	//insertmos un bean de message ya que tenemos este bean messageSource definido en el contexto
	@Autowired
	MessageSource messageSource;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String muestraEntrada(Model model) {

		List<Usuarios> listaUsus = personal.cargaUsuariosServicio();
		model.addAttribute("listaUsuarios", listaUsus);	
		return "Usuarios/ListadoUsuarios";
	}
	
	@RequestMapping(value="/addUsuario", method=RequestMethod.GET)
	public String NewUser(Model model){
		
		Usuarios user = new Usuarios();
		model.addAttribute("usuario", user);
		model.addAttribute("edit", false);
		return "Usuarios/actualizaUsuarios";
	}	

	@RequestMapping(value = "/addUsuario", method = RequestMethod.POST)
	public String addUsuario(@ModelAttribute("usuario") @Validated Usuarios usuario, BindingResult result,
			Model model) {		
	
		if (result.hasErrors()) {
			return "Usuarios/actualizaUsuarios";
			}
		
		// comprobamos si el usuario que se va a insertar es unico y no esta repetido  para que cuando se le envie no vaya a petar  en este caso no estara repetido por que la key es unica y no depende de que
		//la escriba el usuario  si no que se incrementa automaticamente asi que lo incluyo por si hubiera un campo que no tendria que reperirse  yo voy a ser que el campo clave no se repita..		
		
		if (personal.isNotUserClaveUnique(usuario.getClave())) {
			
			FieldError claveError = new FieldError("usuario", "clave", messageSource.getMessage("non.unique.clave", null, "new String[]{usuario.getClave()}", Locale.getDefault()));
			result.addError(claveError);
			return "Usuarios/actualizaUsuarios";
		}

			personal.insertUsuario(usuario);				
			return "redirect:/list";
		
	}	
	
	@RequestMapping(value="/edit-user-{idusuario}", method=RequestMethod.GET)
	public String upDateUsuario(@PathVariable("idusuario") Integer idusuario, Model model){		
		
		Usuarios usu = personal.getById(idusuario);			
		model.addAttribute("usuario", usu);		
		model.addAttribute("edit", true);
		return "Usuarios/actualizaUsuarios";
		
	}
	
	@RequestMapping(value="/edit-user-{idusuario}", method= RequestMethod.POST)
	public String ejecutaUpdate(@ModelAttribute("usuario") @Validated Usuarios usua, BindingResult result, Model modelo, @PathVariable("idusuario") Integer idusuario){		

		if (result.hasErrors()) {
			return "Usuarios/actualizaUsuarios";
		}
		
		personal.updateUsuario(usua, idusuario);
		
		return "redirect:/list";		
				
		//ejemplos de redireccion
				//return "redirect:/cargaUsuarios";						
				//return "redirect:/cargaUsuarios";			
		
	}
	
	@RequestMapping(value="/delete-user-{idusuario}", method = RequestMethod.GET)
	public String borrar(@PathVariable("idusuario") Integer idusuario){		
		
		personal.deleteUsuario(idusuario);				
		return "redirect:/list";
		
	}	

	
	
	//si llamo a este metodo desde un boton seria @RequestMapping(value = "cargaUsuarios", method = RequestMethod.GET)

		@RequestMapping(value = "/cargaUsuarios", method = RequestMethod.GET)
		public String loadUsuarios(Model model) {

			List<Usuarios> listaUsus = personal.cargaUsuariosServicio();

			model.addAttribute("listaUsuarios", listaUsus);

			for (Usuarios usua : listaUsus) {

				String nombre = usua.getNombre();

				if (nombre.equals("Sebastian")) {
					// recogemos solo los nombres

					System.out.println("prefecto");

				}

			}

			// model.addAttribute("usuario", new Usuarios());

			// return "showMessage";

			return "Usuarios/ListadoUsuarios";

		}
		
		
		//con los siguientes metodos recuperamos objetos y listados de objetos
		
		@RequestMapping(value="/usuarios", method=RequestMethod.GET)
		public @ResponseBody List<Usuarios> getAllUsuarios(){
			
			logger.debug("provider has received request to get all persons");
			
			List<Usuarios> listadoUsuarios = personal.cargaUsuariosServicio();
			
			return listadoUsuarios;
			
			
		}	
		
		


}




