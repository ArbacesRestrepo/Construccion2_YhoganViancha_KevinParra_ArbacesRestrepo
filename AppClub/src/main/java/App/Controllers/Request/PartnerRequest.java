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
public class PartnerRequest {
    private String document;
    private String amount;
    private String type;
}
