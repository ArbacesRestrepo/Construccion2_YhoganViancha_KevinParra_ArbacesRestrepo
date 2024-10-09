package App.Dto;

/**
 * @author Arbaces Restrepo, Yhogan Viancha, Kevin Parra
 */

import java.sql.Timestamp;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import App.Model.User;

@Getter
@Setter
@NoArgsConstructor
public class PartnerInvoiceAmountDto {
    private long id;
    private User userId;
    private double amount;
    private String type;
    private Timestamp creationDate;
    private double invoiceAmount;
}
