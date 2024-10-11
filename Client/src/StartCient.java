import javax.swing.*;

import java.net.Socket;

public class StartCient {
    static String port = "4907";

    public static void main(String[] args) {
        String ip = JOptionPane.showInputDialog("Please enter server ip");
        new StartCient().initialize(ip, Integer.parseInt(port));
    }
public static void initialize(String ip, int port) {
    try {
        if (!isValidIPAddress(ip)) {
            throw new IllegalArgumentException("Invalid IP address");
        }
        Socket sc = new Socket(ip, port);
        System.out.println("Connecting to the server");
        Authentication frame1 = new Authentication(sc);
        frame1.setSize(300, 80);
        frame1.setLocation(500, 300);
        frame1.setVisible(true);
    } catch (Exception e) {
        e.printStackTrace();
    }
}

    private static boolean isValidIPAddress(String ip) {
        return ip.matches("\\b(?:\\d{1,3}\\.){3}\\d{1,3}\\b");
    }

}
