package co.q64.dynamicalsystems.client.gui.bar;

public enum DefaultBars implements Bar {
    ARROW_RIGHT(BarDirection.RIGHT, "arrow_right", "arrow_right_filled", 32, 16);

    private BarDirection direction;
    private String base;
    private String overlay;
    private int width;
    private int height;

    private DefaultBars(BarDirection direction, String base, String overlay, int width, int height) {
        this.direction = direction;
        this.base = "textures/gui/" + base + ".png";
        this.overlay = "textures/gui/" + overlay + ".png";
        this.width = width;
        this.height = height;
    }

    public BarDirection getDirection() {
        return direction;
    }

    public String getBaseTexture() {
        return base;
    }

    public String getOverlayTexture() {
        return overlay;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
