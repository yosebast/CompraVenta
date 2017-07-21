package com.compraventa.controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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

protected static Logger logger = Logger.getLogger("ProductoController");
	

	@Autowired
	HttpServletRequest request;

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
		
		//esto funciona del siguiente modo
		//mapeo el campo urlFoto1   con la columna de la bbdd   pero le digo que no es obligatorio    asi que hibernate le da igual si se lo 
		//mando o no pero como tengo @validate   entonces tendre que evaluar el bindingResult    asi que  
		//dentro de la evaluacion  result.hasErrors()   evaluo si se esta enviando la imagen con
		//file.getSize() == 0 || file.isEmpty()   si esi bien si no lanzo para que se muestre el error  y no siga con
		//result.rejectValue("urlFoto1", "NotEmpty", "Es obligatorio insertar una imagen");	
			
		if (result.hasErrors()) {
				
				if(file.getSize() == 0 || file.isEmpty()){			
					result.rejectValue("urlFoto1", "NotEmpty", "Es obligatorio insertar una imagen");			
			}				
				return "Producto/insertaProducto";	
		}else{			
			if(file.getSize() == 0 || file.isEmpty()){			
				result.rejectValue("urlFoto1", "NotEmpty", "Es obligatorio insertar una imagen");				
				return "Producto/insertaProducto";				
		   }			
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
	
	//edita el producto para ser actializado
	
	
	@RequestMapping(value="rutaProducto", method=RequestMethod.GET)
	public String UpdateProducto(@RequestParam("idproducto") Integer idproducto, Model model){
		
		Productos product = null; 
		
		if( idproducto != null ){			
			product = producto.cargaProductoById(idproducto);					
		}
		
		if(  product != null ){
			
			model.addAttribute("producto", product);
			
			return "Producto/actualizaProducto";
		}
		
		return "Producto/ListadoProductos";	
	
	}
	
	@RequestMapping(value="updateProducto", method=RequestMethod.POST)
	public String ejecutaUpdateProducto(@ModelAttribute("producto") @Validated Productos product, BindingResult result, @RequestParam(value = "file", required = true) MultipartFile file, Model model){
		
		//boolean  seleccion = WebUtils.hasSubmitParameter(request, "actualizar");
		
		//if(seleccion){
		
		if (result.hasErrors()) {			
			return "Producto/actualizaProducto";	
		}		
	
		//si pasa la validacion 
		//1º no puedo traer la imagen con el formulario  por que spring no puede convertir la imagen de string a blob en el mapeo  
		//esto es porque no puede realizar el mapeo con este tipo de dato dentro del objeto producto  por esta razon siempre lo trato externamente
		//evitando la conversion de la propiedad
		//entonces no incluyo dentro del formulario un campo hidden   con la propiedad urlFoto1 por que lanza esta excepcion
		
		//2º entonces lo que hago es   recupero el objeto con su id y obtengo la imagen   y si file es diferente de cero o es vacio le seteo la imagen que obtengo si no 
		//no por que la imagen sera la del file
		
		if(file.getSize() == 0 || file.isEmpty()){		

			Productos prod = producto.cargaProductoById(product.getIdproducto());
			
			product.setUrlFoto1(prod.getUrlFoto1());
			
		}
		
			producto.actualizaProducto(product, file);
			return "redirect:/cargaProductos";		
		
		//}else{				
			
			//return "redirect:/cargaProductos";			
	
		//}
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
	
	//con los siguientes metodos recuperamos objetos y listados de objetos
	
	@RequestMapping(value="/productos", method=RequestMethod.GET, headers="Accept=application/xml, application/json")
	public @ResponseBody List<Productos>  getAllProductos(){
		
		logger.debug("Provider has received request to get all products");
		
		List<Productos> listaProductos = producto.cargaProductosServicio();		
		
		return listaProductos;
		
		
	}
	
	
	//enviar un pdf
	@RequestMapping(value = "/downloadPDF", method = RequestMethod.GET, produces = "application/pdf")
    public ResponseEntity<byte[]> getPDF() {
        FileInputStream fileStream;
        try {
            fileStream = new FileInputStream(new File("C:\\test.pdf"));
            byte[] contents = IOUtils.toByteArray(fileStream);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("application/pdf"));
            String filename = "test.pdf";
            headers.setContentDispositionFormData(filename, filename);
            ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(contents, headers, HttpStatus.OK);
            return response;
        } catch (FileNotFoundException e) {
           System.err.println(e);
        } catch (IOException e) {
            System.err.println(e);
        }
        return null;
    }
    
    
    //enviar una imagen
    @RequestMapping(value="/productos/{idproducto}", method=RequestMethod.GET, headers="Accept=application/xml, application/json", produces="image/jpg")
	public ResponseEntity<byte[]> getIdProducto(@PathVariable("idproducto") int idproducto) throws SQLException, IOException {
		
		logger.debug("provider has received request to get one products");
		
		Productos product = producto.cargaProductoById(idproducto);
		
		Blob blob = product.getUrlFoto1();
		InputStream blobInputStream = blob.getBinaryStream();
		
		// FileInputStream fileStream;		
		// fileStream = new FileInputStream(new File("C:\\pulserapiel.jpg"));			
		            
		
       byte[] contents = IOUtils.toByteArray(blobInputStream);
       //byte[] encodeBase64 = Base64.encodeBase64(contents);
       
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_JPEG);
		String filename = "test.jpg";
		headers.setContentDispositionFormData(filename, filename);
		ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(contents, headers, HttpStatus.OK);
	  
		return response;
		
		//la explicacion esta en el lado del cliente
       
	}
	
	
	//enviar una lista de imagenes
	
    @RequestMapping(value="/imagenes", method=RequestMethod.GET, headers="Accept=application/xml, application/json")
	
    public @ResponseBody List<ResponseEntity<byte[]>> getIdProductos() throws SQLException, IOException {
		
		logger.debug("provider has received request to get one products");  
		
		List<ResponseEntity<byte[]>> listadoImagenes = new ArrayList<ResponseEntity<byte[]>>();	
		
	/*	byte[] content1 = IOUtils.toByteArray(new FileInputStream(new File("C:\\estrellas.jpg")));
		
		byte[] content2 = IOUtils.toByteArray(new FileInputStream(new File("C:\\nebulosa.jpg")));
		
		byte[] content3 = IOUtils.toByteArray(new FileInputStream(new File("C:\\pulserapiel.jpg")));
		
		
		
		HttpHeaders headers1 = new HttpHeaders();
		headers1.setContentType(MediaType.IMAGE_JPEG);
		String filename = "test1.jpg";
		headers1.setContentDispositionFormData(filename, filename);
		ResponseEntity<byte[]> response1 = new ResponseEntity<byte[]>(content1, headers1, HttpStatus.OK);  
		
		HttpHeaders headers2 = new HttpHeaders();
		headers2.setContentType(MediaType.IMAGE_JPEG);
		String filename2 = "test2.jpg";
		headers2.setContentDispositionFormData(filename2, filename2);
		ResponseEntity<byte[]> response2 = new ResponseEntity<byte[]>(content2, headers2, HttpStatus.OK);     		
		
		HttpHeaders headers3 = new HttpHeaders();
		headers3.setContentType(MediaType.IMAGE_JPEG);
		String filename3 = "test3.jpg";
		headers3.setContentDispositionFormData(filename3, filename3);
		ResponseEntity<byte[]> response3 = new ResponseEntity<byte[]>(content3, headers3, HttpStatus.OK);   
		
		 
		listadoImagenes.add(response1);
		listadoImagenes.add(response2);
		listadoImagenes.add(response3);	*/

		
		List<Productos> listProductos = producto.cargaProductosServicio();
		
		String filename = "";
		byte[] contents = null;
		InputStream blobInputStream = null;
		Blob blob = null;
		ResponseEntity<byte[]> response = null;
		HttpHeaders headers = null;
		
		for(Productos product : listProductos){			
			blob = product.getUrlFoto1();
			blobInputStream = blob.getBinaryStream();
			contents = IOUtils.toByteArray(blobInputStream);
			headers = new HttpHeaders();
			headers.setContentType(MediaType.IMAGE_JPEG);
			filename = product.getNomImagen();			
			headers.setContentDispositionFormData(filename, filename);
			response = new ResponseEntity<byte[]>(contents, headers, HttpStatus.OK);			
			listadoImagenes.add(response);
		}
				
		  return listadoImagenes;      
		
		
		//la explicacion esta en el lado del cliente
       
	}
    
    
    
 
    
    
	
	/* private static void saveBytesToFile(String filePath, byte[] fileBytes, HttpServletResponse response) throws IOException {
		 ServletOutputStream outputStream = response.getOutputStream(); 
		 
	        outputStream.write(fileBytes);
	        outputStream.close();
	    }*/
}
