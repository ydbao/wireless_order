package ecnu.pb.wireless_order.model;

import java.util.List;

public class OrderModel {

    private int id;
    private String data;
    private int people;
    private int status;
    private int total;
    private List<MenuModel> list;

    private int user_id;
    private int order_id;
    private int user_amount;
    private String order_date;

    public OrderModel() {}

    public OrderModel(int id, String data, int people, int status, int total, List<MenuModel> list) {
        this.id = id;
        this.data = data;
        this.people = people;
        this.status = status;
        this.total = total;
        this.list = list;
    }

    public int getOrder_id() {
        return order_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getUser_amount() {
        return user_amount;
    }

    public String getOrder_date() {
        return order_date;
    }

    public int getId() {
        return id;
    }

    public String getData() {
        return data;
    }

    public int getPeople() {
        return people;
    }

    public int getStatus() {
        return status;
    }

    public int getTotal() {
        return total;
    }

    public List<MenuModel> getList() {
        return list;
    }
}
