package ui;

import javax.swing.*;
import java.awt.Font;

import controller.SystemController;
import data.Address;
import data.Author;
import data.Book;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddBookUI extends JPanel {
    private JTextField txtTitle;
    private JTextField txtISBN;
    private JTextField txtAvailability;
    SystemController controller = SystemController.getInstance();

    /**
     * Create the panel.
     */
    public AddBookUI() {
        setLayout(null);

        JLabel lblNewLabel = new JLabel("Add Book");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblNewLabel.setBounds(61, 43, 125, 32);
        add(lblNewLabel);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(249, 10, 2, 2);
        add(scrollPane);

        JLabel lblNewLabel_1 = new JLabel("Title :");
        lblNewLabel_1.setFont(new Font("Lucida Bright", Font.BOLD, 17));
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setBounds(61, 104, 108, 32);
        add(lblNewLabel_1);

        txtTitle = new JTextField();
        txtTitle.setFont(new Font("Tahoma", Font.BOLD, 12));
        txtTitle.setBounds(90, 146, 412, 32);
        add(txtTitle);
        txtTitle.setColumns(10);

        txtISBN = new JTextField();
        txtISBN.setBounds(90, 230, 412, 32);
        add(txtISBN);
        txtISBN.setColumns(10);

        JLabel lblNewLabel_2 = new JLabel("ISBN :");
        lblNewLabel_2.setFont(new Font("Lucida Bright", Font.BOLD, 17));
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.LEFT);
        lblNewLabel_2.setBounds(90, 200, 96, 13);
        add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("Authors :");
        lblNewLabel_3.setFont(new Font("Lucida Bright", Font.BOLD, 17));
        lblNewLabel_3.setHorizontalAlignment(SwingConstants.LEFT);
        lblNewLabel_3.setBounds(90, 272, 96, 32);
        add(lblNewLabel_3);

        JComboBox cmbAuthors = new JComboBox();
        cmbAuthors.setBounds(90, 314, 412, 38);
        for (Author a : controller.getAuthors()) {
            cmbAuthors.addItem(a.toString());
        }
        add(cmbAuthors);

        JLabel lblNewLabel_4 = new JLabel("Availability :");
        lblNewLabel_4.setHorizontalAlignment(SwingConstants.LEFT);
        lblNewLabel_4.setFont(new Font("Lucida Bright", Font.BOLD, 17));
        lblNewLabel_4.setBounds(90, 375, 125, 32);
        add(lblNewLabel_4);

        txtAvailability = new JTextField();
        txtAvailability.setBounds(90, 417, 412, 38);
        add(txtAvailability);
        txtAvailability.setColumns(10);

        JButton btnAddAuthors = new JButton("Add");
        btnAddAuthors.setFont(new Font("Tahoma", Font.BOLD, 17));
        btnAddAuthors.setBounds(512, 314, 85, 38);
        add(btnAddAuthors);

        JButton btnSave = new JButton("Save");
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                var avaiableBooks = 0;
                try {
                    avaiableBooks = Integer.parseInt(txtAvailability.getText());
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null, "Available books must be a number", "Available books" , JOptionPane.ERROR_MESSAGE);
                    return;
                }
                var selectedAuthor = controller.getAuthors().get(cmbAuthors.getSelectedIndex());
                controller.addBook(new Book(controller.getBooks().size() + 1, txtTitle.getText(), txtISBN.getText(), selectedAuthor, avaiableBooks));
            }
        });
        btnSave.setFont(new Font("Lucida Bright", Font.BOLD, 17));
        btnSave.setBounds(90, 500, 108, 32);
        add(btnSave);

        JButton btnCancel = new JButton("Cancel");
        btnCancel.setFont(new Font("Lucida Bright", Font.BOLD, 17));
        btnCancel.setBounds(207, 500, 108, 32);
        add(btnCancel);

    }
}
