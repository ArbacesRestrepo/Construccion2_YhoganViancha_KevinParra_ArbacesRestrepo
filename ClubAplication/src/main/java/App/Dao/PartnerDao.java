package App.Dao;

/**
 * @author Arbaces Restrepo, Jhogan Viancha
 */

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import App.Config.MYSQLConnection;
import App.Dto.UserDto;
import App.Dto.PartnerDto;
import App.Dto.GuestDto;
import App.Helper.Helper;
import App.Model.Partner;
import java.time.LocalDateTime;
import App.Dao.Interfaces.PartnerDaoInterface;
import App.Dto.InvoiceDto;
import java.util.ArrayList;

public class PartnerDao implements PartnerDaoInterface{

    @Override
    public boolean existsByUserId(UserDto userDto) throws Exception {
        String query = "SELECT ID, USERID, TYPE, AMOUNT, CREATIONDATE FROM PARTNER WHERE USERID = ?";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setString(1, String.valueOf( userDto.getId() ) );
        ResultSet resulSet = preparedStatement.executeQuery();
        boolean exists = resulSet.next();
        resulSet.close();
        preparedStatement.close();
        return exists;
    }

    @Override
    public void createPartner(PartnerDto partnerDto) throws Exception {
        String query = "INSERT INTO PARTNER( USERID, TYPE, AMOUNT, CREATIONDATE ) VALUES (?,?,?,?) ";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setLong( 1, partnerDto.getUserId() );
        preparedStatement.setString( 2, partnerDto.getType() );
        preparedStatement.setDouble( 3, partnerDto.getAmount() );
        preparedStatement.setString( 4, LocalDateTime.now().toString() );

        preparedStatement.execute();
        preparedStatement.close();
    }

    @Override
    public void updateAmountPartner(PartnerDto partnerDto) throws Exception {
        String query = "UPDATE PARTNER SET AMOUNT = ? WHERE ID = ? ";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setDouble( 1, partnerDto.getAmount());
        preparedStatement.setLong( 2, partnerDto.getId());

        preparedStatement.execute();
        preparedStatement.close();
    }

    @Override
    public void updateTypePartner(PartnerDto partnerDto) throws Exception {
        String query = "UPDATE PARTNER SET TYPE = ? WHERE ID = ? ";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setString( 1, partnerDto.getType() );
        preparedStatement.setLong( 2, partnerDto.getId());

        preparedStatement.execute();
        preparedStatement.close();
    }

    @Override
    public void deletePartner(PartnerDto partnerDto) throws Exception {
        String query = "DELETE FROM PARTNER WHERE ID = ? ";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setLong( 1, partnerDto.getId());

        preparedStatement.execute();
        preparedStatement.close();
    }

    @Override
    public PartnerDto findByUserId( UserDto userDto ) throws Exception {        
        String query = "SELECT ID, USERID, TYPE, AMOUNT, CREATIONDATE FROM PARTNER WHERE USERID = ?";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setString(1, String.valueOf( userDto.getId() ) );
        ResultSet resulSet = preparedStatement.executeQuery();
        if (resulSet.next()) {
            Partner partner = new Partner();
            partner.setId( resulSet.getLong("ID") );
            partner.setUserId( resulSet.getLong("USERID") );
            partner.setType( resulSet.getString( "TYPE") );
            partner.setAmount( resulSet.getDouble( "AMOUNT") );
            partner.setCreationDate( resulSet.getDate( "CREATIONDATE") );

            resulSet.close();
            preparedStatement.close();
            return Helper.parse(partner);
        }
                
        resulSet.close();
        preparedStatement.close();        
        return null;
    }

    @Override
    public PartnerDto findByGuestPartnerId( GuestDto guestDto ) throws Exception {        
        String query = "SELECT ID, USERID, TYPE, AMOUNT, CREATIONDATE FROM PARTNER WHERE USERID = ?";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setString(1, String.valueOf( guestDto.getPartnerId() ) );
        ResultSet resulSet = preparedStatement.executeQuery();
        if (resulSet.next()) {
            Partner partner = new Partner();
            partner.setId( resulSet.getLong("ID") );
            partner.setUserId( resulSet.getLong("USERID") );
            partner.setType( resulSet.getString( "TYPE") );
            partner.setAmount( resulSet.getDouble( "AMOUNT") );
            partner.setCreationDate( resulSet.getDate( "CREATIONDATE") );

            resulSet.close();
            preparedStatement.close();
            return Helper.parse(partner);
        }
                
        resulSet.close();
        preparedStatement.close();        
        return null;
    }

    @Override
    public PartnerDto findByPartnerId( InvoiceDto invoiceDto ) throws Exception {
        String query = "SELECT ID, USERID, TYPE, AMOUNT, CREATIONDATE FROM PARTNER WHERE USERID = ?";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setString(1, String.valueOf( invoiceDto.getPartnerId() ) );
        ResultSet resulSet = preparedStatement.executeQuery();
        if (resulSet.next()) {
            Partner partner = new Partner();
            partner.setId( resulSet.getLong("ID") );
            partner.setUserId( resulSet.getLong("USERID") );
            partner.setType( resulSet.getString( "TYPE") );
            partner.setAmount( resulSet.getDouble( "AMOUNT") );
            partner.setCreationDate( resulSet.getDate( "CREATIONDATE") );

            resulSet.close();
            preparedStatement.close();
            return Helper.parse(partner);
        }
                
        resulSet.close();
        preparedStatement.close();        
        return null;
    }

    @Override
    public long numberPartnersVIP(  ) throws Exception {
        String query = "SELECT COUNT( ID ) AS NUMBERVIP FROM PARTNER WHERE TYPE = ?";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setString(1, "VIP" );
        ResultSet resulSet = preparedStatement.executeQuery();
        if (resulSet.next()) {
            long numberVIPDao = resulSet.getLong("NUMBERVIP") ;
            resulSet.close();
            preparedStatement.close();
            return numberVIPDao;
        }
                
        resulSet.close();
        preparedStatement.close();        
        return 0;
    }

    @Override
    public long numberPartnersRequestVIP(  ) throws Exception {
        String query = "SELECT COUNT( ID ) AS NUMBERREQUEST FROM PARTNER WHERE TYPE = ?";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setString(1, "PIDE CAMBIO A VIP" );
        ResultSet resulSet = preparedStatement.executeQuery();
        if (resulSet.next()) {
            long numberVIPDao = resulSet.getLong("NUMBERREQUEST") ;
            resulSet.close();
            preparedStatement.close();
            return numberVIPDao;
        }
                
        resulSet.close();
        preparedStatement.close();        
        return 0;
    }
    
    @Override
    public ArrayList<PartnerDto> listPartnerRequestVIP( ) throws Exception{
        ArrayList<PartnerDto> listPartners = new ArrayList<PartnerDto>();
        
        String query = "SELECT ID, USERID, TYPE, AMOUNT, CREATIONDATE FROM PARTNER WHERE TYPE = ? ORDER BY AMOUNT DESC, CREATIONDATE DESC";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setString(1, String.valueOf( "PIDE CAMBIO A VIP" ) );
        ResultSet resulSet = preparedStatement.executeQuery();

        while (resulSet.next()) {
            Partner partner = new Partner();
            partner.setId( resulSet.getLong("ID") );
            partner.setUserId( resulSet.getLong("USERID") );
            partner.setType( resulSet.getString( "TYPE") );
            partner.setAmount( resulSet.getDouble( "AMOUNT") );
            partner.setCreationDate( resulSet.getDate( "CREATIONDATE") );
            
            listPartners.add( Helper.parse( partner ) );
        }
        resulSet.close();
        preparedStatement.close();
        
        return listPartners;
    }
    
}
