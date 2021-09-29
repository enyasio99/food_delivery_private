package food.delivery.work.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import food.delivery.work.Message;

import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="message", url = "${api.message.url}", fallback = MessageServiceFallback.class)
public interface MessageService {
  
    @RequestMapping(method=RequestMethod.POST, path="/createMessageInfo")
    public boolean publishCoupon(@RequestBody Message promote);
    
    @RequestMapping(method=RequestMethod.POST, path="/cancelMessage")
    public boolean cancelMessage(@RequestBody Message message);
}
