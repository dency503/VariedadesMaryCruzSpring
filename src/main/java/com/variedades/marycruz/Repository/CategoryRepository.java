package com.variedades.marycruz.Repository;

import com.variedades.marycruz.models.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoryRepository extends JpaRepository<Categoria, Long> {

}
