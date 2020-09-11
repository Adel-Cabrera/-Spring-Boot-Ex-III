package com.darkonnen.mvc.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.darkonnen.mvc.models.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Long>{

	List<Book> findAll(); // crea queries a través de la firma del método.
	
	Long deleteById(String search);
	
//	//Este método encuentra un libro por su descripción
//    List<Book> findByDescriptionContaining(String search);
//    
//    //Este método cuenta cuántos libros contiene cierta cadena en el título
//    Long countByTitleContaining(String search);
//    
//    //Este método borra un libro que empieza con un título específico
//    Long deleteByTitleStartingWith(String search);
    
	
}
