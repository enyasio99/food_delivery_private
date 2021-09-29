package food.delivery.work;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;
import java.util.Date;

@Entity
@Table(name="Message_table")
public class Message {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	private Long orderId;
	private String productId;
	private String productName;
    private String userName;
    private String messageStatus;
    private String phoneNo;


    @PrePersist
    public void onPrePersist(){

        try {
            Thread.currentThread().sleep((long) (400 + Math.random() * 220));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        
    }

    @PostPersist
    public void onPostPersist(){
        CouponPublished couponPublished = new CouponPublished();
        BeanUtils.copyProperties(this, couponPublished);
        couponPublished.publishAfterCommit();
        System.out.println("\n\n promote onPostPersist() \n\n");
        
    }
    
    @PostUpdate
    public void onPostUpdate(){
        MessageCanceled messageCanceled = new MessageCanceled();
        BeanUtils.copyProperties(this, messageCanceled);
        messageCanceled.publishAfterCommit();
        System.out.println("\n\n message onPostUpdate() \n\n");
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

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMessageStatus() {
		return messageStatus;
	}

	public void setMessageStatus(String messageStatus) {
		this.messageStatus = messageStatus;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
}