package messenger.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.sun.security.ntlm.Client;

public class MServer {

	ExecutorService executorService;
	ServerSocket serVerSocket;
	List<Client> connections = new Vector<Client>();
	final String IP = "localhost";
	final int PORT = 5500;

	void startServer() {
		executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

		try {
			serVerSocket = new ServerSocket();
			serVerSocket.bind(new InetSocketAddress(IP, PORT));
		} catch (Exception e) {
			if (!serVerSocket.isClosed()) {
				stopServer();
			}
			return;
		}

		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				try {
					Socket socket = serVerSocket.accept();
					String message = "[연결수락 :" + socket.getRemoteSocketAddress() + ": "
							+ Thread.currentThread().getName() + "]";
					System.out.println(message);

					Client client = new Client(socket);
					connections.add(client);
				} catch (IOException e) {
					if (!serVerSocket.isClosed()) {
						stopServer();
					}
					break;
				
			}
		}
	};
		executorService.submit(runnable);

}

	void stopServer() {
		Iterator<Client> iterator = connections.iterator();
		try {
			while(iterator.hasNext()) {
				Client client = iterator.next();
				client.socket.close();
				iterator.remove();
			}
			if(serVerSocket != null && !serVerSocket.isClosed()) {
				serVerSocket.close();
			}
			if(executorService != null && !executorService.isShutdown()) {
				executorService.shutdown();
			}
			System.out.println("[서버 멈춤]");
			catch(IOException e) {
				e.printStackTrace();
			}
		
	}
	
	class Client {
		Socket socket;

		Client(Socket socket) {
			this.socket = socket;
			receive();
		}
			void receive() {
				Runnable runnable = new Runnable() {
					@Override
					public void run() {
						byte[] byteArr = new byte[10];
						try {
							InputStream inputstream = socket.getInputStream();
							int readByteCount = inputstream.read(byteArr);
							if(readByteCount == -1) {throw new IOException();};
							Strin message = "[요청처리 : " + socket.getRemoteSocketAddress() + ": "+
									Thread.currentThread().getName();
							System.out.println(message);
							String data = new String(byteArr,0,readByteCount,"UTF-8");
							for(Client client : connections) {
								client.send();
							} catch(IOException e) {
								connections.remove(Client.this);
								String message = "[클라이언트 통신 안됨 : "+socket.getRemoteSocketAddress()+" : "+
								Thread.currentThread().getName()+"]";
								System.out.println(message);
								}
							}
							}
						}
					}
				}
			}

	void send() {

	}

	public static void main(String[] args) {
		ExecutorService executorService;
		ServerSocket serVerSocket;
		List<Client> connection = new Vector<Client>();
	}
}
