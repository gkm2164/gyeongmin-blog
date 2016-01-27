package kr.pe.imarch.model.entity.user;

import lombok.Data;

import javax.persistence.Embeddable;

/**
 * Created by USER on 2016-01-27.
 */
@Embeddable
@Data
public class RealName {
    public String firstName;
    public String lastName;
}
