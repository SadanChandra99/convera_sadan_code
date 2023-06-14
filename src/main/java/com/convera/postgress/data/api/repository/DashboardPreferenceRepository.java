package com.convera.postgress.data.api.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;


import com.convera.postgress.data.api.repository.model.DashboardPreference;

@Repository
public interface DashboardPreferenceRepository extends JpaRepository<DashboardPreference, Integer>{

	  List<DashboardPreference> findByUserPreferenceId(Integer userId);
	  
	  

	  @Query(value = "SELECT dp.userpreferenceid " +
	            "FROM dashboard_preference dp, " +
	            "LATERAL jsonb_array_elements(dp.widgetquicklink->'widget_preference') AS widget " +
	            "WHERE (widget->>'widget_id')::bigint = :widgetIdParam",
	            nativeQuery = true)
	    List<Integer> findUserPreferenceIdsByWidgetId(@Param("widgetIdParam") Long widgetId);
	}
	  

