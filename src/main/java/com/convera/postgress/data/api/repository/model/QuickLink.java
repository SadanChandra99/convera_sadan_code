package com.convera.postgress.data.api.repository.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
//@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Entity
@IdClass(QuickLinkID.class)
@Table(name = "quick_links")
@Builder
public class QuickLink {
	
	@Id
	@NotNull
	@JsonProperty
	@Column(name = "link_id")
	private Integer linkId;
	
	@Id
	@NotNull
	@JsonProperty
	@Column(name = "link_name")
	private String linkName;
	

	
    @JsonIgnore
    @UpdateTimestamp
    @Column(name= "created_on", nullable = false, updatable = false)
    private LocalDateTime updatedBy;
    
    @JsonIgnore
    @UpdateTimestamp
    @Column(name= "updated_on", nullable = false, updatable = false)
    private LocalDateTime updatedOn;

	public QuickLink(@NotNull Integer linkId, @NotNull String linkName, 
			LocalDateTime updatedBy, LocalDateTime updatedOn) {
		super();
		this.linkId = linkId;
		this.linkName = linkName;
		this.updatedBy = updatedBy;
		this.updatedOn = updatedOn;
	}
    
    

}
