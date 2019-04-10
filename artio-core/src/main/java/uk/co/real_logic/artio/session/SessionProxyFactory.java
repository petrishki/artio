/*
 * Copyright 2015-2019 Real Logic Ltd., Adaptive Financial Consulting Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package uk.co.real_logic.artio.session;

import org.agrona.concurrent.EpochClock;
import uk.co.real_logic.artio.protocol.GatewayPublication;

public interface SessionProxyFactory
{
    SessionProxy make(
        int sessionBufferSize,
        GatewayPublication gatewayPublication,
        SessionIdStrategy sessionIdStrategy,
        SessionCustomisationStrategy customisationStrategy,
        EpochClock clock,
        long connectionId,
        int libraryId);
}
