/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) Rainbowdashlabs and Contributor
 */

package de.chojo.universalis.websocket.builder;

import com.neovisionaries.ws.client.DualStackMode;
import com.neovisionaries.ws.client.WebSocketFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocketFactory;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.URI;

/**
 * Class to build a {@link WebSocketFactory}. Results in a {@link UniversalisWsBuilder}
 */
public class WebsocketFactoryBuilder {
    private final WebSocketFactory factory = new WebSocketFactory();

    /**
     * Set an SSL socket factory.
     * See {@link WebSocketFactory#createSocket(URI)} for details.
     *
     * @param factory An SSL socket factory.
     * @return {@code this} instance.
     */
    public WebsocketFactoryBuilder setSSLSocketFactory(SSLSocketFactory factory) {
        this.factory.setSSLSocketFactory(factory);
        return this;
    }

    /**
     * Set an SSL context to get a socket factory.
     * See {@link WebSocketFactory#createSocket(URI)} for details.
     *
     * @param context An SSL context.
     * @return {@code this} instance.
     */
    public WebsocketFactoryBuilder setSSLContext(SSLContext context) {
        factory.setSSLContext(context);
        return this;
    }

    /**
     * Set the timeout value in milliseconds for socket connection.
     * A timeout of zero is interpreted as an infinite timeout.
     *
     * @param timeout The connection timeout value in milliseconds.
     * @return {@code this} object.
     * @throws IllegalArgumentException The given timeout value is negative.
     * @since 1.10
     */
    public WebsocketFactoryBuilder setConnectionTimeout(int timeout) {
        factory.setConnectionTimeout(timeout);
        return this;
    }

    /**
     * Set the timeout value in milliseconds for socket read and write operations.
     * A timeout of zero is interpreted as an infinite timeout.
     *
     * <p>
     * This can be changed later with {@code getSocket().setSoTimeout(int)}.
     * </p>
     *
     * @param timeout The socket timeout value in milliseconds.
     * @return {@code this} object.
     * @throws IllegalArgumentException The given timeout value is negative.
     * @see Socket#setSoTimeout(int)
     * @since 2.14
     */
    public WebsocketFactoryBuilder setSocketTimeout(int timeout) {
        factory.setSocketTimeout(timeout);
        return this;
    }

    /**
     * Set the dual stack mode that will be applied when establishing a socket
     * connection.
     *
     * @param mode The dual stack mode to be applied.
     * @return {@code this} object.
     */
    public WebsocketFactoryBuilder setDualStackMode(DualStackMode mode) {
        factory.setDualStackMode(mode);
        return this;
    }

    /**
     * Set the dual stack fallback delay in milliseconds that will be applied
     * when establishing a socket connection.
     *
     * @param delay The dual stack fallback delay in milliseconds.
     * @return {@code this} object.
     */
    public WebsocketFactoryBuilder setDualStackFallbackDelay(int delay) {
        factory.setDualStackFallbackDelay(delay);
        return this;
    }

    /**
     * Set the flag which indicates whether the hostname in the
     * server's certificate should be verified or not. The default
     * value is {@code true}.
     *
     * <p>
     * Manual hostname verification has been enabled since the version
     * 2.1. Because the verification is executed manually after {@code
     * Socket.}{@link Socket#connect(SocketAddress, int)
     * connect(SocketAddress, int)}
     * succeeds, the hostname verification is always executed even if
     * you has passed an {@link SSLContext} which naively accepts any
     * server certificate (e.g. <code><a href=
     * "https://gist.github.com/TakahikoKawasaki/d07de2218b4b81bf65ac"
     * >NaiveSSLContext</a></code>). However, this behavior is not
     * desirable in some cases and you may want to disable the hostname
     * verification. This setter method exists for the purpose and you
     * can disable hostname verification by passing {@code false} to
     * this method.
     * </p>
     *
     * @param verifyHostname {@code true} to enable hostname verification.
     *                       {@code false} to disable hostname verification.
     * @return {@code this} object.
     * @since 2.3
     */
    public WebsocketFactoryBuilder setVerifyHostname(boolean verifyHostname) {
        factory.setVerifyHostname(verifyHostname);
        return this;
    }

    /**
     * Set server names for SNI (Server Name Indication).
     * <p>
     * If {@code setServerNames(List<SNIServerName>)} method of
     * {@link SSLParameters SSLParameters} class is available
     * in the underlying system, the method is called to set up server names
     * for SNI (Server Name Indication).
     *
     * @param serverNames List of host names.
     * @return {@code this} object.
     * @since 2.4
     */
    public WebsocketFactoryBuilder setServerNames(String[] serverNames) {
        factory.setServerNames(serverNames);
        return this;
    }

    /**
     * Set a server name for SNI (Server Name Indication).
     * <p>
     * This method internally creates a String array of size 1 which
     * contains the given {@code serverName} and calls {@link
     * #setServerNames(String[])}.
     *
     * @param serverName A host name.
     * @return {@code this} object.
     * @since 2.4
     */
    public WebsocketFactoryBuilder setServerName(String serverName) {
        factory.setServerName(serverName);
        return this;
    }

    /**
     * Build the factory and returns an universalis websocket builder with the factory.
     *
     * @return universalis websocket builder
     */
    public UniversalisWsBuilder build() {
        return new UniversalisWsBuilder(factory);
    }
}
