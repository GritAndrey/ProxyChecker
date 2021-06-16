package ProxyChecker;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import static ProxyChecker.Main.RESULTPATH;

public class Writer extends Thread{
    String ip;
    int port;
    String ipPort;



    public Writer(String ip, int port) {
        super();
        this.ip = ip;
        this.port = port;
        this.ipPort=ip + ":" + port;
    }
    @Override
    public void run() {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(RESULTPATH), StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            writer.write(ipPort);
            writer.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
