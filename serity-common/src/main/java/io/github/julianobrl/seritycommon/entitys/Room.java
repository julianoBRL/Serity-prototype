package io.github.julianobrl.seritycommon.entitys;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import io.github.julianobrl.seritycommon.enums.RoomPeriodType;
import io.github.julianobrl.seritycommon.enums.RoomType;
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
@Table(name = "Room")
@EqualsAndHashCode
public class Room{
	
	@Id
	@GeneratedValue
	private Long id;
	
	@NotNull
	private char category;
	
	@NotNull
	private int serie;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private RoomPeriodType period;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private RoomType type;
	
	@Builder.Default
	@JsonProperty(access = Access.WRITE_ONLY)
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	private LocalDateTime createdAt = LocalDateTime.now();
	
	@Builder.Default
	@JsonProperty(access = Access.WRITE_ONLY)
	private boolean active = true;
		
}
