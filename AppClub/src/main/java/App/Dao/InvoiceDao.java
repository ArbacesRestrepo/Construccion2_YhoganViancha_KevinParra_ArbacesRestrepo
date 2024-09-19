package App.Dao;

/**
 * @author Arbaces Restrepo, Yhogan Viancha, Kevin Parra
 */

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import App.Config.MYSQLConnection;

import App.Dao.Interfaces.InvoiceDaoInterface;
import App.Dao.Repository.InvoiceRepository;
import App.Dto.InvoiceDto;
import App.Dto.PartnerDto;
import App.Dto.PersonDto;
import App.Helper.Helper;
import App.Model.Invoice;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class InvoiceDao implements InvoiceDaoInterface {
    InvoiceRepository invoiceRepository;

    @Override
    public double amountActiveInvoices( PersonDto personDto ) throws Exception {
        String query = "SELECT SUM( AMOUNT ) AS AMOUNT FROM INVOICE WHERE PERSONID = ? AND STATUS = ?";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setLong( 1, personDto.getId() );
        preparedStatement.setString( 2, "PENDIENTE" );
        ResultSet resulSet = preparedStatement.executeQuery();
        if (resulSet.next()) {
            double amointActiveInvoicesDao = resulSet.getDouble( "AMOUNT");
            resulSet.close();
            preparedStatement.close();
            return amointActiveInvoicesDao;
        }
                
        resulSet.close();
        preparedStatement.close();        
        return 0;
    }
    
    @Override
    public double amountInvoicesByPartner( PartnerDto partnerDto ) throws Exception {
        String query = "SELECT SUM( AMOUNT ) AS AMOUNT FROM INVOICE WHERE PARTNERID = ? ";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setLong( 1, partnerDto.getId() );
        ResultSet resulSet = preparedStatement.executeQuery();
        if (resulSet.next()) {
            double amointActiveInvoicesDao = resulSet.getDouble( "AMOUNT");
            resulSet.close();
            preparedStatement.close();
            return amointActiveInvoicesDao;
        }
                
        resulSet.close();
        preparedStatement.close();        
        return 0;
    }
    
    @Override
    public InvoiceDto firstActiveInvoice( PartnerDto partnerDto ) throws Exception {
        String query = "SELECT ID, PERSONID, PARTNERID, CREATIONDATE, AMOUNT, STATUS FROM INVOICE WHERE PARTNERID = ? AND STATUS = ? ORDER BY CREATIONDATE DESC";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setLong( 1, partnerDto.getId() );
        preparedStatement.setString( 2, "PENDIENTE" );
        ResultSet resulSet = preparedStatement.executeQuery();

        if (resulSet.next()) {
            Invoice invoice = new Invoice();
            invoice.setId( resulSet.getLong( "ID" ) );
            invoice.setPersonId( resulSet.getLong( "PERSONID" ) );
            invoice.setPartnerId( resulSet.getLong( "PARTNERID" ) );
            invoice.setCreationDate( resulSet.getDate( "CREATIONDATE" ) );
            invoice.setAmount( resulSet.getDouble( "AMOUNT" ) );
            invoice.setStatus( resulSet.getString( "STATUS" ) );

            resulSet.close();
            preparedStatement.close();
            return Helper.parse(invoice);
        }
                
        resulSet.close();
        preparedStatement.close();        
        return null;
    }

    @Override
    public InvoiceDto firstInvoiceByPartnerId( PartnerDto partnerDto ) throws Exception {
        String query = "SELECT ID, PERSONID, PARTNERID, CREATIONDATE, AMOUNT, STATUS FROM INVOICE WHERE PARTNERID = ? ORDER BY CREATIONDATE DESC";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setLong( 1, partnerDto.getId() );
        ResultSet resulSet = preparedStatement.executeQuery();

        if (resulSet.next()) {
            Invoice invoice = new Invoice();
            invoice.setId( resulSet.getLong( "ID" ) );
            invoice.setPersonId( resulSet.getLong( "PERSONID" ) );
            invoice.setPartnerId( resulSet.getLong( "PARTNERID" ) );
            invoice.setCreationDate( resulSet.getDate( "CREATIONDATE" ) );
            invoice.setAmount( resulSet.getDouble( "AMOUNT" ) );
            invoice.setStatus( resulSet.getString( "STATUS" ) );

            resulSet.close();
            preparedStatement.close();
            return Helper.parse(invoice);
        }
                
        resulSet.close();
        preparedStatement.close();        
        return null;
    }
    
    @Override
    public InvoiceDto firstInvoiceByPersonId( PersonDto personDto ) throws Exception {
        String query = "SELECT ID, PERSONID, PARTNERID, CREATIONDATE, AMOUNT, STATUS FROM INVOICE WHERE PERSONID = ? ORDER BY CREATIONDATE DESC";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setLong( 1, personDto.getId() );
        ResultSet resulSet = preparedStatement.executeQuery();

        if (resulSet.next()) {
            Invoice invoice = new Invoice();
            invoice.setId( resulSet.getLong( "ID" ) );
            invoice.setPersonId( resulSet.getLong( "PERSONID" ) );
            invoice.setPartnerId( resulSet.getLong( "PARTNERID" ) );
            invoice.setCreationDate( resulSet.getDate( "CREATIONDATE" ) );
            invoice.setAmount( resulSet.getDouble( "AMOUNT" ) );
            invoice.setStatus( resulSet.getString( "STATUS" ) );

            resulSet.close();
            preparedStatement.close();
            return Helper.parse(invoice);
        }
                
        resulSet.close();
        preparedStatement.close();        
        return null;
    }
    
    @Override
    public long lastInvoiceByPartnerId( PartnerDto partnerDto ) throws Exception {
        String query = "SELECT MAX( ID ) AS ID FROM INVOICE WHERE PARTNERID = ? ";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setLong( 1, partnerDto.getId() );
        ResultSet resulSet = preparedStatement.executeQuery();

        if (resulSet.next()) {
            long lastInvoice = resulSet.getLong( "ID" );
            return lastInvoice;
        }
                
        resulSet.close();
        preparedStatement.close();        
        return 0;
    }

    @Override
    public long lastInvoiceByPersonId( PersonDto personDto ) throws Exception {
        String query = "SELECT MAX( ID ) AS ID FROM INVOICE WHERE PERSONID = ? ";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setLong( 1, personDto.getId() );
        ResultSet resulSet = preparedStatement.executeQuery();

        if (resulSet.next()) {
            long lastInvoice = resulSet.getLong( "ID" );
            return lastInvoice;
        }
                
        resulSet.close();
        preparedStatement.close();        
        return 0;
    }

    @Override
    public void createInvoice( InvoiceDto invoiceDto ) throws Exception {
        Invoice invoice = Helper.parse( invoiceDto );
        invoice.setCreationDate( LocalDateTime.now().toString() );
        invoiceRepository.save( invoice );
    }
    
    @Override
    public void updateInvoiceAmount( InvoiceDto invoiceDto ) throws Exception {
        Invoice invoice = Helper.parse( invoiceDto );
        invoiceRepository.save( invoice );
    }

    @Override
    public void deleteInvoice( InvoiceDto invoiceDto ) throws Exception {
        Invoice invoice = Helper.parse( invoiceDto );
        invoiceRepository.deleteById( invoice.getId() );
    }

    @Override
    public void cancelInvoice( InvoiceDto invoiceDto ) throws Exception {
        Invoice invoice = Helper.parse( invoiceDto );
        invoice.setStatus("CANCELADA");
        invoiceRepository.save( invoice );
    }
    
    @Override
    public ArrayList<InvoiceDto> listClubInvoices( ) throws Exception{
        ArrayList<InvoiceDto> listInvoices = new ArrayList<InvoiceDto>();

        String query = "SELECT ID, PERSONID, PARTNERID, CREATIONDATE, AMOUNT, STATUS FROM INVOICE ORDER BY CREATIONDATE DESC";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        ResultSet resulSet = preparedStatement.executeQuery();

        while (resulSet.next()) {
            Invoice invoice = new Invoice();
            invoice.setId( resulSet.getLong( "ID" ) );
            invoice.setPersonId( resulSet.getLong( "PERSONID" ) );
            invoice.setPartnerId( resulSet.getLong( "PARTNERID" ) );
            invoice.setCreationDate( resulSet.getDate( "CREATIONDATE" ) );
            invoice.setAmount( resulSet.getDouble( "AMOUNT" ) );
            invoice.setStatus( resulSet.getString( "STATUS" ) );

            listInvoices.add( Helper.parse(invoice) );
        }
        resulSet.close();
        preparedStatement.close();
        
        
        return listInvoices;
    }
    
    @Override
    public ArrayList<InvoiceDto> listPartnerInvoices( PartnerDto partnerDto ) throws Exception{
        ArrayList<InvoiceDto> listInvoices = new ArrayList<InvoiceDto>();

        String query = "SELECT ID, PERSONID, PARTNERID, CREATIONDATE, AMOUNT, STATUS FROM INVOICE WHERE PARTNERID = ? ORDER BY CREATIONDATE DESC";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setLong( 1, partnerDto.getId() );
        ResultSet resulSet = preparedStatement.executeQuery();

         while (resulSet.next()) {
            Invoice invoice = new Invoice();
            invoice.setId( resulSet.getLong( "ID" ) );
            invoice.setPersonId( resulSet.getLong( "PERSONID" ) );
            invoice.setPartnerId( resulSet.getLong( "PARTNERID" ) );
            invoice.setCreationDate( resulSet.getDate( "CREATIONDATE" ) );
            invoice.setAmount( resulSet.getDouble( "AMOUNT" ) );
            invoice.setStatus( resulSet.getString( "STATUS" ) );

            listInvoices.add( Helper.parse(invoice) );
        }
        resulSet.close();
        preparedStatement.close();


        return listInvoices;
    }
    
    @Override
    public ArrayList<InvoiceDto> listGuestInvoices( PersonDto personDto ) throws Exception{
        ArrayList<InvoiceDto> listInvoices = new ArrayList<InvoiceDto>();

        String query = "SELECT ID, PERSONID, PARTNERID, CREATIONDATE, AMOUNT, STATUS FROM INVOICE WHERE PERSONID = ? ORDER BY CREATIONDATE DESC";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setLong( 1, personDto.getId() );
        ResultSet resulSet = preparedStatement.executeQuery();

         while (resulSet.next()) {
            Invoice invoice = new Invoice();
            invoice.setId( resulSet.getLong( "ID" ) );
            invoice.setPersonId( resulSet.getLong( "PERSONID" ) );
            invoice.setPartnerId( resulSet.getLong( "PARTNERID" ) );
            invoice.setCreationDate( resulSet.getDate( "CREATIONDATE" ) );
            invoice.setAmount( resulSet.getDouble( "AMOUNT" ) );
            invoice.setStatus( resulSet.getString( "STATUS" ) );

            listInvoices.add( Helper.parse(invoice) );
        }
        resulSet.close();
        preparedStatement.close();


        return listInvoices;
    }
}
