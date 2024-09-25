package App.Dto;

/**
 * @author Arbaces Restrepo, Yhogan Viancha, Kevin Parra
 */

import App.Dto.Interfaces.InvoiceDetailDtoInterface;

import App.Controllers.Utils;
import App.Controllers.Validator.InvoiceDetailValidator;
import App.Model.Invoice;

public class InvoiceDetailDto implements InvoiceDetailDtoInterface{
    private final InvoiceDetailValidator invoiceDetailValidator = new InvoiceDetailValidator();

    private long id;
    private Invoice invoiceId;
    private int itemNumber;
    private String description;
    private double amount;

    @Override
    public void getInvoiceDetailDescriptionDto() throws Exception {
        System.out.println("Ingrese la descrpción del detalle");
        String invoiceDetailDesctiptionDto = Utils.getReader().nextLine();
        this.invoiceDetailValidator.validDescription( invoiceDetailDesctiptionDto );
        this.description = invoiceDetailDesctiptionDto ;
    }

    @Override
    public void getInvoiceDetailAmountDto() throws Exception {
        System.out.println("Ingrese el monto del detalle");
        String invoiceDetailAmountDto = Utils.getReader().nextLine();
        this.amount = this.invoiceDetailValidator.validAmount( invoiceDetailAmountDto ) ;
    }
    
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Invoice getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Invoice invoiceId) {
        this.invoiceId = invoiceId;
    }

    public int getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(int itemNumber) {
        this.itemNumber = itemNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    

}
