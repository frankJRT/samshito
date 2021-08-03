package com.hito.am.web.app.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.hito.am.web.app.models.dao.ICarteraDao;
import com.hito.am.web.app.models.entity.Cartera;
import com.hito.am.web.app.models.entity.CatalogoComportamiento;
import com.hito.am.web.app.models.entity.ElementoCatalogoComportamiento;
import com.hito.am.web.app.models.model.ModelCartera;

@Service
public class CarteraService {
	private static final Logger logger = LoggerFactory.getLogger(CarteraService.class);

	@Value("${catalogoComportamiento.I_administrador}")
	private String iAdministrador;

	@Value("${catalogoComportamiento.I_IntermediarioFinanciero}")
	private String iIntermediarioFinanciero;

	@Autowired
	private ICarteraDao carteraDao;

	@Autowired
	private CatalogoComportamientoService catalogoComportamientoService;

	

	public List<ModelCartera> getCarteras() {
		logger.info("CarteraService : getCarteras obtener carteras ");
		List<ModelCartera> lscarteras = new ArrayList<ModelCartera>();
		List<ElementoCatalogoComportamiento> lsAdministrador = catalogoComportamientoService
				.getElementosCatalogo(Integer.valueOf(iAdministrador));

		System.out.println(iAdministrador);

		List<ElementoCatalogoComportamiento> lsIntermediarioFinanciero = catalogoComportamientoService
				.getElementosCatalogo(Integer.valueOf(iIntermediarioFinanciero));

		for (Cartera cartera : carteraDao.findAll()) {
			ModelCartera cr = new ModelCartera();
			cr.setI_Cartera_Comportamiento(cartera.getI_Cartera_Comportamiento());
			cr.setD_Cartera_Comportamiento(cartera.getD_Cartera_Comportamiento());
			cr.setE_Cartera_Comportamiento_Descripcion(cartera.getE_Cartera_Comportamiento_Descripcion());
			cr.setN_Cartera_Comportamiento_Clave(cartera.getN_Cartera_Comportamiento_Clave());
			cr.setN_Cartera_Comportamiento(cartera.getN_Cartera_Comportamiento());
			cr.setX_Cartera_Comportamiento_Status(cartera.getX_Cartera_Comportamiento_Status());
			cr.setI_Archivo_Comportamieto(cartera.getI_Archivo_Comportamieto());

			if (cartera.getI_Administrador() != null) {
				cr.setI_Administrador(findElementoCatalogo(cartera.getI_Administrador(), lsAdministrador));
			}

			if (cartera.getI_Intermediario_Financiero() != null) {
				cr.setI_Intermediario_Financiero(
						findElementoCatalogo(cartera.getI_Intermediario_Financiero(), lsIntermediarioFinanciero));
			}
			lscarteras.add(cr);
		}
		return lscarteras;
	}

	public List<CatalogoComportamiento> getCatalogos() {
		return catalogoComportamientoService.getCatalogos();
	}

	public List<ElementoCatalogoComportamiento> getElementosCatalogo(Integer cat) {
		return catalogoComportamientoService.getElementosCatalogo(cat);
	}

	

	private String findElementoCatalogo(Integer idElemento, List<ElementoCatalogoComportamiento> lsElementos) {
		logger.info("CarteraService : findElementoCatalogo encontrar el elemento de un catalogo");
		for (ElementoCatalogoComportamiento elemento : lsElementos) {
			if (idElemento.equals(elemento.getI_Elemento_Catalogo_Comportamiento())) {
				return elemento.getE_Elemento_Catalogo_Comportamiento_Decripcion();
			}
		}
		return null;
	}

}
