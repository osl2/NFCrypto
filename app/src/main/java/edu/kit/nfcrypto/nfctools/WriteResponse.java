package edu.kit.nfcrypto.nfctools;

public class WriteResponse {
    private int status;
    private String message;

    WriteResponse(int Status, String Message) {
        this.status = Status;
        this.message = Message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
