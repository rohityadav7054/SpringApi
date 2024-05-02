package com.springApi.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.springApi.entities.Books;
@Service
public class BookServices {

	private static List<Books> list = new ArrayList<>();
	
	static {
		list.add(new Books(1212,"Complete refrence","david putor"));
		list.add(new Books(1213,"Complete Java refrence","JonSon putor"));
		list.add(new Books(1214,"Complete refrence","Haward Son"));	
	}
	
	// get All Books
	public List<Books> getAllBooks() {
		return list;
	}
	
	
	//Get book by id
	public Books getBookById(Integer id) {
		Books book=null;
       try { book = list.stream().filter(e -> e.getId().equals(id)).findFirst().get();
        }
       catch(Exception e){
    	   e.printStackTrace();
       }
       return book;
    }
	
	
	//add books 
	public Books addBook( Books b) {
		list.add(b);
		return b;
	}
	
	
	// delete the books
	public void deleteBook(Integer bid) {
	    list = list.stream()
	               .filter(e -> !e.getId().equals(bid))
	               .collect(Collectors.toList());
	}

//update the books
	public void deleteBook(Books Book, Integer bookid) {
		list.stream().map(b->{
			if(b.getId().equals(bookid)) {
				b.setTitle(Book.getTitle());
				b.setAuthor(Book.getAuthor());
			}
			return b;
		}).collect(Collectors.toList());
	
	}
}
