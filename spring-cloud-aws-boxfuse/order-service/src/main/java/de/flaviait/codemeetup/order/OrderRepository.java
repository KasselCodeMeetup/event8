package de.flaviait.codemeetup.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//@RepositoryRestResource(path = "orders")
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
