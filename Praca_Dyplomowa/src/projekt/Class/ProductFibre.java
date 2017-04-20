/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt.Class;

/**
 *
 * @author Andrzej Kierepka
 */
public class ProductFibre {
    private String nameProduct;
    private Double fibre;
    private Double weight; 
    public ProductFibre(String nameProduct, Double fibre) {
        this.nameProduct = nameProduct;
        this.fibre = fibre;
    }
    public ProductFibre(ProductFibre product){
        this.nameProduct=product.nameProduct;
        this.fibre=product.fibre;
        this.weight=product.weight;
    }
    public ProductFibre() {
        fibre=new Double(0);
        nameProduct= new String();
        weight = new Double(100);
    }

    public void setFibre(Double fibre) {
        this.fibre = fibre;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public Double getFibre() {
        return fibre;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getWeight() {
        return weight;
    }
    /**
     ** Metoda stworzona po to aby wyświetlć ile błonnika przyjęto 
     * @return 
     */
    @Override
    public String toString() {
        return nameProduct + " w "+ getWeight() +"g zawiera "+getFibre()*(getWeight()/100)+"g błonnika";
                
    }
}
