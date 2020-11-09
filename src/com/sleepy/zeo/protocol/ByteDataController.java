package com.sleepy.zeo.protocol;

import java.util.ArrayList;

public class ByteDataController {

    private static final byte BYTE_QUOTE = 0x0B;
    private static final byte BYTE_ESC = 0x1E;

    private static final int STATUS_WAITING_FOR_QUOTE = 1;
    private static final int STATUS_WAITING_FOR_DATA = 2;
    private static final int STATUS_WAITING_FOR_ESC_XXX = 3;

    public static byte[] serializeData(byte[] data) {
        if (data == null || data.length == 0) {
            return new byte[]{BYTE_QUOTE, BYTE_QUOTE};
        }

        ArrayList<Byte> bufferData = new ArrayList<>();
        bufferData.add(BYTE_QUOTE);
        for (byte b : data) {
            if (b == BYTE_QUOTE) {
                bufferData.add(BYTE_ESC);
                bufferData.add(BYTE_QUOTE);
            } else if (b == BYTE_ESC) {
                bufferData.add(BYTE_ESC);
                bufferData.add(BYTE_ESC);
            } else {
                bufferData.add(b);
            }
        }
        bufferData.add(BYTE_QUOTE);

        byte[] serializedData = new byte[bufferData.size()];
        int index = 0;
        for (byte b : bufferData) {
            serializedData[index++] = b;
        }
        return serializedData;
    }

    public static byte[] processSingleByte(Packet packet, byte b) throws Exception {
        if (packet.waitingStatus == STATUS_WAITING_FOR_QUOTE) {
            if (b == BYTE_QUOTE) {
                packet.waitingStatus = STATUS_WAITING_FOR_DATA;
                packet.processedData = new ArrayList<>();
            }
        } else if (packet.waitingStatus == STATUS_WAITING_FOR_DATA) {
            if (b == BYTE_ESC) {
                packet.waitingStatus = STATUS_WAITING_FOR_ESC_XXX;
            } else if (b == BYTE_QUOTE) {
                // produce data
                byte[] processedData = new byte[packet.processedData.size()];
                int index = 0;
                for (byte b_ : packet.processedData) {
                    processedData[index++] = b_;
                }
                packet.reset();
                return processedData;
            } else {
                packet.processedData.add(b);
            }
        } else {
            // waitingStatus == STATUS_WAITING_FOR_ESC_XXX
            if (b == BYTE_QUOTE) {
                packet.waitingStatus = STATUS_WAITING_FOR_DATA;
                packet.processedData.add(BYTE_QUOTE);
            } else if (b == BYTE_ESC) {
                packet.waitingStatus = STATUS_WAITING_FOR_DATA;
                packet.processedData.add(BYTE_ESC);
            } else {
                throw new Exception("Process byte error, unexpected byte after esc");
            }
        }
        return null;
    }

    public static class Packet {
        public int waitingStatus;
        public ArrayList<Byte> processedData;

        public Packet() {
            reset();
        }

        public void reset(){
            waitingStatus = STATUS_WAITING_FOR_QUOTE;
        }
    }
}
