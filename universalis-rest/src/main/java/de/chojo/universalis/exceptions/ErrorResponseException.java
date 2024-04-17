/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.exceptions;

public class ErrorResponseException extends ResponseException {
    public ErrorResponseException(int code, String message) {
        super("Received error code " + code + ": " + message);
    }
}
