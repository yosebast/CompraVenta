CREATE TABLE `productos` (
  `idproducto` int(7) NOT NULL AUTO_INCREMENT,
  `idsubcategoria` int(7) NOT NULL,
  `idcategoria` int(7) NOT NULL,
  `idseccion` int(2) NOT NULL,
  `titulo` varchar(30) NOT NULL,
  `precio` double NOT NULL,
  `fechaPublicacion` date NOT NULL,
  `estado` varchar(10) NOT NULL,
  `visitas` int(11) DEFAULT NULL,
  `nomImagen` varchar(70) NOT NULL,
  `urlFoto1` longblob NOT NULL,
  PRIMARY KEY (`idproducto`),
  KEY `categoria_ibfk1` (`idcategoria`),
  KEY `usuario_ibfk1` (`idsubcategoria`),
  KEY `seccion_ibfk1` (`idseccion`),
  CONSTRAINT `categoria_ibfk1` FOREIGN KEY (`idcategoria`) REFERENCES `categoria` (`idcategoria`),
  CONSTRAINT `seccion_ibfk1` FOREIGN KEY (`idseccion`) REFERENCES `seccion` (`idseccion`),
  CONSTRAINT `subcategoria_ibfk1` FOREIGN KEY (`idsubcategoria`) REFERENCES `subcategoria` (`idsubcategoria`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;


CREATE TABLE `categoria` (
  `idcategoria` int(7) NOT NULL AUTO_INCREMENT,
  `nomcategoria` varchar(50) NOT NULL,
  `descripcion` varchar(70) NOT NULL,
  PRIMARY KEY (`idcategoria`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

CREATE TABLE `subcategoria` (
  `idsubcategoria` int(7) NOT NULL AUTO_INCREMENT,
  `nomsubcategoria` varchar(50) NOT NULL,
  `idcategoria` int(7) NOT NULL,
  PRIMARY KEY (`idsubcategoria`),
  KEY `subcategoria_ibfk_1` (`idcategoria`),
  CONSTRAINT `subcategoria_ibfk_1` FOREIGN KEY (`idcategoria`) REFERENCES `categoria` (`idcategoria`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;


CREATE TABLE `seccion` (
  `idseccion` int(2) NOT NULL AUTO_INCREMENT,
  `nomseccion` varchar(50) NOT NULL,
  PRIMARY KEY (`idseccion`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;


CREATE TABLE `usuarios` (
  `idusuario` int(6) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(30) NOT NULL,
  `apellidos` varchar(60) DEFAULT NULL,
  `email` varchar(50) NOT NULL,
  `edad` int(2) unsigned DEFAULT NULL,
  `tlf` varchar(12) DEFAULT NULL,
  `fotoUrl` varchar(60) DEFAULT NULL,
  `registrado` varchar(1) DEFAULT NULL,
  `clave` varchar(30) DEFAULT NULL,
  `reg_date` date DEFAULT NULL,
  PRIMARY KEY (`idusuario`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=latin1;






select * from productos, seccion
where productos.idseccion = seccion.idseccion
and seccion.nomseccion = 'Hombre';


select * FROM productos join seccion on productos.idseccion = seccion.idseccion
where seccion.nomseccion = 'Hombre';


/*

cuantos productos hay de mujer y de hombre
select count(P.idproducto), S.nomseccion  FROM productos P  JOIN seccion S on P.idseccion = S.idseccion
GROUP BY S.nomseccion

cuantos productos hay por categoria

select count(P.idproducto), C.nomcategoria  FROM productos P  JOIN categoria C on P.idcategoria = C.idcategoria
GROUP BY C.nomcategoria


obtenemos todos los productos que son de subcategoria  pulseras de acero

select * FROM productos join categoria ON categoria.idcategoria = productos.idcategoria join subcategoria ON subcategoria.idsubcategoria = productos.idsubcategoria
where subcategoria.nomsubcategoria = 'Pulseras de acero'

todo los productos

select * FROM productos join seccion on productos.idseccion = seccion.idseccion;


todos los producto de HOMBRE

select * FROM productos join seccion on productos.idseccion = seccion.idseccion
where seccion.nomseccion = 'Hombre'


todos los productos de mujer

select * FROM productos join seccion on productos.idseccion = seccion.idseccion
where seccion.nomseccion = 'Mujer'


todas las categorias para el combo categorias  y todos los productos  cuando se selecciona una seccion

select c.idcategoria, p.titulo from categoria c join productos p on c.idcategoria = p.idcategoria join seccion s on p.idseccion = s.idseccion
where s.nomseccion = 'Hombre';



todas las subcategorias para el combo subcategorias  y todos los productos  cuando se selecciona una categoria

select p.idproducto ,c.idsubcategoria, p.titulo from subcategoria c join productos p on c.idsubcategoria = p.idsubcategoria 
join seccion s on p.idseccion = s.idseccion
join categoria t on t.idcategoria = p.idcategoria
where s.nomseccion = 'Hombre' and t.nomcategoria = 'Pulseras';







Query query = sesion.createQuery("update Usuarios set nombre = :nombre," +" apellidos = :apellidos," + " email = :email," + " edad = :edad," + " tlf = :tlf,"
				+ " fotoUrl = :fotoUrl," + " registrado = :registrado," + " clave = :clave," + " reg_Date = :reg_Date" +
	    				" where idusuario = :idUsuario");
	query.setParameter("nombre", usu.getNombre());
	query.setParameter("apellidos", usu.getApellidos());
	query.setParameter("email", usu.getEmail());
	query.setParameter("edad", usu.getEdad());
	query.setParameter("tlf", usu.getTlf());
	query.setParameter("fotoUrl", usu.getFotoUrl());
	query.setParameter("registrado", usu.getRegistrado());
	query.setParameter("clave", usu.getClave());
	query.setParameter("reg_Date", usu.getReg_Date() );
	 query.executeUpdate();

*/
