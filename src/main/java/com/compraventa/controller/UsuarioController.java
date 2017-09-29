package com.compraventa.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;


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

		List<Usuarios> listaUsus = personal.getAll();
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

			personal.persist(usuario);
			return "redirect:/list";
		
	}	
	
	@RequestMapping(value="/edit-user-{idusuario}", method=RequestMethod.GET)
	public String upDateUsuario(@PathVariable("idusuario") Integer idusuario, Model model){		
		
		Usuarios usu = personal.get(idusuario);			
		model.addAttribute("usuario", usu);		
		model.addAttribute("edit", true);
		return "Usuarios/actualizaUsuarios";
		
	}
	
	@RequestMapping(value="/edit-user-{idusuario}", method= RequestMethod.POST)
	public String ejecutaUpdate(@ModelAttribute("usuario") @Validated Usuarios usua, BindingResult result, Model modelo, @PathVariable("idusuario") Integer idusuario){		

		if (result.hasErrors()) {
			return "Usuarios/actualizaUsuarios";
		}
		
		personal.update(usua);
		
		return "redirect:/list";		
				
		//ejemplos de redireccion
				//return "redirect:/cargaUsuarios";						
				//return "redirect:/cargaUsuarios";			
		
	}
	
	@RequestMapping(value="/delete-user-{idusuario}", method = RequestMethod.GET)
	public String borrar(@PathVariable("idusuario") Integer idusuario){		
		
		Usuarios usu = personal.get(idusuario);
		personal.remove(usu);			
		return "redirect:/list";
		
	}	

	
	
	//si llamo a este metodo desde un boton seria @RequestMapping(value = "cargaUsuarios", method = RequestMethod.GET)

		@RequestMapping(value = "/cargaUsuarios", method = RequestMethod.GET)
		public String loadUsuarios(Model model) {

			List<Usuarios> listaUsus = personal.getAll();

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
		
		
		//con los siguientes metodos hacemos el crud para las llamadas   desde el proyecto angular externo por lo que seria mejor devolver la llamada a traves de un 
		//ResponseEntityque va a empaquetar la respuesta
		
		@RequestMapping(value="/usuarios", method=RequestMethod.GET)
		public @ResponseBody List<Usuarios> getAllUsuarios(){
			
			logger.debug("provider has received request to get all persons");			
			List<Usuarios> listadoUsuarios = personal.getAll();			
			return listadoUsuarios;			
		}
		
		/*  el metodo anterios tambien se puede hacer con ResponseEntity   y seria lo correcto
	
 			@RequestMapping(value = "/user/", method = RequestMethod.GET)
    		public ResponseEntity<List<User>> listAllUsers() {
        	List<User> users = userService.findAllUsers();
        	if(users.isEmpty()){
            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        	}
        	return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }
				
		 */		
		
		
		// metodo para obtener un registro
		@RequestMapping(value="/usuarios-{idusuario}", method=RequestMethod.GET, headers="Accept=application/xml, application/json, text/plain", produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Usuarios> getUserById(@PathVariable("idusuario") Integer idusuario){		
			
			
			System.out.println("Fetching User with id " + idusuario);
			Usuarios user = personal.get(idusuario);
			if(user== null){
				System.out.println("User with id " + idusuario + "not found");
				
				return new ResponseEntity<Usuarios>(HttpStatus.NOT_FOUND);
			}
			HttpHeaders headers = new HttpHeaders();				
			return new ResponseEntity<Usuarios>(user, headers, HttpStatus.OK);
			
		}
		
		
		//metodo para actualizar un registro
		
		@RequestMapping(value="/usuarios-{id}", method=RequestMethod.PUT, headers="Accept=application/xml, application/json, text/plain", produces=MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Void> upDateUser(@PathVariable("id") Integer idusuario, @RequestBody Usuarios user){
			
			System.out.println("Updating User " + idusuario);
			
			Usuarios currentUser = personal.get(idusuario);
			
			if(currentUser == null){
				System.out.println("User with id "+ idusuario +" not found");
				return  new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
			}			
			
			personal.update(user);
			
			HttpHeaders header= new HttpHeaders();			
			
			return new ResponseEntity<Void>(header, HttpStatus.OK);
		}
		
		
		//metodo para eliminar un registro
		
		@RequestMapping(value="/usuarios-{id}", method=RequestMethod.DELETE, headers = "Accept=application/xml, application/json, text/plain", produces=MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Void> deleteUser(@PathVariable("id") Integer idusuario){
			
			System.out.println("Fetching & Deleting User with id " + idusuario);
			
			Usuarios user = personal.get(idusuario);
			
			if( user == null ){
				System.out.println("Unable to delete. User with id " + idusuario + " not found");
				return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
				
			}
			
			Usuarios usu = personal.get(idusuario);
			personal.remove(usu);			
			HttpHeaders header = new HttpHeaders();			
			return new ResponseEntity<Void>(header, HttpStatus.NO_CONTENT);
			
		}
		
		
		@RequestMapping(value="/user", method=RequestMethod.POST, headers="Accept=application/xml, application/json", produces = "application/json")
		public ResponseEntity<Void> createUser(@RequestBody Usuarios user, UriComponentsBuilder ucBuilder){
			
			System.out.println("Creating User " + user.getNombre());
			
			if (personal.isNotUserClaveUnique(user.getClave())) {
				
				System.out.println("A User with name " + user.getNombre() + "already exist");
				return new ResponseEntity<Void>(HttpStatus.CONFLICT);
			}
			
			personal.persist(user);
			
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getIdusuario()).toUri());
			
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		}

	
		
		
		
		
		
		
		


}




