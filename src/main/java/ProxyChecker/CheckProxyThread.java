package ProxyChecker;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;

public class CheckProxyThread extends Thread {
        String ip;
        int port;
        public CheckProxyThread(String ip, int port) {
            super();
            this.ip = ip;
            this.port = port;
        }

        @Override
        public void run(){
            try {
                Proxy proxy = new Proxy(Proxy.Type.HTTP,new InetSocketAddress(ip,port));
                URL url = new URL("https://vozhzhaev.ru/test.php");
                URLConnection urlConnection = url.openConnection(proxy);
                InputStream is = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(is);
                int i;
                StringBuilder result = new StringBuilder();
                while ((i=reader.read()) != -1){
                    result.append((char)i);
                }
                System.out.println(result);
                Writer writer = new Writer(result.toString(),port);
                writer.start();
            } catch (IOException exception) {
                System.out.println(ip+" - не работает!");
            }
        }

    }

