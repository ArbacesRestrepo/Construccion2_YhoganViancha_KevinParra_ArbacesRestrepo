package App.Dao.Repository;

/**
 * @author Arbaces Restrepo, Yhogan Viancha, Kevin Parra
 */

import App.Model.Invoice;
import App.Model.InvoiceDetail;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceDetailRepository extends JpaRepository<InvoiceDetail,Long>{
    public void deleteByInvoiceId( Invoice invoice );
    public List<InvoiceDetail> findByInvoiceId( Invoice invoice );
    
}
