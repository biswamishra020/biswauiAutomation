package com.ea.framework.utilities;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class CommonUtil {

	public static String getIpAddress() throws UnknownHostException {
		InetAddress IP=InetAddress.getLocalHost();
		Socket socket = new Socket();
		String completeIP=IP.getHostAddress()+":"+findFreePort();
		System.out.println("IP of my system is := "+IP.getHostAddress()+":"+socket.getLocalPort());
		return completeIP;
	}

	private static int findFreePort() {
		ServerSocket socket = null;
		try {
			socket = new ServerSocket(0);
			socket.setReuseAddress(true);
			int port = socket.getLocalPort();
			try {
				socket.close();
			} catch (IOException e) {
				// Ignore IOException on close()
			}
			return port;
		} catch (IOException e) {
		} finally {
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
				}
			}
		}
		throw new IllegalStateException("Could not find a free TCP/IP port to start embedded Jetty HTTP Server on");
	}
}
