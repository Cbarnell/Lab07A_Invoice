import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class InvoiceFrame extends JFrame {
    private final Invoice invoice;

    private final JTextField txtProductName;
    private final JTextField txtUnitPrice;
    private final JTextField txtQuantity;
    private JTextArea txtInvoiceDisplay;

    public InvoiceFrame() {
        invoice = new Invoice();

        setTitle("Invoice Entry System - Lab07A_Invoice");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Add Line Item"));

        inputPanel.add(new JLabel("Product Name:"));
        txtProductName = new JTextField();
        inputPanel.add(txtProductName);

        inputPanel.add(new JLabel("Unit Price ($):"));
        txtUnitPrice = new JTextField();
        inputPanel.add(txtUnitPrice);

        inputPanel.add(new JLabel("Quantity:"));
        txtQuantity = new JTextField();
        inputPanel.add(txtQuantity);

        JButton btnAdd = new JButton("Add Line Item");
        btnAdd.addActionListener(this::handleAddLineItem);
        inputPanel.add(btnAdd);

        JButton btnDisplay = new JButton("Display Invoice");
        btnDisplay.addActionListener(_ -> txtInvoiceDisplay.setText(invoice.getFormattedInvoice()));
        inputPanel.add(btnDisplay);

        add(inputPanel, BorderLayout.NORTH);

        // Display Area
        txtInvoiceDisplay = new JTextArea();
        txtInvoiceDisplay.setFont(new Font("Monospaced", Font.PLAIN, 12));
        txtInvoiceDisplay.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(txtInvoiceDisplay);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Invoice Display"));

        add(scrollPane, BorderLayout.CENTER);
    }

    private void handleAddLineItem(ActionEvent e) {
        try {
            String name = txtProductName.getText().trim();
            double price = Double.parseDouble(txtUnitPrice.getText().trim());
            int qty = Integer.parseInt(txtQuantity.getText().trim());

            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a product name.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Product product = new Product(name, price);
            LineItem item = new LineItem(product, qty);
            invoice.addLineItem(item);

            // Clear inputs
            txtProductName.setText("");
            txtUnitPrice.setText("");
            txtQuantity.setText("");

            JOptionPane.showMessageDialog(this, "Line item added successfully!");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numeric values for price and quantity.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    static void main() {
        SwingUtilities.invokeLater(() -> {
            InvoiceFrame frame = new InvoiceFrame();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}