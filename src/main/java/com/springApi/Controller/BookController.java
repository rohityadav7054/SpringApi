package com.springApi.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springApi.Services.BookServices;
import com.springApi.entities.Books;

@RestController
public class BookController {

	@Autowired
	private BookServices bookService;
	
	@GetMapping("/books")
	public ResponseEntity<List<Books>> getBooks() {
		List<Books> list=bookService.getAllBooks();
		if(list.size()<=0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(list);
	}
	
	
	@GetMapping("/books/{id}")
	public  ResponseEntity<Optional<Books>> getBook(@PathVariable("id") Integer id){
		Optional<Books> book=bookService.getBookById(id);
		if(book==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(book));
	}
	
	
	@PostMapping("/books")
	public ResponseEntity<Books> addBooks(@RequestBody Books book) {
		 Books b = null;
      try {
		b =this.bookService.addBook(book);
		return ResponseEntity.status(HttpStatus.CREATED).body(b);
	} catch (Exception e) {
		e.printStackTrace();
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
		
	}
	
	
	@DeleteMapping("/books/{bookid}")
	public  ResponseEntity<Void> deleteBooks(@PathVariable("bookid") Integer bookid) {
		
		try {
			this.bookService.deleteBook(bookid);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
		
	
	}
	
	
	@PutMapping("/books/{bookid}")
	public  ResponseEntity<Books> updatebooks(@RequestBody Books book,@PathVariable("bookid") Integer bookid) {
	
		try {
			this.bookService.updateBook(book,bookid);
			return ResponseEntity.ok().body(book);
			
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	
	}
}
