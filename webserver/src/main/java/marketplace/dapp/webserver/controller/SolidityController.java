package marketplace.dapp.webserver.controller;

import lombok.RequiredArgsConstructor;
import marketplace.dapp.webserver.controller.DTO.ContractRequest;
import marketplace.dapp.webserver.service.SolidityService;
import marketplace.dapp.webserver.solidity.MarketplaceAbi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tuples.generated.Tuple6;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/solidity")
@RestController
@RequiredArgsConstructor
public class SolidityController {
    Web3j web3j = Web3j.build(new HttpService("http://localhost:7545")); // Ethereum 네트워크 주소에 맞게 수정
    String privateKey = "0x4dc3413644829c96ccf0414acdf7510bb190655057a95594a6a1e28d5583a976";
    Credentials credentials = Credentials.create(privateKey);
    // TransactionManager 생성
    TransactionManager transactionManager = new RawTransactionManager(web3j, credentials);
    BigInteger GAS_PRICE = BigInteger.valueOf(364312179);
    BigInteger GAS_LIMIT = BigInteger.valueOf(3000000);
    String contractAddress = "0xe7BD86b7b19606415fB8eBC2eE8be0dc84b80fFF"; // 실제 컨트랙트 주소로 변경
    MarketplaceAbi contract = MarketplaceAbi.load(contractAddress, web3j, credentials, GAS_PRICE, GAS_LIMIT);
    private final SolidityService solidityService;
    @GetMapping("/getdetail")
    public ResponseEntity<?> registGoods(@RequestParam Long goodsId) throws Exception {
        BigInteger GOODSID = BigInteger.valueOf(goodsId);
        solidityService.registGoods(contract,goodsId,GOODSID);
        return ResponseEntity.ok("ok");
//        RemoteFunctionCall<TransactionReceipt> transactionReceipt = contract.createItem(GOODSID, PRICE);
//        try {
//            TransactionReceipt receipt = transactionReceipt.send();
//            return ResponseEntity.ok(receipt);
//            // 트랜잭션 결과를 처리하는 코드 작성
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//            // 예외 처리
//        }
    }
    @PostMapping("/bidplacing")
    public ResponseEntity<?> bidplacing(@RequestBody ContractRequest contractRequest){
        BigInteger PRICE = BigInteger.valueOf(contractRequest.getPrice());
        solidityService.bidplacing(contract, contractRequest, PRICE);
    }
    @GetMapping("/getGoodsDetail")
    public ResponseEntity<?> getGoodsDetail(@RequestParam int goodsIndex) {
        BigInteger GOODSINDEX = BigInteger.valueOf(goodsIndex);
        RemoteFunctionCall<Tuple6<BigInteger, BigInteger, BigInteger, String, Boolean, BigInteger>> getDetails = contract.getItemDetailsByGoodsId(GOODSINDEX);

        try {
            Tuple6<BigInteger, BigInteger, BigInteger, String, Boolean, BigInteger> details = getDetails.send();
            Map<String, Object> response = new HashMap<>();
            response.put("goodsId", details.component1());
            response.put("index", details.component2());
            response.put("price", details.component3());
            response.put("highestBidder", details.component4());
            response.put("soldOut", details.component5());
            response.put("highestBid", details.component6());

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }



}
