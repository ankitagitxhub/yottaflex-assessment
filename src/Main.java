package src;

import java.util.ArrayList;
import java.util.List;

public class Main {

    // Simple Data Model representing a verified transaction
    public static class Transaction {
        private String id;
        private double amount;
        private String status;
        private String notes;

        public Transaction(String id, double amount, String status, String notes) {
            this.id = id;
            this.amount = amount;
            this.status = status;
            this.notes = notes;
        }

        @Override
        public String toString() {
            return String.format("ID: %s | Amount: ₹%.2f | Status: %s | Notes: %s", id, amount, status, notes);
        }
    }

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("🚀 YottaFlex Local Data Engine - Starting Execution");
        System.out.println("==================================================\n");

        // Mocking incoming local sample raw stream data strings
        String[] rawInputData = {
            "TXN1001,5500.50,APPROVED",
            "TXN1002,-200.00,PENDING", 
            "TXN1003,125000.00,APPROVED",
            "TXN1004,0.00,FAILED",
            "INVALID_STREAM_RECORD"
        };

        List<Transaction> processedTransactions = new ArrayList<>();

        System.out.println("--- Processing Raw Input Streams ---");
        for (String record : rawInputData) {
            try {
                Transaction txn = parseAndValidateRecord(record);
                processedTransactions.add(txn);
                System.out.println("[SUCCESS] Successfully processed: " + txn.id);
            } catch (Exception e) {
                System.out.println("[ERROR] Failed to process record [" + record + "]: " + e.getMessage());
            }
        }

        System.out.println("\n==================================================");
        System.out.println("📊 Final Local Processing Summary Report");
        System.out.println("==================================================");
        for (Transaction t : processedTransactions) {
            System.out.println(t);
        }
        System.out.println("==================================================");
        System.out.println("Execution Completed Successfully.");
    }

    /**
     * Parses a comma-separated record string, applying human-style business rules.
     */
    private static Transaction parseAndValidateRecord(String record) throws IllegalArgumentException {
        if (record == null || !record.contains(",")) {
            throw new IllegalArgumentException("Malformed record structural format.");
        }

        String[] tokens = record.split(",");
        if (tokens.length < 3) {
            throw new IllegalArgumentException("Missing key data metrics.");
        }

        String id = tokens[0].trim();
        double amount;
        
        try {
            amount = Double.parseDouble(tokens[1].trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Amount token is not a valid scalar number.");
        }

        String status = tokens[2].trim();

        // Evaluated Human Business Logic Rules
        String notes = "Verified Standard Transaction";
        if (amount <= 0) {
            status = "REJECTED";
            notes = "Alert: Transaction amount must be strictly greater than zero.";
        } else if (amount > 100000) {
            notes = "Flagged: High-value transaction tier requiring upper management review.";
        }

        return new Transaction(id, amount, status, notes);
    }
}
