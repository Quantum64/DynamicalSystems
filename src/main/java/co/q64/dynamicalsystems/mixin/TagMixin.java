package co.q64.dynamicalsystems.mixin;

import net.minecraft.tag.Tag;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Collection;

@Mixin(Tag.class)
public interface TagMixin<T> {
    public @Accessor Collection<Tag.Entry<T>> getEntries();

    public @Accessor void setEntries(Collection<Tag.Entry<T>> entries);
}
