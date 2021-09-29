package food.delivery.work;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel="messages", path="messages")
public interface MessageRepository extends PagingAndSortingRepository<Message, Long>{

	List<Message> findByOrderId(Long orderId);

}
