package co.q64.dynamicalsystems.resource;

import co.q64.dynamicalsystems.resource.MultipartBuilder.MultipartPart.MultipartWhenCondition;
import com.google.auto.factory.AutoFactory;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.util.ResourceLocation;

@AutoFactory
public class MultipartBuilder {
    private JsonArray multipart = new JsonArray();

    protected MultipartBuilder() {}

    public MultipartBuilder apply(String model) {
        return part().apply(model);
    }

    public MultipartBuilder apply(String model, int x, int y) {
        return part().apply(model, x, y);
    }

    public MultipartBuilder apply(String model, int y) {
        return part().apply(model, 0, y);
    }

    public MultipartBuilder apply(String model, int x, int y, boolean uvlock) {
        return part().apply(model, x, y, uvlock);
    }

    public MultipartBuilder apply(ResourceLocation model) {
        return part().apply(model.toString());
    }

    public MultipartBuilder apply(ResourceLocation model, int x, int y) {
        return part().apply(model.toString(), x, y);
    }

    public MultipartBuilder apply(ResourceLocation model, int y) {
        return part().apply(model.toString(), y);
    }

    public MultipartBuilder apply(ResourceLocation model, int x, int y, boolean uvlock) {
        return part().apply(model.toString(), x, y, uvlock);
    }

    public MultipartPart when(String property, String condition) {
        return part().when(property, condition);
    }

    public MultipartWhenCondition or(String property, String condition) {
        return part().or();
    }

    public MultipartWhenCondition and(String property, String condition) {
        return part().and();
    }

    public MultipartPart part() {
        return new MultipartPart();
    }

    public JsonObject build() {
        JsonObject result = new JsonObject();
        result.add("multipart", multipart);
        return result;
    }

    public class MultipartPart {
        private JsonObject part = new JsonObject();

        protected MultipartPart() {}

        public MultipartPart when(String property, String condition) {
            JsonObject when = new JsonObject();
            when.addProperty(property, condition);
            part.add("when", when);
            return this;
        }

        public MultipartWhenCondition or() {
            return new MultipartWhenCondition("OR");
        }

        public MultipartWhenCondition and() {
            return new MultipartWhenCondition("AND");
        }

        public MultipartBuilder apply(ResourceLocation model) {
            return apply(model.toString());
        }

        public MultipartBuilder apply(ResourceLocation model, int x, int y) {
            return apply(model.toString(), x, y);
        }

        public MultipartBuilder apply(ResourceLocation model, int y) {
            return apply(model.toString(), y);
        }

        public MultipartBuilder apply(ResourceLocation model, int x, int y, boolean uvlock) {
            return apply(model.toString(), x, y, uvlock);
        }

        public MultipartBuilder apply(String model) {
            return apply(model, 0, 0);
        }

        public MultipartBuilder apply(String model, int y) {
            return apply(model, 0, y, false);
        }

        public MultipartBuilder apply(String model, int x, int y) {
            return apply(model, x, y, false);
        }

        public MultipartBuilder apply(String model, int x, int y, boolean uvlock) {
            JsonObject apply = new JsonObject();
            apply.addProperty("model", model);
            apply.addProperty("x", x);
            apply.addProperty("y", y);
            apply.addProperty("uvlock", uvlock);
            part.add("apply", apply);
            multipart.add(part);
            return MultipartBuilder.this;
        }

        public class MultipartWhenCondition {
            private JsonArray conditions = new JsonArray();
            private String key;

            protected MultipartWhenCondition(String key) {
                this.key = key;
            }

            public MultipartWhenCondition when(String property, String condition) {
                JsonObject when = new JsonObject();
                when.addProperty(property, condition);
                conditions.add(when);
                return this;
            }

            public MultipartPart end() {
                JsonObject when = new JsonObject();
                when.add(key, conditions);
                part.add("when", when);
                return MultipartPart.this;
            }
        }
    }
}
