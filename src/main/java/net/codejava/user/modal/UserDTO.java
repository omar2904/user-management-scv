package net.codejava.user.modal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.codejava.common.enums.AuthenticationTypeE;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    public String fullName;
    public AuthenticationTypeE authType;
}
