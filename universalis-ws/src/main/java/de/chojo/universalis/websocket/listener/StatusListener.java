/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.websocket.listener;

import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketFrame;
import de.chojo.universalis.websocket.UniversalisWsImpl;
import de.chojo.universalis.websocket.subscriber.Subscription;
import org.bson.BSONEncoder;
import org.bson.BasicBSONEncoder;
import org.bson.BasicBSONObject;
import org.slf4j.Logger;

import java.util.List;
import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Status listener for the websocket
 */
public class StatusListener extends WebSocketAdapter {
    private static final Logger log = getLogger(StatusListener.class);
    private final BSONEncoder encoder = new BasicBSONEncoder();
    private final UniversalisWsImpl universalisWs;
    private final List<Subscription> subscriptions;
    private boolean connected;

    /**
     * Creates a new status listener
     * @param universalisWs socket
     * @param subscriptions subscriptions
     */
    public StatusListener(UniversalisWsImpl universalisWs, List<Subscription> subscriptions) {
        this.universalisWs = universalisWs;
        this.subscriptions = subscriptions;
    }

    @Override
    public void onConnected(WebSocket websocket, Map<String, List<String>> headers) {
        log.info("WebSocket Connected");
        connected = true;
        for (var entry : headers.entrySet()) {
            log.trace("{}:{}", entry.getKey(), String.join(", ", entry.getValue()));
        }

        for (Subscription subscription : subscriptions) {
            subscribe(subscription);
        }
    }

    /**
     * Subscribe a route
     *
     * @param subscription subscription
     * @throws IllegalStateException When the socket is not connected
     */
    public void subscribe(Subscription subscription) {
        if (!isConnected()) {
            throw new IllegalStateException("The socket is not connected.");
        }
        for (String channel : subscription.channel()) {
            universalisWs.socket().sendBinary(subscribeChannel(channel));
            log.debug("Subscribed to channel {}", channel);
        }
    }

    /**
     * Unsubscribe a route
     *
     * @param subscription subscription
     * @throws IllegalStateException When the socket is not connected
     */
    public void unsubscribe(Subscription subscription) {
        if (!isConnected()) {
            throw new IllegalStateException("The socket is not connected.");
        }
        for (String channel : subscription.channel()) {
            universalisWs.socket().sendBinary(unsubscribeChannel(channel));
            log.debug("Unsubscribed from channel {}", channel);
        }
    }

    private byte[] unsubscribeChannel(String channel) {
        return subscriptionChange(channel, "unsubscribe");
    }

    private byte[] subscribeChannel(String channel) {
        return subscriptionChange(channel, "subscribe");
    }

    private byte[] subscriptionChange(String channel, String event) {
        BasicBSONObject payload = new BasicBSONObject().append("event", event)
                                                       .append("channel", channel);
        return encoder.encode(payload);
    }

    @Override
    public void handleCallbackError(WebSocket websocket, Throwable cause) {
        log.error("Callback error", cause);
    }

    @Override
    public void onError(WebSocket websocket, WebSocketException cause) {
        log.error("Error in websocket", cause);
        log.error("Reason: {}", cause.getError());
    }

    @Override
    public void onDisconnected(WebSocket websocket, WebSocketFrame serverCloseFrame, WebSocketFrame clientCloseFrame, boolean closedByServer) {
        connected = false;
        if (closedByServer) {
            log.info("Remote host closed the connection");
        } else {
            int code = clientCloseFrame != null ? clientCloseFrame.getCloseCode() : -1;
            String reason = clientCloseFrame != null ? clientCloseFrame.getCloseReason() : "none";
            log.info("Closed connection to remote host\nCode: {}\nReason: {}", code, reason);
        }
        universalisWs.ignite();
    }

    @Override
    public void onUnexpectedError(WebSocket websocket, WebSocketException cause) {
        log.error("Error in websocket", cause);
    }

    /**
     * True when the socket is connected.
     *
     * @return true when connected
     */
    public boolean isConnected() {
        return connected;
    }
}
