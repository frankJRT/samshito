package com.hito.am.web.app.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.hito.am.web.app.models.dao.IPeriodoDao;
import com.hito.am.web.app.models.entity.Periodo;

@Service
public class PenalizacionesService {
	private static final Logger logger = LoggerFactory.getLogger(CarteraService.class);

	@Autowired
	private IPeriodoDao periodoDao;

	@Autowired
	private PythonCallerService pyhtonService;
	
	@Value("${python.executer.penalizaciones1}")
	private String pathPenalizaciones1;


	public List<Periodo> getPeriodos() {
		logger.info("PenalizacionesService : getPeriodos   listado de los ultimos 10 periodos");
		Pageable paging = PageRequest.of(0, 10, Sort.Direction.DESC, "consecutivo");
		Page<Periodo> periodos = periodoDao.findAll(paging);

		return periodos.getContent();
	}

	public String callPythonProcess() {
		
		pyhtonService.ejecuteScript(pathPenalizaciones1);
		return null;
		
	}

	
}
