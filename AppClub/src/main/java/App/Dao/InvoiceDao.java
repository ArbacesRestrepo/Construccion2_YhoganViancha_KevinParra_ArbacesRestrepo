package App.Dao;

/**
 * @author Arbaces Restrepo, Jhogan Viancha, Kevin Parra
 */
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import App.Config.MYSQLConnection;

import App.Dao.Interfaces.InvoiceDaoInterface;
import App.Dto.InvoiceDto;
import App.Dto.PartnerDto;
import App.Dto.PersonDto;
import App.Helper.Helper;
import App.Model.Invoice;
import java.time.LocalDateTime;
import java.util.ArrayList;
import App.Dao.Repository.InvoiceRepository;
import App.Model.Partner;
import App.Model.Person;
import java.util.List;

public class InvoiceDao implements InvoiceDaoInterface {

    InvoiceRepository invoiceRepository;

    @Override
    public double amountActiveInvoices(PersonDto personDto) throws Exception {
        Person person = Helper.parse(personDto);
        List<Invoice> invoiceList = invoiceRepository.findByPersonId(person);
        double amount = 0;
        for (Invoice invoice : invoiceList) {
            if(invoice.getStatus().equals("PENDIENTE")){
                amount += invoice.getAmount();
            }
        }
        return amount;
    }

    @Override
    public double amountInvoicesByPartner(PartnerDto partnerDto) throws Exception {
        Partner partner = Helper.parse(partnerDto);
        List<Invoice> invoiceList = invoiceRepository.findByPartnerId(partner);
        double amount = 0;
        for (Invoice invoice : invoiceList) {
            if(invoice.getStatus().equals("PENDIENTE")){
                amount += invoice.getAmount();
            }
        }
        return amount;
    }

    @Override
    public InvoiceDto firstActiveInvoice(PartnerDto partnerDto) throws Exception {
        String query = "SELECT ID, PERSONID, PARTNERID, CREATIONDATE, AMOUNT, STATUS FROM INVOICE WHERE PARTNERID = ? AND STATUS = ? ORDER BY CREATIONDATE DESC";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setLong(1, partnerDto.getId());
        preparedStatement.setString(2, "PENDIENTE");
        ResultSet resulSet = preparedStatement.executeQuery();

        if (resulSet.next()) {
            Invoice invoice = new Invoice();
            invoice.setId(resulSet.getLong("ID"));
            invoice.setPersonId(resulSet.getLong("PERSONID"));
            invoice.setPartnerId(resulSet.getLong("PARTNERID"));
            invoice.setCreationDate(resulSet.getDate("CREATIONDATE"));
            invoice.setAmount(resulSet.getDouble("AMOUNT"));
            invoice.setStatus(resulSet.getString("STATUS"));

            resulSet.close();
            preparedStatement.close();
            return Helper.parse(invoice);
        }

        resulSet.close();
        preparedStatement.close();
        return null;
    }

    @Override
    public InvoiceDto firstInvoiceByPartnerId(PartnerDto partnerDto) throws Exception {
        String query = "SELECT ID, PERSONID, PARTNERID, CREATIONDATE, AMOUNT, STATUS FROM INVOICE WHERE PARTNERID = ? ORDER BY CREATIONDATE DESC";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setLong(1, partnerDto.getId());
        ResultSet resulSet = preparedStatement.executeQuery();

        if (resulSet.next()) {
            Invoice invoice = new Invoice();
            invoice.setId(resulSet.getLong("ID"));
            invoice.setPersonId(resulSet.getLong("PERSONID"));
            invoice.setPartnerId(resulSet.getLong("PARTNERID"));
            invoice.setCreationDate(resulSet.getDate("CREATIONDATE"));
            invoice.setAmount(resulSet.getDouble("AMOUNT"));
            invoice.setStatus(resulSet.getString("STATUS"));

            resulSet.close();
            preparedStatement.close();
            return Helper.parse(invoice);
        }

        resulSet.close();
        preparedStatement.close();
        return null;
    }

    @Override
    public InvoiceDto firstInvoiceByPersonId(PersonDto personDto) throws Exception {
        String query = "SELECT ID, PERSONID, PARTNERID, CREATIONDATE, AMOUNT, STATUS FROM INVOICE WHERE PERSONID = ? ORDER BY CREATIONDATE DESC";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setLong(1, personDto.getId());
        ResultSet resulSet = preparedStatement.executeQuery();

        if (resulSet.next()) {
            Invoice invoice = new Invoice();
            invoice.setId(resulSet.getLong("ID"));
            invoice.setPersonId(resulSet.getLong("PERSONID"));
            invoice.setPartnerId(resulSet.getLong("PARTNERID"));
            invoice.setCreationDate(resulSet.getDate("CREATIONDATE"));
            invoice.setAmount(resulSet.getDouble("AMOUNT"));
            invoice.setStatus(resulSet.getString("STATUS"));

            resulSet.close();
            preparedStatement.close();
            return Helper.parse(invoice);
        }

        resulSet.close();
        preparedStatement.close();
        return null;
    }

    @Override
    public long lastInvoiceByPartnerId(PartnerDto partnerDto) throws Exception {
        String query = "SELECT MAX( ID ) AS ID FROM INVOICE WHERE PARTNERID = ? ";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setLong(1, partnerDto.getId());
        ResultSet resulSet = preparedStatement.executeQuery();

        if (resulSet.next()) {
            long lastInvoice = resulSet.getLong("ID");
            return lastInvoice;
        }

        resulSet.close();
        preparedStatement.close();
        return 0;
    }

    @Override
    public long lastInvoiceByPersonId(PersonDto personDto) throws Exception {
        String query = "SELECT MAX( ID ) AS ID FROM INVOICE WHERE PERSONID = ? ";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setLong(1, personDto.getId());
        ResultSet resulSet = preparedStatement.executeQuery();

        if (resulSet.next()) {
            long lastInvoice = resulSet.getLong("ID");
            return lastInvoice;
        }

        resulSet.close();
        preparedStatement.close();
        return 0;
    }

    @Override
    public void createInvoice(InvoiceDto invoiceDto) throws Exception {
        String query = "INSERT INTO INVOICE ( PERSONID, PARTNERID, CREATIONDATE, AMOUNT, STATUS ) VALUES ( ?, ?, ?, 0, ? )";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setLong(1, invoiceDto.getPersonId());
        preparedStatement.setLong(2, invoiceDto.getPartnerId());
        preparedStatement.setString(3, LocalDateTime.now().toString());
        preparedStatement.setString(4, "PENDIENTE");

        preparedStatement.execute();
        preparedStatement.close();
    }

    @Override
    public void updateInvoiceAmount(InvoiceDto invoiceDto) throws Exception {
        String query = "UPDATE INVOICE SET AMOUNT = ? WHERE ID = ?";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setDouble(1, invoiceDto.getAmount());
        preparedStatement.setLong(2, invoiceDto.getId());

        preparedStatement.execute();
        preparedStatement.close();
    }

    @Override
    public void deleteInvoice(InvoiceDto invoiceDto) throws Exception {
        String query = "DELETE FROM INVOICE WHERE ID = ?";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setLong(1, invoiceDto.getId());

        preparedStatement.execute();
        preparedStatement.close();
    }

    @Override
    public void cancelInvoice(InvoiceDto invoiceDto) throws Exception {
        String query = "UPDATE INVOICE SET STATUS = ? WHERE ID = ?";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setString(1, "CANCELADA");
        preparedStatement.setLong(2, invoiceDto.getId());

        preparedStatement.execute();
        preparedStatement.close();
    }

    @Override
    public ArrayList<InvoiceDto> listClubInvoices() throws Exception {
       ArrayList<InvoiceDto> listInvoices = new ArrayList<InvoiceDto>();
        List<Invoice> invoiceList = invoiceRepository.findById();
        for (Invoice invoice : invoiceList) {
            listInvoices.add(Helper.parse(invoice));
        }
        return listInvoices;
    }

    @Override
    public ArrayList<InvoiceDto> listPartnerInvoices(PartnerDto partnerDto) throws Exception {
       ArrayList<InvoiceDto> listInvoices = new ArrayList<InvoiceDto>();
        Partner partner = Helper.parse(partnerDto);
        List<Invoice> invoiceList = invoiceRepository.findByPartnerId(partner);
        for (Invoice invoice : invoiceList) {
            listInvoices.add(Helper.parse(invoice));
        }
        return listInvoices;
    }

    @Override
    public ArrayList<InvoiceDto> listPersonInvoices(PersonDto personDto) throws Exception {
        ArrayList<InvoiceDto> listInvoices = new ArrayList<InvoiceDto>();
        Person person = Helper.parse(personDto);
        List<Invoice> invoiceList = invoiceRepository.findByPersonId(person);
        for (Invoice invoice : invoiceList) {
            listInvoices.add(Helper.parse(invoice));
        }
        return listInvoices;
    }
}
