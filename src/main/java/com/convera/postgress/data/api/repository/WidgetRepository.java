package com.convera.postgress.data.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.convera.postgress.data.api.repository.model.Widget;
import com.convera.postgress.data.api.repository.model.WidgetsID;

@Repository
public interface WidgetRepository extends JpaRepository<Widget, WidgetsID>{

	Optional<Widget> findByWidgetId(Long widgetId);
	

	
}
