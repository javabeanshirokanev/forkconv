package org.test.mocks;

public class Tool {
    private final InnerTool innerTool;

    public Tool(InnerTool innerTool) {
        this.innerTool = innerTool;
    }

    public String getUser() {
        return "Name is " + innerTool.getName();
    }
}
