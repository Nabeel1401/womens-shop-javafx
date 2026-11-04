package womenshop.model;

public class Shoes extends Product {

    private int shoeSize;

    public Shoes(String name, double purchasePrice, double sellPrice, int shoeSize) {
        super(name, purchasePrice, sellPrice);
        this.shoeSize = shoeSize;
    }

    public Shoes(int id, String name, double purchasePrice, double sellPrice,
                 double discountPrice, int shoeSize, int stock) {

        super(id, name, purchasePrice, sellPrice, discountPrice, stock);
        this.shoeSize = shoeSize;
    }

    public int getShoeSize() {
        return shoeSize;
    }

    public void setShoeSize(int shoeSize) {

        if (shoeSize < 36 || shoeSize > 50)
            throw new IllegalArgumentException("Shoe size must be between 36 and 50");

        this.shoeSize = shoeSize;
    }
}
