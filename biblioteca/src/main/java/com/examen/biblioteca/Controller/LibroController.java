package com.examen.biblioteca.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;


import com.examen.biblioteca.Entity.Autor;
import com.examen.biblioteca.Entity.Editorial;
import com.examen.biblioteca.Entity.Libro;
import com.examen.biblioteca.Repository.AutorRepository;
import com.examen.biblioteca.Repository.EditorialRepository;
import com.examen.biblioteca.Repository.LibroRepository;
import com.examen.biblioteca.dto.LibroRequest;

@Controller
public class LibroController {
	@Autowired
	private LibroRepository libroRepository;
	
	@Autowired
	private AutorRepository autorRepository;
	
	@Autowired
	private EditorialRepository editorialRepository;
	
	@QueryMapping
	public List<Libro> listarLibros(){
		return libroRepository.findAll();
	}
	
	@QueryMapping
	public Libro listarLibroPorId(@Argument("id") Long id) {
		return libroRepository.findById(id).orElse(null);
	}
	
	@MutationMapping
	public Libro guardarLibro(@Argument("libro")  LibroRequest libroRequest) {
		Libro libroBBDD = new Libro();
		libroBBDD.setTitulo(libroRequest.getTitulo());
		libroBBDD.setDescripcion(libroRequest.getDescripcion());
		libroBBDD.setPagina(libroRequest.getPagina());
		libroBBDD.setEdicion(libroRequest.getEdicion());
		
		Autor autor = autorRepository.findById(libroRequest.getIdautor()).orElse(null);
		Editorial editorial = editorialRepository.findById(libroRequest.getIdeditorial()).orElse(null);
		
		libroBBDD.setAutor(autor);
		libroBBDD.setEditorial(editorial);

		return libroRepository.save(libroBBDD);
	}
	@MutationMapping
    public Libro actualizarLibro(@Argument("id") Long id, @Argument("libro")  LibroRequest libroRequest)  {
		Optional<Libro> optionalLibro = libroRepository.findById(id);
		if (optionalLibro.isPresent()) {
		Libro libroBBDD = optionalLibro.get();
		libroBBDD.setTitulo(libroRequest.getTitulo());
		libroBBDD.setDescripcion(libroRequest.getDescripcion());
		libroBBDD.setPagina(libroRequest.getPagina());
		libroBBDD.setEdicion(libroRequest.getEdicion());
        
        Autor autor = autorRepository.findById(libroRequest.getIdautor()).orElse(null);
        Editorial editorial = editorialRepository.findById(libroRequest.getIdeditorial()).orElse(null);
        
        libroBBDD.setAutor(autor);
        libroBBDD.setEditorial(editorial);

        return libroRepository.save(libroBBDD);
		}
		return null;
	}


    @MutationMapping
    public boolean eliminarLibro(@Argument("id") Long id){
    	libroRepository.deleteById(id);
		return true;
    }
}
