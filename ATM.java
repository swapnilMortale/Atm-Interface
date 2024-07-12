import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATM extends JFrame {
    private BankAccount account;
    private JTextField amountField;
    private JLabel messageLabel;
    private JLabel balanceLabel;

    public ATM(BankAccount account) {
        this.account = account;
        createUI();
    }

    private void createUI() {
        setTitle("ATM Machine");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10));

        JLabel amountLabel = new JLabel("Enter Amount:");
        amountField = new JTextField();
        
        JButton withdrawButton = new JButton("Withdraw");
        JButton depositButton = new JButton("Deposit");
        JButton checkBalanceButton = new JButton("Check Balance");

        messageLabel = new JLabel("");
        balanceLabel = new JLabel("Balance: $" + account.getBalance());

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleWithdraw();
            }
        });

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleDeposit();
            }
        });

        checkBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleCheckBalance();
            }
        });

        panel.add(amountLabel);
        panel.add(amountField);
        panel.add(withdrawButton);
        panel.add(depositButton);
        panel.add(checkBalanceButton);
        panel.add(messageLabel);
        panel.add(balanceLabel);

        add(panel);

        setVisible(true);
    }

    private void handleWithdraw() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            if (account.withdraw(amount)) {
                messageLabel.setText("Withdrawal successful!");
            } else {
                messageLabel.setText("Insufficient balance!");
            }
        } catch (NumberFormatException e) {
            messageLabel.setText("Invalid amount!");
        }
        balanceLabel.setText("Balance: $" + account.getBalance());
    }

    private void handleDeposit() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            account.deposit(amount);
            messageLabel.setText("Deposit successful!");
        } catch (NumberFormatException e) {
            messageLabel.setText("Invalid amount!");
        }
        balanceLabel.setText("Balance: $" + account.getBalance());
    }

    private void handleCheckBalance() {
        balanceLabel.setText("Balance: $" + account.getBalance());
        messageLabel.setText("Balance checked.");
    }

    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000.00);
        new ATM(account);
    }
}
