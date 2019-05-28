package co.q64.dynamicalsystems.block;

import com.google.auto.factory.AutoFactory;

import lombok.Getter;

@Getter
@AutoFactory
public class MaterialBlock extends BaseBlock {

	public MaterialBlock(String name, Settings settings) {
		super(name, settings);
	}

	public MaterialBlock(String name) {
		super(name);
	}
}
