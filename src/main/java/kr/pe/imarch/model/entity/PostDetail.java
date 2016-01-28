package kr.pe.imarch.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by USER on 2016-01-27.
 */
@Entity
@Table(name = "PostDetails")
@Data
public class PostDetail implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "PostDetailIDGen")
    @TableGenerator(
            name = "PostDetailIDGen", table = "SurrogateKeys",
            pkColumnName = "TableName", pkColumnValue = "PostDetails",
            valueColumnName = "KeyValue", initialValue = 0,
            allocationSize = 1
    )
    private Integer id;

    @OneToOne
    @JoinColumn(name = "PostID")
    private Post post;
    private String text;
}
