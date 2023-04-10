package testData;

import java.util.HashMap;
import java.util.Map;
import static apiTest.UserStatusService.id;

public class UserStatusServiceData {

    public Map<Object,Object> reqTestDataBody01(){

        HashMap<Object,Object> reqBodyData = new HashMap<>();

        reqBodyData.put("name","turgut");
        reqBodyData.put("description","user active");

        return reqBodyData;

    }


    public Map<Object,Object> reqTestDataBody02(){

        HashMap<Object,Object> reqBodyData = new HashMap<>();

        reqBodyData.put("id",id);
        reqBodyData.put("name","turgut");
        reqBodyData.put("description","User account is activ");
        return reqBodyData;

    }

}
