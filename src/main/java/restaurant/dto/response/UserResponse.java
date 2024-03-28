package restaurant.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import restaurant.enums.Role;
@Getter
@Setter
@Builder
public class UserResponse {
    private String token;
    private String email;
    private Long id;
    private Role role;

    public UserResponse(String token, String email, Long id, Role role) {
        this.token = token;
        this.email = email;
        this.id = id;
        this.role = role;
    }
    public UserResponse( String email, Long id, Role role) {
        this.token = token;
        this.email = email;
        this.id = id;
        this.role = role;
    }

}
