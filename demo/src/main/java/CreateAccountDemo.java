import io.eblock.eos4j.EosRpcService;
import io.eblock.eos4j.api.exception.ApiException;
import io.eblock.eos4j.api.vo.transaction.Transaction;

/**
 * @author hy
 */
public class CreateAccountDemo {

    public static void main(String[] args) {

        // MGP is the core asset/symbol for MangoChain

        String newAccount = "mgpstestdemo"; //The name of the new account: [a-z,1-5] max 12 characters
        String newAccountOwnerPubKey = "EOS6xt9rXurXqp7TTx4FkPff9A28BoZXbAS6aU2Rk1cEjJNx6icqr";
        String newAccountActivePubKey = newAccountOwnerPubKey; //can choose a different one. using the same one is for simplicity.

        String creator = "mgp.heyingss";    //The name of the account creating the new account
        String creatorPrivKey = "5Jy1B6Ejidkc5E7WLcGNMzBvLPx3X7NbszypVnpdddMYVdAwgc2"; //Creator's private key to sign the transaction
        
        String mgpTestnetRpcUrl = "http://hk-t2.nchain.me:8888";
        EosRpcService eosRpcService = new EosRpcService( mgpTestnetRpcUrl );

        long buyRamBytes = 4300L;
        String stakeNetAsset = "0.0100 MGP"; //The amount of tokens delegated for net bandwidth, MGP coin precision is 4
        String stakeCpuAsset = "0.0100 MGP"; //The amount of tokens delegated for CPU bandwidth, MGP coin precision is 4
        long transfer = 0L; // whether or not to transfer the delegated assets to the new account

        try {
            Transaction account = eosRpcService.createAccount(privKey, creator, 
                newAccount, newAccountOwnerPubKey, newAccountActivePubKey, 
                buyRamBytes, stakeNetAsset, stakeCpuAsset, transfer);

        } catch (ApiException e) {
            e.printStackTrace();
            //System.err.println(e.getError().getError().getWhat());
            System.out.println(JSON.toJSONString(e.getError().getError()));

        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
