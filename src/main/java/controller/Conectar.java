package controller;

import java.io.ByteArrayOutputStream;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class Conectar {
	public void ssh(String username, String password, String host, int port, String comandos) throws Exception {
		Session session = null;
		ChannelExec channel = null;
		
		try {
			session = new JSch().getSession(username, host, port);
			session.setPassword(password);
			session.setConfig("StrictHostKeyChecking", "no");
			session.connect();
			
			channel = (ChannelExec) session.openChannel("exec");
			channel.setCommand(comandos);
			ByteArrayOutputStream output_stream = new ByteArrayOutputStream();
			channel.setOutputStream(output_stream);
			channel.connect();
			
			while (channel.isConnected()) {
				Thread.sleep(100);
			}
			
			String output = new String(output_stream.toByteArray());
			
			System.out.println("Terminal - " + host);
			System.out.println(output);
		}finally {
			if(session != null) {
				session.disconnect();
			}
			if(channel != null) {
				channel.disconnect();
			}
		}
	}
}
