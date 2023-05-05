package net.codejava.user.Data.entity;

import net.codejava.common.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.codejava.common.enums.AuthenticationTypeE;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@Inheritance(strategy=InheritanceType.JOINED)
public class User extends AbstractEntity {
	private String fullname;
	private String email;
	private String password;
	private boolean verified;
	private String token;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "auth_type")
	private AuthenticationTypeE authType;
}
