package food.delivery.work;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="OrderStatus_table")
public class OrderStatus {

        @Id
        @GeneratedValue(strategy=GenerationType.AUTO)
        private Long id;
        private String username;
        private String userId;
        private Long orderId;
        private String orderStatus;
        private String productId;
        private String productName;
        private Long productPrice;
        private int qty; 
        private String couponId;
        private String couponKind;
        private String couponUseYn;
        
        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
        public String getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(String orderStatus) {
            this.orderStatus = orderStatus;
        }
        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
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

		public Long getProductPrice() {
			return productPrice;
		}

		public void setProductPrice(Long productPrice) {
			this.productPrice = productPrice;
		}

		public int getQty() {
			return qty;
		}

		public void setQty(int qty) {
			this.qty = qty;
		}

		public String getCouponId() {
			return couponId;
		}

		public void setCouponId(String couponId) {
			this.couponId = couponId;
		}

		public String getCouponKind() {
			return couponKind;
		}

		public void setCouponKind(String couponKind) {
			this.couponKind = couponKind;
		}

		public String getCouponUseYn() {
			return couponUseYn;
		}

		public void setCouponUseYn(String couponUseYn) {
			this.couponUseYn = couponUseYn;
		}

		public Long getOrderId() {
			return orderId;
		}

		public void setOrderId(Long orderId) {
			this.orderId = orderId;
		}
        

}
