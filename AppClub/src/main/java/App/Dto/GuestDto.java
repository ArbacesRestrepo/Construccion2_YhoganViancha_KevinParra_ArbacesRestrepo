package App.Dto;

/**
 * @author Arbaces Restrepo, Yhogan Viancha, Kevin Parra
 */

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import App.Dto.Interfaces.GuestDtoInterface;
import App.Model.Partner;
import App.Model.User;

@Getter
@Setter
@NoArgsConstructor
public class GuestDto implements GuestDtoInterface{
    private long id;
    private User userId;
    private Partner partnerId;
    private String status;
}
