/*
 * Copyright 2020 Monotonic Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package uk.co.real_logic.artio.library;

import org.agrona.sbe.MessageEncoderFlyweight;
import uk.co.real_logic.artio.messages.DisconnectReason;

// NB: This is an experimental API and is subject to change or potentially removal.
public abstract class ILink3Session
{
    public static final long NOT_AWAITING_RETRANSMIT = -1L;

    public enum State
    {
        /** TCP connection established, negotiate not sent.*/
        CONNECTED,
        /** Negotiate sent but no reply received */
        SENT_NEGOTIATE,
        RETRY_NEGOTIATE,

        NEGOTIATE_REJECTED,
        /** Negotiate accepted, Establish not sent */
        NEGOTIATED,
        /** Negotiate accepted, Establish sent */
        SENT_ESTABLISH,
        RETRY_ESTABLISH,
        ESTABLISH_REJECTED,
        /** Establish accepted, messages can be exchanged */
        ESTABLISHED,
        RETRANSMITTING,
        AWAITING_KEEPALIVE,
        RESEND_TERMINATE,
        RESEND_TERMINATE_ACK,
        UNBINDING,
        SENT_TERMINATE,
        UNBOUND
    }

    // -----------------------------------------------
    // Operations
    // -----------------------------------------------

    public abstract long claimMessage(MessageEncoderFlyweight message);

    public abstract void commit();

    public abstract long requestDisconnect(DisconnectReason reason);

    public abstract long terminate(String shutdown, int errorCodes);

    // -----------------------------------------------
    // Accessors
    // -----------------------------------------------

    public abstract long uuid();

    public abstract long connectionId();

    public abstract State state();

    public abstract long nextSentSeqNo();

    public abstract void nextSentSeqNo(long nextSentSeqNo);

    public abstract long nextRecvSeqNo();

    public abstract void nextRecvSeqNo(long nextRecvSeqNo);

    public abstract long retransmitFillSeqNo();

    // -----------------------------------------------
    // Internal Methods below, not part of public API
    // -----------------------------------------------

    abstract int poll(long timeInMs);

    abstract void onReplayComplete();

}
