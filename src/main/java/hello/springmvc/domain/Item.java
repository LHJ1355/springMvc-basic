package hello.springmvc.domain;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
public class Item {
    @NotNull(groups = UpdateCheck.class)
    private Long id;

    @NotBlank(message = "공백은 입력할 수 없습니다. {0}")
    private String itemName;

    @NotNull
    @Range(message = "범위는 {1}에서 {2}사이여야 합니다.",min = 1000, max = 100000)
    private Integer price;
    @NotNull
    @Max(value = 9999, groups = SaveCheck.class)
    private Integer quantity;

    public Item() {

    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }

    public interface SaveCheck {}
    public interface UpdateCheck {}

}
