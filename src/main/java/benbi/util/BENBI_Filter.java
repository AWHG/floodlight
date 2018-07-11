package benbi.util;

import benbi.util.security.SEAlgorithm;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.routing.Filter;

import java.io.IOException;

/**
 * Created by jessy on 2017/12/2.
 */
public class BENBI_Filter extends Filter {

    @Override
    protected int beforeHandle(Request request, Response response) {


        Representation representation = request.getEntity();
        try {
            //TODO
            byte[] key = "12345".getBytes();
            byte[] IV = {100, -85, -55, 85, -72, 17, 16, -70, 64, 101, -128, -25, 76, 74, -96, 67};

            String jsonStr = representation.getText();
            //{"switch":"00:00:00:00:00:00:00:01", "name":"flow-mod-1",
            // "priority":"32768", "ingress-port":"1","active":"true",
            // "actions":"output=2"}"
            System.out.println("stream - "+ jsonStr);
            String[] keyValues = jsonStr.substring(1, jsonStr.length()-1).split(",");
            String[] key_value = null;
            String value = null;

            StringBuilder sb = new StringBuilder();
            sb.append("{");
            for(int i=0; i<keyValues.length; i++){
                key_value = keyValues[i].split(":");
                System.out.println(key_value[0] + " : " + key_value[1]);
                value = key_value[1].substring(1, key_value[1].length()-1);
                System.out.println("value -- " + value);
                String dec_value = SEAlgorithm.streamDec(value, key, IV);
                System.out.println("decrypted value -- " + dec_value);

                sb.append(key_value[0]);
                sb.append(":");
                sb.append("\"");
                sb.append(dec_value);
                sb.append("\"");
                if(i<keyValues.length-1)
                    sb.append(",");
            }
            sb.append("}");
            System.out.println("result -- " + sb);
            MediaType type = new MediaType("application/json");
            request.setEntity(String.valueOf(sb), type);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return super.beforeHandle(request, response);

    }
    @Override
    protected void afterHandle(Request request, Response response) {
        super.afterHandle(request, response);
    }
}