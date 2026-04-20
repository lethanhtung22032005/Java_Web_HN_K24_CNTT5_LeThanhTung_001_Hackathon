package re.java_web_hn_k24_cntt5_lethanhtung_001.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class BookDto {
    private Long id;
    @NotBlank(message = "Title không được để trống")
    @Size(min = 3, max = 100, message = "Độ dài title phải từ 3 đến 100 kí tự")
    private String title;
    @NotBlank(message = "Tên tác giả không được để trống")
    private String author;
    @Min(value = 0, message = "Số lượng tồn kho phải lớn hơn hoặc bằng 0")
    private int quantity;
    private String coverImage;

    public BookDto() {
    }

    public BookDto(Long id, String title, String author, int quantity, String coverImage) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.quantity = quantity;
        this.coverImage = coverImage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }
}