package com.hito.am.web.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hito.am.web.app.models.dao.ICatalogoComportamientoDao;
import com.hito.am.web.app.models.dao.IElementoCatalogoComportamientoDao;
import com.hito.am.web.app.models.entity.CatalogoComportamiento;
import com.hito.am.web.app.models.entity.ElementoCatalogoComportamiento;

@Service
public class CatalogoComportamientoService {

	@Autowired
	private ICatalogoComportamientoDao catalogoComportamientoDao;

	@Autowired
	private IElementoCatalogoComportamientoDao elementoCatalogoDao;
	
	public List<CatalogoComportamiento> getCatalogos(){
		return catalogoComportamientoDao.findAll();
	}
	
	public List<ElementoCatalogoComportamiento> getElementosCatalogo(Integer catalogo){
		return elementoCatalogoDao.findElementosCatalogoByCatalogo(catalogo);
	}
	
	public ElementoCatalogoComportamiento getElementoCatalogo(Integer elemento) {
		return elementoCatalogoDao.getById(elemento);
	}
	
}
