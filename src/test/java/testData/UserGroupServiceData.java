package testData;

import java.util.HashMap;

public class UserGroupServiceData {
    public static HashMap<String, Object> requestBody;
    public  HashMap<String, Object> setReqBody(String name){
        requestBody = new HashMap<>();
        requestBody.put("name", name);


        return requestBody;
    }
    public HashMap<String, Object> setUpForPutReq (Integer id,String updateName,Integer organization_id,Integer group_type_id) {


        requestBody = new HashMap<>();
        requestBody.put("id", id);
        requestBody.put("name", updateName);
        requestBody.put("group_type_id", group_type_id);
        requestBody.put("organization_id", organization_id);



        return requestBody;
    }
    public  HashMap<String, Object> setUpAddUser(String id,String user_group_id,String user_id) {
        requestBody =new HashMap<>();
        requestBody.put("id", 92);
        requestBody.put("user_group_id",111);
        requestBody.put("user_id",343);

        return requestBody;
    }
}
