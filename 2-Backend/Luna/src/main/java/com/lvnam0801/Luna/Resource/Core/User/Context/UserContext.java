package com.lvnam0801.Luna.Resource.Core.User.Context;

import org.springframework.stereotype.Component;

@Component
public class UserContext {
    public int getCurrentUserID() {
        // Temporary mock
        return 3;  // replace later with actual authenticated user id
    }
}