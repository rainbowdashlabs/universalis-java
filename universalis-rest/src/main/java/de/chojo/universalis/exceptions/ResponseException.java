/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.exceptions;

public class ResponseException extends RuntimeException{
    public ResponseException(String message) {
        super(message);
    }
    public ResponseException(String message, Throwable cause) {
        super(message, cause);
    }
}
