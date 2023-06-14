package com.convera.postgress.data.api.repository.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.vladmihalcea.hibernate.type.json.JsonStringType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Table(name = "dashboard_preference", schema = "public")
@TypeDef(name = "json", typeClass = JsonStringType.class)
@Entity
public class DashboardPreference implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @NotNull
    @JsonProperty
    @Column(name= "userPreferenceId")
	private Integer userPreferenceId;
	
    
    @JsonIgnore
    @CreationTimestamp
    @Column(name= "createdon")
    private LocalDateTime createdOn;

    
    @JsonIgnore
    @UpdateTimestamp
    @Column(name= "updatedon")
    private LocalDateTime updatedOn;
    
    @NotNull
    @Type(type = "json")
    @JsonProperty
    @Column
    private WidgetQuickLink widgetQuickLink;


}
