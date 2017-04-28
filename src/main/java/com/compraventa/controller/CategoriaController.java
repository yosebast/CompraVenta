package com.compraventa.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.compraventa.entidades.Categoria;
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
		
		categoriaMan.insertarCategory(categoria);		
		return "Categoria/insertaCategoria";
	}
	
	
	/*@ModelAttribute("listaSubCategorias")
	public List<Subcategoria> listaSubCategoria(){
		
		return subcateg.listaSubCategoriaManag();
		
	}*/
	
	
}
