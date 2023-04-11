package testData;

import java.util.HashMap;
import java.util.Map;
import static apiTest.MembershipTypeService.id;

public class MembershipTypeServiceData {


    public Map<Object,Object> reqTestDataBody01(){

        HashMap<Object,Object> reqBodyData = new HashMap<>();

        reqBodyData.put("name","STAR");
        reqBodyData.put("is_enabled",true);
        reqBodyData.put("app_id",2);

        return reqBodyData;

    }


    public Map<Object,Object> reqTestDataBody02(){

        HashMap<Object,Object> reqBodyData = new HashMap<>();

        reqBodyData.put("name","STAR");
        reqBodyData.put("is_enabled",true);
        reqBodyData.put("app_id",2);
        reqBodyData.put("id",id);

        return reqBodyData;

    }








}
