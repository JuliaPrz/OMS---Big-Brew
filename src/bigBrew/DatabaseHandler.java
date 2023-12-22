package bigBrew;

import java.awt.Color;
import java.awt.Font;

import java.sql.*;

import java.text.DecimalFormat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;


public class DatabaseHandler {
        
        // database information
       public static String data_connection = "jdbc:mysql://localhost:3306/BigBrew_DB"; // database location
       public static String user = "root"; // database username
       public static String pass = "Gabriel(1218)"; // database password
       
       //Change table name depending on the value of comboBox; used only in this class
    public static String TName (JComboBox<String> comboBox){
        String selectedValue = comboBox.getSelectedItem().toString();
        
        String tableName;
        switch (selectedValue) {
            case "Milk Tea":
                tableName = "milk_tea";
                break;
            case "Iced Coffee":
                tableName = "iced_coffee";
                break;
            case "Hot Brew":
                tableName = "hot_brew";
                break;
            case "Fruit Tea":
                tableName = "fruit_tea";
                break;
            case "Praf":
                tableName = "praf";
                break;
            default:
                tableName = "add_ons";
                break;
        }
        return tableName;
    }
       
        // ORDER: updates the product name table in the order panel
    public static void orderUpdateProductName(JComboBox<String> comboBox, JTable table) {   
    //Modifies the table text properties
       
        table.setFont(new Font("Bahnschrift SemiBold", Font.PLAIN, 20));

        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setForeground(Color.black);
        Font headerFont = new Font("Bahnschrift SemiBold", Font.PLAIN, 20);
        tableHeader.setFont(headerFont);
        
        String tableName = TName (comboBox);
   
        try {
                // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
             
             // Establish a connection to the MySQL database
            try (Connection connection = DriverManager.getConnection(data_connection, user, pass); 
                    // Create a statement object for executing SQL queries
                Statement statement = connection.createStatement()) {
                String query = "SELECT * FROM " + tableName;
                ResultSet rs = statement.executeQuery(query);
         
                DefaultTableModel model =  (DefaultTableModel) table.getModel();
                
                // Clear all rows in the model
                model.setRowCount(0);

                String pname; // Variables to store data from the result set
                // Iterate through the result set and populate the table model
                    while (rs.next()) {
                        pname = rs.getString(1);
                        
                        // Create a row array and add it to the table model
                        String[] row = {pname};
                        model.addRow(row);
                    }        
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
        //ORDER: Disable or enable the combobox for size
    public static void orderUpdateSize(JComboBox<String> category_comboBox, JComboBox<String> size_comboBox){
        String tableName = TName (category_comboBox);
        if (tableName.equals("hot_brew") || tableName.equals("add_ons")) 
            size_comboBox.setEnabled(false);
        else 
            size_comboBox.setEnabled(true);
        }
    
        // ORDER:  adds the order to the 'Current Order' table
    public static void addOrder (JComboBox<String> category, JComboBox<String> size_cbb, JTable table, JTable currentOrder_table, JSpinner qty_spinner, LocalDate date){
        
        String tableName = TName (category);
        int selectedRow = table.getSelectedRow();
        String selectedValue = size_cbb.getSelectedItem().toString();
        int qty = Integer.parseInt(qty_spinner.getValue().toString()); // gets the value of the spinner
        ResultSet rs;
        int currentRow = 0;
        double value = 0; 
        
        
        // Set-up the design of the currentOrder_table
        DefaultTableModel model = (DefaultTableModel) currentOrder_table.getModel();
        currentOrder_table.setFont(new Font("Bahnschrift SemiBold", Font.PLAIN, 13));
        
        JTableHeader tableHeader = currentOrder_table.getTableHeader();
        tableHeader.setForeground(Color.black);
        Font headerFont = new Font("Bahnschrift SemiBold", Font.PLAIN, 13);
        tableHeader.setFont(headerFont);
        
        currentOrder_table.setRowHeight(table.getRowHeight()+7);
 
              try {     // connect to the database
                Class.forName("com.mysql.cj.jdbc.Driver");
                try (Connection connection = DriverManager.getConnection(data_connection, user, pass); 
                    Statement statement = connection.createStatement()) {
                    
                    if (tableName.equals("hot_brew") || tableName.equals("add_ons")) {
                        String price_query = "SELECT `Price` FROM " + tableName;
                        rs = statement.executeQuery(price_query);

                        while (rs.next()) {
                            if (currentRow == selectedRow) {
                                value = rs.getDouble("Price"); // Now 'value' contains the Price for the selected row
                                break; // Break the loop once the selected row is found
                            }
                            currentRow++;
                        }
                        if (selectedRow != -1) {
                            // Process the selected row
                            DecimalFormat decimal_format = new DecimalFormat("0.00");
                            double total_perItem = qty * value;
                            String formatted_totalPerItem = decimal_format.format(total_perItem);
                            Object name = table.getValueAt(selectedRow, 0);
                            Object size = "N/A"; // Selecting size is not applicable for Hot Brew and Add-Ons
                            Object[] rowData = {name, size, qty, formatted_totalPerItem};
                            model.addRow(rowData);
                        }                   
                     else 
                            JOptionPane.showMessageDialog(null, "No row selected.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        // FOR SIZE COMBOBOX

                        if (selectedValue.equals("Medio")) {
                        String medio_query = "SELECT `Medio Price` FROM " + tableName;
                        rs = statement.executeQuery(medio_query);

                        while (rs.next()) {
                            if (currentRow == selectedRow) {
                                value = rs.getDouble("Medio Price"); // Now 'value' contains the Price for the selected row
                                break; // Break the loop once the selected row is found
                            }
                            currentRow++;
                        }
                        if (selectedRow != -1) {
                            // Process the selected row
                            DecimalFormat decimal_format = new DecimalFormat("0.00");
                            double total_perItem = qty * value;
                            String formatted_totalPerItem = decimal_format.format(total_perItem);
                            Object name = table.getValueAt(selectedRow, 0);
                            Object size = selectedValue;
                            Object[] rowData = {name, size, qty, formatted_totalPerItem};
                            model.addRow(rowData);
                        }
                        else 
                            JOptionPane.showMessageDialog(null, "No row selected.", "Error", JOptionPane.ERROR_MESSAGE);
 
                    } else if (selectedValue.equals("Grande")) {
                        String grande_query = "SELECT `Grande Price` FROM " + tableName;
                        rs = statement.executeQuery(grande_query);

                        while (rs.next()) {
                            if (currentRow == selectedRow) {
                                value = rs.getDouble("Grande Price"); // Now 'value' contains the Price for the selected row
                                break; // Break the loop once the selected row is found
                            }
                            currentRow++;
                        }
                        if (selectedRow != -1) {
                            // Process the selected row
                            DecimalFormat decimal_format = new DecimalFormat("0.00");
                            double total_perItem = qty * value;
                            String formatted_totalPerItem = decimal_format.format(total_perItem);
                            Object name = table.getValueAt(selectedRow, 0);
                            Object size = selectedValue;
                            Object[] rowData = {name, size, qty, formatted_totalPerItem};
                            model.addRow(rowData);
                        }
                        else 
                            JOptionPane.showMessageDialog(null, "No row selected.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
                       connection.close();
                    } catch (SQLException ex) {
                    Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
                    }
       }   catch (ClassNotFoundException ex) {
               Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
           }
              
    }
    
        // ORDER: 
    public static void calculateTotal(JTable currentOrder_table, JLabel label){
        
        DefaultTableModel model = (DefaultTableModel) currentOrder_table.getModel();
        int rowCount = model.getRowCount();
        double price = 0, total = 0;

                for (int i = 0; i < rowCount; i++) {
                    price = Double.parseDouble(model.getValueAt(i, 3).toString()); // parsed kasi naging String yung total_perItem due to decimal fornmatting
                    total += price;
                }
                
                DecimalFormat decimal_format = new DecimalFormat("0.00");
                label.setText(String.valueOf(decimal_format.format(total)));
            }
    
        // ORDER: 
    public static void calculateChange(JTextField cash, JLabel total, JLabel change){

            try {
                String cashText = cash.getText().trim();  // Trim to remove leading/trailing whitespaces
                if (cashText.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter a cash value.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;  // Exit the method if the cash value is empty
                }
                
                    double c = Double.parseDouble(cash.getText());
                    double t = Double.parseDouble(total.getText());
                    
                    double b = c - t;
                    DecimalFormat decimal_format = new DecimalFormat("0.00");
                    change.setText(String.valueOf(b));
                    
                } catch (NumberFormatException e) {     // Handle the case where the input is not a valid double
                        JOptionPane.showMessageDialog(null, "Invalid cash value! Please enter a valid numeric value.", "Error", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                }
        
               
            }
    
        // ORDER: Remove the selected order in the 'Current Order' table
    public static void deleteCurrentOrder (JTable table, JLabel label){
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int selectedRow = table.getSelectedRow();

        if (selectedRow == -1) {
            // No row selected, show an error message
            JOptionPane.showMessageDialog(null, "Please select a row to delete.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove the item?", "Delete", JOptionPane.YES_NO_OPTION); // yes == 0

            if (option == 0) { //yes
                
                // Get the value of the item to be deleted
                String stringValue = (String) model.getValueAt(selectedRow, 3);
                double deletedPrice = Double.parseDouble(stringValue);
              
                // Remove the selected row from the table model
                model.removeRow(selectedRow);
                // Update the total by subtracting the value of the deleted item
                double currentTotal = Double.parseDouble(label.getText());
                double newTotal = currentTotal - deletedPrice;
                DecimalFormat decimal_format = new DecimalFormat("0.00");
                label.setText(String.valueOf(decimal_format.format(newTotal)));
            }
        }
    }
    
        // ORDER: Save the orders made in the database        
    public static void saveOrder(JTable table, LocalDate date, JTextField cash){
            // Retrieve data from the JTable
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            int rowCount = model.getRowCount();
            String tableName = "history"; 

            try {
                // Establish database connection
                Class.forName("com.mysql.cj.jdbc.Driver");
                try (Connection connection = DriverManager.getConnection(data_connection, user, pass)) {
                    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    String formattedDate = date.format(dateFormat);

                    for (int i = 0; i < rowCount; i++) {
                        String productName = (String) model.getValueAt(i, 0);
                        String size = (String) model.getValueAt(i, 1);
                        int quantity = (int) model.getValueAt(i, 2);
                        String stringValue = (String) model.getValueAt(i, 3);
                        double price = Double.parseDouble(stringValue);
                        

                        // Insert data into the database
                        try (PreparedStatement pst = connection.prepareStatement("INSERT INTO " + tableName + " (`Date`, `Product Name`, `Size`, Quantity, Price) VALUES (?,?,?,?,?)")) {
                            pst.setDate(1, java.sql.Date.valueOf(formattedDate));
                            pst.setString(2, productName);
                            pst.setString(3, tableName.equals("hot_brew") || tableName.equals("add_ons") ? "N/A" : size);
                            pst.setInt(4, quantity);
                            pst.setDouble(5, price);
                            pst.executeUpdate();
                        }
                    }
                }
                    } catch (ClassNotFoundException | SQLException e) {
                } 
       }    
    
        // HISTORY: retrieves the data from the database
    public static void orderHistory(JTable table) {   
    //Modifies the table text properties
       
        table.setFont(new Font("Bahnschrift SemiBold", Font.PLAIN, 20));

        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setForeground(Color.black);
        Font headerFont = new Font("Bahnschrift SemiBold", Font.PLAIN, 20);
        tableHeader.setFont(headerFont);
        table.setRowHeight(table.getRowHeight()+20);
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection(data_connection, user, pass); 
                    
                Statement statement = connection.createStatement()) {
                String query = "SELECT * FROM history";
                ResultSet rs = statement.executeQuery(query);
         
                DefaultTableModel model =  (DefaultTableModel) table.getModel();
                
                // Clear all rows in the model
                model.setRowCount(0);
                
                Date date;
                String name, size; 
                int qty;
                double price;
                
                    while (rs.next()) {
                        date = rs.getDate(1);
                        name = rs.getString(2);
                        size = rs.getString(3);
                        qty = rs.getInt(4);
                        price = rs.getDouble(5);
                        
                        // Create a row array and add/print it to the table model
                        Object[] row = {date, name, size,qty,price};
                        model.addRow(row);
                    }
                    
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
       // MENU: updates the data in the jTable/database
    public static void updateTableData(JComboBox<String> comboBox, JTable table) {

        table.setModel(new DefaultTableModel()); // used to clear the table
        //Modifies the table text properties
        table.setFont(new Font("Bahnschrift SemiBold", Font.PLAIN, 20));
        
        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setForeground(Color.black);
        Font headerFont = new Font("Bahnschrift SemiBold", Font.PLAIN, 20);
        tableHeader.setFont(headerFont);
        
        String tableName = TName(comboBox);

        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            try ( // Establish a connection to the MySQL database
                   Connection connection = DriverManager.getConnection(data_connection, user, pass); // Create a statement object for executing SQL queries
                Statement statement = connection.createStatement()) {
                // Define an SQL query to select all records from the selected table from the jcombobox
                String query = "SELECT * FROM " + tableName;
                // Execute the SQL query and get the result set
                ResultSet rs = statement.executeQuery(query);
                // Retrieve metadata about the result set
                ResultSetMetaData rsmd = rs.getMetaData();
                // Obtain the table model from the 'mt_table' component
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                int c_count = rsmd.getColumnCount(); // Get the number of columns in the result set
                String[] colName = new String[c_count]; // Create an array to store column names
                // Populate the array with column names from the result set metadata
                for (int i = 0; i < c_count; i++)
                    colName[i] = rsmd.getColumnName(i + 1);
                model.setColumnIdentifiers(colName);
                String pname, mprice, gprice, pprice; // Variables to store data from the result set
                // Iterate through the result set and populate the table model
                if ("hot_brew".equals(tableName) || "add_ons".equals(tableName)){
                    while (rs.next()) {
                        pname = rs.getString(1);
                        pprice = rs.getString(2);
                        
                        // Create a row array and add it to the table model
                        String[] row = {pname,pprice};
                        model.addRow(row);
                    }
                } else {
                    while (rs.next()) {
                        pname = rs.getString(1);
                        mprice = rs.getString(2);
                        gprice = rs.getString(3);
                        
                        String[] row = {pname, mprice, gprice};
                        model.addRow(row);
                    }
                }  
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
        // MENU: add new items in the table/database
    public static void addItem (JComboBox<String> comboBox, JTextField name, JTextField name1, JTextField price1, JTextField price2, JTextField price3){
         
        String tableName = TName (comboBox);
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(data_connection, user, pass);
                
                if (tableName.equals("hot_brew") || tableName.equals("add_ons")){
                    try{
                        PreparedStatement pst = connection.prepareStatement("INSERT INTO " + tableName + " (`Product Name`, `Price`) VALUES (?,?)");

                        pst.setString(1, name1.getText());
                        pst.setDouble(2, Double.parseDouble(price3.getText()));
                        pst.executeUpdate();
                        JOptionPane.showMessageDialog (null, "Record Added");
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Invalid Price! Please enter a valid numeric value.", "Error", JOptionPane.ERROR_MESSAGE);
                    }catch (SQLIntegrityConstraintViolationException ex) { // Handle the case where there is a duplicate entry
                           JOptionPane.showMessageDialog(null, ex.getMessage() + " is already listed.", "Error", JOptionPane.ERROR_MESSAGE);
                         }
                } else {
                    
                    try{
                        PreparedStatement pst = connection.prepareStatement("INSERT INTO " + tableName + " (`Product Name`, `Medio Price`, `Grande Price`) VALUES (?,?,?)");
                        
                        pst.setString(1, name.getText());
                        pst.setDouble(2, Double.parseDouble(price1.getText()));
                        pst.setDouble(3, Double.parseDouble(price2.getText()));
                        pst.executeUpdate();
                        JOptionPane.showMessageDialog (null, "Record Added");
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Invalid Price! Please enter a valid numeric value.", "Error", JOptionPane.ERROR_MESSAGE);
                    } catch (SQLIntegrityConstraintViolationException ex) { // Handle the case where there is a duplicate entry
                           JOptionPane.showMessageDialog(null, ex.getMessage() + " is already listed.", "Error", JOptionPane.ERROR_MESSAGE);
                         }
                }            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
        // MENU: delete a product item
    public static void deleteItem (JTable table, JComboBox<String> comboBox){
        int selectedRow = table.getSelectedRow();

        if (selectedRow == -1) {
            // No row selected, show an error message
            JOptionPane.showMessageDialog(null, "Please select a row to delete.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            int option = JOptionPane.showConfirmDialog(null, "Are you sure?", "Delete", JOptionPane.YES_NO_OPTION); // yes == 0
            
            if (option == 0){ //yes
                String tableName = TName (comboBox);
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    try (Connection connection = DriverManager.getConnection(data_connection, user, pass)) {
                        int row = table.getSelectedRow();
                        String value = (table.getModel().getValueAt(row, 0).toString());
                        String query = "DELETE FROM " + tableName + " WHERE `Product Name` = ?";
                        
                        PreparedStatement pst = connection.prepareStatement(query);
                        
                        pst.setString(1, value);
                        pst.executeUpdate();
                        JOptionPane.showMessageDialog (null, "Item successfully deleted.");
                    }

                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
                }  
                
            } 
        }  
    }
    
        // MENU:Populates the last comboBox in delete_panel depending on product category
    public static void editComboBox (JComboBox<String> comboBox, JComboBox<String> comboBox1){
        
        String tableName = TName (comboBox);
        
         try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection(data_connection, user, pass)) {
                Statement statement = connection.createStatement();
                String query = "SELECT `Product Name` FROM " + tableName;
                ResultSet rs = statement.executeQuery(query);
                
                comboBox1.removeAllItems();
                while (rs.next()){
                    String name = rs.getString(1);
                    comboBox1.addItem(name);  // item name
                }
            }
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

        // MENU: edit the price of an item
    public static void editItem (JComboBox<String> comboBox, JComboBox<String> comboBox2, JTextField price1, JTextField price2, JTextField price3){
        String tableName = TName (comboBox);
        String selectedValue = comboBox2.getSelectedItem().toString();
        
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection(data_connection, user, pass);

                    if (tableName.equals("hot_brew") || tableName.equals("add_ons")){
                    try{
                        String query = "UPDATE " + tableName + " SET `Price` = ? WHERE `Product Name` = ?";
                        PreparedStatement pst = connection.prepareStatement(query);

                        pst.setDouble(1, Double.parseDouble(price3.getText()));
                        pst.setString(2, selectedValue);
                        pst.executeUpdate();
                        JOptionPane.showMessageDialog (null, "Record Updated");
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Invalid Price! Please enter a valid numeric value.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    
                    try{
                       
                        String query = "UPDATE " + tableName + " SET `Medio Price` = ?, `Grande Price` = ? WHERE `Product Name` = ?";
                        PreparedStatement pst = connection.prepareStatement(query);
 
                        pst.setDouble(1, Double.parseDouble(price1.getText()));
                        pst.setDouble(2, Double.parseDouble(price2.getText()));
                        pst.setString(3, selectedValue);
                        pst.executeUpdate();
                        JOptionPane.showMessageDialog (null, "Record Updated");
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Invalid Price! Please enter a valid numeric value.", "Error", JOptionPane.ERROR_MESSAGE);
                    } 
                    connection.close();
                    }
           } catch (ClassNotFoundException ex) {
               Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
           } catch (SQLException ex) {
               Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
           }
    
    }

        
    
        

        
    
         
    
        
    
        

    
}
        
    
    
    
    
    
    
    
    



