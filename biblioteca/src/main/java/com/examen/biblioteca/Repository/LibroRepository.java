package com.examen.biblioteca.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.examen.biblioteca.Entity.Libro;

public interface LibroRepository extends JpaRepository<Libro,Long>{

}
