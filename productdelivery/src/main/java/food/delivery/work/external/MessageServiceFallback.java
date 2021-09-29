package food.delivery.work.external;

import org.springframework.stereotype.Component;

import food.delivery.work.Message;

@Component
public class MessageServiceFallback implements MessageService {
    @Override
    public boolean publishCoupon(Message message) {
        //do nothing if you want to forgive it

        System.out.println("Circuit breaker has been opened. Fallback returned instead.");
        return false;
    }
    
    @Override
    public boolean cancelMessage(Message message) {
        //do nothing if you want to forgive it

        System.out.println("Circuit breaker has been opened. Fallback returned instead.");
        return false;
    }
}
