package com.convera.postgress.data.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.convera.postgress.data.api.repository.model.QuickLink;
import com.convera.postgress.data.api.repository.model.QuickLinkID;

@Repository
public interface QuickLinkRepository extends JpaRepository<QuickLink, QuickLinkID>{

	public Optional<QuickLink> findByLinkId(Integer id);
}
