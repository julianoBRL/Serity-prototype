package io.github.julianobrl.seritycommon.entitys;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "History")
@EqualsAndHashCode
public class History{
	
	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	@OneToOne
	private User student;
	
	@NotNull
	@OneToOne
	private User teacher;
	
	@NotNull
	@OneToOne
	private Room room;
	
	@ManyToMany
	private List<Grade> grades;
	
	@NotNull
	@OneToOne
	private Bimester bimester;
	
	@NotNull
	@OneToOne
	private Subject subject;
	
	@NotNull
	private int year;
	
	@Builder.Default
	@JsonProperty(access = Access.WRITE_ONLY)
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	private LocalDateTime createdAt = LocalDateTime.now();
	
	@Builder.Default
	@JsonProperty(access = Access.WRITE_ONLY)
	private boolean active = true;
	
}
