package pojo;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//去重(EqualsAndHashCode) (of true, exclude false)
@EqualsAndHashCode(of = "id")
//@EqualsAndHashCode(of = {"id","name"})
@ToString
public class Category {
    private Integer id;

    private Integer parentId;

    private String name;

    private Boolean status;

    private Integer sortOrder;

    private Date createTime;

    private Date updateTime;

}