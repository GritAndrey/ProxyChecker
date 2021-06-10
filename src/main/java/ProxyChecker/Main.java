package ProxyChecker;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Main {
    static final String PATH = "ip";
    static final String RESULTPATH = "good_ip";

    public static void main(String[] args) {
        try {
            FileInputStream fis = new FileInputStream(PATH);
            int i;
            String proxy = "";
            while ((i = fis.read()) != -1) {
                if (i == 13) continue;
                else if (i == 10) {
                    String ip = proxy.split(":")[0];
                    String port = proxy.split(":")[1];
                    checkProxy(ip, Integer.parseInt(port));
                    proxy = "";
                } else if (i != 9) {
                    proxy += (char) i;
                } else {
                    proxy += ":";
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void checkProxy(String ip, int port) {
        try {
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(ip, port));
            URL url = new URL("https://vozhzhaev.ru/test.php");
            URLConnection urlConnection = url.openConnection(proxy);
            InputStream is = urlConnection.getInputStream();
            InputStreamReader reader = new InputStreamReader(is);
            int i;
            StringBuilder result = new StringBuilder();
            while ((i = reader.read()) != -1) {
                result.append((char) i);
            }
            System.out.println(result);
            saveResult(result.toString() + ":" + port);
        } catch (IOException exception) {
            System.out.println(ip + " - down!");
        }
    }

    private static void saveResult(String ipPort) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(RESULTPATH), StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            writer.write(ipPort);
            writer.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
