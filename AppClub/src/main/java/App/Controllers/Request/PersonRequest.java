package App.Controllers.Request;

/**
 * @author Arbaces Restrepo, Yhogan Viancha, Kevin Parra
 */

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PersonRequest {
    private String document;
    private String name;
    private String cellPhone;
    
}
