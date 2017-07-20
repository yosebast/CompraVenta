package com.compraventa.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

	
	//este modo de trabajar con esta clase que creo llamada Hibernate Util donde estoy utilizando el SessionFactory 
	//para crear un metodo con el mismo nombre para luego llamarlo dentro de mi DAO no es correcta por que estoy haciendo uso de
	//hibernate y no spring   ademas en el DAO no estoy controlando las excepciones y las transacciones   que si uso de esta forma tendria 
	//que controlarlas
	// la forma correcta es usar Spring   ya que spring abstrae todo esto de los dao como se detalla a continuacion
	/*
	 * Spring Hibernate: HibernateTemplate Class
Hablando del HibernateTemplate, la implementación de Hibernate según Spring, surge siempre la misma pregunta entre la gente que no lo utiliza asiduamente: ¿De que me pierdo si no lo uso?


Si consultamos la documentación de Spring Hibernate encontramos que dice que HibernateTemplate es una clase que provee muchas de las funcionalidades de la interfaz Session de Hibernate, adicionando algunos métodos que facilitan considerablemente su utilización. Con HibernateTemplate siempre podremos obtener los métodos que trae la Session exactamente igual que como lo usábamos antes.

Entonces ¿Que ventajas me trae usar HibernateTemplate?

HibernateTemplate se asegura de que las instancias de la Session sean correctamente abiertas y cerradas.
Maneja las transacciones automáticamente.
Las instancias del HibernateTemplate son thread-safe (y toda la gestión de concurrencia que implica esto) y son reusable.
Para las operaciones básicas del CRUD (Create-Replace-Update-Delete) el HibernateTemplate ofrece métodos muy simples que ocupan una sola línea de código y resuelven el problema.
Spring provee una clase acoplable al HibernateTemplate: HibernateDaoSupport, de la cual debe extender una clase DAO para poder hacer uso inmediatamente de métodos get y set del SessionFactory y un método getHibernateTemplate() para usar todas las funcionalidades de HibernateTemplate. Esto nos permite implementar un DAO simple y rápidamente con un muy reducido tiempo y costo de aprendizaje.
Maneja el commit (o auto-commit) de una transacción.
Gestiona las excepciones convirtiendo las HibernateException en DataAccessException siguiendo la jerarquía de excepciones.
Importante también mencionar que el lazy loading trabajará directamente con la Session cuando este abierta, y para muchos de los casos se cierra mandando una LazyInitializationException que se puede manejar con un interceptor de nombre OpenSessionInView.
Si vemos como manejan la Session en el HibernateTemplate nos daremos cuenta que muchas de las veces que usabamos a Hibernate tal vez no lo haciamos tan bien como creíamos...

En conclusión, si tu aplicación tiene implementado Spring Hibernate, no dejes de usar esta fabulosa clase HibernateTemplate porque siempre va a estar al día con las buenas prácticas y el impecable, performante y correcto uso de Hibernate.

	 */
	
	private static SessionFactory sessionFactory;
    
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            // loads configuration and mappings
            Configuration configuration = new Configuration().configure();
           // Configuration configuration = new Configuration().configure(HibernateUtil.class.getResource("/hibernate.cfg.xml"));
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
             
            // builds a session factory from the service registry
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);           
        }
         
        return sessionFactory;
    }
    
}
