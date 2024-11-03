package App.Controllers.Request;

/**
 * @author Arbaces Restrepo, Yhogan Viancha, Kevin Parra
 */

import java.util.ArrayList;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InvoiceRequest {
    private String document;
    private ArrayList<InvoiceDetailRequest> invoiceDetails;
}
