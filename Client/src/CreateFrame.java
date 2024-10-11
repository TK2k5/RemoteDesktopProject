import javax.sound.midi.Receiver;
import javax.swing.*;
import java.awt.*;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class CreateFrame extends Thread {
    String width = "", height = "";
    private JFrame frame = new JFrame();
    private JDesktopPane desktop = new JDesktopPane();
    private Socket cSocket = null;
    private JInternalFrame internalFrame = new JInternalFrame("Server Screen", true, true, true);
    private JPanel cPanel = new JPanel();

    public CreateFrame(Socket cSocket, String width, String height) {
        this.width = width;
        this.height = height;
        this.cSocket = cSocket;
        start();
    }

    public void drawGUI() {
        frame.add(desktop, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);

        internalFrame.setLayout(new BorderLayout());

        internalFrame.getContentPane().add(cPanel, BorderLayout.CENTER);
        internalFrame.setSize(100, 100);
        desktop.add(internalFrame);

        try {
            internalFrame.setMaximum(true);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }

        cPanel.setFocusable(true);
        internalFrame.setVisible(true);
    }


    public void run() {
        InputStream in = null;
        drawGUI();
        try {
            in = cSocket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        new ReceivingScreen(in, cPanel);
        new sendEvents(cSocket, cPanel, width, height);
    }
}
