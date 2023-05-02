package user.Data.entity;

import common.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import common.enums.AuthenticationTypeE;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@Inheritance(strategy=InheritanceType.JOINED)
public class User extends AbstractEntity {
	private String username;
	private String email;
	private String password;
	private boolean enabled;

	
	@Enumerated(EnumType.STRING)
	@Column(name = "auth_type")
	private AuthenticationTypeE authType;
}
