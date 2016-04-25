package com.decorpot.rest.model;

import java.util.List;

public class PastWork {

    private Integer id;
    private List<String> images;
    private String apartmentName;
    private String mainImage;
    private String mainImageHd;
    
    private List<PastWorkImageUrls> imageUrls;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public List<String> getImages() {
        return images;
    }
    public void setImages(List<String> images) {
        this.images = images;
    }
    public String getApartmentName() {
        return apartmentName;
    }
    public void setApartmentName(String apartmentName) {
        this.apartmentName = apartmentName;
    }
    public List<PastWorkImageUrls> getImageUrls() {
        return imageUrls;
    }
    public void setImageUrls(List<PastWorkImageUrls> imageUrls) {
        this.imageUrls = imageUrls;
    }
    public String getMainImage() {
        return mainImage;
    }
    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }
    public String getMainImageHd() {
        return mainImageHd;
    }
    public void setMainImageHd(String mainImageHd) {
        this.mainImageHd = mainImageHd;
    }
    
}
