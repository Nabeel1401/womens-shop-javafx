package womenshop.model;

public abstract class Product implements Discount, Comparable<Product> {

    private static int nextNumber = 1;

    private int number;
    private String name;
    private double purchasePrice;
    private double sellPrice;
    private double discountPrice;
    private int nbItems;

    private static double capital;
    private static double income = 0.0;
    private static double cost = 0.0;

    public Product(String name, double purchasePrice, double sellPrice) {
        this.number = nextNumber++;
        setName(name);
        setPurchasePrice(purchasePrice);
        setSellPrice(sellPrice);
        this.discountPrice = 0.0;
        this.nbItems = 0;
    }

    public Product(int id, String name, double purchasePrice, double sellPrice,
                   double discountPrice, int nbItems) {
        this.number = id;
        this.name = name;
        this.purchasePrice = purchasePrice;
        this.sellPrice = sellPrice;
        this.discountPrice = discountPrice;
        this.nbItems = nbItems;
    }


    public static void setCapital(double initialCapital) {
        if (initialCapital <= 0)
            throw new IllegalArgumentException("Negative price!");
        capital = initialCapital;
    }

    public static double getCapital() { return capital; }
    public static double getIncome() { return income; }
    public static double getCost() { return cost; }

    public int getNumber() { return number; }
    public String getName() { return name; }
    public double getPurchasePrice() { return purchasePrice; }
    public double getSellPrice() { return sellPrice; }
    public double getDiscountPrice() { return discountPrice; }
    public int getNbItems() { return nbItems; }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Name Required !");
        this.name = name.trim();
    }

    public void setPurchasePrice(double purchasePrice) {
        if (purchasePrice < 0)
            throw new IllegalArgumentException("Negative price !");
        this.purchasePrice = purchasePrice;
    }

    public void setSellPrice(double sellPrice) {
        if (sellPrice < 0)
            throw new IllegalArgumentException("Negative price !");
        this.sellPrice = sellPrice;
    }

    public void setDiscountPrice(double discountPrice) {
        if (discountPrice < 0)
            throw new IllegalArgumentException("Negative price !");
        this.discountPrice = discountPrice;
    }

    public void setNbItems(int nbItems) {
        if (nbItems < 0)
            throw new IllegalArgumentException("Negative number !");
        this.nbItems = nbItems;
    }

    public void sell(int count) {
        if (count <= 0)
            throw new IllegalArgumentException("Negative price !");
        if (count > nbItems)
            throw new IllegalArgumentException("Product Unavailable");

        nbItems -= count;
        double amount = getEffectiveSellPrice() * count;
        income += amount;
        capital += amount;
    }

    public void purchase(int count) {
        if (count <= 0)
            throw new IllegalArgumentException("Negative price !");
        double amount = purchasePrice * count;
        if (capital < amount)
            throw new IllegalArgumentException("Insufficient budget");
        nbItems += count;
        cost += amount;
        capital -= amount;
    }

    protected double getEffectiveSellPrice() {
        return (discountPrice > 0.0) ? discountPrice : sellPrice;
    }

    @Override
    public String toString() {
        return String.format(
                "%s{#%d, name='%s', buy=%.2f, sell=%.2f, discount=%.2f, stock=%d}",
                this.getClass().getSimpleName(),
                number, name, purchasePrice, sellPrice, discountPrice, nbItems);
    }

    @Override
    public void applyDiscount() {
        double rate;
        if (this instanceof Clothes) {
            rate = CLOTHES_DISCOUNT;
        } else if (this instanceof Shoes) {
            rate = SHOES_DISCOUNT;
        } else {
            rate = ACCESSORY_DISCOUNT;
        }
        this.discountPrice = this.sellPrice * (1.0 - rate);
    }

    @Override
    public void unApplyDiscount() {
        this.discountPrice = 0.0;
    }

    @Override
    public int compareTo(Product other) {
        return Double.compare(this.sellPrice, other.sellPrice);
    }
}
