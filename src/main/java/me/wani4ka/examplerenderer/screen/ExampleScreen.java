package me.wani4ka.examplerenderer.screen;

import com.mojang.authlib.GameProfile;
import io.wispforest.owo.ui.base.BaseOwoScreen;
import io.wispforest.owo.ui.component.Components;
import io.wispforest.owo.ui.component.EntityComponent;
import io.wispforest.owo.ui.container.Containers;
import io.wispforest.owo.ui.container.FlowLayout;
import io.wispforest.owo.ui.core.*;
import net.minecraft.client.gui.screen.Screen;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class ExampleScreen extends BaseOwoScreen<FlowLayout> {

    protected final Screen parent;

    public ExampleScreen(Screen parent) {
        super();
        this.parent = parent;
    }

    @Override
    protected @NotNull OwoUIAdapter<FlowLayout> createAdapter() {
        return OwoUIAdapter.create(this, Containers::verticalFlow);
    }

    @Override
    protected void build(FlowLayout rootComponent) {

        rootComponent
                .child(
                        Components.entity(Sizing.fill(100), new ExamplePlayerEntity())
                                .allowMouseRotation(true)
                                .sizing(Sizing.fixed(50), Sizing.fixed(69))
                )
                .surface(Surface.VANILLA_TRANSLUCENT)
                .horizontalAlignment(HorizontalAlignment.CENTER)
                .verticalAlignment(VerticalAlignment.CENTER);
    }

    @Override
    public void close() {
        super.close();
        if (parent != null) {
            client.setScreen(parent);
        }
    }

    protected static class ExamplePlayerEntity extends EntityComponent.RenderablePlayerEntity {

        protected ExamplePlayerEntity() {
            super(new GameProfile(UUID.randomUUID(), "test"));
        }
    }
}
