package io.github.julianobrl.seritycommon.entitys;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import io.github.julianobrl.seritycommon.enums.QuestionType;
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
@Table(name = "Question")
@EqualsAndHashCode
public class Question {

	@Id
	@GeneratedValue
	private Long id;
	
	@NotNull
	private String title;
	
	@NotNull
	private int answersCount;
	
	@NotNull
	private String answers;
	
	@NotNull
	private QuestionType type;
	
	@NotNull
	private int correctAnswersIndex;
	
	@NotNull
	@OneToOne
	private User author;
	
	@Builder.Default
	@JsonProperty(access = Access.WRITE_ONLY)
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	private LocalDateTime createdAt = LocalDateTime.now();
	
	@Builder.Default
	@JsonProperty(access = Access.WRITE_ONLY)
	private boolean active = true;
	
}
