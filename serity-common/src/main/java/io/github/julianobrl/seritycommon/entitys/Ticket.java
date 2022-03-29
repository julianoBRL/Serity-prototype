package io.github.julianobrl.seritycommon.entitys;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

import io.github.julianobrl.seritycommon.enums.TicketStatus;
import io.github.julianobrl.seritycommon.enums.TicketType;
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
@Table(name = "Ticket")
@EqualsAndHashCode
public class Ticket{
	
	@Id
	@GeneratedValue
	private Long id;
	
	@NotNull
	@OneToOne
	private User sender;
	
	@NotNull
	@OneToOne
	private User reviewer;
	
	@NotNull
	private String title;
	
	@NotNull
	private String description;
	
	@ManyToMany
	private List<Comment> comments;
	
	@NotNull
	@Builder.Default
	@Enumerated(EnumType.STRING)
	private TicketStatus status = TicketStatus.OPEN;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private TicketType type;
	
	@Builder.Default
	@JsonProperty(access = Access.WRITE_ONLY)
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	private LocalDateTime createdAt = LocalDateTime.now();
	
	@Builder.Default
	@JsonProperty(access = Access.WRITE_ONLY)
	private boolean active = true;

}
