package br.com.caelum.jms.log;


import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.naming.InitialContext;

public class TesteProdutorFila {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		
		InitialContext context = new InitialContext();
		ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");
		
		Connection connection = factory.createConnection(); 
		connection.start();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
		Destination fila = (Destination) context.lookup("LOG");
		
		MessageProducer producer = session.createProducer(fila);
		
		Message message = session.createTextMessage("WARN | ActiveMQ WebConsole available at http://0.0.0.0:8161/");
		producer.send(message, DeliveryMode.NON_PERSISTENT, 7, 80000);
				
//		for (int i = 0; i < 1000; i++) {
//			
//			Message message = session.createTextMessage("<pedido><id>"  +  i + "</id></pedido>");
//			producer.send(message);
//		}
				
		//new Scanner(System.in).nextLine();
		
		session.close();
		connection.close();
		context.close();
	}
}
