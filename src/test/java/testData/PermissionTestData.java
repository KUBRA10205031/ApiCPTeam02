package testData;
import java.util.HashMap;
import java.util.Map;

import static apiTest.PermissionServise.permissionId;


public class PermissionTestData {
    public Map<Object, Object> reqTestDataBody01() {

    /*
     {

             "resource": "defter",
             "action": "read",
             "app_id": 2
     }
 */

        HashMap<Object, Object> reqBodyData = new HashMap<>();


        reqBodyData.put("resource", "file");
        reqBodyData.put("action", "read");
        reqBodyData.put("app_id", 2);


        return reqBodyData;

    }
    public Map<Object, Object> reqTestDataBody02() {

        HashMap<Object, Object> reqBodyData = new HashMap<>();

        reqBodyData.put("id",permissionId);
        reqBodyData.put("resource","Team007");
        reqBodyData.put("action", "read");
        reqBodyData.put("app_id", 2);



        return reqBodyData;


    }
}
