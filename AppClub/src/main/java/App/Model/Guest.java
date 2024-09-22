package App.Model;

/**
 * @author Arbaces Restrepo, Yhogan Viancha, Kevin Parra
 */

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="guest")
public class Guest {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private long id;
    @JoinColumn(name="userid")
    @OneToOne
    private User userId;
    @JoinColumn(name="partnerid")
    @ManyToOne
    private Partner partnerId;
    @Column(name="status")
    private String status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Partner getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(Partner partnerId) {
        this.partnerId = partnerId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
    
}
