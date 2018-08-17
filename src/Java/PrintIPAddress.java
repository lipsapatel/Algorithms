package Java;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Write java program to print your IP address.
 *
 * Approach:
 *
 * 1) Get the localhost address using getLocalHost() method.
 * 2) Use getHostAddress() method to get the IP address from localhost
 */
public class PrintIPAddress {

    private static void printIPAddress() {

        try {

            //Get the Localhost
            InetAddress localHost = InetAddress.getLocalHost();

            //Get the IP address
            String ipaddress = localHost.getHostAddress();

            System.out.println("IP Address is: " + ipaddress);

        } catch (UnknownHostException uhe) {

            uhe.printStackTrace();
        }
    }

    public static void main(String[] args) {
        printIPAddress();
    }
}
