package com.infotech.springmvc.web.conversion;

import com.infotech.springmvc.domain.Permission;

import java.beans.PropertyEditorSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * customr editor for permission.
 *
 * @author Sevak Gharibian
 *
 */
public class CustomPermissionEditor extends PropertyEditorSupport {

    private static final Logger LOG = LoggerFactory.getLogger(CustomPermissionEditor.class);
    
    @Override
    public String getAsText() {
        Permission permission = (Permission)getValue();
        LOG.debug("permission convert to text: {} ---> {}", permission.toString(), String.valueOf(permission.getId()));
        return String.valueOf(permission.getId());
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        int id = Integer.valueOf(text);
        Permission permission = new Permission();
        permission.setId((long)id);
        LOG.debug("text convert to permission: {} ---> {}", text, permission.toString());
        setValue(permission);
    }
}
