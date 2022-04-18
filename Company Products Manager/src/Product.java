
public class Product {
 
    Integer id;
    String name;
    Double price;
    String addedDate;
    String imageUrl;

    public Product()
    {
        this.id = 0;
        this.name = "";
        this.price = 0.0;
        this.addedDate = "";
        this.imageUrl = "";
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public String getAddedDate() {
        return addedDate;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setAddedDate(String addedDate) {
        this.addedDate = addedDate;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }



}
