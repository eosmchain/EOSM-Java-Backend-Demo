package service;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import com.fasterxml.jackson.databind.ObjectMapper;
import io.eblock.eos4j.Ecc;
import io.eblock.eos4j.api.service.RpcService;
import io.eblock.eos4j.api.utils.Generator;
import io.eblock.eos4j.api.vo.*;
import io.eblock.eos4j.api.vo.account.Account;
import io.eblock.eos4j.api.vo.action.Actions;
import io.eblock.eos4j.api.vo.transaction.Transaction;
import io.eblock.eos4j.api.vo.transaction.push.Tx;
import io.eblock.eos4j.api.vo.transaction.push.TxAction;
import io.eblock.eos4j.api.vo.transaction.push.TxRequest;
import io.eblock.eos4j.api.vo.transaction.push.TxSign;
import io.eblock.eos4j.ese.Action;
import io.eblock.eos4j.ese.DataParam;
import io.eblock.eos4j.ese.DataType;
import io.eblock.eos4j.ese.Ese;
import io.eblock.eos4j.utils.ByteUtils;
import io.eblock.eos4j.utils.Hex;

import java.text.SimpleDateFormat;
import java.util.*;

public class EosRpcMyService {
    private final RpcService rpcService;
    SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    public EosRpcMyService(String baseUrl) {
        this.dateFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        this.rpcService = (RpcService) Generator.createService(RpcService.class, baseUrl);
    }


    public ChainInfo getChainInfo() {
        return (ChainInfo) Generator.executeSync(this.rpcService.getChainInfo());
    }

    public Block getBlock(String blockNumberOrId) {
        return (Block) Generator.executeSync(this.rpcService.getBlock(Collections.singletonMap("block_num_or_id", blockNumberOrId)));
    }

    public Transaction pushTransaction(String compression, Tx pushTransaction, String[] signatures) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String mapJakcson = mapper.writeValueAsString(new TxRequest(compression, pushTransaction, signatures));
        System.out.println(mapJakcson);
        return (Transaction) Generator.executeSync(this.rpcService.pushTransaction(new TxRequest(compression, pushTransaction, signatures)));
    }

    public Transaction pushAction(String pk, String contractAccount, String actor, Map<String, Object> paramMap,String actionName) throws Exception {
        ChainInfo info = this.getChainInfo();
        Block block = this.getBlock(info.getLastIrreversibleBlockNum().toString());
        Tx tx = new Tx();
        tx.setExpiration(info.getHeadBlockTime().getTime() / 1000L + 60L);
        tx.setRef_block_num(info.getLastIrreversibleBlockNum());
        tx.setRef_block_prefix(block.getRefBlockPrefix());
        tx.setNet_usage_words(0L);
        tx.setMax_cpu_usage_ms(0L);
        tx.setDelay_sec(0L);
        List<TxAction> actions = new ArrayList();

        TxAction action = new TxAction(actor, contractAccount, actionName, paramMap);
        actions.add(action);
        tx.setActions(actions);
        String sign = Ecc.signTransaction(pk, new TxSign(info.getChainId(), tx));

        //action.setData(data);
        tx.setExpiration(this.dateFormatter.format(new Date(1000L * Long.parseLong(tx.getExpiration().toString()))));

        return this.pushTransaction("none", tx, new String[]{sign});
    }

}
