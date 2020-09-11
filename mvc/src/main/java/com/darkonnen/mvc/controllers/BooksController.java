package com.darkonnen.mvc.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.darkonnen.mvc.models.Book;
import com.darkonnen.mvc.services.BookService;

@Controller
public class BooksController {
	private final BookService bookService;

	public BooksController(BookService bookService) {
		this.bookService = bookService;
	}

	@RequestMapping("/books")
	public String index(Model model) {
		List<Book> books = bookService.allBooks();
		model.addAttribute("books", books);
		return "index";
	}

	@RequestMapping("/books/new")
	public String newBook(@ModelAttribute("book") Book book) {
		return "new";

	}

	@RequestMapping(value = "/books", method = RequestMethod.POST)
	public String create(@Valid @ModelAttribute("book") Book book, BindingResult result) {
		if (result.hasErrors()) {
			return "redirect:/books/new";
		} else {
			bookService.createBook(book);
			return "redirect:/books";
		}
	}

	@RequestMapping(value = "books/{id}", method = RequestMethod.GET)
	public String show(@PathVariable("id") Long id, Model model) {
		Book book = bookService.findBook(id);

		model.addAttribute("book", book);

		return "show";
	}

	@RequestMapping("books/{id}/edit")
	public String edit(@PathVariable("id") Long id, Model model) {
		Book book = bookService.findBook(id);

		model.addAttribute("book", book);
		return "edit";
	}

	@RequestMapping(value = "/books/{id}", method = RequestMethod.PUT)
	public String update(@Valid @ModelAttribute("book") Book book, BindingResult result, @PathVariable("id") Long id,
			@RequestParam(value = "title") String title, @RequestParam(value = "description") String desc,
			@RequestParam(value = "language") String lang, @RequestParam(value = "numberOfPages") Integer numOfPages) {
		if (result.hasErrors()) {
			return "redirect:/books/edit";
		} else {
			bookService.updateBook(id, title, desc, lang, numOfPages);
			return "redirect:/books";
		}
	}

	@RequestMapping(value = "/books/{id}", method = RequestMethod.DELETE)
	public String destory(@PathVariable("id") Long id) {
		bookService.deleteBook(id);
		;
		return "redirect:/books";
	}

}