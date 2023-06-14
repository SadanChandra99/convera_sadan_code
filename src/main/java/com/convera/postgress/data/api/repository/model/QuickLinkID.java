package com.convera.postgress.data.api.repository.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuickLinkID implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer linkId;

	private String linkName;
}
