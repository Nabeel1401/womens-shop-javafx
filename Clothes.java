package womenshop.model;

public class Clothes extends Product {

    private int size;

    public Clothes(String name, double purchasePrice, double sellPrice, int size) {
        super(name, purchasePrice, sellPrice);
        this.size = size;
    }

    public Clothes(int id, String name, double purchasePrice, double sellPrice,
                   double discountPrice, int size, int stock) {

        super(id, name, purchasePrice, sellPrice, discountPrice, stock);
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        if (size < 34 || size > 54)
            throw new IllegalArgumentException("Clothing size must be between 34 and 54");

        this.size = size;
    }
}
