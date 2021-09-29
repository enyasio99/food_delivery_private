package food.delivery.work;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.Optional;

 @RestController
 public class MessageController {
	 
	 @Autowired
	 MessageRepository messageRepository;

     @PostMapping(value = "/createMessageInfo")
     public boolean createMessageInfo(@RequestBody Map<String, String> param) {

        boolean result = false;
        
        Message message = new Message();
        message.setPhoneNo(param.get("phoneNo")); 
        message.setUserName(param.get("userName")); 
        message.setOrderId(Long.parseLong(param.get("orderId"))); 
        message.setProductId(param.get("productId"));
        message.setProductName(param.get("productName"));
        messageRepository.save(message);
        try {
            message = messageRepository.save(message);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
     
     @PostMapping(value = "/cancelMessage")
     public boolean cancelMessage(@RequestBody Map<String, String> param) {

        boolean result = false;
        //System.out.println("\n\n@@MessageController orderID->> " + param.get("orderId"));

        List<Message> messageList = messageRepository.findByOrderId(Long.parseLong(param.get("orderId")));
        
        try {
            //System.out.println("\n\n@@try");
	        for (Message message:messageList)
	        {
                //System.out.println("\n\n@@for -> " + messageList.size());
	        	message.setMessageStatus("Message Canceled");
                
	            messageRepository.save(message);
                //System.out.println("\n\n@@save" + messageList.size());
	            result = true;
	        }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return result;

    }
	 
 }