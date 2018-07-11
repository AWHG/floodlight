/**
 *    Copyright 2013, Big Switch Networks, Inc.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License"); you may
 *    not use this file except in compliance with the License. You may obtain
 *    a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 *    WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 *    License for the specific language governing permissions and limitations
 *    under the License.
 **/

package net.floodlightcontroller.linkdiscovery.web;

import java.io.IOException;

import benbi.util.security.SEAlgorithm;
import org.projectfloodlight.openflow.types.DatapathId;
import org.projectfloodlight.openflow.types.OFPort;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import net.floodlightcontroller.linkdiscovery.ILinkDiscovery.LinkDirection;
import net.floodlightcontroller.linkdiscovery.ILinkDiscovery.LinkType;
import net.floodlightcontroller.routing.Link;

/**
 * This class is both the datastructure and the serializer
 * for a link with the corresponding type of link.
 * @author alexreimers
 */
@JsonSerialize(using=LinkWithType.class)
public class LinkWithType extends JsonSerializer<LinkWithType> {
    public DatapathId srcSwDpid;
    public OFPort srcPort;
    public DatapathId dstSwDpid;
    public OFPort dstPort;
    public LinkType type;
    public LinkDirection direction;

    // Do NOT delete this, it's required for the serializer
    public LinkWithType() {}

    public LinkWithType(Link link,
            LinkType type,
            LinkDirection direction) {


        this.srcSwDpid = link.getSrc();
        this.srcPort = link.getSrcPort();
        this.dstSwDpid = link.getDst();
        this.dstPort = link.getDstPort();
        this.type = type;
        this.direction = direction;
    }

    @Override
    public void serialize(LinkWithType lwt, JsonGenerator jgen, SerializerProvider arg2)
            throws IOException, JsonProcessingException {
        //TODO
        byte[] key = "12345".getBytes();
//        byte[] IV = {100, -85, -55, 85, -72, 17, 16, -70, 64, 101, -128, -25, 76, 74, -96, 67};
        byte[] IV = SEAlgorithm.generateIV();

        // You ****MUST*** use lwt for the fields as it's actually a different object.
        jgen.writeStartObject();
        jgen.writeStringField(SEAlgorithm.streamEnc("src-switch", key, IV), SEAlgorithm.streamEnc(lwt.srcSwDpid.toString(), key, IV));
        jgen.writeNumberField(SEAlgorithm.streamEnc("src-port", key, IV), lwt.srcPort.getPortNumber());
        jgen.writeStringField(SEAlgorithm.streamEnc("dst-switch", key, IV), SEAlgorithm.streamEnc(lwt.dstSwDpid.toString(), key, IV));
        jgen.writeNumberField(SEAlgorithm.streamEnc("dst-port", key, IV), lwt.dstPort.getPortNumber());
        jgen.writeStringField(SEAlgorithm.streamEnc("type",key, IV), SEAlgorithm.streamEnc(lwt.type.toString(), key, IV));
        jgen.writeStringField(SEAlgorithm.streamEnc("direction",key, IV), SEAlgorithm.streamEnc(lwt.direction.toString(), key, IV));
        jgen.writeEndObject();
    }

    @Override
    public Class<LinkWithType> handledType() {
        return LinkWithType.class;
    }
}