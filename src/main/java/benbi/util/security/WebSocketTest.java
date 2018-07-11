package benbi.util.security;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.channels.NotYetConnectedException;

import org.java_websocket.WebSocket.READYSTATE;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;

public class WebSocketTest {

    public static WebSocketClient client;

    public static void main(String[] args) throws URISyntaxException, NotYetConnectedException, UnsupportedEncodingException {
        client = new WebSocketClient(new URI("ws://18.218.202.62:6006"),new Draft_6455()) {

            @Override
            public void onOpen(ServerHandshake arg0) {
                System.out.println("open");
            }

            @Override
            public void onMessage(String arg0) {
                System.out.println("receive msg "+arg0);
            }

            @Override
            public void onError(Exception arg0) {
                arg0.printStackTrace();
                System.out.println("error");
            }

            @Override
            public void onClose(int arg0, String arg1, boolean arg2) {
                System.out.println("close");
            }

            @Override
            public void onMessage(ByteBuffer bytes) {
                try {
                    System.out.println(new String(bytes.array(),"utf-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }


        };

        client.connect();

        while(!client.getReadyState().equals(READYSTATE.OPEN)){
            System.out.println("fail to open");
        }
        System.out.println("open successfully");
        send("hello world".getBytes("utf-8"));
        client.send("hello world");
    }

    public static void send(byte[] bytes){
        client.send(bytes);
    }
}
