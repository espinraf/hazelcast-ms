package se.voipbusiness;

import org.testng.Assert;
import org.testng.annotations.Test;
import se.voipbusiness.batch.Order;

public class test1 {

    @Test
    public void orderString() {
        Order o = new Order();

        o.setOrderID("O-001");
        o.setOrderName("Jacket");

        String os = o.toString();
        System.out.println(os);
        Assert.assertNotNull(os);
    }
}
