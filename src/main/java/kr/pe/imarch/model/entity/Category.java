package kr.pe.imarch.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 2016-01-27.
 */
@Entity
@Table(name = "Categories")
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "CategoryIDGen")
    @TableGenerator(
            name = "CategoryIDGen", table = "SurrogateKeys",
            pkColumnName = "TableName", pkColumnValue = "Categories",
            valueColumnName = "KeyValue", initialValue = 0,
            allocationSize = 1
    )
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "OwnerID")
    private User owner;

    @ManyToOne
    @JoinColumn(name = "ParentID")
    private Category parent;

    private String categoryName;

    @OneToMany(mappedBy = "parent")
    private List<Category> childList = new ArrayList<>();

    @OneToMany(mappedBy = "category")
    private List<Post> postList = new ArrayList<>();
}
