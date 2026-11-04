package womenshop.model;

public class Accessories extends Product {

    public Accessories(String name, double purchasePrice, double sellPrice) {
        super(name, purchasePrice, sellPrice);
    }

    public Accessories(int id, String name, double purchasePrice, double sellPrice,
                       double discountPrice, int stock) {

        super(id, name, purchasePrice, sellPrice, discountPrice, stock);
    }
}
