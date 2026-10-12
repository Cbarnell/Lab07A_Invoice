import java.util.ArrayList;
import java.util.List;

public class Invoice {
    private final List<LineItem> lineItems;

    public Invoice() {
        this.lineItems = new ArrayList<>();
    }

    public void addLineItem(LineItem item) {
        lineItems.add(item);
    }

    public double getTotalAmountDue() {
        double total = 0.0;
        for (LineItem item : lineItems) {
            total += item.getTotal();
        }
        return total;
    }

    public String getFormattedInvoice() {
        StringBuilder sb = new StringBuilder();
        sb.append("=====================================================\n");
        sb.append("                       INVOICE                       \n");
        sb.append("=====================================================\n");
        sb.append(String.format("%-20s %-5s %-9s %-9s\n", "Item", "Qty", "Price", "Total"));
        sb.append("-----------------------------------------------------\n");

        for (LineItem item : lineItems) {
            sb.append(item.getFormattedLine()).append("\n");
        }

        sb.append("-----------------------------------------------------\n");
        sb.append(String.format("AMOUNT DUE: $%35.2f\n", getTotalAmountDue()));
        sb.append("=====================================================\n");

        return sb.toString();
    }
}