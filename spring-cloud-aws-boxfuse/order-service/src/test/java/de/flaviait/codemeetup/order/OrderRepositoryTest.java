package de.flaviait.codemeetup.order;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles(profiles = "boxfuse")
@SpringApplicationConfiguration(classes = {DaoTestConfig.class})
public class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void testName() {
        // given
        OrderEntity clubMate = new OrderEntity("waldemar", DrinkType.CLUB_MATE);

        // when
        OrderEntity entity = orderRepository.save(clubMate);

        // then
        System.out.println(entity.getName() + ", " + entity.getDrink().name());
    }
}
