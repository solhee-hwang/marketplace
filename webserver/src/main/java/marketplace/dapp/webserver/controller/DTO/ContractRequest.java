package marketplace.dapp.webserver.controller.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContractRequest {
    private Long userId;
    private int price;
    private Long goodsId;

}
