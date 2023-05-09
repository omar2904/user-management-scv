package net.codejava.user.modal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.codejava.common.enums.AuthenticationTypeE;
import net.codejava.common.enums.RoleTypeE;
import org.springframework.stereotype.Component;

@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    public String fullName;
    public AuthenticationTypeE authType;

    public boolean verified;
    public String token;

    public boolean profileCompleted;

    public int age;
    public int height;
    public int weight;

    public RoleTypeE role;
}
