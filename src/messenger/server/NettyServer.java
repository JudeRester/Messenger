package messenger.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.SelfSignedCertificate;

public class NettyServer {
	private final int port;
	
	public NettyServer(int port) {
		super();
		this.port = port;
	}
	
	public static void main(String[] args) throws Exception {
		new NettyServer(5500).run();
	}
	
	public void run() throws Exception{
		SelfSignedCertificate ssc = new SelfSignedCertificate();
		SslContext sslCtx = SslContextBuilder.forServer(ssc.certificate(), ssc.privateKey()).build();
		
		//SslContext를 사용하면
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		
		try {
			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.group(bossGroup,workerGroup)
			.channel(NioServerSocketChannel.class)
			.handler(new LoggingHandler(LogLevel.INFO))
			.childHandler(new ChatServerInitializer(sslCtx));
			
			bootstrap.bind(port).sync().channel().closeFuture().sync();
			}finally {
				bossGroup.shutdownGracefully();
				workerGroup.shutdownGracefully();
		}
	}
	
	/*public static void main(String[] args) {
		Charset charset = Charset.forName("UTF-8");
//		String [] types = {"UTF-8","EUC-KR","ISO-8859-1"};
//		String testValue = "한글";
		try {
			ServerSocketChannel serverSocketChannel =
					ServerSocketChannel.open();
			serverSocketChannel.socket().bind(new InetSocketAddress(8888));
			serverSocketChannel.configureBlocking(false);
			
			while(true) {
				System.out.println("접속전");
				SocketChannel socketChannel = serverSocketChannel.accept();
				System.out.println("접속후");
				if(socketChannel != null) {
					ByteBuffer byteBuffer = ByteBuffer.wrap("안".getBytes());
					socketChannel.write(byteBuffer);
					socketChannel.close();
				}
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		try {
			ServerSocket serverSocket = new ServerSocket(8888);
			while(true) {
				System.out.println("before accept");
				Socket socket = serverSocket.accept();
				System.out.println("after accept");
				System.out.println("===========");
				try {
					OutputStream outputStream = socket.getOutputStream();
					outputStream.write("Hello, world!".getBytes());
				}catch(Exception e) {
					e.printStackTrace();
				}finally {
					socket.close();
				}
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}*/
}
