/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.connection.listener;

import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketFrame;
import de.chojo.universalis.connection.UniversalisWsImpl;
import de.chojo.universalis.subscriber.Subscription;
import org.bson.BSONEncoder;
import org.bson.BasicBSONEncoder;
import org.bson.BasicBSONObject;
import org.slf4j.Logger;

import java.util.List;
import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;

public class StatusListener extends WebSocketAdapter {
    private static final Logger log = getLogger(StatusListener.class);
    private final BSONEncoder encoder = new BasicBSONEncoder();
    private final UniversalisWsImpl universalisWs;
    private final List<Subscription> subscriptions;
    private boolean connected;

    public StatusListener(UniversalisWsImpl universalisWs, List<Subscription> subscriptions) {
        this.universalisWs = universalisWs;
        this.subscriptions = subscriptions;
    }

    @Override
    public void onConnected(WebSocket websocket, Map<String, List<String>> headers) throws Exception {
        log.info("WebSocket Connected");
        connected = true;
        for (var entry : headers.entrySet()) {
            log.trace("{}:{}", entry.getKey(), String.join(", ", entry.getValue()));
        }

        for (Subscription subscription : subscriptions) {
            for (String channel : subscription.channel()) {
                websocket.sendBinary(subscribeChannel(channel));
                log.debug("Subscribed to channel {}", channel);
            }
        }
    }

    public void subscibe(Subscription subscription) {
        if (!isConnected()) {
            throw new IllegalStateException("The socket is not connected.");
        }
        for (String channel : subscription.channel()) {
            universalisWs.socket().sendBinary(subscribeChannel(channel));
            log.debug("Subscribed to channel {}", channel);
        }
    }

    public void unsubscribe(Subscription subscription) {
        if (!isConnected()) {
            throw new IllegalStateException("The socket is not connected.");
        }
        for (String channel : subscription.channel()) {
            universalisWs.socket().sendBinary(unsubscribeChannel(channel));
            log.debug("Subscribed to channel {}", channel);
        }
    }

    private byte[] unsubscribeChannel(String channel) {
        return subscriptionChange(channel, "unsubscribe");
    }

    private byte[] subscribeChannel(String channel) {
        return subscriptionChange(channel, "subscribe");
    }

    private byte[] subscriptionChange(String channel, String event){
        BasicBSONObject payload = new BasicBSONObject().append("event", event)
                                                       .append("channel", channel);
        return encoder.encode(payload);
    }

    @Override
    public void handleCallbackError(WebSocket websocket, Throwable cause) throws Exception {
        log.error("Callback error", cause);
    }

    @Override
    public void onError(WebSocket websocket, WebSocketException cause) throws Exception {
        log.error("Error in websocket", cause);
        log.error("Reason: {}", cause.getError());
    }

    @Override
    public void onDisconnected(WebSocket websocket, WebSocketFrame serverCloseFrame, WebSocketFrame clientCloseFrame, boolean closedByServer) throws Exception {
        connected = false;
        if (closedByServer) {
            log.info("Remote host closed the connection");
        } else {
            log.info("Closed connection to remote host");
        }
        universalisWs.ignite();
    }

    @Override
    public void onUnexpectedError(WebSocket websocket, WebSocketException cause) throws Exception {
        log.error("Error in websocket", cause);
    }

    public boolean isConnected() {
        return connected;
    }
}
