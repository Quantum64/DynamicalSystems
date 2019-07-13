package co.q64.dynamicalsystems.client.texture;

import ar.com.hjg.pngj.ImageLineInt;
import ar.com.hjg.pngj.PngReader;
import ar.com.hjg.pngj.PngWriter;
import co.q64.dynamicalsystems.resource.ResourcePackGenerator;
import com.google.auto.factory.AutoFactory;
import com.google.auto.factory.Provided;
import net.minecraft.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.concurrent.atomic.AtomicLong;

@AutoFactory
public class AlphaMapSprite {
    private static final boolean DEBUG_WRITE_TO_FILE = false;

    private ResourceLocation identifier, base, overlay;
    private ResourcePackGenerator resourcePackGenerator;

    protected AlphaMapSprite(ResourceLocation identifier, ResourceLocation base, ResourceLocation overlay, @Provided ResourcePackGenerator resourcePackGenerator) {
        this.resourcePackGenerator = resourcePackGenerator;
        this.identifier = new ResourceLocation(identifier.getNamespace(), "textures/" + identifier.getPath() + ".png");
        this.base = new ResourceLocation(base.getNamespace(), "textures/" + base.getPath() + ".png");
        this.overlay = new ResourceLocation(overlay.getNamespace(), "textures/" + overlay.getPath() + ".png");
        AtomicLong longd;
    }

    public void load(IResourceManager manager) {
        try {
            PngReader baseReader = new PngReader(manager.getResource(base).getInputStream());
            PngReader overlayReader = new PngReader(manager.getResource(overlay).getInputStream());

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PngWriter writer = new PngWriter(outputStream, baseReader.imgInfo);

            while (baseReader.hasMoreRows()) {
                ImageLineInt baseLine = (ImageLineInt) baseReader.readRow();
                if (!overlayReader.hasMoreRows()) {
                    writer.writeRow(baseLine);
                    continue;
                }
                ImageLineInt overlayLine = (ImageLineInt) overlayReader.readRow();
                int[] baseScanline = baseLine.getScanline();
                int[] overlayScanline = overlayLine.getScanline();
                for (int col = 0; col < baseScanline.length; ) {
                    for (int i = 0; i < 3; i++) {
                        col++;
                    }
                    if (col < overlayScanline.length) {
                        baseScanline[col] = overlayScanline[col] == 0 ? baseScanline[col] : 0x00000000;
                    }
                    col++;
                }
                writer.writeRow(baseLine);
            }

            baseReader.end();
            overlayReader.end();
            writer.end();

            resourcePackGenerator.getVirtualResourcePack().put(identifier, outputStream.toByteArray());
            if (DEBUG_WRITE_TO_FILE) {
                File file = new File("E:\\" + identifier.getPath() + ".png");
                file.getParentFile().mkdirs();
                FileOutputStream os = new FileOutputStream(file);
                os.write(outputStream.toByteArray());
                os.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
