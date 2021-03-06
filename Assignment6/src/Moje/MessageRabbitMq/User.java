package Moje.MessageRabbitMq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.concurrent.*;

public class User {
    public Connector connector;
    public Channel ch;
    public String name;

    public User() throws URISyntaxException, IOException, NoSuchAlgorithmException, KeyManagementException {
        connector = new Connector();
        this.ch = connector.getChannel();

    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @throws IOException
     * @throws InterruptedException
     * @link https://www.rabbitmq.com/tutorials/tutorial-three-java.html
     */
    public String consume() throws IOException, InterruptedException, ExecutionException {
        // when we supply no parameters to queueDeclare() we create a non-durable, exclusive, autodelete queue with a generated name:
        String queueName = ch.queueDeclare().getQueue();

        //From now on the chat exchange will append messages to our generated queue.
        ch.queueBind(queueName, "chat", "");

        /*
         * is deprecated but easier to do.
         * "Convenience class: an implementation of Consumer with straightforward <b>blocking</b> semantics."
         */
        QueueingConsumer consumer = new QueueingConsumer(ch);
        ch.basicConsume(queueName, true, consumer);
        ArrayList<Future<String>> list = new ArrayList<Future<String>>();

        Callable<String> callable = new ReceiverThread(consumer);
        ExecutorService executor = Executors.newFixedThreadPool(10);
        Future<String> fut = executor.submit(callable);
        list.add(fut);
        for (Future<String> fute : list) {
            System.out.println(fute.get());
        }
        executor.shutdown();
        return "nic";
    }

    public void publish(String msg) throws IOException {
        ch.basicPublish("chat", "", null, msg.getBytes());
        System.out.println(msg);
    }
}



