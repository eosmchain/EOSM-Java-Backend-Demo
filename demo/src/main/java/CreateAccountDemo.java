import io.eblock.eos4j.EosRpcService;
import io.eblock.eos4j.api.exception.ApiException;
import io.eblock.eos4j.api.vo.transaction.Transaction;

/**
 * @author hy
 */
public class CreateAccountDemo {

    public static void main(String[] args) {

        EosRpcService eosRpcService = new EosRpcService("http://sh-test.vm.mgps.me:8888");

        
        
        String ownerPubKey = "EOS6xt9rXurXqp7TTx4FkPff9A28BoZXbAS6aU2Rk1cEjJNx6icqr";
        String activePubKey = ownerPubKey; //can choose a different one. using the same one is for simplicity.
        String privKey = "5Jg9EGiVX7b8HSNyPGHVDTxKDwe4VusZhu4GDNZEmxJsovDKiFe";
    
        String creator = "mgpheying111";
        String newAccount = "mgp.testdemo";
 
        try {
            Transaction account = eosRpcService.createAccount(privKey, creator, newAccount, ownerPubKey, activePubKey, 4300L, "0.0004 MGP", "0.0004 MGP", 0L); 
        } catch (ApiException e) {
            System.err.println(e.getError().getError().getWhat());
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
