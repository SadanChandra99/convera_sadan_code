package com.convera.postgress.data.api.repository.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.convera.postgress.data.api.repository.model.payments.Address;
import com.convera.postgress.data.api.repository.model.payments.PayorDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



//@Data
//@Builder
@NoArgsConstructor
//@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Entity
@IdClass(WidgetsID.class)
@Table(name = "widget")
public class Widget implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @NotNull
    @JsonProperty
	private Long widgetId;
	
    @Id
    @NotNull
    @JsonProperty
    private String widgetName;
    
    @NotNull
    @JsonProperty
    private String template;
    
    @NotNull
    @JsonProperty
    private String imageUrl;
    
    @NotNull
    private boolean active;
    

    @JsonIgnore
    @CreationTimestamp
    private LocalDateTime createdOn;
    

    @JsonIgnore
    @UpdateTimestamp
    private LocalDateTime updatedOn;
    
    @JsonProperty
    @NotNull
    private Integer numberOfRecords;
    
    @Transient
    private Integer position;

	public Widget(@NotNull Long widgetId, @NotNull String widgetName, @NotNull String template,
			@NotNull String imageUrl, @NotNull boolean active, LocalDateTime createdOn, LocalDateTime updatedOn,
			@NotNull Integer numberOfRecords) {
		super();
		this.widgetId = widgetId;
		this.widgetName = widgetName;
		this.template = template;
		this.imageUrl = imageUrl;
		this.active = active;
		this.createdOn = createdOn;
		this.updatedOn = updatedOn;
		this.numberOfRecords = numberOfRecords;
	}

	public Long getWidgetId() {
		return widgetId;
	}

	public void setWidgetId(Long widgetId) {
		this.widgetId = widgetId;
	}

	public String getWidgetName() {
		return widgetName;
	}

	public void setWidgetName(String widgetName) {
		this.widgetName = widgetName;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}

	public LocalDateTime getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(LocalDateTime updatedOn) {
		this.updatedOn = updatedOn;
	}

	public Integer getNumberOfRecords() {
		return numberOfRecords;
	}

	public void setNumberOfRecords(Integer numberOfRecords) {
		this.numberOfRecords = numberOfRecords;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

    

}
