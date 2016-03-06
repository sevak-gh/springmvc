package com.infotech.springmvc.web.conversion;

import com.infotech.springmvc.domain.Role;

import java.beans.PropertyEditorSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * customr editor for role.
 *
 * @author Sevak Gharibian
 *
 */
public class CustomRoleEditor extends PropertyEditorSupport {

    private static final Logger LOG = LoggerFactory.getLogger(CustomRoleEditor.class);
    
    @Override
    public String getAsText() {
        Role role = (Role)getValue();
        LOG.debug("role convert to text: {} ---> {}", role.toString(), String.valueOf(role.getId()));
        return String.valueOf(role.getId());
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        int id = Integer.valueOf(text);
        Role role = new Role();
        role.setId((long)id);
        LOG.debug("text convert to role: {} ---> {}", text, role.toString());
        setValue(role);
    }
}
