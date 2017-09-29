package com.compraventa.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;

import com.compraventa.entidades.Categoria;
import com.compraventa.entidades.Seccion;
import com.compraventa.entidades.Usuarios;
import com.compraventa.service.CategoriaManger;
import com.compraventa.service.SubCategoriaManager;


@Controller
public class CategoriaController {

	@Autowired	
	CategoriaManger categoriaMan;
	
	@Autowired
	SubCategoriaManager  subcateg;
	
	
	@RequestMapping(value="insertaCategoria", method=RequestMethod.GET)
	public String cargaFormCategoria(Model model){
		
		model.addAttribute("categoria", new Categoria());		
		return "Categoria/insertaCategoria";
	}
	
	@RequestMapping(value="insertaCateg", method=RequestMethod.POST)
	public String insertaCategoria(@ModelAttribute("categoria") @Validated Categoria categoria, BindingResult result, Model model){
		
		categoriaMan.persist(categoria);	
		return "Categoria/insertaCategoria";
	}
	
	
	/*@ModelAttribute("listaSubCategorias")
	public List<Subcategoria> listaSubCategoria(){
		
		return subcateg.listaSubCategoriaManag();
		
	}*/
	
	//con los siguientes metododos recuperamos objetos y listado de objetos
	
	@RequestMapping(value="/categoria", method= RequestMethod.GET, headers="Accept=application/xml, application/json")
	public @ResponseBody List<Categoria> getAllCategoria(){		
		
		List<Categoria> listaCategorias = categoriaMan.getAll();		
		return listaCategorias;
	}
	
	
	//obtendremos las categorias de una seccion.
	
	
	@RequestMapping(value="/categoria", method=RequestMethod.POST, headers="Accept=application/xml, application/json", produces = "application/json")
	public @ResponseBody List<Categoria> getAllCategoria(@RequestBody Seccion seccion, UriComponentsBuilder ucBuilder){
		
		System.out.println("Obteniendo nombre de la seccion " + seccion.getNomseccion());	
		
		List<Categoria> listaCategorias  = categoriaMan.getCategoryBySeccion(seccion);		
		
		return listaCategorias;
	}
	
	
	
	
}
