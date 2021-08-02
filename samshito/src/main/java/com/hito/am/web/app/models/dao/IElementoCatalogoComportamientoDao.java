package com.hito.am.web.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hito.am.web.app.models.entity.ElementoCatalogoComportamiento;

public interface IElementoCatalogoComportamientoDao extends JpaRepository<ElementoCatalogoComportamiento, Integer>{

	@Query("select elemnts from ElementoCatalogoComportamiento elemnts join elemnts.catalogoComportamiento cats "
			+ " where cats.I_Catalogo_Comportamiento =?1")
	List<ElementoCatalogoComportamiento> findElementosCatalogoByCatalogo(Integer catalofo);
}
