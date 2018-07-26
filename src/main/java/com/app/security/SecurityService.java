package com.app.security;

import com.app.office.shared.api.dto.NameIdDTO;
import com.app.office.user.api.dto.CurrentUser;

public interface SecurityService {
    CurrentUser getCurrentUser();

    NameIdDTO getCurrentUserAsNameIdDTO();
}
