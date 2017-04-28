package com.compraventa.controller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.compraventa.entidades.Categoria;
import com.compraventa.entidades.Productos;
import com.compraventa.entidades.Seccion;
import com.compraventa.entidades.Subcategoria;
import com.compraventa.service.CategoriaManger;
import com.compraventa.service.ProductoManager;
import com.compraventa.service.SeccionManager;
import com.compraventa.service.SubCategoriaManager;
import com.compraventa.service.UsuarioManager;


@Controller
public class ProductoController {

	
	@Autowired
	ProductoManager producto;
	
	@Autowired
	CategoriaManger cateman;
	
	@Autowired
	UsuarioManager usuman;
	
	@Autowired
	SeccionManager secman;
	
	@Autowired
	SubCategoriaManager subcat;
	
	
	@RequestMapping(value="insertarProd", method= RequestMethod.POST)
	public String InsertaProductos(@ModelAttribute("producto") @Validated Productos product, BindingResult result, @RequestParam(value = "file", required = true) MultipartFile file, Model model ){		
		
		
		if (result.hasErrors()) {
			return "Producto/insertaProducto";
		}		
		
		producto.insertaProducto(product, file);		
		return "Producto/insertaProducto";		
	}
	
	
	@RequestMapping(value="InserProd", method=RequestMethod.GET)
	public String cargaFormInsertproducto(Model model){		
		model.addAttribute("producto", new Productos());		
		return "Producto/insertaProducto";
	}
	
	
	@RequestMapping(value = "cargaProductos", method = RequestMethod.GET)
	public String loadProductos(Model model) {

		List<Productos> listaProduct = producto.cargaProductosServicio();

		model.addAttribute("listaProductos", listaProduct);

		for (Productos prod : listaProduct) {

			String nombre = prod.getSubcategoria().getNomsubcategoria();			
			
				System.out.println(nombre);			

		}

		// model.addAttribute("usuario", new Usuarios());

		// return "showMessage";

		return "Producto/ListadoProductos";

	}
	
	
	@ModelAttribute("listaCategorias")
	public List<Categoria> listaCategorias(){		
		return cateman.listaCategorias();		
	}
		
	
	@ModelAttribute("listaSubcategoria")
	public List<Subcategoria> listaSubcategoria(){		
		return subcat.listaSubCategoriaManag();		
	}
	
	
	@ModelAttribute("listaSeccion")
	public List<Seccion> listaSeccion(){		
		return secman.listaSeccion();
		
		
	}
	
	
	
}
