package com.compraventa.controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.util.UriComponentsBuilder;

import com.compraventa.entidades.Categoria;
import com.compraventa.entidades.Productos;
import com.compraventa.entidades.Seccion;
import com.compraventa.entidades.Subcategoria;
import com.compraventa.objectMapper.ProductosMap;
import com.compraventa.service.CategoriaManger;
import com.compraventa.service.ProductoManager;
import com.compraventa.service.SeccionManager;
import com.compraventa.service.SubCategoriaManager;
import com.compraventa.service.UsuarioManager;
import com.fasterxml.jackson.databind.ObjectMapper;



@Controller
public class ProductoController {

private static Logger logger = LoggerFactory.getLogger("ProductoController");
	

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
	
	@Autowired
	ProductosMap prodMap;
	

	
	
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

		logger.debug("carga los products");
		
		List<Productos> listaProduct = producto.getAll();

		model.addAttribute("listaProductos", listaProduct);

		for (Productos prod : listaProduct) {

			String nombre = prod.getSubcategoria().getNomsubcategoria();			
			
				//System.out.println(nombre);
				
				//System.out.println(prod.getFechaPublicacion());
				

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
			product = producto.get(idproducto);					
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

			Productos prod = producto.get(product.getIdproducto());
			
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
		return cateman.getAll();		
	}
		
	
	@ModelAttribute("listaSubcategoria")
	public List<Subcategoria> listaSubcategoria(){		
		return subcat.getAll();		
	}
	
	
	@ModelAttribute("listaSeccion")
	public List<Seccion> listaSeccion(){		
		return secman.getAll();
		
		
	}
	
	//con los siguientes metodos recuperamos objetos y listados de objetos
	
	@RequestMapping(value="/productos", method=RequestMethod.GET, headers="Accept=application/xml, application/json")
	public @ResponseBody List<Productos>  getAllProductos(){
		
		logger.debug("Provider has received request to get all products");
		
		List<Productos> listaProductos = producto.getAll();		
		
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
		
		Productos product = producto.get(idproducto);
		
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

		
		List<Productos> listProductos = producto.getAll();
		
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
			headers.set("nombre", filename);
			headers.set("precio", String.valueOf(product.getPrecio()));
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
    
    
    
  //con los siguientes metodos hacemos el crud para las llamadas   desde el proyecto angular externo por lo que seria mejor devolver la llamada a traves de un 
  		//ResponseEntity que va a empaquetar la respuesta
    
    @RequestMapping(value="/allproductos", method=RequestMethod.GET)
    public ResponseEntity<List<ProductosMap>> listAllProductos(){
    	
    	
    	List<ProductosMap> listadoProductos = new ArrayList<ProductosMap>(); 	
    	
    	List<Productos> productos = producto.getAll();
    	if(productos.isEmpty() ){
    		
    		return new ResponseEntity<List<ProductosMap>>(HttpStatus.NO_CONTENT);
    	}
    	
    	byte[] contents = null;
		InputStream blobInputStream = null;
		Blob blob = null;
		//ResponseEntity<ProductosMap> response = null;
		//HttpHeaders headers = null;
		
		for(Productos product : productos){	
			
			
			blob = product.getUrlFoto1();
			
			try {
				blobInputStream = blob.getBinaryStream();
				contents = IOUtils.toByteArray(blobInputStream);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			prodMap = new ProductosMap();
			
			prodMap.setCategoria(product.getCategoria());
			prodMap.setEstado(product.getEstado());
			prodMap.setFechaPublicacion(new SimpleDateFormat("dd/MM/yyyy").format(product.getFechaPublicacion()));			
			//System.out.println(product.getFechaPublicacion());
			prodMap.setIdproducto(product.getIdproducto());
			prodMap.setNomImagen(product.getNomImagen());
			prodMap.setPrecio(product.getPrecio());
			prodMap.setSeccion(product.getSeccion());
			prodMap.setSubcategoria(product.getSubcategoria());
			prodMap.setTitulo(product.getTitulo());
			prodMap.setUrlFoto1(contents);
			prodMap.setVisitas(product.getVisitas());		
    	
			listadoProductos.add(prodMap);
    		
		}
		return new ResponseEntity<List<ProductosMap>>(listadoProductos, HttpStatus.OK);
    }
    
    //metodo para obtener un registro	se enviara el objeto como se envio para el listado no puede ir el objeto por el tipo de dato Blob   entonces lo cambio a byte  y asi lo envio	
    	@RequestMapping(value="/allproductos-{id}", method=RequestMethod.GET, headers = "Accept=application/xml, application/json, text/plain", produces=MediaType.APPLICATION_JSON_VALUE)
		public  ResponseEntity<ProductosMap> getProductosById(@PathVariable("id") Integer idproducto){
     	   Productos product = null; 
    	   product = producto.get(idproducto);			
    	
    	if( producto == null ){
    		return new ResponseEntity<ProductosMap>(HttpStatus.NOT_FOUND);
    	}
    	
    	byte[] contents = null;
		InputStream blobInputStream = null;
		Blob blob = null;
		
		blob = product.getUrlFoto1();
		
		try {
			blobInputStream = blob.getBinaryStream();
			contents = IOUtils.toByteArray(blobInputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		prodMap = new ProductosMap();
		
		prodMap.setCategoria(product.getCategoria());
		prodMap.setEstado(product.getEstado());
		prodMap.setFechaPublicacion(new SimpleDateFormat("dd/MM/yyyy").format(product.getFechaPublicacion()));			
		//System.out.println(product.getFechaPublicacion());
		prodMap.setIdproducto(product.getIdproducto());
		prodMap.setNomImagen(product.getNomImagen());
		prodMap.setPrecio(product.getPrecio());
		prodMap.setSeccion(product.getSeccion());
		prodMap.setSubcategoria(product.getSubcategoria());
		prodMap.setTitulo(product.getTitulo());
		prodMap.setUrlFoto1(contents);
		prodMap.setVisitas(product.getVisitas());	
		
    	HttpHeaders headers = new HttpHeaders();
    	
    	return new ResponseEntity<ProductosMap>(prodMap, headers, HttpStatus.OK);   	
    
    	
    }
    
    //metodo para actualizar un registro 
    	
    	@RequestMapping(value="/allproductos-{id}", method=RequestMethod.PUT, headers="Accept=application/xml, application/json, text/plain", produces=MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<Void> upDateProducto(@PathVariable("id") Integer idProducto, @RequestBody ProductosMap ObjProductosMap) throws ParseException, SerialException, SQLException{
		
    	
    		//lo he mapeado con ProductoMap   por que si lo mapeo con la entidad producto peta    por que no puede mapear la fecha con del json tipo string   con la de la entidad tipo date. y tampoco puede mapear de byte a blob  asi que aqui ya no es necesario el mapper
    		//hay que revisar el resto por si derrepente tampoco es necesario hacer un mapper  y directamente lo mapeamos a ProductosMap
    		
              
        	
        	//3.- construyo el objeto Productos
        	
        	Productos ObjProducto = new Productos();
        	ObjProducto.setIdproducto(ObjProductosMap.getIdproducto());
        	ObjProducto.setSeccion(ObjProductosMap.getSeccion());
        	ObjProducto.setCategoria(ObjProductosMap.getCategoria());
        	ObjProducto.setSubcategoria(ObjProductosMap.getSubcategoria());
        	ObjProducto.setTitulo(ObjProductosMap.getTitulo());
        	ObjProducto.setPrecio(ObjProductosMap.getPrecio());
        	ObjProducto.setFechaPublicacion(new SimpleDateFormat("dd/MM/yyyy").parse(ObjProductosMap.getFechaPublicacion()));
        	ObjProducto.setEstado(ObjProductosMap.getEstado());
        	ObjProducto.setVisitas(ObjProductosMap.getVisitas());
        	ObjProducto.setNomImagen(ObjProductosMap.getNomImagen());
        	ObjProducto.setUrlFoto1(new SerialBlob(ObjProductosMap.getUrlFoto1()));
    	
    	producto.update(ObjProducto);
    	HttpHeaders header = new HttpHeaders();
    	return new ResponseEntity<Void>(header, HttpStatus.OK);  	 	
    	
    }
    
    
    //metodo para eliminar un resgistro 
    	
    	@RequestMapping(value="/allproductos-{id}", method=RequestMethod.DELETE, headers = "Accept=application/xml, application/json, text/plain", produces=MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Void> deleteProducto(@PathVariable("id") Integer idproducto){
    	Productos currentProduct = null;
    	currentProduct = producto.get(idproducto);
    	
    	if( currentProduct == null ){
    		
    		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    		
    	}
    	
    	producto.remove(currentProduct);
    	
    	HttpHeaders header = new HttpHeaders();
    	return new ResponseEntity<Void>(header, HttpStatus.NO_CONTENT);
    	
    }
    
    //metodo para crear un producto
    
    @RequestMapping(value="/product", method=RequestMethod.POST, headers="Accept=application/xml, application/json, image/jpg", produces = "application/json")
    public ResponseEntity<Void> createdProducto( @RequestParam(value="fichero", required=false) MultipartFile file,
            @RequestParam(value="data") Object data) throws Exception {
    	
    	 SimpleDateFormat formatt = new SimpleDateFormat("dd-MMM-yyyy");  	
    	//usamos una la clase ProductosMap   por que si usamos la entidad Productos   el  mappear no puede mapear el tipo de dato  de string a date y lanza error   asi que lo mapeamos a este objeto y luego lo pasamos a la entidad
    	//pero todo esto lo hacemos aqui en el controller y no en el servicio por que al estar usando un generic  lo llamamos desde aqui y no desde el servicio.
    	
    	//1.- creo el objeto mapper
    	ObjectMapper mapper = new ObjectMapper();
    	
    	//2.- mapeo el json que recibo al objeto ProductosMap
    	ProductosMap ObjProductosMap = mapper.readValue(data.toString(), ProductosMap.class);
    	
    	ObjProductosMap.setUrlFoto1(file.getBytes());
    	
    	//3.- construyo el objeto Productos
    	
    	Productos ObjProducto = new Productos();
    	
    	ObjProducto.setSeccion(ObjProductosMap.getSeccion());
    	ObjProducto.setCategoria(ObjProductosMap.getCategoria());
    	ObjProducto.setSubcategoria(ObjProductosMap.getSubcategoria());
    	ObjProducto.setTitulo(ObjProductosMap.getTitulo());
    	ObjProducto.setPrecio(ObjProductosMap.getPrecio());
    	ObjProducto.setFechaPublicacion(new SimpleDateFormat("dd/MM/yyyy").parse(ObjProductosMap.getFechaPublicacion()));
    	ObjProducto.setEstado(ObjProductosMap.getEstado());
    	ObjProducto.setVisitas(ObjProductosMap.getVisitas());
    	ObjProducto.setNomImagen(ObjProductosMap.getNomImagen());
    	ObjProducto.setUrlFoto1(new SerialBlob(ObjProductosMap.getUrlFoto1()));
    	
    	
    /*con este trozo de codigo los byte[]  conviero en imagen el el fichero
    		 byte[] Json = file.getBytes();
			FileOutputStream fileOuputStream = new FileOutputStream("C:\\Clienteservidor2.jpg");
			fileOuputStream.write(Json);
			fileOuputStream.close();
	*/
    	
    	producto.persist(ObjProducto);
    	
    	HttpHeaders headers = new HttpHeaders();
    	
    	//headers.setLocation(ucBuilder.path("/productos/{id}").buildAndExpand(product.getIdproducto()).toUri());
    	
    	
    	return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    	
    	
    }
    
}
