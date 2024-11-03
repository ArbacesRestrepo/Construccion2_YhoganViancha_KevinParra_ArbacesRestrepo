package App.Controllers.Request;

import java.util.ArrayList;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Arbaces Restrepo, Yhogan Viancha, Kevin Parra
 */

@Getter
@Setter
@NoArgsConstructor
public class InvoiceRequest {
    private String document;
    private ArrayList<InvoiceDetailRequest> invoiceDetails;
}
