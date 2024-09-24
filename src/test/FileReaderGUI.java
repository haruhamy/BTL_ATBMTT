package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class FileReaderGUI extends JFrame implements ActionListener {
    private JTextArea textArea;
    private JButton openButton;

    public FileReaderGUI() {
        // Tạo khung giao diện
        super("File Reader");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);

        // Tạo một JTextArea để hiển thị nội dung file
        textArea = new JTextArea();
        textArea.setEditable(false);  // Đặt không cho sửa nội dung

        // Đặt JTextArea vào trong JScrollPane để hỗ trợ cuộn
        JScrollPane scrollPane = new JScrollPane(textArea);

        // Tạo nút mở file
        openButton = new JButton("Open File");
        openButton.addActionListener(this);

        // Sắp xếp giao diện
        setLayout(new BorderLayout());
        add(openButton, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Đặt cửa sổ ở giữa màn hình
        setLocationRelativeTo(null);

        // Hiển thị giao diện
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == openButton) {
            JFileChooser fileChooser = new JFileChooser();
            int option = fileChooser.showOpenDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                    textArea.setText("");  // Xóa nội dung cũ
                    String line;
                    while ((line = br.readLine()) != null) {
                        textArea.append(line + "\n");
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Error reading file: " + ex.getMessage());
                }
            }
        }
    }

    public static void main(String[] args) {
        // Đảm bảo giao diện được hiển thị ngay khi khởi động
        SwingUtilities.invokeLater(() -> new FileReaderGUI());
    }
}
