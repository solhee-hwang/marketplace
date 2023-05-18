package marketplace.dapp.webserver.solidity;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.StaticStruct;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple6;
import org.web3j.tuples.generated.Tuple8;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.4.2.
 */
@SuppressWarnings("rawtypes")
public class
MarketplaceAbi extends Contract {
    public static final String BINARY = "Bin file was not provided";

    public static final String FUNC_CREATEITEM = "createItem";

    public static final String FUNC_PLACEBID = "placeBid";

    public static final String FUNC_SOLDOUT = "soldout";

    public static final String FUNC_GETITEMDETAILS = "getItemDetails";

    public static final String FUNC_GETITEMDETAILSBYGOODSID = "getItemDetailsByGoodsId";

    public static final String FUNC_GETITEMS = "getItems";

    public static final String FUNC_ITEMCOUNT = "itemCount";

    public static final String FUNC_ITEMS = "items";

    public static final String FUNC_OWNER = "owner";

    public static final Event BIDPLACED_EVENT = new Event("BidPlaced", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event ITEMCREATED_EVENT = new Event("ItemCreated", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Address>() {}, new TypeReference<Bool>() {}));
    ;

    @Deprecated
    protected MarketplaceAbi(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected MarketplaceAbi(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected MarketplaceAbi(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected MarketplaceAbi(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<BidPlacedEventResponse> getBidPlacedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(BIDPLACED_EVENT, transactionReceipt);
        ArrayList<BidPlacedEventResponse> responses = new ArrayList<BidPlacedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            BidPlacedEventResponse typedResponse = new BidPlacedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.index = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.price = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.bidder = (String) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.bid = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<BidPlacedEventResponse> bidPlacedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, BidPlacedEventResponse>() {
            @Override
            public BidPlacedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(BIDPLACED_EVENT, log);
                BidPlacedEventResponse typedResponse = new BidPlacedEventResponse();
                typedResponse.log = log;
                typedResponse.index = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.price = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.bidder = (String) eventValues.getNonIndexedValues().get(2).getValue();
                typedResponse.bid = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<BidPlacedEventResponse> bidPlacedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(BIDPLACED_EVENT));
        return bidPlacedEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> createItem(BigInteger _goodsId, BigInteger _price) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_CREATEITEM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_goodsId), 
                new org.web3j.abi.datatypes.generated.Uint256(_price)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public static List<ItemCreatedEventResponse> getItemCreatedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(ITEMCREATED_EVENT, transactionReceipt);
        ArrayList<ItemCreatedEventResponse> responses = new ArrayList<ItemCreatedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ItemCreatedEventResponse typedResponse = new ItemCreatedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.goodsId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.index = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.price = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.seller = (String) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse.soldOut = (Boolean) eventValues.getNonIndexedValues().get(4).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ItemCreatedEventResponse> itemCreatedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ItemCreatedEventResponse>() {
            @Override
            public ItemCreatedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(ITEMCREATED_EVENT, log);
                ItemCreatedEventResponse typedResponse = new ItemCreatedEventResponse();
                typedResponse.log = log;
                typedResponse.goodsId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.index = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.price = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
                typedResponse.seller = (String) eventValues.getNonIndexedValues().get(3).getValue();
                typedResponse.soldOut = (Boolean) eventValues.getNonIndexedValues().get(4).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ItemCreatedEventResponse> itemCreatedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ITEMCREATED_EVENT));
        return itemCreatedEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> placeBid(BigInteger _index, BigInteger weiValue) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_PLACEBID, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_index)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<TransactionReceipt> soldout(BigInteger _index) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SOLDOUT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_index)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Tuple6<BigInteger, BigInteger, BigInteger, String, Boolean, BigInteger>> getItemDetails(BigInteger _index) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETITEMDETAILS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_index)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Address>() {}, new TypeReference<Bool>() {}, new TypeReference<Uint256>() {}));
        return new RemoteFunctionCall<Tuple6<BigInteger, BigInteger, BigInteger, String, Boolean, BigInteger>>(function,
                new Callable<Tuple6<BigInteger, BigInteger, BigInteger, String, Boolean, BigInteger>>() {
                    @Override
                    public Tuple6<BigInteger, BigInteger, BigInteger, String, Boolean, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple6<BigInteger, BigInteger, BigInteger, String, Boolean, BigInteger>(
                                (BigInteger) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue(), 
                                (String) results.get(3).getValue(), 
                                (Boolean) results.get(4).getValue(), 
                                (BigInteger) results.get(5).getValue());
                    }
                });
    }

    public RemoteFunctionCall<Tuple6<BigInteger, BigInteger, BigInteger, String, Boolean, BigInteger>> getItemDetailsByGoodsId(BigInteger _goodsId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETITEMDETAILSBYGOODSID, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_goodsId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Address>() {}, new TypeReference<Bool>() {}, new TypeReference<Uint256>() {}));
        return new RemoteFunctionCall<Tuple6<BigInteger, BigInteger, BigInteger, String, Boolean, BigInteger>>(function,
                new Callable<Tuple6<BigInteger, BigInteger, BigInteger, String, Boolean, BigInteger>>() {
                    @Override
                    public Tuple6<BigInteger, BigInteger, BigInteger, String, Boolean, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple6<BigInteger, BigInteger, BigInteger, String, Boolean, BigInteger>(
                                (BigInteger) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue(), 
                                (String) results.get(3).getValue(), 
                                (Boolean) results.get(4).getValue(), 
                                (BigInteger) results.get(5).getValue());
                    }
                });
    }

    public RemoteFunctionCall<List> getItems() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETITEMS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Item>>() {}));
        return new RemoteFunctionCall<List>(function,
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteFunctionCall<BigInteger> itemCount() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ITEMCOUNT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<Tuple8<BigInteger, BigInteger, BigInteger, String, Boolean, String, String, BigInteger>> items(BigInteger param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ITEMS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Address>() {}, new TypeReference<Bool>() {}, new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        return new RemoteFunctionCall<Tuple8<BigInteger, BigInteger, BigInteger, String, Boolean, String, String, BigInteger>>(function,
                new Callable<Tuple8<BigInteger, BigInteger, BigInteger, String, Boolean, String, String, BigInteger>>() {
                    @Override
                    public Tuple8<BigInteger, BigInteger, BigInteger, String, Boolean, String, String, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple8<BigInteger, BigInteger, BigInteger, String, Boolean, String, String, BigInteger>(
                                (BigInteger) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue(), 
                                (String) results.get(3).getValue(), 
                                (Boolean) results.get(4).getValue(), 
                                (String) results.get(5).getValue(), 
                                (String) results.get(6).getValue(), 
                                (BigInteger) results.get(7).getValue());
                    }
                });
    }

    public RemoteFunctionCall<String> owner() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_OWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    @Deprecated
    public static MarketplaceAbi load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new MarketplaceAbi(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static MarketplaceAbi load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new MarketplaceAbi(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static MarketplaceAbi load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new MarketplaceAbi(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static MarketplaceAbi load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new MarketplaceAbi(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static class Item extends StaticStruct {
        public BigInteger goodsId;

        public BigInteger index;

        public BigInteger price;

        public String seller;

        public Boolean soldOut;

        public String buyer;

        public String highestBidder;

        public BigInteger highestBid;

        public Item(BigInteger goodsId, BigInteger index, BigInteger price, String seller, Boolean soldOut, String buyer, String highestBidder, BigInteger highestBid) {
            super(new org.web3j.abi.datatypes.generated.Uint256(goodsId), 
                    new org.web3j.abi.datatypes.generated.Uint256(index), 
                    new org.web3j.abi.datatypes.generated.Uint256(price), 
                    new org.web3j.abi.datatypes.Address(160, seller), 
                    new org.web3j.abi.datatypes.Bool(soldOut), 
                    new org.web3j.abi.datatypes.Address(160, buyer), 
                    new org.web3j.abi.datatypes.Address(160, highestBidder), 
                    new org.web3j.abi.datatypes.generated.Uint256(highestBid));
            this.goodsId = goodsId;
            this.index = index;
            this.price = price;
            this.seller = seller;
            this.soldOut = soldOut;
            this.buyer = buyer;
            this.highestBidder = highestBidder;
            this.highestBid = highestBid;
        }

        public Item(Uint256 goodsId, Uint256 index, Uint256 price, Address seller, Bool soldOut, Address buyer, Address highestBidder, Uint256 highestBid) {
            super(goodsId, index, price, seller, soldOut, buyer, highestBidder, highestBid);
            this.goodsId = goodsId.getValue();
            this.index = index.getValue();
            this.price = price.getValue();
            this.seller = seller.getValue();
            this.soldOut = soldOut.getValue();
            this.buyer = buyer.getValue();
            this.highestBidder = highestBidder.getValue();
            this.highestBid = highestBid.getValue();
        }
    }

    public static class BidPlacedEventResponse extends BaseEventResponse {
        public BigInteger index;

        public BigInteger price;

        public String bidder;

        public BigInteger bid;
    }

    public static class ItemCreatedEventResponse extends BaseEventResponse {
        public BigInteger goodsId;

        public BigInteger index;

        public BigInteger price;

        public String seller;

        public Boolean soldOut;
    }
}
