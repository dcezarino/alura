package br.com.alura.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.naming.InitialContext;

public class TesteProdutor {
	
	public static void main(String[] args) throws Exception {
		
		InitialContext context = new InitialContext();		
		ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");
		
		// Estabelecendo uma connection atraves de factory
		Connection connection = factory.createConnection();
		connection.start();
		// Session faz a parte de confirmacao da mensagem de recebimento e também trabalha com transacoes
		// Session.AUTO_ACKNOWLEDGE: confirmar automaticamente o recebimento das mensagens
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
		Destination fila = (Destination) context.lookup("financeiro");
		
		MessageProducer producer = session.createProducer(fila);
		
		for (int i = 0; i < 2000; i++) {
			
			Message message = session.createTextMessage("<pedido><id> " + i + "</id></pedido>");
			producer.send(message);
			
		}
		
		//new Scanner(System.in).nextLine();
		
		session.close();
		connection.close();
		context.close();
		
	}

}
