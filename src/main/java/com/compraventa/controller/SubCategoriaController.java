package com.compraventa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.compraventa.entidades.Subcategoria;
import com.compraventa.service.CategoriaManger;
import com.compraventa.service.SubCategoriaManager;


@Controller
public class SubCategoriaController {
	
	
	@Autowired
	SubCategoriaManager subcat;
	
	@Autowired	
	CategoriaManger categoriaMan;
		
	
	@RequestMapping(value="InsertaSubCategoria", method=RequestMethod.GET)
	public String cargaFormSubCategoria(Model model){
		
		model.addAttribute("subcategoria", new Subcategoria());
		
		return "Subcategoria/InsertaSubcategoria";
		
	}
		
	@RequestMapping(value="insertaSubCatego", method=RequestMethod.POST)
	public String insertarSubCategoria(@ModelAttribute("subcategoria") @Validated Subcategoria subcateg, BindingResult result, Model model ){
		
		subcat.persist(subcateg);
		
		
		return "Subcategoria/InsertaSubcategoria";
		
		
	}
	
	@ModelAttribute("listaCategorias")
	public List<Categoria> listaCategoria(){
		
		return categoriaMan.getAll();
		
	}
	
	
	
	//con los siguientes metododos recuperamos objetos y listado de objetos
	
	@RequestMapping(value="/subcategoria", method=RequestMethod.GET, headers="Accept=application/xml, application/json")
	public @ResponseBody List<Subcategoria> getAllSubcategoria(){	
		
		List<Subcategoria> listaSubcategoria = subcat.getAll();
		return listaSubcategoria;
		
		
		
		
	}
	
	//obtenemos las subcategorias de una categoria
	
	@RequestMapping(value="/subcategoria", method=RequestMethod.POST, headers="Accept=application/xml, application/json", produces="application/json")
	public @ResponseBody List<Subcategoria> getAllSubCategorias(@RequestBody Categoria categoria, UriComponentsBuilder ucBuilder ){
		
		
		List<Subcategoria> listaSubcategoria = subcat.getSubcategoriaByCategoria(categoria);
		
		
		
		
		return listaSubcategoria;
		
		
		
		
	}

}
