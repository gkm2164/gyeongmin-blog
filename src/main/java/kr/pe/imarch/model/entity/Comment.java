package kr.pe.imarch.model.entity;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by USER on 2016-01-27.
 */
@Entity
@Table(name = "Comments")
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "CommentIDGen")
    @TableGenerator(
            name = "CommentIDGen", table = "SurrogateKeys",
            pkColumnName = "TableName", pkColumnValue = "Comments",
            valueColumnName = "KeyValue", initialValue = 0,
            allocationSize = 1
    )
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "OwnerID")
    private User owner;

    @ManyToOne
    @JoinColumn(name = "PostID")
    private Post post;
    private Timestamp createdTime;
    private Timestamp modifiedTime;

    @Column(name = "Deleted", columnDefinition = "TINYINT")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean deleted;

    @OneToOne(mappedBy = "comment")
    private CommentDetail commentDetail;
}
