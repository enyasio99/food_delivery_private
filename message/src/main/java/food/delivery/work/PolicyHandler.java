package food.delivery.work;

import food.delivery.work.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PolicyHandler{
    @Autowired MessageRepository MessageRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverDeliveryStarted_SendMessage(@Payload DeliveryStarted deliveryStarted){

        if(!deliveryStarted.validate()) return;

        // delivery 객체 생성 //
         Message message = new Message();

         message.setOrderId(deliveryStarted.getOrderId());
         message.setUserName(deliveryStarted.getUserName());
         message.setPhoneNo(deliveryStarted.getPhoneNo());
         message.setProductId(deliveryStarted.getProductId());
         message.setProductName(deliveryStarted.getProductName()); 
         message.setMessageStatus("Message Sended");

         System.out.println("==================================");
         System.out.println(deliveryStarted.getId());
         System.out.println(deliveryStarted.toJson());
         System.out.println("==================================");
         System.out.println(deliveryStarted.getOrderId());

         MessageRepository.save(message);

    }


}
