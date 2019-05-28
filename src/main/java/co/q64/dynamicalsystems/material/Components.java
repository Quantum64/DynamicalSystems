package co.q64.dynamicalsystems.material;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.components.BlockComponent;
import co.q64.dynamicalsystems.material.components.CentrifugedCrushedComponent;
import co.q64.dynamicalsystems.material.components.CentrifugedTinyCrushedComponent;
import co.q64.dynamicalsystems.material.components.ChainComponent;
import co.q64.dynamicalsystems.material.components.ChunkComponent;
import co.q64.dynamicalsystems.material.components.CrushedComponent;
import co.q64.dynamicalsystems.material.components.CurvedPlateComponent;
import co.q64.dynamicalsystems.material.components.DensePlateComponent;
import co.q64.dynamicalsystems.material.components.DoubleIngotComponent;
import co.q64.dynamicalsystems.material.components.DustComponent;
import co.q64.dynamicalsystems.material.components.ExquisiteGemComponent;
import co.q64.dynamicalsystems.material.components.FineWireComponent;
import co.q64.dynamicalsystems.material.components.FlawedGemComponent;
import co.q64.dynamicalsystems.material.components.FlawlessGemComponent;
import co.q64.dynamicalsystems.material.components.FoilComponent;
import co.q64.dynamicalsystems.material.components.GearComponent;
import co.q64.dynamicalsystems.material.components.GemComponent;
import co.q64.dynamicalsystems.material.components.HotIngotComponent;
import co.q64.dynamicalsystems.material.components.ImpureDustComponent;
import co.q64.dynamicalsystems.material.components.IngotComponent;
import co.q64.dynamicalsystems.material.components.LegendaryGemComponent;
import co.q64.dynamicalsystems.material.components.LensComponent;
import co.q64.dynamicalsystems.material.components.LongStickComponent;
import co.q64.dynamicalsystems.material.components.NuggetComponent;
import co.q64.dynamicalsystems.material.components.PlateComponent;
import co.q64.dynamicalsystems.material.components.PureDustComponent;
import co.q64.dynamicalsystems.material.components.PurifiedCrushedComponent;
import co.q64.dynamicalsystems.material.components.PurifiedTinyCrushedComponent;
import co.q64.dynamicalsystems.material.components.QuadrupleIngotComponent;
import co.q64.dynamicalsystems.material.components.QuintupleIngotComponent;
import co.q64.dynamicalsystems.material.components.RingComponent;
import co.q64.dynamicalsystems.material.components.RockComponent;
import co.q64.dynamicalsystems.material.components.ScrapComponent;
import co.q64.dynamicalsystems.material.components.ScrewComponent;
import co.q64.dynamicalsystems.material.components.SmallCasingComponent;
import co.q64.dynamicalsystems.material.components.SmallDustComponent;
import co.q64.dynamicalsystems.material.components.SmallGearComponent;
import co.q64.dynamicalsystems.material.components.SmallSpringComponent;
import co.q64.dynamicalsystems.material.components.SpringComponent;
import co.q64.dynamicalsystems.material.components.StickComponent;
import co.q64.dynamicalsystems.material.components.TinyCrushedComponent;
import co.q64.dynamicalsystems.material.components.TinyPlateComponent;
import co.q64.dynamicalsystems.material.components.TripleIngotComponent;

@Singleton
public class Components {
	public @Inject BlockComponent block;
	public @Inject CentrifugedCrushedComponent centrifugedCrushed;
	public @Inject CentrifugedTinyCrushedComponent centrifugedTinyCrushed;
	public @Inject ChainComponent chain;
	public @Inject ChunkComponent chunk;
	public @Inject CrushedComponent crushed;
	public @Inject CurvedPlateComponent curvedPlate;
	public @Inject DensePlateComponent densePlate;
	public @Inject DoubleIngotComponent doubleIngot;
	public @Inject DustComponent dust;
	public @Inject ExquisiteGemComponent exquisiteGem;
	public @Inject FineWireComponent fineWire;
	public @Inject FlawedGemComponent flawedGem;
	public @Inject FlawlessGemComponent flawlessGem;
	public @Inject FoilComponent foil;
	public @Inject GearComponent gear;
	public @Inject GemComponent gem;
	public @Inject HotIngotComponent hotIngot;
	public @Inject ImpureDustComponent impureDust;
	public @Inject IngotComponent ingot;
	public @Inject LegendaryGemComponent legendaryGem;
	public @Inject LensComponent lens;
	public @Inject LongStickComponent longStick;
	public @Inject NuggetComponent nugget;
	public @Inject PlateComponent plate;
	public @Inject PureDustComponent pureDust;
	public @Inject PurifiedCrushedComponent purifiedCrushed;
	public @Inject PurifiedTinyCrushedComponent purifiedTinyCrushed;
	public @Inject QuadrupleIngotComponent quadrupleIngot;
	public @Inject QuintupleIngotComponent quintupleIngot;
	public @Inject RingComponent ring;
	public @Inject RockComponent rock;
	public @Inject ScrapComponent scrap;
	public @Inject ScrewComponent screw;
	public @Inject SmallCasingComponent smallCasing;
	public @Inject SmallDustComponent smallDust;
	public @Inject SmallGearComponent smallGear;
	public @Inject SmallSpringComponent smallSpring;
	public @Inject SpringComponent spring;
	public @Inject StickComponent stick;
	public @Inject TinyCrushedComponent tinyCrushed;
	public @Inject TinyPlateComponent tinyPlate;
	public @Inject TripleIngotComponent tripleIngot;

	protected @Inject Components() {}

}
