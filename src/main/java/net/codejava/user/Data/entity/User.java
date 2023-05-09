package net.codejava.user.Data.entity;

import net.codejava.common.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.codejava.common.enums.AuthenticationTypeE;
import net.codejava.common.enums.RoleTypeE;

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

	private boolean profileCompleted;

	private int age;
	private int height;
	private int weight;

	@Enumerated(EnumType.STRING)
	@Column(name = "auth_type")
	private AuthenticationTypeE authType;

	@Enumerated(EnumType.STRING)
	private RoleTypeE role;
}
