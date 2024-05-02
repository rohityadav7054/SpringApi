package com.springApi.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.springApi.dao.BookRepository;
import com.springApi.entities.Books;
@Service
public class BookServices {

//	private static List<Books> list = new ArrayList<>();
//	static {
//		list.add(new Books(1212,"Complete refrence","david putor"));
//		list.add(new Books(1213,"Complete Java refrence","JonSon putor"));
//		list.add(new Books(1214,"Complete refrence","Haward Son"));	
//	}
	@Autowired
	private BookRepository bookRepository;
	
	// get All Books
	public List<Books> getAllBooks() {
	List<Books> list=(List<Books>)this.bookRepository.findAll();
		return list;
	}
	

	//Get book by id
	public Optional<Books> getBookById(Integer id) {
		Optional<Books> book=null;
       try { 
    	  book = this.bookRepository.findById(id);
        }
       catch(Exception e){
    	   e.printStackTrace();
       }
       return book;
    }
	
	
	//add books 
	public Books addBook( Books b) {
	  Books result=this.bookRepository.save(b);
		return result;
	}
	
	
	// delete the books
	public void deleteBook(Integer bid) {
//	    list = list.stream()
//	               .filter(e -> !e.getId().equals(bid))
//	               .collect(Collectors.toList());
	 bookRepository.deleteById(bid);
	}

//update the books
	public void updateBook(Books Book, Integer bookid) {
//		list.stream().map(b->{
//			if(b.getId().equals(bookid)) {
//				b.setTitle(Book.getTitle());
//				b.setAuthor(Book.getAuthor());
//			}
//			return b;
//		}).collect(Collectors.toList());
	Book.setId(bookid);
	bookRepository.save(Book);
	}
}
