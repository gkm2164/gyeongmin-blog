package kr.pe.imarch.model.entity;

import kr.pe.imarch.model.entity.user.RealName;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

/**
 * Created by USER on 2016-01-27.
 */
@Entity
@Table(name = "UserDetails")
@Data
public class UserDetail implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.TABLE, generator = "UserDetailIDGen")
    @TableGenerator(
            name = "UserDetailIDGen", table = "SurrogateKeys",
            pkColumnName = "TableName", pkColumnValue = "UserDetails",
            valueColumnName = "KeyValue", initialValue = 0,
            allocationSize = 1
    )
    private Integer id;

    @OneToOne
    @JoinColumn(name = "UserID")
    private User user;

    @Embedded
    private RealName name;

    private String blogAddress;

    private Date birthDate;
}
