/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.routes.requests;

import de.chojo.universalis.rest.UniversalisRestImpl;
import de.chojo.universalis.rest.requests.RequestBuilder;
import de.chojo.universalis.rest.response.UserListResponse;
import de.chojo.universalis.rest.routes.api.UserListRequest;

/**
 * Implementation for a {@link UserListRequest}
 */
public class UserListRequestImpl extends RequestBuilder<UserListResponse> implements UserListRequest {
    /**
     * Create a new user list request
     *
     * @param rest   rest client
     * @param listId id of the list
     */
    public UserListRequestImpl(UniversalisRestImpl rest, String listId) {
        super(rest, UserListResponse.class);
        path("lists", listId);
    }
}
