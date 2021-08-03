package com.hito.am.web.app.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hito.am.web.app.models.entity.Periodo;

public interface IPeriodoDao extends JpaRepository<Periodo, Integer> {

}
