package food.delivery.work;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;
import java.util.Date;

@Entity
@Table(name="StockDelivery_table")
public class StockDelivery {

     //Distance 삭제 및 Id auto로 변경
    
    private Long orderId;
    private String orderStatus;
    private String userName;
    private String address;
    private String productId;
    private Integer qty;
    private String storeName;
    private Date orderDate;
    private Date confirmDate;
    private String productName;
    private String phoneNo;
    private Long productPrice;
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String customerId;
    private String deliveryStatus;
    private Date deliveryDate;
    private String userId;
    
    private static final String DELIVERY_STARTED = "delivery Started";
    private static final String DELIVERY_CANCELED = "delivery Canceled";
  
    @PostPersist
    public void onPostPersist() throws Exception{

    	Promote promote = new Promote();
        promote.setPhoneNo(this.phoneNo); 
        promote.setUserId(this.userId); 
        promote.setUsername(this.userName); 
        promote.setOrderId(this.orderId); 
        promote.setOrderStatus(this.orderStatus); 
        promote.setProductId(this.productId); 
        System.out.println("\n\npostpersist() : "+this.deliveryStatus +"\n\n");
        // deliveryStatus 따라 로직 분기
        if(DELIVERY_STARTED == this.deliveryStatus){
        	
	        boolean result = (boolean) ProductdeliveryApplication.applicationContext.getBean(food.delivery.work.external.PromoteService.class).publishCoupon(promote);
	
	        if(result){
	        	System.out.println("----------------");
	            System.out.println("Coupon Published");
	            System.out.println("----------------");
		       	DeliveryStarted deliveryStarted = new DeliveryStarted();
		        BeanUtils.copyProperties(this, deliveryStarted);
		        deliveryStarted.publishAfterCommit();
	        }else {
	        	throw new RollbackException("Failed during coupon publish");
	        }
        
        }
  
    }

    @PreRemove
    public void onPreRemove(){
        DeliveryCanceled deliveryCanceled = new DeliveryCanceled();
        BeanUtils.copyProperties(this, deliveryCanceled);
        deliveryCanceled.publishAfterCommit();
    }

    @PostUpdate
    public void onPostUpdate(){
       // DeliveryCompleted deliveryCompleted = new DeliveryCompleted();
       // BeanUtils.copyProperties(this, deliveryCompleted);
       // deliveryCompleted.publishAfterCommit();
    	Promote promote = new Promote();
        Message message = new Message();

        promote.setPhoneNo(this.phoneNo); 
        promote.setUserId(this.userId); 
        promote.setUsername(this.userName); 
        promote.setOrderId(this.orderId); 
        promote.setOrderStatus(this.orderStatus); 
        promote.setProductId(this.productId); 

        message.setPhoneNo(this.phoneNo); 
        message.setUsername(this.userName); 
        message.setOrderId(this.orderId); 
        message.setProductName(this.productName); 
        message.setProductId(this.productId); 

    	if(DELIVERY_CANCELED == this.deliveryStatus) {
        
	        boolean result = (boolean) ProductdeliveryApplication.applicationContext.getBean(food.delivery.work.external.PromoteService.class).cancelCoupon(promote);
	    	boolean result2 = (boolean) ProductdeliveryApplication.applicationContext.getBean(food.delivery.work.external.MessageService.class).cancelMessage(message);

	        if(result && result2){
	        	System.out.println("----------------");
	            System.out.println("Coupon Canceled && Message Canceled");
	            System.out.println("----------------");
		       	DeliveryCanceled deliveryCanceled = new DeliveryCanceled();
		        BeanUtils.copyProperties(this, deliveryCanceled);
		        deliveryCanceled.publishAfterCommit();
	        }else {
	        	throw new RollbackException("Failed during coupon cancel or message cancel");
	        }
	        
        }
    }
 
  
   

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderid) {
        this.orderId = orderid;
    }
    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }
    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
    public Date getConfirmDate() {
        return confirmDate;
    }

    public void setConfirmDate(Date confirmDate) {
        this.confirmDate = confirmDate;
    }
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
    public Long getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Long productPrice) {
        this.productPrice = productPrice;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }
    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
 




}