package co.q64.dynamicalsystems.resource;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TranslationTextComponent;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

@Singleton
public class Translations {
    private List<DelegateTextComponent> components = new ArrayList<>();
    private TranslationService service;
    
    public ITextComponent
            playerInventory = register("Player Inventory"),
            configuration = register("Configuration"),
            machineInfo = register("Machine Info");

    @Inject
    protected Translations(TranslationService service) {
        this.service = service;
    }

    public void init() {
        for (DelegateTextComponent component : components) {
            component.get();
        }
    }

    private ITextComponent register(String string) {
        DelegateTextComponent result = new DelegateTextComponent(() -> service.registerTranslation(string));
        components.add(result);
        return result;
    }

    private static class DelegateTextComponent implements ITextComponent {
        private Supplier<TranslationTextComponent> componentSupplier;
        private TranslationTextComponent component;

        private DelegateTextComponent(Supplier<TranslationTextComponent> componentSupplier) {
            this.componentSupplier = componentSupplier;
        }

        private TranslationTextComponent get() {
            if (component == null) {
                component = componentSupplier.get();
            }
            return component;
        }

        public ITextComponent setStyle(Style style) {
            return get().setStyle(style);
        }

        public Style getStyle() {
            return get().getStyle();
        }

        public ITextComponent appendSibling(ITextComponent iTextComponent) {
            return get().appendSibling(iTextComponent);
        }

        public String getUnformattedComponentText() {
            return get().getUnformattedComponentText();
        }

        public List<ITextComponent> getSiblings() {
            return get().getSiblings();
        }

        public Stream<ITextComponent> stream() {
            return get().stream();
        }

        public ITextComponent shallowCopy() {
            return get().shallowCopy();
        }
    }
}
