import java.io.File;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.util.StringConverter;


public class AllProductsPane extends Pane implements DBInfo {

    TableView table = new TableView();
    TableColumn<Product, Integer> columnId = new TableColumn("ID");
    TableColumn<Product, String> columnName = new TableColumn("Name");
    TableColumn<Product, Double> columnPrice = new TableColumn("Price ($)");
    TableColumn<Product, String> columnAddedDate = new TableColumn("Added Date");
    Label productImage = new Label("No Image Found");
    Label idLabel = new Label("ID");
    Label nameLabel = new Label("Name");
    Label priceLabel = new Label("Price");
    Label dateLabel = new Label("Date");
    Label moveFastLabel = new Label("Move Fast");
    Label searchLabel = new Label("Search");
    TextField idField = new TextField();
    TextField nameField = new TextField();
    TextField priceField = new TextField();
    TextField searchField = new TextField();
    Button updateImageButton = new Button("Update Image");
    Button insertButton = new Button("Add New", new ImageView(new Image(getClass().getResourceAsStream("/images/insert.png"))));
    Button updateButton = new Button("Update", new ImageView(new Image(getClass().getResourceAsStream("/images/update.png"))));
    Button deleteButton = new Button("Delete", new ImageView(new Image(getClass().getResourceAsStream("/images/delete.png"))));
    Button selectFirstButton = new Button("First", new ImageView(new Image(getClass().getResourceAsStream("/images/first.png"))));
    Button selectLastButton = new Button("Last", new ImageView(new Image(getClass().getResourceAsStream("/images/last.png"))));
    Button selectNextButton = new Button("Next", new ImageView(new Image(getClass().getResourceAsStream("/images/next.png"))));
    Button selectPreviousButton = new Button("Previous", new ImageView(new Image(getClass().getResourceAsStream("/images/previous.png"))));
    Button exitButton = new Button("Exit", new ImageView(new Image(getClass().getResourceAsStream("/images/exit.png"))));
    DatePicker datePicker = new DatePicker();



    ObservableList<Product> data = FXCollections.observableArrayList();
    
    AddNewProductDialog addProductDialog = new AddNewProductDialog(data);

    File selectedFile = null;

    final String dateFormat = "yyyy-MM-dd";

    SpecialAlert alert = new SpecialAlert();

    



    //////////////////////////////////////////////////////////////////////////////////////





    public AllProductsPane() {

        datePicker.setConverter(dateFormatter());

        table.getColumns().addAll(columnId, columnName, columnPrice, columnAddedDate);

        table.setItems(data);

        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        columnAddedDate.setCellValueFactory(new PropertyValueFactory<>("addedDate"));

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);



        productImage.setPrefSize(270, 244);
        updateImageButton.setPrefSize(130, 35);
        idLabel.setPrefSize(50, 40);
        idField.setPrefSize(270, 40);
        nameLabel.setPrefSize(50, 40);
        nameField.setPrefSize(270, 40);
        priceLabel.setPrefSize(50, 40);
        priceField.setPrefSize(270, 40);
        dateLabel.setPrefSize(50, 40);
        datePicker.setPrefSize(270, 40);
        deleteButton.setPrefSize(130, 40);
        updateButton.setPrefSize(130, 40);
        table.setPrefSize(520, 505);
        searchField.setPrefSize(255, 36);
        searchLabel.setPrefSize(115, 40);
        insertButton.setPrefSize(130, 60);
        moveFastLabel.setPrefSize(190, 30);
        selectFirstButton.setPrefSize(130, 40);
        selectLastButton.setPrefSize(130, 40);
        selectNextButton.setPrefSize(130, 40);
        selectPreviousButton.setPrefSize(130, 40);
        exitButton.setPrefSize(130, 40);






        productImage.setTranslateX(80);
        productImage.setTranslateY(40);
        updateImageButton.setTranslateX(150);
        updateImageButton.setTranslateY(295);
        idLabel.setTranslateX(20);
        idLabel.setTranslateY(355);
        idField.setTranslateX(80);
        idField.setTranslateY(355);
        nameLabel.setTranslateX(20);
        nameLabel.setTranslateY(405);
        nameField.setTranslateX(80);
        nameField.setTranslateY(405);
        priceLabel.setTranslateX(20);
        priceLabel.setTranslateY(455);
        priceField.setTranslateX(80);
        priceField.setTranslateY(455);
        dateLabel.setTranslateX(20);
        dateLabel.setTranslateY(505);
        datePicker.setTranslateX(80);
        datePicker.setTranslateY(505);
        deleteButton.setTranslateX(80);
        deleteButton.setTranslateY(575);
        updateButton.setTranslateX(220);
        updateButton.setTranslateY(575);
        table.setTranslateX(377);
        table.setTranslateY(40);
        searchField.setTranslateX(530);
        searchField.setTranslateY(577);
        searchLabel.setTranslateX(460);
        searchLabel.setTranslateY(575);
        insertButton.setTranslateX(920);
        insertButton.setTranslateY(40);
        moveFastLabel.setTranslateX(890);
        moveFastLabel.setTranslateY(150);
        selectFirstButton.setTranslateX(920);
        selectFirstButton.setTranslateY(200);
        selectLastButton.setTranslateX(920);
        selectLastButton.setTranslateY(250);
        selectNextButton.setTranslateX(920);
        selectNextButton.setTranslateY(300);
        selectPreviousButton.setTranslateX(920);
        selectPreviousButton.setTranslateY(350);
        exitButton.setTranslateX(920);
        exitButton.setTranslateY(575);






        updateImageButton.setFont(Font.font("Arial", FontWeight.BOLD, 13));
        idLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        idField.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        nameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        nameField.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        priceLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        priceField.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        dateLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        datePicker.getEditor().setFont(Font.font("Arial", FontWeight.BOLD, 15));
        deleteButton.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        updateButton.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        insertButton.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        searchField.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        searchLabel.setFont(Font.font("Arial", FontWeight.BOLD, 17));
        moveFastLabel.setFont(Font.font("Arial", FontWeight.BOLD, 22));
        selectFirstButton.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        selectLastButton.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        selectNextButton.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        selectPreviousButton.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        exitButton.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        productImage.setStyle("-fx-border-color: lightgray; -fx-border-width: 2;");
        idField.setStyle("-fx-border-color: lightgray; -fx-border-width: 2; -fx-background-color: #eee;");
        nameField.setStyle("-fx-border-color: lightgray; -fx-border-width: 2;");
        priceField.setStyle("-fx-border-color: lightgray; -fx-border-width: 2;");
        searchField.setStyle("-fx-border-color: lightgray; -fx-border-width: 2;");
        datePicker.setStyle("-fx-border-color: lightgray; -fx-border-width: 2;");
        productImage.setAlignment(Pos.CENTER);
        moveFastLabel.setAlignment(Pos.CENTER);
        idField.setEditable(false);





        this.getChildren().add(table);
        this.getChildren().add(productImage);
        this.getChildren().add(updateImageButton);
        this.getChildren().add(idLabel);
        this.getChildren().add(idField);
        this.getChildren().add(nameLabel);
        this.getChildren().add(nameField);
        this.getChildren().add(priceLabel);
        this.getChildren().add(priceField);
        this.getChildren().add(dateLabel);
        this.getChildren().add(datePicker);
        this.getChildren().add(insertButton);
        this.getChildren().add(updateButton);
        this.getChildren().add(deleteButton);
        this.getChildren().add(searchField);
        this.getChildren().add(searchLabel);
        this.getChildren().add(moveFastLabel);
        this.getChildren().add(selectFirstButton);
        this.getChildren().add(selectLastButton);
        this.getChildren().add(selectNextButton);
        this.getChildren().add(selectPreviousButton);
        this.getChildren().add(exitButton);







        fillTheTable();

        showFirstProduct();
        
        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                showProduct(table.getSelectionModel().getSelectedIndex());
            }
        });

        insertButton.setOnAction(Action -> {
            addProductDialog.display(true);
        });

        selectFirstButton.setOnAction(Action -> {
            showFirstProduct();
        });

        selectLastButton.setOnAction(Action -> {
            showLastProduct();
        });

        selectNextButton.setOnAction(Action -> {
            showNextProduct();
        });

        selectPreviousButton.setOnAction(Action -> {
            showPreviousProduct();
        });


        updateImageButton.setOnAction(Action -> {
            updateImage();
        });

        updateButton.setOnAction(Action -> {
            updateProduct();
        });
        
        deleteButton.setOnAction(Action -> {
            deleteProduct();
        });

        exitButton.setOnAction(Action -> {
            System.exit(0);
        });
        
        searchField.textProperty().addListener((obs, oldText, newText) -> {
            search();
        });


        
    }  // end of constructor





    ///////////////////////////////////////////////////////////////////////////////




    private void search()
    {
        String keyword = searchField.getText();
        
        if (keyword.equals(""))
        {
            table.setItems(data);
        }
        else {
            ObservableList<Product> filteredData = FXCollections.observableArrayList();
            for (Product product : data) {
                if(product.getName().contains(keyword))
                    filteredData.add(product);
            }
            table.setItems(filteredData);
        }
    }




    ////////////////////////////////////////////////////////////////////////




    private void showProduct(int index)
    {
        idField.setText(data.get(index).getId()+ "");
        nameField.setText(data.get(index).getName());
        priceField.setText(data.get(index).getPrice() + "");
        datePicker.getEditor().setText(data.get(index).getAddedDate());


        if (data.get(index).getImageUrl() == null) {
            productImage.setGraphic(null);
            productImage.setText("No image found");
        }
        else {
            productImage.setText("");
            productImage.setGraphic(new ImageView(new Image(
                    new File(data.get(index).getImageUrl()).toURI().toString(),
                    224, 224, true, true)));
        }        
    }





    /////////////////////////////////////////////////////////////////





    private void showFirstProduct()
    {
        if (!data.isEmpty()) {
            table.getSelectionModel().select(0);
            showProduct(0);
        }
    }




    //////////////////////////////////////////////////////////////////



    private void showLastProduct()
    {
        if (!data.isEmpty()) {
            table.getSelectionModel().select(data.size() - 1);
            showProduct(data.size() - 1);
        }
    }



    //////////////////////////////////////////////////////////////////




    private void showNextProduct()
    {
        if (table.getSelectionModel().getSelectedIndex() < data.size() - 1) {
            int currentSelectedRow = table.getSelectionModel().getSelectedIndex() + 1;
            table.getSelectionModel().select(currentSelectedRow);
            showProduct(currentSelectedRow);
        }
    }




    //////////////////////////////////////////////////////////////////




    private void showPreviousProduct()
    {
        if (table.getSelectionModel().getSelectedIndex() > 0) {
            int currentSelectedRow = table.getSelectionModel().getSelectedIndex() - 1;
            table.getSelectionModel().select(currentSelectedRow);
            showProduct(currentSelectedRow);
        }
    }




    //////////////////////////////////////////////////////////////////





    private void updateImage()
    {

        if(table.getSelectionModel().getSelectedItem() == null)
        {
            alert.show("Message", "Select the item that you want to update its image first", AlertType.INFORMATION);
            return;
        }

        FileChooser fileChooser = new FileChooser();

        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Select a .JPG .PNG .GIF image", "*.jpg", "*.png", "*.gif")
        );

        selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            
            try {
                String createImagePath = Common.saveSelectedImage(selectedFile);

                Connection con = Common.getConnection();
           
                String query = "UPDATE products SET image_url = ? WHERE id = ?";
                
                PreparedStatement ps = con.prepareStatement(query);
                
                ps.setString(1, createImagePath);
                ps.setInt(2, Integer.parseInt(idField.getText()));
                
                ps.executeUpdate();

                con.close();
                
                Product selectedProduct = (Product) table.getSelectionModel().getSelectedItem();

                Common.deleteImage(selectedProduct.getImageUrl());

                selectedProduct.setImageUrl(createImagePath);

                productImage.setText("");
                productImage.setGraphic(new ImageView(new Image(
                    selectedFile.toURI().toString(), 224, 224, true, true)));
            }
            catch (Exception ex) {
                alert.show("Error", "Failed to update Image", AlertType.ERROR);
            }
        }

    }





    ////////////////////////////////////////////////////////////////////////////////





    private void updateProduct()
    {

        if(table.getSelectionModel().getSelectedItem() == null)
        {
            alert.show("Message", "Select the item that you want to update first", AlertType.INFORMATION);
            return;
        }


        if (checkInputs() == false) {
            return;
        }

        Product selectedProduct = (Product) table.getSelectionModel().getSelectedItem();

        try {
            Connection con = getConnection();
            String query;

            query = "UPDATE products SET name = ?, price = ?, added_date = ? WHERE id = ?";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, nameField.getText());
            ps.setDouble(2, Double.valueOf(priceField.getText()));
            ps.setDate(3, Date.valueOf(datePicker.getEditor().getText()));
            ps.setInt(4, Integer.parseInt(idField.getText()));
            ps.executeUpdate();

            con.close();

            selectedProduct.setName(nameField.getText());
            selectedProduct.setPrice(Double.valueOf(priceField.getText()));
            selectedProduct.setAddedDate(datePicker.getEditor().getText());
            
            table.refresh();

            alert.show("Product Successfully updated",
                    "Product information has been successfully updated",
                    AlertType.INFORMATION);
        
        }
        catch (Exception e) {
            alert.show("Error", e.getMessage(), AlertType.ERROR);
        }
    }




    ///////////////////////////////////////////////////////////////////////




    private void deleteProduct()
    {

        if(table.getSelectionModel().getSelectedItem() == null)
        {
            alert.show("Message", "Select the item that you want to delete first", AlertType.INFORMATION);
            return;
        }
        
        Product selectedProduct = (Product) table.getSelectionModel().getSelectedItem();
        
        try {
            Connection con = getConnection();

            String query = "DELETE FROM products WHERE id = ?";

            PreparedStatement ps = con.prepareStatement(query);
            
            ps.setInt(1, selectedProduct.getId());

            ps.executeUpdate();

            con.close();
            
            Common.deleteImage(selectedProduct.getImageUrl());
            
            data.remove(selectedProduct);
            
            table.refresh();
            
            if(data.size() > 0) {
                showNextProduct();
            }
            else
            {
                idField.setText("");
                nameField.setText("");
                priceField.setText("");
                datePicker.getEditor().setText("");
                productImage.setText("No image found");
                productImage.setGraphic(null);
            }
            
        }
        catch (Exception e) {
            alert.show("Error", e.getMessage(), AlertType.ERROR);
        }
    }




    //////////////////////////////////////////////////////////////////////////////////




    public StringConverter dateFormatter()   // ahmed said
    {
        StringConverter converter = new StringConverter<LocalDate>() {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(dateFormat);

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                }
                return "";
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                }
                return null;
            }
        };
        return converter;
    }




    ///////////////////////////////////////////////////////////////////////////




    private Connection getConnection()
    {
        Connection con;
        try {
            con = DriverManager.getConnection(DB_NAME_WITH_ENCODING, USER, PASSWORD);
            return con;
        }
        catch (SQLException e) {
            alert.show("Connection Error", e.getMessage(), AlertType.ERROR);
            return null;
        }
    }





    //////////////////////////////////////////////////////////////////




    private void fillTheTable()
    {
        Connection con = getConnection();
        String query = "SELECT * FROM products";

        Statement st;
        ResultSet rs;

        try {
            st = con.createStatement();
            rs = st.executeQuery(query);

            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice(Double.parseDouble(rs.getString("price")));
                product.setAddedDate(rs.getDate("added_date").toString());
                product.setImageUrl(rs.getString("image_url"));

                data.add(product);
            }

            con.close();
        }
        catch (SQLException e) {
            alert.show("Error", e.getMessage(), AlertType.ERROR);
        }
    }




    //////////////////////////////////////////////////////////////////




    private boolean checkInputs()
    {
        if (nameField.getText().equals("") && priceField.getText().equals("")) {
            alert.show("Missing required Fields", "Name and Price fields cannot be empty!", AlertType.WARNING);
            return false;
        }
        else if (nameField.getText().equals("")) {
            alert.show("Missing required Fields", "Please enter product name", AlertType.WARNING);
            return false;
        }
        else if (priceField.getText().equals("")) {
            alert.show("Missing required Fields", "Please enter product price", AlertType.WARNING);
            return false;
        }

        try {
            Float.parseFloat(priceField.getText());
            return true;
        }
        catch (NumberFormatException e) {
            alert.show("Error", "Price should be a decimal number (eg: 40, 10.5)", AlertType.ERROR);
            return false;
        }
    }



}
