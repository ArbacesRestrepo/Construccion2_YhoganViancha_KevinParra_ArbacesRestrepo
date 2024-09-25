package App.Controllers.Validator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * @author Arbaces Restrepo, Yhogan Viancha, Kevin Parra
 */

@Getter
@Setter
@NoArgsConstructor
@Component
public class InvoiceDetailValidator extends CommonsValidator{
    public double validAmount(String amount) throws Exception{
        return super.isValidDouble("el monto del detalle ", amount);
    }

    public void validDescription(String description) throws Exception {
        super.isValidString("la descripción ", description);
    }
}
