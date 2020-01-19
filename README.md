# rabbitMQ
rabbit MQ 五种模式总结
1）简单队列：一个生产者，一个消费者  

![image](https://github.com/connie777/rabbitMQ/blob/master/src/main/resources/imgs/basic1.png)  
        
2）work队列:一个生产者，多个消费者，一个消息只能被一个消费者消费  

![image](https://github.com/connie777/rabbitMQ/blob/master/src/main/resources/imgs/work01.png)  

轮询分发：每个消费者获得相同数量的消息  
公平分发（能者多劳）：联合使用 Qos 和 Acknowledge，basicQos 方法设置当前信道最大预获取（prefetch）消息数量，关闭自动应答，改为手动应答  

3）Publish/Subscribe（发布/订阅）：  
![image](https://github.com/connie777/rabbitMQ/blob/master/src/main/resources/imgs/Publish%26Subscribe.png)  
![image](https://github.com/connie777/rabbitMQ/blob/master/src/main/resources/imgs/FanoutExchange.png)  
一个生产者，多个消费者  
每一个消费者都有自己的一个队列  
生产者没有将消息直接发送到队列，而是发送到了交换机  
每个队列都要绑定到交换机  
生产者发送的消息，经过交换机，达到队列，实现一个消息被多个消费者获取的目的  
一个消费者队列可以有多个消费者实例，只有其中一个消费者实例能消费  
消息发送到没有绑定队列的交换机时，消息将丢失。因为交换机没有存储消息的能力，消息只能存在队列中。  

4）路由模式（direct）  
![image](https://github.com/connie777/rabbitMQ/blob/master/src/main/resources/imgs/DirectExchange.png)  
![image](https://github.com/connie777/rabbitMQ/blob/master/src/main/resources/imgs/DirectExchange02.png)  	
与发布/订阅相似  
生产者发布消息时指定routing key  
队列与交换机绑定时指定routing key  
生产者将消息发送到交换机，交换机以direct模式，根据routing key将消息路由到相应的队列  

5）主题模式（通配符模式，topic）  
![image](https://github.com/connie777/rabbitMQ/blob/master/src/main/resources/imgs/TopicExchange01.png)  
![image](https://github.com/connie777/rabbitMQ/blob/master/src/main/resources/imgs/TopicExchange02.png)  
*表示匹配最多一个词；#表示匹配一个或多个词  

    	
    	
