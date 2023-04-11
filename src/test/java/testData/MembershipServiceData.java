package testData;

import java.util.HashMap;
import java.util.Map;




public class MembershipServiceData {

    public Map<Object,Object> reqTestDataBody01(int app_id, int user_id,int membership_type_id,
                                                int subscription_type_id,boolean is_individual_membership ,boolean is_default){



        HashMap<Object,Object> reqBodyData = new HashMap<>();

        reqBodyData.put("app_id",app_id);
        reqBodyData.put("user_id",user_id);
        reqBodyData.put("membership_type_id",membership_type_id);
        reqBodyData.put("subscription_type_id",subscription_type_id);
        reqBodyData.put("is_individual_membership",is_individual_membership);
        reqBodyData.put("is_default",is_default);

        return reqBodyData;

    }



}
