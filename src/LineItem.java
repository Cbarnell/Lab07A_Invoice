public class LineItem {
    private final Product product;
    private final int quantity;

    public LineItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public double getTotal() {
        return product.getUnitPrice() * quantity;
    }

    public String getFormattedLine() {
        return String.format("%-20s %-5d $%8.2f $%8.2f",
                product.getName(), quantity, product.getUnitPrice(), getTotal());
    }
}