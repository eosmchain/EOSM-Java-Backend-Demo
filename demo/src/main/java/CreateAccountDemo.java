import io.eblock.eos4j.EosRpcService;
import io.eblock.eos4j.api.exception.ApiException;
import io.eblock.eos4j.api.vo.transaction.Transaction;

/**
 * @author hy
 */
public class CreateAccountDemo {

    public static void main(String[] args) {

        EosRpcService eosRpcService = new EosRpcService("http://sh-test.vm.mgps.me:8888");

        String pk = "5Jg9EGiVX7b8HSNyPGHVDTxKDwe4VusZhu4GDNZEmxJsovDKiFe";
        String creator = "mgpheying111";
        String newAccount = "mgp.testdemo";
        String publicKey = "EOS6xt9rXurXqp7TTx4FkPff9A28BoZXbAS6aU2Rk1cEjJNx6icqr";

        try {

            Transaction account = eosRpcService.createAccount(pk, creator, newAccount, publicKey, publicKey, 4300L, "0.0004 MGP", "0.0004 MGP", 0L);

        } catch (ApiException e) {
            System.err.println(e.getError().getError().getWhat());
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
