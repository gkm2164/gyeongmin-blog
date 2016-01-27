package kr.pe.imarch.model.entity;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 2016-01-27.
 */
@Entity
@Table(name = "Posts")
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "PostIDGen")
    @TableGenerator(
            name = "PostIDGen", table = "SurrogateKeys",
            pkColumnName = "TableName", pkColumnValue = "Posts",
            valueColumnName = "KeyValue", initialValue = 0,
            allocationSize = 1
    )
    @Column(name = "ID")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "OwnerID")
    private User owner;

    @ManyToOne
    @JoinColumn(name = "CategoryID")
    private Category category;

    @Column(name = "Title")
    private String title;

    @Column(name = "CreatedTime")
    private Timestamp createdTime;

    @Column(name = "ModifiedTime")
    private Timestamp modifiedTime;

    @Column(name = "Deleted", columnDefinition = "TINYINT")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean deleted;

    @OneToOne(mappedBy = "post")
    private PostDetail postDetail;

    @OneToMany(mappedBy = "post")
    private List<Comment> commentList = new ArrayList<>();
}
