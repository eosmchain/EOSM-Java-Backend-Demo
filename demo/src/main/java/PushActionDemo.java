import io.eblock.eos4j.api.exception.ApiException;
import io.eblock.eos4j.api.exception.ErrorDetails;
import netscape.javascript.JSObject;
// import org.springframework.web.client.RestTemplate;
import service.EosRpcMyService;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author hy
 */
public class PushActionDemo {
    public static void main(String[] args) {

        String url = "http://sh-test.vm.mgps.me:8888";
        String pk = "5Jy1B6Ejidkc5E7WLcGNMzBvLPx3X7NbszypVnpdddMYVdAwgc2";
        String contractAccount = "mgp.bpvoting";
        String actor = "mgp.bpvoting";
        Map<String, Object> paramMap = new LinkedHashMap();
        String actionName = "execute";
        EosRpcMyService eosRpcMyService = new EosRpcMyService(url);

        try {
            eosRpcMyService.pushAction(pk, contractAccount, actor, paramMap, actionName);


        } catch (ApiException apiException) {
            apiException.printStackTrace();
            System.err.println(apiException.getError().getError().getWhat());
            ErrorDetails[] details = apiException.getError().getError().getDetails();
            for (ErrorDetails detail : details) {
                System.err.println(detail.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
