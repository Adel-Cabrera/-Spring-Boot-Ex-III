package com.darkonnen.mvc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.darkonnen.mvc.models.Book;
import com.darkonnen.mvc.repositories.BookRepository;

@Service
public class BookService {

	private final BookRepository bookRepository;

	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	// Devolviendo todos los libros.
	// READ ALL
	
	public List<Book> allBooks() {
		return bookRepository.findAll();
	}


	// Obteniendo la información de un libro
	// READ ONE
	
	public Book findBook(Long id) {
		Optional<Book> optionalBook = bookRepository.findById(id);
		if (optionalBook.isPresent()) {
			return optionalBook.get();
		} else {
			return null;
		}
	}
	
	// Creando un libro.
	//CREATE
	
	public Book createBook(Book b) {
		return bookRepository.save(b);
	}
	
	
	// UPDATE
	public Book updateBook(Long id, String title, String desc, String lang, Integer numOfPages) {
		Optional<Book> optionalBook = bookRepository.findById(id);
		if (optionalBook.isPresent()) {
			Book updateBook = optionalBook.get();

			// Update Fields
			updateBook.setTitle(title);
			updateBook.setDescription(desc);
			updateBook.setLanguage(lang);
			updateBook.setNumberOfPages(numOfPages);

			// Saves the bookObject
			return bookRepository.save(updateBook);
		} else {
			return null;
		}
	}
	
	
	// DESTROY - DELETE
	
	public void deleteBook(Long id) {
		Optional<Book> optionalBook = bookRepository.findById(id);
		if (optionalBook.isPresent()) {
			bookRepository.deleteById(id);
		}
	}

}
