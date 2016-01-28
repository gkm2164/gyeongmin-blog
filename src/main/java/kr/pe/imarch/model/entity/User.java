package kr.pe.imarch.model.entity;

import kr.pe.imarch.model.entity.user.UserType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 2016-01-27.
 */
@Entity
@Table(name = "Users")
@Data
@EqualsAndHashCode(exclude = {"userDetail", "categoryList", "postList", "commentList"})
public class User implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.TABLE, generator = "UserIDGen")
    @TableGenerator(
            name = "UserIDGen", table = "SurrogateKeys",
            pkColumnName = "TableName", pkColumnValue = "Users",
            valueColumnName = "KeyValue", initialValue = 0,
            allocationSize = 1
    )
    @Column(name = "ID")
    private Integer id;

    @Column(name = "Email")
    private String email;

    @Column(name = "Password")
    private String password;

    @Column(name = "NickName")
    private String nickName;

    @Column(name = "UserType")
    @Enumerated(EnumType.STRING)
    private UserType userType;

    @Column(name = "CreatedTime")
    private Timestamp createdTime;

    @Column(name = "Deleted", columnDefinition = "TINYINT")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean deleted;

    @OneToOne(mappedBy = "user")
    private UserDetail userDetail;

    @OneToMany(mappedBy = "owner")
    private List<Category> categoryList = new ArrayList<>();

    @OneToMany(mappedBy = "owner")
    private List<Post> postList = new ArrayList<>();

    @OneToMany(mappedBy = "owner")
    private List<Comment> commentList = new ArrayList<>();
}
