package App.Controllers.Validator;

/**
 * @author Arbaces Restrepo, Yhogan Viancha, Kevin Parra
 */

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@Component
public class GuestValidator extends CommonsValidator{
    public void validStatus(String status) throws Exception {
        super.isValidString("el estado del invitado ", status);
    }
}
