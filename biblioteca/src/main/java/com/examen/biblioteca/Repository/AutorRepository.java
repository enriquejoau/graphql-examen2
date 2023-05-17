package com.examen.biblioteca.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.examen.biblioteca.Entity.Autor;


public interface AutorRepository extends JpaRepository<Autor,Long> {

}
