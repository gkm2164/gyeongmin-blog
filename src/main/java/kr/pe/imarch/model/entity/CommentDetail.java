package kr.pe.imarch.model.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by USER on 2016-01-27.
 */
@Entity
@Table(name = "CommentDetails")
@Data
public class CommentDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "CommentDetailIDGen")
    @TableGenerator(
            name = "CommentDetailIDGen", table = "SurrogateKeys",
            pkColumnName = "TableName", pkColumnValue = "CommentDetails",
            valueColumnName = "KeyValue", initialValue = 0,
            allocationSize = 1
    )
    private Integer id;

    @OneToOne
    @JoinColumn(name = "CommentID")
    private Comment comment;
    private String text;
}
