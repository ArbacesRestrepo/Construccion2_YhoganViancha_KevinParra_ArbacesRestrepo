package App.Controllers.Requst;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Arbaces Restrepo, Yhogan Viancha, Kevin Parra
 */

@Getter
@Setter
@NoArgsConstructor
public class UserRequest {
    private String personnId;
    private String userName;
    private String password;
    private String role;
}
