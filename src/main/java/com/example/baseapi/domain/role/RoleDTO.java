package com.example.baseapi.domain.role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Edward Reyes
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO {

    private int noRole;
    private String roleName;
    private boolean selected;

}
