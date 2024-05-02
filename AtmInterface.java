import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AtmInterface extends JFrame implements ActionListener {
    private JLabel balanceLabel;
    private JTextField amountField;
    private JButton withdrawButton;
    private JButton depositButton;
    private JButton balanceButton;

    private BankAccount userAccount;

    public AtmInterface(BankAccount userAccount) {
        this.userAccount = userAccount;

        setTitle("ATM Interface");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        balanceLabel = new JLabel("Balance: $" + userAccount.getBalance());
        panel.add(balanceLabel);

        amountField = new JTextField();
        panel.add(amountField);

        withdrawButton = new JButton("Withdraw");
        withdrawButton.addActionListener(this);
        panel.add(withdrawButton);

        depositButton = new JButton("Deposit");
        depositButton.addActionListener(this);
        panel.add(depositButton);

        balanceButton = new JButton("Check Balance");
        balanceButton.addActionListener(this);
        panel.add(balanceButton);

        add(panel);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == withdrawButton) {
            double amount = Double.parseDouble(amountField.getText());
            if (userAccount.withdraw(amount)) {
                balanceLabel.setText("Balance: $" + userAccount.getBalance());
                JOptionPane.showMessageDialog(this, "Withdrawal successful.");
            } else {
                JOptionPane.showMessageDialog(this, "Insufficient balance.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == depositButton) {
            double amount = Double.parseDouble(amountField.getText());
            userAccount.deposit(amount);
            balanceLabel.setText("Balance: $" + userAccount.getBalance());
            JOptionPane.showMessageDialog(this, "Deposit successful.");
        } else if (e.getSource() == balanceButton) {
            JOptionPane.showMessageDialog(this, "Current balance: $" + userAccount.getBalance());
        }
    }

    public static void main(String[] args) {
        BankAccount userAccount = new BankAccount(1000.0);
        new AtmInterface(userAccount);
    }
}

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }
}
