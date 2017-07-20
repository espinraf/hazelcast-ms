package se.voipbusiness.batch;

public class Order {

    private String orderID;
    private String orderName;

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Order [orderID=");
        builder.append(orderID);
        builder.append(", orderName=");
        builder.append(orderName);
        builder.append("]");
        return builder.toString();
    }

}
