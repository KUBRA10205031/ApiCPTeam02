package testData;

import javax.naming.ldap.HasControls;
import java.util.HashMap;
import java.util.Objects;

public class OrganizationServiceTestData {
    public HashMap<String, Object> requestBody;
    public HashMap<String, Object> setReqBody (String name, int founderId){
        requestBody = new HashMap<>();
        requestBody.put("name", name);
        requestBody.put("founder_id", founderId );

        return requestBody;
    }


}
