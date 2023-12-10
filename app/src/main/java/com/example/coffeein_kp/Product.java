package com.example.coffeein_kp;

public class Product {
    private String Name;
    private String Description;
    private Integer Quantity;
    private String Calories;
    private String Image;
    private String Compound;
    private String Category;
    private String DocumentID;

   public Product(String Name, String Description, Integer Quantity, String Calories, String Image, String Compound, String Category, String DocumentID){
        this.Name = Name;
        this.Description = Description;
        this.Quantity = Quantity;
        this.Calories = Calories;
        this.Image = Image;
        this.Compound = Compound;
        this.Category = Category;
        this.DocumentID = DocumentID;
    }

    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }
    public void setDescription(String description) {
        Description = description;
    }

    public Integer getQuantity() {
        return Quantity;
    }
    public void setQuantity(Integer quantity) {
        Quantity = quantity;
    }

    public String getCalories() {
        return Calories;
    }
    public void setCalories(String calories) {
        Calories = calories;
    }

    public String getImage() {
        return Image;
    }
    public void setImage(String image) {
        Image = image;
    }

    public String getCompound() {
        return Compound;
    }
    public void setCompound(String compound) {
        Compound = compound;
    }

    public String getCategory() {
        return Category;
    }
    public void setCategory(String category) {
        Category = category;
    }

    public String getDocumentID() {
        return DocumentID;
    }
    public void setDocumentID(String documentID) {
        DocumentID = documentID;
    }
}
