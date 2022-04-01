package io.github.julianobrl.seritycommon.entitys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ToStringExclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;



@Data
@Entity
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "User")
public class User{
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
	
    @NotNull(message = "The field 'username' is mandatory")
    @Column(nullable = false)
    private String username;
    
    @NotNull(message = "The field 'password' is mandatory")
    @Column(nullable = false)
    @ToStringExclude
    private String password;
    
    @NotNull(message = "The field 'role' is mandatory")
    @Column(nullable = false)
    @Builder.Default
    private String role = "USER";

    public User(@NotNull User applicationUser) {
        this.id = applicationUser.getId();
        this.username = applicationUser.getUsername();
        this.password = applicationUser.getPassword();
        this.role = applicationUser.getRole();
    }
		
}
