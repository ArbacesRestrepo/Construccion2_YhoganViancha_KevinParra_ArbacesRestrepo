package App.Controllers.Request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Arbaces Restrepo, Yhogan Viancha, Kevin Parra
 */

@Getter
@Setter
@NoArgsConstructor
public class PersonRequest {
    private String document;
    private String name;
    private String cellPhone;
    
}
